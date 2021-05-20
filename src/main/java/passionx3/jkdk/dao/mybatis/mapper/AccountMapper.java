package passionx3.jkdk.dao.mybatis.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import passionx3.jkdk.domain.Account;

public interface AccountMapper {
	
	@Select("SELECT USERID, ALIAS, NAME, EMAIL,"
			+ " PHONE, ADDRESS1, ADDRESS2, ZIP, MILEAGE"
			+ " FROM ACCOUNT WHERE ACCOUNT.USERID = #{username}")
	Account getAccountByUsername(String username);

	@Select("SELECT USERID, ALIAS, NAME, EMAIL,"
			+ " PHONE, ADDRESS1, ADDRESS2, ZIP, MILEAGE"
			+ " FROM ACCOUNT WHERE ACCOUNT.USERID = #{username}"
			+ " AND PASSWORD = #{password}")
	Account getAccountByUsernameAndPassword(String username, String password);
	
	@Insert("INSERT INTO ACCOUNT (USERID, PASSWORD, ALIAS, NAME, EMAIL, PHONE, ADDRESS1, ADDRESS2, ZIP)"
			+ " VALUES (#{account.userId}, #{account.password}, #{account.alias}, #{account.name}, #{account.email}, "
			+ "#{account.phone}, #{account.address1}, #{account.address2}, #{account.zip})")
	void insertAccount(Account account);
	
	@Update("UPDATE ACCOUNT SET USERID = #{account.userId}, PASSWORD = #{account.password}, "
			+ "ALIAS = #{account.alias}, NAME = #{account.name}, EMAIL = #{account.email}, "
			+ "PHONE = #{account.phone}, ADDRESS1 = #{account.address1}, ADDRESS2 = #{account.address2}, "
			+ "ZIP = #{account.zip} WHERE USERID = #{account.userId}")
	void updateAccount(Account account);

	@Delete("DELETE FROM ACCOUNT WHERE USERID = #{account.userId}")
	void removeAccount(String userId);
	
}
