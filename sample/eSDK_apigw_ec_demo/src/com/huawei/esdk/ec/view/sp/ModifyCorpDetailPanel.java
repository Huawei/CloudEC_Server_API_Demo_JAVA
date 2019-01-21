package com.huawei.esdk.ec.view.sp;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.huawei.esdk.ec.bean.common.RestRequest;
import com.huawei.esdk.ec.bean.common.Token;
import com.huawei.esdk.ec.bean.sp.CorpModRestBean;
import com.huawei.esdk.ec.constant.HTTPConstant;
import com.huawei.esdk.ec.service.EcService;
import com.huawei.esdk.ec.utils.LoginUtils;
import com.huawei.esdk.ec.utils.Properties_language_Utils;
import com.huawei.esdk.ec.utils.StringUtils;
import com.huawei.esdk.ec.view.common.LogPanel;
import com.huawei.esdk.ec.view.common.MyButton;

public class ModifyCorpDetailPanel extends JPanel{

	private static final long serialVersionUID = -5031335164641324587L;
	
	private static final Logger LOGGER = LogManager.getLogger(ModifyCorpDetailPanel.class);
	
	private JLabel errInfoLabel = new JLabel();
	
	private JLabel reqParams = new JLabel(Properties_language_Utils.getValue("reqParams"));	

	private JLabel corpIdLabel = new JLabel("corpId:");
	private JTextField corpIdField = new JTextField(10);
	
	private JLabel phoneLabel = new JLabel("phone:");
	private JTextField phoneField = new JTextField(10);
	
	private JLabel emailLabel = new JLabel("email:");
	private JTextField emailField = new JTextField(10);
	
	private JLabel faxLabel = new JLabel("fax:");
	private JTextField faxField = new JTextField(10);
	
	private JLabel smsLabel = new JLabel("sms:");
	private JTextField smsField = new JTextField(10);
	
	private JLabel addressLabel = new JLabel("address:");
	private JTextField addressField = new JTextField(10);
	
	private JLabel hangUpSmLabel = new JLabel("hangUpSm:");
	private JTextField hangUpSmField = new JTextField(10);
	
	private JLabel allowedJumpLabel = new JLabel("allowedJump:");
	private JTextField allowedJumpField = new JTextField(10);
	
	private JLabel accountSmsNotifyLabel = new JLabel("accountSmsNotify:");
	private JTextField accountSmsNotifyField = new JTextField(10);
	
	private JLabel mdxAuthorizeLabel = new JLabel("mdxAuthorize:");
	private JTextField mdxAuthorizeField = new JTextField(10);
	
	private JLabel isNoNeedLoginMatchLabel = new JLabel("isNoNeedLoginMatch:");
	private JTextField isNoNeedLoginMatchField = new JTextField(10);
	
	private JLabel deptPushTypeLabel = new JLabel("deptPushType:");
	private JTextField deptPushTypeField = new JTextField(10);
	
	private JLabel smsSignLabel = new JLabel("smsSign:");
	private JTextField smsSignField = new JTextField(10);
	
	private JLabel ldapPwdLabel = new JLabel("ldapPwd:");
	private JPasswordField ldapPwdField = new JPasswordField(10);
	
	private JButton modifyDetailBtn = new MyButton(Properties_language_Utils.
			getValue("sp.corpManagementPanel.modifyCorpDetailBtn"),200,35);
	
	public ModifyCorpDetailPanel() 
	{
		add(getPanels());
		init();
	}
	
	//设置样式
	private JPanel getPanels()
	{
		GridBagLayout gridbag = new GridBagLayout();
	    GridBagConstraints c = new GridBagConstraints();
	    JPanel panel = new JPanel(gridbag);
	    
	    buildPanel(panel, gridbag, c, new JComponent[] {reqParams}, 0, 0, 10, 40, 1, 1);  
	    buildPanel(panel, gridbag, c, new JComponent[] {corpIdLabel}, 0, 1, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {corpIdField}, 1, 1, 10, 20, 2, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {phoneLabel}, 2, 1, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {phoneField}, 3, 1, 6, 20, 2, 1);
	    
	    buildPanel(panel, gridbag, c, new JComponent[] {emailLabel}, 0, 3, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {emailField}, 1, 3, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {faxLabel}, 2, 3, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {faxField}, 3, 3, 10, 20, 1, 1);
	    
	    buildPanel(panel, gridbag, c, new JComponent[] {smsLabel}, 0, 4, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {smsField}, 1, 4, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {addressLabel}, 2, 4, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {addressField}, 3, 4, 10, 20, 1, 1);
	    
	    buildPanel(panel, gridbag, c, new JComponent[] {hangUpSmLabel}, 0, 5, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {hangUpSmField}, 1, 5, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {allowedJumpLabel}, 2, 5, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {allowedJumpField}, 3, 5, 10, 20, 1, 1);
	    
	    buildPanel(panel, gridbag, c, new JComponent[] {accountSmsNotifyLabel}, 0, 6, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {accountSmsNotifyField}, 1, 6, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {mdxAuthorizeLabel}, 2, 6, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {mdxAuthorizeField}, 3, 6, 10, 20, 1, 1);
	    
	    buildPanel(panel, gridbag, c, new JComponent[] {isNoNeedLoginMatchLabel}, 0, 7, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {isNoNeedLoginMatchField}, 1, 7, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {deptPushTypeLabel}, 2, 7, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {deptPushTypeField}, 3, 7, 10, 20, 1, 1);
	    
	    buildPanel(panel, gridbag, c, new JComponent[] {smsSignLabel}, 0, 8, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {smsSignField}, 1, 8, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {ldapPwdLabel}, 2, 8, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {ldapPwdField}, 3, 8, 10, 20, 1, 1);
	  
	    
	    buildPanel(panel, gridbag, c, new JComponent[] {modifyDetailBtn}, 0, 9, 10, 20, 2, 1);
	    
	    buildPanel(panel, gridbag, c, new JComponent[] {errInfoLabel}, 0, 10, 100, 5, 4, 1);
	    
	    //报文位置
		//message location
		buildPanel(panel, gridbag, c, new JComponent[] {new LogPanel()}, 4, 0, 10, 20, 4, 10);
	    return panel;
	
	}
	
