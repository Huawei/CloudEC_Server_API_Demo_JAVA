package com.huawei.esdk.ec.bean.common;

/**
 * 查询模板
 * Query template
 * @author t81023743
 *
 * @param <T>
 */
public class QueryResponse<T> 
{

	private int returnCode;
	
	private String returnDesc;
	
	private T data;

	public int getReturnCode() 
	{
		return returnCode;
	}

	public void setReturnCode(int returnCode) 
	{
		this.returnCode = returnCode;
	}

	public String getReturnDesc() 
	{
		return returnDesc;
	}

	public void setReturnDesc(String returnDesc) 
	{
		this.returnDesc = returnDesc;
	}

	public T getData() 
	{
		return data;
	}

	public void setData(T data) 
	{
		this.data = data;
	}
}
