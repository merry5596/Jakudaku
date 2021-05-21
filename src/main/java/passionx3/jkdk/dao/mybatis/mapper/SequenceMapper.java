package passionx3.jkdk.dao.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;

import passionx3.jkdk.domain.Sequence;

@Mapper
public interface SequenceMapper {
  Sequence getSequence(Sequence sequence);
  Sequence getOracleSequence(Sequence sequence);
  void updateSequence(Sequence sequence);
}
