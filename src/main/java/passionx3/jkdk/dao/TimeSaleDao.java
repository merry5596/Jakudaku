package passionx3.jkdk.dao;

import org.springframework.dao.DataAccessException;

import passionx3.jkdk.domain.TimeSale;

public interface TimeSaleDao {
	
	TimeSale getTimeSale(String openTime) throws DataAccessException; //가장 최근의 Time Sale 가져오기
	void insertTimeSale(int itemId, String openTime, String closeTime) throws DataAccessException; //TimeSale 설정
	void updateNotSale(String date);
}
