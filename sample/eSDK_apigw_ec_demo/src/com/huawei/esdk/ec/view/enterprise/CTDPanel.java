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
import com.huawei.esdk.ec.bean.enterprise.CallBean;
import com.huawei.esdk.ec.constant.HTTPConstant;
import com.huawei.esdk.ec.service.EcService;
import com.huawei.esdk.ec.utils.LoginUtils;
import com.huawei.esdk.ec.utils.Properties_language_Utils;
import com.huawei.esdk.ec.utils.StringUtils;
import com.huawei.esdk.ec.view.common.LogPanel;
import com.huawei.esdk.ec.view.common.MyButton;

/**
 * 点击呼叫视图
 * Click on the call view
 * @author wwx534262
 *
 */
@SuppressWarnings("all")
public class CTDPanel extends JPanel
{

	private static final long serialVersionUID = -3430551772782982749L;
	
	private static final Logger LOGGER = LogManager.getLogger(CTDPanel.class);
	
	private JLabel requestLabel = new JLabel(Properties_language_Utils.getValue("reqParams"));
	 
	private JLabel errInfoLabel = new JLabel();

	private JLabel numberLabel = new JLabel(Properties_language_Utils.
			getValue("enter.CTDPanel.numberLabel"));
	private JTextField numberField = new JTextField(20);
	 
	private JLabel callerLabel = new JLabel(Properties_language_Utils.
			getValue("enter.CTDPanel.callerLabel"));
	private JTextField callerField = new JTextField(20);
	 
	private JLabel calleeLabel = new JLabel(Properties_language_Utils.
			getValue("enter.CTDPanel.calleeLabel"));
	private JTextField calleeField = new JTextField(20);
	    
	private JButton ctdBtn = new MyButton(Properties_language_Utils.
			getValue("enter.CTDPanel.ctdBtn"));
	 
	
	 
	public CTDPanel()
	{
		add(getPanels());
		init();
	}
	 
	private JPanel getPanels()
	{
		// 主面板
		// Main panel
		GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		JPanel panel = new JPanel(gridbag);
        
		buildPanel(panel, gridbag, c, new JComponent[] {requestLabel}, 0, 0, 20, 40, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {numberLabel}, 0, 2, 20, 10, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {numberField}, 1, 2, 20, 10, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {callerLabel}, 0, 3, 20, 10, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {callerField}, 1, 3, 20, 10, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {calleeLabel}, 0, 4, 20, 10, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {calleeField}, 1, 4, 20, 10, 1, 1);
		// 按钮
		// button
		buildPanel(panel, gridbag, c, new JComponent[] {ctdBtn}, 1, 5, 5, 10, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {errInfoLabel}, 0, 6, 100, 5, 4, 1);
        
	    //报文位置
		//message location
		buildPanel(panel, gridbag, c, new JComponent[] {new LogPanel()}, 4, 0, 10, 20, 4, 10);
		return panel;
	}
    
	private void init()
	{
	    //点击呼叫按钮事件   
		//Click the call button event
		this.ctdBtn.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
			{
				showErrInfoWithColor("");
	                
				boolean flag = validateParams(
						new JTextField[] {numberField, calleeField},
						new String[] {"number", "callee"});
	                    
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
	            		call();
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
	
	// 点击呼叫
	// Click to call
    private void call()
    {
    	RestRequest request = new RestRequest(HTTPConstant.HTTP_METHOD_POST);
    	CallBean payload = new CallBean();
    	payload.setCallee(calleeField.getText());
    	if (StringUtils.isNotEmpty(callerField.getText())) 
    	{
    		payload.setCaller(callerField.getText());
		}
    	request.setPayload(payload);
    	try 
		{
			Token token = LoginUtils.getToken();
			EcService.post("/number/" + numberField.getText().replace("+", "%2b") + "/ctd", request, errInfoLabel, token);
		} catch (Exception e) 
		{
			LOGGER.error("getToken error:" + e);
			showErrInfoWithColor(Properties_language_Utils.getValue("enter.CTDPanel.resultTip"));
		}
    	finally
    	{
    		EcService.finish();
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
			if (null == textFields[i] || null == textFields[i].getText() || "".equals(textFields[i].getText()))
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
