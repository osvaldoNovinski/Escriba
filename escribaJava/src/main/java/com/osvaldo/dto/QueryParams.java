package com.osvaldo.dto;

import org.springframework.data.jpa.domain.JpaSort;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Sort;

@JsonIgnoreProperties(ignoreUnknown = true)
public class QueryParams {
	private String filter = "";
	private String sortOrder = Sort.Direction.ASC.name();
	private String sortField = "";
	private Integer pageNumber = 0;
	private Integer pageSize = 10;
	private List<String> statusFilter = new ArrayList<>();
	private List<List<String>> multiParamFilter;
	private LocalDate beginDate;
	private LocalDate endDate;
	private Boolean booleanFilter;

	public String getFilter() {
		return filter;
	}

	public void setFilter(String filter) {
		this.filter = filter;
	}

	public String getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}

	public String getSortField() {
		return sortField;
	}

	public void setSortField(String sortField) {
		this.sortField = sortField;
	}

	public Integer getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public List<String> getStatusFilter() {
		return statusFilter;
	}

	public void setStatusFilter(List<String> statusFilter) {
		this.statusFilter = statusFilter;
	}

	public List<List<String>> getMultiParamFilter() {
		return multiParamFilter;
	}

	public void setMultiParamFilter(List<List<String>> multiParamFilter) {
		this.multiParamFilter = multiParamFilter;
	}

	public LocalDate getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(LocalDate beginDate) {
		this.beginDate = beginDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public Boolean getBooleanFilter() {
		return booleanFilter;
	}

	public void setBooleanFilter(Boolean booleanFilter) {
		this.booleanFilter = booleanFilter;
	}

	public Sort sortStringNumber(String order, String column) {

		return JpaSort.unsafe(Sort.Direction.valueOf(order.toUpperCase()), "TRY_CAST(" + column + " AS int)");

	}

	@Override
	public String toString() {
		return "QueryParams [filter=" + filter + ", sortOrder=" + sortOrder + ", sortField=" + sortField
				+ ", pageNumber=" + pageNumber + ", pageSize=" + pageSize + ", statusFilter=" + statusFilter
				+ ", multiParamFilter=" + multiParamFilter + ", beginDate=" + beginDate + ", endDate=" + endDate
				+ ", booleanFilter=" + booleanFilter + "]";
	}

}
