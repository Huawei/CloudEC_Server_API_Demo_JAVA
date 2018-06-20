package com.huawei.esdk.ec.utils;

public class ResultUtils 
{
	private static String response = "";
	
	private static String req = "";

	public static String getResponse() 
	{
		return response;
	}

	public static void setResponse(String response) 
	{
		ResultUtils.response = response;
	}

	public static String getReq() 
	{
		return req;
	}

	public static void setRequest(String req) 
	{
		ResultUtils.req = req;
	}
	
	
}
