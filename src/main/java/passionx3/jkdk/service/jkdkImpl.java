package passionx3.jkdk.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import passionx3.jkdk.dao.AccountDao;
import passionx3.jkdk.dao.BattleSaleDao;
import passionx3.jkdk.dao.TimeSaleDao;
import passionx3.jkdk.domain.*;

@Service
@Transactional
public class jkdkImpl implements jkdkFacade {

	@Autowired
	private AccountDao accountDao;
	
	@Override
	public Account getAccount(String userId) {
		return accountDao.getAccount(userId);
		}
	
	@Override
	public Account getAccount(String userId, String password) {
		return accountDao.getAccount(userId, password);
	}
  
	@Autowired
	private TimeSaleDao timeSaleDao;
	
	@Autowired
	private BattleSaleDao battleSaleDao;
	
	@Override
	public int insertAccount(Account account) {
		return accountDao.insertAccount(account);
	}

	@Override
	public int updateAccount(Account account) {
		return accountDao.updateAccount(account);
	}

	@Override
	public int removeAccount(String userId) {
		// TODO Auto-generated method stub
		return accountDao.removeAccount(userId);
	}
	
	@Override
	public int existingUser(String userId) {
		// TODO Auto-generated method stub
		return accountDao.existingUser(userId);
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
	public TimeSale getTimeSale() {
		return timeSaleDao.getTimeSale();
	}

	@Override
	public void insertTimeSale(String itemId, String openTime, String closeTime) {
		timeSaleDao.insertTimeSale(itemId, openTime, closeTime);
	}

	@Override
	public BattleSale getBattleSale() {
		return battleSaleDao.getBattleSale();
	}

	@Override
	public int insertVote(String battleId, String userId) {
		return battleSaleDao.insertVote(battleId, userId);
	}

	@Override
	public void updateBattleSaleVote1(String battleId) {
		battleSaleDao.updateBattleSaleVote1(battleId);
	}

	@Override
	public void updateBattleSaleVote2(String battleId) {
		battleSaleDao.updateBattleSaleVote2(battleId);
  }
  
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
	public void resetVote() {
		battleSaleDao.resetVote();
	}

	@Override
	public int getWinnerItemId() {
		return battleSaleDao.getWinnerItemId();
	}

	@Override
	public void insertBattleSale(String itemId1, String itemId2, String openTime, String closeTime) {
		battleSaleDao.insertBattleSale(itemId1, itemId2, openTime, closeTime);
	}
  
	@Override
	public List<Item> getNotApprovedOnlineItems() {
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

	public boolean isItemInStock(String itemId) {
		return false;
	}

	@Override
	public Item getItem(String itemId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertOrder(Order order) {
		// TODO Auto-generated method stub
		
	}

}
