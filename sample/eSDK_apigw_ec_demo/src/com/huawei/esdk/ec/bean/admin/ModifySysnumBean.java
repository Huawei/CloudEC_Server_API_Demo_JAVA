package com.huawei.esdk.ec.bean.admin;

public class ModifySysnumBean 
{
	private String oldStartNum;
	
	private String oldEndNum;
	
	private String newStartNum;
	
	private String newEndNum;

	public String getOldStartNum()
	{
		return oldStartNum;
	}

	public void setOldStartNum(String oldStartNum) 
	{
		this.oldStartNum = oldStartNum;
	}

	public String getOldEndNum() 
	{
		return oldEndNum;
	}

	public void setOldEndNum(String oldEndNum)
	{
		this.oldEndNum = oldEndNum;
	}

	public String getNewStartNum() 
	{
		return newStartNum;
	}

	public void setNewStartNum(String newStartNum)
	{
		this.newStartNum = newStartNum;
	}

	public String getNewEndNum() 
	{
		return newEndNum;
	}

	public void setNewEndNum(String newEndNum)
	{
		this.newEndNum = newEndNum;
	}
	
}
