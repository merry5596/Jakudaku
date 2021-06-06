package passionx3.jkdk.service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import passionx3.jkdk.domain.*;

public interface jkdkFacade {
	
	void closeFunding(int itemId, Date closingTime);
	
	Account getAccount(String userId);

	Account getAccount(String userId, String password);

	int insertAccount(Account account);  	
	
	int updateAccount(Account account);	
	
	int removeAccount(String userId);     

	int existingUser(String userId);	
	
	int updateLike(int itemId, String userId);
	
	int approveItem(int itemId);
	
	int refuseItem(int itemId);
	
	Review getReview(int reviewId);
	
	String getReviewIdByLineItmeId(int lineItemId);
	
	int insertReview (Review review);

	int updateReview (Review review);
	
	int deleteReview (int reviewId);
		
	List<Review> getReviewsByItemId(int itemId);
	
	Online getOnlineItemById(int itemId);
	
	Funding getFundingItemById(int itemId); 
	
	Review getReviewById(int reviewId);

	List<Online> getOnlineItemsByKeyword(String keyword);

	List<Funding> getFundingItemsByKeyword(String keyword);
	
	List<Online> getOnlineItemListByCategory(int cateogryId, int themeId, int device, String keyword, int sortBy);

	List<Funding> getFundingItemList(int themeId, String keyword, int sortBy);
	
	void sortOnlineItemList(List<Online> onlineList, int sortBy);
	
	void sortFundingItemList(List<Funding> fundingList, int sortBy);

	List<Online> getBestOnlineItemListforHome();

	List<Online> getNewOnlineItemList();

	List<Funding> getNewFundingItemList(); 
	
	TimeSale getTimeSale(String openTime);
	
	void insertTimeSale();
	
	BattleSale getBattleSale(String openTime) ;
	
	int insertVote(String battleId, String userId);
	
	void updateBattleSaleVote1(String battleSaleId);
	
	void updateBattleSaleVote2(String battleSaleId);
	
	void resetVote();
	
	int getWinnerItemId();
	
	void insertBattleSale();
	
	//List<Funding> getNewFundingItemList();

	List<Theme> getAllThemes();

	List<Category> getAllCategories();

	int registerFundingItem(Funding funding);

	int updateFundingItem(Funding funding);

	List<Online> getNotApprovedOnlineItems();

	List<Funding> getNotApprovedFundingItems();

	Map<String, List<Order>> getLineItemsByUserId(String userId) throws ParseException;

	List<Online> getOnlineItemListByProducerId(String userId);

	List<Funding> getFundingItemListByProducerId(String userId);

	int registerOnlineItem(Online online);

	int updateOnlineItem(Online online);
	
	int insertOrder(Order order);
	
	int insertFundOrder(FundOrder fundOrder);

	Order getOrderByOrderId(int orderId);
	
	FundOrder getFundOrderByOrderId(int orderId);

	List<Online> getNewOnlineItemListforHome();

	List<Funding> getNewFundingItemListforHome(String today);
	
	String getCategoryNameByCategoryId(int categoryId);
	
	int updateOnlineItemSaleState(int itemId);
	
	Category getCategoryByCategoryId(int categoryId);
	
	void updateSaleState(int state, int itemId);
	
	void updateNotSale(String date);
}
