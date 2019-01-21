package com.huawei.esdk.ec.bean.admin;
/**
 * 增加或者修改服务提供商参数实体
 * @author wWX534262
 *
 */

import java.util.List;

public class SPInfo
{
	private String spId;
	
	private String spName;
	
	private String spDomain;
	
	private String contact;
	
	private String phone;
	
	private String mobile;
	
	private String fax;
	
	private String email;
	
	private String website;
	
	private String address;
	
	private String servPackName;
	
	private Integer ucCount;
	
	private Integer confCount;
	
	private Integer corpCount;
	
	private List<Integer> servTpIdList;
	
	private String umsServerInfo;
	
	private Integer isActive = 1;
	
	private Integer smartCenterIfc;
	
	private Integer pgmIFC;
	
	private Integer cerbtID;
	
	private Integer confRight;
	
	private String mgrDomain;
	
	private String servCode;
	
	private String subServCode;
	
	private Integer sdMultiPicCount;
	
	private Integer hdMultiPicCount;
	
	private Integer simulcastSdVideoCount;
	
	private Integer simulcastHdVideoCount;
	
	private Integer audioCount;
	
	private Integer sdCount;
	
	private Integer hdCount;
	
	private Integer dataCount;
	
	private  Integer recordCapability;
	
	private  Integer pinCodeCount;
	
	private  Integer agentCount;
	
	private  Integer ivrCount;
	
	private  Integer huntgrpCount;
	
	private  Integer upathGrpCount;
	
	private  Integer recordCount;
	
	private  Integer curcallCount;
	
	private  Boolean useFullServPack;
	
	private  Boolean useIptServPack;
	
	private  String iptServPackName;
	
	private  Boolean useConfServPack;
	
	private  String confServPackName;
	
	private  Integer recordCycleTime;
	
	private  Integer meetingType;
	
	private  Integer teHardCount;
	
	private  Integer teSoftCount;
	
	private  Integer port1080P;
	
	private  List<VmrPkgResBean> vmrPkgList;
	
	private  String iptFunction = "1";
	
	private String ucFunction = "1";
	
	private Integer pmsiCount = 0;
	
	private Integer agentNumberCount;

	public String getSpId() 
	{
		return spId;
	}

	public void setSpId(String spId) 
	{
		this.spId = spId;
	}

	public String getSpName() 
	{
		return spName;
	}

	public void setSpName(String spName) 
	{
		this.spName = spName;
	}

	public String getSpDomain() 
	{
		return spDomain;
	}

	public void setSpDomain(String spDomain) 
	{
		this.spDomain = spDomain;
	}

	public String getContact()
	{
		return contact;
	}

	public void setContact(String contact) 
	{
		this.contact = contact;
	}

	public String getPhone() 
	{
		return phone;
	}

	public void setPhone(String phone) 
	{
		this.phone = phone;
	}

	public String getMobile() 
	{
		return mobile;
	}

	public void setMobile(String mobile) 
	{
		this.mobile = mobile;
	}

	public String getFax() 
	{
		return fax;
	}

	public void setFax(String fax) 
	{
		this.fax = fax;
	}

	public String getEmail() 
	{
		return email;
	}

	public void setEmail(String email) 
	{
		this.email = email;
	}

	public String getWebsite() 
	{
		return website;
	}

	public void setWebsite(String website) 
	{
		this.website = website;
	}

	public String getAddress()
	{
		return address;
	}

	public void setAddress(String address) 
	{
		this.address = address;
	}

	public String getServPackName()
	{
		return servPackName;
	}

	public void setServPackName(String servPackName)
	{
		this.servPackName = servPackName;
	}

	public Integer getUcCount() 
	{
		return ucCount;
	}

	public void setUcCount(Integer ucCount) 
	{
		this.ucCount = ucCount;
	}

	public Integer getConfCount() 
	{
		return confCount;
	}

	public void setConfCount(Integer confCount)
	{
		this.confCount = confCount;
	}

	public Integer getCorpCount() 
	{
		return corpCount;
	}

