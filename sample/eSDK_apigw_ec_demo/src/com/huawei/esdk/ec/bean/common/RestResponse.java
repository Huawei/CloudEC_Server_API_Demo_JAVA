package com.huawei.esdk.ec.bean.common;
/**
 * Copyright 2016 Huawei Technologies Co., Ltd. All rights reserved.
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


import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * HTTP响应消息结构
 * HTTP response message structure
 * 
 * @author  l00295065
 * 
 */
public class RestResponse
{
    private int httpCode;
    
    private Map<String, List<String>> headers;
    
    private String entity;
    
    public RestResponse()
    {
        headers = new HashMap<String, List<String>>();
    }
    
    public int getHttpCode()
    {
        return httpCode;
    }
    
    public void setHttpCode(int httpCode)
    {
        this.httpCode = httpCode;
    }
    
    public Map<String, List<String>> getHeaders()
    {
        if (headers == null)
        {
            headers = new HashMap<String, List<String>>();
        }
        return headers;
    }
    
    public void setHeaders(Map<String, List<String>> headers)
    {
        this.headers = headers;
    }
    
    public String getEntity()
    {
        return entity;
    }
    
    public void setEntity(String entity)
    {
        this.entity = entity;
    }
    
}
