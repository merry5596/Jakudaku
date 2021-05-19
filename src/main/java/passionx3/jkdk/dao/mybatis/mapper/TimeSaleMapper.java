package passionx3.jkdk.dao.mybatis.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;

import passionx3.jkdk.domain.TimeSale;

@Mapper
public interface TimeSaleMapper {
	
	@Select("SELECT * FROM TimeSale WHERE rownum = 1 ORDER BY opentime DESC")
	TimeSale getTimeSale(); //가장 최근의 battle sale Get
	
	@Insert("INSERT INTO TimeSale (timeSaleId, itemId, discountRate, openTime, closeTime, quantity) " + 
			"SELECT #{timeSaleId}, #{itemId}, 50, #{openTime}, #{closeTime}, 0 from dual " + 
			"WHERE NOT EXISTS ( SELECT openTime FROM timesale WHERE opentime = '#{openTime}')")
	@SelectKey(statement="select timeSaleId_seq.nextval FROM DUAL", keyProperty="timeSaleId", before=true, resultType=int.class)
	void insetTimeSale(@Param("itemId") String itemId, @Param("openTime") String openTime, @Param("closeTime") String closeTime);
	
	
}
