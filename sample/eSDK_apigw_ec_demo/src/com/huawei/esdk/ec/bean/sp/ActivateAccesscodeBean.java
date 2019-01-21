package com.huawei.esdk.ec.bean.sp;
/**
 * 激活/去激活接入号参数实体
 * @author wWX534262
 *
 */
public class ActivateAccesscodeBean
{
	//待激活的的接入号
	private String confCodeID;
	
	//接入号所属企业ID
	private String orgID;

	public String getConfCodeID() 
	{
		return confCodeID;
	}

	public void setConfCodeID(String confCodeID) 
	{
		this.confCodeID = confCodeID;
	}

	public String getOrgID() 
	{
		return orgID;
	}

	public void setOrgID(String orgID) 
	{
		this.orgID = orgID;
	}
	
	
}
