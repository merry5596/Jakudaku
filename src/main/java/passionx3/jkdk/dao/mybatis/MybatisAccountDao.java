package passionx3.jkdk.dao.mybatis;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import passionx3.jkdk.dao.AccountDao;
import passionx3.jkdk.dao.mybatis.mapper.AccountMapper;
import passionx3.jkdk.domain.Account;

public class MybatisAccountDao {
	@Autowired
	private AccountMapper accountMapper;
 
	public Account getAccount(String userId) throws DataAccessException {
		return accountMapper.getAccountByUsername(userId);
	}
	
	public Account getAccount(String userId, String password) 
	throws DataAccessException {
		return accountMapper.getAccountByUsernameAndPassword(userId, password);
	}
	public void insertAccount(Account account) throws DataAccessException {
		accountMapper.insertAccount(account);
	}
	
	public void updateAccount(Account account) throws DataAccessException {
		accountMapper.updateAccount(account);
	}	
	
	public void removeAccount(String userId) throws DataAccessException {
		accountMapper.removeAccount(userId);
	}	
}
