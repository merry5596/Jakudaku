package passionx3.jkdk.dao;

import java.util.List;

import passionx3.jkdk.domain.Funding;

public interface FundingDao {
	List<Funding> getFundingItemsByKeyword(String keyword);
}
