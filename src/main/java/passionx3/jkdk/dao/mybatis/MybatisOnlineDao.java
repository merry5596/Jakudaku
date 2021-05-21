package passionx3.jkdk.dao.mybatis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import passionx3.jkdk.dao.OnlineDao;
import passionx3.jkdk.dao.mybatis.mapper.OnlineMapper;
import passionx3.jkdk.domain.Item;
import passionx3.jkdk.domain.Online;

@Repository
public class MybatisOnlineDao implements OnlineDao{
	
	@Autowired
	private OnlineMapper onlineMapper;

	public List<Online> getOnlineItemsByKeyword(String keyword) throws DataAccessException {
		return onlineMapper.getOnlineItemsByKeyword(keyword);
	}

	@Override
	public List<Online> getBestOnlineItemList() throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Online> getNewOnlineItemList() throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Online> getOnlineItemListByCategory(String categoryId) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Online> getOnlineItemListByCategory(String categoryId, String themeId) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Online> getOnlineItemListByCategory(String categoryId, int device) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Online> getOnlineItemListByCategory(String categoryId, String themeId, int device)
			throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Online getOnlineItemById(int itemId) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int registerOnlineItem(Online OnlineItem) throws DataAccessException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setWatermark(String fileAddr) throws DataAccessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Online> getNotApprovedOnlineItems() throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Online> getOnlineItemListByProducerId(String userId) throws DataAccessException {
		return onlineMapper.getOnlineItemListByProducerId(userId);
	}
	
}