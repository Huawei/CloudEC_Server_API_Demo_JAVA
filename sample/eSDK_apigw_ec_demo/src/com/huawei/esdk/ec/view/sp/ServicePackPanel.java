package com.huawei.esdk.ec.view.sp;

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
 * 业务包管理视图
 * service package management view
 * @author wWX534262
 *
 */
@SuppressWarnings("all")
public class ServicePackPanel extends JPanel implements ActionListener
{
	private static final long serialVersionUID = 2996295440437217443L;
	
	private static final Logger LOGGER = LogManager.getLogger(ServicePackPanel.class);
	
	private JLabel errInfoLabel = new JLabel();
	
	private JLabel reqParams = new JLabel(Properties_language_Utils.getValue("reqParams"));
	
	private JLabel orgIdLabel = new JLabel("orgId:");
	private JTextField orgIdField = new JTextField(10);
	
	private JLabel searchKeyLabel = new JLabel("searchKey:");
	private JTextField searchKeyField = new JTextField(10);
	
	private JLabel pageIndexLabel = new JLabel("pageIndex:");
	private JTextField pageIndexField = new JTextField(10);
	
	private JLabel pageSizeLabel = new JLabel("pageSize:");
	private JTextField pageSizeField = new JTextField(10);
	
	private JLabel packTypeLabel = new JLabel("packType:");
	private JTextField packTypeField = new JTextField(10);
	
	private JLabel packNameLabel = new JLabel("packName:");
	private JTextField packNameField = new JTextField(10);
	
	private JButton pageSearchBtn = new MyButton(Properties_language_Utils.getValue("sp.ServicePackPanel.pageSearchBtn"),200,35);
	
	private JButton queryBtn = new MyButton(Properties_language_Utils.getValue("sp.ServicePackPanel.queryBtn"),200,35);
	
	private JButton deleBtn = new MyButton(Properties_language_Utils.getValue("sp.ServicePackPanel.deleBtn"),200,35);
	
	private JButton addBtn = new MyButton(Properties_language_Utils.getValue("sp.ServicePackPanel.addBtn"),200,35);
	
	private JButton modifyBtn = new MyButton(Properties_language_Utils.getValue("sp.ServicePackPanel.modifyBtn"),200,35);
	
	public ServicePackPanel() 
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
	    buildPanel(panel, gridbag, c, new JComponent[] {orgIdLabel}, 0, 2, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {orgIdField}, 1, 2, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {searchKeyLabel}, 2, 2, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {searchKeyField}, 3, 2, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {pageIndexLabel}, 0, 3, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {pageIndexField}, 1, 3, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {pageSizeLabel}, 2, 3, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {pageSizeField}, 3, 3, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {packTypeLabel}, 0, 4, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {packTypeField}, 1, 4, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {packNameLabel}, 2, 4, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {packNameField}, 3, 4, 10, 20, 1, 1);
	    
	    buildPanel(panel, gridbag, c, new JComponent[] {queryBtn}, 0, 5, 10, 20, 2, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {deleBtn}, 2, 5, 10, 20, 2, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {addBtn}, 0, 6, 10, 20, 2, 1);
	    addBtn.addActionListener(this);
	    buildPanel(panel, gridbag, c, new JComponent[] {modifyBtn}, 2, 6, 10, 20, 2, 1);
	    modifyBtn.addActionListener(this);
	    buildPanel(panel, gridbag, c, new JComponent[] {pageSearchBtn}, 0, 7, 10, 20, 2, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {errInfoLabel}, 0, 8, 10, 20, 2, 1);
	    
	    //报文位置
		//message location
		buildPanel(panel, gridbag, c, new JComponent[] {new LogPanel()}, 4, 0, 10, 20, 4, 10);
	    
