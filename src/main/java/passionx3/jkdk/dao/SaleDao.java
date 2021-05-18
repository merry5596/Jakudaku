package passionx3.jkdk.dao;

import passionx3.jkdk.domain.Sale;

public interface SaleDao {
	void insertSale(Sale sale); // sale저장
	Sale getSaleByItemId(int saleId, int itemId); // sale 반환
}
