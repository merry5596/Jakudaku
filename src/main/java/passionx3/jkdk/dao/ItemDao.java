
package passionx3.jkdk.dao;

import org.springframework.dao.DataAccessException;

import passionx3.jkdk.domain.Item;

public interface ItemDao {
	int registerItem(Item item) throws DataAccessException;
	int updateItem(Item item) throws DataAccessException;
}
