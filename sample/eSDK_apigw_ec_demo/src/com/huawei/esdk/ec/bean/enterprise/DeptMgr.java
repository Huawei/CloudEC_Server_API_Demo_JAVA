package com.huawei.esdk.ec.bean.enterprise;

import java.io.Serializable;

/**
 * 增加企业管理员参数实体中企业管理员管理的部门 
 * Increase the enterprise administrator management department in the enterprise administrator parameter entity
 * @author wwx534262
 *
 */
public class DeptMgr implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3636187329709989218L;

	private String deptName;
	
	private String deptCode;

	public String getDeptName() 
	{
		return deptName;
	}

	public void setDeptName(String deptName) 
	{
		this.deptName = deptName;
	}

	public String getDeptCode() 
	{
		return deptCode;
	}

	public void setDeptCode(String deptCode) 
	{
		this.deptCode = deptCode;
	}
	
	
}
