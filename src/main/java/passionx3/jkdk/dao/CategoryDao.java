package passionx3.jkdk.dao;

import java.util.List;

import passionx3.jkdk.domain.Theme;

public interface CategoryDao {
	List<Theme> getAllCategories();
	
	String getCategoryNameByCategoryId(int CategoryId);
}
