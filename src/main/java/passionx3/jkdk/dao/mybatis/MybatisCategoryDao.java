package passionx3.jkdk.dao.mybatis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import passionx3.jkdk.dao.AccountDao;
import passionx3.jkdk.dao.CategoryDao;
import passionx3.jkdk.dao.mybatis.mapper.AccountMapper;
import passionx3.jkdk.dao.mybatis.mapper.CategoryMapper;
import passionx3.jkdk.domain.Account;
import passionx3.jkdk.domain.Theme;

@Repository
public class MybatisCategoryDao implements CategoryDao{
	@Autowired
	private CategoryMapper categoryMapper;

	@Override
	public List<Theme> getAllCategories() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCategoryNameByCategoryId(int CategoryId) {
		return categoryMapper.getCategoryNameByCategoryId(CategoryId);
	}
 
	
}
