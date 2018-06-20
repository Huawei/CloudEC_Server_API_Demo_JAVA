package com.huawei.esdk.ec.bean.sp;

import java.util.List;

/**
 * 分配服务商号码给企业的参数实体类
 * Parameter entity that assigns the service provider number to the enterprise
 * @author wwx534262
 *
 */
public class AssignNumberBean 
{
	private String srcOrgId;
	
	private String corpId;
	
	private List<NumIndexBean> numAssignList;
	
	private NumberParamBean numParam;
	
	private String operateUser;

	public String getSrcOrgId() 
	{
		return srcOrgId;
	}

	public void setSrcOrgId(String srcOrgId)
	{
		this.srcOrgId = srcOrgId;
	}

	public String getCorpId()
	{
		return corpId;
	}

	public void setCorpId(String corpId) 
	{
		this.corpId = corpId;
	}

	public List<NumIndexBean> getNumAssignList() 
	{
		return numAssignList;
	}

	public void setNumAssignList(List<NumIndexBean> numAssignList) 
	{
		this.numAssignList = numAssignList;
	}

	public NumberParamBean getNumParam() 
	{
		return numParam;
	}

	public void setNumParam(NumberParamBean numParam)
	{
		this.numParam = numParam;
	}

	public String getOperateUser() 
	{
		return operateUser;
	}

	public void setOperateUser(String operateUser) 
	{
		this.operateUser = operateUser;
	}
	
	
}
