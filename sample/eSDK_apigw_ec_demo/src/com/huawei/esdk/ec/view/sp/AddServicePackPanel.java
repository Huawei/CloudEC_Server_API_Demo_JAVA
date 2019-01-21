package com.huawei.esdk.ec.view.sp;

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

import com.huawei.esdk.ec.bean.common.RestRequest;
import com.huawei.esdk.ec.bean.common.Token;
import com.huawei.esdk.ec.bean.sp.PackTypeEnum;
import com.huawei.esdk.ec.bean.sp.ResourcePack;
import com.huawei.esdk.ec.bean.sp.ResourcePackTypeEnum;
import com.huawei.esdk.ec.bean.sp.ServPack;
import com.huawei.esdk.ec.bean.sp.ServRightEnum;
import com.huawei.esdk.ec.bean.sp.ServRightStatusEnum;
import com.huawei.esdk.ec.constant.HTTPConstant;
import com.huawei.esdk.ec.service.EcService;
import com.huawei.esdk.ec.utils.LoginUtils;
import com.huawei.esdk.ec.utils.Properties_language_Utils;
import com.huawei.esdk.ec.utils.StringUtils;
import com.huawei.esdk.ec.view.common.LogPanel;
import com.huawei.esdk.ec.view.common.MyButton;

/**
 * 业务包管理视图
 * ServPack management view
 * @author wWX534262
 *
 */
@SuppressWarnings("all")
public class AddServicePackPanel extends JPanel implements ActionListener
{
	private static final long serialVersionUID = -127062622711803490L;
	
	private static final Logger LOGGER = LogManager.getLogger(AddServicePackPanel.class);
	
	private JLabel errInfoLabel = new JLabel();
	
	private JLabel reqParams = new JLabel(Properties_language_Utils.getValue("reqParams"));
	
	private JLabel packNameLabel = new JLabel("packName:");
	private JTextField packNameField = new JTextField(10);
	
	private JLabel orgIdLabel = new JLabel("orgId:");
	private JTextField orgIdField = new JTextField(10);
	
	private JLabel packTypeLabel = new JLabel("packType:");
	private JTextField packTypeField = new JTextField(10);
	
	private JLabel usrPermsLabel = new JLabel("usrPerms:");
	private JTextField usrPermsField = new JTextField(10);
	
	private JLabel packDescLabel = new JLabel("packDesc:");
	private JTextField packDescField = new JTextField(10);
	
	private JLabel packRatioLabel = new JLabel("packRatio:");
	private JTextField packRatioField = new JTextField(10);
	
	private JLabel isCustomizeRightsLabel = new JLabel("isCustomizeRights:");
	private JTextField isCustomizeRightsField = new JTextField(10);
	
	//资源包资源信息 ResourcePack
	//Resource Package Resource Information ResourcePack
	private JLabel sdMultiPicCountLabel = new JLabel("sdMultiPicCount:");
	private JTextField sdMultiPicCountField = new JTextField(10);
	
	private JLabel hdMultiPicCountLabel = new JLabel("hdMultiPicCount:");
	private JTextField hdMultiPicCountField = new JTextField(10);
	
	private JLabel useSimulcastVideoLabel = new JLabel("useSimulcastVideo:");
	private JTextField useSimulcastVideoField = new JTextField(10);
	
	private JLabel simulcastSdVideoCountLabel = new JLabel("simulcastSdVideoCount:");
	private JTextField simulcastSdVideoCountField = new JTextField(10);
	
	private JLabel simulcastHdVideoCountLabel = new JLabel("simulcastHdVideoCount:");
	private JTextField simulcastHdVideoCountField = new JTextField(10);
	
	private JLabel audioCountLabel = new JLabel("audioCount:");
	private JTextField audioCountField = new JTextField(10);
	
	private JLabel sdCountLabel = new JLabel("sdCount:");
	private JTextField sdCountField = new JTextField(10);
	
