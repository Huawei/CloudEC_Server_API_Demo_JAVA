package com.huawei.esdk.ec.bean.common;

import org.apache.http.HttpHost;

/**
 * 登录鉴权后获取的Token信息
 * 
 * Token information obtained after login authentication
 *
 */
public class Token 
{

    /**
     * 构造函数
     * Constructor
     * @param httpHost API网关主机信息/API Gateway Host Information
     * @param token 从服务器获取的token,以Basic开头/The token obtained from the server, starting with Basic
     * @param userName 用户名，以Base64加密/Username, encrypted with Base64
     * @param password 密码，以Base64加密/Password, encrypted with Base64
     * @param id 从服务器获取的id/The id obtained from the server
     */
    public Token(HttpHost httpHost, String token, String userName, String password, String id) 
    {
        this.httpHost = httpHost;
        this.token = token;
        this.userName = userName;
        this.password = password;
        this.id = id;
    }

    /**
     * API网关主机信息
     * API Gateway Host Information
     */
    private HttpHost httpHost;

    /**
     * 从服务器获取的token,以Basic开头
     * The token obtained from the server, starting with Basic
     */
    private String token;

    /**
     * 用户名，以Base64加密
     * Username, encrypted with Base64
     */
    private String userName;

    /**
     * 密码，以Base64加密
     * Password, encrypted with Base64
     */
    private String password;
    
    /**
     * 从服务器获取的id
     * The id obtained from the server
     * @return
     */
    
    private String id;

    public String getId() 
    {
		return id;
	}

	public HttpHost getHttpHost() 
    {
        return httpHost;
    }

    public String getToken() 
    {
        return token;
    }

    public String getUserName() 
    {
        return userName;
    }

    public String getPassword() 
    {
        return password;
    }

}
