package passionx3.jkdk.dao.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import passionx3.jkdk.domain.Category;

@Mapper
public interface CategoryMapper {
	@Select("SELECT categoryId AS categoryId, name AS name FROM category")
	List<Category> getAllCategories();
	
	@Select("SELECT Name FROM category WHERE categoryId = #{categoryId}")
	String getCategoryNameByCategoryId(@Param("categoryId") int categoryId);	
}
