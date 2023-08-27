package com.osvaldo.dto;

import java.util.List;

public class QueryResults {
	@SuppressWarnings("rawtypes")
	private List items;
	
	private Long totalCount;
	
	@SuppressWarnings("rawtypes")
	public QueryResults(List items, Long totalCount) {
		super();
		this.items = items;
		this.totalCount = totalCount;
	}
	
	@SuppressWarnings("rawtypes")
	public List getItems() {
		return items;
	}
	@SuppressWarnings("rawtypes")
	public void setItems(List items) {
		this.items = items;
	}
	public Long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	

	@Override
	public String toString() {
		return "QueryResults [items=" + items + ", totalCount=" + totalCount + "]";
	}

}
