package com.huawei.esdk.ec.bean.enterprise;

import java.util.Map;

import com.huawei.esdk.ec.bean.sp.RightsEnum;
import com.huawei.esdk.ec.bean.sp.RightsStatusEnum;

/**
 * 增加企业号码请求体参数
 * Increase corp number request body parameters
 * @author wwx534262
 *
 */
public class AddCorpNumBean 
{

	private String number;
	
	private String pwd;
	
	private String didNum;
	
	private String cxType;
	
	private String status;
	
	private String odspMode;
	
	private String outOcxPfx;
	
	private String voipDomain;
	
	private String sipServerGrp;
	
	private String localGateway;
	
	private Integer localGatewaySync;
	
	private Integer userPriority;
	
	private Integer isConfAccessNumber;
	
	private Integer numberType;
	
	private MMTelExtensionBean mmTelExtension;
	
	private Map<RightsEnum,RightsStatusEnum> callRights;
	
	private NumberCallOutRightsVo callOutRights;

	public String getNumber()
	{
		return number;
	}

	public void setNumber(String number) 
	{
		this.number = number;
	}

	public String getPwd() 
	{
		return pwd;
	}

	public void setPwd(String pwd)
	{
		this.pwd = pwd;
	}

	public String getDidNum() 
	{
		return didNum;
	}

	public void setDidNum(String didNum) 
	{
		this.didNum = didNum;
	}

	public String getCxType()
	{
		return cxType;
	}

	public void setCxType(String cxType) 
	{
		this.cxType = cxType;
	}

	public String getStatus() 
	{
		return status;
	}

	public void setStatus(String status) 
	{
		this.status = status;
	}

	public String getOdspMode() 
	{
		return odspMode;
	}

	public void setOdspMode(String odspMode)
	{
		this.odspMode = odspMode;
	}

	public String getOutOcxPfx() 
	{
		return outOcxPfx;
	}

	public void setOutOcxPfx(String outOcxPfx) 
	{
		this.outOcxPfx = outOcxPfx;
	}

	public String getVoipDomain() 
	{
		return voipDomain;
	}

	public void setVoipDomain(String voipDomain)
	{
		this.voipDomain = voipDomain;
	}

	public String getSipServerGrp()
	{
		return sipServerGrp;
	}

	public void setSipServerGrp(String sipServerGrp)
	{
		this.sipServerGrp = sipServerGrp;
	}

	public String getLocalGateway() 
	{
		return localGateway;
	}

	public void setLocalGateway(String localGateway) 
	{
		this.localGateway = localGateway;
	}

	public Integer getLocalGatewaySync()
	{
		return localGatewaySync;
	}

	public void setLocalGatewaySync(Integer localGatewaySync) 
	{
		this.localGatewaySync = localGatewaySync;
	}

	public Integer getUserPriority() 
	{
		return userPriority;
	}

	public void setUserPriority(Integer userPriority) 
	{
		this.userPriority = userPriority;
	}

	public Integer getIsConfAccessNumber()
	{
		return isConfAccessNumber;
	}

	public void setIsConfAccessNumber(Integer isConfAccessNumber) 
	{
		this.isConfAccessNumber = isConfAccessNumber;
	}

	public Integer getNumberType() 
	{
		return numberType;
	}

	public void setNumberType(Integer numberType)
	{
		this.numberType = numberType;
	}

	public MMTelExtensionBean getMmTelExtension() 
	{
		return mmTelExtension;
	}

	public void setMmTelExtension(MMTelExtensionBean mmTelExtension)
	{
		this.mmTelExtension = mmTelExtension;
	}

	public Map<RightsEnum, RightsStatusEnum> getCallRights()
	{
		return callRights;
	}

	public void setCallRights(Map<RightsEnum, RightsStatusEnum> callRights) 
	{
		this.callRights = callRights;
	}

	public NumberCallOutRightsVo getCallOutRights()
	{
		return callOutRights;
	}

	public void setCallOutRights(NumberCallOutRightsVo callOutRights) 
	{
		this.callOutRights = callOutRights;
	}
	
}
