package com.huawei.esdk.ec.view.admin;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
 * 服务提供商号段管理视图
 * Service Provider Segment Management View
 * @author wWX534262
 *
 */
@SuppressWarnings("all")
public class SpNumPanel extends JPanel 
{
	private static final long serialVersionUID = 5080862099918186261L;
	
	private static final Logger LOGGER = LogManager.getLogger(SpNumPanel.class);
	
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
	
	private JLabel assignStatusLabel = new JLabel("assignStatus:");
	private JTextField assignStatusField = new JTextField(10);
	
	private JLabel numberTypeLabel = new JLabel("numberType:");
	private JTextField numberTypeField = new JTextField(10);
	
	private JLabel startNumLabel = new JLabel("startNum:");
	private JTextField startNumField = new JTextField(10);
	
	private JLabel endNumLabel = new JLabel("endNum:");
	private JTextField endNumField = new JTextField(10);
	
	private JButton queryPageBtn = new MyButton(Properties_language_Utils.getValue("admin.SpNumPanel.queryPageBtn"),200,35);
	
	private JButton queryBtn = new MyButton(Properties_language_Utils.getValue("admin.SpNumPanel.queryBtn"),200,35);
	
	public SpNumPanel() 
	{
		add(getPanels());
	    init();
	}
	
	//设置组件位置
	//Set component location
	private JPanel getPanels() 
	{
		GridBagLayout gridbag = new GridBagLayout();
	    GridBagConstraints c = new GridBagConstraints();
	    JPanel panel = new JPanel(gridbag);
	    
	    buildPanel(panel, gridbag, c, new JComponent[] {reqParams}, 0, 0, 10, 40, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {spIdLabel}, 0, 1, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {spIdField}, 1, 1, 10, 20, 2, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {searchKeyLabel}, 2, 1, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {searchKeyField}, 3, 1, 6, 20, 2, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {pageIndexLabel}, 0, 3, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {pageIndexField}, 1, 3, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {pageSizeLabel}, 2, 3, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {pageSizeField}, 3, 3, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {assignStatusLabel}, 0, 4, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {assignStatusField}, 1, 4, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {numberTypeLabel}, 2, 4, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {numberTypeField}, 3, 4, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {startNumLabel}, 0, 5, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {startNumField}, 1, 5, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {endNumLabel}, 2, 5, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {endNumField}, 3, 5, 10, 20, 1, 1);
	    
	    buildPanel(panel, gridbag, c, new JComponent[] {queryPageBtn}, 0, 6, 10, 20, 2, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {queryBtn}, 2, 6, 10, 20, 2, 1);
	    
	    buildPanel(panel, gridbag, c, new JComponent[] {errInfoLabel}, 0, 7, 100, 5, 4, 1);
	   
	    //报文位置
	    //message location
	    buildPanel(panel, gridbag, c, new JComponent[] {new LogPanel()}, 4, 0, 10, 20, 4, 10);
	    
	    return panel;
	}
	
	private void init() 
	{
		//查询服务提供商号段按钮事件
		//Query service provider segment button event
		this.queryBtn.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
	        {
				showErrInfoWithColor("");
	            
	            boolean flag = validateParams(
	            		new JTextField[] {spIdField, startNumField, endNumField},
	            		new String[] {"spId", "startNum", "endNum"});
	                
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
	            		query();
	            	}
	            };
				Future future = Executors.newSingleThreadExecutor().submit(runnable);
	            if(!future.isDone()) {
	            	LOGGER.error("addMouseListener fail");
	            }
	        }
		});
		        
		//分页查询服务提供商号段按钮事件
		//Paging Query Service Provider Number Segment Button Events
		this.queryPageBtn.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
	        {
				showErrInfoWithColor("");
	            
	            boolean flag = validateParams(
	            		new JTextField[] {spIdField, pageIndexField, pageSizeField},
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
	            		String spId = spIdField.getText();
	                	String pageIndex = pageIndexField.getText();
	                	String pageSize = pageSizeField.getText();
	                	String searchKey = searchKeyField.getText();
	                	String assignStatus = assignStatusField.getText();
	                	String numberType = numberTypeField.getText();
	                	
	                	queryPage(spId, pageIndex, pageSize, searchKey, assignStatus, numberType);
	            	}
	            };
	            Future future = Executors.newSingleThreadExecutor().submit(runnable);
	            if(!future.isDone()) {
	            	LOGGER.error("addMouseListener fail");
	            }
	        }
		});
	}
	
	//查询服务提供商号码段
	//Query service provider number segment
	private void query() 
	{
		RestRequest request = new RestRequest(HTTPConstant.HTTP_METHOD_GET);
		Map<String, String> parameters = request.getParameters();
		parameters.put("startNum", startNumField.getText());
		parameters.put("endNum", endNumField.getText());
		
		request.setParameters(parameters);
		try 
		{
			Token token = LoginUtils.getToken();
			EcService.get("/sp/" + spIdField.getText(), request, errInfoLabel, token);
			EcService.finish();
		} catch (Exception e) 
		{
			LOGGER.error("getToken error:" + e);
		}
	}

	//分页查询服务提供商号段
	//Paging query service provider number segment
	private void queryPage(String spId, String pageIndex, String pageSize, String searchKey,
			String assignStatus, String numberType) 
	{
		RestRequest request = new RestRequest(HTTPConstant.HTTP_METHOD_GET);
		Map<String, String> parameters = request.getParameters();
		parameters.put("spId", spId);
		parameters.put("pageIndex", pageIndex);
		parameters.put("pageSize", pageSize);
		if (StringUtils.isNotEmpty(searchKey)) 
		{
			parameters.put("searchKey", searchKey);
		}
		if (StringUtils.isNotEmpty(assignStatus)) 
		{
			parameters.put("assignStatus", assignStatus);
		}
		if (StringUtils.isNotEmpty(numberType)) 
		{
			parameters.put("numberType", numberType);
		}
		
		request.setParameters(parameters);
		try 
		{
			Token token = LoginUtils.getToken();
			EcService.get("/spnum", request, errInfoLabel,token);
			
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
		// 加高组件直到他足以在垂直方向上填满其显示区域，但不更改其高度
		// Raise the component until he is large enough to fill its display area vertically but do not change its height
		c.fill = GridBagConstraints.VERTICAL;
		
		// 当组件小于其显示区域时，用于确定将组件至于西面
		// When the component is smaller than its display area, used to determine the component to the west
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
  
    //判断参数是否为空
    //Determine the parameter is or not empty
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
