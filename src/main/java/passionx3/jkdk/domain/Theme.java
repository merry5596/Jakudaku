package passionx3.jkdk.domain;

public class Theme {
	int themeId;
	String name;
	
	public Theme() {
	}

	public Theme(int themeId, String name) {
		this.themeId = themeId;
		this.name = name;
	}
	
	public int getThemeId() {
		return themeId;
	}
	public void setThemeId(int themeId) {
		this.themeId = themeId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
