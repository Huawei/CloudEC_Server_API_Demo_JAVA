package com.huawei.esdk.ec.view.enterprise;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
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
import com.huawei.esdk.ec.bean.enterprise.AddDeviceBean;
import com.huawei.esdk.ec.bean.enterprise.IpccLineInfo;
import com.huawei.esdk.ec.constant.HTTPConstant;
import com.huawei.esdk.ec.service.EcService;
import com.huawei.esdk.ec.utils.LoginUtils;
import com.huawei.esdk.ec.utils.Properties_language_Utils;
import com.huawei.esdk.ec.utils.StringUtils;
import com.huawei.esdk.ec.view.common.LogPanel;
import com.huawei.esdk.ec.view.common.MyButton;
/**
 * 增加设备视图
 * @author wWX534262
 *
 */
@SuppressWarnings("all")
public class AddDevicePanel extends JPanel implements ActionListener 
{

	private static final long serialVersionUID = -4674341297452413752L;
	
	private static final Logger LOGGER = LogManager.getLogger(AddDevicePanel.class);
	
	private JLabel errInfoLabel = new JLabel();
	
	private JLabel tipInfoLabel = new JLabel(Properties_language_Utils.
			getValue("enter.AddDevicePanel.tipInfoLabel"));
	
	private JLabel reqParams = new JLabel(Properties_language_Utils.getValue("reqParams"));
	
	private JLabel deviceIdLabel = new JLabel("deviceId:");
	private JTextField deviceIdField = new JTextField(10);
	
	private JLabel deviceIdTypeLabel = new JLabel("deviceIdType:");
	private JTextField deviceIdTypeField = new JTextField(10);
	
	private JLabel deviceTypeLabel = new JLabel("deviceType:");
	private JTextField deviceTypeField = new JTextField(10);
	
	private JLabel deviceModelLabel = new JLabel("deviceModel:");
	private JTextField deviceModelField = new JTextField(10);
	
	private JLabel corpIdLabel = new JLabel("corpId:");
	private JTextField corpIdField = new JTextField(10);
	
	private JLabel deviceSrcLabel = new JLabel("deviceSrc:");
	private JTextField deviceSrcField = new JTextField(10);
	
	private JLabel orgIdLabel = new JLabel("orgId:");
	private JTextField orgIdField = new JTextField(10);
	
	private JLabel deviceStatusLabel = new JLabel("deviceStatus:");
	private JTextField deviceStatusField = new JTextField(10);
	
	private JLabel tmsManagedLabel = new JLabel("tmsManaged:");
	private JTextField tmsManagedField = new JTextField(10);
	
	private JLabel ipCheckFlagLabel = new JLabel("ipCheckFlag:");
	private JTextField ipCheckFlagField = new JTextField(10);
	
	private JLabel suppExtMobLabel = new JLabel("suppExtMob:");
	private JTextField suppExtMobField = new JTextField(10);
	
	private JLabel siteNameLabel = new JLabel("siteName:");
	private JTextField siteNameField = new JTextField(10);
	
	private JLabel deviceLocationLabel = new JLabel("deviceLocation:");
	private JTextField deviceLocationField = new JTextField(10);
	
	private JLabel descriptionLabel = new JLabel("description:");
	private JTextField descriptionField = new JTextField(10);
	
	private JLabel lineLstLabel = new JLabel("lineLst:");
	private JTextField lineLstField = new JTextField(10);
	
	private JLabel numberLabel = new JLabel("number:");
	private JTextField numberField = new JTextField(10);
	
	private JLabel ccLineBtnIndexLabel = new JLabel("ccLineBtnIndex:");
	private JTextField ccLineBtnIndexField = new JTextField(10);
	
	private JLabel commonLineBtnIndexLabel = new JLabel("commonLineBtnIndex:");
	private JTextField commonLineBtnIndexField = new JTextField(10);
	
	private JButton addDeviceBtn = new MyButton(Properties_language_Utils.
			getValue("enter.AddDevicePanel.addDeviceBtn"));
	
	private JButton modifyDeviceBtn = new MyButton(Properties_language_Utils.
			getValue("enter.AddDevicePanel.modifyDeviceBtn"));
	
