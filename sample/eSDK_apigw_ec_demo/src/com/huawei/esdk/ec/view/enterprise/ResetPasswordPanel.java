package com.huawei.esdk.ec.view.enterprise;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
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

import com.huawei.esdk.ec.bean.common.RestRequest;
import com.huawei.esdk.ec.bean.common.Token;
import com.huawei.esdk.ec.bean.enterprise.ResetPassword;
import com.huawei.esdk.ec.constant.HTTPConstant;
import com.huawei.esdk.ec.service.EcService;
import com.huawei.esdk.ec.utils.LoginUtils;
import com.huawei.esdk.ec.utils.Properties_language_Utils;
import com.huawei.esdk.ec.view.common.LogPanel;
import com.huawei.esdk.ec.view.common.MyButton;
/**
 * 重置密码界面视图
 * Reset password interface view
 * @author wWX534262
 *
 */
@SuppressWarnings("all")
public class ResetPasswordPanel extends JPanel
{
	private static final long serialVersionUID = 8860734076788443701L;
	
	private static final Logger LOGGER = LogManager.getLogger(ResetPasswordPanel.class);
	
	private JLabel errInfoLabel = new JLabel();
	
	private JLabel reqParams = new JLabel(Properties_language_Utils.getValue("reqParams")); 
	
	private JLabel userAccountLabel = new JLabel(Properties_language_Utils.
			getValue("enter.ResetPasswordPanel.userAccountLabel"));
	private JTextField userAccountField = new JTextField(15);
	
	private JLabel newPwdLabel = new JLabel(Properties_language_Utils.
			getValue("enter.ResetPasswordPanel.newPwdLabel"));
	private JPasswordField newPwdField = new JPasswordField(15);
	
	private JButton resertBtn = new MyButton(Properties_language_Utils.
			getValue("enter.ResetPasswordPanel.resertBtn"));
	
	
	
	public ResetPasswordPanel()
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
	    buildPanel(panel, gridbag, c, new JComponent[] {userAccountLabel}, 0, 2, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {userAccountField}, 1, 2, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {newPwdLabel}, 0, 3, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {newPwdField}, 1, 3, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {resertBtn}, 0, 4, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {errInfoLabel}, 0, 5, 10, 20, 2, 1);
	    
	    //报文位置
		//message location
		buildPanel(panel, gridbag, c, new JComponent[] {new LogPanel()}, 4, 0, 10, 20, 4, 10);
	    
	    return panel;
	}


	private void init()
	{
		//重置密码按钮事件
		//Reset password button event
		this.resertBtn.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
	        {
				showErrInfoWithColor("");
	                
				boolean flag = validateParams(
	                    new JTextField[] {userAccountField, newPwdField},
	                    new String[] {"userAccount", "newPwd"});
	                    
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
						resetPassword();
					}
		        };
		        Future future = Executors.newSingleThreadExecutor().submit(runnable);
		        if(!future.isDone()) {
		            LOGGER.error("addMouseListener fail");
		        }
	        }
		});
	}
	//重置密码
	//reset password
	private void resetPassword() 
    {
		try 
		{
			Token token = LoginUtils.getToken();
			// 利用token发送restful请求
			// Send a restful request using token
			ResetPassword payload = new ResetPassword();
			payload.setUserAccount(userAccountField.getText());
			char[] newPwd = newPwdField.getPassword();
			payload.setNewPwd(new String(newPwd));
			
			RestRequest request = new RestRequest(HTTPConstant.HTTP_METHOD_PUT);
			request.setPayload(payload);
			EcService.post("/password", request, errInfoLabel, token);
			
			Arrays.fill(newPwd, ' ');
			EcService.finish();
			
		} 
		catch (Exception e) 
		{
			showErrInfoWithColor(Properties_language_Utils.getValue("enter.ResetPasswordPanel.resultTip"));
			LOGGER.error("get Token error:" + e);
		}
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
}
