package com.huawei.esdk.ec.bean.enterprise;
/**
 * 订阅会议状态参数体
 * Subscribe to the conference state parameter body
 * @author wwx534262
 *
 */
public class StateSubscriptionBean 
{
	private String callBackURL;
	
	private Integer enableConforenceStatus;
	
	private String enableSpeakerChange;

	public String getCallBackURL() 
	{
		return callBackURL;
	}

	
	public void setCallBackURL(String callBackURL) {
		this.callBackURL = callBackURL;
	}

	public Integer getEnableConforenceStatus() 
	{
		return enableConforenceStatus;
	}

	public void setEnableConforenceStatus(Integer enableConforenceStatus) 
	{
		this.enableConforenceStatus = enableConforenceStatus;
	}

	public String getEnableSpeakerChange() 
	{
		return enableSpeakerChange;
	}

	public void setEnableSpeakerChange(String enableSpeakerChange) 
	{
		this.enableSpeakerChange = enableSpeakerChange;
	}
	
	
}
