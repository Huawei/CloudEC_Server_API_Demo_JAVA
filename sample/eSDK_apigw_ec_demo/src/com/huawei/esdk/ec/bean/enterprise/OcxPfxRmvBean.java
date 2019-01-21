package com.huawei.esdk.ec.bean.enterprise;
/**
 * 出企业字冠，删除出企业字冠参数 OcxPfxRmvBean
 * @author wWX534262
 *
 */
public class OcxPfxRmvBean 
{
	//出企业字冠
	private String outGrpPfx;
	
	//是否通配，默认false
	private boolean wpf = false;

	public String getOutGrpPfx() 
	{
		return outGrpPfx;
	}

	public void setOutGrpPfx(String outGrpPfx) 
	{
		this.outGrpPfx = outGrpPfx;
	}

	public boolean isWpf() 
	{
		return wpf;
	}

	public void setWpf(boolean wpf) 
	{
		this.wpf = wpf;
	}
	
}
