package passionx3.jkdk.dao.mybatis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import passionx3.jkdk.dao.ItemDao;
import passionx3.jkdk.dao.mybatis.mapper.ItemMapper;
import passionx3.jkdk.domain.Item;

@Repository
public class MybatisItemDao implements ItemDao{
	
	@Autowired
	private ItemMapper itemMapper;

	@Override
	public int registerItem(Item item) throws DataAccessException {
		return itemMapper.registerItem(item);
	}

	@Override
	public int updateItem(Item item) throws DataAccessException {
		return itemMapper.updateItem(item);
	}

	@Override
	public int plusLikeNum(int itemId) throws DataAccessException {
		return itemMapper.plusLikeNum(itemId);
	}

	@Override
	public int minusLikeNum(int itemId) throws DataAccessException {
		return itemMapper.minusLikeNum(itemId);
	}
	
}