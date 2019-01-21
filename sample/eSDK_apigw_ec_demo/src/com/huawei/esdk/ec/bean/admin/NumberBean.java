package com.huawei.esdk.ec.bean.admin;
/**
 * 接入号号码相关基础信息
 * @author wWX534262
 *
 */

import java.util.Map;

import com.huawei.esdk.ec.bean.enterprise.MMTelExtensionBean;
import com.huawei.esdk.ec.bean.enterprise.NumberCallOutRightsVo;

public class NumberBean 
{
	private String cxType;
	
	private Integer userPriority;
	
	private MMTelExtensionBean mmTelExtension;
	
	private Map<String,String> callRights;
	
	private NumberCallOutRightsVo callOutRights;

	public String getCxType()
	{
		return cxType;
	}

	public void setCxType(String cxType) 
	{
		this.cxType = cxType;
	}

	public Integer getUserPriority()
	{
		return userPriority;
	}

	public void setUserPriority(Integer userPriority) 
	{
		this.userPriority = userPriority;
	}


	public MMTelExtensionBean getMmTelExtension()
	{
		return mmTelExtension;
	}

	public void setMmTelExtension(MMTelExtensionBean mmTelExtension) 
	{
		this.mmTelExtension = mmTelExtension;
	}

	public Map<String, String> getCallRights()
	{
		return callRights;
	}

	public void setCallRights(Map<String, String> callRights) 
	{
		this.callRights = callRights;
	}

	public NumberCallOutRightsVo getCallOutRights() 
	{
		return callOutRights;
	}

	public void setCallOutRights(NumberCallOutRightsVo callOutRights) 
	{
		this.callOutRights = callOutRights;
	}
	
}
