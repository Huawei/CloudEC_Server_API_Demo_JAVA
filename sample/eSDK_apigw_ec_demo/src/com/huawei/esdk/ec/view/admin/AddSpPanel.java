package com.huawei.esdk.ec.view.admin;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
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

import com.huawei.esdk.ec.bean.admin.SPInfo;
import com.huawei.esdk.ec.bean.common.RestRequest;
import com.huawei.esdk.ec.bean.common.Token;
import com.huawei.esdk.ec.constant.HTTPConstant;
import com.huawei.esdk.ec.service.EcService;
import com.huawei.esdk.ec.utils.LoginUtils;
import com.huawei.esdk.ec.utils.Properties_language_Utils;
import com.huawei.esdk.ec.utils.StringUtils;
import com.huawei.esdk.ec.view.common.LogPanel;
import com.huawei.esdk.ec.view.common.MyButton;

/**
 * 增加或者修改服务提供商视图
 * @author wWX534262
 *
 */
public class AddSpPanel extends JPanel implements ActionListener
{
	private static final long serialVersionUID = -5387856248690198271L;
	
	private static final Logger LOGGER = LogManager.getLogger(AddSpPanel.class);
	
	private JLabel errInfoLabel = new JLabel();
	
	private JLabel tipInfoLabel = new JLabel(Properties_language_Utils.getValue("admin.AddSpPanel.tipInfoLabel"));
	
	private JLabel reqParams = new JLabel(Properties_language_Utils.getValue("reqParams"));
	
	private JLabel spIdLabel = new JLabel("spId:");
	private JTextField spIdField = new JTextField(10);
	
	private JLabel spNameLabel = new JLabel("spName:");
	private JTextField spNameField = new JTextField(10);
	
	private JLabel spDomainLabel = new JLabel("spDomain:");
	private JTextField spDomainField = new JTextField(10);
	
	private JLabel contactLabel = new JLabel("contact:");
	private JTextField contactField = new JTextField(10);
	
	private JLabel phoneLabel = new JLabel("phone:");
	private JTextField phoneField = new JTextField(10);
	
	private JLabel mobileLabel = new JLabel("mobile:");
	private JTextField mobileField = new JTextField(10);
	
	private JLabel faxLabel = new JLabel("fax:");
	private JTextField faxField = new JTextField(10);
	
	private JLabel emailLabel = new JLabel("email:");
	private JTextField emailField = new JTextField(10);
	
	private JLabel websiteLabel = new JLabel("website:");
	private JTextField websiteField = new JTextField(10);
	
	private JLabel addressLabel = new JLabel("address:");
	private JTextField addressField = new JTextField(10);
	
	private JLabel servPackNameLabel = new JLabel("servPackName:");
	private JTextField servPackNameField = new JTextField(10);
	
	private JLabel ucCountLabel = new JLabel("ucCount:");
	private JTextField ucCountField = new JTextField(10);
	
	private JLabel confCountLabel = new JLabel("confCount:");
	private JTextField confCountField = new JTextField(10);
	
	private JLabel corpCountLabel = new JLabel("corpCount:");
	private JTextField corpCountField = new JTextField(10);
	
	private JLabel servTpIdListLabel = new JLabel("servTpIdList:");
	private JTextField servTpIdListField = new JTextField(10);
	
	private JLabel umsServerInfoLabel = new JLabel("umsServerInfo:");
	private JTextField umsServerInfoField = new JTextField(10);
	
	private JLabel isActiveLabel = new JLabel("isActive:");
	private JTextField isActiveField = new JTextField(10);
	
	private JLabel smartCenterIfcLabel = new JLabel("smartCenterIfc:");
	private JTextField smartCenterIfcField = new JTextField(10);
	
	private JLabel pgmIFCLabel = new JLabel("pgmIFC:");
	private JTextField pgmIFCField = new JTextField(10);
	
	private JLabel cerbtIDLabel = new JLabel("cerbtID:");
	private JTextField cerbtIDField = new JTextField(10);
	
	private JLabel confRightLabel = new JLabel("confRight:");
	private JTextField confRightField = new JTextField(10);
	
	private JLabel mgrDomainLabel = new JLabel("mgrDomain:");
	private JTextField mgrDomainField = new JTextField(10);
	
	private JLabel servCodeLabel = new JLabel("servCode:");
	private JTextField servCodeField = new JTextField(10);
	
	private JLabel subServCodeLabel = new JLabel("subServcode:");
	private JTextField subServCodeField = new JTextField(10);
	
	//录制存储空间
	private JLabel recordCapabilityLabel = new JLabel("recordCapability:");
	private JTextField recordCapabilityField = new JTextField(10);
	
