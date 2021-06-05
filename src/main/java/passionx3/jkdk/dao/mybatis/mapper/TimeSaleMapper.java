package passionx3.jkdk.dao.mybatis.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;

import passionx3.jkdk.domain.TimeSale;

@Mapper
public interface TimeSaleMapper {
	
	@Select("SELECT timesaleId, opentime, closetime, TO_DATE(closetime, 'YYYY-MM-DD HH24:MI:SS') AS closetime, itemid FROM TIMESALE WHERE openTime = #{openTime}")
	TimeSale getTimeSale( @Param("openTime") String openTime);
	
	@Insert("INSERT INTO TimeSale (timeSaleId, itemId,openTime, closeTime) " + 
			"SELECT #{timeSaleId}, #{itemId}, #{openTime}, #{closeTime} from dual " + 
			"WHERE NOT EXISTS ( SELECT openTime FROM timesale WHERE opentime = #{openTime})")
	@SelectKey(statement="select timeSaleId_seq.nextval FROM DUAL", keyProperty="timeSaleId", before=true, resultType=int.class)
	void insertTimeSale(@Param("itemId") int itemId, @Param("openTime") String openTime, @Param("closeTime") String closeTime);

	@Insert("UPDATE onlineitem o set o.salestate = 0 where EXISTS ( SELECT * FROM timeSale b WHERE b.closetime <= #{date})")
	void updateNotSale(String date);
	
	
}
