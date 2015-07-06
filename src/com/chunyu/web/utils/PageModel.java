package com.chunyu.web.utils;

import java.util.List;

public class PageModel<T> {

	//总记录数
	public long totalCount;
	//每一页显示的记录数
	public int pageSize;
	//起始记录
	public int pageOffSet;
	//查询结果
	public List<T> list;
	public PageModel() {
		this.pageSize=10;//设置每页默认显示10条记录
	}
	
	public Long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageOffSet() {
		return pageOffSet;
	}
	public void setPageOffSet(int pageOffSet) {
		this.pageOffSet = pageOffSet;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	
	
}
