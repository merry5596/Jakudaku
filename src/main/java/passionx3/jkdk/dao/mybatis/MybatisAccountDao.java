package passionx3.jkdk.dao.mybatis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import passionx3.jkdk.dao.AccountDao;
import passionx3.jkdk.dao.mybatis.mapper.AccountMapper;
import passionx3.jkdk.domain.Account;

public class MybatisAccountDao implements AccountDao{
	@Autowired
	private AccountMapper accountMapper;
 
	@Override
	public Account getAccount(String userId) throws DataAccessException {
		return accountMapper.getAccountByUsername(userId);
	}
	
	@Override
	public Account getAccount(String userId, String password) 
	throws DataAccessException {
		return accountMapper.getAccountByUsernameAndPassword(userId, password);
	}
	
	@Override
	public void insertAccount(Account account) throws DataAccessException {
		accountMapper.insertAccount(account);
	}
	
	@Override
	public void updateAccount(Account account) throws DataAccessException {
		accountMapper.updateAccount(account);
	}	
	
	@Override
	public void removeAccount(String userId) throws DataAccessException {
		accountMapper.removeAccount(userId);
	}

	@Override
	public int existingUser(String userId) {
		return accountMapper.exisingUser(userId);
	}	
}