	//是否使用IPT+会议资源包
	private JLabel useFullServPackLabel = new JLabel("useFullServPack:");
	private JTextField useFullServPackField = new JTextField(10);
	
	//会议资源类型
	private JLabel meetingTypeLabel = new JLabel("meetingType:");
	private JTextField meetingTypeField = new JTextField(10);
	
	//软终端账户数
	private JLabel teSoftCountLabel = new JLabel("teSoftCount:");
	private JTextField teSoftCountField = new JTextField(10);
	
	//是否有IPT功能
	private JLabel iptFunctionLabel = new JLabel("iptFunction:");
	private JTextField iptFunctionField = new JTextField(10);
	
	//是否有UC功能
	private JLabel ucFunctionLabel = new JLabel("ucFunction:");
	private JTextField ucFunctionField = new JTextField(10);
	
	private JButton addSpBtn = new MyButton(Properties_language_Utils.
			getValue("admin.AddSpPanel.addSpBtn"), 200, 35);
	
	private JButton modifySpBtn = new MyButton(Properties_language_Utils.
			getValue("admin.AddSpPanel.modifySpBtn"), 200, 35); 
	
	private JButton emptyBtn =  new MyButton(Properties_language_Utils.
			getValue("admin.AddSpPanel.emptyBtn"));
	
	public AddSpPanel() 
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
	    
	    buildPanel(panel, gridbag, c, new JComponent[] {spIdLabel}, 0, 1, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {spIdField}, 1, 1, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {spNameLabel}, 2, 1, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {spNameField}, 3, 1, 10, 20, 1, 1);
	    
	    buildPanel(panel, gridbag, c, new JComponent[] {spDomainLabel}, 0, 2, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {spDomainField}, 1, 2, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {contactLabel}, 2, 2, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {contactField}, 3, 2, 10, 20, 1, 1);
	    
	    buildPanel(panel, gridbag, c, new JComponent[] {phoneLabel}, 0, 3, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {phoneField}, 1, 3, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {mobileLabel}, 2, 3, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {mobileField}, 3, 3, 10, 20, 1, 1);
	    
	    buildPanel(panel, gridbag, c, new JComponent[] {faxLabel}, 0, 4, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {faxField}, 1, 4, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {emailLabel}, 2, 4, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {emailField}, 3, 4, 10, 20, 1, 1);
	    
	    buildPanel(panel, gridbag, c, new JComponent[] {websiteLabel}, 0, 5, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {websiteField}, 1, 5, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {addressLabel}, 2, 5, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {addressField}, 3, 5, 10, 20, 1, 1);
	    
	    buildPanel(panel, gridbag, c, new JComponent[] {servPackNameLabel}, 0, 6, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {servPackNameField}, 1, 6, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {ucCountLabel}, 2, 6, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {ucCountField}, 3, 6, 10, 20, 1, 1);
	    
	    buildPanel(panel, gridbag, c, new JComponent[] {confCountLabel}, 0, 7, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {confCountField}, 1, 7, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {corpCountLabel}, 2, 7, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {corpCountField}, 3, 7, 10, 20, 1, 1);
	    
	    buildPanel(panel, gridbag, c, new JComponent[] {servTpIdListLabel}, 0, 8, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {servTpIdListField}, 1, 8, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {umsServerInfoLabel}, 2, 8, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {umsServerInfoField}, 3, 8, 10, 20, 1, 1);
	    
	    buildPanel(panel, gridbag, c, new JComponent[] {isActiveLabel}, 0, 9, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {isActiveField}, 1, 9, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {smartCenterIfcLabel}, 2, 9, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {smartCenterIfcField}, 3, 9, 10, 20, 1, 1);
	    
	    buildPanel(panel, gridbag, c, new JComponent[] {pgmIFCLabel}, 0, 10, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {pgmIFCField}, 1, 10, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {cerbtIDLabel}, 2, 10, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {cerbtIDField}, 3, 10, 10, 20, 1, 1);
	    
	    buildPanel(panel, gridbag, c, new JComponent[] {confRightLabel}, 0, 11, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {confRightField}, 1, 11, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {mgrDomainLabel}, 2, 11, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {mgrDomainField}, 3, 11, 10, 20, 1, 1);
	    
	    buildPanel(panel, gridbag, c, new JComponent[] {servCodeLabel}, 0, 12, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {servCodeField}, 1, 12, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {subServCodeLabel}, 2, 12, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {subServCodeField}, 3, 12, 10, 20, 1, 1);
	    
