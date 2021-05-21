package passionx3.jkdk.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import passionx3.jkdk.domain.*;

public interface jkdkFacade {

	void insertAccount(Account account);  	
	
	void updateAccount(Account account);	
	
	void removeAccount(String userId);     
	
	Account getAccount(String userId);     	
	
	Account getAccount(String userId, String password);	
	
	Boolean existingUser(String userId);	
	
	void updateLike(int itemId, String userId);
	
	void approveItem(int itemId);
	
	void refuseItem(int itemId);
	
	int insertReview (Review review);

	int updateReview (Review review);
	
	int deleteReview (int reviewId);
	
	List<Review> getReviewsByItemId(int reviewId);
	
	Online getOnlineItemById(String itemId);
	
	Funding getFundingItemById(String itemId); 
	
	Review getRevieById(int reviewId);

	List<Online> getOnlineItemsByKeyword(String keyword);

	List<Funding> getFundingItemsByKeyword(String keyword);

	List<Online> getOnlineItemListByCategory(String categoryId);

	List<Online> getOnlineItemListByCategory(String categoryId, int themeId);

	List<Online> getOnlineItemListByCategory2(String categoryId, int device);

	List<Online> getOnlineItemListByCategory(String categoryId, int themeId, int device);

	Item getCategoryByCategoryId(String categoryId);

	List<Funding> getFundingItemList();

	List<Funding> getFundingItemList(int themeId);

	List<Online> getBestOnlineItemList();

	List<Online> getNewOnlineItemList();

	List<Funding> getNewFundingItemList(); 
	
	TimeSale getTimeSale();
	
	void insertTimeSale(String itemId, String openTime,String closeTime);
	
	BattleSale getBattleSale() ;
	
	int insertVote(String battleId, String userId);
	
	void updateBattleSaleVote1(String battleId);
	
	void updateBattleSaleVote2(String battleId);
	
	void resetVote();
	
	int getWinnerItemId();
	
	void insertBattleSale(String itemId1, String itemId2, String openTime, String closeTime);
	
}
