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
	public TimeSale getTimeSale() throws DataAccessException {
		System.out.println(timeSaleMapper.getTimeSale());
		
		return timeSaleMapper.getTimeSale();
	}

	@Override
	public void insetTimeSale(String itemId, String openTime, String closeTime) throws DataAccessException {
		timeSaleMapper.insetTimeSale(itemId, openTime, closeTime);
	}
	
}