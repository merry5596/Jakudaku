
package passionx3.jkdk.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import passionx3.jkdk.domain.Online;

public interface OnlineDao {
	int getCountOfOnlineItemListByCategory(int categoryId, String keyword, int start, int end) throws DataAccessException; 
	int getCountOfOnlineItemListByTheme(int categoryId, int themeId, String keyword, int start, int end) throws DataAccessException; 
	
	List<Online> getOnlineItemListByCategory(int categoryId, String keyword, int sortBy, int start, int end) throws DataAccessException; 	
	List<Online> getOnlineItemListByTheme(int categoryId, int themeId, String keyword, int sortBy, int start, int end) throws DataAccessException; 
	List<Online> getOnlineItemListByDevice(int categoryId, int device, String keyword, int sortBy, int start, int end) throws DataAccessException;
	List<Online> getOnlineItemListByThemeAndDevice(int categoryId, int themeId, int device, String keyword, int sortBy, int start, int end) throws DataAccessException;
	
	List<Online> getOnlineItemsByKeyword(String keyword) throws DataAccessException;
	List<Online> getOnlineItemListByProducerId(String userId) throws DataAccessException;
	Online getOnlineItemById(int itemId) throws DataAccessException;
	int registerOnlineItem (Online OnlineItem) throws DataAccessException;
	int updateOnlineItemSaleState(int itemId) throws DataAccessException;
	int updateOnlineItem(Online OnlineItem) throws DataAccessException;

	List<Online> getNotApprovedOnlineItems() throws DataAccessException;
	int refuseItem(int itemId) throws DataAccessException;
	int approveItem(int itemId);
	void updateSaleState(int state, int itemId) throws DataAccessException;
	List<Integer> getOnlineItemIdByCategoryforSale(int categoryId) throws DataAccessException;
	List<Online> getBestOnlineItemListforHome()throws DataAccessException;
	List<Online> getNewOnlineItemListforHome()throws DataAccessException;
	
	void updateUploadDate(int itemId, String today);
}
