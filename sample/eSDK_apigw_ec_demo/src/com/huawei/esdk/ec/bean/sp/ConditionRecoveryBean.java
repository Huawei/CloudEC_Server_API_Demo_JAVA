package com.huawei.esdk.ec.bean.sp;

/**
 * 服务提供商号段管理接口中从企业按条件回收服务提供商号段实体
 * Conditional service provider segment segment recovery 
 * from enterprise in service provider segment management interface
 * @author wWX534262
 *
 */
public class ConditionRecoveryBean
{

	private String startNum;
	
	private Integer numCount;
	
	private Integer cxType;
	
	private Integer confAcssNumType;
	
	private Integer assignStatus;
	
	private String searchKey;
	
	private String srcCorpId;
	
	private String localGateway;
	
	private Integer callSrcCode;

	public String getStartNum() 
	{
		return startNum;
	}

	public void setStartNum(String startNum) 
	{
		this.startNum = startNum;
	}

	public Integer getNumCount()
	{
		return numCount;
	}

	public void setNumCount(Integer numCount) 
	{
		this.numCount = numCount;
	}

	public Integer getCxType() 
	{
		return cxType;
	}

	public void setCxType(Integer cxType) 
	{
		this.cxType = cxType;
	}

	public Integer getConfAcssNumType() 
	{
		return confAcssNumType;
	}

	public void setConfAcssNumType(Integer confAcssNumType)
	{
		this.confAcssNumType = confAcssNumType;
	}

	public Integer getAssignStatus() 
	{
		return assignStatus;
	}

	public void setAssignStatus(Integer assignStatus)
	{
		this.assignStatus = assignStatus;
	}

	public String getSearchKey() 
	{
		return searchKey;
	}

	public void setSearchKey(String searchKey) 
	{
		this.searchKey = searchKey;
	}

	public String getSrcCorpId()
	{
		return srcCorpId;
	}

	public void setSrcCorpId(String srcCorpId)
	{
		this.srcCorpId = srcCorpId;
	}

	public String getLocalGateway() 
	{
		return localGateway;
	}

	public void setLocalGateway(String localGateway)
	{
		this.localGateway = localGateway;
	}

	public Integer getCallSrcCode() 
	{
		return callSrcCode;
	}

	public void setCallSrcCode(Integer callSrcCode)
	{
		this.callSrcCode = callSrcCode;
	}
	
	
}
