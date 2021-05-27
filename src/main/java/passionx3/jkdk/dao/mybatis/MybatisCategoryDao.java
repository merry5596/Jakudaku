package passionx3.jkdk.dao.mybatis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import passionx3.jkdk.dao.CategoryDao;
import passionx3.jkdk.dao.mybatis.mapper.CategoryMapper;
import passionx3.jkdk.domain.Category;

@Repository
public class MybatisCategoryDao implements CategoryDao {
	@Autowired
	private CategoryMapper categoryMapper;

	@Override
	public List<Category> getAllCategories() throws DataAccessException {
		return categoryMapper.getAllCategories();
	}

	@Override
	public String getCategoryNameByCategoryId(int categoryId) throws DataAccessException {
		return categoryMapper.getCategoryNameByCategoryId(categoryId);
	}
 
	
}
