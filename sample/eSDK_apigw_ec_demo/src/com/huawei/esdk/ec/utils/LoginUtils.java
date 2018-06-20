package com.huawei.esdk.ec.utils;


import com.huawei.esdk.ec.bean.common.Token;


public class LoginUtils {

	// 登录用户名
	// Login Username
	private static String name;

	// 登录密码
	// Login password
	private static String pwd;

	// API网关IP
	// API Gateway IP
	private static String ip;

	// 获取的Token
	// get Token
	private static Token token;


	public static Token getToken() 
	{
		return token;
	}

	public static void setToken(Token token) 
	{
		LoginUtils.token = token;
	}

	public static void setName(String name) 
	{
		LoginUtils.name = name;
	}

	public static void setPwd(String pwd) 
	{
		LoginUtils.pwd = pwd;
	}

	public static void setIp(String ip) 
	{
		LoginUtils.ip = ip;
	}

	public static String getIp() 
	{
		return ip;
	}

	public static String getName() {
		return name;
	}

	public static String getPwd() {
		return pwd;
	}
}
