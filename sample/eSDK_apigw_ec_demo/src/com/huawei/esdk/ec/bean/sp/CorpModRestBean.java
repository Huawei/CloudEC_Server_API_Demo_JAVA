package com.huawei.esdk.ec.bean.sp;
/**
 * 修改企业基本信息实体
 * @author wWX534262
 *
 */
public class CorpModRestBean 
{
	
	private String phone;
	
	private String email;
	
	private String fax;
	
	private String sms;
	
	private String address;
	
	private String hangUpSm;
	
	private Boolean allowedJump;
	
	private Boolean accountSmsNotify;
	
	private Boolean mdxAuthorize;
	
	private Integer isNoNeedLoginMatch;
	
	private Integer deptPushType;
	
	private String smsSign;
	
	private String ldapPwd;

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

	public String getFax() 
	{
		return fax;
	}

	public void setFax(String fax) 
	{
		this.fax = fax;
	}

	public String getSms() 
	{
		return sms;
	}

	public void setSms(String sms) 
	{
		this.sms = sms;
	}

	public String getAddress()
	{
		return address;
	}

	public void setAddress(String address)
	{
		this.address = address;
	}

	public String getHangUpSm() 
	{
		return hangUpSm;
	}

	public void setHangUpSm(String hangUpSm) 
	{
		this.hangUpSm = hangUpSm;
	}

	public Boolean getAllowedJump() 
	{
		return allowedJump;
	}

	public void setAllowedJump(Boolean allowedJump) 
	{
		this.allowedJump = allowedJump;
	}

	public Boolean getAccountSmsNotify() 
	{
		return accountSmsNotify;
	}

	public void setAccountSmsNotify(Boolean accountSmsNotify) 
	{
		this.accountSmsNotify = accountSmsNotify;
	}

	public Boolean getMdxAuthorize() 
	{
		return mdxAuthorize;
	}

	public void setMdxAuthorize(Boolean mdxAuthorize) 
	{
		this.mdxAuthorize = mdxAuthorize;
	}

	public Integer getIsNoNeedLoginMatch()
	{
		return isNoNeedLoginMatch;
	}

	public void setIsNoNeedLoginMatch(Integer isNoNeedLoginMatch) 
	{
		this.isNoNeedLoginMatch = isNoNeedLoginMatch;
	}

	public Integer getDeptPushType() 
	{
		return deptPushType;
	}

	public void setDeptPushType(Integer deptPushType) 
	{
		this.deptPushType = deptPushType;
	}

	public String getSmsSign() 
	{
		return smsSign;
	}

	public void setSmsSign(String smsSign)
	{
		this.smsSign = smsSign;
	}

	public String getLdapPwd() 
	{
		return ldapPwd;
	}

	public void setLdapPwd(String ldapPwd)
	{
		this.ldapPwd = ldapPwd;
	}
	
}
