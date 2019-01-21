package com.huawei.esdk.ec.view.admin;

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
import com.huawei.esdk.ec.utils.StringUtils;
import com.huawei.esdk.ec.view.common.LogPanel;
import com.huawei.esdk.ec.view.common.MyButton;
import com.huawei.esdk.ec.view.enterprise.OcxpfxPanel;
/**
 * 服务提供商查询接口视图
 * Service provider query interface view
 * @author wWX534262
 *
 */
@SuppressWarnings("all")
public class SpManagePanel extends JPanel implements ActionListener
{

	private static final long serialVersionUID = -684398873743548718L;
	
	private static final Logger LOGGER = LogManager.getLogger(SpManagePanel.class);
	
	private JLabel errInfoLabel = new JLabel();
	
	private JLabel reqParams = new JLabel(Properties_language_Utils.getValue("reqParams"));
	
	private JLabel spIdLabel = new JLabel("spId:");
	private JTextField spIdField = new JTextField(10);
	
	private JLabel pageIndexLabel = new JLabel("pageIndex:");
	private JTextField pageIndexField = new JTextField(10);
	
	private JLabel pageSizeLabel = new JLabel("pageSize:");
	private JTextField pageSizeField = new JTextField(10);
	
	private JLabel searchKeyLabel = new JLabel("searchKey:");
	private JTextField searchKeyField = new JTextField(10);
	
	private JLabel meetingTypeLabel = new JLabel("meetingType:");
	private JTextField meetingTypeField = new JTextField(10);
	
	//查询服务提供商按钮
	private JButton searchSpBtn = new MyButton(Properties_language_Utils.
			getValue("admin.SpManagePanel.searchSpBtn"),200,35);
	
	//查询服务提供商已用资源按钮
	private JButton searchUsedResourceBtn = new MyButton(Properties_language_Utils.
			getValue("admin.SpManagePanel.searchUsedResourceBtn"),200,35);
	
	//分页查询服务提供商按钮
	private JButton pageQuerySpBtn = new MyButton(Properties_language_Utils.
			getValue("admin.SpManagePanel.pageQuerySpBtn"), 200, 35);
	
	//增加服务提供商按钮
	private JButton addSpBtn = new MyButton(Properties_language_Utils.
			getValue("admin.SpManagePanel.addSpBtn"), 200, 35);
	
	//修改服务提供商按钮
	private JButton modifySpBtn = new MyButton(Properties_language_Utils.
			getValue("admin.SpManagePanel.modifySpBtn"), 200, 35);
	
	//删除服务提供商按钮
	private JButton delSpBtn = new MyButton(Properties_language_Utils.
			getValue("admin.SpManagePanel.delSpBtn"), 200, 35);
	
	public SpManagePanel() 
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
	    
	    buildPanel(panel, gridbag, c, new JComponent[] {spIdLabel}, 0, 2, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {spIdField}, 1, 2, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {pageIndexLabel}, 2, 2, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {pageIndexField}, 3, 2, 10, 20, 1, 1);
	    
	    buildPanel(panel, gridbag, c, new JComponent[] {pageSizeLabel}, 0, 3, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {pageSizeField}, 1, 3, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {searchKeyLabel}, 2, 3, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {searchKeyField}, 3, 3, 10, 20, 1, 1);
	    
	    buildPanel(panel, gridbag, c, new JComponent[] {meetingTypeLabel}, 0, 4, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {meetingTypeField}, 1, 4, 10, 20, 1, 1);
	    
	    buildPanel(panel, gridbag, c, new JComponent[] {pageQuerySpBtn}, 0, 5, 10, 20, 2, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {addSpBtn}, 2, 5, 10, 20, 2, 1);
	    addSpBtn.addActionListener(this);
	    buildPanel(panel, gridbag, c, new JComponent[] {modifySpBtn}, 0, 6, 10, 20, 2, 1);
	    modifySpBtn.addActionListener(this);
	    buildPanel(panel, gridbag, c, new JComponent[] {delSpBtn}, 2, 6, 10, 20, 2, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {searchSpBtn}, 0, 7, 10, 20, 2, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {searchUsedResourceBtn}, 2, 7, 10, 20, 2, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {errInfoLabel}, 0, 8, 10, 5, 2, 1);
	    
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
	            if(future.isDone()) 
	            {
	            	LOGGER.info("future.isDone() is true");
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
	            if(future.isDone()) 
	            {
	            	LOGGER.info("future.isDone() is true");
	            }
	        }
		});
		
		//分页查询服务提供商按钮事件
		this.pageQuerySpBtn.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
	        {
				showErrInfoWithColor("");
	            
	            boolean flag = validateParams(
	            		new JTextField[] {pageIndexField, pageSizeField},
	            		new String[] {"pageIndex", "pageSize"});
	                
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
	            		pageQuerySp();
	            	}
	            };
	            Future future = Executors.newSingleThreadExecutor().submit(runnable);
	            if(future.isDone()) 
	            {
	            	LOGGER.info("future.isDone() is true");
	            }
	        }
		});
		
		//删除服务提供商按钮事件
		this.delSpBtn.addMouseListener(new MouseAdapter()
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
	            		delSp();
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
	
	//删除服务提供商
	private void delSp() 
	{
		Token token = LoginUtils.getToken();
		
		RestRequest request = new RestRequest(HTTPConstant.HTTP_METHOD_DELETE);
		
		EcService.delete("/sp/" + spIdField.getText(), request, errInfoLabel,token);
		
		EcService.finish();
	}
	
	//分页查询服务提供商
	private void pageQuerySp() 
	{
		Token token = LoginUtils.getToken();
		
		RestRequest request = new RestRequest(HTTPConstant.HTTP_METHOD_GET);
		Map<String, String> parameters = request.getParameters();
		parameters.put("pageIndex", pageIndexField.getText());
		parameters.put("pageSize", pageSizeField.getText());
		if (StringUtils.isNotEmpty(searchKeyField.getText()))
		{
			parameters.put("searchKey", searchKeyField.getText());
		}
		if (StringUtils.isNotEmpty(meetingTypeField.getText()))
		{
			parameters.put("meetingType", meetingTypeField.getText());
		}
		
		EcService.get("/sp", request, errInfoLabel,token);
		
		EcService.finish();
	}
	
	//查询服务提供商已使用资源
	//Query service provider has used resources
	private void searchUsedResource() 
	{
		RestRequest request = new RestRequest(HTTPConstant.HTTP_METHOD_GET);
		
		Token token = LoginUtils.getToken();
		EcService.get("/sp/" + spIdField.getText() + "/usedResource", request, errInfoLabel,token);
		
		EcService.finish();
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

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		// 构造一个新的JFrame，作为新窗口。
		// Construct a new JFrame as a new window
		JFrame frame = new JFrame(Properties_language_Utils.getValue("admin.SpManagePanel.frameTitle"));
		
		setSize(1050, 680);
	    frame.setSize(1050, 680);
	    
	    JPanel center = new AddSpPanel();
	    JScrollPane centerJPane = new JScrollPane(center);
	    
	    frame.getContentPane().add(centerJPane, BorderLayout.CENTER);
        frame.setResizable(true);
        frame.setVisible(true);
	}
}
