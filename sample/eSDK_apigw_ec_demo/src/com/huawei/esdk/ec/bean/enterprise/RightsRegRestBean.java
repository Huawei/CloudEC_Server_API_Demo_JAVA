package com.huawei.esdk.ec.bean.enterprise;

import java.util.List;
import java.util.Map;

/**
 * 修改号码呼叫权限参数
 * @author wWX534262
 *
 */
public class RightsRegRestBean 
{
	private List<String> impuList;
	
	private String servPackName;
	
	private Map<String,String> rights;
	
	private Integer isCustomizeRights;

	public List<String> getImpuList() 
	{
		return impuList;
	}

	public void setImpuList(List<String> impuList) 
	{
		this.impuList = impuList;
	}

	public String getServPackName() 
	{
		return servPackName;
	}

	public void setServPackName(String servPackName) 
	{
		this.servPackName = servPackName;
	}

	public Map<String, String> getRights() 
	{
		return rights;
	}

	public void setRights(Map<String, String> rights) 
	{
		this.rights = rights;
	}

	public Integer getIsCustomizeRights() 
	{
		return isCustomizeRights;
	}

	public void setIsCustomizeRights(Integer isCustomizeRights)
	{
		this.isCustomizeRights = isCustomizeRights;
	}
	
}