	    buildPanel(panel, gridbag, c, new JComponent[] {recordCapabilityLabel}, 0, 13, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {recordCapabilityField}, 1, 13, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {useFullServPackLabel}, 2, 13, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {useFullServPackField}, 3, 13, 10, 20, 1, 1);
	    
	    buildPanel(panel, gridbag, c, new JComponent[] {meetingTypeLabel}, 0, 14, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {meetingTypeField}, 1, 14, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {teSoftCountLabel}, 2, 14, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {teSoftCountField}, 3, 14, 10, 20, 1, 1);
	    
	    buildPanel(panel, gridbag, c, new JComponent[] {iptFunctionLabel}, 0, 15, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {iptFunctionField}, 1, 15, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {ucFunctionLabel}, 2, 15, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {ucFunctionField}, 3, 15, 10, 20, 1, 1);
	    
	    buildPanel(panel, gridbag, c, new JComponent[] {addSpBtn}, 0, 16, 10, 40, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {modifySpBtn}, 1, 16, 10, 40, 2, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {emptyBtn}, 3, 16, 10, 40, 1, 1);
	    emptyBtn.addActionListener(this);
	    
	    buildPanel(panel, gridbag, c, new JComponent[] {errInfoLabel}, 0, 17, 10, 5, 2, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {tipInfoLabel}, 0, 18, 10, 5, 4, 1);
	    //报文位置
	    //message location
	    buildPanel(panel, gridbag, c, new JComponent[] {new LogPanel()}, 4, 0, 10, 20, 4, 10);
	    
