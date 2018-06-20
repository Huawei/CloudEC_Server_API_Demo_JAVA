package com.huawei.esdk.ec.rest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.huawei.esdk.ec.bean.common.RestRequest;
import com.huawei.esdk.ec.bean.common.RestResponse;
import com.huawei.esdk.ec.bean.common.Token;
import com.huawei.esdk.ec.utils.RestUtils;
/**
 * 发送请求
 * send request
 * @author wwx534262
 *
 */
public class EcRest 
{
	private static final Logger LOGGER = LogManager.getLogger(EcRest.class); 
	
	public RestResponse sendMessage(Token token, String resourceUrl, RestRequest request) 
	{
		try 
		{
			RestUtils restUtil = RestUtils.getInstance();
			RestResponse response = restUtil.sendMessage(token, resourceUrl, request);
			return response;
		} 
		catch (Exception e) 
		{
			LOGGER.error("sendMessage error:" + e);
			return null;
		}
		
	}
}