	private JLabel hdCountLabel = new JLabel("hdCount:");
	private JTextField hdCountField = new JTextField(10);
	
	private JLabel dataCountLabel = new JLabel("dataCount:");
	private JTextField dataCountField = new JTextField(10);
	
	private JLabel recordCapabilityLabel = new JLabel("recordCapability:");
	private JTextField recordCapabilityField = new JTextField(10);
	
	private JLabel pinCodeCountLabel = new JLabel("pinCodeCount:");
	private JTextField pinCodeCountField = new JTextField(10);
	
	private JLabel agentCountLabel = new JLabel("agentCount:");
	private JTextField agentCountField = new JTextField(10);
	
	private JLabel ivrCountLabel = new JLabel("ivrCount:");
	private JTextField ivrCountField = new JTextField(10);
	
	private JLabel huntgrpCountLabel = new JLabel("huntgrpCount:");
	private JTextField huntgrpCountField = new JTextField(10);
	
	private JLabel recordCountLabel = new JLabel("recordCount:");
	private JTextField recordCountField = new JTextField(10);
	
	private JLabel curcallCountLabel = new JLabel("curcallCount:");
	private JTextField curcallCountField = new JTextField(10);
	
	private JLabel resPackTypeLabel = new JLabel("resPackType:");
	private JTextField resPackTypeField = new JTextField(10);
	
	private JLabel srvPackModeLabel = new JLabel("srvPackMode:");
	private JTextField srvPackModeField = new JTextField(10);
	
	private JLabel recordCycleTimeLabel = new JLabel("recordCycleTime:");
	private JTextField recordCycleTimeField = new JTextField(10);
	
	private JLabel upathGrpCountLabel = new JLabel("upathGrpCount:");
	private JTextField upathGrpCountField = new JTextField(10);
	
	private JButton addBtn = new MyButton(Properties_language_Utils.getValue("sp.AddServicePackPanel.addBtn"));
	
	private JButton modifyBtn = new MyButton(Properties_language_Utils.getValue("sp.AddServicePackPanel.modifyBtn"));
	
	private JButton cancleBtn = new MyButton(Properties_language_Utils.getValue("sp.AddServicePackPanel.cancleBtn"));
	
	public AddServicePackPanel() 
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
	    
	    buildPanel(panel, gridbag, c, new JComponent[] {packNameLabel}, 0, 2, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {packNameField}, 1, 2, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {orgIdLabel}, 2, 2, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {orgIdField}, 3, 2, 10, 20, 1, 1);
	    
	    buildPanel(panel, gridbag, c, new JComponent[] {packTypeLabel}, 0, 3, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {packTypeField}, 1, 3, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {usrPermsLabel}, 2, 3, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {usrPermsField}, 3, 3, 10, 20, 1, 1);
	    
	    buildPanel(panel, gridbag, c, new JComponent[] {packDescLabel}, 0, 4, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {packDescField}, 1, 4, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {packRatioLabel}, 2, 4, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {packRatioField}, 3, 4, 10, 20, 1, 1);
	    
	    buildPanel(panel, gridbag, c, new JComponent[] {isCustomizeRightsLabel}, 0, 5, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {isCustomizeRightsField}, 1, 5, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {sdMultiPicCountLabel}, 2, 5, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {sdMultiPicCountField}, 3, 5, 10, 20, 1, 1);
	    
	    buildPanel(panel, gridbag, c, new JComponent[] {hdMultiPicCountLabel}, 0, 6, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {hdMultiPicCountField}, 1, 6, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {useSimulcastVideoLabel}, 2, 6, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {useSimulcastVideoField}, 3, 6, 10, 20, 1, 1);
	    
	    buildPanel(panel, gridbag, c, new JComponent[] {simulcastSdVideoCountLabel}, 0, 7, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {simulcastSdVideoCountField}, 1, 7, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {simulcastHdVideoCountLabel}, 2, 7, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {simulcastHdVideoCountField}, 3, 7, 10, 20, 1, 1);
	    
