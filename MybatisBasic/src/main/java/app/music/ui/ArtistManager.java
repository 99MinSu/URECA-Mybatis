package app.music.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import app.music.ui.ArtistManager;
import app.music.config.MyBatisConfig;
import app.music.dao.ArtistDao;
import app.music.dto.ArtistDto;

public class ArtistManager extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;
    private JButton searchButton, addButton, editDeleteButton, listButton;
    private JTextField searchWordField;
    
    private ArtistDao artistDao;
    
    public ArtistManager() {
    	
    	SqlSessionFactory sqlSessionFactory = new MyBatisConfig().getSqlSessionFactoty();
    	SqlSession session = sqlSessionFactory.openSession(true);
    	artistDao = session.getMapper(ArtistDao.class);
    	
        setTitle("아티스트 관리");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        tableModel = new DefaultTableModel(new Object[]{"Artist ID", "Artist Name"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table = new JTable(tableModel);

        listArtists(); 

        JPanel buttonPanel = new JPanel();
        addButton = new JButton("추가");
        editDeleteButton = new JButton("수정 & 삭제");
        listButton = new JButton("목록");

        buttonPanel.add(addButton);
        buttonPanel.add(editDeleteButton);
        buttonPanel.add(listButton);

        Dimension textFieldSize = new Dimension(400, 28);
        searchWordField = new JTextField();
        searchWordField.setPreferredSize(textFieldSize);

        searchButton = new JButton("검색");

        JPanel searchPanel = new JPanel();
        searchPanel.add(new JLabel("아티스트 검색"));
        searchPanel.add(searchWordField);
        searchPanel.add(searchButton);

        add(new JScrollPane(table), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        add(searchPanel, BorderLayout.NORTH);

        addButton.addActionListener(e -> {
            AddArtistDialog addDialog = new AddArtistDialog(this, tableModel);
            addDialog.setVisible(true);
        });

        editDeleteButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow >= 0) {
                EditArtistDialog editDialog = new EditArtistDialog(this, tableModel, selectedRow);
                editDialog.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "아티스트를 선택하세요.");
            }
        });

        listButton.addActionListener(e -> {
            listArtists();
        });

        searchButton.addActionListener(e -> {
            String searchWord = searchWordField.getText();
            if (!searchWord.isBlank()) {
                listArtists(searchWord);
            }
        });

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int selectedRow = table.convertRowIndexToModel(table.getSelectedRow());
                    if (selectedRow >= 0) {
                        EditArtistDialog editDialog = new EditArtistDialog(ArtistManager.this, tableModel, selectedRow);
                        editDialog.setVisible(true);
                    }
                }
            }
        });
    }

    private void clearTable() {
        tableModel.setRowCount(0);
    }

    private void listArtists() {
        clearTable();
        List<ArtistDto> artistList = artistDao.listArtist();
        for (ArtistDto artist : artistList) {
            tableModel.addRow(new Object[]{artist.getArtist_id(), artist.getArtist_name()});
        }
    }

    private void listArtists(String searchWord) {
        clearTable();
        List<ArtistDto> artistList = artistDao.listArtistSearch(searchWord);
        
        for (ArtistDto artist : artistList) {
            tableModel.addRow(new Object[]{artist.getArtist_id(), artist.getArtist_name()});
        }
    }

    ArtistDto detailArtist(int artistId) {
        return artistDao.detailArtist(artistId);
    }

    public void deleteArtist(int artistId) {
        int ret = artistDao.deleteArtist(artistId);
        if (ret == 1) {
            listArtists();
        }
    }

    public void insertArtist(ArtistDto artist) {
        int ret = artistDao.insertArtist(artist);
        if (ret == 1) {
            listArtists();
        }
    }

    public void updateArtist(ArtistDto artist) {
        int ret = artistDao.updateArtist(artist);
        if (ret == 1) {
            listArtists();
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ArtistManager().setVisible(true);
        });
    }
}
