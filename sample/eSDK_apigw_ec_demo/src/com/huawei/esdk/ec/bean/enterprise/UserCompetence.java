package com.huawei.esdk.ec.bean.enterprise;

/**
 * 增加企业管理员实体参数中企业账号权限信息
 * Increase enterprise account authority information in enterprise administrator entity parameters
 * @author wwx534262
 *
 */
public class UserCompetence 
{
	private String accessLevel;
	
	private String accessLevelName;
	
	private String confidential;
	
	private String isUC;
	
	private String ucFunctionName;
	
	private String isMeeting;
	
	private String customOrder;

	public String getAccessLevel() 
	{
		return accessLevel;
	}

	public void setAccessLevel(String accessLevel) 
	{
		this.accessLevel = accessLevel;
	}

	public String getAccessLevelName() 
	{
		return accessLevelName;
	}

	public void setAccessLevelName(String accessLevelName) 
	{
		this.accessLevelName = accessLevelName;
	}

	public String getConfidential() 
	{
		return confidential;
	}

	public void setConfidential(String confidential) 
	{
		this.confidential = confidential;
	}

	public String getIsUC() 
	{
		return isUC;
	}

	public void setIsUC(String isUC) 
	{
		this.isUC = isUC;
	}

	public String getUcFunctionName() 
	{
		return ucFunctionName;
	}

	public void setUcFunctionName(String ucFunctionName) 
	{
		this.ucFunctionName = ucFunctionName;
	}

	public String getIsMeeting() 
	{
		return isMeeting;
	}

	public void setIsMeeting(String isMeeting) 
	{
		this.isMeeting = isMeeting;
	}

	public String getCustomOrder() 
	{
		return customOrder;
	}

	public void setCustomOrder(String customOrder) 
	{
		this.customOrder = customOrder;
	}
	
	
}
