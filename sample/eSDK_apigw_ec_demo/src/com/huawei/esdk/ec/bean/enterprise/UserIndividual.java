package com.huawei.esdk.ec.bean.enterprise;
/**
 * 增加企业管理员参数实体中的企业成员个人信息实体类
 * Increase the enterprise member personal information entity class in the enterprise administrator parameter entity
 * @author wwx534262
 *
 */
public class UserIndividual 
{
	private String name;
	
	private String gender;
	
	private String deptCode;
	
	private String deptName;
	
	private String title;

	public String getName() 
	{
		return name;
	}

	public void setName(String name) 
	{
		this.name = name;
	}

	public String getGender() 
	{
		return gender;
	}

	public void setGender(String gender) 
	{
		this.gender = gender;
	}

	public String getDeptCode()
	{
		return deptCode;
	}

	public void setDeptCode(String deptCode) 
	{
		this.deptCode = deptCode;
	}

	public String getDeptName() 
	{
		return deptName;
	}

	public void setDeptName(String deptName)
	{
		this.deptName = deptName;
	}

	public String getTitle() 
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}
	
}
