package com.huawei.esdk.ec.bean.sp;

import java.util.List;

/**
 * 给企业分配接入号/从企业回收接入号参数实体
 * @author wWX534262
 *
 */
public class AssignAccesscodeToCorpBean 
{
	//待分配的接入号列表
	private List<String> confcodeList;
	
	//指定分配的组织ID，这里企业ID
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
