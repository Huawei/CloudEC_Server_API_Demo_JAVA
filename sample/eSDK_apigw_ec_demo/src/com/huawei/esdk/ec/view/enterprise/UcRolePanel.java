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
 * uc功能角色管理界面
 * Uc function role management view
 * @author wWX534262
 *
 */
@SuppressWarnings("all")
public class UcRolePanel extends JPanel
{

	private static final long serialVersionUID = 1829606051761504423L;
	
	private static final Logger LOGGER = LogManager.getLogger(UcRolePanel.class);
	
	private JLabel errInfoLabel = new JLabel();
	
	private JLabel reqParams = new JLabel(Properties_language_Utils.getValue("reqParams"));
	
	private JLabel corpIdLabel = new JLabel("corpId:");
	private JTextField corpIdField = new JTextField(10);
	
	private JLabel pageIndexLabel = new JLabel("pageIndex:");
	private JTextField pageIndexField = new JTextField(10);
	
	private JLabel pageSizeLabel = new JLabel("pageSize:");
	private JTextField pageSizeField = new JTextField(10);
	
	private JLabel searchKeyLabel = new JLabel("searchKey:");
	private JTextField searchKeyField = new JTextField(10);
	
	private JButton pageQueryBtn = new MyButton(Properties_language_Utils.
			getValue("enter.UcRolePanel.pageQueryBtn"));
	
	public UcRolePanel() 
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
		buildPanel(panel, gridbag, c, new JComponent[] {pageIndexLabel}, 0, 4, 20, 10, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {pageIndexField}, 1, 4, 20, 10, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {pageSizeLabel}, 0, 5, 20, 10, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {pageSizeField}, 1, 5, 20, 10, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {searchKeyLabel}, 0, 6, 20, 10, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {searchKeyField}, 1, 6, 20, 10, 1, 1);
		//按钮
		buildPanel(panel, gridbag, c, new JComponent[] {pageQueryBtn}, 0, 7, 5, 10, 1, 1);
		
		buildPanel(panel, gridbag, c, new JComponent[] {errInfoLabel}, 0, 8, 30, 5, 4, 1);
		
	    //报文位置
		//message location
		buildPanel(panel, gridbag, c, new JComponent[] {new LogPanel()}, 4, 0, 10, 20, 4, 10);
		
		
		return panel;
	}
	
	private void init() 
	{
		//分页查询Uc功能角色按钮事件
		//Paging Query Uc Function Role Button Events
		this.pageQueryBtn.addMouseListener(new MouseAdapter() 
		{
			public void mouseClicked(MouseEvent e) 
			{
				showErrInfoWithColor("");
				
				boolean flag = validateParams(
						new JTextField[] {corpIdField, pageIndexField, pageSizeField},
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
						pageQueryUcRoles();
					}
			    };
			    Future future = Executors.newSingleThreadExecutor().submit(runnable);
		        if(!future.isDone()) {
		            LOGGER.error("addMouseListener fail");
		        }
			}
		});
	}

	
	//分页查询Uc功能角色
	//Paging Query Uc Function Role
	private void pageQueryUcRoles() 
	{
		Map<String, String> param = new HashMap<String, String>();
		param.put("pageIndex", pageIndexField.getText());
		param.put("pageSize", pageSizeField.getText());
		if (StringUtils.isNotEmpty(searchKeyField.getText())) 
		{
			param.put("searchKey", searchKeyField.getText());
		}
		
		try 
		{
			Token token = LoginUtils.getToken();
			 
			// 利用token发送restful请求
			// Send a restful request using token
			RestRequest request = new RestRequest(HTTPConstant.HTTP_METHOD_GET);
			request.setParameters(param); 
			EcService.get("/corp/" + corpIdField.getText() + "/roles", request, errInfoLabel, token);
			 
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
		if(null == errInfo)
		{
			return;
		}
		
		errInfoLabel.setForeground(Color.red);
		errInfoLabel.setText(errInfo);
	}

}
