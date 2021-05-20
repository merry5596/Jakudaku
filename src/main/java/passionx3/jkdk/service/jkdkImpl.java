package passionx3.jkdk.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import passionx3.jkdk.domain.*;

@Service
@Transactional
public class jkdkImpl implements jkdkFacade {

	@Override
	public void insertAccount(Account account) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateAccount(Account account) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeAccount(String userId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Account getAccount(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account getAccount(String userId, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean existingUser(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateLike(int itemId, String userId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void approveItem(int itemId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void refuseItem(int itemId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int insertReview(Review review) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateReview(Review review) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteReview(int reviewId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Review> getReviewsByItemId(int reviewId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Online getOnlineItemById(String itemId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Funding getFundingItemById(String itemId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Review getRevieById(int reviewId) {
		// TODO Auto-generated method stub
		return null;
	}


}
