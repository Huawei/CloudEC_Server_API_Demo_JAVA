package com.huawei.esdk.ec.bean.sp;

import java.util.Map;
/**
 * 业务包信息
 * servpack information
 * @author wwx534262
 *
 */
public class ServPack 
{
	private String packName;
	
	private String orgId;
	
	private String packType;
	
	private ResourcePack resource;
	
	private Map<ServRightEnum,ServRightStatusEnum> usrPerms;
	
	private String packDesc;
	
	private String packRatio;
	
	private Integer isCustomizeRights;

	public String getPackName() 
	{
		return packName;
	}

	public void setPackName(String packName)
	{
		this.packName = packName;
	}

	public String getOrgId() 
	{
		return orgId;
	}

	public void setOrgId(String orgId) 
	{
		this.orgId = orgId;
	}

	public String getPackType()
	{
		return packType;
	}

	public void setPackType(String packType) 
	{
		this.packType = packType;
	}

	public ResourcePack getResource() 
	{
		return resource;
	}

	public void setResource(ResourcePack resource)
	{
		this.resource = resource;
	}

	public Map<ServRightEnum, ServRightStatusEnum> getUsrPerms() 
	{
		return usrPerms;
	}

	public void setUsrPerms(Map<ServRightEnum, ServRightStatusEnum> usrPerms)
	{
		this.usrPerms = usrPerms;
	}

	public String getPackDesc() 
	{
		return packDesc;
	}

	public void setPackDesc(String packDesc)
	{
		this.packDesc = packDesc;
	}

	public String getPackRatio() 
	{
		return packRatio;
	}

	public void setPackRatio(String packRatio) 
	{
		this.packRatio = packRatio;
	}

	public Integer getIsCustomizeRights() 
	{
		return isCustomizeRights;
	}

	public void setIsCustomizeRights(Integer isCustomizeRights) 
	{
		this.isCustomizeRights = isCustomizeRights;
	}
	
	
	
}