	public void setCorpCount(Integer corpCount)
	{
		this.corpCount = corpCount;
	}

	public List<Integer> getServTpIdList() 
	{
		return servTpIdList;
	}

	public void setServTpIdList(List<Integer> servTpIdList) 
	{
		this.servTpIdList = servTpIdList;
	}

	public String getUmsServerInfo()
	{
		return umsServerInfo;
	}

	public void setUmsServerInfo(String umsServerInfo) 
	{
		this.umsServerInfo = umsServerInfo;
	}

	public Integer getIsActive()
	{
		return isActive;
	}

	public void setIsActive(Integer isActive) 
	{
		this.isActive = isActive;
	}

	public Integer getSmartCenterIfc() 
	{
		return smartCenterIfc;
	}

	public void setSmartCenterIfc(Integer smartCenterIfc)
	{
		this.smartCenterIfc = smartCenterIfc;
	}

	public Integer getPgmIFC() 
	{
		return pgmIFC;
	}

	public void setPgmIFC(Integer pgmIFC)
	{
		this.pgmIFC = pgmIFC;
	}

	public Integer getCerbtID() 
	{
		return cerbtID;
	}

	public void setCerbtID(Integer cerbtID)
	{
		this.cerbtID = cerbtID;
	}

	public Integer getConfRight()
	{
		return confRight;
	}

	public void setConfRight(Integer confRight) 
	{
		this.confRight = confRight;
	}

	public String getMgrDomain() 
	{
		return mgrDomain;
	}

	public void setMgrDomain(String mgrDomain)
	{
		this.mgrDomain = mgrDomain;
	}

	public String getServCode() 
	{
		return servCode;
	}

	public void setServCode(String servCode) 
	{
		this.servCode = servCode;
	}

	public String getSubServCode()
	{
		return subServCode;
	}

	public void setSubServCode(String subServCode) 
	{
		this.subServCode = subServCode;
	}

	public Integer getSdMultiPicCount()
	{
		return sdMultiPicCount;
	}

	public void setSdMultiPicCount(int sdMultiPicCount)
	{
		this.sdMultiPicCount = sdMultiPicCount;
	}

	public Integer getHdMultiPicCount()
	{
		return hdMultiPicCount;
	}

	public void setHdMultiPicCount(int hdMultiPicCount) 
	{
		this.hdMultiPicCount = hdMultiPicCount;
	}

	public Integer getSimulcastSdVideoCount() 
	{
		return simulcastSdVideoCount;
	}

	public void setSimulcastSdVideoCount(int simulcastSdVideoCount) 
	{
		this.simulcastSdVideoCount = simulcastSdVideoCount;
	}

	public Integer getSimulcastHdVideoCount() 
	{
		return simulcastHdVideoCount;
	}

	public void setSimulcastHdVideoCount(int simulcastHdVideoCount)
	{
		this.simulcastHdVideoCount = simulcastHdVideoCount;
	}

	public Integer getAudioCount() 
	{
		return audioCount;
	}

	public void setAudioCount(int audioCount) 
	{
		this.audioCount = audioCount;
	}

	public Integer getSdCount() 
	{
		return sdCount;
	}

	public void setSdCount(int sdCount)
	{
		this.sdCount = sdCount;
	}

	public Integer getHdCount()
	{
		return hdCount;
	}

	public void setHdCount(int hdCount) 
	{
		this.hdCount = hdCount;
	}

	public Integer getDataCount() 
	{
		return dataCount;
	}

	public void setDataCount(int dataCount)
	{
		this.dataCount = dataCount;
	}

	public Integer getRecordCapability()
	{
		return recordCapability;
	}

	public void setRecordCapability(int recordCapability) 
	{
		this.recordCapability = recordCapability;
	}

	public Integer getPinCodeCount() 
	{
		return pinCodeCount;
	}

	public void setPinCodeCount(int pinCodeCount) 
	{
		this.pinCodeCount = pinCodeCount;
	}

