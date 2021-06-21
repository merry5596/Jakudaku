package passionx3.jkdk.dao;

import passionx3.jkdk.domain.Sale;

public interface SaleDao {
	void insertSale(Sale sale);
	Sale getSaleByItemId(int saleId, int itemId);
}
