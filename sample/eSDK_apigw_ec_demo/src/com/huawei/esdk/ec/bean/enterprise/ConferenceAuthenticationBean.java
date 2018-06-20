package com.huawei.esdk.ec.bean.enterprise;
/**
 * 获取会议鉴权的实体
 * @author wWX534262
 *
 */
public class ConferenceAuthenticationBean 
{
	//昵称
	private String nickName;
	
	//会控临时密码或会议密码
	private String password;
	
	private String confURL;

	public String getNickName()
	{
		return nickName;
	}

	public void setNickName(String nickName) 
	{
		this.nickName = nickName;
	}

	public String getPassword() 
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getConfURL() 
	{
		return confURL;
	}

	public void setConfURL(String confURL) 
	{
		this.confURL = confURL;
	}
	
}
