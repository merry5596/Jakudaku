package passionx3.jkdk.dao;

public interface VoteDao {
	int insertVote(String userId, int battleSaleId); // vote에 insert
	// => 성공일 경우 inserVote 실행 + updateVote
	// => -1일 경우.. “이미 추천하셨습니다.”... 를 하던지 투표를 못하게 하던지..


}
