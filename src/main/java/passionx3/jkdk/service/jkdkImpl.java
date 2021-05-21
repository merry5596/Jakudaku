package passionx3.jkdk.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import passionx3.jkdk.domain.*;

@Service
@Transactional
public class jkdkImpl implements jkdkFacade {

	@Override
	public int insertAccount(Account account) {
		return 0;
		// TODO Auto-generated method stub
		
	}

	@Override
	public int updateAccount(Account account) {
		return 0;
		// TODO Auto-generated method stub
		
	}

	@Override
	public int removeAccount(String userId) {
		return 0;
		// TODO Auto-generated method stub
		
	}

	@Override
	public Account getAccount(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account getAccount(String userId, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean existingUser(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateLike(int itemId, String userId) {
		return itemId;
		// TODO Auto-generated method stub
		
	}

	@Override
	public int approveItem(int itemId) {
		return itemId;
		// TODO Auto-generated method stub
		
	}

	@Override
	public int refuseItem(int itemId) {
		return itemId;
		// TODO Auto-generated method stub
		
	}

	@Override
	public int insertReview(Review review) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateReview(Review review) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteReview(int reviewId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Review> getReviewsByItemId(int reviewId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Review getRevieById(int reviewId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Online> getOnlineItemsByKeyword(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Funding> getFundingItemsByKeyword(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Online> getOnlineItemListByCategory(String categoryId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Online> getOnlineItemListByCategory(String categoryId, int themeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Online> getOnlineItemListByCategory2(String categoryId, int device) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Online> getOnlineItemListByCategory(String categoryId, int themeId, int device) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Item getCategoryByCategoryId(String categoryId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Funding> getFundingItemList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Funding> getFundingItemList(int themeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Online> getBestOnlineItemList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Online> getNewOnlineItemList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Funding> getNewFundingItemList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Online getOnlineItemById(int itemId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Funding getFundingItemById(int itemId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Theme> getAllThemes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Category> getAllCategories() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void registerFundingItem(Funding funding) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateFundingItem(Funding funding) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Item getNotApprovedOnlineItems() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Item getNotApprovedFundingItems() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, List<LineItem>> getLineItemsByUserId(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Online> getOnlineItemListByProducerId(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Funding> getFundingItemListByProducerId(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int registerOnlineItem(Online online) {
		return 0;
		// TODO Auto-generated method stub
		
	}

	@Override
	public int updateOnlineItem(Online online) {
		return 0;
		// TODO Auto-generated method stub
		
	}

}
