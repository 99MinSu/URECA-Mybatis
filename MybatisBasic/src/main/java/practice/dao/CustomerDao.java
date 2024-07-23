package practice.dao;

import java.util.List;



import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import practice.dto.CustomerDto;


public interface CustomerDao {
	
	@Select("select custid custId, name, address , phone from customer")
	List<CustomerDto> listCustomer();
	
	@Select("select custid custId, name, address , phone from customer where custid = #{custId}")	
	CustomerDto detailCustomer(int custId);
	
	@Insert("insert into customer (custid, name, address, phone) values (#{custId}, #{name}, #{address}, #{phone})")		
	int insertCustomer(CustomerDto customerDto);
	
	@Update("update customer set name = #{name}, address = #{address}, phone = #{phone} where custid = #{custId}")	
	int updateCustomer(CustomerDto customerDto);
	
	@Delete("delete from cutomer where custid = #{custId}")
	int deleteCustomer(int custId);
}
