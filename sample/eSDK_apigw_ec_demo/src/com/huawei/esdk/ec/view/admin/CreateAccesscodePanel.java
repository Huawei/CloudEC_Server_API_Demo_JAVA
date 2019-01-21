package com.huawei.esdk.ec.view.admin;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.huawei.esdk.ec.bean.admin.AddAccesscodeBean;
import com.huawei.esdk.ec.bean.admin.ConfCodeBasicInfo;
import com.huawei.esdk.ec.bean.admin.NumberBean;
import com.huawei.esdk.ec.bean.common.RestRequest;
import com.huawei.esdk.ec.bean.common.Token;
import com.huawei.esdk.ec.bean.enterprise.MMTelExtensionBean;
import com.huawei.esdk.ec.constant.HTTPConstant;
import com.huawei.esdk.ec.service.EcService;
import com.huawei.esdk.ec.utils.LoginUtils;
import com.huawei.esdk.ec.utils.Properties_language_Utils;
import com.huawei.esdk.ec.utils.StringUtils;
import com.huawei.esdk.ec.view.common.LogPanel;
import com.huawei.esdk.ec.view.common.MyButton;
/**
 * 创建和修改接入号接口视图
 * @author wWX534262
 *
 */
public class CreateAccesscodePanel extends JPanel implements ActionListener
{
	private static final long serialVersionUID = -4312074981985904081L;
	
	private static final Logger LOGGER = LogManager.getLogger(CreateAccesscodePanel.class);

	private JLabel errInfoLabel = new JLabel();
	
	private JLabel reqParams = new JLabel(Properties_language_Utils.getValue("reqParams"));
	
	//ConfCodeBasicInfo参数结构
	private JLabel interPrfxLabel = new JLabel("interPrfx:");
	private JTextField interPrfxField = new JTextField(10);
	
	private JLabel countryCodeLabel = new JLabel("countryCode:");
	private JTextField countryCodeField = new JTextField(10);
	
	private JLabel localPrfxLabel = new JLabel("localPrfx:");
	private JTextField localPrfxField = new JTextField(10);
	
	private JLabel countryRegionLabel = new JLabel("countryRegion:");
	private JTextField countryRegionField = new JTextField(10);
	
	private JLabel accessNumberIDLabel = new JLabel("accessNumberID:");
	private JTextField accessNumberIDField = new JTextField(10);
	
	private JLabel sipFormatAccessNumberIDLabel = new JLabel("sipFormatAccessNumberID:");
	private JTextField sipFormatAccessNumberIDField = new JTextField(10);
	
	private JLabel conferenceIDPrefixLabel = new JLabel("conferenceIDPrefix:");
	private JTextField conferenceIDPrefixField = new JTextField(10);
	
	private JLabel isAutoInviteLabel = new JLabel("isAutoInvite:");
	private JTextField isAutoInviteField = new JTextField(10);
	
	private JLabel conferenceTypeLabel = new JLabel("conferenceType:");
	private JTextField conferenceTypeField = new JTextField(10);
	
	private JLabel authTypeLabel = new JLabel("authType:");
	private JTextField authTypeField = new JTextField(10);
	
	private JLabel confrenceConfigPathLabel = new JLabel("confrenceConfigPath:");
	private JTextField confrenceConfigPathField = new JTextField(10);
	
	private JLabel accNumAlias1Label = new JLabel("accNumAlias1:");
	private JTextField accNumAlias1Field = new JTextField(10);
	
	private JLabel accNumAlias2Label = new JLabel("accNumAlias2:");
	private JTextField accNumAlias2Field = new JTextField(10);
	
	private JLabel accNumAlias3Label = new JLabel("accNumAlias3:");
	private JTextField accNumAlias3Field = new JTextField(10);
	
	private JLabel accNumAlias4Label = new JLabel("accNumAlias4:");
	private JTextField accNumAlias4Field = new JTextField(10);
	
	private JLabel accNumAlias5Label = new JLabel("accNumAlias5:");
	private JTextField accNumAlias5Field = new JTextField(10);
	
	private JLabel callingPartyCategoryTypeLabel = new JLabel("callingPartyCategoryType:");
	private JTextField callingPartyCategoryTypeField = new JTextField(10);
	
	private JLabel callingPartyCategoryValueLabel = new JLabel("callingPartyCategoryValue:");
	private JTextField callingPartyCategoryValueField = new JTextField(10);
	
