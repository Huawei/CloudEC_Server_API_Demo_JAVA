package com.huawei.esdk.ec.view.enterprise;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
import com.huawei.esdk.ec.bean.enterprise.RightsRegRestBean;
import com.huawei.esdk.ec.constant.HTTPConstant;
import com.huawei.esdk.ec.service.EcService;
import com.huawei.esdk.ec.utils.LoginUtils;
import com.huawei.esdk.ec.utils.Properties_language_Utils;
import com.huawei.esdk.ec.utils.StringUtils;
import com.huawei.esdk.ec.view.common.LogPanel;
import com.huawei.esdk.ec.view.common.MyButton;
import com.huawei.esdk.ec.view.sp.AddCorpPanel;

public class ModifyCallRightsPanel extends JPanel
{
	private static final long serialVersionUID = 5262867673471402830L;
	
	private static final Logger LOGGER = LogManager.getLogger(AddCorpPanel.class);
	
	private JLabel reqParams = new JLabel(Properties_language_Utils.getValue("reqParams"));
	
	private JLabel errInfoLabel = new JLabel();
	private JLabel tipInfoLabel = new JLabel(Properties_language_Utils.getValue("enter.CorpNumPanel.tipInfoLabel2"));
	private JLabel tipInfo_rightsLabel = new JLabel(Properties_language_Utils.getValue("enter.CorpNumPanel.tipInfoLabel3"));
	
	private JLabel corpIdLabel = new JLabel("corpId:");
	private JTextField corpIdField = new JTextField(10);
	
	private JLabel impuListLabel = new JLabel("impuList:");
	private JTextField impuListField = new JTextField(10);
	
	private JLabel servPackNameLabel = new JLabel("servPackName:");
	private JTextField servPackNameField = new JTextField(10);
	
	private JLabel rightsEnumLabel = new JLabel("RightsEnum:");
	private JTextField rightsEnumField = new JTextField(10);
	
	private JLabel rightsStatusEnumLabel = new JLabel("RightsStatusEnum:");
	private JTextField rightsStatusEnumField = new JTextField(10);
	
	private JLabel isCustomizeRightsLabel = new JLabel("isCustomizeRights:");
	private JTextField isCustomizeRightsField = new JTextField(10);
	
	private JButton modifyCallRightBtn = new MyButton(Properties_language_Utils.
			getValue("enter.CorpNumPanel.modifyNumCallRightsBtn"),250,35);
	
	private JButton setRightsBtn = new MyButton(Properties_language_Utils.
			getValue("enter.CorpNumPanel.setRightsBtn"),250,35);
	
	private List<String> impuList =  new ArrayList<>();
	
	private Map<String,String> rights = new HashMap<>();
	
	public ModifyCallRightsPanel() 
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
	    
	    buildPanel(panel, gridbag, c, new JComponent[] {corpIdLabel}, 0, 1, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {corpIdField}, 1, 1, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {impuListLabel}, 2, 1, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {impuListField}, 3, 1, 6, 20, 2, 1);
	    
	    buildPanel(panel, gridbag, c, new JComponent[] {servPackNameLabel}, 0, 2, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {servPackNameField}, 1, 2, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {rightsEnumLabel}, 2, 2, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {rightsEnumField}, 3, 2, 6, 20, 1, 1);
	    
	    buildPanel(panel, gridbag, c, new JComponent[] {rightsStatusEnumLabel}, 0, 3, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {rightsStatusEnumField}, 1, 3, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {isCustomizeRightsLabel}, 2, 3, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {isCustomizeRightsField}, 3, 3, 6, 20, 1, 1);
	    
	    buildPanel(panel, gridbag, c, new JComponent[] {setRightsBtn}, 0, 4, 10, 20, 2, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {modifyCallRightBtn}, 2, 4, 10, 20, 2, 1);
	    
	    buildPanel(panel, gridbag, c, new JComponent[] {tipInfoLabel}, 0, 5, 100, 5, 4, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {tipInfo_rightsLabel}, 0, 6, 100, 5, 4, 1);
	    
	    buildPanel(panel, gridbag, c, new JComponent[] {errInfoLabel}, 0, 7, 100, 5, 4, 1);
	    
	    //报文位置
		//message location
		buildPanel(panel, gridbag, c, new JComponent[] {new LogPanel()}, 4, 0, 10, 20, 4, 10);
	    return panel;
	
	}
	
	private void init() 
	{
		tipInfoLabel.setForeground(Color.blue);
		tipInfo_rightsLabel.setForeground(Color.blue);
		
		//设置呼叫权限按钮事件
		this.setRightsBtn.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
	        {
				showErrInfoWithColor("");
	            
	            boolean flag = validateParams(
	            		new JTextField[] {rightsEnumField, rightsStatusEnumField},
	            		new String[] {"RightsEnum", "RightsStatusEnum"});
	                
	            if (!flag)
	            {
	            	return;
	            }
	            
	            Runnable runnable = new Runnable()
	            {
	            	@Override
	            	public void run()
	            	{
	            		rights.put(rightsEnumField.getText(), rightsStatusEnumField.getText());
	            		showErrInfoWithColor("set " + rightsEnumField.getText() + " success");
	            	}
	            };
	            Future<?> future = Executors.newSingleThreadExecutor().submit(runnable);
	            if(future.isDone()) 
	            {
	            	LOGGER.info("future.isDone() is true");
	            }
	        }
		});
		
		//点击修改业务权限按钮事件
		this.modifyCallRightBtn.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
	        {
				showErrInfoWithColor("");
	            
	            boolean flag = validateParams(
	            		new JTextField[] {corpIdField, impuListField},
	            		new String[] {"corpId", "impuList"});
	                
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
	            		modifyCallRight();
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
	 * 修改号码业务权限
	 */
	private void modifyCallRight() 
	{
		RestRequest request = new RestRequest(HTTPConstant.HTTP_METHOD_PUT);
		try 
		{
			Token token = LoginUtils.getToken();
			
			request.setPayload(addRightsRegRestBean());
			EcService.put("/corp/" + corpIdField.getText() + "/ucuser/rights", request, errInfoLabel,token);
			rights.clear();
			
			impuList.clear();
			
			EcService.finish();
		} 
		catch (Exception e) 
		{
			LOGGER.error("getToken error:" + e);
		}
		
	}
	
	/**
	 * 设置修改呼叫权限参数
	 * @return
	 */
	private RightsRegRestBean addRightsRegRestBean() 
	{
		RightsRegRestBean rightsRegRestBean = new RightsRegRestBean();
		String[] impuListArray = impuListField.getText().split(",|，");
		for (String impu : impuListArray) 
		{
			impuList.add(impu);
		}
		rightsRegRestBean.setImpuList(impuList);
		if(StringUtils.isNotEmpty(servPackNameField.getText())) 
		{
			rightsRegRestBean.setServPackName(servPackNameField.getText());
		}
		rightsRegRestBean.setRights(rights);
		if(StringUtils.isNotEmpty(isCustomizeRightsField.getText())) 
		{
			rightsRegRestBean.setIsCustomizeRights(Integer.parseInt(isCustomizeRightsField.getText()));
		}
		return rightsRegRestBean;
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

}
