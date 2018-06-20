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
 * 企业管理员视图
 * Enterprise administrator view
 * @author wWX534262
 *
 */
@SuppressWarnings("all")
public class CorpManagerPanel extends JPanel implements ActionListener
{
	private static final long serialVersionUID = -144460592410020013L;
	
	private static final Logger LOGGER = LogManager.getLogger(CorpManagerPanel.class);
	
	private JLabel errInfoLabel = new JLabel();
	private JLabel tipInfoLabel = new JLabel(Properties_language_Utils.
			getValue("enter.CorpManagerPanel.tipInfoLabel"));
	
	private JLabel reqParams = new JLabel(Properties_language_Utils.getValue("reqParams"));
	
	private JLabel corpIdLabel = new JLabel("corpId:");
	private JTextField corpIdField = new JTextField(10);
	
	private JLabel pageIndexLabel = new JLabel("pageIndex:");
	private JTextField pageIndexField = new JTextField(10);
	
	private JLabel pageSizeLabel = new JLabel("pageSize:");
	private JTextField pageSizeField = new JTextField(10);
	
	private JLabel searchKeyLabel = new JLabel("searchKey:");
	private JTextField searchKeyField = new JTextField(10);
	
	private JLabel onlySearchAdminLabel = new JLabel("onlySearchAdmin:");
	private JTextField onlySearchAdminField = new JTextField(10);
	
	private JLabel userAccountsLabel = new JLabel("userAccounts:");
	private JTextField userAccountsField = new JTextField(10);
	
	private JLabel userAccountLabel = new JLabel("userAccount:");
	private JTextField userAccountField = new JTextField(10);
	
	private JLabel setCorpAdminLabel = new JLabel("setCorpAdmin:");
	private JTextField setCorpAdminField = new JTextField(10);
	
	private JButton addManagerBtn = new MyButton(Properties_language_Utils.
			getValue("enter.CorpManagerPanel.addManagerBtn"),190,35);
	
	private JButton modifyManagerBtn = new MyButton(Properties_language_Utils.
			getValue("enter.CorpManagerPanel.modifyManagerBtn"),190,35);
	
	private JButton deleManagerBtn = new MyButton(Properties_language_Utils.
			getValue("enter.CorpManagerPanel.deleManagerBtn"),190,35);
	
	private JButton searchManagerBtn = new MyButton(Properties_language_Utils.
			getValue("enter.CorpManagerPanel.searchManagerBtn"),190,35);
	
	private JButton queryPageManagerBtn = new MyButton(Properties_language_Utils.
			getValue("enter.CorpManagerPanel.queryPageManagerBtn"),190,35);
	
	
	public CorpManagerPanel() 
	{
		add(getPanels());
		init();
	}
	
	//设置样式
	//interface design
	private JPanel getPanels()
	{
		GridBagLayout gridbag = new GridBagLayout();
	    GridBagConstraints c = new GridBagConstraints();
	    JPanel panel = new JPanel(gridbag);
	    
	    buildPanel(panel, gridbag, c, new JComponent[] {reqParams}, 0, 0, 10, 40, 1, 1);  
	    buildPanel(panel, gridbag, c, new JComponent[] {corpIdLabel}, 0, 1, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {corpIdField}, 1, 1, 10, 20, 2, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {searchKeyLabel}, 2, 1, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {searchKeyField}, 3, 1, 6, 20, 2, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {pageIndexLabel}, 0, 3, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {pageIndexField}, 1, 3, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {pageSizeLabel}, 2, 3, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {pageSizeField}, 3, 3, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {onlySearchAdminLabel}, 0, 4, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {onlySearchAdminField}, 1, 4, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {userAccountsLabel}, 2, 4, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {userAccountsField}, 3, 4, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {userAccountLabel}, 2, 5, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {userAccountField}, 3, 5, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {setCorpAdminLabel}, 0, 5, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {setCorpAdminField}, 1, 5, 10, 20, 1, 1);
	  
	    buildPanel(panel, gridbag, c, new JComponent[] {addManagerBtn}, 0, 6, 10, 20, 2, 1);
	    addManagerBtn.addActionListener(this);
	    buildPanel(panel, gridbag, c, new JComponent[] {modifyManagerBtn}, 2, 6, 10, 20, 2, 1);
	    modifyManagerBtn.addActionListener(this);
	    buildPanel(panel, gridbag, c, new JComponent[] {deleManagerBtn}, 0, 7, 10, 20, 2, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {searchManagerBtn}, 2, 7, 10, 20, 2, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {queryPageManagerBtn}, 0, 8, 10, 20, 2, 1);
	    
	    buildPanel(panel, gridbag, c, new JComponent[] {errInfoLabel}, 0, 9, 100, 5, 4, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {tipInfoLabel}, 2, 8, 100, 5, 4, 1);
	    
	    //报文位置
	    //message location
		buildPanel(panel, gridbag, c, new JComponent[] {new LogPanel()}, 4, 0, 10, 20, 4, 10);
	    return panel;
	}
	