	private JLabel suffixLenLabel = new JLabel("suffixLen:");
	private JTextField suffixLenField = new JTextField(10);
	
	private JLabel conferenceOnOffModeLabel = new JLabel("conferenceOnOffMode:");
	private JTextField conferenceOnOffModeField = new JTextField(10);
	
	private JLabel ipPrefixLabel = new JLabel("ipPrefix:");
	private JTextField ipPrefixField = new JTextField(10);
	
	private JLabel crjionconfmodeLabel = new JLabel("crjionconfmode:");
	private JTextField crjionconfmodeField = new JTextField(10);
	
	private JLabel confgrpLabel = new JLabel("confgrp:");
	private JTextField confgrpField = new JTextField(10);
	
	private JLabel organizationIDLabel = new JLabel("organizationID:");
	private JTextField organizationIDField = new JTextField(10);
	
	private JLabel isShareLabel = new JLabel("isShare:");
	private JTextField isShareField = new JTextField(10);
	
	private JLabel isWildCardLabel = new JLabel("isWildCard:");
	private JTextField isWildCardField = new JTextField(10);
	
	//NumberBean数据结构
	private JLabel cxTypeLabel = new JLabel("cxType:");
	private JTextField cxTypeField = new JTextField(10);
	
	private JLabel userPriorityLabel = new JLabel("userPriority:");
	private JTextField userPriorityField = new JTextField(10);
	
	//NumberBean中的呼叫权限(rightsEnum,rightsStatusEnum)
	private JLabel rightsEnumLabel = new JLabel("RightsEnum:");
	private JTextField rightsEnumField = new JTextField(10);
	
	private JLabel rightsStatusEnumLabel = new JLabel("RightsStatusEnum:");
	private JTextField rightsStatusEnumField = new JTextField(10);
	
	//NumberBean中用户扩展业务数据MMTelExtensionBean(callSrcCode,iupSubscribe)
	private JLabel callSrcCodeLabel = new JLabel("callSrcCode:");
	private JTextField callSrcCodeField = new JTextField(10);
	
	private JLabel iupSubscribeLabel = new JLabel("iupSubscribe:");
	private JTextField iupSubscribeField = new JTextField(10);
	
	
	//设置呼叫权限按钮
	private JButton setCallPermissionBtn = new MyButton(Properties_language_Utils.
			getValue("admin.CreateAccesscodePanel.setCallPermissionBtn"),170,35);
	
	//创建接入号按钮
	private JButton createAccessNumberBtn = new MyButton(Properties_language_Utils.
			getValue("admin.CreateAccesscodePanel.createAccessNumberBtn"), 170, 35);
	
	//修改接入号按钮
	private JButton modifyAccessNumberBtn = new MyButton(Properties_language_Utils.
			getValue("admin.CreateAccesscodePanel.modifyAccessNumberBtn"), 170, 35);
	
	//清空按钮
	private JButton emptyBtn = new MyButton(Properties_language_Utils.
			getValue("admin.CreateAccesscodePanel.emptyBtn"));
	
	private Map<String,String> callRights = new HashMap<>();
	
	//标记设置呼叫权限的次数
	private int i = 0;
	
	public CreateAccesscodePanel() 
	{
		add(getPanels());
	    init();
	}
	
