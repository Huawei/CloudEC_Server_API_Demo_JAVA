package com.huawei.esdk.ec.bean.admin;
/**
 * 待分配号码段信息
 * @author wWX534262
 *
 */
public class NumIndexIssueSpBean
{
	private String startNum;
	
	private String endNum;
	
	private Integer cxType;
	
	//是否总机类型 0:否；1:是,默认为0
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

	public Integer getAttendantFlag() 
	{
		return attendantFlag;
	}

	public void setAttendantFlag(Integer attendantFlag)
	{
		this.attendantFlag = attendantFlag;
	}

	
}
