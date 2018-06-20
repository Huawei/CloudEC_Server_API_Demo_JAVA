package com.huawei.esdk.ec.bean.enterprise;

import java.util.List;

/**
 * 增加设备请求体参数
 * Increase device request body parameters
 * @author wWX534262
 *
 */
public class AddDeviceBean 
{
	private String deviceId;
	
	private Integer deviceIdType;
	
	private Integer deviceType;
	
	private String deviceModel;
	
	private String corpId;
	
	private Integer deviceSrc;
	
	private String orgId;
	
	private Integer deviceStatus;
	
	private Integer tmsManaged;
	
	private Integer ipCheckFlag;
	
	private Integer suppExtMob;
	
	private String siteName;
	
	private String deviceLocation;
	
	private String description;
	
	private List<String> lineLst;
	
	private IpccLineInfo ipccLineInfo;

	public String getDeviceId() 
	{
		return deviceId;
	}

	public void setDeviceId(String deviceId)
	{
		this.deviceId = deviceId;
	}

	public Integer getDeviceIdType() 
	{
		return deviceIdType;
	}

	public void setDeviceIdType(Integer deviceIdType) 
	{
		this.deviceIdType = deviceIdType;
	}

	public Integer getDeviceType()
	{
		return deviceType;
	}

	public void setDeviceType(Integer deviceType) 
	{
		this.deviceType = deviceType;
	}

	public String getDeviceModel()
	{
		return deviceModel;
	}

	public void setDeviceModel(String deviceModel)
	{
		this.deviceModel = deviceModel;
	}

	public String getCorpId()
	{
		return corpId;
	}

	public void setCorpId(String corpId) 
	{
		this.corpId = corpId;
	}

	public Integer getDeviceSrc() 
	{
		return deviceSrc;
	}

	public void setDeviceSrc(Integer deviceSrc)
	{
		this.deviceSrc = deviceSrc;
	}

	public String getOrgId()
	{
		return orgId;
	}

	public void setOrgId(String orgId) 
	{
		this.orgId = orgId;
	}

	public Integer getDeviceStatus() 
	{
		return deviceStatus;
	}

	public void setDeviceStatus(Integer deviceStatus) 
	{
		this.deviceStatus = deviceStatus;
	}

	public Integer getTmsManaged() 
	{
		return tmsManaged;
	}

	public void setTmsManaged(Integer tmsManaged) 
	{
		this.tmsManaged = tmsManaged;
	}

	public Integer getIpCheckFlag()
	{
		return ipCheckFlag;
	}

	public void setIpCheckFlag(Integer ipCheckFlag)
	{
		this.ipCheckFlag = ipCheckFlag;
	}

	public Integer getSuppExtMob() 
	{
		return suppExtMob;
	}

	public void setSuppExtMob(Integer suppExtMob)
	{
		this.suppExtMob = suppExtMob;
	}

	public String getSiteName()
	{
		return siteName;
	}

	public void setSiteName(String siteName) 
	{
		this.siteName = siteName;
	}

	public String getDeviceLocation() 
	{
		return deviceLocation;
	}

	public void setDeviceLocation(String deviceLocation) 
	{
		this.deviceLocation = deviceLocation;
	}

	public String getDescription() 
	{
		return description;
	}

	public void setDescription(String description) 
	{
		this.description = description;
	}

	public List<String> getLineLst()
	{
		return lineLst;
	}

	public void setLineLst(List<String> lineLst)
	{
		this.lineLst = lineLst;
	}

	public IpccLineInfo getIpccLineInfo() {
		return ipccLineInfo;
	}

	public void setIpccLineInfo(IpccLineInfo ipccLineInfo)
	{
		this.ipccLineInfo = ipccLineInfo;
	}
	
}
