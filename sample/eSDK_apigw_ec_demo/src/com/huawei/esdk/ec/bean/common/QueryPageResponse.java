package com.huawei.esdk.ec.bean.common;

import java.util.List;

/**
 * 分页查询返回模板
 * template of pagination query 
 * @author t81023743
 *
 * @param <T>
 */
public class QueryPageResponse<E> 
{
	private int pageIndex;

	private int pageSize;

	private int totalCount;

	private List<E> data;

	public int getPageIndex() 
	{
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) 
	{
		this.pageIndex = pageIndex;
	}

	public int getPageSize() 
	{
		return pageSize;
	}

	public void setPageSize(int pageSize) 
	{
		this.pageSize = pageSize;
	}

	public int getTotalCount() 
	{
		return totalCount;
	}

	public void setTotalCount(int totalCount) 
	{
		this.totalCount = totalCount;
	}

	public List<E> getData() 
	{
		return data;
	}

	public void setData(List<E> data) 
	{
		this.data = data;
	}

}
