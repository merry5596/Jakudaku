
package passionx3.jkdk.dao.mybatis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import passionx3.jkdk.dao.ReviewDao;
import passionx3.jkdk.dao.mybatis.mapper.OnlineMapper;
import passionx3.jkdk.dao.mybatis.mapper.ReviewMapper;
import passionx3.jkdk.domain.Item;
import passionx3.jkdk.domain.Review;

@Transactional
@Repository
public class MybatisReviewDao implements ReviewDao {
	@Autowired
	private ReviewMapper reviewMapper;
	
	@Autowired
	private OnlineMapper onlineMapper;
	
	@Override
	@Transactional
	public int insertReview(Review review) throws DataAccessException {
		int r1 = reviewMapper.insertReview(review);				
		int r2 = onlineMapper.updateTotalrate(review.getItemId());
		
		if (r1 * r2 == 0)
			return 0;
		return 1;
	}
	
	@Override
	@Transactional
	public int updateReview(Review review) throws DataAccessException {
		int r1 = reviewMapper.updateReview(review);
		int r2 = onlineMapper.updateTotalrate(review.getItemId());
		
		if (r1 * r2 == 0)
			return 0;
		return 1;
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
