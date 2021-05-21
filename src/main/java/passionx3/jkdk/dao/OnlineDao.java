
package passionx3.jkdk.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import passionx3.jkdk.domain.Online;

public interface OnlineDao {
	// getItem
	List<Online> getBestOnlineItemList() throws DataAccessException;  	// best 온라인 상품 list 가져오기
	List<Online> getNewOnlineItemList() throws DataAccessException; 	// 신상품 온라인 list 가져오기
	List<Online> getOnlineItemListByCategory(String categoryId) throws DataAccessException; 	
	List<Online> getOnlineItemListByCategory(String categoryId, String themeId) throws DataAccessException; 	
	List<Online> getOnlineItemListByCategory(String categoryId, int device) throws DataAccessException;
	List<Online> getOnlineItemListByCategory(String categoryId, String themeId, int device) throws DataAccessException; 	// 카테고리별 온라인 상품 list 출력 (+ 조건 설정 시 테마, 기기에 따라)
	List<Online> getOnlineItemByKeyword(String keyword) throws DataAccessException; 	// 전체검색에서 키워드로 온라인 상품 list 가져와서 온라인 검색 결과에 출력하기 위함
	List<Online> getOnlineItemListByProducerId(String userId) throws DataAccessException; // 사용자의 판매 창작물 list 가져오기
	Online getOnlineItemById(int itemId) throws DataAccessException;   	// itemId로 Online 반환
	int registerOnlineItem (Online OnlineItem) throws DataAccessException; // 온라인 상품 등록, 메소드에서 할일: online Item insert & approval = 0

	//워터마크 // 비즈니스 로직
	void setWatermark(String fileAddr) throws DataAccessException;  // 이미지 주소를 받아와야 될지 아니면 item을 받아와서 item.thumnail로 써야될지 모르겠다..

	List<Online> getNotApprovedOnlineItems() throws DataAccessException; // 조건문에서 approval 무조건 0 (수정)

	//	+조건 검색 내 키워드 검색
}
