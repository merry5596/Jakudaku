package passionx3.jkdk.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import passionx3.jkdk.domain.Theme;

public interface ThemeDao {
	List<Theme> getAllThemes() throws DataAccessException;
}
