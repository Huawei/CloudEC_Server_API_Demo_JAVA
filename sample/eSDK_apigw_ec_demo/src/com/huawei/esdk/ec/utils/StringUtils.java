package com.huawei.esdk.ec.utils;


import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.huawei.esdk.ec.bean.common.RestResponse;


public class StringUtils
{
	private static final Logger LOG = LogManager.getLogger(StringUtils.class);
	
	public static final String EMPTY_STRING = "";
    
    public static boolean isEmpty(String str)
    {
        if (null == str || str.trim().length() == 0)
        {
            return true;
        }
        
        return false;
    }
    
    public static boolean isNotEmpty(String str)
    {
        return !isEmpty(str);
    }
    
    public static boolean isValue(String str)
    {
        return !isEmpty(str);
    }
    
    public static String hasValue(String value)
    {
        return isEmpty(value) ? "" : value;
    }
    
    public static String avoidNull(String value)
    {
        if (null != value)
        {
            return value;
        }
        
        return "";
    }
    
    public static boolean isNumber(String str)
    {
        if (null == str)
        {
            return false;
        }
        
        return str.matches("\\d+");
    }
    
    public static boolean isEmptyWihthoutTrim(String str)
    {
        if (str == null || str.length() == 0)
        {
            return true;
        }
        return false;
    }
    
    public static String getIpFromHttpAddress(String httpAddress)
    {
        if (isEmpty(httpAddress))
        {
            return null;
        }
        
        try
        {
        	httpAddress = Normalizer.normalize(httpAddress, Form.NFKC);
        	if(Encode.encodeURL(httpAddress)) 
        	{
        		URL url = new URL(httpAddress);
                return url.getHost();
        	} 
        	else 
        	{
        		return null;
        	}
        }
        catch (MalformedURLException e)
        {
            return null;
        }
        
    }
    
    public static int getPortFromHttpAddress(String httpAddress)
    {
        if (isEmpty(httpAddress))
        {
            return -1;
        }
        
        try
        {
        	httpAddress = Normalizer.normalize(httpAddress, Form.NFKC);
        	if(Encode.encodeURL(httpAddress)) 
        	{
        		URL url = new URL(httpAddress);
        		return url.getPort();
        	}
        	else 
        	{
        		return -1;
        	}
        }
        catch (MalformedURLException e)
        {
            return -1;
        }
        
    }
    
    public static String getSchemeFromHttpAddress(String httpAddress)
    {
        if (isEmpty(httpAddress))
        {
            return null;
        }
        
        try
        {
        	httpAddress = Normalizer.normalize(httpAddress, Form.NFKC);
        	if(Encode.encodeURL(httpAddress)) 
        	{
        		URL url = new URL(httpAddress);
        		return url.getProtocol();
        	}
        	else 
        	{
        		return null;
        	}
        }
        catch (MalformedURLException e)
        {
            return null;
        }
        
    }
    
    public static String formatJson(String jsonStr)
    {
        if (null == jsonStr || "".equals(jsonStr))
            return "";
        StringBuilder sb = new StringBuilder();
        char last = '\0';
        char current = '\0';
        int indent = 0;
        for (int i = 0; i < jsonStr.length(); i++)
        {
            last = current;
            current = jsonStr.charAt(i);
            switch (current)
            {
                case '{':
                case '[':
                    sb.append(current);
                    sb.append('\n');
                    indent++;
                    addIndentBlank(sb, indent);
                    break;
                case '}':
                case ']':
                    sb.append('\n');
                    indent--;
                    addIndentBlank(sb, indent);
                    sb.append(current);
                    break;
                case ',':
                    sb.append(current);
                    if (last != '\\')
                    {
                        sb.append('\n');
                        addIndentBlank(sb, indent);
                    }
                    break;
                default:
                    sb.append(current);
            }
        }
        
        return sb.toString();
    }
    
    private static void addIndentBlank(StringBuilder sb, int indent)
    {
        for (int i = 0; i < indent; ++i)
        {
            sb.append("  ");
        }
    }
    
    public static String formatHttpHeads(RestResponse response) 
    {
    	Map<String, List<String>> headers = response.getHeaders();
    	String mapHeader = headers.toString();
    	String header = mapHeader.replace(", ", "\n").substring(1, mapHeader.replace(", ", "\n").length() - 1);
    	
    	return header.replace("=", ":");
    }
    
    /**
     * json to map method
     * @param json
     * @return map
     */
    @SuppressWarnings("unchecked")
	public static HashMap<String, Object> jsonToMap(String json)
    {
        ObjectMapper objectMapper = new ObjectMapper();
        HashMap<String, Object> result;
        try
        {
            result = objectMapper.readValue(json, HashMap.class);
            return result;
        }
        catch (JsonParseException e)
        {
            LOG.error("catch JsonParseException");
            return null;
        }
        catch (JsonMappingException e)
        {
            LOG.error("catch JsonMappingException");
            return null;
        }
        catch (IOException e)
        {
            LOG.error("catch IOException");
            return null;
        }
    }
    
    /**
     * Object to json
     * @param object object
     * @return json Json String
     * @throws IOException 
     */
    public static String beanToJson(Object object)
    {
    	 ObjectMapper mapper = new ObjectMapper();
         String json = StringUtils.EMPTY_STRING;
         
         try 
     	{
 			json = mapper.writeValueAsString(object);
 		} 
     	catch (JsonProcessingException e) 
     	{
 			 LOG.error("object to json string failed");
 		}
     	
     	return json;
    }
}
