package com.huawei.esdk.ec.utils;

import com.huawei.esdk.ec.bean.common.Authentication;
import com.huawei.esdk.ec.bean.common.Token;

public class TokenUtils 
{
	static private Token token = null;
	
	public static Token update(String url, String username, String password) throws Exception
	{
		Authentication authentication = new Authentication(url, username, password);
		
		token = AuthUtils.getInstance().getToken(authentication);
		
		LoginUtils.setToken(token);
		
		return token;
	}

}
