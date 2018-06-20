package com.huawei.esdk.ec.utils;
/**
 * 用于存储会议鉴权的token，基本会控制中需要这个token
 * The token used to store the conference authentication. This token is required for basic control.
 * @author wWX534262
 *
 */
public class MeetingTokenUtils 
{
	private static String meetingToken;

	public static String getMeetingToken() 
	{
		return meetingToken;
	}

	public static void setMeetingToken(String meetingToken) 
	{
		MeetingTokenUtils.meetingToken = meetingToken;
	}
	
	
}
