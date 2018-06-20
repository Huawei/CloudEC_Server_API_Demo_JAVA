package com.huawei.esdk.ec.utils;
/**
 * 记录语言数字，0代表中文，1代表英文
 * Record language number, 0 for Chinese, 1 for English
 * @author wwx534262
 *
 */
public class LanguageUtils 
{
	private static int index;

	public static int getIndex() 
	{
		return index;
	}

	public static void setIndex(int index) 
	{
		LanguageUtils.index = index;
	}
	
}
