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

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;

import passionx3.jkdk.domain.BattleSale;

@Mapper
public interface BattleSaleMapper {
	@Select("SELECT * FROM battleSale WHERE rownum = 1 ORDER BY opentime DESC")
	BattleSale getBattleSale(); //가장 최근의 battle sale Get
	
	@Insert("INSERT INTO BattleSale (battlesaleId, itemId1, itemId2, votes1, votes2, discountRate, openTime, closeTime) " + 
			"SELECT #{battleSaleId}, #{itemId1}, #{itemId2}, 0, 0, 20, #{openTime}, #{closeTime} from dual " + 
			"WHERE NOT EXISTS ( SELECT openTime FROM battlesale WHERE opentime = '#{openTime}')")
	@SelectKey(statement="select battleSaleId_seq.nextval FROM DUAL", keyProperty="battleSaleId", before=true, resultType=int.class)
	void insetBattleSale(@Param("itemId1") String itemId1, @Param("itemId2") String itemId2, @Param("openTime") String openTime, @Param("closeTime") String closeTime);
	
	@Insert("INSERT into vote (userId, battleSaleId, voteId) " + 
			"SELECT #{userId}, #{battleSaleId}, #{voteId} FROM DUAL " + 
			"WHERE NOT EXISTS (SELECT userId FROM VOTE WHERE userId='#{userId}') ")
	@SelectKey(statement="select votdId_seq.nextval FROM DUAL", keyProperty="voteId", before=true, resultType=int.class)
	int insertVote(@Param("battleId") String battleId, @Param("userId") String userId);
	
	@Delete("TRUNCATE TABLE vote ")
	void resetVote();
	
	@Select("SELECT case WHEN votes1 > votes2 THEN itemId1 WHEN votes1 < votes2 THEN itemId2 END AS winner " + 
			"FROM battleSale WHERE rownum = 1 ORDER BY opentime DESC")
	int getWinnerItemId();
	
	@Insert("UPDATE battlesale SET votes1 = votes1 + 1 where battlesaleid = 1")
	void updateBattleSaleVote1(@Param("battleId") String battleId);
	
	@Insert("UPDATE battlesale SET votes2 = votes2 + 1 where battlesaleid = 1")
	void updateBattleSaleVote2(@Param("battleId") String battleId);
}
