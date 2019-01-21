package com.huawei.esdk.ec.bean.enterprise;
/**
 * 增加/修改出企业字冠，出企业字冠数据内容 OcxPfxRestBean
 * @author wWX534262
 *
 */
public class OcxPfxRestBean 
{
	//是否通配字冠,默认false
	private Boolean wpf = false;
	
	//出企业字冠,格式和wpf取值有关
	private String outGrpPfx;
	
	//出企业类型，默认值为NORMAL
	private String outGrpType = "NORMAL";
	
	//删除字冠长度当wpf=true，只能取值0；当wpf=false，取值范围[0,16],默认值是0
	private Integer deleteLength = 0;

	public Boolean getWpf() 
	{
		return wpf;
	}

	public void setWpf(Boolean wpf) 
	{
		this.wpf = wpf;
	}

	public String getOutGrpPfx() 
	{
		return outGrpPfx;
	}

	public void setOutGrpPfx(String outGrpPfx) 
	{
		this.outGrpPfx = outGrpPfx;
	}

	public String getOutGrpType() 
	{
		return outGrpType;
	}

	public void setOutGrpType(String outGrpType) 
	{
		this.outGrpType = outGrpType;
	}

	public Integer getDeleteLength() 
	{
		return deleteLength;
	}

	public void setDeleteLength(Integer deleteLength) 
	{
		this.deleteLength = deleteLength;
	}
	
}
