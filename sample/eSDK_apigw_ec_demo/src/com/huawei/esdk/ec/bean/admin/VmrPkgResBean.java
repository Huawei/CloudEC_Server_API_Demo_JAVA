package com.huawei.esdk.ec.bean.admin;
/**
 * 增加服务提供商中分配给组织的虚拟会议室类型信息VmrPkgResBean参数实体
 * @author wWX534262
 *
 */
public class VmrPkgResBean 
{
	//发放的虚拟会议室类型名称
	private String vmrPkgName;
	
	//发放的虚拟会议室类型个数
	private Integer vmrPkgCount;

	public String getVmrPkgName() 
	{
		return vmrPkgName;
	}

	public void setVmrPkgName(String vmrPkgName) 
	{
		this.vmrPkgName = vmrPkgName;
	}

	public Integer getVmrPkgCount() 
	{
		return vmrPkgCount;
	}

	public void setVmrPkgCount(Integer vmrPkgCount)
	{
		this.vmrPkgCount = vmrPkgCount;
	}
	
}
