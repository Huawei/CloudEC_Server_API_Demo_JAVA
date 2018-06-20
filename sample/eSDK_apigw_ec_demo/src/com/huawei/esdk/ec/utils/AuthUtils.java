package com.huawei.esdk.ec.utils;
/**
 * Copyright 2015 Huawei Technologies Co., Ltd. All rights reserved.
 * eSDK is licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *   
 * http://www.apache.org/licenses/LICENSE-2.0
 *   
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpHost;
import org.apache.http.client.ClientProtocolException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.huawei.esdk.ec.bean.common.Authentication;
import com.huawei.esdk.ec.bean.common.RestRequest;
import com.huawei.esdk.ec.bean.common.RestResponse;
import com.huawei.esdk.ec.bean.common.Token;


/**
 * 登录鉴权实现类，通过loginAuthentication方法，传入用户账号，进行登录鉴权获取Token
 * Login authentication implementation class, through the loginAuthentication method,
 * incoming user account, login authentication to obtain the token
 * @author t81023743
 *
 */
public class AuthUtils {

	private static final Logger LOGGER = LogManager.getLogger(AuthUtils.class);

	private static final AuthUtils AUTH_UTIL = new AuthUtils();

	private static int DEFAULT_PORT = 8443;

	private AuthUtils() 
	{

	}

	public synchronized static AuthUtils getInstance() {
		return AUTH_UTIL;
	}

	/**
	 * This method is mainly to obtain tokens. There are two requests here. 
	 * First, the authentication information is queried and then the authentication is performed.
	 * @param authentication
	 * @return Token
	 * @throws IllegalArgumentException
	 * @throws ClientProtocolException
	 * @throws URISyntaxException
	 * @throws IOException
	 */
	public Token getToken(Authentication authentication)
			throws IllegalArgumentException, ClientProtocolException, URISyntaxException, IOException 
	{
		LOGGER.debug("uPortal getToken start...");
		if (null == authentication) {
			LOGGER.error("authentication is null!");
			throw new IllegalArgumentException("authentication is null!");
		}

		String username = authentication.getUser();
		String pwd = authentication.getPassword();
		String address = authentication.getAddress();

		String targetIP = StringUtils.getIpFromHttpAddress(address);
		if (null == targetIP) {
			LOGGER.error("Auth uPortal Failed, address illegal: " + Encode.encodeForLog(address));
			throw new IllegalArgumentException("Auth uPortal Failed, address illegal: " + Encode.encodeForLog(address));
		}

		String targetScheme = StringUtils.getSchemeFromHttpAddress(address);
		if (null == targetScheme) {
			targetScheme = "https";
		}

		int targetPort = StringUtils.getPortFromHttpAddress(address);
		if (-1 == targetPort)
		{
			targetPort = DEFAULT_PORT;
		}

		LOGGER.info("Start to auth uPortal: " + Encode.encodeForLog(targetIP));
		HttpHost target = new HttpHost(targetIP, targetPort, targetScheme);
		RestRequest restRequest_first = new RestRequest();
		restRequest_first.setHttpMethod("POST");

		Map<String, String> mapheaders = restRequest_first.getHttpHeaders();
		String authorization = "Digest username=" + username + ", algorithm=MD5";
		mapheaders.put("Authorization", authorization);

		RestResponse responses = RestUtils.getInstance().sendMessage(target, "/login", restRequest_first);
		if (null != responses)
		{
			Map<String, List<String>> respHeaders = responses.getHeaders();

			List<String> authInfoList = respHeaders.get("WWW-Authenticate");
			if (null == authInfoList || authInfoList.size() == 0) 
			{
				LOGGER.error(
						"Auth uPortal Failed: WWW-Authenticate not exist in response of first step. Check your username or password.");
				return null;
			}
			String authInfo[] = authInfoList.get(0).split("\"");
			String authname = username;
			String password = pwd;
			String realm = authInfo[5];
			String nonce = authInfo[3];
			if (401 == responses.getHttpCode()) 
			{
				String A1 = authname + ":" + realm + ":" + password;
				String A2 = "POST:/login";

				String response = MD5Utils
						.do32BitMD5(MD5Utils.do32BitMD5(A1) + ":" + nonce + ":" + MD5Utils.do32BitMD5(A2));

				String digest = "Digest username=\"" + authname + "\", realm=\"" + realm + "\", nonce=\"" + nonce
						+ "\", uri=\"/login\", response=\"" + response + "\"";

				RestRequest restRequest_second = new RestRequest();
				restRequest_second.setHttpMethod("POST");

				Map<String, String> secondmapheaders = restRequest_second.getHttpHeaders();
				secondmapheaders.put("Authorization", digest);
				secondmapheaders.put("Host", targetIP + ":" + targetPort);

				RestResponse digestResponses = RestUtils.getInstance().sendMessage(target, "/login",
						restRequest_second);
				
				if(null != digestResponses) 
				{
					String entity = digestResponses.getEntity();
					if (StringUtils.isEmpty(entity)) 
					{
						LOGGER.error("Auth uPortal: " + Encode.encodeForLog(targetIP) + " failed in second step: response entity is null");
						throw new IllegalArgumentException(
								"Auth uPortal: " + Encode.encodeForLog(targetIP) + " failed in second step: response entity is null");
					} 
					else 
					{
						if (entity.contains("<AccessToken>")) 
						{
							String tag = entity.split("<AccessToken>")[1].split("</AccessToken>")[0];
							String token = "Basic " + tag;
							String authUserName = Base64Utils.encode(username.getBytes("UTF-8"));
							String authPassword = Base64Utils.encode(pwd.getBytes("UTF-8"));
							String id = "";
							if (entity.contains("<SpId>"))
							{
								id = entity.split("<SpId>")[1].split("</SpId>")[0];
							}
							if (entity.contains("<CorpId>"))
							{
								id = entity.split("<CorpId>")[1].split("</CorpId>")[0];
							}
							
							Token tokenInfo = new Token(target, token, authUserName, authPassword, id);
							
							return tokenInfo;

						} 
						else 
						{
							
							LOGGER.error("Auth uPortal: " + Encode.encodeForLog(targetIP) + " failed:httpstatus code is:"
									+ digestResponses.getHttpCode() + ",and response entity is illegal: no tag.");
							throw new IllegalArgumentException(
									"Auth uPortal: " + Encode.encodeForLog(targetIP) + " failed:httpstatus code is:"
											+ digestResponses.getHttpCode() + ",and response entity is illegal: no tag.");
						}
					}
				}else 
				{
					return null;
				}
				
			} else 
			{
				LOGGER.error("Auth uPortal failed in first step, http status code:" + responses.getHttpCode());
				throw new IllegalArgumentException(
						"Argument error, http status code:" + responses.getHttpCode());
			}
			
		} 
		else 
		{
			LOGGER.error("Auth uPortal failed in first step, response is null.");
			throw new IllegalArgumentException(
					"Connection error, response is null.");
		}
	}
}
