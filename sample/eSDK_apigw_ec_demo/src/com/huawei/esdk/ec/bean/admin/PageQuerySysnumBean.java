package com.huawei.esdk.ec.bean.admin;

public class PageQuerySysnumBean 
{
	private String searchKey;
	
	private Integer assignStatus;
	
	private Integer cxType;
	
	private Integer pageIndex;
	
	private Integer pageSize;

	public String getSearchKey() 
	{
		return searchKey;
	}

	public void setSearchKey(String searchKey)
	{
		this.searchKey = searchKey;
	}

	public Integer getAssignStatus() 
	{
		return assignStatus;
	}

	public void setAssignStatus(Integer assignStatus)
	{
		this.assignStatus = assignStatus;
	}

	public Integer getCxType()
	{
		return cxType;
	}

	public void setCxType(Integer cxType) 
	{
		this.cxType = cxType;
	}

	public Integer getPageIndex()
	{
		return pageIndex;
	}

	public void setPageIndex(Integer pageIndex) 
	{
		this.pageIndex = pageIndex;
	}

	public Integer getPageSize() 
	{
		return pageSize;
	}

	public void setPageSize(Integer pageSize) 
	{
		this.pageSize = pageSize;
	}
	
}