	    buildPanel(panel, gridbag, c, new JComponent[] {audioCountLabel}, 0, 8, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {audioCountField}, 1, 8, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {sdCountLabel}, 2, 8, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {sdCountField}, 3, 8, 10, 20, 1, 1);
	    
	    buildPanel(panel, gridbag, c, new JComponent[] {hdCountLabel}, 0, 9, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {hdCountField}, 1, 9, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {dataCountLabel}, 2, 9, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {dataCountField}, 3, 9, 10, 20, 1, 1);
	    
	    buildPanel(panel, gridbag, c, new JComponent[] {recordCapabilityLabel}, 0, 10, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {recordCapabilityField}, 1, 10, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {pinCodeCountLabel}, 2, 10, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {pinCodeCountField}, 3, 10, 10, 20, 1, 1);
	    
	    buildPanel(panel, gridbag, c, new JComponent[] {agentCountLabel}, 0, 11, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {agentCountField}, 1, 11, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {ivrCountLabel}, 2, 11, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {ivrCountField}, 3, 11, 10, 20, 1, 1);
	    
	    buildPanel(panel, gridbag, c, new JComponent[] {huntgrpCountLabel}, 0, 12, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {huntgrpCountField}, 1, 12, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {recordCountLabel}, 2, 12, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {recordCountField}, 3, 12, 10, 20, 1, 1);
	    
	    buildPanel(panel, gridbag, c, new JComponent[] {curcallCountLabel}, 0, 13, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {curcallCountField}, 1, 13, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {resPackTypeLabel}, 2, 13, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {resPackTypeField}, 3, 13, 10, 20, 1, 1);
	    
	    buildPanel(panel, gridbag, c, new JComponent[] {srvPackModeLabel}, 0, 14, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {srvPackModeField}, 1, 14, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {recordCycleTimeLabel}, 2, 14, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {recordCycleTimeField}, 3, 14, 10, 20, 1, 1);
	    
	    buildPanel(panel, gridbag, c, new JComponent[] {upathGrpCountLabel}, 0, 15, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {upathGrpCountField}, 1, 15, 10, 20, 1, 1);
	    
	    buildPanel(panel, gridbag, c, new JComponent[] {addBtn}, 0, 16, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {modifyBtn}, 1, 16, 10, 20, 2, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {cancleBtn}, 3, 16, 10, 20, 1, 1);
	    cancleBtn.addActionListener(this);
	    buildPanel(panel, gridbag, c, new JComponent[] {errInfoLabel}, 0, 17, 10, 20, 1, 1);
	    
	    //报文位置
		//message location
		buildPanel(panel, gridbag, c, new JComponent[] {new LogPanel()}, 4, 0, 10, 20, 4, 10);
	    
