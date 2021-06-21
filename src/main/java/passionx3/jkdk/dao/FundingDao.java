
package passionx3.jkdk.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import passionx3.jkdk.domain.Funding;

public interface FundingDao {
	List<Funding> getFundingItemList(String keyword, int sortBy, int start, int end) throws DataAccessException;   
	List<Funding> getFundingItemListByTheme(int themeId, String keyword, int sortBy, int start, int end) throws DataAccessException;
	int getCountOfFundingItemList(String keyword, int start, int end) throws DataAccessException;   
	int getCountOfFundingItemListByTheme(int themeId, String keyword, int start, int end) throws DataAccessException;   

	List<Funding> getFundingItemsByKeyword(String keyword) throws DataAccessException;
	Funding getFundingItemById(int itemId) throws DataAccessException;
	int registerFundingItem (Funding funding) throws DataAccessException;
	List<Funding> getFundingItemListByProducerId(String userId) throws DataAccessException;

	List<Funding> getNotApprovedFundingItems() throws DataAccessException;
	List<Funding> getNewFundingItemListforHome()throws DataAccessException;
	void closeFunding(int itemId);
}

