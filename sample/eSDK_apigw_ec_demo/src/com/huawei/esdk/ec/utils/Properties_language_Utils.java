package com.huawei.esdk.ec.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Logger;

public class Properties_language_Utils 
{
private static final Logger LOGGER = Logger.getLogger(Properties_language_Utils.class.getName());
    
    private static Properties properties = null;
    
    
    static
	{
		properties = loadProperty();
	}
    
    
    public static Properties loadProperty()
    {
        Properties p = new Properties();
        //0代表中文，读取中文文件/0 for Chinese, read Chinese file
        //1代表英文，读取英文文件/1 for English, read English file
        if (LanguageUtils.getIndex() == 0) 
        {
        	loadProp("rest_config_ch.properties", p);
		}else 
		{
			loadProp("rest_config_en.properties", p);
		}
        
        
        return p;
    }
    
    private static void loadProp(String conf, Properties p)
    {
        InputStream is = null;
        try
        {
            is = getInputStream(conf);
            if (null == is) 
            {
				LOGGER.log(java.util.logging.Level.WARNING, "conf is not exist");
			}else 
			{
				p.load(is);
			}
            
        }
        catch (IOException e)
        {
            LOGGER.log(java.util.logging.Level.WARNING, "IO Exception,Exception happened in loadProp() " + conf);
        }
        finally
        {
            if (null != is)
            {
                try
                {
                    is.close();
                }
                catch (IOException e)
                {
                    LOGGER.log(java.util.logging.Level.WARNING, "IO Exception,Exception happened in loadProperty()" + conf);
                }
            }
        }
    }
    
    public static void set(String key, String value){
    	properties.setProperty(key, value);
    	properties.put(key, value);
    }
    
    public static String getValue(String key)
    {
        String value = properties.getProperty(key);
        
        return null == value ? "" : value;
    }
    
    private static InputStream getInputStream(String path)
        throws IOException
    {
    	if (null == Thread.currentThread().getContextClassLoader()) 
    	{
    		return null;
    	}else 
    	{
    		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
    		if (null == is)
    		{
    			throw new FileNotFoundException(path + " cannot be opened because it does not exist");
    		}
    		return is;
    	}
    }
}
