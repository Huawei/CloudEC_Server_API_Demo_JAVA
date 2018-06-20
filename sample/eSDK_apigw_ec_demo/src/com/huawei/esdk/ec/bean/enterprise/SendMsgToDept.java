package com.huawei.esdk.ec.bean.enterprise;
/**
 * 发送信息到部门的参数体
 * sending information to department parameter body
 * @author wwx534262
 *
 */
public class SendMsgToDept
{
	private String sendNumber;
	
	private String deptId;
	
	private String message;
	
	private String subject;
	
	private String deptGrade;
	
	private String priorityLevel;
	
	public String getSendNumber()
	{
		return sendNumber;
	}
	
	public void setSendNumber(String sendNumber)
	{
		this.sendNumber = sendNumber;
	}
	
	public String getDeptId()
	{
		return deptId;
	}
	
	public void setDeptId(String deptId)
	{
		this.deptId = deptId;
	}
	
	public String getMessage()
	{
		return message;
	}
	
	public void setMessage(String message)
	{
		this.message = message;
	}
	
	public String getSubject()
	{
		return subject;
	}
	
	public void setSubject(String subject)
	{
		this.subject = subject;
	}
	
	public String getDeptGrade()
	{
		return deptGrade;
	}
	
	public void setDeptGrad(String deptGrade)
	{
		this.deptGrade = deptGrade;
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





















