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

package passionx3.jkdk.dao.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import passionx3.jkdk.domain.Item;
import passionx3.jkdk.domain.Online;

@Mapper
public interface OnlineMapper {
	@Select("SELECT i.itemId, i.name, i.price, i.likeNum, i.thumbnail1, i.isForSale, o.totalRate, o.pcFile, otabletFile, o.phoneFile "
			+ "FROM item i, onlineitem o "
			+ "WHERE i.itemId = o.itemId "
			+ "AND i.name LIKE '%#{keyword}%' "
			+ "AND i.isForSale = 1 "
			+ "AND i.approval = 1")
	List<Item> getOnlineItemsByKeyword(@Param("keyword") String keyword);
	
	@Select("SELECT i.itemId, name, userID AS producerId, themeId, approval, description, categoryid, uploaddate, price " + 
			"FROM item i, onlineitem o WHERE approval = 0 AND i.itemId = o.itemId")
	List<Online> getNotApprovedOnlineItems();

	@Insert("UPDATE ITEM SET APPROVAL = -1 WHERE ITEMID = #{itemId}")
	int refuseItem(@Param("itemId") int itemId);

	@Insert("UPDATE ITEM SET APPROVAL = 1 WHERE ITEMID = #{itemId}")
	int approveItem(@Param("itemId") int itemId);

}
