package com.huawei.esdk.ec.view.enterprise;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
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
 * 设备管理视图
 * Device Management View
 * @author wwx534262
 *
 */
@SuppressWarnings("all")
public class DeviceManagementPanel extends JPanel implements ActionListener
{

	private static final long serialVersionUID = 6415691988962077782L;
	
	private static final Logger LOGGER = LogManager.getLogger(DeviceManagementPanel.class);
	
	private JLabel errInfoLabel = new JLabel();
	
	private JLabel reqParams = new JLabel(Properties_language_Utils.getValue("reqParams"));
	
	private JLabel deviceIdLabel = new JLabel("deviceId:");
	private JTextField deviceIdField = new JTextField(10);
	
	private JLabel orgTypeLabel = new JLabel("orgType:");
	private JTextField orgTypeField = new JTextField(10);
	
	private JLabel orgIdLabel = new JLabel("orgId:");
	private JTextField orgIdField = new JTextField(10);
	
	private JLabel isForceDelLabel = new JLabel("isForceDel:");
	private JTextField isForceDelField = new JTextField(10);
	
	private JLabel pageIndexLabel = new JLabel("pageIndex:");
	private JTextField pageIndexField = new JTextField(10);
	
	private JLabel pageSizeLabel = new JLabel("pageSize:");
	private JTextField pageSizeField = new JTextField(10);
	
	private JLabel searchKeyLabel = new JLabel("searchKey:");
	private JTextField searchKeyField = new JTextField(10);
	
	private JLabel deviceTypeLabel = new JLabel("deviceType:");
	private JTextField deviceTypeField = new JTextField(10);
	
	private JLabel assignStatusLabel = new JLabel("assignStatus:");
	private JTextField assignStatusField = new JTextField(10);
	
	private JLabel corpIdLabel = new JLabel("corpId:");
	private JTextField corpIdField = new JTextField(10);
	
	private JLabel tmsManagedLabel = new JLabel("tmsManaged:");
	private JTextField tmsManagedField = new JTextField(10);
	
	private JButton addDeviceBtn = new MyButton(Properties_language_Utils.
			getValue("enter.DeviceManagementPanel.addDeviceBtn"),180,35);
	
	private JButton modifyDeviceBtn = new MyButton(Properties_language_Utils.
			getValue("enter.DeviceManagementPanel.modifyDeviceBtn"),180,35);
	
	private JButton deleDeviceBtn = new MyButton(Properties_language_Utils.
			getValue("enter.DeviceManagementPanel.deleDeviceBtn"),180,35);
	
	private JButton searchDeviceBtn = new MyButton(Properties_language_Utils.
			getValue("enter.DeviceManagementPanel.searchDeviceBtn"),180,35);
	
	private JButton queryPageDeviceBtn = new MyButton(Properties_language_Utils.
			getValue("enter.DeviceManagementPanel.queryPageDeviceBtn"),180,35);
	

	public DeviceManagementPanel() 
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
	    buildPanel(panel, gridbag, c, new JComponent[] {corpIdLabel}, 2, 1, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {corpIdField}, 3, 1, 6, 20, 2, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {pageIndexLabel}, 0, 3, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {pageIndexField}, 1, 3, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {pageSizeLabel}, 2, 3, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {pageSizeField}, 3, 3, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {searchKeyLabel}, 0, 4, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {searchKeyField}, 1, 4, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {orgTypeLabel}, 2, 4, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {orgTypeField}, 3, 4, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {orgIdLabel}, 0, 5, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {orgIdField}, 1, 5, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {isForceDelLabel}, 2, 5, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {isForceDelField}, 3, 5, 10, 20, 1, 1);
	    
	    buildPanel(panel, gridbag, c, new JComponent[] {deviceTypeLabel}, 0, 6, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {deviceTypeField}, 1, 6, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {assignStatusLabel}, 2, 6, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {assignStatusField}, 3, 6, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {tmsManagedLabel}, 0, 7, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {tmsManagedField}, 1, 7, 10, 20, 1, 1);
	  
	    buildPanel(panel, gridbag, c, new JComponent[] {addDeviceBtn}, 0, 8, 10, 20, 2, 1);
	    addDeviceBtn.addActionListener(this);
	    buildPanel(panel, gridbag, c, new JComponent[] {modifyDeviceBtn}, 2, 8, 10, 20, 2, 1);
	    modifyDeviceBtn.addActionListener(this);
	    buildPanel(panel, gridbag, c, new JComponent[] {deleDeviceBtn}, 0, 9, 10, 20, 2, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {searchDeviceBtn}, 2, 9, 10, 20, 2, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {queryPageDeviceBtn}, 0, 10, 10, 20, 2, 1);
	    
	    buildPanel(panel, gridbag, c, new JComponent[] {errInfoLabel}, 2, 7, 100, 5, 4, 1);
	    
	    //报文位置
		//message location
		buildPanel(panel, gridbag, c, new JComponent[] {new LogPanel()}, 4, 0, 10, 20, 4, 10);
	    
