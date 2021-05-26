package passionx3.jkdk.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import passionx3.jkdk.dao.AccountDao;
import passionx3.jkdk.dao.BattleSaleDao;
import passionx3.jkdk.dao.CategoryDao;
import passionx3.jkdk.dao.OnlineDao;
import passionx3.jkdk.dao.ThemeDao;
import passionx3.jkdk.dao.TimeSaleDao;
import passionx3.jkdk.dao.FundingDao;
import passionx3.jkdk.domain.*;

@Service
@Transactional
public class jkdkImpl implements jkdkFacade {

	@Autowired
	private AccountDao accountDao;
	
	@Autowired
	private OnlineDao onlineDao;
	
	@Autowired
	private FundingDao fundingDao;
	
	@Autowired
	private TimeSaleDao timeSaleDao;
	
	@Autowired
	private BattleSaleDao battleSaleDao;
	
	@Autowired
	private CategoryDao categoryDao;
	
	@Autowired
	private ThemeDao themeDao;
	
	@Override
	public Account getAccount(String userId) {
		return accountDao.getAccount(userId);
		}
	
	@Override
	public Account getAccount(String userId, String password) {
		return accountDao.getAccount(userId, password);
	}
  
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
		return onlineDao.approveItem(itemId);
		
	}

	@Override
	public int refuseItem(int itemId) {
		return onlineDao.refuseItem(itemId);
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
	public List<Online> getOnlineItemListByCategory(int categoryId) {
		return onlineDao.getOnlineItemListByCategory(categoryId);
	}

	@Override
	public List<Online> getOnlineItemListByTheme(int categoryId, int themeId) {
		return onlineDao.getOnlineItemListByTheme(categoryId, themeId);
	}

	@Override
	public List<Online> getOnlineItemListByDevice(int categoryId, int device) {
		return onlineDao.getOnlineItemListByDevice(categoryId, device);
	}

	@Override
	public List<Online> getOnlineItemListByThemeAndDevice(int categoryId, int themeId, int device) {
		return onlineDao.getOnlineItemListByThemeAndDevice(categoryId, themeId, device);
	}

	@Override
	public List<Funding> getFundingItemList() {
		return fundingDao.getFundingItemList();
	}

	@Override
	public List<Funding> getFundingItemListByTheme(int themeId) {
		return fundingDao.getFundingItemListByTheme(themeId);
	}

	@Override
	public List<Online> getBestOnlineItemListforHome() {
		return onlineDao.getBestOnlineItemListforHome();
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
	public TimeSale getTimeSale(String openTime) {
		return timeSaleDao.getTimeSale(openTime);
	}

	@Override
	public void insertTimeSale(String itemId, String openTime, String closeTime) {
		timeSaleDao.insertTimeSale(itemId, openTime, closeTime);
	}

	@Override
	public BattleSale getBattleSale(String openTime) {
		return battleSaleDao.getBattleSale(openTime);
	}

	@Override
	public int insertVote(String battleId, String userId) {
		return battleSaleDao.insertVote(battleId, userId);
	}

	@Override
	public void updateBattleSaleVote1(String battleSaleId) {
		battleSaleDao.updateBattleSaleVote1(battleSaleId);
	}

	@Override
	public void updateBattleSaleVote2(String battleSaleId) {
		battleSaleDao.updateBattleSaleVote2(battleSaleId);
  }
  
	public Online getOnlineItemById(int itemId) {
		return onlineDao.getOnlineItemById(itemId);
	}

	@Override
	public Funding getFundingItemById(int itemId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Theme> getAllThemes() {
		return themeDao.getAllThemes();
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
	public List<Online> getNotApprovedOnlineItems() {
		return onlineDao.getNotApprovedOnlineItems();
	}

	@Override
	public List<Funding> getNotApprovedFundingItems() {
		return fundingDao.getNotApprovedFundingItems();
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

	@Override
	public Item getItem(String itemId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertOrder(Order order) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Order getOrderByOrderId(int orderId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getOnlineItemIdByCategoryforSale(int category) {
		return onlineDao.getOnlineItemIdByCategoryforSale(category);
	}

	@Override
	public List<Online> getNewOnlineItemListforHome() {
		return onlineDao.getNewOnlineItemListforHome();
	}

	@Override
	public List<Funding> getNewFundingItemListforHome(){
		return fundingDao.getNewFundingItemListforHome();
	}

	@Override
	public String getCategoryNameByCategoryId(int CategoryId) {
		return categoryDao.getCategoryNameByCategoryId(CategoryId);
	}
	
	@Override
	public Category getCategoryByCategoryId(int categoryId) {
		return categoryDao.getCategoryByCategoryId(categoryId);
	}


}
