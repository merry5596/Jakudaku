package passionx3.jkdk.dao.mybatis.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;

import passionx3.jkdk.domain.TimeSale;

@Mapper
public interface TimeSaleMapper {
	
	@Select("SELECT timesaleId, TO_CHAR(opentime, 'YYYY-MM-DD HH24:MI:SS') AS opentime, TO_CHAR(closetime, 'YYYY-MM-DD HH24:MI:SS') AS closetime, itemid FROM TIMESALE WHERE openTime =  TO_DATE(#{openTime}, 'yyyy-MM-dd hh24:mi:ss')")
	TimeSale getTimeSale( @Param("openTime") String openTime);
	
	@Insert("INSERT INTO TimeSale (timeSaleId, itemId, openTime, closeTime) " + 
			"SELECT #{timeSaleId}, #{itemId}, TO_DATE(#{openTime}, 'yyyy-MM-dd hh24:mi:ss') AS openTime, TO_DATE(#{closeTime}, 'yyyy-MM-dd hh24:mi:ss') AS closeTime from dual " + 
			"WHERE NOT EXISTS ( SELECT TO_DATE(#{openTime}, 'yyyy-MM-dd hh24:mi:ss') AS openTime FROM timesale WHERE opentime = TO_DATE(#{openTime}, 'yyyy-MM-dd hh24:mi:ss'))")
	@SelectKey(statement="select timeSaleId_seq.nextval FROM DUAL", keyProperty="timeSaleId", before=true, resultType=int.class)
	int insertTimeSale(@Param("itemId") int itemId, @Param("openTime") String openTime, @Param("closeTime") String closeTime);

	@Insert("MERGE INTO onlineitem o " + 
			"USING (SELECT t.itemid FROM  timeSale t WHERE t.closetime <= TO_DATE(#{date}, 'yyyy-MM-dd hh24:mi:ss')) t " + 
			"ON (t.itemid = o.itemid) WHEN MATCHED THEN UPDATE SET o.salestate = 0")
	int updateNotSale(@Param("date") String date);
	
	
}
