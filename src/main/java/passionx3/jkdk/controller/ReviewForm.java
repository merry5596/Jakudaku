package passionx3.jkdk.controller;

import java.io.Serializable;

import passionx3.jkdk.domain.Review;

@SuppressWarnings("serial")
public class ReviewForm implements Serializable{

	private Review review;
	private boolean newReview;
	
	public ReviewForm(Review review) {
		this.review = review;
		this.newReview = false;
	}
	
	public ReviewForm() {
		this.review = new Review();
		this.newReview = true;
	}
	
	public Review getReview() {
		return review;
	}
	
	public boolean isNewReview() {
		return newReview;
	}
	
}
