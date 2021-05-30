package passionx3.jkdk.dao;

import org.springframework.dao.DataAccessException;

public interface SequenceDao {
	public int getNextId(String name) throws DataAccessException;
	public int getOrderSequenceNextVal() throws DataAccessException;
	public int getLineItemSequenceNextVal() throws DataAccessException;
}
