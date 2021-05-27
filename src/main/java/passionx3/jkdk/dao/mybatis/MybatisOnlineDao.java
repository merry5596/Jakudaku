package passionx3.jkdk.dao.mybatis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import passionx3.jkdk.dao.OnlineDao;
import passionx3.jkdk.dao.mybatis.mapper.OnlineMapper;
import passionx3.jkdk.domain.Online;

@Repository
public class MybatisOnlineDao implements OnlineDao {
	
	@Autowired
	private OnlineMapper onlineMapper;

	@Override
	public List<Online> getBestOnlineItemListforHome() throws DataAccessException {
		return onlineMapper.getBestOnlineItemListforHome();
	}

	@Override
	public List<Online> getNewOnlineItemList() throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Online> getOnlineItemListByCategory(int categoryId) throws DataAccessException {
		return onlineMapper.getOnlineItemListByCategory(categoryId);
	}

	@Override
	public List<Online> getOnlineItemListByTheme(int categoryId, int themeId) throws DataAccessException {
		return onlineMapper.getOnlineItemListByTheme(categoryId, themeId);
	}

	@Override
	public List<Online> getOnlineItemListByDevice(int categoryId, int device) throws DataAccessException {
	
		List<Online> onlineItemList = onlineMapper.getOnlineItemListByCategory(categoryId);

		List<Online> listFilteredByDevice = new ArrayList<>();
		
		switch(device) {
			case 1:
				for (Online onlineitem : onlineItemList) {
					if (onlineitem.getPcFile() != null) {
						listFilteredByDevice.add(onlineitem);
					}
				}
				break;
			case 2:
				for (Online onlineitem : onlineItemList) {
					if (onlineitem.getTabletFile() != null)
						listFilteredByDevice.add(onlineitem);
				}
				break;
			case 3: 
				for (Online onlineitem : onlineItemList) {
					if (onlineitem.getPhoneFile() != null)
						listFilteredByDevice.add(onlineitem);
				}
				break;
		}
		
		return listFilteredByDevice;
	}

	@Override
	public List<Online> getOnlineItemListByThemeAndDevice(int categoryId, int themeId, int device)
			throws DataAccessException {
		List<Online> onlineItemList = onlineMapper.getOnlineItemListByTheme(categoryId, themeId);

		List<Online> listFilteredByDevice = new ArrayList<>();
		
		switch(device) {
			case 1:
				for (Online onlineitem : onlineItemList) {
					if (onlineitem.getPcFile() != null)
						listFilteredByDevice.add(onlineitem);
				}
				break;
			case 2:
				for (Online onlineitem : onlineItemList) {
					if (onlineitem.getTabletFile() != null)
						listFilteredByDevice.add(onlineitem);
				}
				break;
			case 3: 
				for (Online onlineitem : onlineItemList) {
					if (onlineitem.getPhoneFile() != null)
						listFilteredByDevice.add(onlineitem);
				}
				break;
		}
		
		return listFilteredByDevice;
	}

	@Override
	public List<Online> getOnlineItemsByKeyword(String keyword) throws DataAccessException {
		return onlineMapper.getOnlineItemsByKeyword(keyword);
	}

	@Override
	public List<Online> getOnlineItemListByProducerId(String userId) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Online getOnlineItemById(int itemId) throws DataAccessException {
		return onlineMapper.getOnlineItemById(itemId);
	}

	@Override
	public int registerOnlineItem(Online onlineItem) throws DataAccessException {
		return onlineMapper.registerOnlineItem(onlineItem);
	}

	@Override
	public void setWatermark(String fileAddr) throws DataAccessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Online> getNotApprovedOnlineItems() throws DataAccessException {
		return onlineMapper.getNotApprovedOnlineItems();
	}

	@Override
	public int refuseItem(int itemId) throws DataAccessException {
		return onlineMapper.refuseItem(itemId) ;
	}

	@Override
	public int approveItem(int itemId) {
		return onlineMapper.approveItem(itemId) ;
	}

	@Override
	public String getOnlineItemIdByCategoryforSale(int categoryId) throws DataAccessException {
		return onlineMapper.getOnlineItemIdByCategoryforSale(categoryId);
	}

	@Override
	public List<Online> getBestOnlineItemList() throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Online> getNewOnlineItemListforHome() throws DataAccessException {
		return onlineMapper.getNewOnlineItemListforHome();
	}

	@Override
	public int updateOnlineItemSaleState(int itemId) throws DataAccessException {
		return onlineMapper.updateOnlineItemSaleState(itemId);
	}

	@Override
	public int updateOnlineItem(Online onlineItem) throws DataAccessException {
		return onlineMapper.updateOnlineItem(onlineItem);
	}


	
}