package com.huawei.esdk.ec.view.enterprise;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import com.huawei.esdk.ec.bean.enterprise.ConferenceAuthenticationBean;
import com.huawei.esdk.ec.constant.HTTPConstant;
import com.huawei.esdk.ec.service.EcService;
import com.huawei.esdk.ec.utils.LoginUtils;
import com.huawei.esdk.ec.utils.Properties_language_Utils;
import com.huawei.esdk.ec.utils.StringUtils;
import com.huawei.esdk.ec.view.common.LogPanel;
import com.huawei.esdk.ec.view.common.MyButton;

/**
 * 会控鉴权的界面
 * Conference control authentication interface
 * @author wWX534262
 *
 */
@SuppressWarnings("all")
public class ConferenceAuthenticationPanel extends JPanel
{

	private static final long serialVersionUID = -8915551586285212003L;
	
	private static final Logger LOGGER = LogManager.getLogger(ConferenceAuthenticationPanel.class);
	
	private JLabel reqParams = new JLabel(Properties_language_Utils.getValue("reqParams"));
	
	private JLabel errInfoLabel = new JLabel();
	
	private JLabel conferenceidLabel = new JLabel("conferenceid:");
	private JTextField conferenceidField = new JTextField(10);
	
	private JLabel nickNameLabel = new JLabel("nickName:");
	private JTextField nickNameField = new JTextField(10);
	
	private JLabel passwordLabel = new JLabel("password:");
	private JPasswordField passwordField = new JPasswordField(10);
	
	private JLabel confURLLabel = new JLabel("ConfURL:");
	private JTextField confURLField = new JTextField(10);
	
	private JButton getConferenceTokenBtn = new MyButton(Properties_language_Utils.
			getValue("enter.ConferenceAuthenticationPanel.getConferenceTokenBtn"));
	

	
	public ConferenceAuthenticationPanel()
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
		buildPanel(panel, gridbag, c, new JComponent[] {conferenceidLabel}, 0, 1, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {conferenceidField}, 1, 1, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {nickNameLabel}, 0, 2, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {nickNameField}, 1, 2, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {passwordLabel}, 0, 3, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {passwordField}, 1, 3, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {confURLLabel}, 0, 4, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {confURLField}, 1, 4, 10, 20, 1, 1);
		
		buildPanel(panel, gridbag, c, new JComponent[] {getConferenceTokenBtn}, 0, 5, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {errInfoLabel}, 0, 6, 10, 5, 4, 1);
		
		//报文位置
		//message location
		buildPanel(panel, gridbag, c, new JComponent[] {new LogPanel()}, 4, 0, 10, 20, 4, 10);
		
		return panel;
	}
	
	private void init() 
	{
		//获取会议token按钮事件
		//Get conference token button event
		this.getConferenceTokenBtn.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
			{
				showErrInfoWithColor("");
			            
				boolean flag = validateParams(
						new JTextField[] {conferenceidField, nickNameField, passwordField},
			            new String[] {"conferenceid", "nickName", "password"});
			                
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
	            		getConferenceToken();
	            	}
	            };
	            Future future = Executors.newSingleThreadExecutor().submit(runnable);
	            if(!future.isDone()) {
	            	LOGGER.error("addMouseListener fail");
	            }
			}
		});
	}
	
	private void getConferenceToken() 
	{
		try 
		{
			Token token = LoginUtils.getToken();
			
			// 利用token发送restful请求
			// Send a restful request using token
			RestRequest request = new RestRequest(HTTPConstant.HTTP_METHOD_POST);
			
			request.setPayload(addConferenceAuth());
			
			EcService.get("/conferences/" + conferenceidField.getText() + "/token", request, errInfoLabel, token);
			
			addConferenceAuth().setPassword(null); 
			
			EcService.finish();
			
		} 
		catch (RuntimeException e) 
		{
			LOGGER.error("get token error");
		}
		catch (Exception e) 
		{
			LOGGER.error("get token error");
		}
	}
	
	private ConferenceAuthenticationBean addConferenceAuth() 
	{
		ConferenceAuthenticationBean conferenceAuthentication = new ConferenceAuthenticationBean();
		
		conferenceAuthentication.setNickName(nickNameField.getText());
		conferenceAuthentication.setPassword(new String(passwordField.getPassword()));
		if (StringUtils.isNotEmpty(confURLField.getText())) 
		{
			conferenceAuthentication.setConfURL(confURLField.getText());
		}
		return conferenceAuthentication;
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
}
