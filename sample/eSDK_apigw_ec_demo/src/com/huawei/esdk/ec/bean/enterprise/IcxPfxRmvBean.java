package com.huawei.esdk.ec.bean.enterprise;
/**
 *  删除企业内字冠/业务字冠参数 
 * @author wWX534262
 *
 */
public class IcxPfxRmvBean 
{
	private String intraGrpPfx;
	
	private boolean wpf;

	public String getIntraGrpPfx() 
	{
		return intraGrpPfx;
	}

	public void setIntraGrpPfx(String intraGrpPfx) 
	{
		this.intraGrpPfx = intraGrpPfx;
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