	    return panel;
	}
	
	private void init() 
	{
		//分页查询设备按钮事件
		//Pagination query device button events
		this.queryPageDeviceBtn.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
			{
				showErrInfoWithColor("");
			            
			    boolean flag = validateParams(
			    		new JTextField[] {deviceTypeField, pageIndexField, pageSizeField, assignStatusField},
			    		new String[] {"deviceType", "pageIndex", "pageSize", "assignStatus"});
			                
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
	            		queryPageDevice();
	            	}
	            };
	            Future future = Executors.newSingleThreadExecutor().submit(runnable);
		        if(!future.isDone()) {
		            LOGGER.error("addMouseListener fail");
		        }
			}
		});
		
		//查询设备按钮事件
		//Query device button events
		this.searchDeviceBtn.addMouseListener(new MouseAdapter()
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
	            		searchDevice();
	            	}
	            };
	            Future future = Executors.newSingleThreadExecutor().submit(runnable);
		        if(!future.isDone()) {
		            LOGGER.error("addMouseListener fail");
		        }
			}
		});
		
		//删除设备按钮事件
		//Delete device button events
		this.deleDeviceBtn.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
			{
				showErrInfoWithColor("");
			            
			    boolean flag = validateParams(
			    		new JTextField[] {deviceIdField, orgTypeField, orgIdField},
			    		new String[] {"deviceId", "orgType", "orgId"});
			                
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
	            		deleDevice();
	            	}
	            };
	            Future future = Executors.newSingleThreadExecutor().submit(runnable);
		        if(!future.isDone()) {
		            LOGGER.error("addMouseListener fail");
		        }
			}
		});
	}
	
	//分页查询设备
	//Paging query device
	private void queryPageDevice() 
	{
		try 
		{
			Token token = LoginUtils.getToken();
			
			RestRequest request = new RestRequest(HTTPConstant.HTTP_METHOD_GET);
			Map<String, String> parameters = request.getParameters();
			parameters.put("deviceType", deviceTypeField.getText());
			if (StringUtils.isNotEmpty(pageIndexField.getText())) 
			{
				parameters.put("pageIndex", pageIndexField.getText());
			}
			if (StringUtils.isNotEmpty(pageSizeField.getText())) 
			{
				parameters.put("pageSize", pageSizeField.getText());
			}
			if (StringUtils.isNotEmpty(searchKeyField.getText())) 
			{
				parameters.put("searchKey", searchKeyField.getText());
			}
			if (StringUtils.isNotEmpty(assignStatusField.getText())) 
			{
				parameters.put("assignStatus", assignStatusField.getText());
			}
			if (StringUtils.isNotEmpty(corpIdField.getText())) 
			{
				parameters.put("corpId", corpIdField.getText());
			}
			if (StringUtils.isNotEmpty(orgTypeField.getText())) 
			{
				parameters.put("orgType", orgTypeField.getText());
			}
			if (StringUtils.isNotEmpty(orgIdField.getText())) 
			{
				parameters.put("orgId", orgIdField.getText());
			}
			if (StringUtils.isNotEmpty(tmsManagedField.getText())) 
			{
				parameters.put("tmsManaged", tmsManagedField.getText());
			}
			request.setParameters(parameters);
			EcService.get("/device", request, errInfoLabel, token);
			
			EcService.finish();
		} 
		catch (Exception e) 
		{
			showErrInfoWithColor("操作失败");
			LOGGER.error("get Token error:" + e);
		}
	}
	
	//查询设备
	//query device
	private void searchDevice()
	{
		try 
		{
			Token token = LoginUtils.getToken();
			
			RestRequest request = new RestRequest(HTTPConstant.HTTP_METHOD_GET);
			EcService.get("/device/" + deviceIdField.getText(), request, errInfoLabel, token);
			
			EcService.finish();
		} 
		catch (Exception e) 
		{
			showErrInfoWithColor(Properties_language_Utils.getValue("resultTip"));
			LOGGER.error("get Token error:" + e);
		}
	}
	
	//删除设备
	//delete device
	private void deleDevice() 
	{
		try 
		{
			Token token = LoginUtils.getToken();
			
			RestRequest request = new RestRequest(HTTPConstant.HTTP_METHOD_DELETE);
			Map<String, String> parameters = request.getParameters();
			parameters.put("deviceId", deviceIdField.getText());
			parameters.put("orgType", orgTypeField.getText());
			parameters.put("orgId", orgIdField.getText());
			if (StringUtils.isNotEmpty(isForceDelField.getText())) 
			{
				parameters.put("isForceDel", isForceDelField.getText());
			}
			request.setParameters(parameters);
			EcService.delete("/device", request, errInfoLabel, token);
			
			EcService.finish();
		} 
		catch (Exception e) 
		{
			showErrInfoWithColor(Properties_language_Utils.getValue("resultTip"));
			LOGGER.error("get Token error:" + e);
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
		// 构造一个新的JFrame，作为新窗口。
		// Construct a new JFrame as a new window
		JFrame frame = new JFrame(Properties_language_Utils.getValue("enter.DeviceManagementPanel.frameTitle"));
		frame.getContentPane().setLayout(new BorderLayout());
        setSize(1050, 680);
        frame.setSize(1050, 680);
        
        JPanel center = new AddDevicePanel();
        JScrollPane centerJPane = new JScrollPane(center);
        
        frame.getContentPane().add(centerJPane,BorderLayout.CENTER);
        
        frame.setResizable(true);
        frame.setVisible(true);
	}
}
