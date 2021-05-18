package passionx3.jkdk.domain;

@SuppressWarnings("serial")
public class BattleSale extends Sale {

  /* Private Fields */
	private int battleSaleId;
	private int itemId1;
	private int itemId2;
	private int vote1;
	private int vote2;

	

  /* JavaBeans Properties */
	public int getBattleSaleId() {
		return battleSaleId;
	}
	public void setBattleSaleId(int battleSaleId) {
		this.battleSaleId = battleSaleId;
	}
	public int getItemId1() {
		return itemId1;
	}
	public void setItemId1(int itemId1) {
		this.itemId1 = itemId1;
	}
	public int getItemId2() {
		return itemId2;
	}
	public void setItemId2(int itemId2) {
		this.itemId2 = itemId2;
	}
	public int getVote1() {
		return vote1;
	}
	public void setVote1(int vote1) {
		this.vote1 = vote1;
	}
	public int getVote2() {
		return vote2;
	}
	public void setVote2(int vote2) {
		this.vote2 = vote2;
	}
}
