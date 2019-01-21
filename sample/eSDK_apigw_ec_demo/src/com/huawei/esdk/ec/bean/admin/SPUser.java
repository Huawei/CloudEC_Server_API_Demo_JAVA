package com.huawei.esdk.ec.bean.admin;
/**
 * 服务提供商管理员信息
 * @author wWX534262
 *
 */
public class SPUser 
{
	//用户账号
	private String account;
	
	//用户姓名
	private String name;
	
	//用户登录密码
	private String pwd;
	
	//管理员类型，默认为1：普通管理员
	private Integer adminType = 1;
	
	//角色编码
	private String rolename;
	
	//移动电话
	private String mobile;
	
	//电子邮件
	private String email;
	
	//是否只读权限
	private Integer readOnlyAdminType;

	public String getAccount() 
	{
		return account;
	}

	public void setAccount(String account) 
	{
		this.account = account;
	}

	public String getName() 
	{
		return name;
	}

	public void setName(String name) 
	{
		this.name = name;
	}

	public String getPwd()
	{
		return pwd;
	}

	public void setPwd(String pwd) 
	{
		this.pwd = pwd;
	}

	public Integer getAdminType()
	{
		return adminType;
	}

	public void setAdminType(Integer adminType) 
	{
		this.adminType = adminType;
	}

	public String getRolename() 
	{
		return rolename;
	}

	public void setRolename(String rolename) 
	{
		this.rolename = rolename;
	}

	public String getMobile() 
	{
		return mobile;
	}

	public void setMobile(String mobile)
	{
		this.mobile = mobile;
	}

	public String getEmail() 
	{
		return email;
	}

	public void setEmail(String email) 
	{
		this.email = email;
	}

	public Integer getReadOnlyAdminType() 
	{
		return readOnlyAdminType;
	}

	public void setReadOnlyAdminType(Integer readOnlyAdminType)
	{
		this.readOnlyAdminType = readOnlyAdminType;
	}
	
	
}
