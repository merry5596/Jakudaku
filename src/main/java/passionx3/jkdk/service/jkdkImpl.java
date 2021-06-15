package passionx3.jkdk.service;

import java.util.List;
import java.util.Map;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
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
  
	@Autowired    // task scheduler 객체를 주입 받음
	private ThreadPoolTaskScheduler scheduler;
	
	public void closeFunding(int itemId, Date closingTime) {
		Runnable updateTableRunner = new Runnable() { 
		@Override
		public void run() {   // 스케줄러에 의해 미래의 특정 시점에 실행될 작업을 정의    
			Date curTime = new Date();
			// 실행 시점의 시각을 전달하여 그 시각 이전의 closing time 값을 갖는 event들의 상태를 변경 
			fundingDao.closeFunding(itemId);    // EVENTS 테이블의 레코드 갱신 
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
	public List<Online> getOnlineItemListByCategory(int categoryId, int themeId, int device, String keyword, int sortBy) {
		List<Online> onlineList = null;
		
		if (themeId == -1 && device == -1) {	// theme 선택 안됨, device 선택 안됨
			onlineList = onlineDao.getOnlineItemListByCategory(categoryId, keyword, sortBy);
		} else if (themeId != -1 && device == -1) {	// theme 선택됨, device 선택 안됨
			onlineList = onlineDao.getOnlineItemListByTheme(categoryId, themeId, keyword, sortBy);
		} else if (themeId == -1 && device != -1) {	// theme 선택 안됨, device 선택됨
			List<Online> ListNotFiltered = onlineDao.getOnlineItemListByCategory(categoryId, keyword, sortBy);
		
			List<Online> listFilteredByDevice = new ArrayList<>();
			
			switch(device) {
				case 1:
					for (Online online : ListNotFiltered) {
						if (online.getPcFile() != null) {
							listFilteredByDevice.add(online);
						}
					}
					break;
				case 2:
					for (Online online : ListNotFiltered) {
						if (online.getTabletFile() != null)
							listFilteredByDevice.add(online);
					}
					break;
				case 3: 
					for (Online online : ListNotFiltered) {
						if (online.getPhoneFile() != null)
							listFilteredByDevice.add(online);
					}
					break;
			}
			onlineList = listFilteredByDevice;
		} else {	// theme 선택됨, device 선택됨
			List<Online> ListNotFiltered = onlineDao.getOnlineItemListByTheme(categoryId, themeId, keyword, sortBy);
			
			List<Online> listFilteredByDevice = new ArrayList<>();
			
			switch(device) {
				case 1:
					for (Online online : ListNotFiltered) {
						if (online.getPcFile() != null) {
							listFilteredByDevice.add(online);
						}
					}
					break;
				case 2:
					for (Online online : ListNotFiltered) {
						if (online.getTabletFile() != null)
							listFilteredByDevice.add(online);
					}
					break;
				case 3: 
					for (Online online : ListNotFiltered) {
						if (online.getPhoneFile() != null)
							listFilteredByDevice.add(online);
					}
					break;
			}
			onlineList = listFilteredByDevice;
		}
		
		return onlineList;
	}

	@Override
	public List<Funding> getFundingItemList(int themeId, String keyword, int sortBy) {
		List<Funding> fundingList = null;
		
		if (themeId == -1) {	// theme 선택 안됨
			fundingList = fundingDao.getFundingItemList(keyword, sortBy);
		} else {	// theme 선택됨
			fundingList = fundingDao.getFundingItemListByTheme(themeId, keyword, sortBy);
		}
		
		return fundingList;
	}

	@Override
	public void sortOnlineItemList(List<Online> onlineList, int sortBy) {
		switch(sortBy) {
			case 1: 	// 신상품순
				Comparator<Online> uploadDateComparator = new Comparator<Online>() {
				    @Override
				    public int compare(Online a, Online b) {
				    	return a.getUploadDate().compareTo(b.getUploadDate());
				    }
				};
				
				Collections.sort(onlineList, uploadDateComparator);
				
				for (Online online : onlineList) {
					System.out.println(online.getUploadDate());
				}
				
				break;
			case 2: 	// 좋아요순
				Comparator<Online> likesComparator = new Comparator<Online>() {
				    @Override
				    public int compare(Online a, Online b) {
				    	return b.getLikeNum() - a.getLikeNum();
				    }
				};
				
				Collections.sort(onlineList, likesComparator);
				
				for (Online online : onlineList) {
					System.out.println(online.getLike());
				}
				
				break;
			case 3:		// 평점순
				Comparator<Online> totalRateComparator = new Comparator<Online>() {
				    @Override
				    public int compare(Online a, Online b) {
				    	System.out.println(a.getTotalRate());
				    	if (b.getTotalRate() - a.getTotalRate() > 0) {
				    		return 1;
				    	} else if (b.getTotalRate() - a.getTotalRate() < 0) {
				    		return -1;
				    	}
				    	else {
				    		return 0;
				    	}
				    }
				};
				
				Collections.sort(onlineList, totalRateComparator);
				
				for (Online online : onlineList) {
					System.out.println(online.getTotalRate());
				}
				
				break;
			case 4:		// 저가순
				Comparator<Online> lowCostComparator = new Comparator<Online>() {
				    @Override
				    public int compare(Online a, Online b) {
				    	return a.getPrice() - b.getPrice();
				    }
				};
				
				Collections.sort(onlineList, lowCostComparator);
				
				for (Online online : onlineList) {
					System.out.println(online.getPrice());
				}
				
				break;
			case 5:		// 고가순
				Comparator<Online> highCostComparator = new Comparator<Online>() {
				    @Override
				    public int compare(Online a, Online b) {
				    	System.out.println(a.getPrice() + " " + b.getPrice());
				    	return b.getPrice() - a.getPrice();
				    }
				};
				
				for (Online online : onlineList) {
					System.out.println(online.getPrice());
				}
				
				Collections.sort(onlineList, highCostComparator);
				break;
		}
		
		return;
	}
	
	@Override
	public void sortFundingItemList(List<Funding> fundingList, int sortBy) {
		switch(sortBy) {
		case 1: 	// 신상품순
			Comparator<Funding> uploadDateComparator = new Comparator<Funding>() {
			    @Override
			    public int compare(Funding a, Funding b) {
			    	return b.getUploadDate().compareTo(a.getUploadDate());
			    }
			};
			
			Collections.sort(fundingList, uploadDateComparator);
			
			break;
		case 2: 	// 좋아요순
			Comparator<Funding> likesComparator = new Comparator<Funding>() {
			    @Override
			    public int compare(Funding a, Funding b) {
			    	return b.getLikeNum() - a.getLikeNum();
			    }
			};
			
			Collections.sort(fundingList, likesComparator);
			
			break;
		case 6:		// 마감임박순
			Comparator<Funding> finishDateComparator = new Comparator<Funding>() {
			    @Override
			    public int compare(Funding a, Funding b) {
			    	return a.getFinishDate().compareTo(b.getFinishDate());
			    }
			};
			
			Collections.sort(fundingList, finishDateComparator);
			
			break;
		case 7: 	// 마감 기간 긴 순...?
			break;
	}
		
		return;
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
	public void insertTimeSale() {
		Calendar cal= Calendar.getInstance ( );
		
		SimpleDateFormat sDate = new SimpleDateFormat("yy/MM/dd");
		cal.setTime(new Date());
		String today = sDate.format(cal.getTime());
		cal.add(Calendar.DATE, 1);
		String tomorrow = sDate.format(cal.getTime());
		
		int categoryId = cal.get(Calendar.DAY_OF_MONTH) % 4;
		
		List<Integer> itemList = onlineDao.getOnlineItemIdByCategoryforSale(categoryId);

		while(itemList.size() < 1) {
			cal.add(Calendar.DATE, 1);
			categoryId = cal.get(Calendar.DAY_OF_MONTH) % 4;
			itemList = onlineDao.getOnlineItemIdByCategoryforSale(categoryId);
		}
		
		timeSaleDao.insertTimeSale(itemList.get(0), today, tomorrow);
		onlineDao.updateSaleState(1, itemList.get(0));
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
	public int updateFundingItem(Funding funding) {
		return fundingDao.updateFundingItem(funding);
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
		
		battleSaleDao.insertBattleSale(itemList.get(0), itemList.get(1), today, tomorrow);
		onlineDao.updateSaleState(2, itemList.get(0));
		onlineDao.updateSaleState(2, itemList.get(1));
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
	public int updateOnlineItem(Online online) {
		return onlineDao.updateOnlineItem(online);
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
	public List<Funding> getNewFundingItemListforHome(String today){
		return fundingDao.getNewFundingItemListforHome(today);
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


}
