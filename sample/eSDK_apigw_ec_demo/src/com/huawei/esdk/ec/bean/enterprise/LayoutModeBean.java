package com.huawei.esdk.ec.bean.enterprise;

import java.util.List;

/**
 * 切换会议显示策略参数实体类
 * Switch conference display policy parameter entity
 * @author wwx534262
 *
 */
public class LayoutModeBean 
{
	private String switchMode;
	
	private String imageType;
	
	private List<SubscriberInPic> subscriberInPics;

	public String getSwitchMode() 
	{
		return switchMode;
	}

	public void setSwitchMode(String switchMode) 
	{
		this.switchMode = switchMode;
	}

	public String getImageType()
	{
		return imageType;
	}

	public void setImageType(String imageType) 
	{
		this.imageType = imageType;
	}

	public List<SubscriberInPic> getSubscriberInPics() 
	{
		return subscriberInPics;
	}

	public void setSubscriberInPics(List<SubscriberInPic> subscriberInPics) 
	{
		this.subscriberInPics = subscriberInPics;
	}
	
	
}
