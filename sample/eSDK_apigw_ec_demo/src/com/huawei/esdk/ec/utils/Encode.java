package com.huawei.esdk.ec.utils;

import java.util.regex.Pattern;

public class Encode
{
    /**
     * 对用户输入内容进行编码
     * Encode user input content
     * @param obj obj
     * @return    result
     */
    public static String encodeForLog(Object obj)
    {
        if (obj == null)
        {
            return "null";
        }   
        String msg = obj.toString();
        int length = msg.length();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++)
        {
            char ch = msg.charAt(i);
            // 将\r\n替换成'_'
            //Replace \r\n with '_'
            if (ch == '\r' || ch == '\n')
            {
                ch = '_';
            }
            sb.append(Character.valueOf(ch));
        }
        return sb.toString();
    }
    
    
    //判断url是否合法
    //Determine the validity of the url
    public static boolean encodeURL(String url) 
    {
    	String regex = "^([hH][tT]{2}[pP]:/*|[hH][tT]{2}[pP][sS]:/*|[fF][tT][pP]:/*)(([A-Za-z0-9-~]+).)+([A-Za-z0-9-~\\/])+(\\?{0,1}(([A-Za-z0-9-~]+\\={0,1})([A-Za-z0-9-~]*)\\&{0,1})*)$";
    	Pattern pattern = Pattern.compile(regex);
    	if(StringUtils.isEmpty(url)) 
		{
			return false;
		}
    	if(pattern.matcher(url).matches()) 
    	{
    		return true;
    	} else 
    	{
    		return false;
    	}
    }
}
