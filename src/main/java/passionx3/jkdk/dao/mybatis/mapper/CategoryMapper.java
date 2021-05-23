package passionx3.jkdk.dao.mybatis.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import passionx3.jkdk.domain.Account;

@Mapper
public interface CategoryMapper {
	
	@Select("SELECT Name FROM category WHERE categoryId = #{CategoryId}")
	String getCategoryNameByCategoryId(@Param("CategoryId") int CategoryId);
		
}
