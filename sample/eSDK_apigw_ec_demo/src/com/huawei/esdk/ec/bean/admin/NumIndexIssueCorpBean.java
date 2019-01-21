package com.huawei.esdk.ec.bean.admin;
/**
 * 待分配给企业号段信息
 * @author wWX534262
 *
 */
public class NumIndexIssueCorpBean 
{
	private String startNum;
	
	private String endNum;
	
	private Integer cxType;
	
	//是否为会议接入号0: 否；1: 是,默认为0
	private Integer isConfAccessNumber = 0;
	
	//是否总机类型0:否；1:是,默认为0
	private Integer attendantFlag = 0;

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

	public Integer getCxType() 
	{
		return cxType;
	}

	public void setCxType(Integer cxType) 
	{
		this.cxType = cxType;
	}

	public Integer getIsConfAccessNumber() 
	{
		return isConfAccessNumber;
	}

	public void setIsConfAccessNumber(Integer isConfAccessNumber)
	{
		this.isConfAccessNumber = isConfAccessNumber;
	}

	public Integer getAttendantFlag() 
	{
		return attendantFlag;
	}

	public void setAttendantFlag(Integer attendantFlag) 
	{
		this.attendantFlag = attendantFlag;
	}
	
}
