package com.huawei.esdk.ec.view.admin;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.huawei.esdk.ec.bean.admin.SPUser;
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
 * 服务提供商管理员接口视图
 * @author wWX534262
 *
 */
public class SpManagerPanel extends JPanel
{
	private static final long serialVersionUID = -6194243062139966632L;
	
	private static final Logger LOGGER = LogManager.getLogger(SpManagerPanel.class);
	
	private JLabel errInfoLabel = new JLabel();
	
	private JLabel reqParams = new JLabel(Properties_language_Utils.getValue("reqParams"));
	
	private JLabel spIdLabel = new JLabel("spId:");
	private JTextField spIdField = new JTextField(10);
	
	private JLabel accountLabel = new JLabel("account:");
	private JTextField accountField = new JTextField(10);
	
	private JLabel pageIndexLabel = new JLabel("pageIndex:");
	private JTextField pageIndexField = new JTextField(10);
	
	private JLabel pageSizeLabel = new JLabel("pageSize:");
	private JTextField pageSizeField = new JTextField(10);
	
	private JLabel searchKeyLabel = new JLabel("searchKey:");
	private JTextField searchKeyField = new JTextField(10);
	
	//增加、修改服务提供商管理员参数
	private JLabel nameLabel = new JLabel("name:");
	private JTextField nameField = new JTextField(10);
	
	private JLabel pwdLabel = new JLabel("pwd:");
	private JPasswordField pwdField = new JPasswordField(10);
	
	private JLabel adminTypeLabel = new JLabel("adminType:");
	private JTextField adminTypeField = new JTextField(10);
	
	private JLabel rolenameLabel = new JLabel("rolename:");
	private JTextField rolenameField = new JTextField(10);
	
	private JLabel mobileLabel = new JLabel("mobile");
	private JTextField mobileField = new JTextField(10);
	
	private JLabel emailLabel = new JLabel("email:");
	private JTextField emailField = new JTextField(10);
	
	private JLabel readOnlyAdminTypeLabel = new JLabel("readOnlyAdminType:");
	private JTextField readOnlyAdminTypeField = new JTextField(10);
	
	//分页查询服务提供商管理员按钮
	private JButton pageQuerySpManagerBtn = new MyButton(Properties_language_Utils.
			getValue("admin.SpManagerpanel.pageQuerySpManagerBtn"),250,35);
	
	//增加服务提供商管理员按钮
	private JButton addSpManagerBtn = new MyButton(Properties_language_Utils.
			getValue("admin.SpManagerpanel.addSpManagerBtn"),250,35);
	
	//修改服务提供商管理员按钮
	private JButton modifySpManagerBtn = new MyButton(Properties_language_Utils.
			getValue("admin.SpManagerpanel.modifySpManagerBtn"),250,35);
	
	//删除服务提供商管理员按钮
	private JButton delSpManagerBtn = new MyButton(Properties_language_Utils.
			getValue("admin.SpManagerpanel.delSpManagerBtn"),250,35);
	
	//查询服务提供商管理员按钮
	private JButton querySpManagerBtn = new MyButton(Properties_language_Utils.
			getValue("admin.SpManagerpanel.querySpManagerBtn"),250,35);
	
	private char[] pwd = null;
	public SpManagerPanel() 
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
	    
	    buildPanel(panel, gridbag, c, new JComponent[] {spIdLabel}, 0, 1, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {spIdField}, 1, 1, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {accountLabel}, 2, 1, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {accountField}, 3, 1, 10, 20, 1, 1);
	    
	    buildPanel(panel, gridbag, c, new JComponent[] {pageSizeLabel}, 0, 2, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {pageSizeField}, 1, 2, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {pageIndexLabel}, 2, 2, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {pageIndexField}, 3, 2, 10, 20, 1, 1);
	    
	    buildPanel(panel, gridbag, c, new JComponent[] {searchKeyLabel}, 0, 3, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {searchKeyField}, 1, 3, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {nameLabel}, 2, 3, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {nameField}, 3, 3, 10, 20, 1, 1);
	    
