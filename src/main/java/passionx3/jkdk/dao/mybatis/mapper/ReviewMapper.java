
package passionx3.jkdk.dao.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import passionx3.jkdk.domain.Review;

@Mapper
public interface ReviewMapper {
	@Select("SELECT r.reviewId AS reviewId, TO_CHAR(r.writtenDate, 'YYYY/MM/DD HH24:MI') AS writtenDate, r.rate AS rate, "
				+ "r.content AS content, r.userId AS userId, a.alias AS userName, r.itemId AS itemId "
			+ "FROM review r, account a "
			+ "WHERE r.userId = a.userId AND r.itemId = #{itemId}")
	List<Review> getReviewsByItemId(@Param("itemId") int itemId);
}