	private void init() 
	{
		this.modifyDetailBtn.addMouseListener(new MouseAdapter()
        {
			 public void mouseClicked(MouseEvent e)
	         {
				 showErrInfoWithColor("");
	            
	             boolean flag = validateParams(
	            		 new JTextField[] {corpIdField},
	            		 new String[] {"corpId"});
	                
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
	            		 modifyDetail();
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
	 * 修改企业详情
	 */
	private void modifyDetail() 
	{
		RestRequest request = new RestRequest(HTTPConstant.HTTP_METHOD_POST);
		request.setPayload(addCorpModRestBean());
		try 
		{
			Token token = LoginUtils.getToken();
			EcService.post("/corp/" + corpIdField.getText() + "/detail", request, errInfoLabel,token);
			
			EcService.finish();
		} 
		catch (Exception e) 
		{
			LOGGER.error("getToken error:" + e);
		}
	}
	
	/**
	 * 设置修改企业基本信息参数
	 * @return
	 */
	private CorpModRestBean addCorpModRestBean() 
	{
		CorpModRestBean corpModRestBean = new CorpModRestBean();
		
		if(StringUtils.isNotEmpty(phoneField.getText())) 
		{
			corpModRestBean.setPhone(phoneField.getText());
		}
		if(StringUtils.isNotEmpty(emailField.getText())) 
		{
			corpModRestBean.setEmail(emailField.getText());
		}
		if(StringUtils.isNotEmpty(faxField.getText())) 
		{
			corpModRestBean.setFax(faxField.getText());
		}
		if(StringUtils.isNotEmpty(smsField.getText())) 
		{
			corpModRestBean.setFax(smsField.getText());
		}
		if(StringUtils.isNotEmpty(addressField.getText())) 
		{
			corpModRestBean.setAddress(addressField.getText());
		}
		if(StringUtils.isNotEmpty(hangUpSmField.getText())) 
		{
			corpModRestBean.setHangUpSm(hangUpSmField.getText());
		}
		if(StringUtils.isNotEmpty(allowedJumpField.getText())) 
		{
			corpModRestBean.setAllowedJump(Boolean.parseBoolean(allowedJumpField.getText()));
		}
		if(StringUtils.isNotEmpty(accountSmsNotifyField.getText())) 
		{
			corpModRestBean.setAccountSmsNotify(Boolean.parseBoolean(accountSmsNotifyField.getText()));
		}
		if(StringUtils.isNotEmpty(mdxAuthorizeField.getText())) 
		{
			corpModRestBean.setMdxAuthorize(Boolean.parseBoolean(mdxAuthorizeField.getText()));
		}
		if(StringUtils.isNotEmpty(isNoNeedLoginMatchField.getText())) 
		{
			corpModRestBean.setIsNoNeedLoginMatch(Integer.parseInt(isNoNeedLoginMatchField.getText()));
		}
		if(StringUtils.isNotEmpty(deptPushTypeField.getText())) 
		{
			corpModRestBean.setDeptPushType(Integer.parseInt(deptPushTypeField.getText()));
		}
		if(StringUtils.isNotEmpty(smsSignField.getText())) 
		{
			corpModRestBean.setSmsSign(smsSignField.getText());
		}
		if(StringUtils.isNotEmpty(new String(ldapPwdField.getPassword()))) 
		{
			char[] password = ldapPwdField.getPassword();
			corpModRestBean.setLdapPwd(new String(password));
			
			Arrays.fill(password, ' ');
		}
		
		return corpModRestBean;
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
}
