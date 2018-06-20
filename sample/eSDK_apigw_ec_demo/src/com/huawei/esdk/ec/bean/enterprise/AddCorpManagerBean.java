package com.huawei.esdk.ec.bean.enterprise;

import java.util.List;

/**
 * 增加企业管理员参数实体类
 * Increase enterprise admin parameter entity
 * @author wwx534262
 *
 */
public class AddCorpManagerBean 
{
	private String userAccount;
	
	private String userType;
	
	private String adminType;
	
	private Integer isActive;
	
	private String emailAddr;
	
	private String pwd;
	
	private String roleName;
	
	private UserIndividual userIndividual;
	
	private UserContactInfo userContactInfo;
	
	private List<NumSegmentMgr> numSegList;
	
	private List<DeptMgr> deptMgrList;
	
	private String personRoleName;
	
	private String alias;
	
	private UserCompetence userCompetence;

	public String getUserAccount() 
	{
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		
		this.userAccount = userAccount;
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

	public Integer getIsActive() 
	{
		return isActive;
	}

	public void setIsActive(Integer isActive) 
	{
		this.isActive = isActive;
	}

	public String getEmailAddr() 
	{
		return emailAddr;
	}

	public void setEmailAddr(String emailAddr) 
	{
		this.emailAddr = emailAddr;
	}

	public String getPwd() 
	{
		return pwd;
	}

	public void setPwd(String pwd) 
	{
		this.pwd = pwd;
	}

	public String getRoleName()
	{
		return roleName;
	}

	public void setRoleName(String roleName) 
	{
		this.roleName = roleName;
	}

	public UserIndividual getUserIndividual() 
	{
		return userIndividual;
	}

	public void setUserIndividual(UserIndividual userIndividual) 
	{
		this.userIndividual = userIndividual;
	}

	public UserContactInfo getUserContactInfo() 
	{
		return userContactInfo;
	}

	public void setUserContactInfo(UserContactInfo userContactInfo) 
	{
		this.userContactInfo = userContactInfo;
	}

	public List<NumSegmentMgr> getNumSegList() 
	{
		return numSegList;
	}

	public void setNumSegList(List<NumSegmentMgr> numSegList) 
	{
		this.numSegList = numSegList;
	}

	public List<DeptMgr> getDeptMgrList() 
	{
		return deptMgrList;
	}

	public void setDeptMgrList(List<DeptMgr> deptMgrList)
	{
		this.deptMgrList = deptMgrList;
	}

	public String getPersonRoleName() 
	{
		return personRoleName;
	}

	public void setPersonRoleName(String personRoleName) 
	{
		this.personRoleName = personRoleName;
	}

	public String getAlias() 
	{
		return alias;
	}

	public void setAlias(String alias) 
	{
		this.alias = alias;
	}

	public UserCompetence getUserCompetence() 
	{
		return userCompetence;
	}

	public void setUserCompetence(UserCompetence userCompetence) 
	{
		this.userCompetence = userCompetence;
	}
	
	
}
