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

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

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
  
	@Select("SELECT ITEMID, USERID, NAME, UPLOADDATE, PRICE, LIKENUM, THUMNAIL1, THUMNAIL2, THUMNAIL3, "
			+ "ISFORSALE, DESCRIPTION, APPROVAL, FINISHDATE, PURCHASEQUANTITY, TARGETQUANTITY, ISSALEENDED"
			+ " FROM ITEM, FUNDINGITEM WHERE ITEM.USERID = #{userId} AND ITEM.ITEMID = FUNDINGITEM.ITEMID ORDER BY UPLOADDATE")
	List<Funding> getFundingItemListByProducerId(String userId);


}
