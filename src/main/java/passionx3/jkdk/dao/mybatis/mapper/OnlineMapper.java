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
			+ "ISFORSALE, DESCRIPTION, APPROVAL, CATEGORYID, THEMEID, PCFILE, TABLETFILE, PHONEFILE"
			+ " FROM ITEM, ONLINEITEM WHERE ITEM.USERID = #{userId} AND ITEM.ITEMID = ONLINEITEM.ITEMID ORDER BY UPLOADDATE")
	List<Online> getOnlineItemListByProducerId(String userId);

	@Select("SELECT i.itemID AS itemID FROM ITEM i, ONLINEITEM o WHERE categoryID = #{categoryId}  AND i.itemId = o.itemId AND ROWNUM = 1 order by dbms_random.value")
	String getOnlineItemIdByCategoryforSale(@Param("categoryId") int categoryId);
	
	@Select("SELECT i.itemId AS itemId, name, uploaddate, price, likenum, thumbnail1, thumbnail2, thumbnail3, isforsale, categoryid, description, themeid, userid, approval, pcfile, tabletfile, phonefile\r\n" + 
			"FROM item i, onlineitem o WHERE i.itemid = o.itemid AND i.itemId = #{itemId}")
	Online getOnlineItemById(@Param("itemId") int itemId);

	@Select("SELECT * FROM (SELECT i.itemId AS itemId, name, uploaddate, price, likenum, thumbnail1, isforsale, categoryid, description, themeid, userid " + 
			"FROM ONLINEITEM o, item i WHERE o.itemid = i.itemid AND i.approval = 1 AND i.isforsale = 1 ORDER BY i.likenum DESC) WHERE ROWNUM < 5")
	List<Online> getBestOnlineItemListforHome();
	
	@Select("SELECT * FROM (SELECT i.itemId AS itemId, name, uploaddate, price, likenum, thumbnail1, isforsale, categoryid, description, themeid, userid " + 
			"FROM ONLINEITEM o, item i WHERE o.itemid = i.itemid AND i.approval = 1 AND i.isforsale = 1 ORDER BY i.uploaddate DESC) WHERE ROWNUM < 5")
	List<Online> getNewOnlineItemListforHome();

	@Select("SELECT I.ITEMID AS ITEMID, I.USERID AS PRODUCERID, A.ALIAS AS PRODUCERNAME, I.THEMEID AS THEMEID, T.NAME AS THEMENAME, I.NAME AS NAME, I.UPLOADDATE AS UPLOADDATE,"
			+ " I.PRICE AS PRICE, I.LIKENUM AS LIKENUM, I.THUMBNAIL1 AS THUMBNAIL1, I.THUMBNAIL2 AS THUMBNAIL2, I.THUMBNAIL3 AS THUMBNAIL3, I.ISFORSALE AS ISFORSALE, I.DESCRIPTION AS DESCRIPTION,"
			+ " I.APPROVAL AS APPROVAL, O.CATEGORYID AS CATEGORYID, C.CATEGORYNAME AS CATEGORYNAME, L.SALESTATE AS SALESTATE, O.TOTALRATE AS TOTALRATE, O.PCFILE AS PCFILE, O.TABLETFILE AS TABLETFILE, O.PHONEFILE AS PHONEFILE"
			+ " FROM ITEM I, ONLINEITEM O LINEITEM L, ACCOUNT A, THEME T, CATEGORY C"
			+ " WHERE LINEITEM.LINEITEMID = #{lineItemId} AND LINEITEM.ITEMID = I.ITEMID"
			+ " AND I.ITEMID = O.ITEMID AND A.USERID = ITEM.USERID AND T.THEMEID = I.THEMEID AND C.CATEGORYID = O.CATEGORYID")
	Online getOnlineItemByLineItemId(int lineItemId);

	// 여기부터 조건 검색
	@Select("SELECT i.itemId AS itemId, name, uploaddate, price, likenum, thumbnail1, isforsale, categoryid, description, themeid, userid, pcFile, tabletFile, phoneFile, totalrate, salestate"
			+ " FROM ONLINEITEM o, item i"
			+ " WHERE o.itemid = i.itemid"
			+ " AND i.approval = 1 AND i.isforsale = 1"
			+ " AND o.categoryid = #{categoryId} AND i.name LIKE '%' ||  #{keyword} || '%'"
			+ " ORDER BY i.uploaddate DESC")
	List<Online> getOnlineItemListByCategoryOrderByUploadDate(int categoryId, String keyword);

	@Select("SELECT i.itemId AS itemId, name, uploaddate, price, likenum, thumbnail1, isforsale, categoryid, description, themeid, userid, pcFile, tabletFile, phoneFile, totalrate, salestate"
			+ " FROM ONLINEITEM o, item i"
			+ " WHERE o.itemid = i.itemid AND i.approval = 1 AND i.isforsale = 1"
			+ " AND o.categoryid = #{categoryId} AND i.themeId = #{themeId} AND i.name LIKE '%' ||  #{keyword} || '%'"
			+ " ORDER BY i.uploaddate DESC")
	List<Online> getOnlineItemListByThemeOrderByUploadDate(int categoryId, int themeId, String keyword);
	
	@Select("SELECT i.itemId AS itemId, name, uploaddate, price, likenum, thumbnail1, isforsale, categoryid, description, themeid, userid, pcFile, tabletFile, phoneFile, totalrate, salestate"
			+ " FROM ONLINEITEM o, item i"
			+ " WHERE o.itemid = i.itemid"
			+ " AND i.approval = 1 AND i.isforsale = 1"
			+ " AND o.categoryid = #{categoryId} AND i.name LIKE '%' ||  #{keyword} || '%'"
			+ " ORDER BY i.likenum DESC")
	List<Online> getOnlineItemListByCategoryOrderByLikeNum(int categoryId, String keyword);

	@Select("SELECT i.itemId AS itemId, name, uploaddate, price, likenum, thumbnail1, isforsale, categoryid, description, themeid, userid, pcFile, tabletFile, phoneFile, totalrate, salestate"
			+ " FROM ONLINEITEM o, item i"
			+ " WHERE o.itemid = i.itemid AND i.approval = 1 AND i.isforsale = 1"
			+ " AND o.categoryid = #{categoryId} AND i.themeId = #{themeId} AND i.name LIKE '%' ||  #{keyword} || '%'"
			+ " ORDER BY i.likenum DESC")
	List<Online> getOnlineItemListByThemeOrderByLikeNum(int categoryId, int themeId, String keyword);
	
	@Select("SELECT i.itemId AS itemId, name, uploaddate, price, likenum, thumbnail1, isforsale, categoryid, description, themeid, userid, pcFile, tabletFile, phoneFile, totalrate, salestate"
			+ " FROM ONLINEITEM o, item i"
			+ " WHERE o.itemid = i.itemid"
			+ " AND i.approval = 1 AND i.isforsale = 1"
			+ " AND o.categoryid = #{categoryId} AND i.name LIKE '%' ||  #{keyword} || '%'"
			+ " ORDER BY o.totalrate DESC")
	List<Online> getOnlineItemListByCategoryOrderByTotalRate(int categoryId, String keyword);

	@Select("SELECT i.itemId AS itemId, name, uploaddate, price, likenum, thumbnail1, isforsale, categoryid, description, themeid, userid, pcFile, tabletFile, phoneFile, totalrate, salestate"
			+ " FROM ONLINEITEM o, item i"
			+ " WHERE o.itemid = i.itemid AND i.approval = 1 AND i.isforsale = 1"
			+ " AND o.categoryid = #{categoryId} AND i.themeId = #{themeId} AND i.name LIKE '%' ||  #{keyword} || '%'"
			+ " ORDER BY o.totalrate DESC")
	List<Online> getOnlineItemListByThemeOrderByTotalRate(int categoryId, int themeId, String keyword);
	
	@Select("SELECT i.itemId AS itemId, name, uploaddate, price, likenum, thumbnail1, isforsale, categoryid, description, themeid, userid, pcFile, tabletFile, phoneFile, totalrate, salestate"
			+ " FROM ONLINEITEM o, item i"
			+ " WHERE o.itemid = i.itemid"
			+ " AND i.approval = 1 AND i.isforsale = 1"
			+ " AND o.categoryid = #{categoryId} AND i.name LIKE '%' ||  #{keyword} || '%'"
			+ " ORDER BY i.price")
	List<Online> getOnlineItemListByCategoryOrderByPriceLow(int categoryId, String keyword);

	@Select("SELECT i.itemId AS itemId, name, uploaddate, price, likenum, thumbnail1, isforsale, categoryid, description, themeid, userid, pcFile, tabletFile, phoneFile, totalrate, salestate"
			+ " FROM ONLINEITEM o, item i"
			+ " WHERE o.itemid = i.itemid AND i.approval = 1 AND i.isforsale = 1"
			+ " AND o.categoryid = #{categoryId} AND i.themeId = #{themeId} AND i.name LIKE '%' ||  #{keyword} || '%'"
			+ " ORDER BY i.price")
	List<Online> getOnlineItemListByThemeOrderByPriceLow(int categoryId, int themeId, String keyword);
	
	@Select("SELECT i.itemId AS itemId, name, uploaddate, price, likenum, thumbnail1, isforsale, categoryid, description, themeid, userid, pcFile, tabletFile, phoneFile, totalrate, salestate"
			+ " FROM ONLINEITEM o, item i"
			+ " WHERE o.itemid = i.itemid"
			+ " AND i.approval = 1 AND i.isforsale = 1"
			+ " AND o.categoryid = #{categoryId} AND i.name LIKE '%' ||  #{keyword} || '%'"
			+ " ORDER BY i.price DESC")
	List<Online> getOnlineItemListByCategoryOrderByPriceHigh(int categoryId, String keyword);

	@Select("SELECT i.itemId AS itemId, name, uploaddate, price, likenum, thumbnail1, isforsale, categoryid, description, themeid, userid, pcFile, tabletFile, phoneFile, totalrate, salestate"
			+ " FROM ONLINEITEM o, item i"
			+ " WHERE o.itemid = i.itemid AND i.approval = 1 AND i.isforsale = 1"
			+ " AND o.categoryid = #{categoryId} AND i.themeId = #{themeId} AND i.name LIKE '%' ||  #{keyword} || '%'"
			+ " ORDER BY i.price DESC")
	List<Online> getOnlineItemListByThemeOrderByPriceHigh(int categoryId, int themeId, String keyword);

}