	private void init() 
	{
		tipInfoLabel.setForeground(Color.blue);
		
		//删除企业企业管理员按钮事件
		//Delete Enterprise Enterprise Manager Button Events
		this.deleManagerBtn.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
			{
				showErrInfoWithColor("");
			            
				boolean flag = validateParams(
						new JTextField[] {corpIdField, userAccountsField, setCorpAdminField},
			            new String[] {"corpId", "userAccounts", "setCorpAdmin"});
			                
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
	            		deleManager();
	            	}
	            };
	            Future future = Executors.newSingleThreadExecutor().submit(runnable);
	            if(!future.isDone()) {
	            	LOGGER.error("addMouseListener fail");
	            }
			}
		});
		
		//查询企业管理员按钮事件
		//Query Enterprise Manager Button Events
		this.searchManagerBtn.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
			{
				showErrInfoWithColor("");
			            
				boolean flag = validateParams(
						new JTextField[] {corpIdField, userAccountField},
			            new String[] {"corpId", "userAccount"});
			                
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
	            		searchManager();
	            	}
	            };
	            Future future = Executors.newSingleThreadExecutor().submit(runnable);
	            if(!future.isDone()) {
	            	LOGGER.error("addMouseListener fail");
	            }
			}
		});
		
		//分页查询企业管理员按钮上事件
		//Paging Query Enterprise Manager Button Events
		this.queryPageManagerBtn.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
			{
				showErrInfoWithColor("");
			            
				boolean flag = validateParams(
						new JTextField[] {corpIdField ,pageIndexField, pageSizeField},
			            new String[] {"corpId", "pageIndex", "pageSize"});
			                
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
	                 	String onlySearchAdmin = onlySearchAdminField.getText();
	                 	
	                 	queryPageManager(pageIndex, pageSize, searchKey, onlySearchAdmin);
	            	}
	            };
	            Future future = Executors.newSingleThreadExecutor().submit(runnable);
	            if(!future.isDone()) {
	            	LOGGER.error("addMouseListener fail");
	            }
			}
		});
	}
	
	//删除企业管理员
	//Delete enterprise administrator
	private void deleManager() 
	{
		try 
		{
			Token token = LoginUtils.getToken();
			
			// 利用token发送restful请求
			// Send a restful request using token
			RestRequest request = new RestRequest(HTTPConstant.HTTP_METHOD_DELETE);
			
			//参数拼接
			//Parameter stitching
			StringBuffer stringBuffer = new StringBuffer();
			String[] split = userAccountsField.getText().split(",");
			stringBuffer.append("[");
			for (String userAccount : split) 
			{
				stringBuffer.append("\"").append(userAccount).append("\"").append(",");
			}
			String members = stringBuffer.toString().substring(0, stringBuffer.toString().length() - 1);
			String userAccountsParam = members + "]";
			Map<String, String> parameters = request.getParameters();
			parameters.put("userAccounts", userAccountsParam);
			
			parameters.put("setCorpAdmin", setCorpAdminField.getText());
			
			request.setParameters(parameters);
			EcService.delete("/corp/" + corpIdField.getText() + "/userAccount",  request, errInfoLabel, token);
			
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
	
	//分页查询企业管理员
	//Paging query enterprise administrator
	private void queryPageManager(String pageIndex, String pageSize, String searchKey,
			String onlySearchAdmin) 
	{
		try 
		{
			Token token = LoginUtils.getToken();
			
			// 利用token发送restful请求
			// Send a restful request using token
			RestRequest request = new RestRequest(HTTPConstant.HTTP_METHOD_GET);
			Map<String, String> parameters = request.getParameters();
			parameters.put("pageIndex", pageIndex);
			parameters.put("pageSize", pageSize);
			if (StringUtils.isNotEmpty(searchKey)) 
			{
				parameters.put("searchKey", searchKey);
			}
			if (StringUtils.isNotEmpty(onlySearchAdmin)) 
			{
				parameters.put("onlySearchAdmin", onlySearchAdmin);
			}
			
			request.setParameters(parameters);
			EcService.get("/corp/" + corpIdField.getText() + "/userAccount", request, errInfoLabel, token);
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
	
	//查询企业管理员
	//Query enterprise administrator
	private void searchManager() 
	{
		try
		{
			Token token = LoginUtils.getToken();
			
			RestRequest request = new RestRequest(HTTPConstant.HTTP_METHOD_GET);
			
			String userAccount = userAccountField.getText().replace("@", "%40");
			EcService.get("/corp/" + corpIdField.getText() + "/userAccount/" + userAccount, 
						request, errInfoLabel, token);
			
			EcService.finish();
		} 
		catch (Exception e)
		{
			LOGGER.error("get Token error",e);
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
	
	//判断参数为空性
	//Judgment parameter is empty
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
		JFrame frame = new JFrame(Properties_language_Utils.getValue("enter.CorpManagerPanel.frameTitle"));
		
		setSize(1050, 680);
	    frame.setSize(1050, 680);
	    
	    JPanel center = new AddCorpManagerPanel();
	    JScrollPane centerJPane = new JScrollPane(center);
	    
	    frame.getContentPane().add(centerJPane, BorderLayout.CENTER);
	    
        frame.setResizable(true);
        frame.setVisible(true);
	}
	
}