	    return panel;
	}
	
	private void init() 
	{
		tipInfoLabel.setForeground(Color.BLUE);
		//增加服务提供商按钮事件
		this.addSpBtn.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
	        {
				showErrInfoWithColor("");
	            
	            boolean flag = validateParams(
	            		new JTextField[] {spIdField, spNameField, spDomainField},
	            		new String[] {"spId", "spName", "spDomain"});
	                
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
	            		addSp();
	            	}
	            };
	            Future<?> future = Executors.newSingleThreadExecutor().submit(runnable);
	            if(future.isDone()) 
	            {
	            	LOGGER.info("future.isDone() is true");
	            }
	        }
		});
		
		//修改服务提供商按钮事件
		this.modifySpBtn.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
	        {
				showErrInfoWithColor("");
	            
	            boolean flag = validateParams(
	            		new JTextField[] {spIdField, spNameField, spDomainField},
	            		new String[] {"spId", "spName", "spDomain"});
	                
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
	            		modifySp();
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
	 * 修改服务提供商
	 */
	private void modifySp() 
	{
		Token token = LoginUtils.getToken();
		
		RestRequest request = new RestRequest(HTTPConstant.HTTP_METHOD_PUT);
		request.setPayload(addSPInfo());
		
		EcService.put("/sp", request, errInfoLabel, token);
		
		EcService.finish();
	}
	
	/**
	 * 增加服务提供商
	 */
	private void addSp() 
	{
		Token token = LoginUtils.getToken();
		
		RestRequest request = new RestRequest(HTTPConstant.HTTP_METHOD_POST);
		request.setPayload(addSPInfo());
		
		EcService.post("/sp", request, errInfoLabel, token);
		
		EcService.finish();
	}
	
	/**
	 * 增加或者修改服务提供商参数接收并封装
	 * @return
	 */
	private SPInfo addSPInfo() 
	{
		SPInfo sPInfo = new SPInfo();
		sPInfo.setSpId(spIdField.getText());
		sPInfo.setSpName(spNameField.getText());
		sPInfo.setSpDomain(spDomainField.getText());
		if (StringUtils.isNotEmpty(contactField.getText())) 
		{
			sPInfo.setContact(contactField.getText());
		}
		if (StringUtils.isNotEmpty(phoneField.getText())) 
		{
			sPInfo.setPhone(phoneField.getText());
		}
		if (StringUtils.isNotEmpty(mobileField.getText())) 
		{
			sPInfo.setMobile(mobileField.getText());
		}
		if (StringUtils.isNotEmpty(faxField.getText())) 
		{
			sPInfo.setFax(faxField.getText());
		}
		if (StringUtils.isNotEmpty(emailField.getText())) 
		{
			sPInfo.setEmail(emailField.getText());
		}
		if (StringUtils.isNotEmpty(websiteField.getText())) 
		{
			sPInfo.setWebsite(websiteField.getText());
		}
		if (StringUtils.isNotEmpty(addressField.getText())) 
		{
			sPInfo.setAddress(addressField.getText());
		}
		if (StringUtils.isNotEmpty(servPackNameField.getText())) 
		{
			sPInfo.setServPackName(servPackNameField.getText());
		}
		if (StringUtils.isNotEmpty(ucCountField.getText())) 
		{
			sPInfo.setUcCount(Integer.parseInt(ucCountField.getText()));
		}
		if (StringUtils.isNotEmpty(confCountField.getText())) 
		{
			sPInfo.setConfCount(Integer.parseInt(confCountField.getText()));
		}
		if (StringUtils.isNotEmpty(corpCountField.getText())) 
		{
			sPInfo.setCorpCount(Integer.parseInt(corpCountField.getText()));
		}
		if (StringUtils.isNotEmpty(servTpIdListField.getText())) 
		{
			String regex = ",|，";
			List<String> asList = Arrays.asList(servTpIdListField.getText().split(regex));
			List<Integer> servTpIdList = new ArrayList<Integer>();
			for (String servTpId : asList) 
			{
				servTpIdList.add(Integer.parseInt(servTpId));
			}
			sPInfo.setServTpIdList(servTpIdList);
		}
		if (StringUtils.isNotEmpty(umsServerInfoField.getText())) 
		{
			sPInfo.setUmsServerInfo(umsServerInfoField.getText());
		}
		if (StringUtils.isNotEmpty(isActiveField.getText())) 
		{
			sPInfo.setIsActive(Integer.parseInt(isActiveField.getText()));
		}
		if (StringUtils.isNotEmpty(smartCenterIfcField.getText())) 
		{
			sPInfo.setSmartCenterIfc(Integer.parseInt(smartCenterIfcField.getText()));
		}
		if (StringUtils.isNotEmpty(pgmIFCField.getText())) 
		{
			sPInfo.setPgmIFC(Integer.parseInt(pgmIFCField.getText()));
		}
		if (StringUtils.isNotEmpty(cerbtIDField.getText())) 
		{
			sPInfo.setCerbtID(Integer.parseInt(cerbtIDField.getText()));
		}
		if (StringUtils.isNotEmpty(confRightField.getText())) 
		{
			sPInfo.setConfRight(Integer.parseInt(confRightField.getText()));
		}
		if (StringUtils.isNotEmpty(mgrDomainField.getText())) 
		{
			sPInfo.setMgrDomain(mgrDomainField.getText());
		}
		if (StringUtils.isNotEmpty(servCodeField.getText())) 
		{
			sPInfo.setServCode(servCodeField.getText());
		}
		if (StringUtils.isNotEmpty(subServCodeField.getText())) 
		{
			sPInfo.setSubServCode(subServCodeField.getText());
		}
		if (StringUtils.isNotEmpty(recordCapabilityField.getText())) 
		{
			sPInfo.setRecordCapability(Integer.parseInt(recordCapabilityField.getText()));
		}
		if (StringUtils.isNotEmpty(useFullServPackField.getText())) 
		{
			sPInfo.setUseFullServPack(Boolean.parseBoolean(useFullServPackField.getText()));
		}
		if (StringUtils.isNotEmpty(meetingTypeField.getText())) 
		{
			sPInfo.setMeetingType(Integer.parseInt(meetingTypeField.getText()));
		}
		if (StringUtils.isNotEmpty(teSoftCountField.getText())) 
		{
			sPInfo.setTeSoftCount(Integer.parseInt(teSoftCountField.getText()));
		}
		if (StringUtils.isNotEmpty(iptFunctionField.getText())) 
		{
			sPInfo.setIptFunction(iptFunctionField.getText());
		}
		if (StringUtils.isNotEmpty(ucFunctionField.getText())) 
		{
			sPInfo.setUcFunction(ucFunctionField.getText());
		}
		
		return sPInfo;
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
		spIdField.setText("");
		spNameField.setText("");
		spDomainField.setText("");
		contactField.setText("");
		phoneField.setText("");
		mobileField.setText("");
		faxField.setText("");
		emailField.setText("");
		websiteField.setText("");
		addressField.setText("");
		servPackNameField.setText("");
		ucCountField.setText("");
		confCountField.setText("");
		corpCountField.setText("");
		servTpIdListField.setText("");
		umsServerInfoField.setText("");
		isActiveField.setText("");
		smartCenterIfcField.setText("");
		pgmIFCField.setText("");
		cerbtIDField.setText("");
		confRightField.setText("");
		mgrDomainField.setText("");
		servCodeField.setText("");
		subServCodeField.setText("");
		recordCapabilityField.setText("");
		useFullServPackField.setText("");
		meetingTypeField.setText("");
		teSoftCountField.setText("");
		iptFunctionField.setText("");
		ucFunctionField.setText("");
	}
}
