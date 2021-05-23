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



import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import passionx3.jkdk.domain.Item;
import passionx3.jkdk.domain.Online;

@Mapper
public interface OnlineMapper {
	@Select("SELECT i.itemId AS itemId,  i.name AS name, i.price AS price, i.likeNum AS likeNum, i.thumbnail1 AS thumbnail1, "
			+ "i.isForSale AS isForSale, o.totalRate AS totalRate, o.pcFile AS pcFile, o.tabletFile AS tabletFile, o.phoneFile AS phoneFile "
			+ "FROM item i, onlineitem o "
			+ "WHERE i.itemId = o.itemId "
			+ "AND i.name LIKE '%#{keyword}%' "
			+ "AND i.isForSale = 1 "
			+ "AND i.approval = 1")
	List<Online> getOnlineItemsByKeyword(@Param("keyword") String keyword);
	
	@Select("SELECT i.itemId AS itemId, name, userID AS producerId, themeId, approval, description, categoryid, uploaddate, price " + 
			"FROM item i, onlineitem o WHERE approval = 0 AND i.itemId = o.itemId")
	List<Online> getNotApprovedOnlineItems();

	@Insert("UPDATE ITEM SET APPROVAL = -1 WHERE ITEMID = #{itemId}")
	int refuseItem(@Param("itemId") int itemId);

	@Insert("UPDATE ITEM SET APPROVAL = 1 WHERE ITEMID = #{itemId}")
	int approveItem(@Param("itemId") int itemId);
	
	@Select("SELECT ITEMID, USERID, NAME, UPLOADDATE, PRICE, LIKENUM, THUMNAIL1, THUMNAIL2, THUMNAIL3, "
			+ "ISFORSALE, DESCRIPTION, APPROVAL, CATEGORYID, THEMEID, PCFILE, TABLEFILE, PHONEFILE"
			+ " FROM ITEM, ONLINEITEM WHERE ITEM.USERID = #{userId} AND ITEM.ITEMID = ONLINEITEM.ITEMID ORDER BY UPLOADDATE")
	List<Online> getOnlineItemListByProducerId(String userId);

	@Select("SELECT i.itemID AS itemID FROM ITEM i, ONLINEITEM o WHERE categoryID = #{categoryId}  AND i.itemId = o.itemId AND ROWNUM = 1 order by dbms_random.value")
	String getOnlineItemIdByCategoryforSale(@Param("categoryId") int categoryId);
	
	@Select("SELECT i.itemId AS itemId, name, uploaddate, price, likenum, thumbnail1, thumbnail2, thumbnail3, isforsale, categoryid, description, themeid, userid, approval, pcfile, tablefile, phonefile\r\n" + 
			"FROM item i, onlineitem o WHERE i.itemid = o.itemid AND i.itemId = #{itemId}")
	Online getOnlineItemById(@Param("itemId") int itemId);

	@Select("SELECT * FROM (SELECT i.itemId AS itemId, name, uploaddate, price, likenum, thumbnail1, isforsale, categoryid, description, themeid, userid " + 
			"FROM ONLINEITEM o, item i WHERE o.itemid = i.itemid AND i.approval = 1 AND i.isforsale = 1 ORDER BY i.likenum DESC) WHERE ROWNUM < 5")
	List<Online> getBestOnlineItemListforHome();
	
	@Select("SELECT * FROM (SELECT i.itemId AS itemId, name, uploaddate, price, likenum, thumbnail1, isforsale, categoryid, description, themeid, userid " + 
			"FROM ONLINEITEM o, item i WHERE o.itemid = i.itemid AND i.approval = 1 AND i.isforsale = 1 ORDER BY i.uploaddate DESC) WHERE ROWNUM < 5")
	List<Online> getNewOnlineItemListforHome();

	@Select("SELECT i.itemId AS itemId, name, uploaddate, price, likenum, thumbnail1, isforsale, categoryid, description, themeid, userid, pcFile, tableFile, phoneFile " + 
			"FROM ONLINEITEM o, item i WHERE o.itemid = i.itemid AND i.approval = 1 AND i.isforsale = 1 AND i.categoryid = #{categoryId} ORDER BY i.uploaddate DESC")
	List<Online> getOnlineItemListByCategory(int categoryId);
}
