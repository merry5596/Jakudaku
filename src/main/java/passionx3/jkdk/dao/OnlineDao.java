
package passionx3.jkdk.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import passionx3.jkdk.domain.Online;

public interface OnlineDao {
	// getItem
	List<Online> getBestOnlineItemList() throws DataAccessException;  	// best 온라인 상품 list 가져오기(for home)
	List<Online> getNewOnlineItemList() throws DataAccessException; 	// 신상품 온라인 list 가져오기
	
	List<Online> getOnlineItemListByCategory(int categoryId, String keyword, int sortBy) throws DataAccessException; 	
	List<Online> getOnlineItemListByTheme(int categoryId, int themeId, String keyword, int sortBy) throws DataAccessException; 

	List<Online> getOnlineItemsByKeyword(String keyword) throws DataAccessException; 	// 전체검색에서 키워드로 온라인 상품 list 가져와서 온라인 검색 결과에 출력하기 위함
	List<Online> getOnlineItemListByProducerId(String userId) throws DataAccessException; // 사용자의 판매 창작물 list 가져오기
	Online getOnlineItemById(int itemId) throws DataAccessException;   	// itemId로 Online 반환
	int registerOnlineItem (Online OnlineItem) throws DataAccessException; // 온라인 상품 등록, 메소드에서 할일: online Item insert & approval = 0
	int updateOnlineItemSaleState(int itemId) throws DataAccessException; //세일 상품 변경 시 이전 상품의 saleState를 0으로 변경
	int updateOnlineItem(Online OnlineItem) throws DataAccessException; //상품 수정 & approval을 0으로 변경
	
	//워터마크 // 비즈니스 로직
	void setWatermark(String fileAddr) throws DataAccessException;  // 이미지 주소를 받아와야 될지 아니면 item을 받아와서 item.thumnail로 써야될지 모르겠다..

	List<Online> getNotApprovedOnlineItems() throws DataAccessException; // 조건문에서 approval 무조건 0 (수정)
	int refuseItem(int itemId) throws DataAccessException;
	int approveItem(int itemId);
	void updateSaleState(int state, int itemId) throws DataAccessException; //(타임 = 1, 배틀 = 2, 끝 = 0)
	List<Integer> getOnlineItemIdByCategoryforSale(int categoryId) throws DataAccessException; //세일 선정을 위해 카테고리 동일의 랜덤으로 뽑기 위한 함수
	List<Online> getBestOnlineItemListforHome()throws DataAccessException;
	List<Online> getNewOnlineItemListforHome()throws DataAccessException;
	//	+조건 검색 내 키워드 검색
	
	void updateUploadDate(int itemId, String today);
}
