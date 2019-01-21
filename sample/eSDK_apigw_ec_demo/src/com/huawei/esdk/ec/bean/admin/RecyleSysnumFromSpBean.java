package com.huawei.esdk.ec.bean.admin;
/**
 * 从服务提供商回收系统号段参数
 * @author wWX534262
 *
 */
public class RecyleSysnumFromSpBean 
{
	private String startNum;
	
	private String endNum;
	
	private String spId;

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

	public String getSpId() 
	{
		return spId;
	}

	public void setSpId(String spId) 
	{
		this.spId = spId;
	}
	
}
