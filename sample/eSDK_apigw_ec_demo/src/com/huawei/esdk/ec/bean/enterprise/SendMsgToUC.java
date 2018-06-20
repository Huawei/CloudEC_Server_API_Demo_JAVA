package com.huawei.esdk.ec.bean.enterprise;
/**
 * 发送消息给用户的参数体
 * sending messages to the user Parameters body
 * @author wwx534262
 *
 */
public class SendMsgToUC 
{
	//接口各个参数定义
	//Interface parameters defined
	
	//发送者EC账号
	//Sender EC account
	private String sendNumber;
	
	//接收者EC账号
	//Receiver EC account
	private String ucAccount;
	
	//消息内容
	//Message content
	private String message;
	
	//消息提交时间
	//Message submission time
	private String dateTime;
	
	//消息优先级
	//Message priority
	private String priorityLevel;
	
	public String getSendNumber()
	{
		return sendNumber;
	}
	
	public void setSendNumber(String sendNumber)
	{
		this.sendNumber = sendNumber;
	}
	
	public String getUcAccount()
	{
		return ucAccount;
	}
	
	public void setUcAccount(String ucAccount)
	{
		this.ucAccount = ucAccount;
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






