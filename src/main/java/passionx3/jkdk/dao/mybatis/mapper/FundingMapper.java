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

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import passionx3.jkdk.domain.Funding;
import passionx3.jkdk.domain.Item;

@Mapper
@Repository
public interface FundingMapper {
	@Select("SELECT i.itemId, i.name, i.price, i.likeNum, i.thumbnail1, "
			+ "TO_DATE(f.finishDate, 'YYYY-MM-DD HH24:MI:SS'), f.purchaseQuantity, f.targetQuantity "
			+ "FROM item i, fundingitem f "
			+ "WHERE i.itemId = f.itemId "
			+ "AND i.name LIKE '%#{keyword}%' "
			+ "AND i.isForSale = 1 "
			+ "AND i.approval = 1")
	List<Item> getFundingItemsByKeyword(@Param("keyword") String keyword);

	@Select("SELECT ITEMID, USERID, NAME, UPLOADDATE, PRICE, LIKENUM, THUMNAIL1, THUMNAIL2, THUMNAIL3, "
			+ "ISFORSALE, DESCRIPTION, APPROVAL, FINISHDATE, PURCHASEQUANTITY, TARGETQUANTITY, ISSALEENDED"
			+ " FROM ITEM, FUNDINGITEM WHERE ITEM.USERID = #{userId} AND ITEM.ITEMID = FUNDINGITEM.ITEMID ORDER BY UPLOADDATE")
	List<Funding> getFundingItemListByProducerId(String userId);

}
