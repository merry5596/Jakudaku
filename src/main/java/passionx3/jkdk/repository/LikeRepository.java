package passionx3.jkdk.repository;

import java.util.Optional;

import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.CrudRepository;

import passionx3.jkdk.domain.UserLike;

public interface LikeRepository extends CrudRepository<UserLike, Integer>{
	Optional<UserLike> findByItemIdAndUserId(int itemId, String userId) throws DataAccessException;
}
