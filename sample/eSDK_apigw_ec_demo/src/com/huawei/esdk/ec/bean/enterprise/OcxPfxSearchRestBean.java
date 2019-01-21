package com.huawei.esdk.ec.bean.enterprise;
/**
 * 出企业字冠查询条件参数 OcxPfxSearchRestBean
 * @author wWX534262
 *
 */
public class OcxPfxSearchRestBean 
{
	//出企业字冠
	private String outGrpPfx;
	
	private Integer pageSize;
	
	private Integer pageIndex;
	
	//是否通配字冠
	private Boolean wpf;

	public String getOutGrpPfx() 
	{
		return outGrpPfx;
	}

	public void setOutGrpPfx(String outGrpPfx) 
	{
		this.outGrpPfx = outGrpPfx;
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
	
}
