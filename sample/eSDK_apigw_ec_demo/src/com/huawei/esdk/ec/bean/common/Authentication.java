package com.huawei.esdk.ec.bean.common;

/**
 * 进行登录鉴权所需信息，包括API网关地址、鉴权登录用户名、鉴权登录密码
 * 
 * The information required for login authentication, 
 * including the API gateway address, authentication login user name, and authentication login password
 * @see AuthUtils
 * @author t81023743
 *
 */
public class Authentication {
    
    /**
     * API网关的地址
     * API Gateway address
     */
    private String address;
    
    /**
     * 进行鉴权登录的用户名
     * User name for authenticating login
     */
    private String user;
    
    /**
     * 进行鉴权登录的密码
     * Password for authenticating login
     */
    private String password;

    /**
     * 构造函数
     * Constructor
     * @param address API网关地址/API Gateway address
     * @param user 用户名/User
     * @param password 密码/password
     */
    public Authentication(String address, String user, String password) 
    {
        this.address = address;
        this.user = user;
        this.password = password;
    }

    public String getAddress() 
    {
        return address;
    }

    public String getUser()
    {
        return user;
    }

    public String getPassword() {
        return password;
    }

}
