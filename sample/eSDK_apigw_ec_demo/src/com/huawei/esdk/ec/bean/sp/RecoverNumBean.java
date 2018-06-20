package com.huawei.esdk.ec.bean.sp;

import java.util.List;

/**
 * 服务提供商号段管理接口/Service Provider Segment Management Interface
 * 回收企业号码请求体实体/Recycling enterprise number request entity
 * @author wWX534262
 *
 */
public class RecoverNumBean 
{
	private String srcCorpId;
	
	private List<String> numberList;

	public String getSrcCorpId() 
	{
		return srcCorpId;
	}

	public void setSrcCorpId(String srcCorpId)
	{
		this.srcCorpId = srcCorpId;
	}

	public List<String> getNumberList() 
	{
		return numberList;
	}

	public void setNumberList(List<String> numberList)
	{
		this.numberList = numberList;
	}
	
}
