package passionx3.jkdk.service;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import passionx3.jkdk.dao.AccountDao;
import passionx3.jkdk.dao.BattleSaleDao;
import passionx3.jkdk.dao.CategoryDao;
import passionx3.jkdk.dao.OnlineDao;
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
	private OrderDao orderDao;
	
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
		// TODO Auto-generated method stub
		return null;
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
			
			for (Funding funding : fundingList) {
				System.out.println(funding.getUploadDate());
			}
			
			break;
		case 2: 	// 좋아요순
			Comparator<Funding> likesComparator = new Comparator<Funding>() {
			    @Override
			    public int compare(Funding a, Funding b) {
			    	return b.getLikeNum() - a.getLikeNum();
			    }
			};
			
			Collections.sort(fundingList, likesComparator);
			
			for (Funding funding : fundingList) {
				System.out.println(funding.getLike());
			}
			
			break;
		case 6:		// 마감임박순
			Comparator<Funding> finishDateComparator = new Comparator<Funding>() {
			    @Override
			    public int compare(Funding a, Funding b) {
			    	return a.getFinishDate().compareTo(b.getFinishDate());
			    }
			};
			
			Collections.sort(fundingList, finishDateComparator);
			
			for (Funding funding : fundingList) {
				System.out.println(funding.getFinishDate());
			}
			
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
	public Item getItem(String itemId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insertOrder(Order order) {
		return orderDao.insertOrder(order);
	}

	@Override
	public Order getOrderByOrderId(int orderId) {
		return orderDao.getOrderByOrderId(orderId);
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

}
