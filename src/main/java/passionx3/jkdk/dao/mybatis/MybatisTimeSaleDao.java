package passionx3.jkdk.dao.mybatis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import passionx3.jkdk.dao.TimeSaleDao;
import passionx3.jkdk.dao.mybatis.mapper.TimeSaleMapper;
import passionx3.jkdk.domain.TimeSale;

@Repository
public class MybatisTimeSaleDao implements TimeSaleDao {

	@Autowired
	private TimeSaleMapper timeSaleMapper;

	@Override
	public TimeSale getTimeSale(String openTime) throws DataAccessException {		
		return timeSaleMapper.getTimeSale(openTime);
	}

	@Override
	public void insertTimeSale(int itemId, String openTime, String closeTime) throws DataAccessException {
		timeSaleMapper.insertTimeSale(itemId, openTime, closeTime);
	}

	@Override
	public void updateNotSale(String date) {
		timeSaleMapper.updateNotSale(date);
	}
	
}