package passionx3.jkdk.dao.mybatis;



import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import passionx3.jkdk.dao.FundingDao;
import passionx3.jkdk.dao.mybatis.mapper.FundingMapper;
import passionx3.jkdk.domain.Funding;

@Repository
public class MybatisFundingDao implements FundingDao {

	@Autowired
	private FundingMapper fundingMapper;

	@Override
	public List<Funding> getNewFundingItemList() throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Funding> getFundingItemList(String keyword, int sortBy, int start, int end) throws DataAccessException {
//		Calendar cal= Calendar.getInstance ( );
//		SimpleDateFormat sDate = new SimpleDateFormat("yy/MM/dd");
//		cal.setTime(new Date());
//		String today = sDate.format(cal.getTime());
		
		if (sortBy == 1)
			return fundingMapper.getFundingItemListOrderByUploadDate(keyword, start, end);
		else if (sortBy == 2)
			return fundingMapper.getFundingItemListOrderByLikeNum(keyword, start, end);
		else // (sortBy == 6)
			return fundingMapper.getFundingItemListOrderByFinishDate(keyword, start, end);
	}

	@Override
	public List<Funding> getFundingItemListByTheme(int themeId, String keyword, int sortBy, int start, int end) throws DataAccessException {
		Calendar cal= Calendar.getInstance ( );
		SimpleDateFormat sDate = new SimpleDateFormat("yy/MM/dd");
		cal.setTime(new Date());
		String today = sDate.format(cal.getTime());
		
		if (sortBy == 1)
			return fundingMapper.getFundingItemListByThemeOrderByUploadDate(themeId, keyword, start, end);
		else if (sortBy == 2)
			return fundingMapper.getFundingItemListByThemeOrderByLikeNum(themeId, keyword, start, end);
		else // (sortBy == 6)
			return fundingMapper.getFundingItemListByThemeOrderByFinishDate(themeId, keyword, start, end);
	}
	
	@Override
	public int getCountOfFundingItemList(String keyword, int start, int end) throws DataAccessException {
		return fundingMapper.getCountOfFundingItemList(keyword, start, end);
	}
	
	@Override
	public int getCountOfFundingItemListByTheme(int themeId, String keyword, int start, int end) throws DataAccessException {
		return fundingMapper.getCountOfFundingItemListByTheme(themeId, keyword, start, end);
	}


	@Override
	public List<Funding> getFundingItemsByKeyword(String keyword) throws DataAccessException {
		return fundingMapper.getFundingItemsByKeyword(keyword);
	}

	@Override
	public Funding getFundingItemById(int itemId) throws DataAccessException {
		return fundingMapper.getFundingItemById(itemId);
	}

	@Override
	public int registerFundingItem(Funding funding) throws DataAccessException {
		return fundingMapper.registerFundingItem(funding);
	}

	@Override
	public List<Funding> getFundingItemListByProducerId(String userId) throws DataAccessException {
		return fundingMapper.getFundingItemListByProducerId(userId);
	}

	@Override
	public List<Funding> getNotApprovedFundingItems() throws DataAccessException {
		return fundingMapper.getNotApprovedFundingItems();
	}

	@Override
	public List<Funding> getNewFundingItemListforHome() throws DataAccessException {
		return fundingMapper.getNewFundingItemListforHome();
	}

	public void closeFunding(int itemId) {
		fundingMapper.closeFunding(itemId);
	}
}