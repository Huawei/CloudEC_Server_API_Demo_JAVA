package com.huawei.esdk.ec.bean.sp;

import java.io.Serializable;

/**
 * 增加企业接口/Increase enterprise interface
 * 用户包信息实体类/User package information entity class
 * @author wWX534262
 *
 */
public class UserPack implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1603724207096310605L;

	private String packName;
	
	private Integer packCount;

	public String getPackName() 
	{
		return packName;
	}

	public void setPackName(String packName) 
	{
		this.packName = packName;
	}

	public Integer getPackCount() 
	{
		return packCount;
	}

	public void setPackCount(Integer packCount) 
	{
		this.packCount = packCount;
	}
	
	
}
