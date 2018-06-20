package com.huawei.esdk.ec.utils;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
/**
 * 检验字符串是否为json字符串
 * Check the string is a json string
 */
public class JsonUtils 
{
private static final Logger LOG = LogManager.getLogger(JsonUtils.class);
	
	public static boolean isBadJson(String json) 
	{
		return !isGoodJson(json);
	}
	
	/**
	 * 
	 * @param json
	 * @return true 是json格式/false 不是正确的json格式
	 *         true is json String/false is not json String
	 */
	public static boolean isGoodJson(String json) 
	{
		if(StringUtils.isEmpty(json)) 
		{
			return false;
		}
		try 
		{
			ObjectMapper mapper = new ObjectMapper();
			mapper.readTree(json);
			
			return true;
		} catch (IOException e) 
		{
			LOG.error("this is a not good jsonString");
			return false;
		}
	}
}
