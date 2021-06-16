
package passionx3.jkdk.dao.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import passionx3.jkdk.domain.Review;

@Mapper
public interface ReviewMapper {
	
	@Insert("INSERT INTO REVIEW (REVIEWID, WRITTENDATE, RATE, CONTENT, USERID, ITEMID, LINEITEMID) "
			+ "values (#{reviewId}, SYSDATE, #{rate}, #{content}, #{userId}, "
				+ "#{itemId}, #{lineItemId})")
	@SelectKey(statement="select reviewId_seq.nextval FROM DUAL", keyProperty="reviewId", before=true, resultType=int.class)
	int insertReview(Review review);

	@Update("UPDATE REVIEW SET RATE = #{rate}, CONTENT = #{content} WHERE REVIEWID = #{reviewId}")
	int updateReview(Review review);
	
	@Delete("DELETE FROM REVIEW WHERE REVIEWID = #{reviewId}")
	int deleteReview(int reviewId);
		
	@Select("SELECT r.reviewId AS reviewId, TO_CHAR(r.writtenDate, 'YYYY/MM/DD HH24:MI') AS writtenDate, r.rate AS rate, "
				+ "r.content AS content, r.userId AS userId, a.alias AS userName, r.itemId AS itemId "
			+ "FROM review r, account a "
			+ "WHERE r.userId = a.userId AND r.itemId = #{itemId}")
	List<Review> getReviewsByItemId(@Param("itemId") int itemId);

	@Select("SELECT r.reviewId AS reviewId, TO_CHAR(r.writtenDate, 'YYYY/MM/DD HH24:MI') AS writtenDate, r.rate AS rate, "
			+ "r.content AS content, r.userId AS userId, r.itemId As itemId, r.lineItemId As lineItemId "
		+ "FROM review r "
		+ "WHERE r.reviewId = #{reviewId}")
	Review getReview(int reviewId);

	@Select("SELECT REVIEWID FROM REVIEW WHERE LINEITEMID = #{lineItemId}")
	String getReviewIdByLineItemId(int lineItemId);	
}
