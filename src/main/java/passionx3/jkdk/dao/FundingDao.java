
package passionx3.jkdk.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import passionx3.jkdk.domain.Funding;

public interface FundingDao {
	// getItem
	List<Funding> getNewFundingItemList() throws DataAccessException; // 새로운 펀딩 상품 list 가져오기 (홈에 새로운 펀딩 상품 list도 출력하면 어떨까 해서 추가해봤삼)
	List<Funding> getFundingItemList(String keyword, int sortBy) throws DataAccessException;   
	List<Funding> getFundingItemListByTheme(int themeId, String keyword, int sortBy) throws DataAccessException;       	// 펀딩 상품 list 출력 (+ 조건 설정 시 테마에 따라)
	List<Funding> getFundingItemsByKeyword(String keyword) throws DataAccessException; // 전체검색에서 키워드로 펀딩 상품 list 가져와서 펀딩 검색 결과에 출력하기 위함
	Funding getFundingItemById(int itemId) throws DataAccessException;   // itemId로 Funding 반환
	int registerFundingItem (Funding funding) throws DataAccessException; // 펀딩 상품 등록, 메소드에서 할일: funding Item insert & approval = 0
	int updateFundingItem(Funding funding) throws DataAccessException; //상품 수정 & approval을 0으로 변경
	List<Funding> getFundingItemListByProducerId(String userId) throws DataAccessException; // 사용자의 판매 펀딩 list 가져오기 (추가)

	List<Funding> getNotApprovedFundingItems() throws DataAccessException; // 조건문에서 approval 무조건 0 (수정)
	List<Funding> getNewFundingItemListforHome()throws DataAccessException;

	// +조건 검색 내 키워드 검색	
}

