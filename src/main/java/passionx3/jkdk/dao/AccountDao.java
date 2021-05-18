package passionx3.jkdk.dao;

import passionx3.jkdk.domain.Account;

public interface AccountDao {

	void insertAccount(Account account);  	
	
	void updateAccount(Account account);
	
	void removeAccount(String userId);     	
	
	Account getAccount(String userId); 
	
	Account getAccount(String userId, String password);	
	
	Boolean existingUser(String userId);	
}
