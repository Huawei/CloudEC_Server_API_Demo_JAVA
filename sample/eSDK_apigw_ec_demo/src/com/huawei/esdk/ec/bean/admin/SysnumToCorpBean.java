package com.huawei.esdk.ec.bean.admin;

import java.util.List;

import com.huawei.esdk.ec.bean.sp.NumberParamBean;

/**
 * 分配系统号段给企业的参数信息
 * @author wWX534262
 *
 */
public class SysnumToCorpBean 
{
	private List<NumIndexIssueCorpBean> numAssignList;
	
	private String corpId;
	
	private NumberParamBean numParam;

	public List<NumIndexIssueCorpBean> getNumAssignList() 
	{
		return numAssignList;
	}

	public void setNumAssignList(List<NumIndexIssueCorpBean> numAssignList) 
	{
		this.numAssignList = numAssignList;
	}

	public String getCorpId() 
	{
		return corpId;
	}

	public void setCorpId(String corpId) 
	{
		this.corpId = corpId;
	}

	public NumberParamBean getNumParam() 
	{
		return numParam;
	}

	public void setNumParam(NumberParamBean numParam) 
	{
		this.numParam = numParam;
	}
	
}
