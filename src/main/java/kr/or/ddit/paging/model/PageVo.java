package kr.or.ddit.paging.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class PageVo {

	private int page;
	private int pageSize;
	private String val;
	
	public int getPage() {
		return page == 0 ? 1 : page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getPageSize() {
		return pageSize == 0 ? 5 : pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public String getVal() {
		return val;
	}

	public void setVal(String val) {
		this.val = val;
	}
	public PageVo(int page, int pageSize) {
		this.page = page;
		this.pageSize = pageSize;
	}
}
