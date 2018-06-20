package com.huawei.esdk.ec.bean.sp;
/**
 *  待分配号段信息
 *  To be allocated number segment information
 * @author wwx534262
 *
 */
public class NumIndexBean 
{
	private String startNum;
	
	private String endNum;
	
	private Integer isConfAccessNumber;

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

	public Integer getIsConfAccessNumber() 
	{
		return isConfAccessNumber;
	}

	public void setIsConfAccessNumber(Integer isConfAccessNumber)
	{
		this.isConfAccessNumber = isConfAccessNumber;
	}
	
}
