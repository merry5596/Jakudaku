package passionx3.jkdk.dao;

import org.springframework.dao.DataAccessException;


import passionx3.jkdk.domain.BattleSale;

public interface BattleSaleDao {
	
	BattleSale getBattleSale(String openTime) throws DataAccessException;
	int insertVote(String battleId, String userId) throws DataAccessException;
	void updateBattleSaleVote1(String battleId)throws DataAccessException;
	void updateBattleSaleVote2(String battleId)throws DataAccessException;
	int insertBattleSale(int itemId1, int itemId2, String openTime, String closeTime) throws DataAccessException;
	void resetVote() throws DataAccessException;
	int getWinnerItemId() throws DataAccessException;
	void updateNotSale(String date) throws DataAccessException;

}
