package com.huawei.esdk.ec.view.common;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;
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

import com.huawei.esdk.ec.bean.common.Token;
import com.huawei.esdk.ec.utils.Properties_language_Utils;
import com.huawei.esdk.ec.utils.TokenUtils;

/**
 * 登录的视图
 * Login view
 * @author wwx534262
 *
 */
 @SuppressWarnings("all")
public class LoginPanel extends JPanel
{
	private static final long serialVersionUID = -2326564013550981677L;
	
	private static final Logger LOGGER = LogManager.getLogger(LoginPanel.class);
	
	private JLabel errInfoLabel = new JLabel();
	
	private JLabel ipLabel = new JLabel("ip:");
	private JTextField ipField = new JTextField(15);
	
	private JLabel userLabel = new JLabel(Properties_language_Utils.
			getValue("common.LoginPanel.userLabel"));
	private JTextField userField = new JTextField(10);
	
	private JLabel portLabel = new JLabel("port:");
	private JTextField portField = new JTextField(5);
	
	private JLabel pwdLabel = new JLabel(Properties_language_Utils.
			getValue("common.LoginPanel.pwdLabel"));
	private JPasswordField pwdField = new JPasswordField(10);
	
	private JLabel idLabel = new JLabel("spId/corpId:");
	private JTextField idField = new JTextField(10);
	
	private JLabel tokenLabel = new JLabel("token:");
	private JTextField tokenField = new JTextField(33);
	
	private JButton loginBtn = new MyButton(Properties_language_Utils.
			getValue("common.LoginPanel.loginBtn"));
	
	private int timerCount = 0;
	
	private String url = "";
	
	public LoginPanel() 
	{
		add(getPanels());
		init();
	}

	private JPanel getPanels() 
	{
		GridBagLayout gridbag = new GridBagLayout();
	    GridBagConstraints c = new GridBagConstraints();
	    JPanel panel = new JPanel(gridbag);
	    buildPanel(panel, gridbag, c, new JComponent[] {ipLabel}, 0, 0, 20, 5, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {ipField}, 1, 0, 10, 5, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {portLabel}, 2, 0, 10, 5, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {portField}, 3, 0, 10, 5, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {userLabel}, 4, 0, 10, 5, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {userField}, 5, 0, 10, 5, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {pwdLabel}, 6, 0, 10, 5, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {pwdField}, 7, 0, 10, 5, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {idLabel}, 0, 1, 10, 5, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {idField}, 1, 1, 10, 5, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {tokenLabel}, 2, 1, 10, 5, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {tokenField}, 3, 1, 10, 5, 5, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {loginBtn}, 8, 0, 10, 5, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {errInfoLabel}, 7, 1, 10, 5, 2, 1);
	    return panel;
	}
	
	private void init() 
	{
		ipField.setText(Properties_language_Utils.getValue("ip"));
		portField.setText(Properties_language_Utils.getValue("port"));
		userField.setText(Properties_language_Utils.getValue("user"));
		//点击登录事件
		//Click on the login event
		this.loginBtn.addMouseListener(new MouseAdapter()
        {
            
			public void mouseClicked(MouseEvent e)
            {
                showErrInfoWithColor("");
                idField.setText("");
                tokenField.setText("");
                
                boolean flag = validateParams(
                    new JTextField[] {ipField, portField, userField, pwdField},
                    new String[] {"ip", "port", Properties_language_Utils.getValue("common.LoginPanel.user"), 
                    		Properties_language_Utils.getValue("common.LoginPanel.pwd")});
                    
                if (!flag)
                {
                    return;
                }
                begin();
                loading();
               
                Runnable runnable = new Runnable()
	            {
	            	@Override
	            	public void run()
	            	{
	            		 getToken();
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

	//获取token
	//get token
	private void getToken()
	{
		try 
		{
			//只支持https
			url = "https://" + ipField.getText().trim() + ":" + portField.getText().trim();
			char[] pwd = pwdField.getPassword();
			Token token = TokenUtils.update(url, userField.getText().trim(), new String(pwd));
			Arrays.fill(pwd, ' ');
			if(null != token)
			{
				showErrInfoWithColor(Properties_language_Utils.getValue("common.LoginPanel.loginSuccessTip"));
				idField.setText(token.getId());
				tokenField.setText(token.getToken());
			}
			else
			{
				showErrInfoWithColor(Properties_language_Utils.getValue("common.LoginPanel.loginFailTip"));
				idField.setText("");
				tokenField.setText("");
			}
				
		} 
		catch (Exception e)
		{
			// TODO: handle exception
			showErrInfoWithColor(Properties_language_Utils.getValue("common.LoginPanel.loginFailTip"));
		}
		finally 
		{
			// TODO: handle finally clause
			finish();
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

    private void begin()
    {
        timerCount = 0;
    }
    
    private void finish()
    {
        timerCount = -1;
    }
    
    private void loading()
    {
        new Timer().schedule(new TimerTask()
        {
            
            @Override
            public void run()
            {
                if (timerCount < 0)
                {
                    return;
                }
                
                if (timerCount < 4)
                {
                    timerCount++;
                }
                else
                {
                    timerCount = 0;
                }
                
                StringBuffer tempBuffer = new StringBuffer();
                for (int i = 0; i < timerCount; i++)
                {
                	tempBuffer.append(". ");
                }
                String temp = tempBuffer.toString();
                temp = Properties_language_Utils.getValue("common.LoginPanel.loginTip") + temp;
                showErrInfoWithColor(temp);
                
                loading();
            }
        }, 250);
    }
}
