package passionx3.jkdk.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import passionx3.jkdk.dao.AccountDao;
import passionx3.jkdk.dao.BattleSaleDao;
import passionx3.jkdk.dao.CategoryDao;
import passionx3.jkdk.dao.FundOrderDao;
import passionx3.jkdk.dao.OnlineDao;
import passionx3.jkdk.dao.ReviewDao;
import passionx3.jkdk.dao.OrderDao;
import passionx3.jkdk.dao.ThemeDao;
import passionx3.jkdk.dao.TimeSaleDao;
import passionx3.jkdk.dao.FundingDao;
import passionx3.jkdk.dao.ItemDao;
import passionx3.jkdk.domain.*;
import passionx3.jkdk.repository.LikeRepository;
import passionx3.jkdk.util.WaterMarkUtil;

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

	@Autowired
	private ItemDao itemDao;
	
	@Autowired
	private ReviewDao reviewDao;
  
	@Autowired
	private OrderDao orderDao;
	
	@Autowired
	private FundOrderDao fundOrderDao;
	
	@Autowired
	private LikeRepository likeRepo;
  
	@Autowired    // task scheduler 객체를 주입 받음
	private ThreadPoolTaskScheduler scheduler;
	
	@Autowired
	private WaterMarkUtil waterMarkUtil;
	
	public void closeFunding(int itemId, Date closingTime) {
		Runnable updateTableRunner = new Runnable() { 
		@Override
		public void run() {   // 스케줄러에 의해 미래의 특정 시점에 실행될 작업을 정의    
			Date curTime = new Date();
			fundingDao.closeFunding(itemId);
			System.out.println("updateTableRunner is executed at " + curTime);
			}
		};
		  
		// task schedule 생성: closingTime에 updateTableRunner.run() 메소드가 자동 실행됨
		scheduler.schedule(updateTableRunner, closingTime);   
	}
	
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
		return reviewDao.insertReview(review);
	}

	@Override
	public int updateReview(Review review) {
		return reviewDao.updateReview(review);
	}

	@Override
	public int deleteReview(int reviewId) {
		return reviewDao.deleteReview(reviewId);
	}

	@Override
	public List<Review> getReviewsByItemId(int itemId) {
		return reviewDao.getReviewsByItemId(itemId);
	}

	@Override
	public Review getReviewById(int reviewId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Online> getOnlineItemsByKeyword(String keyword) {
		return onlineDao.getOnlineItemsByKeyword(keyword);
	}

	@Override
	public List<Funding> getFundingItemsByKeyword(String keyword) {
		return fundingDao.getFundingItemsByKeyword(keyword);
	}

	@Override
	public List<Online> getOnlineItemListByCategory(int categoryId, int themeId, int device, String keyword, int sortBy, int start, int end) {
		List<Online> onlineList = null;
		
		if (themeId == -1 && device == -1) {	// theme 선택 안됨, device 선택 안됨
			onlineList = onlineDao.getOnlineItemListByCategory(categoryId, keyword, sortBy, start, end);
		} else if (themeId != -1 && device == -1) {	// theme 선택됨, device 선택 안됨
			onlineList = onlineDao.getOnlineItemListByTheme(categoryId, themeId, keyword, sortBy, start, end);
		} else if (themeId == -1 && device != -1) {	// theme 선택 안됨, device 선택됨
			onlineList = onlineDao.getOnlineItemListByDevice(categoryId, device, keyword, sortBy, start, end);
		} else {	// theme 선택됨, device 선택됨
			onlineList = onlineDao.getOnlineItemListByThemeAndDevice(categoryId, themeId, device, keyword, sortBy, start, end);
		}
		return onlineList;
	}
	
	@Override
	public int getCountOfOnlineItemListByCategory(int categoryId, int themeId, String keyword, int start, int end) {
		if (themeId == -1) {	// theme 선택 안됨, device 선택 안됨
			return onlineDao.getCountOfOnlineItemListByCategory(categoryId, keyword, start, end);
		} else {	// theme 선택됨, device 선택 안됨
			return onlineDao.getCountOfOnlineItemListByTheme(categoryId, themeId, keyword, start, end);
		}
	}

	@Override
	public List<Funding> getFundingItemList(int themeId, String keyword, int sortBy, int start, int end) {
		List<Funding> fundingList = null;
		
		if (themeId == -1) {	// theme 선택 안됨
			fundingList = fundingDao.getFundingItemList(keyword, sortBy, start, end);
		} else {	// theme 선택됨
			fundingList = fundingDao.getFundingItemListByTheme(themeId, keyword, sortBy, start, end);
		}
		
		return fundingList;
	}
	
	@Override
	public int getCountOfFundingItemList(int themeId, String keyword, int start, int end) {
		if (themeId == -1) {	// theme 선택 안됨
			return fundingDao.getCountOfFundingItemList(keyword, start, end);
		} else {	// theme 선택됨
			return fundingDao.getCountOfFundingItemListByTheme(themeId, keyword, start, end);
		}
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
	public TimeSale getTimeSale() {
		Calendar cal= Calendar.getInstance ( );
		
		SimpleDateFormat sDate = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		
		cal.setTime(new Date());
		cal.set(Calendar.HOUR_OF_DAY, 12);
		cal.set(Calendar.MINUTE, 00);
		cal.set(Calendar.SECOND, 00);
		
		String today = sDate.format(cal.getTime());
		
		return timeSaleDao.getTimeSale(today);
	}

	@Override
	public void insertTimeSale() {
		Calendar cal= Calendar.getInstance ( );
		
		SimpleDateFormat sDate = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		
		cal.setTime(new Date());
		cal.set(Calendar.HOUR_OF_DAY, 12);
		cal.set(Calendar.MINUTE, 00);
		cal.set(Calendar.SECOND, 00);
		
		String today = sDate.format(cal.getTime());
		
		cal.set(Calendar.HOUR_OF_DAY, 15);
		String tomorrow = sDate.format(cal.getTime());
		
		int categoryId = cal.get(Calendar.DAY_OF_MONTH) % 4;
		
		List<Integer> itemList = onlineDao.getOnlineItemIdByCategoryforSale(categoryId);

		while(itemList.size() < 1) {
			cal.add(Calendar.DATE, 1);
			categoryId = cal.get(Calendar.DAY_OF_MONTH) % 4;
			itemList = onlineDao.getOnlineItemIdByCategoryforSale(categoryId);
		}
		int  num = timeSaleDao.insertTimeSale(itemList.get(0), today, tomorrow);
		if(num > 0) {
			onlineDao.updateSaleState(1, itemList.get(0));
		}	
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
  
	@Override
	public Online getOnlineItemById(int itemId) {
		return onlineDao.getOnlineItemById(itemId);
	}

	@Override
	public Funding getFundingItemById(int itemId) {
		return fundingDao.getFundingItemById(itemId);
	}

	@Override
	public List<Theme> getAllThemes() {
		return themeDao.getAllThemes();
	}

	@Override
	public List<Category> getAllCategories() {
		return categoryDao.getAllCategories();
	}

	@Override
	@Transactional
	public int registerFundingItem(Funding funding) {
		Item item = funding;
		int r1 = itemDao.registerItem(item);
		int r2 = fundingDao.registerFundingItem(funding);
		
		if (r1 * r2 == 0)
			return 0;
		
		
		//펀딩 마감시 isForSale 자동 변경할 수 있도록 closeFunding 추가
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		Date finish = null;
		try {
			finish = transFormat.parse(funding.getFinishDate());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		closeFunding(funding.getItemId(), finish);
		
		
		return 1;
	}

	@Override
	public void resetVote() {
		battleSaleDao.resetVote();
	}

	@Override
	public int getWinnerItemId() {
		int winnerId = battleSaleDao.getWinnerItemId();
		onlineDao.updateSaleState(2, winnerId);
		return winnerId;
	}

	@Override
	public void insertBattleSale() {
		Calendar cal= Calendar.getInstance ( );
		
		SimpleDateFormat sDate = new SimpleDateFormat("yy/MM/dd");
		cal.setTime(new Date());
		String today = sDate.format(cal.getTime());
		cal.add(Calendar.DATE, 2);
		String tomorrow = sDate.format(cal.getTime());
		
		int categoryId = cal.get(Calendar.DAY_OF_MONTH) % 4;
		
		List<Integer> itemList = onlineDao.getOnlineItemIdByCategoryforSale(categoryId);
		
		while(itemList.size() < 2) {
			cal.add(Calendar.DATE, 1);
			categoryId = cal.get(Calendar.DAY_OF_MONTH) % 4;
			itemList = onlineDao.getOnlineItemIdByCategoryforSale(categoryId);
		}
		
		int num = battleSaleDao.insertBattleSale(itemList.get(0), itemList.get(1), today, tomorrow);
		if(num > 0) {
			onlineDao.updateSaleState(2, itemList.get(0));
			onlineDao.updateSaleState(2, itemList.get(1));
		}	
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
	public Map<String, List<Order>> getLineItemsByUserId(String userId) throws ParseException {
		return orderDao.getLineItemsByUserId(userId);
	}

	@Override
	public List<Online> getOnlineItemListByProducerId(String userId) {
		return onlineDao.getOnlineItemListByProducerId(userId);
	}

	@Override
	public List<Funding> getFundingItemListByProducerId(String userId) {
		return fundingDao.getFundingItemListByProducerId(userId);
	}

	@Override
	@Transactional
	public int registerOnlineItem(Online online) {
		Item item = online;
		int r1 = itemDao.registerItem(item);
		int r2 = onlineDao.registerOnlineItem(online);
		
		if (r1 * r2 == 0)
			return 0;
		return 1;
	}

	@Override
	@Transactional
	public int updateOnlineItem(Online online) {
		Item item = online;
		
		int r1 = itemDao.updateItem(item);
		int r2 = onlineDao.updateOnlineItem(online);
		
		if (r1 * r2 == 0)
			return 0;
		return 1;
	}

	@Override
	public int insertOrder(Order order) {
		return orderDao.insertOrder(order);
	}
	
	@Override
	public int insertFundOrder(FundOrder fundOrder) {
		return fundOrderDao.insertFundOrder(fundOrder);
	}

	@Override
	public Order getOrderByOrderId(int orderId) {
		return orderDao.getOrderByOrderId(orderId);
	}
	
	@Override
	public FundOrder getFundOrderByOrderId(int orderId) {
		return fundOrderDao.getFundOrderByOrderId(orderId);
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
	public String getCategoryNameByCategoryId(int categoryId) {
		return categoryDao.getCategoryNameByCategoryId(categoryId);
	}

	@Override
	public int updateOnlineItemSaleState(int itemId) {
		return onlineDao.updateOnlineItemSaleState(itemId);
	}
	
	@Override
	public Category getCategoryByCategoryId(int categoryId) {
		return categoryDao.getCategoryByCategoryId(categoryId);
	}

	@Override
	public Review getReview(int reviewId) {
		return reviewDao.getReview(reviewId);
	}

	@Override
	public String getReviewIdByLineItmeId(int lineItemId) {
		return reviewDao.getReviewIdByLineItemId(lineItemId);
	}

	@Override
	public void updateSaleState(int state, int itemId) {
		onlineDao.updateSaleState(state, itemId);
	}

	@Override
	public void updateNotSale(String date) {
		battleSaleDao.updateNotSale(date);
		timeSaleDao.updateNotSale(date);
	}

	@Override
	@Transactional
	public UserLike plusLike(UserLike userLike) {		
		UserLike updateUserLike = likeRepo.save(userLike);
		itemDao.plusLikeNum(userLike.getItemId());
		
		return updateUserLike;
	}

	@Override
	@Transactional
	public UserLike minusLike(UserLike userLike) {
		UserLike updateUserLike = likeRepo.save(userLike);
		itemDao.minusLikeNum(userLike.getItemId());
		
		return updateUserLike;
	}

	@Override
	public UserLike getUserLike(int itemId, String userId) {		
		Optional<UserLike> result = likeRepo.findByItemIdAndUserId(itemId, userId);
		
		if (result.isPresent()) {
			return result.get();
		} else {
			return null;
		}
	}

	@Override
	public void setWaterMark(Item item) {
		waterMarkUtil.setWaterMarkFile(item.getProducerName(), item.getThumbnail1());
		
		if (item.getThumbnail2() != null) {
			waterMarkUtil.setWaterMarkFile(item.getProducerName(), item.getThumbnail2());
		}
		
		if (item.getThumbnail3() != null) {
			waterMarkUtil.setWaterMarkFile(item.getProducerName(), item.getThumbnail3());
		}
	}
}
