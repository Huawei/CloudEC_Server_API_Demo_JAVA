package com.huawei.esdk.ec.view.enterprise;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.huawei.esdk.ec.bean.enterprise.SendMsgToDept;
import com.huawei.esdk.ec.bean.enterprise.SendMsgToGroup;
import com.huawei.esdk.ec.bean.enterprise.SendMsgToUC;
import com.huawei.esdk.ec.constant.HTTPConstant;
import com.huawei.esdk.ec.service.EcService;
import com.huawei.esdk.ec.utils.Base64Utils;
import com.huawei.esdk.ec.utils.LoginUtils;
import com.huawei.esdk.ec.utils.Properties_language_Utils;
import com.huawei.esdk.ec.utils.StringUtils;
import com.huawei.esdk.ec.view.common.LogPanel;
import com.huawei.esdk.ec.view.common.MyButton;
/**
 * 即时消息视图
 * Instant messaging view
 * @author wwx534262
 *
 */
@SuppressWarnings("all")
public class SendIMsgPanel extends JPanel
{
	private static final long serialVersionUID = 1L;
	
	private static final Logger LOGGER = LogManager.getLogger(SendIMsgPanel.class);
	
	private JLabel errInfoLabel = new JLabel();
		
	//发送者EC账号
	//Sender EC account
	private JTextField sendNumberField = new JTextField(10);
	
	//接收者EC账号
	//Receiver EC account
	private JTextField ucAccountField = new JTextField(10);
	
	//群组ID
	//Group ID
	private JTextField groupIdField = new JTextField(10);
	
	//部门号
	//Department number
	private JTextField deptIdField = new JTextField(10);
	
	//消息内容
	//Message content
	private JTextField messageField = new JTextField(10);
	
	//通知消息标题
	//Notification message title
	private JTextField subjectField = new JTextField(10);
	
	//消息优先级
	//Message priority
	private JTextField priorityLevelField = new JTextField(10);
	
	//按钮
	//button
	private JButton appSendMsgToUCBtn = new MyButton(Properties_language_Utils.
			getValue("enter.SendIMsgPanel.appSendMsgToUCBtn"),150,35);
	
	private JButton appSendMsgToGroupBtn = new MyButton(Properties_language_Utils.
			getValue("enter.SendIMsgPanel.appSendMsgToGroupBtn"),150,35);
	
	private JButton appSendMsgToDeptBtn = new MyButton(Properties_language_Utils.
			getValue("enter.SendIMsgPanel.appSendMsgToDeptBtn"),150,35);
	
	public SendIMsgPanel()
	{
		add(getPanels());
		init();
	}
	
	private JPanel getPanels()
	{
		GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		JPanel panel = new JPanel(gridbag);
		
		buildPanel(panel, gridbag, c, new JComponent[] {new JLabel(Properties_language_Utils.getValue("reqParams"))},
				0, 0, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {new JLabel("sendNumber:")}, 0, 3, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {sendNumberField}, 1, 3, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {new JLabel("ucAccount:")}, 2, 3, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {ucAccountField}, 3, 3, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {new JLabel("groupId:")}, 0, 4, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {groupIdField}, 1, 4, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {new JLabel("deptId:")}, 2, 4, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {deptIdField}, 3, 4, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {new JLabel("message:")}, 0, 5, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {messageField}, 1, 5, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {new JLabel("subject:")}, 2, 5, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {subjectField}, 3, 5, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {new JLabel("priorityLevel:")}, 0, 6, 10, 40, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {priorityLevelField}, 1, 6, 10, 40, 1, 1);
		
		buildPanel(panel, gridbag, c, new JComponent[] {appSendMsgToUCBtn}, 0, 7, 100, 20, 2, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {appSendMsgToGroupBtn}, 2, 7, 100, 20, 2, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {appSendMsgToDeptBtn}, 0, 8, 100, 10, 2, 1);
		
		buildPanel(panel, gridbag, c, new JComponent[] {errInfoLabel}, 2, 8, 10, 5, 2, 1);
		
	    //报文位置
		//message location
		buildPanel(panel, gridbag, c, new JComponent[] {new LogPanel()}, 4, 0, 10, 20, 4, 10);
		
		return panel;
	}
	
