package com.huawei.esdk.ec.bean.sp;
/**
 * 分配服务商号码给企业接口里的号码参数实体
 * Assign the service provider number to the number parameter entity in the enterprise interface
 * @author wwx534262
 *
 */

import java.util.Map;

import com.huawei.esdk.ec.bean.enterprise.MMTelExtensionBean;

public class NumberParamBean 
{
	private String pwd;
	
	private String status;
	
	private String odspMode;
	
	private String outOcxPfx;
	
	private String voipDomain;
	
	private String sipServerGrp;
	
	private String localGateway;
	
	private String localGatewaySync;
	
	private String userPriority;
	
	private MMTelExtensionBean mmTelExtension;
	
	private Map<RightsEnum,RightsStatusEnum> callRights;

	public String getPwd() 
	{
		return pwd;
	}

	public void setPwd(String pwd) 
	{
		this.pwd = pwd;
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

	public String getLocalGatewaySync() 
	{
		return localGatewaySync;
	}

	public void setLocalGatewaySync(String localGatewaySync) 
	{
		this.localGatewaySync = localGatewaySync;
	}

	public String getUserPriority() 
	{
		return userPriority;
	}

	public void setUserPriority(String userPriority) 
	{
		this.userPriority = userPriority;
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
	
}
