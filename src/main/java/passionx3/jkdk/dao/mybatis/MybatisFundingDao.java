package passionx3.jkdk.dao.mybatis;



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
	public List<Funding> getFundingItemList() throws DataAccessException {
		return fundingMapper.getFundingItemList();
	}

	@Override
	public List<Funding> getFundingItemListByTheme(int themeId) throws DataAccessException {
		return fundingMapper.getFundingItemListByTheme(themeId);
	}

	@Override
	public List<Funding> getFundingItemsByKeyword(String keyword) throws DataAccessException {
		return fundingMapper.getFundingItemsByKeyword(keyword);
	}

	@Override
	public Funding getFundingItemById(int itemId) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
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

	@Override
	public int updateFundingItem(Funding funding) throws DataAccessException {
		return fundingMapper.updateFundingItem(funding);
	}

	
}