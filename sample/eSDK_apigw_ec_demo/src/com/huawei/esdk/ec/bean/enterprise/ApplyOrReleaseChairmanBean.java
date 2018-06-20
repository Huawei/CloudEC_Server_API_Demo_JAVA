package com.huawei.esdk.ec.bean.enterprise;
/**
 * 申请释放主席的参数实体
 * Apply or release chairman request body parameters
 * @author wWX534262
 *
 */
public class ApplyOrReleaseChairmanBean 
{
	private Integer applyChair;
	
	private String chairmanPwd;

	public Integer getApplyChair() 
	{
		return applyChair;
	}

	public void setApplyChair(Integer applyChair) 
	{
		this.applyChair = applyChair;
	}

	public String getChairmanPwd() 
	{
		return chairmanPwd;
	}

	public void setChairmanPwd(String chairmanPwd) 
	{
		this.chairmanPwd = chairmanPwd;
	}
	
	
}
