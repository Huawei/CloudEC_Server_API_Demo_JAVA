package com.huawei.esdk.ec.bean.enterprise;
/**
 * 增加企业成员MemberInfo参数实体类
 * Increase enterprise member MemberInfo parameter entity 
 * @author wWX534262
 *
 */
public class MemberInfo
{
    private Boolean ldapOpen;
    
    private String userAccount;
    
    private String realm;
    
    private String password;
    
    private String personRole;
    
    private String userType;
    
    private String adminType;
    
    private String ucServerNumber;
    
    private String shortNum;
    
    private String softNumberType;
    
    private Integer isActive;
    
    private String ucFunction;
    
    private String ucFunctionName;
    
    private String confFunction;
    
    private Employee employee;
    
    private ConfUserInfo confUserInfo;
    
    private String alias;

	public Boolean getLdapOpen() 
	{
		return ldapOpen;
	}

	public void setLdapOpen(Boolean ldapOpen)
	{
		this.ldapOpen = ldapOpen;
	}

	public String getUserAccount()
	{
		return userAccount;
	}

	public void setUserAccount(String userAccount) 
	{
		this.userAccount = userAccount;
	}

	public String getRealm() 
	{
		return realm;
	}

	public void setRealm(String realm) 
	{
		this.realm = realm;
	}

	public String getPassword() 
	{
		return password;
	}

	public void setPassword(String password) 
	{
		this.password = password;
	}

	public String getPersonRole() 
	{
		return personRole;
	}

	public void setPersonRole(String personRole) 
	{
		this.personRole = personRole;
	}

	public String getUserType()
	{
		return userType;
	}

	public void setUserType(String userType) 
	{
		this.userType = userType;
	}

	public String getAdminType()
	{
		return adminType;
	}

	public void setAdminType(String adminType)
	{
		this.adminType = adminType;
	}

	public String getUcServerNumber() 
	{
		return ucServerNumber;
	}

	public void setUcServerNumber(String ucServerNumber)
	{
		this.ucServerNumber = ucServerNumber;
	}

	public String getShortNum()
	{
		return shortNum;
	}

	public void setShortNum(String shortNum)
	{
		this.shortNum = shortNum;
	}

	public String getSoftNumberType() 
	{
		return softNumberType;
	}

	public void setSoftNumberType(String softNumberType) 
	{
		this.softNumberType = softNumberType;
	}

	public Integer getIsActive() 
	{
		return isActive;
	}

	public void setIsActive(Integer isActive) 
	{
		this.isActive = isActive;
	}

	public String getUcFunction() 
	{
		return ucFunction;
	}

	public void setUcFunction(String ucFunction)
	{
		this.ucFunction = ucFunction;
	}

	public String getUcFunctionName()
	{
		return ucFunctionName;
	}

	public void setUcFunctionName(String ucFunctionName) 
	{
		this.ucFunctionName = ucFunctionName;
	}

	public String getConfFunction()
	{
		return confFunction;
	}

	public void setConfFunction(String confFunction) 
	{
		this.confFunction = confFunction;
	}

	public Employee getEmployee()
	{
		return employee;
	}

	public void setEmployee(Employee employee) 
	{
		this.employee = employee;
	}

	public ConfUserInfo getConfUserInfo() 
	{
		return confUserInfo;
	}

	public void setConfUserInfo(ConfUserInfo confUserInfo)
	{
		this.confUserInfo = confUserInfo;
	}

	public String getAlias() 
	{
		return alias;
	}

	public void setAlias(String alias) 
	{
		this.alias = alias;
	}
    
    
}
