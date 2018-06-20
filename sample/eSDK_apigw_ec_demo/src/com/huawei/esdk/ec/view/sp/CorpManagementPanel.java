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
 * 企业管理接口视图
 * Enterprise Management Interface view
 * @author wWX534262
 *
 */
@SuppressWarnings("all")
public class CorpManagementPanel extends JPanel implements ActionListener 
{

	private static final long serialVersionUID = 2491978798145040874L;
	
	private static final Logger LOGGER = LogManager.getLogger(CorpManagementPanel.class);
	
	private JLabel errInfoLabel = new JLabel();
	
	private JLabel reqParams = new JLabel(Properties_language_Utils.getValue("reqParams"));
	
	private JLabel spIdLabel = new JLabel("spId:");
	private JTextField spIdField = new JTextField(10);
	
	private JLabel corpIdLabel = new JLabel("corpId:");
	private JTextField corpIdField = new JTextField(10);
	
	private JLabel pageIndexLabel = new JLabel("pageIndex:");
	private JTextField pageIndexField = new JTextField(10);
	
	private JLabel pageSizeLabel = new JLabel("pageSize:");
	private JTextField pageSizeField = new JTextField(10);
	
	private JLabel searchKeyLabel = new JLabel("searchKey:");
	private JTextField searchKeyField = new JTextField(10);
	
	private JLabel searchCharLabel = new JLabel("searchChar:");
	private JTextField searchCharField = new JTextField(10);
	
	private JButton corpAddBtn = new MyButton(Properties_language_Utils.
			getValue("sp.corpManagementPanel.corpAddBtn"),200,35);
	
	private JButton modifyCorpBtn = new MyButton(Properties_language_Utils.
			getValue("sp.corpManagementPanel.modifyCorpBtn"),200,35);
	
	private JButton deleCorpBtn = new MyButton(Properties_language_Utils.
			getValue("sp.corpManagementPanel.deleCorpBtn"),200,35);
	
	private JButton searchCorpBtn = new MyButton(Properties_language_Utils.
			getValue("sp.corpManagementPanel.searchCorpBtn"),200,35);
	
	private JButton queryPageCorpBtn = new MyButton(Properties_language_Utils.
			getValue("sp.corpManagementPanel.queryPageCorpBtn"),200,35);
	
	private JButton queryCorpResourceBtn = new MyButton(Properties_language_Utils.
			getValue("sp.corpManagementPanel.queryCorpResourceBtn"),200,35);
	
	public CorpManagementPanel() 
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
	    buildPanel(panel, gridbag, c, new JComponent[] {spIdLabel}, 0, 1, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {spIdField}, 1, 1, 10, 20, 2, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {corpIdLabel}, 2, 1, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {corpIdField}, 3, 1, 6, 20, 2, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {pageIndexLabel}, 0, 3, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {pageIndexField}, 1, 3, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {pageSizeLabel}, 2, 3, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {pageSizeField}, 3, 3, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {searchKeyLabel}, 0, 4, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {searchKeyField}, 1, 4, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {searchCharLabel}, 2, 4, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {searchCharField}, 3, 4, 10, 20, 1, 1);
	  
	    buildPanel(panel, gridbag, c, new JComponent[] {corpAddBtn}, 0, 6, 10, 20, 2, 1);
	    corpAddBtn.addActionListener(this);
	    buildPanel(panel, gridbag, c, new JComponent[] {modifyCorpBtn}, 2, 6, 10, 20, 2, 1);
	    modifyCorpBtn.addActionListener(this);
	    buildPanel(panel, gridbag, c, new JComponent[] {deleCorpBtn}, 0, 7, 10, 20, 2, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {searchCorpBtn}, 2, 7, 10, 20, 2, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {queryPageCorpBtn}, 0, 8, 10, 20, 2, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {queryCorpResourceBtn}, 2, 8, 10, 20, 2, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {errInfoLabel}, 0, 9, 100, 5, 4, 1);
	    
