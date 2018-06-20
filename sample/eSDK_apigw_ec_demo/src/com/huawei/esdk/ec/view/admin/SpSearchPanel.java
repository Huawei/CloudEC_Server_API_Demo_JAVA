package com.huawei.esdk.ec.view.admin;

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
import javax.swing.JTextArea;
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
 * 服务提供商查询接口视图
 * Service provider query interface view
 * @author wWX534262
 *
 */
@SuppressWarnings("all")
public class SpSearchPanel extends JPanel
{

	private static final long serialVersionUID = -684398873743548718L;
	
	private static final Logger LOGGER = LogManager.getLogger(SpSearchPanel.class);
	
	private JLabel errInfoLabel = new JLabel();
	
	private JLabel reqParams = new JLabel(Properties_language_Utils.getValue("reqParams"));
	
	private JLabel spIdLabel = new JLabel("spId:");
	private JTextField spIdField = new JTextField(10);
	
	private JButton searchSpBtn = new MyButton(Properties_language_Utils.getValue("admin.SpSearchPanel.searchSpBtn"),200,35);
	
	private JButton searchUsedResourceBtn = new MyButton(Properties_language_Utils.
			getValue("admin.SpSearchPanel.searchUsedResourceBtn"),200,35);
	
	public SpSearchPanel() 
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
	    buildPanel(panel, gridbag, c, new JComponent[] {spIdLabel}, 0, 2, 10, 40, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {spIdField}, 1, 2, 10, 40, 1, 1);
	    
	    buildPanel(panel, gridbag, c, new JComponent[] {searchSpBtn}, 0, 4, 10, 40, 2, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {searchUsedResourceBtn}, 0, 6, 10, 40, 2, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {errInfoLabel}, 0, 7, 10, 5, 2, 1);
	    
	    //报文位置
	    //message location
	    buildPanel(panel, gridbag, c, new JComponent[] {new LogPanel()}, 4, 0, 10, 20, 4, 10);
	    
	    return panel;
	}
	
	private void init()
	{
		//查询服务提供商已使用资源按钮事件
		//Query service provider has used resource button events
		this.searchUsedResourceBtn.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
	        {
				showErrInfoWithColor("");
	            
	            boolean flag = validateParams(
	            		new JTextField[] {spIdField},
	            		new String[] {"spId"});
	                
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
	            		searchUsedResource();
	            	}
	            };
	            Future future = Executors.newSingleThreadExecutor().submit(runnable);
	            if(!future.isDone()) {
	            	LOGGER.error("addMouseListener fail");
	            }
	        }
		});
		
		//查询服务提供商按钮事件
		//Query service provider button events
		this.searchSpBtn.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
	        {
				showErrInfoWithColor("");
	            
	            boolean flag = validateParams(
	            		new JTextField[] {spIdField},
	            		new String[] {"spId"});
	                
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
	            		searchSp();
	            	}
	            };
	            Future future = Executors.newSingleThreadExecutor().submit(runnable);
	            if(!future.isDone()) {
	            	LOGGER.error("addMouseListener fail");
	            }
	        }
		});
	}
	
	//查询服务提供商已使用资源
	//Query service provider has used resources
	private void searchUsedResource() 
	{
		RestRequest request = new RestRequest(HTTPConstant.HTTP_METHOD_GET);
		
		try 
		{
			Token token = LoginUtils.getToken();
			EcService.get("/sp/" + spIdField.getText() + "/usedResource", request, errInfoLabel,token);
			EcService.finish();
		} catch (Exception e) 
		{
			LOGGER.error("getToken error:" + e);
		}
	}
	
	//查询服务提供商
	//Query Service Provider
	private void searchSp()
	{
		RestRequest request = new RestRequest(HTTPConstant.HTTP_METHOD_GET);
		
		try 
		{
			Token token = LoginUtils.getToken();
			EcService.get("/sp/" + spIdField.getText(), request, errInfoLabel,token);
			EcService.finish();
		} catch (Exception e) 
		{
			LOGGER.error("getToken error:" + e);
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
