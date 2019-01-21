package com.huawei.esdk.ec.bean.sp;
/**
 * 增加企业接口中增加企业实体类
 * Increase enterprise interface to increase enterprise entity 
 * @author wWX534262
 *
 */

import java.util.List;


public class AddCorpBean 
{
	private String spId;
	
	private String corpName;
	
	private String corpId;
	
	private String primeNum;
	
	private String corpFax;
	
	private String smsrvNumber;
	
	private String hangUpSm;
	
	private String corpAddr;
	
	private String email;
	
	private String domain;
	
	private String resPackName;
	
	private Integer ucCount;
	
	private Integer confCount;
	
	private Integer teSoftCount;
	
	private Integer teHardCount;
	
	private Integer pmsiType;
	
	private Integer pmsiCount;
	
	private List<UserPack> usrPacks;
	
	private Integer useFixedTP;
	
	private Integer serviceTpId;
	
	private String mgrDomain;
	
	private String servCode;
	
	private String subServCode;
	
	private String confFunction;
	
	private String isCloudVoice;
	
	private String regionAreaId;
	
	private Boolean useFullPack;
	
	private Boolean useIptPack;
	
	private Boolean useConfPack;
	
	private String iptResPackName;
	
	private String confResPackName;
	
	private Integer meetingType;
	
	private List<VmrPkgResBean> vmrPkgList;
	
	private Integer agentCount;
	
	private Integer ivrCount;
	
	private Integer huntgrpCount;
	
	private Integer recordCount;
	
	private Integer curcallCount;
	
	private Integer pinCodeCount;
	
	private Integer upathGrpCount;
	
	private Integer audioCount;
	
	private Integer sdCount;
	
	private Integer hdCount;
	
	private Integer dataCount;
	
	private Integer sdMultiPicCount;
	
	private Integer hdMultiPicCount;
	
	private Integer simulcastSdVideoCount;
	
	private Integer simulcastHdVideoCount;
	
	private String useSimcastVideo;
	
	private Integer recordCapability;
	
	private Integer recordCycleTime;
	
	private String iptFunction = "1";
	
	private String ucFunction = "1";
	
	private String isLbs;
	
	private String locationName;

	public String getSpId()
	{
		return spId;
	}

	public void setSpId(String spId)
	{
		this.spId = spId;
	}

	public String getCorpName() 
	{
		return corpName;
	}

	public void setCorpName(String corpName) 
	{
		this.corpName = corpName;
	}
	
	public String getCorpId() 
	{
		return corpId;
	}

	public void setCorpId(String corpId) 
	{
		this.corpId = corpId;
	}

	public String getPrimeNum() 
	{
		return primeNum;
	}

	public void setPrimeNum(String primeNum)
	{
		this.primeNum = primeNum;
	}

	public String getCorpFax() 
	{
		return corpFax;
	}

	public void setCorpFax(String corpFax)
	{
		this.corpFax = corpFax;
	}

	public String getSmsrvNumber() 
	{
		return smsrvNumber;
	}

	public void setSmsrvNumber(String smsrvNumber) 
	{
		this.smsrvNumber = smsrvNumber;
	}

	public String getHangUpSm() 
	{
		return hangUpSm;
	}

	public void setHangUpSm(String hangUpSm)
	{
		this.hangUpSm = hangUpSm;
	}

	public String getCorpAddr() 
	{
		return corpAddr;
	}

	public void setCorpAddr(String corpAddr)
	{
		this.corpAddr = corpAddr;
	}

	public String getEmail() 
	{
		return email;
	}

	public void setEmail(String email) 
	{
		this.email = email;
	}

	public String getDomain() 
	{
		return domain;
	}

	public void setDomain(String domain) 
	{
		this.domain = domain;
	}

	public String getResPackName() 
	{
		return resPackName;
	}

