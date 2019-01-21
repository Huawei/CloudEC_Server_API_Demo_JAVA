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
import javax.swing.JTextField;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.huawei.esdk.ec.bean.common.RestRequest;
import com.huawei.esdk.ec.bean.common.Token;
import com.huawei.esdk.ec.bean.enterprise.StateSubscriptionBean;
import com.huawei.esdk.ec.constant.HTTPConstant;
import com.huawei.esdk.ec.service.EcService;
import com.huawei.esdk.ec.utils.LoginUtils;
import com.huawei.esdk.ec.utils.Properties_language_Utils;
import com.huawei.esdk.ec.view.common.LogPanel;
import com.huawei.esdk.ec.view.common.MyButton;
/**
 * 定义会议状态视图
 * Define meeting status view
 * @author wWX534262
 *
 */
@SuppressWarnings("all")
public class StateSubscriptionPanel extends JPanel
{
	private static final long serialVersionUID = -1433843002685889133L;
	
	private static final Logger LOGGER = LogManager.getLogger(StateSubscriptionPanel.class);
	
	private JLabel reqParams = new JLabel(Properties_language_Utils.getValue("reqParams"));
	
	private JLabel errInfoLabel = new JLabel();
	
	private JLabel conferenceidLabel = new JLabel("conferenceid:");
	private JTextField conferenceidField = new JTextField(10);
	
	private JLabel callBackURLLabel = new JLabel("callBackURL:");
	private JTextField callBackURLField = new JTextField(10);
	
	private JLabel enableConforenceStatusLabel = new JLabel("enableConforenceStatus:");
	private JTextField enableConforenceStatusField = new JTextField(10);
	
	private JLabel enableSpeakerChangeLabel = new JLabel("enableSpeakerChange:");
	private JTextField enableSpeakerChangeField = new JTextField(10);
	
	private JButton subscribeStatusBtn = new MyButton(Properties_language_Utils.
			getValue("enter.StateSubscriptionPanel.subscribeStatusBtn"));
	
	public StateSubscriptionPanel()
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
		buildPanel(panel, gridbag, c, new JComponent[] {callBackURLLabel}, 0, 2, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {callBackURLField}, 1, 2, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {enableConforenceStatusLabel}, 0, 3, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {enableConforenceStatusField}, 1, 3, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {enableSpeakerChangeLabel}, 0, 4, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {enableSpeakerChangeField}, 1, 4, 10, 20, 1, 1);
		
		buildPanel(panel, gridbag, c, new JComponent[] {subscribeStatusBtn}, 0, 5, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {errInfoLabel}, 0, 6, 10, 20, 2, 1);
		
	    //报文位置
		//message location
		buildPanel(panel, gridbag, c, new JComponent[] {new LogPanel()}, 4, 0, 10, 20, 4, 10);
		return panel;
	}
	
	private void init() 
	{
		//订阅会议状态按钮事件
		//Subscription conference status button events
		this.subscribeStatusBtn.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
	        {
				showErrInfoWithColor("");
	            
	            boolean flag = validateParams(
	            		new JTextField[] {conferenceidField, callBackURLField, enableConforenceStatusField, enableSpeakerChangeField},
	            		new String[] {"conferenceid", "callBackURL", "enableConforenceStatus", "enableSpeakerChange"});
	                
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
						 subscribeStatus();
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
	
	//订阅会议状态
	//Subscribe to conference status
	private void subscribeStatus() 
	{
		RestRequest request = new RestRequest(HTTPConstant.HTTP_METHOD_POST);
		
		StateSubscriptionBean stateSubscriptionBean = new StateSubscriptionBean();
		stateSubscriptionBean.setCallBackURL(callBackURLField.getText());
		stateSubscriptionBean.setEnableConforenceStatus(Integer.parseInt(enableConforenceStatusField.getText()));
		stateSubscriptionBean.setEnableSpeakerChange(enableSpeakerChangeField.getText());
		
		try 
		{
			Token token = LoginUtils.getToken();
			
			request.setPayload(stateSubscriptionBean);
			EcService.post("/conferences/" + conferenceidField.getText() + "/subscribers", request, errInfoLabel, token);
			EcService.finish();
		} 
		catch (Exception e) 
		{
			LOGGER.error("getToken error", e);
			showErrInfoWithColor(Properties_language_Utils.getValue("resultTip"));
		}
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
