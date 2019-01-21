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
import com.huawei.esdk.ec.constant.HTTPConstant;
import com.huawei.esdk.ec.service.EcService;
import com.huawei.esdk.ec.utils.LoginUtils;
import com.huawei.esdk.ec.utils.Properties_language_Utils;
import com.huawei.esdk.ec.view.common.LogPanel;
import com.huawei.esdk.ec.view.common.MyButton;
/**
 * 成员级别管理视图
 * Member level management view
 * @author wwx534262
 *
 */
@SuppressWarnings("all")
public class MemberLevelPanel extends JPanel
{

	private static final long serialVersionUID = 4155515695549042601L;
	
	private static final Logger LOGGER = LogManager.getLogger(UcRolePanel.class);
	
	private JLabel errInfoLabel = new JLabel();
	
	private JLabel reqParams = new JLabel(Properties_language_Utils.getValue("reqParams"));
	
	private JLabel corpIdLabel = new JLabel("corpId:");
	private JTextField corpIdField = new JTextField(10);
	
	private JButton queryBtn = new MyButton(Properties_language_Utils.
			getValue("enter.MemberLevelPanel.queryBtn"));
	

	
	public MemberLevelPanel() 
	{
		add(getPanels());
		init();
	}
	
	private JPanel getPanels()
	{
		GridBagLayout gridbag = new GridBagLayout();
	    GridBagConstraints c = new GridBagConstraints();
	    JPanel panel = new JPanel(gridbag);
		
		buildPanel(panel, gridbag, c, new JComponent[] {reqParams}, 0, 0, 20, 40, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {corpIdLabel}, 0, 3, 20, 10, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {corpIdField}, 1, 3, 20, 10, 1, 1);
		//按钮
		//button
		buildPanel(panel, gridbag, c, new JComponent[] {queryBtn}, 0, 4, 5, 10, 1, 1);
		
		buildPanel(panel, gridbag, c, new JComponent[] {errInfoLabel}, 0, 5, 30, 5, 4, 1);
		
	    //报文位置
		//message location
		buildPanel(panel, gridbag, c, new JComponent[] {new LogPanel()}, 4, 0, 10, 20, 4, 10);
		
		return panel;
	}
	
	private void init() 
	{
		//查询成员级别按钮事件
		//Query member level button events
		this.queryBtn.addMouseListener(new MouseAdapter() 
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
						queryMemberLevel();
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
	
	//查询成员级别
	//Query member level
	private void queryMemberLevel() 
	{
		try 
		{
			Token token = LoginUtils.getToken();
			 
			// 利用token发送restful请求
			// Send a restful request using token
			RestRequest request = new RestRequest(HTTPConstant.HTTP_METHOD_GET);
			EcService.get("/corp/" + corpIdField.getText() + "/memberlevel", 
					request, errInfoLabel, token);
			 
			EcService.finish();
			 
		} 
		catch (RuntimeException e) 
		{
			LOGGER.error("get Token error");
		}
		catch (Exception e) 
		{
			LOGGER.error("get Token error");
		}
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
