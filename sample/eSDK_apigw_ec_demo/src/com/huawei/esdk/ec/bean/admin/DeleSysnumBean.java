package com.huawei.esdk.ec.bean.admin;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DeleSysnumBean 
{
	private List<NumIndexDeleBean> numIndexDeleBeanLst;

	@JsonProperty("data")
	public List<NumIndexDeleBean> getNumIndexDeleBeanLst() 
	{
		return numIndexDeleBeanLst;
	}

	public void setNumIndexDeleBeanLst(List<NumIndexDeleBean> numIndexDeleBeanLst)
	{
		this.numIndexDeleBeanLst = numIndexDeleBeanLst;
	}
	
}
