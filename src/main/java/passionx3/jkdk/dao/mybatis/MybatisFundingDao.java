package passionx3.jkdk.dao.mybatis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import passionx3.jkdk.dao.mybatis.mapper.FundingMapper;
import passionx3.jkdk.domain.Item;

@Repository
public class MybatisFundingDao {
	
	@Autowired
	private FundingMapper fundingMapper;

	public List<Item> getFundingItemsByKeyword(String keyword) throws DataAccessException {
		return fundingMapper.getFundingItemsByKeyword(keyword);
	}
	
}