	private JPanel getPanels()
	{
		GridBagLayout gridbag = new GridBagLayout();
	    GridBagConstraints c = new GridBagConstraints();
	    JPanel panel = new JPanel(gridbag);
	    
	    buildPanel(panel, gridbag, c, new JComponent[] {reqParams}, 0, 0, 10, 40, 1, 1);  
	    
	    buildPanel(panel, gridbag, c, new JComponent[] {interPrfxLabel}, 0, 1, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {interPrfxField}, 1, 1, 10, 20, 2, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {countryCodeLabel}, 2, 1, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {countryCodeField}, 3, 1, 6, 20, 2, 1);
	    
	    buildPanel(panel, gridbag, c, new JComponent[] {localPrfxLabel}, 0, 2, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {localPrfxField}, 1, 2, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {countryRegionLabel}, 2, 2, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {countryRegionField}, 3, 2, 10, 20, 1, 1);
	    
	    buildPanel(panel, gridbag, c, new JComponent[] {accessNumberIDLabel}, 0, 3, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {accessNumberIDField}, 1, 3, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {sipFormatAccessNumberIDLabel}, 2, 3, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {sipFormatAccessNumberIDField}, 3, 3, 10, 20, 1, 1);
	    
	    buildPanel(panel, gridbag, c, new JComponent[] {conferenceIDPrefixLabel}, 0, 4, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {conferenceIDPrefixField}, 1, 4, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {isAutoInviteLabel}, 2, 4, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {isAutoInviteField}, 3, 4, 10, 20, 1, 1);
	    
	    buildPanel(panel, gridbag, c, new JComponent[] {conferenceTypeLabel}, 0, 5, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {conferenceTypeField}, 1, 5, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {authTypeLabel}, 2, 5, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {authTypeField}, 3, 5, 10, 20, 1, 1);
	    
	    buildPanel(panel, gridbag, c, new JComponent[] {confrenceConfigPathLabel}, 0, 6, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {confrenceConfigPathField}, 1, 6, 10, 20, 2, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {accNumAlias1Label}, 2, 6, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {accNumAlias1Field}, 3, 6, 6, 20, 2, 1);
	    
	    buildPanel(panel, gridbag, c, new JComponent[] {accNumAlias2Label}, 0, 7, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {accNumAlias2Field}, 1, 7, 10, 20, 2, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {accNumAlias3Label}, 2, 7, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {accNumAlias3Field}, 3, 7, 6, 20, 2, 1);
	    
	    buildPanel(panel, gridbag, c, new JComponent[] {accNumAlias4Label}, 0, 8, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {accNumAlias4Field}, 1, 8, 10, 20, 2, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {accNumAlias5Label}, 2, 8, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {accNumAlias5Field}, 3, 8, 6, 20, 2, 1);
	    
	    buildPanel(panel, gridbag, c, new JComponent[] {callingPartyCategoryTypeLabel}, 0, 9, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {callingPartyCategoryTypeField}, 1, 9, 10, 20, 2, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {callingPartyCategoryValueLabel}, 2, 9, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {callingPartyCategoryValueField}, 3, 9, 6, 20, 2, 1);
	    
	    buildPanel(panel, gridbag, c, new JComponent[] {suffixLenLabel}, 0, 10, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {suffixLenField}, 1, 10, 10, 20, 2, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {conferenceOnOffModeLabel}, 2, 10, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {conferenceOnOffModeField}, 3, 10, 6, 20, 2, 1);
	    
	    buildPanel(panel, gridbag, c, new JComponent[] {ipPrefixLabel}, 0, 11, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {ipPrefixField}, 1, 11, 10, 20, 2, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {crjionconfmodeLabel}, 2, 11, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {crjionconfmodeField}, 3, 11, 6, 20, 2, 1);
	    
	    buildPanel(panel, gridbag, c, new JComponent[] {confgrpLabel}, 0, 12, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {confgrpField}, 1, 12, 10, 20, 2, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {organizationIDLabel}, 2, 12, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {organizationIDField}, 3, 12, 6, 20, 2, 1);
	    
	    buildPanel(panel, gridbag, c, new JComponent[] {isShareLabel}, 0, 13, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {isShareField}, 1, 13, 10, 20, 2, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {isWildCardLabel}, 2, 13, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {isWildCardField}, 3, 13, 6, 20, 2, 1);
	    
	    //NumberBean数据结构相对位置
	    buildPanel(panel, gridbag, c, new JComponent[] {cxTypeLabel}, 0, 14, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {cxTypeField}, 1, 14, 10, 20, 2, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {userPriorityLabel}, 2, 14, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {userPriorityField}, 3, 14, 6, 20, 2, 1);
	    
	    buildPanel(panel, gridbag, c, new JComponent[] {rightsEnumLabel}, 0, 15, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {rightsEnumField}, 1, 15, 10, 20, 2, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {rightsStatusEnumLabel}, 2, 15, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {rightsStatusEnumField}, 3, 15, 6, 20, 2, 1);
	    
	    buildPanel(panel, gridbag, c, new JComponent[] {callSrcCodeLabel}, 0, 16, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {callSrcCodeField}, 1, 16, 10, 20, 2, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {iupSubscribeLabel}, 2, 16, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {iupSubscribeField}, 3, 16, 6, 20, 2, 1);
	    
	    buildPanel(panel, gridbag, c, new JComponent[] {setCallPermissionBtn}, 0, 20, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {createAccessNumberBtn}, 0, 21, 10, 20, 2, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {modifyAccessNumberBtn}, 2, 21, 10, 20, 2, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {emptyBtn}, 2, 20, 10, 20, 2, 1);
	    emptyBtn.addActionListener(this);
	    
	    buildPanel(panel, gridbag, c, new JComponent[] {errInfoLabel}, 0, 22, 100, 5, 4, 1);
	    
	    //报文位置
	    //message location
		buildPanel(panel, gridbag, c, new JComponent[] {new LogPanel()}, 4, 0, 10, 20, 4, 10);
		return panel;
	}
	
