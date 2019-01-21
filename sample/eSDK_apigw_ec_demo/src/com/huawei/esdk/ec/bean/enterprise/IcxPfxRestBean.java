package com.huawei.esdk.ec.bean.enterprise;
/**
 * 增加企业内字冠/业务字冠参数实体
 * @author wWX534262
 *
 */
public class IcxPfxRestBean 
{
	//是否通配字冠,默认false
	private boolean wpf = false;
	
	//企业内字冠/业务字冠，格式和长度与wpf的取值有关
	private String intraGrpPfx;
	
	//业务名称,取值NORMAL为企业内字冠，其他为业务字冠
	private String srvName;
	
	//最小号长
	private Integer minNumLen;
	
	//最大号长
	private Integer maxNumLen;

	public boolean isWpf() 
	{
		return wpf;
	}

	public void setWpf(boolean wpf) 
	{
		this.wpf = wpf;
	}

	public String getIntraGrpPfx() 
	{
		return intraGrpPfx;
	}

	public void setIntraGrpPfx(String intraGrpPfx) 
	{
		this.intraGrpPfx = intraGrpPfx;
	}

	public String getSrvName() 
	{
		return srvName;
	}

	public void setSrvName(String srvName) 
	{
		this.srvName = srvName;
	}

	public Integer getMinNumLen() 
	{
		return minNumLen;
	}

	public void setMinNumLen(Integer minNumLen) 
	{
		this.minNumLen = minNumLen;
	}

	public Integer getMaxNumLen() 
	{
		return maxNumLen;
	}

	public void setMaxNumLen(Integer maxNumLen) 
	{
		this.maxNumLen = maxNumLen;
	}
	
	
}
