package com.huawei.esdk.ec.bean.enterprise;
/**
 * 增加设备接口/Add device interface
 * IPCC线路信息/IPCC line information
 * @author wWX534262
 *
 */
public class IpccLineInfo 
{
	private String number;
	
	private Integer ccLineBtnIndex;
	
	private Integer commonLineBtnIndex;

	public String getNumber() 
	{
		return number;
	}

	public void setNumber(String number) 
	{
		this.number = number;
	}

	public Integer getCcLineBtnIndex() 
	{
		return ccLineBtnIndex;
	}

	public void setCcLineBtnIndex(Integer ccLineBtnIndex) 
	{
		this.ccLineBtnIndex = ccLineBtnIndex;
	}

	public Integer getCommonLineBtnIndex()
	{
		return commonLineBtnIndex;
	}

	public void setCommonLineBtnIndex(Integer commonLineBtnIndex)
	{
		this.commonLineBtnIndex = commonLineBtnIndex;
	}
	
	
}
