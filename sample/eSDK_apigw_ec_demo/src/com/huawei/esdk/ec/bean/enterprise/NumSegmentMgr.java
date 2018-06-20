package com.huawei.esdk.ec.bean.enterprise;

/**
 * 增加企业管理员实体参数中的企业管理员管理的号码段 
 * Increase the number segment managed by the enterprise administrator in the enterprise administrator entity parameters
 * @author wwx534262
 *
 */
public class NumSegmentMgr 
{
	private String startNum;
	
	private String endNum;
	
	private String cxType;

	public String getStartNum() 
	{
		return startNum;
	}

	public void setStartNum(String startNum) 
	{
		this.startNum = startNum;
	}

	public String getEndNum() 
	{
		return endNum;
	}

	public void setEndNum(String endNum) 
	{
		this.endNum = endNum;
	}

	public String getCxType() 
	{
		return cxType;
	}

	public void setCxType(String cxType) 
	{
		this.cxType = cxType;
	}
	
	
}
