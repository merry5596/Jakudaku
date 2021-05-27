package passionx3.jkdk.dao;

import java.util.List;

import passionx3.jkdk.domain.Category;

public interface CategoryDao {
	List<Category> getAllCategories();
	String getCategoryNameByCategoryId(int categoryId);
}
