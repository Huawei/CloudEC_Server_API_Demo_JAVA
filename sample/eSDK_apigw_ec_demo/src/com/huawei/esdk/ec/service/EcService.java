package com.huawei.esdk.ec.service;

import java.awt.Color;
import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.huawei.esdk.ec.bean.common.QueryResponse;
import com.huawei.esdk.ec.bean.common.RestRequest;
import com.huawei.esdk.ec.bean.common.RestResponse;
import com.huawei.esdk.ec.bean.common.Token;
import com.huawei.esdk.ec.rest.EcRest;
import com.huawei.esdk.ec.utils.JsonUtils;
import com.huawei.esdk.ec.utils.MeetingTokenUtils;
import com.huawei.esdk.ec.utils.Properties_language_Utils;
import com.huawei.esdk.ec.utils.ResultUtils;
import com.huawei.esdk.ec.utils.StringUtils;

public class EcService 
{
	private static final Logger LOGGER = LogManager.getLogger(EcService.class);
	
	//返回码200表示ok
	//Return code 200 indicates ok
	private static final int STATUSCODE_OK = 200;
	
	//0   表示操作成功
	//0 indicates successful operation
	private static final int RETURNCODE_OK = 0;
	
	private static int timerCount = 0;
			
	static EcRest ecRest = new EcRest();

	private static String beanToJson;
	
	//post请求
	public static void post(String resourceUri, RestRequest request, JLabel errInfoLabel,Token token) 
	{
		RestResponse response = ecRest.sendMessage(token, resourceUri, request);
		//记录响应消息
		//Record response message
		ResultUtils.setResponse(analysisResult(response, errInfoLabel));
		//记录请求消息
		//Record request message
		ResultUtils.setRequest(analysisRequest(resourceUri, request));
		
	}
	
	//delete请求
	public static void delete(String resourceUri, RestRequest request, JLabel errInfoLabel,Token token) 
	{
		RestResponse response = ecRest.sendMessage(token, resourceUri, request);
		ResultUtils.setResponse(analysisResult(response, errInfoLabel));
		ResultUtils.setRequest(analysisRequest(resourceUri, request));
		
	}
	
	//put请求
	public static void put(String resourceUri, RestRequest request, JLabel errInfoLabel,Token token) 
	{
		RestResponse response = ecRest.sendMessage(token, resourceUri, request);
		ResultUtils.setResponse(analysisResult(response, errInfoLabel));
		ResultUtils.setRequest(analysisRequest(resourceUri, request));
		
	}
	
	//get请求
	public static void get(String resourceUri, RestRequest request, JLabel errInfoLabel,Token token) 
	{
		RestResponse response = ecRest.sendMessage(token, resourceUri, request);
		ResultUtils.setResponse(analysisResult(response, errInfoLabel));
		ResultUtils.setRequest(analysisRequest(resourceUri, request));
		
	}
	
