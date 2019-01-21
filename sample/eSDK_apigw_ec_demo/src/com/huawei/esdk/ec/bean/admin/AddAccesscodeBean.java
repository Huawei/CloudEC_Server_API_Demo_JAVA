package com.huawei.esdk.ec.bean.admin;
/**
 * 创建和修改接入号参数信息
 * @author wWX534262
 *
 */
public class AddAccesscodeBean 
{
	private ConfCodeBasicInfo confcodeInfo;
	
	private NumberBean numberInfo;

	public ConfCodeBasicInfo getConfcodeInfo() 
	{
		return confcodeInfo;
	}

	public void setConfcodeInfo(ConfCodeBasicInfo confcodeInfo) 
	{
		this.confcodeInfo = confcodeInfo;
	}

	public NumberBean getNumberInfo() 
	{
		return numberInfo;
	}

	public void setNumberInfo(NumberBean numberInfo) 
	{
		this.numberInfo = numberInfo;
	}
	
}
