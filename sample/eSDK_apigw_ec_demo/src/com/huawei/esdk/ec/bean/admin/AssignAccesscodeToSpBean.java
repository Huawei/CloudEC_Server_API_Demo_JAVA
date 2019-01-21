package com.huawei.esdk.ec.bean.admin;

import java.util.List;

/**
 * 给SP分配接入号的参数实体
 * @author wWX534262
 *
 */
public class AssignAccesscodeToSpBean 
{
	//待分配的接入号列表
	private List<String> confcodeList;
	
	//指定分配的组织ID，这里为SPID
	private String orgId;

	public List<String> getConfcodeList() 
	{
		return confcodeList;
	}

	public void setConfcodeList(List<String> confcodeList)
	{
		this.confcodeList = confcodeList;
	}

	public String getOrgId() 
	{
		return orgId;
	}

	public void setOrgId(String orgId) 
	{
		this.orgId = orgId;
	}
}
