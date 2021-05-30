package passionx3.jkdk.dao;

import java.util.List;

import passionx3.jkdk.domain.Review;

public interface ReviewDao {

	int insertReview (Review review);

	int updateReview (Review review);
	
	int deleteReview (int reviewId);
	
	List<Review> getReviewsByItemId(int itemId);
	
}
