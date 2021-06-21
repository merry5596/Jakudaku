package passionx3.jkdk.dao.mybatis.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import passionx3.jkdk.domain.Item;

@Mapper
public interface ItemMapper {
	@Insert("INSERT INTO item (itemId, themeId, name, uploadDate, price, likeNum, thumbnail1, thumbnail2, thumbnail3, isforsale, description, approval, userid) "
			+ "values (#{item.itemId}, #{item.themeId}, #{item.name}, sysdate, #{item.price}, 0, "
				+ "#{item.thumbnail1, jdbcType=VARCHAR}, #{item.thumbnail2, jdbcType=VARCHAR}, #{item.thumbnail3, jdbcType=VARCHAR}, 1, #{item.description}, 0, #{item.producerId})")
	@SelectKey(statement="select itemId_seq.nextval FROM DUAL", keyProperty="item.itemId", before=true, resultType=int.class)
	int registerItem(@Param("item") Item item);
	
	@Update("UPDATE item "
			+ "SET name = #{item.name}, themeid = #{item.themeId}, price = #{item.price}, "
				+ "thumbnail1 = #{item.thumbnail1, jdbcType=VARCHAR}, thumbnail2 = #{item.thumbnail2, jdbcType=VARCHAR}, thumbnail3 = #{item.thumbnail3, jdbcType=VARCHAR}, "
				+ "isforsale = #{item.isForSale}, description = #{item.description}, approval = 0 "
			+ "WHERE itemid = #{item.itemId}")
	int updateItem(@Param("item") Item item);
	
	@Update("UPDATE item "
			+ "SET likeNum = likeNum + 1 "
			+ "WHERE itemid = #{itemId}")
	int plusLikeNum(int itemId);
	
	@Update("UPDATE item "
			+ "SET likeNum = likeNum - 1 "
			+ "WHERE itemid = #{itemId}")
	int minusLikeNum(int itemId);
}
