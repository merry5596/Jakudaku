package passionx3.jkdk.service;

import java.util.List;

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
	
	Online getOnlineItemById(int itemId);
	
	Funding getFundingItemById(int itemId); 

}
