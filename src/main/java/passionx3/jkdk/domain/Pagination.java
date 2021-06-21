package passionx3.jkdk.domain;

public class Pagination {
	private int curPage;         //현재 페이지
	private int itemPerPage;     //한 페이지에 들어있는 아이템의 수
	private int totalItemCount;  //총 아이템 수
	private int pageUnit = 10;

	public Pagination(int curPage, int itemPerPage, int totalItemCount) {
		this.curPage = curPage;
		this.itemPerPage = itemPerPage;
		this.totalItemCount = totalItemCount;
	}


	public int getPageCount() {  // 총 몇 페이지
		return (totalItemCount - 1) / itemPerPage + 1;
	}
	
	public int getCurSection() {	// 현재 몇 번째 섹션
		return (curPage - 1) / pageUnit + 1;
	}
	
	public int getSectionStartPage() {	// 현재 섹션 시작 페이지
		return (getCurSection() - 1) * pageUnit + 1;
	}
	
	public int getSectionEndPage() {	// 현재 섹션 끝 페이지
		if (getLastSection() == getCurSection())
			return getPageCount();
		return getCurSection() * pageUnit;
	}
	
	public int getNextSectionFirstPage() {	// 다음 섹션 시작 페이지
		int nextSectionLastPage = (getCurSection() + 1) * pageUnit;
		return nextSectionLastPage - pageUnit + 1;
	}
	
	public int getBeforeSectionLastPage() {	// 이전 섹션 끝 페이지
		return (getCurSection() - 1) * pageUnit;
	}
	
	public int getLastSection() {	// 마지막 섹션
		return getPageCount() / pageUnit + 1;
	}
	
	public int getCurPage() {
		return curPage;
	}
	
	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}

	public int getItemPerPage() {
		return itemPerPage;
	}
	
	public void setItemPerPage(int itemPerPage) {
		this.itemPerPage = itemPerPage;
	}
	
	public int getTotalItemCount() {
		return totalItemCount;
	}

	public void setTotalItemCount(int totalItemCount) {
		this.totalItemCount = totalItemCount;
	}

	public int getPageUnit() {
		return pageUnit;
	}

}
