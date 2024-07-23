package practice;

import java.util.List;


import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import practice.config.MyBatisConfig;
import practice.dao.CustomerDao;
import practice.dto.CustomerDto;

// config : java
// sql(mapper) : java
public class Test {

	public static void main(String[] args) throws Exception{
		// 자바 설정 이용
		SqlSessionFactory sqlSessionFactory = new MyBatisConfig().getSqlSessionFactoty();
		SqlSession session = sqlSessionFactory.openSession();
		
		CustomerDao customerDao = session.getMapper(CustomerDao.class); 
		
		//목록
//		{
//			List<CustomerDto> coustomerList = customerDao.listCustomer();
//			for (CustomerDto customerDto : coustomerList) {
//				System.out.println(customerDto);
//			}
//		}
//		
		// 상세
//		{
//			CustomerDto customerDto = customerDao.detailCustomer(1);
//			System.out.println(customerDto);
//		}
		
//		// 등록
//		{
//			CustomerDto customerDto = new CustomerDto(7, "카리나", "대한민국 서울", "010-2222-2222");
//			int ret = customerDao.insertCustomer(customerDto);
//			System.out.println(ret);
//			session.commit();
//		}
		
//		// 수정
		{
			CustomerDto customerDto = new CustomerDto(7, "카리나 윈터", "대한민국 익산", "010-1111-2222");
			int ret = customerDao.updateCustomer(customerDto);
			System.out.println(ret);
			session.commit();
		}
		
//		// 삭제
//		{
//			int ret = customerDao.deleteCustomer(7);
//			System.out.println(ret);
//			session.commit();
//		}
		
		session.close();
	}

}