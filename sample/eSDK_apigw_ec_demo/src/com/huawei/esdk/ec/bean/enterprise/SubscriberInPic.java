package com.huawei.esdk.ec.bean.enterprise;

import java.io.Serializable;
/**
 * 子画面列表参数体
 * Sub screen list parameter body
 * @author wwx534262
 *
 */
public class SubscriberInPic implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2526864833468941220L;

	private Integer index = 1;
	
	private String subscriber;

	public Integer getIndex() 
	{
		return index;
	}

	public void setIndex(Integer index)
	{
		this.index = index;
	}

	public String getSubscriber() 
	{
		return subscriber;
	}

	public void setSubscriber(String subscriber)
	{
		this.subscriber = subscriber;
	}
	
	
}
