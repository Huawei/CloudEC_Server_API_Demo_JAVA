package com.huawei.esdk.ec.bean.enterprise;

import java.io.Serializable;

/**
 * 会议提醒参数实体
 * Conference reminder parameter entity
 * @author wWX534262
 *
 */
public class Reminder implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6615280002079192979L;

	private String type;
	
	private Long time;

	public String getType() 
	{
		return type;
	}

	public void setType(String type) 
	{
		this.type = type;
	}

	public Long getTime() 
	{
		return time;
	}

	public void setTime(Long time)
	{
		this.time = time;
	}
	
	
}
