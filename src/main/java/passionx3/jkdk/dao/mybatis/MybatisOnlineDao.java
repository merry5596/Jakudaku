package passionx3.jkdk.dao.mybatis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import passionx3.jkdk.dao.mybatis.mapper.OnlineMapper;
import passionx3.jkdk.domain.Item;

@Repository
public class MybatisOnlineDao {
	
	@Autowired
	private OnlineMapper onlineMapper;

	public List<Item> getOnlineItemsByKeyword(String keyword) throws DataAccessException {
		return onlineMapper.getOnlineItemsByKeyword(keyword);
	}
	
}