package com.huawei.esdk.ec.bean.admin;

import java.util.List;

public class SysnumToSpBean 
{
	private List<NumIndexIssueSpBean> numAssignList;
	
	private String spId;

	public List<NumIndexIssueSpBean> getNumAssignList() 
	{
		return numAssignList;
	}

	public void setNumAssignList(List<NumIndexIssueSpBean> numAssignList)
	{
		this.numAssignList = numAssignList;
	}

	public String getSpId() 
	{
		return spId;
	}

	public void setSpId(String spId) 
	{
		this.spId = spId;
	}
	
}