	private JButton cancleBtn = new MyButton(Properties_language_Utils.
			getValue("enter.AddDevicePanel.cancleBtn"));
	
	
	public AddDevicePanel() 
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
	    buildPanel(panel, gridbag, c, new JComponent[] {deviceIdLabel}, 0, 1, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {deviceIdField}, 1, 1, 10, 20, 2, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {deviceIdTypeLabel}, 2, 1, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {deviceIdTypeField}, 3, 1, 6, 20, 2, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {deviceTypeLabel}, 0, 2, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {deviceTypeField}, 1, 2, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {deviceModelLabel}, 2, 2, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {deviceModelField}, 3, 2, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {corpIdLabel}, 0, 3, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {corpIdField}, 1, 3, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {deviceSrcLabel}, 2, 3, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {deviceSrcField}, 3, 3, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {orgIdLabel}, 0, 4, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {orgIdField}, 1, 4, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {deviceStatusLabel}, 2, 4, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {deviceStatusField}, 3, 4, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {tmsManagedLabel}, 0, 5, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {tmsManagedField}, 1, 5, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {ipCheckFlagLabel}, 2, 5, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {ipCheckFlagField}, 3, 5, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {suppExtMobLabel}, 0, 6, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {suppExtMobField}, 1, 6, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {siteNameLabel}, 2, 6, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {siteNameField}, 3, 6, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {deviceLocationLabel}, 0, 7, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {deviceLocationField}, 1, 7, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {descriptionLabel}, 2, 7, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {descriptionField}, 3, 7, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {lineLstLabel}, 0, 8, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {lineLstField}, 1, 8, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {numberLabel}, 2, 8, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {numberField}, 3, 8, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {ccLineBtnIndexLabel}, 0, 9, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {ccLineBtnIndexField}, 1, 9, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {commonLineBtnIndexLabel}, 2, 9, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {commonLineBtnIndexField}, 3, 9, 10, 20, 1, 1);
	  
	    buildPanel(panel, gridbag, c, new JComponent[] {addDeviceBtn}, 1, 10, 10, 20, 2, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {modifyDeviceBtn}, 2, 10, 10, 20, 2, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {cancleBtn}, 3, 10, 10, 20, 2, 1);
	    cancleBtn.addActionListener(this);
	    
	    buildPanel(panel, gridbag, c, new JComponent[] {errInfoLabel}, 1, 11, 100, 5, 4, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {tipInfoLabel}, 1, 12, 100, 5, 4, 1);
	    
        //报文位置
        //message location
        buildPanel(panel, gridbag, c, new JComponent[] {new LogPanel()}, 4, 0, 10, 20, 4, 10);
	    
