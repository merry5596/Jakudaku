package passionx3.jkdk.dao.mybatis.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import passionx3.jkdk.domain.Account;

@Mapper
public interface AccountMapper {
	
	@Select("SELECT USERID, ALIAS, NAME, EMAIL,"
			+ " PHONE, ADDRESS1, ADDRESS2, ZIP, MILEAGE"
			+ " FROM ACCOUNT WHERE ACCOUNT.USERID = #{userId}")
	Account getAccountByUsername(String userId);

	@Select("SELECT USERID, ALIAS, NAME, EMAIL,"
			+ " PHONE, ADDRESS1, ADDRESS2, ZIP, MILEAGE"
			+ " FROM ACCOUNT WHERE ACCOUNT.USERID = #{userId}"
			+ " AND PASSWORD = #{password}")
	Account getAccountByUsernameAndPassword(String userId, String password);
	
	@Insert("INSERT INTO ACCOUNT (USERID, PASSWORD, ALIAS, NAME, EMAIL, PHONE, ADDRESS1, ADDRESS2, ZIP)"
			+ " VALUES (#{userId}, #{password}, #{alias}, #{name}, #{email}, "
			+ "#{phone}, #{address1}, #{address2}, #{zip})")
	int insertAccount(Account account);
	
	@Insert("UPDATE ACCOUNT SET USERID = #{userId}, PASSWORD = #{password}, "
			+ "ALIAS = #{alias}, NAME = #{name}, EMAIL = #{email}, "
			+ "PHONE = #{phone}, ADDRESS1 = #{address1}, ADDRESS2 = #{address2}, "
			+ "ZIP = #{zip} WHERE USERID = #{userId}")
	int updateAccount(Account account);

	@Delete("DELETE FROM ACCOUNT WHERE USERID = #{userId}")
	int removeAccount(String userId);

	@Select("SELECT USERID FROM ACCOUNT WHERE ACCOUNT.USERID = #{userId}")
	int exisingUser(String userId);	
	
	
}
