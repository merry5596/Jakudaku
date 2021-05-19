package passionx3.jkdk.domain;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class Review implements Serializable{
	private int reviewId;
	private Date date;
	private int rate;
	private String comment;
	private String userId;
	private int itemId;
	private int lineItemId;
	public int getReviewId() {
		return reviewId;
	}
	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getRate() {
		return rate;
	}
	public void setRate(int rate) {
		this.rate = rate;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public int getLineItemId() {
		return lineItemId;
	}
	public void setLineItemId(int lineItemId) {
		this.lineItemId = lineItemId;
	}
	
	
}
