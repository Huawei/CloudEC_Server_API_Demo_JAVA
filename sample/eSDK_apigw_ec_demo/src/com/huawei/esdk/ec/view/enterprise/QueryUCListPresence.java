package com.huawei.esdk.ec.view.enterprise;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
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
import com.huawei.esdk.ec.constant.HTTPConstant;
import com.huawei.esdk.ec.service.EcService;
import com.huawei.esdk.ec.utils.LoginUtils;
import com.huawei.esdk.ec.utils.Properties_language_Utils;
import com.huawei.esdk.ec.utils.StringUtils;
import com.huawei.esdk.ec.view.common.LogPanel;
import com.huawei.esdk.ec.view.common.MyButton;
/**
 * 查询EC用户状态信息视图
 * Query EC user status information view
 * @author wwx534262
 *
 */
@SuppressWarnings("all")
public class QueryUCListPresence extends JPanel
{
	private static final long serialVersionUID = 1L;
	
	private static final Logger LOGGER = LogManager.getLogger(QueryUCListPresence.class);
	
	private JLabel errInfoLabel = new JLabel();
	
	private JTextField ECUserField = new JTextField(10);
	
	private JTextField queryUsersField = new JTextField(10);
	
	private JButton queryUserStateBtn = new MyButton(Properties_language_Utils.
			getValue("enter.QueryUCListPresence.queryUserStateBtn"));
	
	private JLabel descLabel = new JLabel(Properties_language_Utils.
			getValue("enter.QueryUCListPresence.descLabel"));
	

	
	public QueryUCListPresence()
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
				0, 0, 20, 40, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {new JLabel("user:")}, 0, 3, 20, 10, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {ECUserField}, 1, 3, 20, 10, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {new JLabel("queryUsers:")}, 0, 4, 20, 50, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {queryUsersField}, 1, 4, 20, 50, 1, 1);
		//按钮
		//button
		buildPanel(panel, gridbag, c, new JComponent[] {queryUserStateBtn}, 1, 5, 5, 10, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {errInfoLabel}, 0, 7, 30, 5, 4, 1);
		
		descLabel.setForeground(Color.blue);
		buildPanel(panel, gridbag, c, new JComponent[] {descLabel}, 0, 6, 100, 5, 4, 1);
		
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
		//查询EC用户状态按钮事件
		//Query EC user status button event
		this.queryUserStateBtn.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
			{
				showErrInfoWithColor("");
				
				boolean flag = validateParams(
						new JTextField[] {queryUsersField},
						new String[] {"queryUsers"});
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
						queryUCListPresence();
					}
		        };
		        Future future = Executors.newSingleThreadExecutor().submit(runnable);
		        if(!future.isDone()) {
		            LOGGER.error("addMouseListener fail");
		        }
			}
		});
	}	

	//查询EC用户状态
	//Query EC user status 
	private void queryUCListPresence()
	{
		//拼装请求参数，发送请求
		//Assembly request parameters, send request
		Map<String, String> param = new HashMap<String, String>();
		String[] queryUsersText = queryUsersField.getText().trim().split(",");
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("[");
		for (String queryUserText : queryUsersText) 
		{
			stringBuffer.append("\"").append(queryUserText).append("\"").append(",");
		}
		String substring = stringBuffer.toString().substring(0, stringBuffer.toString().length() -1) + "]";
		param.put("queryUsers", substring);
		if (StringUtils.isNotEmpty(ECUserField.getText().trim())) 
		{
			param.put("user", ECUserField.getText());
		}
		
		try 
		{
			Token token = LoginUtils.getToken();
			 
			// 利用token发送restful请求
			// Send a restful request using token
			RestRequest request = new RestRequest(HTTPConstant.HTTP_METHOD_GET);
			request.setParameters(param); 
			
			EcService.get("/im/presence", request, errInfoLabel, token);
			 
		} 
		catch (Exception e) 
		{
			LOGGER.error("get Token error",e);
			showErrInfoWithColor(Properties_language_Utils.getValue("resultTip"));
		}
		finally
		{
			EcService.finish();
		}
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
		if(null == errInfo)
		{
			return;
		}
		
		errInfoLabel.setForeground(Color.red);
		errInfoLabel.setText(errInfo);
	}
	
}