	    buildPanel(panel, gridbag, c, new JComponent[] {pwdLabel}, 0, 4, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {pwdField}, 1, 4, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {adminTypeLabel}, 2, 4, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {adminTypeField}, 3, 4, 10, 20, 1, 1);
	    
	    buildPanel(panel, gridbag, c, new JComponent[] {rolenameLabel}, 0, 5, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {rolenameField}, 1, 5, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {mobileLabel}, 2, 5, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {mobileField}, 3, 5, 10, 20, 1, 1);
	    
	    buildPanel(panel, gridbag, c, new JComponent[] {emailLabel}, 0, 6, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {emailField}, 1, 6, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {readOnlyAdminTypeLabel}, 2, 6, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {readOnlyAdminTypeField}, 3, 6, 10, 20, 1, 1);
	    
	    buildPanel(panel, gridbag, c, new JComponent[] {pageQuerySpManagerBtn}, 0, 7, 10, 20, 2, 1);
	    
	    buildPanel(panel, gridbag, c, new JComponent[] {addSpManagerBtn}, 2, 7, 10, 20, 2, 1);
	    
	    buildPanel(panel, gridbag, c, new JComponent[] {modifySpManagerBtn}, 0, 8, 10, 20, 2, 1);
	    
	    buildPanel(panel, gridbag, c, new JComponent[] {delSpManagerBtn}, 2, 8, 10, 20, 2, 1);
	    
	    buildPanel(panel, gridbag, c, new JComponent[] {querySpManagerBtn}, 0, 9, 10, 20, 2, 1);
	    
	    buildPanel(panel, gridbag, c, new JComponent[] {errInfoLabel}, 2, 9, 10, 5, 2, 1);
	    
	    //报文位置
	    //message location
	    buildPanel(panel, gridbag, c, new JComponent[] {new LogPanel()}, 4, 0, 10, 20, 4, 10);
	    
