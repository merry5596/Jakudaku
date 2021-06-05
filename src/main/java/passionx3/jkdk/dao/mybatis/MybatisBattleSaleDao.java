package passionx3.jkdk.dao.mybatis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import passionx3.jkdk.dao.BattleSaleDao;
import passionx3.jkdk.dao.mybatis.mapper.BattleSaleMapper;
import passionx3.jkdk.domain.BattleSale;

@Repository
public class MybatisBattleSaleDao implements BattleSaleDao {

	@Autowired
	private BattleSaleMapper battleSaleMapper;

	@Override
	public BattleSale getBattleSale(String openTime) throws DataAccessException {
		return battleSaleMapper.getBattleSale(openTime);
		
	}

	@Override
	public int insertVote(String battleId, String userId) throws DataAccessException {
		return battleSaleMapper.insertVote(battleId, userId);
	}
	
	@Override
	public void insertBattleSale(int itemId1, int itemId2, String openTime, String closeTime) throws DataAccessException {
		battleSaleMapper.insertBattleSale(itemId1, itemId2, openTime, closeTime);
	}

	@Override
	public void resetVote() throws DataAccessException {
		battleSaleMapper.resetVote();
	}
	
	public int getWinnerItemId() throws DataAccessException {
		return battleSaleMapper.getWinnerItemId();

	}

	@Override
	public void updateBattleSaleVote1(String battleId) throws DataAccessException {
		battleSaleMapper.updateBattleSaleVote1(battleId);
		
	}

	@Override
	public void updateBattleSaleVote2(String battleId) throws DataAccessException {
		battleSaleMapper.updateBattleSaleVote2(battleId);
	}
	
	@Override
	public void updateNotSale(String date) throws DataAccessException {
		battleSaleMapper.updateNotSale(date);
	}
	
}