package passionx3.jkdk.dao;

import org.springframework.dao.DataAccessException;


import passionx3.jkdk.domain.BattleSale;

public interface BattleSaleDao {
	
	BattleSale getBattleSale() throws DataAccessException; //가장 최근의 battle Sale 가져오기
	int insertVote(String battleId, String userId) throws DataAccessException;// 추천 뒤에 컬럼 생성
	void updateBattleSaleVote1(String battleId)throws DataAccessException;
	void updateBattleSaleVote2(String battleId)throws DataAccessException;
	void insetBattleSale(String itemId1, String itemId2, String openTime, String closeTime) throws DataAccessException;
	void resetVote() throws DataAccessException;
	int getWinnerItemId() throws DataAccessException;
}