	    return panel;
	}
	
	private void init() 
	{
		//分页查询业务包信息按钮事件
		//Paging query service package information button event
		this.pageSearchBtn.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
	        {
				showErrInfoWithColor("");
	            
	            boolean flag = validateParams(
	            		new JTextField[] {orgIdField, pageIndexField, pageSizeField, packTypeField},
	            		new String[] {"orgId", "pageIndex", "pageSize", "packType"});
	                
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
            			String orgId = orgIdField.getText();
            			String searchKey = searchKeyField.getText();
            			String packType = packTypeField.getText();
            			String pageIndex = pageIndexField.getText();
            			String pageSize = pageSizeField.getText();
                 	 
            			pageSearch(orgId, searchKey, packType, pageIndex, pageSize);
            		}
				};
				Future future = Executors.newSingleThreadExecutor().submit(runnable);
				if(future.isDone()) 
	            {
	            	LOGGER.info("future.isDone() is true");
	            }
	        }
		});
		
		//查询业务包信息按钮事件
		//Query service package information button events
		this.queryBtn.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
	        {
				showErrInfoWithColor("");
	            
	            boolean flag = validateParams(
	            		new JTextField[] {packNameField, orgIdField},
	            		new String[] {"packName", "orgId"});
	                
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
            			String orgId = orgIdField.getText();
                    	query(orgId);
            		}
				};
				Future future = Executors.newSingleThreadExecutor().submit(runnable);
				if(future.isDone()) 
	            {
	            	LOGGER.info("future.isDone() is true");
	            }
	        }
		});
		
		//删除业务包信息按钮事件
		//Delete Service Package Information Button Event
		this.deleBtn.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
	        {
				showErrInfoWithColor("");
	            
	            boolean flag = validateParams(
	            		new JTextField[] {packNameField, orgIdField},
	            		new String[] {"packName", "orgId"});
	                
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
            			dele();
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
	
	//删除业务包信息
	//Delete Service Package Information
	private void dele() 
	{
		try 
		{
			Token token = LoginUtils.getToken();
			
			// 利用token发送restful请求
			// Send a restful request using token
			RestRequest request = new RestRequest(HTTPConstant.HTTP_METHOD_DELETE);
			
			Map<String, String> parameters = request.getParameters();
			parameters.put("packName", packNameField.getText());
			parameters.put("orgId", orgIdField.getText());
			
			request.setParameters(parameters);
			EcService.delete("/servicepack", request, errInfoLabel, token);
			
			EcService.finish();
		} 
		catch (Exception e) 
		{
			LOGGER.error("get token error :" + e);
		}
	}
	
	//查询业务包信息
	//Query Service Package Information
	private void query(String orgId) 
	{
		try 
		{
			Token token = LoginUtils.getToken();
			
			// 利用token发送restful请求
			// Send a restful request using token
			RestRequest request = new RestRequest(HTTPConstant.HTTP_METHOD_GET);
			
			Map<String, String> parameters = request.getParameters();
			parameters.put("orgId", orgId);
				
			request.setParameters(parameters);
			
			EcService.get("/servicepack/" + packNameField.getText(), request, errInfoLabel, token);
			EcService.finish();
			
		} 
		catch (Exception e) 
		{
			LOGGER.error("get token error :" + e);
		}
	}
	
	//分页查询业务包信息
	//Paging Query Service Package Information
	private void pageSearch(String orgId, String searchKey, String packType, String pageIndex,
			String pageSize) 
	{
		try 
		{
			Token token = LoginUtils.getToken();
			
			// 利用token发送restful请求
			// Send a restful request using token
			RestRequest request = new RestRequest(HTTPConstant.HTTP_METHOD_GET);
			
			Map<String, String> parameters = request.getParameters();
			parameters.put("orgId", orgId);
			parameters.put("pageIndex", pageIndex);
			parameters.put("pageSize", pageSize);
			parameters.put("packType", packType);
			if (StringUtils.isNotEmpty(searchKey)) 
			{
				parameters.put("searchKey", searchKey);
			}
			
			request.setParameters(parameters);
		
			EcService.get("/servicepack", request, errInfoLabel, token);
			EcService.finish();
		} 
		catch (Exception e) 
		{
			LOGGER.error("get token error :" + e);
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
		// Construct a new JFrame as a new window.
		JFrame frame = new JFrame(Properties_language_Utils.getValue("sp.ServicePackPanel.frameTitle"));
		
		setSize(1050, 680);
	    frame.setSize(1050, 680);
	    
	    JPanel center = new AddServicePackPanel();
	    JScrollPane centerJPane = new JScrollPane(center);
	    
	    frame.getContentPane().add(centerJPane, BorderLayout.CENTER);
	    
        frame.setResizable(true);
        frame.setVisible(true);
	}
}