	    //报文位置
		//message location
		buildPanel(panel, gridbag, c, new JComponent[] {new LogPanel()}, 4, 0, 10, 20, 4, 10);
	    return panel;
	}
	
	private void init() 
	{
		//查询企业资源按钮事件
		//Query enterprise resource button events
		this.queryCorpResourceBtn.addMouseListener(new MouseAdapter()
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
	            		 queryCorpResource();
	            	 }
				 };
				 Future future = Executors.newSingleThreadExecutor().submit(runnable);
			     if(!future.isDone()) {
			         LOGGER.error("addMouseListener fail");
			     }
	         }
	    });
		
		//查询企业按钮事件
		//Query enterprise button events
		this.searchCorpBtn.addMouseListener(new MouseAdapter()
        {
			 public void mouseClicked(MouseEvent e)
	         {
				 showErrInfoWithColor("");
	            
	             boolean flag = validateParams(
	            		 new JTextField[] {corpIdField, spIdField},
	            		 new String[] {"corpId", "spId"});
	                
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
	            		 searchCorp();
	            	 }
				 };
				 Future future = Executors.newSingleThreadExecutor().submit(runnable);
			     if(!future.isDone()) {
			         LOGGER.error("addMouseListener fail");
			     }
	         }
	    });
		
		//删除企业按钮事件
		//Delete enterprise button event
		this.deleCorpBtn.addMouseListener(new MouseAdapter()
        {
			 public void mouseClicked(MouseEvent e)
	         {
				 showErrInfoWithColor("");
	            
	             boolean flag = validateParams(
	            		 new JTextField[] {corpIdField, spIdField},
	            		 new String[] {"corpId", "spId"});
	                
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
	            		 deleCorp();
	            	 }
				 };
				 Future future = Executors.newSingleThreadExecutor().submit(runnable);
			     if(!future.isDone()) {
			         LOGGER.error("addMouseListener fail");
			     }
	         }
	    });
		
		// 分页查询企业按钮事件
		// Pagination Query Enterprise Button Events
		this.queryPageCorpBtn.addMouseListener(new MouseAdapter()
        {
			 public void mouseClicked(MouseEvent e)
	         {
				 showErrInfoWithColor("");
	            
	             boolean flag = validateParams(
	            		 new JTextField[] {pageIndexField, pageSizeField, spIdField},
	            		 new String[] {"pageIndex", "pageSize", "spId"});
	                
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
	            		 String pageIndex = pageIndexField.getText();
	                	 String pageSize = pageSizeField.getText();
	                	 String searchKey = searchKeyField.getText();
	                	 String searchChar = searchCharField.getText();
	                	 
	                     queryPageCorp(pageIndex, pageSize, searchKey, searchChar);
	            	 }
				 };
				 Future future = Executors.newSingleThreadExecutor().submit(runnable);
			     if(!future.isDone()) {
			         LOGGER.error("addMouseListener fail");
			     }
	         }
	    });
	}
	
	//查询企业资源
	//Query enterprise resources
	private void queryCorpResource() 
	{
		RestRequest request = new RestRequest(HTTPConstant.HTTP_METHOD_GET);
		try 
		{
			Token token = LoginUtils.getToken();
			EcService.get("/sp/corp/" + corpIdField.getText() + "/resource", request, errInfoLabel,token);
			EcService.finish();
		} catch (Exception e) 
		{
			LOGGER.error("getToken error:" + e);
		}
	}
	
	//查询企业
	//Query enterprise
	private void searchCorp() 
	{
		RestRequest request = new RestRequest(HTTPConstant.HTTP_METHOD_GET);
		try 
		{
			Token token = LoginUtils.getToken();
			EcService.get("/sp/" + spIdField.getText() + "/corp/" + corpIdField.getText(), request, errInfoLabel,token);
			EcService.finish();
		} catch (Exception e) 
		{
			LOGGER.error("getToken error:" + e);
		}
	}
	
	//删除企业
	//delete enterprise
	private void deleCorp() 
	{
		RestRequest request = new RestRequest(HTTPConstant.HTTP_METHOD_DELETE);
		try 
		{
			Token token = LoginUtils.getToken();
			EcService.delete("/sp/" + spIdField.getText() + "/corp/" + corpIdField.getText(), request, errInfoLabel,token);
			EcService.finish();
		} catch (Exception e) 
		{
			LOGGER.error("getToken error:" + e);
		}
	}
	
	//分页查询企业
	//Pagination query enterprise
	private void queryPageCorp(String pageIndex, String pageSize, String searchKey,
			String searchChar)
	{
		RestRequest request = new RestRequest(HTTPConstant.HTTP_METHOD_GET);
		Map<String, String> parameters = request.getParameters();
		parameters.put("pageIndex", pageIndex);
		parameters.put("pageSize", pageSize);
		if (StringUtils.isNotEmpty(searchKey)) 
		{
			parameters.put("searchKey", searchKey);
		}
		if (StringUtils.isNotEmpty(searchChar)) 
		{
			parameters.put("searchChar", searchChar);
		}
		
		request.setParameters(parameters);
		try 
		{
			Token token = LoginUtils.getToken();
			EcService.get("/sp/" + spIdField.getText() + "/corp", request, errInfoLabel,token);
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
		JFrame frame = new JFrame(Properties_language_Utils.getValue("sp.corpManagementPanel.frameTitle"));
		
		setSize(1050, 680);
	    frame.setSize(1050, 680);
	    
	    JPanel center = new AddCorpPanel();
	    JScrollPane centerJPane = new JScrollPane(center);
	    
	    frame.getContentPane().add(centerJPane, BorderLayout.CENTER);
        frame.setResizable(true);
        frame.setVisible(true);
	}
}
