package passionx3.jkdk.dao.mybatis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import passionx3.jkdk.dao.ThemeDao;
import passionx3.jkdk.dao.mybatis.mapper.ThemeMapper;
import passionx3.jkdk.domain.Theme;

@Repository
public class MybatisThemeDao implements ThemeDao{
	@Autowired
	private ThemeMapper themeMapper;

	@Override
	public List<Theme> getAllThemes() throws DataAccessException {
		return themeMapper.getAllThemes();
	}

}
