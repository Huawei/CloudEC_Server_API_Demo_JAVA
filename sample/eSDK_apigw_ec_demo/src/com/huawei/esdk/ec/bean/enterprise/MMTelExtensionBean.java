package com.huawei.esdk.ec.bean.enterprise;

/**
 * 增加企业号码中的用户扩展业务数据参数实体
 * Add user extension service data parameter entity in enterprise number
 * @author wWX534262
 *
 */
public class MMTelExtensionBean 
{
	private Integer callSrcCode;
	
	private Boolean iupSubscribe;

	public Integer getCallSrcCode() 
	{
		return callSrcCode;
	}

	public void setCallSrcCode(Integer callSrcCode) 
	{
		this.callSrcCode = callSrcCode;
	}

	public Boolean getIupSubscribe() 
	{
		return iupSubscribe;
	}

	public void setIupSubscribe(Boolean iupSubscribe) 
	{
		this.iupSubscribe = iupSubscribe;
	}
	
	
}
