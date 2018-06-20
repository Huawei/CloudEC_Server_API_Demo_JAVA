package com.huawei.esdk.ec.bean.enterprise;
/**
 * 向群组发送消息的参数体
 * sending messages to the group Parameters body
 * @author wwx534262
 *
 */
public class SendMsgToGroup 
{
	private String sendNumber;
	
	private String groupId;
	
	private String message;
	
	private String dateTime;
	
	private String priorityLevel;
	
	public String getSendNumber()
	{
		return sendNumber;
	}
	
	public void setSendNumber(String sendNumber)
	{
		this.sendNumber = sendNumber;
	}
	
	public String getGroupId()
	{
		return groupId;
	}
	
	public void setGroupId(String groupId)
	{
		this.groupId = groupId;
	}
	
	public String getMessage()
	{
		return message;
	}
	
	public void setMessage(String message)
	{
		this.message = message;
	}
	
	public String getDateTime()
	{
		return dateTime;
	}
	
	public void setDateTime(String dateTime)
	{
		this.dateTime = dateTime;
	}

	
	public String getPriorityLevel()
	{
		return priorityLevel;
	}
	
	public void setPriorityLevel(String priorityLevel)
	{
		this.priorityLevel = priorityLevel;
	}
}