	public Integer getAgentCount() 
	{
		return agentCount;
	}

	public void setAgentCount(int agentCount) 
	{
		this.agentCount = agentCount;
	}

	public Integer getIvrCount() 
	{
		return ivrCount;
	}

	public void setIvrCount(int ivrCount)
	{
		this.ivrCount = ivrCount;
	}

	public Integer getHuntgrpCount() 
	{
		return huntgrpCount;
	}

	public void setHuntgrpCount(int huntgrpCount) 
	{
		this.huntgrpCount = huntgrpCount;
	}

	public Integer getUpathGrpCount() 
	{
		return upathGrpCount;
	}

	public void setUpathGrpCount(int upathGrpCount)
	{
		this.upathGrpCount = upathGrpCount;
	}

	public Integer getRecordCount()
	{
		return recordCount;
	}

	public void setRecordCount(int recordCount) 
	{
		this.recordCount = recordCount;
	}

	public Integer getCurcallCount() 
	{
		return curcallCount;
	}

	public void setCurcallCount(int curcallCount) 
	{
		this.curcallCount = curcallCount;
	}

	public Boolean getUseFullServPack() 
	{
		return useFullServPack;
	}

	public void setUseFullServPack(Boolean useFullServPack) 
	{
		this.useFullServPack = useFullServPack;
	}

	public Boolean getUseIptServPack() 
	{
		return useIptServPack;
	}

	public void setUseIptServPack(Boolean useIptServPack) 
	{
		this.useIptServPack = useIptServPack;
	}

	public String getIptServPackName()
	{
		return iptServPackName;
	}

	public void setIptServPackName(String iptServPackName)
	{
		this.iptServPackName = iptServPackName;
	}

	public Boolean getUseConfServPack() 
	{
		return useConfServPack;
	}

	public void setUseConfServPack(Boolean useConfServPack) 
	{
		this.useConfServPack = useConfServPack;
	}

	public String getConfServPackName() 
	{
		return confServPackName;
	}

	public void setConfServPackName(String confServPackName)
	{
		this.confServPackName = confServPackName;
	}

	public Integer getRecordCycleTime() 
	{
		return recordCycleTime;
	}

	public void setRecordCycleTime(int recordCycleTime) 
	{
		this.recordCycleTime = recordCycleTime;
	}

	public Integer getMeetingType()
	{
		return meetingType;
	}

	public void setMeetingType(int meetingType) 
	{
		this.meetingType = meetingType;
	}

	public Integer getTeHardCount() 
	{
		return teHardCount;
	}

	public void setTeHardCount(int teHardCount) 
	{
		this.teHardCount = teHardCount;
	}

	public Integer getTeSoftCount() 
	{
		return teSoftCount;
	}

	public void setTeSoftCount(int teSoftCount) 
	{
		this.teSoftCount = teSoftCount;
	}

	public Integer getPort1080P() 
	{
		return port1080P;
	}

	public void setPort1080P(int port1080p) 
	{
		port1080P = port1080p;
	}

	public List<VmrPkgResBean> getVmrPkgList() 
	{
		return vmrPkgList;
	}

	public void setVmrPkgList(List<VmrPkgResBean> vmrPkgList)
	{
		this.vmrPkgList = vmrPkgList;
	}

	public String getIptFunction() 
	{
		return iptFunction;
	}

	public void setIptFunction(String iptFunction) 
	{
		this.iptFunction = iptFunction;
	}

	public String getUcFunction() 
	{
		return ucFunction;
	}

	public void setUcFunction(String ucFunction) 
	{
		this.ucFunction = ucFunction;
	}

	public Integer getPmsiCount() 
	{
		return pmsiCount;
	}

	public void setPmsiCount(int pmsiCount) 
	{
		this.pmsiCount = pmsiCount;
	}

	public Integer getAgentNumberCount()
	{
		return agentNumberCount;
	}

	public void setAgentNumberCount(int agentNumberCount)
	{
		this.agentNumberCount = agentNumberCount;
	}
	
}
