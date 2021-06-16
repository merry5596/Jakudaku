package passionx3.jkdk.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import passionx3.jkdk.domain.Category;

public interface CategoryDao {
	List<Category> getAllCategories() throws DataAccessException;
	String getCategoryNameByCategoryId(int categoryId) throws DataAccessException;
	Category getCategoryByCategoryId(int catetgoryId) throws DataAccessException;
}
