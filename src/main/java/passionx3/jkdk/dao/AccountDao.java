package passionx3.jkdk.dao;

import passionx3.jkdk.domain.Account;

public interface AccountDao {

	int insertAccount(Account account);  	
	
	int updateAccount(Account account);
	
	int removeAccount(String userId);     	
	
	Account getAccount(String userId); 
	
	Account getAccount(String userId, String password);	
	
	int existingUser(String userId);
}
