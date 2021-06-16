package passionx3.jkdk.dao.mybatis.mapper;

/*
 *    Copyright 2010-2013 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */




import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import passionx3.jkdk.domain.Item;

@Mapper
public interface ItemMapper {
	@Insert("INSERT INTO item (itemId, themeid, name, uploadDate, price, likeNum, thumbnail1, thumbnail2, thumbnail3, isforsale, description, approval, userid) "
			+ "values (#{item.itemId}, #{item.themeId}, #{item.name}, sysdate, #{item.price}, 0, "
				+ "#{item.thumbnail1, jdbcType=VARCHAR}, #{item.thumbnail2, jdbcType=VARCHAR}, #{item.thumbnail3, jdbcType=VARCHAR}, 1, #{item.description}, 0, #{item.producerId})")
	@SelectKey(statement="select itemId_seq.nextval FROM DUAL", keyProperty="item.itemId", before=true, resultType=int.class)
	int registerItem(@Param("item") Item item);
	
	@Update("UPDATE item "
			+ "SET themeid = #{item.themeid}, price = #{item.price}, "
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
