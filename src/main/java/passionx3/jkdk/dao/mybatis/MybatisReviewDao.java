
package passionx3.jkdk.dao.mybatis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import passionx3.jkdk.dao.ReviewDao;
import passionx3.jkdk.dao.mybatis.mapper.OnlineMapper;
import passionx3.jkdk.dao.mybatis.mapper.ReviewMapper;
import passionx3.jkdk.domain.Review;

@Repository
public class MybatisReviewDao implements ReviewDao {
	@Autowired
	private ReviewMapper reviewMapper;
	
	@Override
	public int insertReview(Review review) throws DataAccessException {
		return reviewMapper.insertReview(review);
	}

	@Override
	public int updateReview(Review review) throws DataAccessException {
		return reviewMapper.updateReview(review);
	}

	@Override
	public int deleteReview(int reviewId) throws DataAccessException {
		return reviewMapper.deleteReview(reviewId);
	}

	@Override
	public List<Review> getReviewsByItemId(int itemId) throws DataAccessException {
		return reviewMapper.getReviewsByItemId(itemId);
	}

	@Override
	public Review getReview(int reviewId) {
		return reviewMapper.getReview(reviewId);
	}

	@Override
	public String getReviewIdByLineItemId(int lineItemId) {
		return reviewMapper.getReviewIdByLineItemId(lineItemId);
	}

	
}
