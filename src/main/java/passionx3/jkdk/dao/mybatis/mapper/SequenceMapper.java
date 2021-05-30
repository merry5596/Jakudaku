package passionx3.jkdk.dao.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import passionx3.jkdk.domain.Sequence;

@Mapper
public interface SequenceMapper {
	@Select("SELECT name, nextid FROM SEQUENCE WHERE NAME = #{name}")
	Sequence getSequence(Sequence sequence);
	Sequence getOracleSequence(Sequence sequence);
	void updateSequence(Sequence sequence);
	@Select("SELECT ORDERID_SEQ.NEXTVAL FROM DUAL")
	int getOrderSequenceNextVal();
	@Select("SELECT LINEITEMID_SEQ.NEXTVAL FROM DUAL")
	int getLineItemSequenceNextVal();
}