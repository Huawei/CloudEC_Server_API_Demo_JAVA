package com.huawei.esdk.ec.bean.admin;
/**
 * 系统管理员删除会议接入号参数实体
 */
import java.util.List;

public class DeleteAccesscodeBean 
{
	//待删除的接入号列表
	private List<String> confCodeList;

	public List<String> getConfCodeList() 
	{
		return confCodeList;
	}

	public void setConfCodeList(List<String> confCodeList) 
	{
		this.confCodeList = confCodeList;
	}
	
}
