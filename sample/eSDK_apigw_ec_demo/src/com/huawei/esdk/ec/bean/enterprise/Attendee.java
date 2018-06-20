package com.huawei.esdk.ec.bean.enterprise;
/**
 * 与会者列表参数实体
 * attendee list parameter entity
 * @author wWX534262
 *
 */

import java.io.Serializable;

public class Attendee implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7697263687672976258L;

	private String accountId;
	
	private String name;
	
	private String role;
	
	private String phone;
	
	private String email;
	
	private String sms;
	
	private Integer isMute;
	
	private Integer isAutoInvite = 1;
	
	private AddressType type;

	public String getAccountId() 
	{
		return accountId;
	}

	public void setAccountId(String accountId) 
	{
		this.accountId = accountId;
	}

	public String getName() 
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getPhone() 
	{
		return phone;
	}

	public void setPhone(String phone)
	{
		this.phone = phone;
	}

	public String getEmail() 
	{
		return email;
	}

	public void setEmail(String email) 
	{
		this.email = email;
	}

	public String getSms() 
	{
		return sms;
	}

	public void setSms(String sms) 
	{
		this.sms = sms;
	}

	public Integer getIsMute() 
	{
		return isMute;
	}

	public void setIsMute(Integer isMute) 
	{
		this.isMute = isMute;
	}

	public Integer getIsAutoInvite() 
	{
		return isAutoInvite;
	}

	public void setIsAutoInvite(Integer isAutoInvite) 
	{
		this.isAutoInvite = isAutoInvite;
	}

	public AddressType getType() 
	{
		return type;
	}

	public void setType(AddressType type) 
	{
		this.type = type;
	}
	
	
}
