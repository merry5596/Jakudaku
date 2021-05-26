package passionx3.jkdk.dao.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import passionx3.jkdk.domain.Theme;

@Mapper
public interface ThemeMapper {

	@Select("SELECT * FROM THEME")
	List<Theme> getAllThemes();
}
