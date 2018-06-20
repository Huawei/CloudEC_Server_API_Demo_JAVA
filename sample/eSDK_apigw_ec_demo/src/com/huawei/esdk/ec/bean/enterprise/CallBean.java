package com.huawei.esdk.ec.bean.enterprise;
/**
 * CTD呼叫参数实体
 * CTD call parameter entity
 * @author wwx534262
 *
 */
public class CallBean
{
	private String caller;
	
	private String callee;

	public String getCaller() 
	{
		return caller;
	}

	public void setCaller(String caller) 
	{
		this.caller = caller;
	}

	public String getCallee() 
	{
		return callee;
	}

	public void setCallee(String callee) 
	{
		this.callee = callee;
	}
	
	
}
