package com.huawei.esdk.ec.bean.enterprise;
/**
 * 重置密码的请求参数
 * Reset password request parameters
 * @author wwx534262
 *
 */
public class ResetPassword 
{
	private String userAccount;
	
	private String newPwd;

	public String getUserAccount() 
	{
		return userAccount;
	}

	public void setUserAccount(String userAccount) 
	{
		this.userAccount = userAccount;
	}

	public String getNewPwd() 
	{
		return newPwd;
	}

	public void setNewPwd(String newPwd) 
	{
		this.newPwd = newPwd;
	}
	
	

}
