package com.huawei.esdk.ec.bean.admin;

import java.util.List;

/**
 * 从企业回收系统号段参数信息
 * @author wWX534262
 *
 */
public class RecyleSysnumFromCorpBean 
{
	private List<String> numberList;
	
	private String srcCorpId;

	public List<String> getNumberList()
	{
		return numberList;
	}

	public void setNumberList(List<String> numberList)
	{
		this.numberList = numberList;
	}

	public String getSrcCorpId()
	{
		return srcCorpId;
	}

	public void setSrcCorpId(String srcCorpId)
	{
		this.srcCorpId = srcCorpId;
	}
	
}
