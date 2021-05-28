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
import org.apache.ibatis.annotations.Update;

import passionx3.jkdk.domain.Funding;

@Mapper
public interface FundingMapper {
	@Select("SELECT i.itemId AS itemId, i.name AS name, i.price AS price, i.likeNum AS likeNum, i.thumbnail1 AS thumbnail1, "
			+ "TO_DATE(f.finishDate, 'YYYY-MM-DD HH24:MI:SS') AS finishDate, f.purchaseQuantity AS purchaseQuantity, f.targetQuantity AS targetQuantity "
			+ "FROM item i, fundingitem f "
			+ "WHERE i.itemId = f.itemId "
			+ "AND i.name LIKE '%#{keyword}%' "
			+ "AND i.isForSale = 1 "
			+ "AND i.approval = 1")
	List<Funding> getFundingItemsByKeyword(@Param("keyword") String keyword);

	
	@Select("SELECT i.itemId AS itemId, name, userID AS producerId, approval, description, uploaddate, price, targetquantity " + 
			"FROM item i, fundingitem f WHERE approval = 0 AND i.itemid = f.itemid")
	List<Funding> getNotApprovedFundingItems();
  
	@Select("SELECT ITEMID, USERID AS PRODUCERID, NAME, UPLOADDATE, PRICE, LIKENUM, THUMNAIL1, THUMNAIL2, THUMNAIL3, "
			+ "ISFORSALE, DESCRIPTION, APPROVAL, FINISHDATE, PURCHASEQUANTITY, TARGETQUANTITY, ISSALEENDED"
			+ " FROM ITEM, FUNDINGITEM WHERE ITEM.USERID = #{userId} AND ITEM.ITEMID = FUNDINGITEM.ITEMID ORDER BY UPLOADDATE")
	List<Funding> getFundingItemListByProducerId(@Param("userId") String userId);


	@Select("SELECT * FROM (SELECT i.itemId AS itemId, name, uploaddate, price, likenum, thumbnail1, isforsale," + 
			"description, themeid, userid, finishdate, targetquantity FROM fundingitem f, item i " + 
			"WHERE f.itemid = i.itemid  AND i.approval = 1 AND i.isforsale = 1 ORDER BY i.uploaddate DESC ) WHERE ROWNUM < 5")
	List<Funding> getNewFundingItemListforHome();

	@Select("SELECT I.ITEMID, I.USERID AS PRODUCERID, A.ALIAS AS PRODUCERNAME, I.THEMEID AS THEMEID, T.THEMENAME, I.NAME AS NAME, I.UPLOADDATE AS UPLOADDATE,"
			+ " I.PRICE AS PRICE, I.LIKENUM AS LIKENUM, I.THUMBNAIL1 AS THUMBNAIL1, I.THUMBNAIL2 AS THUMBNAIL2, I.THUMBNAIL3 AS THUMBNAIL3,"
			+ " I.ISFORSALE AS ISFORSALE, I.DESCRIPTION AS DESCRIPTION, I.APPROVAL AS APPROVAL, "
			+ " F.FINISHDATE AS FINISHDATE, F.PURCHASEQUANTITY AS PURCHASEQUANTITY, F.TARGETQUANTITY AS TARGETQUANTITY"
			+ " FROM ITEM I, FUNDINGITEM F, LINEITEM L THEME T, ACCOUNT A"
			+ " WHERE #{lineItemId} = L.LINEITEMID AND L.ITEMID = I.ITEMID AND I.ITEMID = F.ITEMID AND T.THEMEID = I.THEMEID AND A.USERID = I.USERID")
	Funding getFundingItemByLineItemId(@Param("lineItemId") int lineItemId);

	
	@Select("SELECT i.ITEMID AS itemID, USERID, NAME, UPLOADDATE, PRICE, LIKENUM, THUMBNAIL1, THUMBNAIL2, THUMBNAIL3, ISFORSALE, DESCRIPTION, APPROVAL, FINISHDATE, PURCHASEQUANTITY, TARGETQUANTITY, ISSALEENDED " + 
			"FROM ITEM i, FUNDINGITEM f WHERE i.ITEMID = f.ITEMID  AND i.isforsale = 1 AND i.approval = 1 ORDER BY UPLOADDATE")
	List<Funding> getFundingItemList();

	@Insert("INSERT INTO fundingitem (itemId, finishDate, purchaseQuantity, targetQuantity) "
			+ "values (#{funding.itemId}, TO_DATE(#{funding.finishDate}, 'YYYY/MM/DD HH24:MI'), #{funding.purchaseQuantity}, #{funding.targetQuantity})")
	int registerFundingItem(@Param("funding") Funding funding);
	
	@Update("UPDATE onlineitem "
			+ "SET pcfile = #{onlineItem.pcFile}, tabletfile = #{onlineItem.tabletFile}, phonefile = #{onlineItem.phoneFile}, categoryid = #{onlineItem.categoryId} "
			+ "WHERE ITEMID = #{onlineItem.itemId}")
	int updateFundingItem(@Param("funding") Funding funding);

	@Select("SELECT i.ITEMID AS itemID, USERID, NAME, UPLOADDATE, PRICE, LIKENUM, THUMBNAIL1, THUMBNAIL2, THUMBNAIL3, ISFORSALE, DESCRIPTION, APPROVAL, FINISHDATE, PURCHASEQUANTITY, TARGETQUANTITY, ISSALEENDED " + 
			"FROM ITEM i, FUNDINGITEM f WHERE i.ITEMID = f.ITEMID  AND i.isforsale = 1 AND i.approval = 1 AND i.themeId = #{themeId} ORDER BY UPLOADDATE")
	List<Funding> getFundingItemListByTheme(int themeId);

}