	public void setResPackName(String resPackName) 
	{
		this.resPackName = resPackName;
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

	public Integer getTeSoftCount() 
	{
		return teSoftCount;
	}

	public void setTeSoftCount(Integer teSoftCount)
	{
		this.teSoftCount = teSoftCount;
	}

	public Integer getTeHardCount()
	{
		return teHardCount;
	}

	public void setTeHardCount(Integer teHardCount)
	{
		this.teHardCount = teHardCount;
	}

	public Integer getPmsiType() 
	{
		return pmsiType;
	}

	public void setPmsiType(Integer pmsiType)
	{
		this.pmsiType = pmsiType;
	}

	public Integer getPmsiCount() 
	{
		return pmsiCount;
	}

	public void setPmsiCount(Integer pmsiCount) 
	{
		this.pmsiCount = pmsiCount;
	}

	public List<UserPack> getUsrPacks() 
	{
		return usrPacks;
	}

	public void setUsrPacks(List<UserPack> usrPacks) 
	{
		this.usrPacks = usrPacks;
	}

	public Integer getUseFixedTP() 
	{
		return useFixedTP;
	}

	public void setUseFixedTP(Integer useFixedTP)
	{
		this.useFixedTP = useFixedTP;
	}

	public Integer getServiceTpId()
	{
		return serviceTpId;
	}

	public void setServiceTpId(Integer serviceTpId) 
	{
		this.serviceTpId = serviceTpId;
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

	public void setSubServcode(String subServCode) 
	{
		this.subServCode = subServCode;
	}

	public String getConfFunction() 
	{
		return confFunction;
	}

	public void setConfFunction(String confFunction) 
	{
		this.confFunction = confFunction;
	}

	public String getIsCloudVoice() 
	{
		return isCloudVoice;
	}

	public void setIsCloudVoice(String isCloudVoice)
	{
		this.isCloudVoice = isCloudVoice;
	}

	public String getRegionAreaId() 
	{
		return regionAreaId;
	}

	public void setRegionAreaId(String regionAreaId) 
	{
		this.regionAreaId = regionAreaId;
	}

	public Boolean getUseFullPack() 
	{
		return useFullPack;
	}

	public void setUseFullPack(Boolean useFullPack) 
	{
		this.useFullPack = useFullPack;
	}

	public Boolean getUseIptPack() 
	{
		return useIptPack;
	}

	public void setUseIptPack(Boolean useIptPack)
	{
		this.useIptPack = useIptPack;
	}

	public Boolean getUseConfPack() 
	{
		return useConfPack;
	}

	public void setUseConfPack(Boolean useConfPack) 
	{
		this.useConfPack = useConfPack;
	}

	public String getIptResPackName() 
	{
		return iptResPackName;
	}

	public void setIptResPackName(String iptResPackName)
	{
		this.iptResPackName = iptResPackName;
	}

	public String getConfResPackName()
	{
		return confResPackName;
	}

	public void setConfResPackName(String confResPackName)
	{
		this.confResPackName = confResPackName;
	}

	public Integer getMeetingType() 
	{
		return meetingType;
	}

	public void setMeetingType(Integer meetingType) 
	{
		this.meetingType = meetingType;
	}

	public List<VmrPkgResBean> getVmrPkgList()
	{
		return vmrPkgList;
	}

	public void setVmrPkgList(List<VmrPkgResBean> vmrPkgList) 
	{
		this.vmrPkgList = vmrPkgList;
	}

	public Integer getAgentCount()
	{
		return agentCount;
	}

	public void setAgentCount(Integer agentCount)
	{
		this.agentCount = agentCount;
	}

	public Integer getIvrCount() 
	{
		return ivrCount;
	}

	public void setIvrCount(Integer ivrCount) 
	{
		this.ivrCount = ivrCount;
	}

	public Integer getHuntgrpCount() 
	{
		return huntgrpCount;
	}

	public void setHuntgrpCount(Integer huntgrpCount) 
	{
		this.huntgrpCount = huntgrpCount;
	}

	public Integer getRecordCount() 
	{
		return recordCount;
	}

	public void setRecordCount(Integer recordCount)
	{
		this.recordCount = recordCount;
	}

	public Integer getCurcallCount() 
	{
		return curcallCount;
	}

	public void setCurcallCount(Integer curcallCount) 
	{
		this.curcallCount = curcallCount;
	}

	public Integer getPinCodeCount() 
	{
		return pinCodeCount;
	}

	public void setPinCodeCount(Integer pinCodeCount) 
	{
		this.pinCodeCount = pinCodeCount;
	}

	public Integer getUpathGrpCount() 
	{
		return upathGrpCount;
	}

	public void setUpathGrpCount(Integer upathGrpCount) 
	{
		this.upathGrpCount = upathGrpCount;
	}

	public Integer getAudioCount() 
	{
		return audioCount;
	}

	public void setAudioCount(Integer audioCount) 
	{
		this.audioCount = audioCount;
	}

	public Integer getSdCount() 
	{
		return sdCount;
	}

	public void setSdCount(Integer sdCount) 
	{
		this.sdCount = sdCount;
	}

	public Integer getHdCount() 
	{
		return hdCount;
	}

	public void setHdCount(Integer hdCount) 
	{
		this.hdCount = hdCount;
	}

	public Integer getDataCount() 
	{
		return dataCount;
	}

	public void setDataCount(Integer dataCount)
	{
		this.dataCount = dataCount;
	}

	public Integer getSdMultiPicCount()
	{
		return sdMultiPicCount;
	}

	public void setSdMultiPicCount(Integer sdMultiPicCount) 
	{
		this.sdMultiPicCount = sdMultiPicCount;
	}

	public Integer getHdMultiPicCount() 
	{
		return hdMultiPicCount;
	}

	public void setHdMultiPicCount(Integer hdMultiPicCount)
	{
		this.hdMultiPicCount = hdMultiPicCount;
	}

	public Integer getSimulcastSdVideoCount() 
	{
		return simulcastSdVideoCount;
	}

	public void setSimulcastSdVideoCount(Integer simulcastSdVideoCount) 
	{
		this.simulcastSdVideoCount = simulcastSdVideoCount;
	}

	public Integer getSimulcastHdVideoCount() 
	{
		return simulcastHdVideoCount;
	}

	public void setSimulcastHdVideoCount(Integer simulcastHdVideoCount) 
	{
		this.simulcastHdVideoCount = simulcastHdVideoCount;
	}

	public String getUseSimcastVideo() 
	{
		return useSimcastVideo;
	}

	public void setUseSimcastVideo(String useSimcastVideo)
	{
		this.useSimcastVideo = useSimcastVideo;
	}

	public Integer getRecordCapability() 
	{
		return recordCapability;
	}

	public void setRecordCapability(Integer recordCapability) 
	{
		this.recordCapability = recordCapability;
	}

	public Integer getRecordCycleTime()
	{
		return recordCycleTime;
	}

	public void setRecordCycleTime(Integer recordCycleTime)
	{
		this.recordCycleTime = recordCycleTime;
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

	public String getIsLbs() 
	{
		return isLbs;
	}

	public void setIsLbs(String isLbs) 
	{
		this.isLbs = isLbs;
	}

	public String getLocationName() 
	{
		return locationName;
	}

	public void setLocationName(String locationName)
	{
		this.locationName = locationName;
	}

}
