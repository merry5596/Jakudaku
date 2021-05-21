package passionx3.jkdk.service;

import java.util.List;
import java.util.Map;

import passionx3.jkdk.domain.*;

public interface jkdkFacade {

	Account getAccount(String userId);

	Account getAccount(String userId, String password);

	int insertAccount(Account account);  	
	
	int updateAccount(Account account);	
	
	int removeAccount(String userId);     

	
	int existingUser(String userId);	
	
	int updateLike(int itemId, String userId);
	
	int approveItem(int itemId);
	
	int refuseItem(int itemId);
	
	int insertReview (Review review);

	int updateReview (Review review);
	
	int deleteReview (int reviewId);
	
	List<Review> getReviewsByItemId(int reviewId);
	
	Online getOnlineItemById(int itemId);
	
	Funding getFundingItemById(int itemId); 
	
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
	
	//List<Funding> getNewFundingItemList();

	List<Theme> getAllThemes();

	List<Category> getAllCategories();

	void registerFundingItem(Funding funding);

	void updateFundingItem(Funding funding);

	Item getNotApprovedOnlineItems();

	Item getNotApprovedFundingItems();

	Map<String, List<LineItem>> getLineItemsByUserId(String userId);

	List<Online> getOnlineItemListByProducerId(String userId);

	List<Funding> getFundingItemListByProducerId(String userId);

	int registerOnlineItem(Online online);

	int updateOnlineItem(Online online);
	
	Item getItem(String itemId);

	void insertOrder(Order order);

	Order getOrderByOrderId(int orderId);

}