	    return panel;
	}
	

	private void init() 
	{
		
		tipInfoLabel.setForeground(Color.blue);
		
		//修改设备按钮事件
		//Modify device button events
		this.modifyDeviceBtn.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
			{
				showErrInfoWithColor("");
			            
			    boolean flag = validateParams(
			    		new JTextField[] {deviceIdField},
			    		new String[] {"deviceId"});
			                
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
	            		modifyDevice();
	            	}
	            };
	            Future future = Executors.newSingleThreadExecutor().submit(runnable);
	            if(future.isDone()) 
	            {
	            	LOGGER.info("future.isDone() is true");
	            }
			}
		});
		
		//增加设备按钮事件
		//Add device button events
		this.addDeviceBtn.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
			{
				showErrInfoWithColor("");
			            
			    boolean flag = validateParams(
			    		new JTextField[] {deviceIdField, deviceIdTypeField, deviceTypeField, deviceModelField, deviceSrcField},
			    		new String[] {"deviceId", "deviceIdType", "deviceType", "deviceModel", "deviceSrc"});
			                
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
	            		addDevice();
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
	
	//修改设备
	//modify device
	private void modifyDevice() 
	{
		try 
		{
			Token token = LoginUtils.getToken();
			
			RestRequest request = new RestRequest(HTTPConstant.HTTP_METHOD_PUT);
			
			//封装参数
			//Package parameters
			AddDeviceBean addDeviceBean = new AddDeviceBean();
			addDeviceBean.setDeviceId(deviceIdField.getText());
			if (StringUtils.isNotEmpty(corpIdField.getText())) 
			{
				addDeviceBean.setCorpId(corpIdField.getText());
			}
			if (StringUtils.isNotEmpty(deviceStatusField.getText())) 
			{
				addDeviceBean.setDeviceStatus(Integer.parseInt(deviceStatusField.getText()));
			}
			if (StringUtils.isNotEmpty(ipCheckFlagField.getText())) 
			{
				addDeviceBean.setIpCheckFlag(Integer.parseInt(ipCheckFlagField.getText()));
			}
			if (StringUtils.isNotEmpty(suppExtMobField.getText())) 
			{
				addDeviceBean.setSuppExtMob(Integer.parseInt(suppExtMobField.getText()));
			}
			if (StringUtils.isNotEmpty(deviceLocationField.getText())) 
			{
				addDeviceBean.setDeviceLocation(deviceLocationField.getText());
			}
			if (StringUtils.isNotEmpty(descriptionField.getText())) 
			{
				addDeviceBean.setDescription(descriptionField.getText());
			}
			if (StringUtils.isNotEmpty(siteNameField.getText())) 
			{
				addDeviceBean.setSiteName(siteNameField.getText());
			}
			if (StringUtils.isNotEmpty(lineLstField.getText())) 
			{
				String[] lines = lineLstField.getText().split(",");
				List<String> lineLst = new ArrayList<>();
				for (String line : lines) {
					lineLst.add(line);
				}
				addDeviceBean.setLineLst(lineLst);
			}
			addDeviceBean.setIpccLineInfo(addIpccLineInfo());
			request.setPayload(addDeviceBean);
			EcService.put("/device", request, errInfoLabel, token);
			
			EcService.finish();
		} 
		catch (RuntimeException e) 
		{
			showErrInfoWithColor("操作失败");
			LOGGER.error("get Token error:" + e);
		}
		catch (Exception e) 
		{
			showErrInfoWithColor(Properties_language_Utils.getValue("resultTip"));
			LOGGER.error("get Token error:" + e);
		}
	}
	
	//增加设备
	//add device
	private void addDevice() 
	{
		try 
		{
			Token token = LoginUtils.getToken();
			
			RestRequest request = new RestRequest(HTTPConstant.HTTP_METHOD_POST);
			
			AddDeviceBean addDeviceBean = new AddDeviceBean();
			addDeviceBean.setDeviceId(deviceIdField.getText());
			addDeviceBean.setDeviceIdType(Integer.parseInt(deviceIdTypeField.getText()));
			addDeviceBean.setDeviceType(Integer.parseInt(deviceTypeField.getText()));
			addDeviceBean.setDeviceModel(deviceModelField.getText());
			addDeviceBean.setDeviceSrc(Integer.parseInt(deviceSrcField.getText()));
			if (StringUtils.isNotEmpty(corpIdField.getText())) 
			{
				addDeviceBean.setCorpId(corpIdField.getText());
			}
			if (StringUtils.isNotEmpty(orgIdField.getText()))
			{
				addDeviceBean.setOrgId(orgIdField.getText());
			}
			if (StringUtils.isNotEmpty(deviceStatusField.getText()))
			{
				addDeviceBean.setDeviceStatus(Integer.parseInt(deviceStatusField.getText()));
			}
			if (StringUtils.isNotEmpty(tmsManagedField.getText()))
			{
				addDeviceBean.setTmsManaged(Integer.parseInt(tmsManagedField.getText()));
			}
			if (StringUtils.isNotEmpty(ipCheckFlagField.getText()))
			{
				addDeviceBean.setIpCheckFlag(Integer.parseInt(ipCheckFlagField.getText()));
			}
			if (StringUtils.isNotEmpty(suppExtMobField.getText()))
			{
				addDeviceBean.setSuppExtMob(Integer.parseInt(suppExtMobField.getText()));
			}
			if (StringUtils.isNotEmpty(siteNameField.getText()))
			{
				addDeviceBean.setSiteName(siteNameField.getText());
			}
			if (StringUtils.isNotEmpty(deviceLocationField.getText()))
			{
				addDeviceBean.setDeviceLocation(deviceLocationField.getText());
			}
			if (StringUtils.isNotEmpty(descriptionField.getText()))
			{
				addDeviceBean.setDescription(descriptionField.getText());
			}
			if (StringUtils.isNotEmpty(lineLstField.getText()))
			{
				String[] lines = lineLstField.getText().split(",");
				List<String> lineLst = new ArrayList<>();
				for (String line : lines) {
					lineLst.add(line);
				}
				addDeviceBean.setLineLst(lineLst);
			}
			
			addDeviceBean.setIpccLineInfo(addIpccLineInfo());
			request.setPayload(addDeviceBean);
			EcService.post("/device", request, errInfoLabel, token);
			
			EcService.finish();
		}
		catch (RuntimeException e) 
		{
			showErrInfoWithColor("操作失败");
			LOGGER.error("get Token error:" + e);
		}
		catch (Exception e) 
		{
			showErrInfoWithColor(Properties_language_Utils.getValue("resultTip"));
			LOGGER.error("get Token error:" + e);
		}
	}
	
	private IpccLineInfo addIpccLineInfo() 
	{
		IpccLineInfo ipccLineInfo = new IpccLineInfo();
		if (StringUtils.isNotEmpty(numberField.getText())) 
		{
			ipccLineInfo.setNumber(numberField.getText());
		}
		if (StringUtils.isNotEmpty(ccLineBtnIndexField.getText())) 
		{
			ipccLineInfo.setCcLineBtnIndex(Integer.parseInt(ccLineBtnIndexField.getText()));
		}
		if (StringUtils.isNotEmpty(commonLineBtnIndexField.getText())) 
		{
			ipccLineInfo.setCommonLineBtnIndex(Integer.parseInt(commonLineBtnIndexField.getText()));
		}
		return ipccLineInfo;
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
	
	private void showErrInfoWithColor(String errInfo)
    {
		if (null == errInfo) 
		{
			return;
		}
	        
        errInfoLabel.setForeground(Color.red);
        errInfoLabel.setText(errInfo);
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

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		deviceIdField.setText("");
		deviceIdTypeField.setText("");
		deviceTypeField.setText("");
		deviceModelField.setText("");
		corpIdField.setText("");
		deviceSrcField.setText("");
		orgIdField.setText("");
		deviceStatusField.setText("");
		tmsManagedField.setText("");
		ipCheckFlagField.setText("");
		suppExtMobField.setText("");
		siteNameField.setText("");
		deviceLocationField.setText("");
		descriptionField.setText("");
		lineLstField.setText("");
		numberField.setText("");
		ccLineBtnIndexField.setText("");
		commonLineBtnIndexField.setText("");
	}
}
