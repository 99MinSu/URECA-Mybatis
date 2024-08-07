package ch02;

import java.io.Reader;

import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import ch02.dao.BookDao;
import ch02.dto.BookDto;

// config : xml
// sql(mapper) : java
public class Test {

	public static void main(String[] args) throws Exception{
		// Mybatis 설정 파일을 읽어 온다.
		Reader reader = Resources.getResourceAsReader("config/mybatis-config-2.xml");
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		SqlSession session = sqlSessionFactory.openSession();
		
		// SQL(mapper) + Java
		// Java 의 어떤 메소드(BookDao) 가 호출되면 mapper 의 어떤 sql 이 수행되는지 연결
		BookDao bookDao = session.getMapper(BookDao.class);  // book-mapper.xml  과 BookDao 가 연결
		
		//목록
//		{
//			List<BookDto> bookList = bookDao.listBook();
//			for (BookDto bookDto : bookList) {
//				System.out.println(bookDto);
//			}
//		}
		
		// 상세
//		{
//			BookDto bookDto = bookDao.detailBook(1);
//			System.out.println(bookDto);
//		}
		
//		// 등록
//		{
//			BookDto bookDto = new BookDto(11, "11번째 책", "uplus press", 5000);
//			int ret = bookDao.insertBook(bookDto);
//			System.out.println(ret);
//			session.commit();
//		}
		
//		// 수정
//		{
//			BookDto bookDto = new BookDto(11, "11번째 수정", "uplus press 수정", 10000);
//			int ret = bookDao.updateBook(bookDto);
//			System.out.println(ret);
//			session.commit();
//		}
		
//		// 삭제
		{
			int ret = bookDao.deleteBook(11);
			System.out.println(ret);
			session.commit();
		}
		
		session.close();
	}

}