	    return panel;
	}
	
	private void init()
	{
		//增加业务包按钮事件
		//add servpaack button events
		this.addBtn.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
	        {
				showErrInfoWithColor("");
	            
	            boolean flag = validateParams(
	            		new JTextField[] {packNameField},
	            		new String[] {"packName"});
	                
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
						addServicePack();
					}
			    };
			    Future future = Executors.newSingleThreadExecutor().submit(runnable);
			    if(future.isDone()) 
	            {
	            	LOGGER.info("future.isDone() is true");
	            }
	        }
		});
		
		//修改业务包按钮事件
		//modify servpack button events
		this.modifyBtn.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
	        {
				showErrInfoWithColor("");
	            
	            boolean flag = validateParams(
	            		new JTextField[] {packNameField},
	            		new String[] {"packName"});
	                
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
						modifyServicePack();
					}
			    };
			    Future future = Executors.newSingleThreadExecutor().submit(runnable);
			    if(future.isDone()) 
	            {
	            	LOGGER.info("future.isDone() is true");
	            }
	        }
		});
	}
	
	//修改业务包
	//modify servpack
	private void modifyServicePack() 
	{
		try 
		{
			Token token = LoginUtils.getToken();
			
			// 利用token发送restful请求
			// Send a restful request using token
			RestRequest request = new RestRequest(HTTPConstant.HTTP_METHOD_PUT);
			request.setPayload(addServPack());
			EcService.put("/servicepack", request, errInfoLabel, token);
			
			EcService.finish();
		} 
		catch (Exception e) 
		{
			LOGGER.error("get Token error:" + e);
			showErrInfoWithColor(Properties_language_Utils.getValue("resultTip"));
		}
	}
	
	//增加业务包
	//add servpack
	private void addServicePack() 
	{
		try 
		{
			Token token = LoginUtils.getToken();
			
			// 利用token发送restful请求
			// Send a restful request using token
			RestRequest request = new RestRequest(HTTPConstant.HTTP_METHOD_POST);
			request.setPayload(addServPack());
			EcService.post("/servicepack", request, errInfoLabel, token);
			
			EcService.finish();
		} 
		catch (Exception e) 
		{
			LOGGER.error("get Token error:" + e);
			showErrInfoWithColor(Properties_language_Utils.getValue("resultTip"));
		}
		
	}
	
	//设置业务包参数
	//Set servpackage parameters
	private ServPack addServPack() 
	{
		ServPack servPack = new ServPack();
		if (StringUtils.isNotEmpty(packNameField.getText())) 
		{
			servPack.setPackName(packNameField.getText());
		}
		if (StringUtils.isNotEmpty(orgIdField.getText())) 
		{
			servPack.setOrgId(orgIdField.getText());
		}
		if (StringUtils.isNotEmpty(packTypeField.getText())) 
		{
			servPack.setPackType(packTypeField.getText());
		}
		servPack.setResource(addResourcePack());
		
		//个人包权限没设置Map<ServRightEnum,ServRightStatusEnum>
		servPack.setUsrPerms(addUsrPerms());
		
		if (StringUtils.isNotEmpty(packDescField.getText())) 
		{
			servPack.setPackDesc(packDescField.getText());
		}
		if (StringUtils.isNotEmpty(packRatioField.getText())) 
		{
			servPack.setPackRatio(packRatioField.getText());
		}
		if (StringUtils.isNotEmpty(isCustomizeRightsField.getText())) 
		{
			servPack.setIsCustomizeRights(Integer.parseInt(isCustomizeRightsField.getText()));
		}
		
		return servPack;
	}
	
	//设置个人包权限
	private Map<ServRightEnum,ServRightStatusEnum> addUsrPerms()
	{
		Map<ServRightEnum, ServRightStatusEnum> usrPerms = new HashMap<ServRightEnum, ServRightStatusEnum>();
		
		return usrPerms;
		
		
	}
	
	//设置资源包的资源
	//Set resources for resource bundles
	private ResourcePack addResourcePack() 
	{
		ResourcePack resourcePack = new ResourcePack();
		if (StringUtils.isNotEmpty(sdMultiPicCountField.getText())) 
		{
			resourcePack.setSdMultiPicCount(Integer.parseInt(sdMultiPicCountField.getText()));
		}
		if (StringUtils.isNotEmpty(hdMultiPicCountField.getText())) 
		{
			resourcePack.setHdMultiPicCount(Integer.parseInt(hdMultiPicCountField.getText()));
		}
		if (StringUtils.isNotEmpty(useSimulcastVideoField.getText())) 
		{
			resourcePack.setUseSimulcastVideo(Boolean.valueOf(useSimulcastVideoField.getText()));
		}
		if (StringUtils.isNotEmpty(simulcastSdVideoCountField.getText())) 
		{
			resourcePack.setSimulcastSdVideoCount(Integer.parseInt(simulcastSdVideoCountField.getText()));
		}
		if (StringUtils.isNotEmpty(simulcastHdVideoCountField.getText())) 
		{
			resourcePack.setSimulcastHdVideoCount(Integer.parseInt(simulcastHdVideoCountField.getText()));
		}
		if (StringUtils.isNotEmpty(audioCountField.getText())) 
		{
			resourcePack.setAudioCount(Integer.parseInt(audioCountField.getText()));
		}
		if (StringUtils.isNotEmpty(sdCountField.getText())) 
		{
			resourcePack.setSdCount(Integer.parseInt(sdCountField.getText()));
		}
		if (StringUtils.isNotEmpty(hdCountField.getText())) 
		{
			resourcePack.setHdCount(Integer.parseInt(hdCountField.getText()));
		}
		if (StringUtils.isNotEmpty(dataCountField.getText())) 
		{
			resourcePack.setDataCount(Integer.parseInt(dataCountField.getText()));
		}
		if (StringUtils.isNotEmpty(recordCapabilityField.getText())) 
		{
			resourcePack.setRecordCapability(Integer.parseInt(recordCapabilityField.getText()));
		}
		if (StringUtils.isNotEmpty(pinCodeCountField.getText())) 
		{
			resourcePack.setPinCodeCount(Integer.parseInt(pinCodeCountField.getText()));
		}
		if (StringUtils.isNotEmpty(agentCountField.getText())) 
		{
			resourcePack.setAgentCount(Integer.parseInt(agentCountField.getText()));
		}
		if (StringUtils.isNotEmpty(ivrCountField.getText())) 
		{
			resourcePack.setIvrCount(Integer.parseInt(ivrCountField.getText()));
		}
		if (StringUtils.isNotEmpty(huntgrpCountField.getText())) 
		{
			resourcePack.setHuntgrpCount(Integer.parseInt(huntgrpCountField.getText()));
		}
		if (StringUtils.isNotEmpty(recordCountField.getText())) 
		{
			resourcePack.setRecordCount(Integer.parseInt(recordCountField.getText()));
		}
		if (StringUtils.isNotEmpty(curcallCountField.getText())) 
		{
			resourcePack.setCurcallCount(Integer.parseInt(curcallCountField.getText()));
		}
		if (StringUtils.isNotEmpty(resPackTypeField.getText())) 
		{
			resourcePack.setResPackType(resPackTypeField.getText());
		}
		if (StringUtils.isNotEmpty(srvPackModeField.getText())) 
		{
			resourcePack.setSrvPackMode(srvPackModeField.getText());
		}
		if (StringUtils.isNotEmpty(recordCycleTimeField.getText())) 
		{
			resourcePack.setRecordCycleTime(Integer.parseInt(recordCycleTimeField.getText()));
		}
		if (StringUtils.isNotEmpty(upathGrpCountField.getText())) 
		{
			resourcePack.setUpathGrpCount(Integer.parseInt(upathGrpCountField.getText()));
		}
		
		return resourcePack;
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
		// TODO Auto-generated method stub
		packNameField.setText("");
		orgIdField.setText("");
		packTypeField.setText("");
		usrPermsField.setText("");
		packDescField.setText("");
		packRatioField.setText("");
		isCustomizeRightsField.setText("");
		sdMultiPicCountField.setText("");
		hdMultiPicCountField.setText("");
		useSimulcastVideoField.setText("");
		simulcastHdVideoCountField.setText("");
		simulcastSdVideoCountField.setText("");
		audioCountField.setText("");
		sdCountField.setText("");
		hdCountField.setText("");
		dataCountField.setText("");
		recordCapabilityField.setText("");
		pinCodeCountField.setText("");
		agentCountField.setText("");
		ivrCountField.setText("");
		huntgrpCountField.setText("");
		recordCountField.setText("");
		curcallCountField.setText("");
		resPackTypeField.setText("");
		srvPackModeField.setText("");
		recordCycleTimeField.setText("");
		upathGrpCountField.setText("");
		
	}

}
