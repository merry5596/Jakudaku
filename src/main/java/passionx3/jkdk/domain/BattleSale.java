package passionx3.jkdk.domain;

import java.io.Serializable;

@SuppressWarnings("serial")
public class BattleSale extends Sale implements Serializable {

  /* Private Fields */
	private int battleSaleId;
	private int itemId1;
	private int itemId2;
	private int votes1;
	private int votes2;
	
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
	public int getVotes1() {
		return votes1;
	}
	public void setVotes1(int vote1) {
		this.votes1 = vote1;
	}
	public int getVotes2() {
		return votes2;
	}
	public void setVotes2(int vote2) {
		this.votes2 = vote2;
	}
}