	/**
	 * Analyze response message
	 * @param response
	 * @param errInfoLabel
	 * @return
	 */
	private static String analysisResult(RestResponse response, JLabel errInfoLabel) 
	{
		String result = "";
		if (STATUSCODE_OK != response.getHttpCode()) 
		{
			showErrInfoWithColor(Properties_language_Utils.getValue("resultTip"), errInfoLabel);
			
			result = "HTTP/1.1 " + response.getHttpCode() + "\n" + StringUtils.formatHttpHeads(response);
			
		}
		else
		{
			String entity = response.getEntity();
			//把json转换为javaBean
			String normalizeEntity = Normalizer.normalize(entity, Form.NFKC);
			boolean goodJson = JsonUtils.isGoodJson(normalizeEntity);
			if(goodJson == false) 
			{
				LOGGER.info("this is not a goodJson");
			}
			try 
			{
				QueryResponse<?> responseBean = new ObjectMapper().readValue(normalizeEntity, QueryResponse.class);
				
				//处理data，获取会议token的,其他接口返回不包含token，只有会控鉴权有返回token值
				//Data, to obtain the meeting token, other interfaces return does not contain tokens, 
				//only the control authentication has returned the token value
				if(null == responseBean) 
				{
					return null;
				}
				else 
				{
					Object data = responseBean.getData();
					beanToJson = StringUtils.beanToJson(data);
					if (beanToJson.startsWith("{"))
					{
						HashMap<String, Object> jsonToMap = StringUtils.jsonToMap(beanToJson);
						if(null != jsonToMap) 
						{
							if (jsonToMap.containsKey("token")) 
							{
								String meetingToken = "Basic " + (String)jsonToMap.get("token");
								//将token存储起来
								//Store the token
								MeetingTokenUtils.setMeetingToken(meetingToken);
							}
						}
					}
					
					if (RETURNCODE_OK == responseBean.getReturnCode()) 
					{
						showErrInfoWithColor(Properties_language_Utils.getValue("resultTip2"), errInfoLabel);
					}
					else
					{
						showErrInfoWithColor(Properties_language_Utils.getValue("resultTip"), errInfoLabel);
					}
					
					result = "HTTP/1.1 " + response.getHttpCode() + "\n" +
							StringUtils.formatHttpHeads(response) + "\n" + 
							StringUtils.formatJson(entity);
				}
			} 
			catch (RuntimeException e) 
			{
				String resultCode = entity.split("<resultCode>")[1].split("</resultCode>")[0];
				if ("0".equals(resultCode)) 
				{
					showErrInfoWithColor(Properties_language_Utils.getValue("resultTip2"), errInfoLabel);
				}else 
				{
					showErrInfoWithColor(Properties_language_Utils.getValue("resultTip"), errInfoLabel);
				}
				
				result = "HTTP/1.1 " + response.getHttpCode() + "\n" +
						StringUtils.formatHttpHeads(response) + "\n" + entity;
			}
			catch (Exception e) 
			{
				String resultCode = entity.split("<resultCode>")[1].split("</resultCode>")[0];
				if ("0".equals(resultCode)) 
				{
					showErrInfoWithColor(Properties_language_Utils.getValue("resultTip2"), errInfoLabel);
				}else 
				{
					showErrInfoWithColor(Properties_language_Utils.getValue("resultTip"), errInfoLabel);
				}
				
				result = "HTTP/1.1 " + response.getHttpCode() + "\n" +
						StringUtils.formatHttpHeads(response) + "\n" + entity;
			}
			
		}
		return result;
	}
	
	/**
	 * Analyze request message
	 * @param resourceUri
	 * @param request
	 * @return
	 */
	private static String analysisRequest(String resourceUri, RestRequest request) 
	{
		String params = request.getParameters().toString();
		String newParams = params.substring(1, params.length() - 1).replace(", ", "&");
		
		if (StringUtils.isEmpty(newParams) ) 
		{
			return request.getHttpMethod() + " " + resourceUri;
		}else 
		{
			return request.getHttpMethod() + " " + resourceUri + "?" + newParams;
		}
	}

	public static void begin()
	{
		timerCount = 0;
	} 
    
	public static void finish()
	{
		timerCount = -1;
	}

	public static void loading(JLabel errInfoLabel)
	{
		new Timer().schedule(new TimerTask()
		{
    
			@Override
			public void run()
			{
				if (timerCount < 0)
				{
					return;
				}
        
				if (timerCount < 5)
				{
					timerCount++;
				}
				else
				{
					timerCount = 0;
				}
        
				 StringBuffer tempBuffer = new StringBuffer();
                for (int i = 0; i < timerCount; i++)
                {
                	tempBuffer.append(". ");
                }
                String temp = tempBuffer.toString();
                temp = Properties_language_Utils.getValue("request") + temp;
				showErrInfoWithColor(temp, errInfoLabel);
        
				loading(errInfoLabel);
			}
		}, 250);
	}
	
	private static void showErrInfoWithColor(String errInfo, JLabel errInfoLabel)
	{
		if (errInfo == null)
		{
			return;
		}
        
		errInfoLabel.setForeground(Color.red);
		errInfoLabel.setText(errInfo);
	}
}
