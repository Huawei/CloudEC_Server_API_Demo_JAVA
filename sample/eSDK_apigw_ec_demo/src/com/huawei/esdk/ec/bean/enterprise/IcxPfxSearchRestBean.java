package com.huawei.esdk.ec.bean.enterprise;
/**
 * 企业内字冠/业务字冠查询条件 IcxPfxSearchRestBean
 * @author wWX534262
 *
 */
public class IcxPfxSearchRestBean 
{
	private String intraGrpPfx;
	
	private Integer pageSize;
	
	private Integer pageIndex;
	
	private Boolean wpf;
	
	private Boolean isNormal;

	public String getIntraGrpPfx() 
	{
		return intraGrpPfx;
	}

	public void setIntraGrpPfx(String intraGrpPfx)
	{
		this.intraGrpPfx = intraGrpPfx;
	}

	public Integer getPageSize() 
	{
		return pageSize;
	}

	public void setPageSize(Integer pageSize) 
	{
		this.pageSize = pageSize;
	}

	public Integer getPageIndex() 
	{
		return pageIndex;
	}

	public void setPageIndex(Integer pageIndex) 
	{
		this.pageIndex = pageIndex;
	}

	public Boolean getWpf() 
	{
		return wpf;
	}

	public void setWpf(Boolean wpf) 
	{
		this.wpf = wpf;
	}

	public Boolean getIsNormal() 
	{
		return isNormal;
	}

	public void setIsNormal(Boolean isNormal) 
	{
		this.isNormal = isNormal;
	}
	
}
