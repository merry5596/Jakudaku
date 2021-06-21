package passionx3.jkdk.dao;

import org.springframework.dao.DataAccessException;

import passionx3.jkdk.domain.TimeSale;

public interface TimeSaleDao {
	
	TimeSale getTimeSale(String openTime) throws DataAccessException;
	int insertTimeSale(int itemId, String openTime, String closeTime) throws DataAccessException;
	void updateNotSale(String date);
}
