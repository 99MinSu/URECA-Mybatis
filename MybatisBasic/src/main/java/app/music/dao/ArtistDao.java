package app.music.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import app.music.dto.ArtistDto;

public interface ArtistDao {
	@Select("select artist_id, artist_name from artist")
	List<ArtistDto> listArtist();
	
	@Select("select artist_id, artist_name from artist where artist_name like concat('%', #{searchWord}, '%')")
	List<ArtistDto> listArtistSearch(String searchWord);
	
	@Select("select artist_id, artist_name from artist where artist_id = #{artist_id}")	
	ArtistDto detailArtist(int artist_id);
	
	@Insert("insert into artist (artist_id, artist_name) values (#{artist_id}, #{artist_name})")		
	int insertArtist(ArtistDto ArtistDto);
	
	@Update("update artist set artist_name = #{artist_name} where artist_id = #{artist_id}")	
	int updateArtist(ArtistDto ArtistDto);
	
	@Delete("delete from artist where artist_id = #{artist_id}")
	int deleteArtist(int artist_id);
}