	private void init()
	{
		
		//点击设置权限呼叫按钮事件
		//Click Set Permission Call Button Event
		this.setCallPermissionBtn.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
	        {
				showErrInfoWithColor("");
	            
	            boolean flag = validateParams(
	            		new JTextField[] {rightsEnumField, rightsStatusEnumField},
	            		new String[] {"RightsEnum", "RightsStatusEnum"});
	                
	            if (!flag)
	            {
	            	return;
	            }
	            
	        	Runnable runnable = new Runnable()
				{
            		@Override
            		public void run()
            		{
            			callRights.put(rightsEnumField.getText(), 
            					rightsStatusEnumField.getText());
            			i++;
            			showErrInfoWithColor(Properties_language_Utils.getValue("admin.CreateAccesscodePanel.setTip")+ i);
            		}
				};
				Future<?> future = Executors.newSingleThreadExecutor().submit(runnable);
				if(future.isDone()) 
	            {
	            	LOGGER.info("future.isDone() is true");
	            }
	        }
		});
		
		//点击创建接入号按钮事件
		this.createAccessNumberBtn.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
	        {
				showErrInfoWithColor("");
	            
	            boolean flag = validateParams(
	            		new JTextField[] {accessNumberIDField, conferenceTypeField, authTypeField, 
	            				confrenceConfigPathField, cxTypeField, callSrcCodeField},
	            		new String[] {"accessNumberID", "conferenceType", "authType", "confrenceConfigPath", 
	            				"cxType", "callSrcCode"});
	                
	            if (!flag)
	            {
	            	return;
	            }
	            EcService.begin();
	            EcService.loading(errInfoLabel);
	            
	            Runnable runnable = new Runnable()
	            {
	            	@Override
	            	public void run()
	            	{
	            		createAccessNumber();
	            	}
	            };
				Future<?> future = Executors.newSingleThreadExecutor().submit(runnable);
				if(future.isDone()) 
	            {
	            	LOGGER.info("future.isDone() is true");
	            }
	        }
		});
		
		//点击修改接入号按钮事件
		this.modifyAccessNumberBtn.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
	        {
				showErrInfoWithColor("");
	            
	            boolean flag = validateParams(
	            		new JTextField[] {accessNumberIDField, conferenceTypeField, authTypeField, 
	            				confrenceConfigPathField},
	            		new String[] {"accessNumberID", "conferenceType", "authType", "confrenceConfigPath"});
	                
	            if (!flag)
	            {
	            	return;
	            }
	            EcService.begin();
	            EcService.loading(errInfoLabel);
	            
	            Runnable runnable = new Runnable()
	            {
	            	@Override
	            	public void run()
	            	{
	            		modifyAccessNumber();
	            	}
	            };
				Future<?> future = Executors.newSingleThreadExecutor().submit(runnable);
				if(future.isDone()) 
	            {
	            	LOGGER.info("future.isDone() is true");
	            }
	        }
		});
	}
	
	/**
	 * 修改接入号
	 */
	private void modifyAccessNumber() 
	{
		Token token = LoginUtils.getToken();
		
		RestRequest request = new RestRequest(HTTPConstant.HTTP_METHOD_PUT);
		
		request.setPayload(addAccesscodeBean());
		
		EcService.put("/conference/accesscode", request, errInfoLabel, token);
		
		callRights.clear();
		
		EcService.finish();	
	}
	
	/**
	 * 创建接入号
	 */
	private void createAccessNumber() 
	{
		Token token = LoginUtils.getToken();
		
		RestRequest request = new RestRequest(HTTPConstant.HTTP_METHOD_POST);
		
		request.setPayload(addAccesscodeBean());
		
		EcService.post("/conference/accesscode", request, errInfoLabel, token);
		
		callRights.clear();
		
		EcService.finish();
		
	}
	
	/**
	 * 设置创建/修改接入号参数
	 * @return
	 */
	private AddAccesscodeBean addAccesscodeBean() 
	{
		AddAccesscodeBean addAccesscodeBean = new AddAccesscodeBean();
		addAccesscodeBean.setConfcodeInfo(addConfCodeBasicInfo());
		addAccesscodeBean.setNumberInfo(addNumberBean());
		
		return addAccesscodeBean;
	}
	
	/**
	 * 设置会议接入号会议相关基础信息参数
	 * @return
	 */
	private ConfCodeBasicInfo addConfCodeBasicInfo() 
	{
		ConfCodeBasicInfo confCodeBasicInfo = new ConfCodeBasicInfo();
		if (StringUtils.isNotEmpty(interPrfxField.getText())) 
		{
			confCodeBasicInfo.setInterPrfx(interPrfxField.getText());
		}
		if (StringUtils.isNotEmpty(countryCodeField.getText())) 
		{
			confCodeBasicInfo.setCountryCode(countryCodeField.getText());
		}
		if (StringUtils.isNotEmpty(localPrfxField.getText())) 
		{
			confCodeBasicInfo.setLocalPrfx(localPrfxField.getText());
		}
		if (StringUtils.isNotEmpty(countryRegionField.getText())) 
		{
			confCodeBasicInfo.setCountryRegion(countryRegionField.getText());
		}
		confCodeBasicInfo.setAccessNumberID(accessNumberIDField.getText());
		
		if (StringUtils.isNotEmpty(sipFormatAccessNumberIDField.getText())) 
		{
			confCodeBasicInfo.setSipFormatAccessNumberID(sipFormatAccessNumberIDField.getText());
		}
		if (StringUtils.isNotEmpty(conferenceIDPrefixField.getText())) 
		{
			confCodeBasicInfo.setConferenceIDPrefix(conferenceIDPrefixField.getText());
		}
		confCodeBasicInfo.setConferenceType(conferenceTypeField.getText());
		
		confCodeBasicInfo.setAuthType(Integer.parseInt(authTypeField.getText()));
		
		confCodeBasicInfo.setConfrenceConfigPath(confrenceConfigPathField.getText());
		
		if (StringUtils.isNotEmpty(accNumAlias1Field.getText())) 
		{
			confCodeBasicInfo.setAccNumAlias1(accNumAlias1Field.getText());
		}
		if (StringUtils.isNotEmpty(accNumAlias2Field.getText())) 
		{
			confCodeBasicInfo.setAccNumAlias2(accNumAlias2Field.getText());
		}
		if (StringUtils.isNotEmpty(accNumAlias3Field.getText())) 
		{
			confCodeBasicInfo.setAccNumAlias3(accNumAlias3Field.getText());
		}
		if (StringUtils.isNotEmpty(accNumAlias4Field.getText())) 
		{
			confCodeBasicInfo.setAccNumAlias4(accNumAlias4Field.getText());
		}
		if (StringUtils.isNotEmpty(accNumAlias5Field.getText())) 
		{
			confCodeBasicInfo.setAccNumAlias5(accNumAlias5Field.getText());
		}
		if (StringUtils.isNotEmpty(callingPartyCategoryTypeField.getText())) 
		{
			confCodeBasicInfo.setCallingPartyCategoryType(callingPartyCategoryTypeField.getText());
		}
		if (StringUtils.isNotEmpty(callingPartyCategoryValueField.getText())) 
		{
			confCodeBasicInfo.setCallingPartyCategoryValue(callingPartyCategoryValueField.getText());
		}
		if (StringUtils.isNotEmpty(suffixLenField.getText())) 
		{
			confCodeBasicInfo.setSuffixLen(Integer.parseInt(suffixLenField.getText()));
		}
		if (StringUtils.isNotEmpty(conferenceOnOffModeField.getText())) 
		{
			confCodeBasicInfo.setConferenceOnOffMode(conferenceOnOffModeField.getText());
		}
		if (StringUtils.isNotEmpty(ipPrefixField.getText())) 
		{
			confCodeBasicInfo.setIpPrefix(ipPrefixField.getText());
		}
		if (StringUtils.isNotEmpty(crjionconfmodeField.getText())) 
		{
			confCodeBasicInfo.setCrjionconfmode(Integer.parseInt(crjionconfmodeField.getText()));
		}
		if (StringUtils.isNotEmpty(confgrpField.getText())) 
		{
			confCodeBasicInfo.setConfgrp(confgrpField.getText());
		}
		if (StringUtils.isNotEmpty(organizationIDField.getText())) 
		{
			confCodeBasicInfo.setOrganizationID(organizationIDField.getText());
		}
		if (StringUtils.isNotEmpty(isShareField.getText())) 
		{
			confCodeBasicInfo.setIsShare(Integer.parseInt(isShareField.getText()));
		}
		if (StringUtils.isNotEmpty(isWildCardField.getText())) 
		{
			confCodeBasicInfo.setIsWildCard(Integer.parseInt(isWildCardField.getText()));
		}
		
		return confCodeBasicInfo;
	}
	
	/**
	 * 设置 接入号号码相关基础信息参数
	 * @return
	 */
	private NumberBean addNumberBean() 
	{
		NumberBean numberBean = new NumberBean();
		numberBean.setCxType(cxTypeField.getText());
		if (StringUtils.isNotEmpty(userPriorityField.getText()))
		{
			numberBean.setUserPriority(Integer.parseInt(userPriorityField.getText()));
		}
		
		MMTelExtensionBean mmTelExtensionBean = new MMTelExtensionBean();
		mmTelExtensionBean.setCallSrcCode(Integer.parseInt(callSrcCodeField.getText()));
		if (StringUtils.isNotEmpty(iupSubscribeField.getText())) 
		{
			mmTelExtensionBean.setIupSubscribe(Boolean.parseBoolean(iupSubscribeField.getText()));
		}
		
		numberBean.setMmTelExtension(mmTelExtensionBean);
		numberBean.setCallRights(callRights);
		//未设置前转呼出参数，直接赋值null
		numberBean.setCallOutRights(null);
		
		return numberBean;
	}
	
	
	private void buildPanel(JPanel panel, GridBagLayout gridbag, GridBagConstraints c, JComponent[] components,
	        int gridx, int gridy, int ipadx, int ipady, int gridwidth, int gridheight)
	{
		JPanel subPanel = new JPanel();
 
		for (JComponent component : components)
		{
			component.setFont(new Font("宋体", Font.CENTER_BASELINE, 18));
			subPanel.add(component);
		}
 
		c.fill = GridBagConstraints.VERTICAL;
		c.anchor = GridBagConstraints.WEST;
		c.gridx = gridx;
		c.gridy = gridy;
		c.ipadx = ipadx;
		c.ipady = ipady;
		c.gridwidth = gridwidth;
		c.gridheight = gridheight;
		gridbag.setConstraints(subPanel, c);
		panel.add(subPanel);
	        
	}
 
	/**
	 * 判断参数为空性
	 * Judgment parameter is empty
	 * @param textFields
	 * @param errTexts
	 * @return
	 */
	private boolean validateParams(JTextField[] textFields, String[] errTexts)
	{
		for (int i = 0; i < textFields.length; i++)
		{
			if (null == textFields[i] || null == textFields[i].getText() || "".equals(textFields[i].getText().trim()))
			{
				showErrInfoWithColor(errTexts[i] + Properties_language_Utils.getValue("isNotEmpty"));
				return false;
			}
		}
	
		return true;
	}
	 
	private void showErrInfoWithColor(String errInfo)
	{
		if (null == errInfo) 
		{
			return;
		}
	        
		errInfoLabel.setForeground(Color.red);
		errInfoLabel.setText(errInfo);
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		interPrfxField.setText("");
		countryCodeField.setText("");
		localPrfxField.setText("");
		countryRegionField.setText("");
		accessNumberIDField.setText("");
		sipFormatAccessNumberIDField.setText("");
		conferenceIDPrefixField.setText("");
		isAutoInviteField.setText("");
		conferenceTypeField.setText("");
		authTypeField.setText("");
		confrenceConfigPathField.setText("");
		accNumAlias1Field.setText("");
		accNumAlias2Field.setText("");
		accNumAlias3Field.setText("");
		accNumAlias4Field.setText("");
		accNumAlias5Field.setText("");
		callingPartyCategoryTypeField.setText("");
		callingPartyCategoryValueField.setText("");
		suffixLenField.setText("");
		conferenceOnOffModeField.setText("");
		ipPrefixField.setText("");
		crjionconfmodeField.setText("");
		confgrpField.setText("");
		organizationIDField.setText("");
		isShareField.setText("");
		isWildCardField.setText("");
		cxTypeField.setText("");
		userPriorityField.setText("");
		rightsEnumField.setText("");
		rightsStatusEnumField.setText("");
		callSrcCodeField.setText("");
		iupSubscribeField.setText("");
	}
	
}
