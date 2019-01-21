package com.huawei.esdk.ec.view.enterprise;

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
import javax.swing.JTextField;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.huawei.esdk.ec.bean.common.RestRequest;
import com.huawei.esdk.ec.bean.common.Token;
import com.huawei.esdk.ec.bean.enterprise.ConfCodeBindShortNumBean;
import com.huawei.esdk.ec.constant.HTTPConstant;
import com.huawei.esdk.ec.service.EcService;
import com.huawei.esdk.ec.utils.LoginUtils;
import com.huawei.esdk.ec.utils.Properties_language_Utils;
import com.huawei.esdk.ec.utils.StringUtils;
import com.huawei.esdk.ec.view.common.LogPanel;
import com.huawei.esdk.ec.view.common.MyButton;

/**
 * 会议接入号绑定短号视图
 * @author wWX534262
 *
 */
public class AccesscodeBindShortNumPanel extends JPanel
{
	private static final long serialVersionUID = 268601475521558747L;
	
	private static final Logger LOGGER = LogManager.getLogger(AccesscodeBindShortNumPanel.class);
	
	private JLabel errInfoLabel = new JLabel();
	
	private JLabel reqParams = new JLabel(Properties_language_Utils.getValue("reqParams"));
	
	private JLabel confCodeIDLabel = new JLabel("confCodeID:");
	private JTextField confCodeIDField = new JTextField(10);
	
	private JLabel shortNumLabel = new JLabel("shortNum:");
	private JTextField shortNumField = new JTextField(10);
	
	private JButton bindShortNumBtn = new MyButton("会议号绑定短号");
	
	public AccesscodeBindShortNumPanel() 
	{
		add(getPanels());
	    init();
	}

	/**
	 * 设置JPanel中各组件位置
	 * @return
	 */
	private JPanel getPanels()
	{
		GridBagLayout gridbag = new GridBagLayout();
	    GridBagConstraints c = new GridBagConstraints();
	    JPanel panel = new JPanel(gridbag);
	    
	    buildPanel(panel, gridbag, c, new JComponent[] {reqParams}, 0, 0, 10, 40, 1, 1); 
	    
	    buildPanel(panel, gridbag, c, new JComponent[] {confCodeIDLabel}, 0, 1, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {confCodeIDField}, 1, 1, 10, 20, 2, 1);
	    
	    buildPanel(panel, gridbag, c, new JComponent[] {shortNumLabel}, 0, 2, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {shortNumField}, 1, 2, 6, 20, 2, 1);
	    
	    buildPanel(panel, gridbag, c, new JComponent[] {bindShortNumBtn}, 0, 3, 10, 20, 2, 1);
	    
	    buildPanel(panel, gridbag, c, new JComponent[] {errInfoLabel}, 0, 4, 100, 5, 4, 1);
	    
	    //报文位置
	    //message location
		buildPanel(panel, gridbag, c, new JComponent[] {new LogPanel()}, 4, 0, 10, 20, 4, 10);
	    
	    return panel;
	}
	
	private void init()
	{
		//绑定短号按钮事件
		this.bindShortNumBtn.addMouseListener(new MouseAdapter() 
		{
			public void mouseClicked(MouseEvent e) 
			{
				showErrInfoWithColor("");
			             
	            EcService.begin();
	            EcService.loading(errInfoLabel);
	             
	            Runnable runnable = new Runnable()
	            {
	            	@Override
	            	public void run()
	            	{
	            		bindShortNum();
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
	 * 接入号绑定短号
	 */
	private void bindShortNum() 
	{
		Token token = LoginUtils.getToken();
		
		RestRequest request = new RestRequest(HTTPConstant.HTTP_METHOD_POST);
		request.setPayload(addConfCodeBindShortNumBean());
		
		EcService.post("/conference/accesscode/shortnum", request, errInfoLabel, token);
		
		EcService.finish();
	}
	
	/**
	 * 会议号绑定短号参数接收并封装
	 * @return
	 */
	private ConfCodeBindShortNumBean addConfCodeBindShortNumBean() 
	{
		ConfCodeBindShortNumBean confCodeBindShortNumBean = new ConfCodeBindShortNumBean();
		if (StringUtils.isNotEmpty(confCodeIDField.getText()))
		{
			confCodeBindShortNumBean.setConfCodeID(confCodeIDField.getText());
		}
		if (StringUtils.isNotEmpty(shortNumField.getText())) 
		{
			confCodeBindShortNumBean.setShortNum(shortNumField.getText());
		}
		
		return confCodeBindShortNumBean;
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
}
