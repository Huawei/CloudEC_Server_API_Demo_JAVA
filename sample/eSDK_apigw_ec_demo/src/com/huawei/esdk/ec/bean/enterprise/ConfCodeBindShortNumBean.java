package com.huawei.esdk.ec.bean.enterprise;
/**
 * 接入号以及短号信息ConfCodeBindShortNumBean,接入号绑定短号的参数实体
 * @author wWX534262
 *
 */
public class ConfCodeBindShortNumBean 
{
	//会议接入号
	private String confCodeID;
	
	//短号
	private String shortNum;

	public String getConfCodeID() 
	{
		return confCodeID;
	}

	public void setConfCodeID(String confCodeID)
	{
		this.confCodeID = confCodeID;
	}

	public String getShortNum() 
	{
		return shortNum;
	}

	public void setShortNum(String shortNum)
	{
		this.shortNum = shortNum;
	}
	
}