	    return panel;
	
	}
	
	private void init()
	{
		//分页查询服务提供商管理员按钮事件
		this.pageQuerySpManagerBtn.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
	        {
				showErrInfoWithColor("");
	            
	            boolean flag = validateParams(
	            		new JTextField[] {spIdField,pageIndexField, pageSizeField},
	            		new String[] {"spId", "pageIndex", "pageSize"});
	                
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
	            		pageQuerySpManager();
	            	}
	            };
	            Future<?> future = Executors.newSingleThreadExecutor().submit(runnable);
	            if(future.isDone()) 
	            {
	            	LOGGER.info("future.isDone() is true");
	            }
	        }
		});
		
		//增加服务提供商管理员按钮事件
		this.addSpManagerBtn.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
	        {
				showErrInfoWithColor("");
	            
	            boolean flag = validateParams(
	            		new JTextField[] {spIdField, accountField, nameField, pwdField},
	            		new String[] {"spId", "account", "name", "pwd"});
	                
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
	            		addSpManager();
	            	}
	            };
	            Future<?> future = Executors.newSingleThreadExecutor().submit(runnable);
	            if(future.isDone()) 
	            {
	            	LOGGER.info("future.isDone() is true");
	            }
	        }
		});
		
		//修改服务提供商管理员按钮事件
		this.modifySpManagerBtn.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
	        {
				showErrInfoWithColor("");
	            
	            boolean flag = validateParams(
	            		new JTextField[] {spIdField, accountField},
	            		new String[] {"spId", "account"});
	                
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
	            		modifySpManager();
	            	}
	            };
	            Future<?> future = Executors.newSingleThreadExecutor().submit(runnable);
	            if(future.isDone()) 
	            {
	            	LOGGER.info("future.isDone() is true");
	            }
	        }
		});
		
		//删除服务提供商管理员按钮事件
		this.delSpManagerBtn.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
	        {
				showErrInfoWithColor("");
	            
	            boolean flag = validateParams(
	            		new JTextField[] {spIdField, accountField},
	            		new String[] {"spId", "account"});
	                
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
	            		delSpManager();
	            	}
	            };
	            Future<?> future = Executors.newSingleThreadExecutor().submit(runnable);
	            if(future.isDone()) 
	            {
	            	LOGGER.info("future.isDone() is true");
	            }
	        }
		});
		
		//查询服务提供商管理员按钮事件
		this.querySpManagerBtn.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
	        {
				showErrInfoWithColor("");
	            
	            boolean flag = validateParams(
	            		new JTextField[] {spIdField, accountField},
	            		new String[] {"spId", "account"});
	                
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
	            		querySpManager();
	            	}
	            };
	            Future<?> future = Executors.newSingleThreadExecutor().submit(runnable);
	            if(future.isDone()) 
	            {
	            	LOGGER.info("future.isDone() is true");
	            }
	        }
		});
	}
	
	/**
	 * 查询服务提供商管理员
	 */
	private void querySpManager() 
	{
		Token token = LoginUtils.getToken();
		
		RestRequest request = new RestRequest(HTTPConstant.HTTP_METHOD_GET);
		
		EcService.get("/sp/" + spIdField.getText() + "/spuser/" + accountField.getText(), request, errInfoLabel,token);
		
		EcService.finish();
	}
	
	/**
	 * 删除服务提供商管理员
	 */
	private void delSpManager() 
	{
		Token token = LoginUtils.getToken();
		
		RestRequest request = new RestRequest(HTTPConstant.HTTP_METHOD_DELETE);
		
		EcService.delete("/sp/" + spIdField.getText() + "/spuser/" + accountField.getText(), request, errInfoLabel,token);
		
		EcService.finish();
	}
	
	/**
	 * 增加服务提供商管理员
	 */
	private void addSpManager()
	{
		Token token = LoginUtils.getToken();
		
		RestRequest request = new RestRequest(HTTPConstant.HTTP_METHOD_POST);
		request.setPayload(addSPUser());
		
		EcService.post("/sp/" + spIdField.getText() + "/spuser", request, errInfoLabel,token);
		
		//储存密码的字符数组清空
		Arrays.fill(pwd, ' ');
		
		EcService.finish();
	}
	
	/**
	 * 修改服务提供商管理员
	 */
	private void modifySpManager()
	{
		Token token = LoginUtils.getToken();
		
		RestRequest request = new RestRequest(HTTPConstant.HTTP_METHOD_PUT);
		request.setPayload(addSPUser());
		
		EcService.put("/sp/" + spIdField.getText() + "/spuser", request, errInfoLabel,token);
		
		//储存密码的字符数组清空
		Arrays.fill(pwd, ' ');
		
		EcService.finish();
	}
	
	/**
	 * 分页查询服务提供商管理员
	 */
	private void pageQuerySpManager() 
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
		
		EcService.get("/sp/" + spIdField.getText() + "/spuser", request, errInfoLabel,token);
		
		EcService.finish();
	}
	
	/**
	 * 增加或者修改服务提供商管理员参数设置
	 * @return
	 */
	private SPUser addSPUser() 
	{
		SPUser spUser = new SPUser();
		spUser.setAccount(accountField.getText());
		if (StringUtils.isNotEmpty(nameField.getText()))
		{
			spUser.setName(nameField.getText());
		}
		pwd = pwdField.getPassword();
		if (StringUtils.isNotEmpty(new String(pwd))) 
		{
			spUser.setPwd(new String(pwd));
		}
		if (StringUtils.isNotEmpty(adminTypeField.getText()))
		{
			spUser.setAdminType(Integer.parseInt(adminTypeField.getText()));
		}
		if (StringUtils.isNotEmpty(rolenameField.getText())) 
		{
			spUser.setRolename(rolenameField.getText());
		}
		if (StringUtils.isNotEmpty(mobileField.getText()))
		{
			spUser.setMobile(mobileField.getText());
		}
		if (StringUtils.isNotEmpty(emailField.getText()))
		{
			spUser.setEmail(emailField.getText());
		}
		if (StringUtils.isNotEmpty(readOnlyAdminTypeField.getText()))
		{
			spUser.setReadOnlyAdminType(Integer.parseInt(readOnlyAdminTypeField.getText()));
		}
		
		return spUser;
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
  
	/**
	 * 判断参数非空性
	 * @param textFields
	 * @param errTexts
	 * @return
	 */
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