	private void buildPanel(JPanel panel, GridBagLayout gridbag, GridBagConstraints c, JComponent[] components,
			int gridx, int gridy, int ipadx, int ipady, int gridwidth, int gridheight)
	{
		JPanel subPanel = new JPanel();
				
		for(JComponent component : components)
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
	
	private void init()
	{
		//向EC用户发送消息按钮事件
		//Send Message to EC Users Button Event
		this.appSendMsgToUCBtn.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
			{
				showErrInfoWithColor("");
				
				boolean flag = validateParams(new JTextField[] {sendNumberField, ucAccountField, messageField},
						new String[] {"sendNumber", "ucAccount", "message"});
				
				if(!flag)
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
	            		appSendMsgToUC();
	            	}
	            };
	            Future future = Executors.newSingleThreadExecutor().submit(runnable);
		        if(!future.isDone()) {
		            LOGGER.error("addMouseListener fail");
		        }

			}
		});
		
		//向群组发送消息按钮事件
		//Send a message to the group button event
		this.appSendMsgToGroupBtn.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
			{
				showErrInfoWithColor("");
				
				boolean flag = validateParams(new JTextField[] {sendNumberField, groupIdField, messageField},
						new String[] {"sendNumber", "groupId", "message"});
				
				if(!flag)
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
						appSendMsgToGroup();
					}
		        };
		        Future future = Executors.newSingleThreadExecutor().submit(runnable);
		        if(!future.isDone()) {
		            LOGGER.error("addMouseListener fail");
		        }
			}
		});	
		
		//向部门发送系统通知消息按钮事件
		//Send system notification messages to department button event
		this.appSendMsgToDeptBtn.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
			{
				showErrInfoWithColor("");
				
				boolean flag = validateParams(new JTextField[] {sendNumberField, deptIdField, messageField, subjectField},
						new String[] {"sendNumber", "deptId", "message", "subject"});
				
				if(!flag)
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
						appSendMsgToDept();
					}
		        };
		        Future future = Executors.newSingleThreadExecutor().submit(runnable);
		        if(!future.isDone()) {
		            LOGGER.error("addMouseListener fail");
		        }
			}
		});
	}

	//向EC用户发送消息
	//Send a message to EC users
	private void appSendMsgToUC()
	{
		//拼装请求参数，发送请求
		//Assembly request parameters, send request
		SendMsgToUC payload = new SendMsgToUC();
		
		payload.setSendNumber(sendNumberField.getText());
		payload.setUcAccount(ucAccountField.getText());
		payload.setMessage(addMessage());
		payload.setDateTime(getDataTime());
		
		if(StringUtils.isNotEmpty(priorityLevelField.getText().trim()))
		{
			payload.setPriorityLevel(priorityLevelField.getText().trim());
		}
		
		try 
		{
			 Token token = LoginUtils.getToken();
			 
			 // 利用token发送restful请求
			 // Send a restful request using token
			 RestRequest request = new RestRequest(HTTPConstant.HTTP_METHOD_POST);
			 request.setPayload(payload);
			 
			 EcService.post("/im/ecAccount", request, errInfoLabel, token);
			 EcService.finish();
		} 
		catch (RuntimeException e) 
		{
			LOGGER.error("get Token error",e);
			showErrInfoWithColor("操作失败");
		}
		catch (Exception e) 
		{
			LOGGER.error("get Token error",e);
			showErrInfoWithColor(Properties_language_Utils.getValue("resultTip"));
		}
		
	}
	
	//向群祖发送消息
	//Send messages to group
	private void appSendMsgToGroup()
	{
		//拼装请求参数，发送请求
		//Assembly request parameters, send request
		SendMsgToGroup payload = new SendMsgToGroup();
		payload.setSendNumber(sendNumberField.getText());
		payload.setGroupId(groupIdField.getText());
		payload.setMessage(addMessage());
		payload.setDateTime(getDataTime());
		if(!"".equals(priorityLevelField.getText()))
		{
			payload.setPriorityLevel(priorityLevelField.getText());
		}
		
		try 
		 {
			 Token token = LoginUtils.getToken();
			 
			 // 利用token发送restful请求
			 // Send a restful request using token
			 RestRequest request = new RestRequest(HTTPConstant.HTTP_METHOD_POST);
			 request.setPayload(payload);
			 
			 EcService.post("/im/group", request, errInfoLabel, token);
			 EcService.finish();
			 
		 } 
		 catch (RuntimeException e) 
		 {
			 LOGGER.error("get Token error",e);
			 showErrInfoWithColor("操作失败");
		 }
		 catch (Exception e) 
		 {
			 LOGGER.error("get Token error");
		 }
		
	}
	
	//向部门发送系统通知消息
	//Send system notification messages to department
	private void appSendMsgToDept()
	{
		//拼装请求参数，发送请求
		//Assembly request parameters, send request
		SendMsgToDept payload = new SendMsgToDept();
		payload.setSendNumber(sendNumberField.getText());
		payload.setDeptId(deptIdField.getText());
		payload.setMessage(messageField.getText());
		payload.setSubject(subjectField.getText());
		if(!"".equals(priorityLevelField.getText()))
		{
			payload.setPriorityLevel(priorityLevelField.getText());
		}
		
		try 
		 {
			 Token token = LoginUtils.getToken();
			 
			 // 利用token发送restful请求
			 // Send a restful request using token
			 RestRequest request = new RestRequest(HTTPConstant.HTTP_METHOD_POST);
			 request.setPayload(payload);
		
			 EcService.post("/im/department", request, errInfoLabel, token);
			 EcService.finish();
			 
		 } 
		 catch (RuntimeException e) 
		 {
			 LOGGER.error("get Token error",e);
			 showErrInfoWithColor("操作失败");
		 }
		 catch (Exception e) 
		 {
			 LOGGER.error("get Token error");
		 }
		
	}
	
	private String addMessage()
	{
		String addMessage = null;
		String message = messageField.getText();
		String newMessage = "<imbody><imagelist/><html><![CDATA[<div style=\"color:red;\">" + message 
				+ "</div>]]></html><content>" + message + "</content></imbody>";
		try
		{
			addMessage = Base64Utils.encode(newMessage.getBytes("UTF-8"));
		}
		catch(UnsupportedEncodingException e)
		{
			LOGGER.error("UnsupportedEncodingException", e);
		}
		
		return addMessage;
	}
	
	private boolean validateParams(JTextField[] textFields, String[] errTexts)
	{
		for(int i = 0; i < textFields.length; i++)
		{
			if(null == textFields[i] || null == textFields[i].getText() || "".equals(textFields[i].getText().trim()))
			{
				showErrInfoWithColor(errTexts[i] + Properties_language_Utils.getValue("isNotEmpty"));
				return false;
			}
		}
		
		return true;
	}
	
	//获取当前时间，为消息发送时间
	//Get current time for message sending time
	public String getDataTime()
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
		String dateStr = sdf.format(new Date());
		return dateStr;
	}
	
	private void showErrInfoWithColor(String errInfo)
	{
		if(null == errInfo)
		{
			return;
		}
		
		errInfoLabel.setForeground(Color.red);
		errInfoLabel.setText(errInfo);
	}
}
