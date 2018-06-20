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
import java.util.ArrayList;
import java.util.List;
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
import com.huawei.esdk.ec.bean.sp.RecoverNumBean;
import com.huawei.esdk.ec.constant.HTTPConstant;
import com.huawei.esdk.ec.service.EcService;
import com.huawei.esdk.ec.utils.LoginUtils;
import com.huawei.esdk.ec.utils.Properties_language_Utils;
import com.huawei.esdk.ec.view.common.LogPanel;
import com.huawei.esdk.ec.view.common.MyButton;
/**
 * 服务提供商号段管理视图
 * Service Provider Section Management View
 * @author wWX534262
 *
 */
@SuppressWarnings("all")
public class SpNumPanel extends JPanel implements ActionListener
{
	private static final long serialVersionUID = 5080862099918186261L;
	
	private static final Logger LOGGER = LogManager.getLogger(SpNumPanel.class);
	
	private JLabel errInfoLabel = new JLabel();
	private JLabel tipInfoLabel = new JLabel(Properties_language_Utils.getValue("sp.SpNumPanel.tipInfoLabel"));
	
	private JLabel reqParams = new JLabel(Properties_language_Utils.getValue("reqParams"));
	
	private JLabel srcCorpIdLabel = new JLabel("srcCorpId:");
	private JTextField srcCorpIdField = new JTextField(15);
	
	private JLabel numberListLabel = new JLabel("numberList:");
	private JTextField numberListField = new JTextField(15);
	
	private JButton assignBtn = new MyButton(Properties_language_Utils.getValue("sp.SpNumPanel.assignBtn"),320,35);
	
	private JButton recoveryBtn = new MyButton(Properties_language_Utils.getValue("sp.SpNumPanel.recoveryBtn"),320,35);
	
	private JButton conditionRecoveryBtn = new MyButton(Properties_language_Utils.
			getValue("sp.SpNumPanel.conditionRecoveryBtn"),320,35);
	
	public SpNumPanel() 
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
	    buildPanel(panel, gridbag, c, new JComponent[] {srcCorpIdLabel}, 0, 2, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {srcCorpIdField}, 1, 2, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {numberListLabel}, 0, 3, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {numberListField}, 1, 3, 10, 20, 1, 1);
	    
	    buildPanel(panel, gridbag, c, new JComponent[] {assignBtn}, 0, 4, 10, 20, 2, 1);
	    assignBtn.addActionListener(new ActionListener() 
	    {
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				// 构造一个新的JFrame，作为新窗口。
				// Construct a new JFrame as a new window.
				JFrame frame = new JFrame(Properties_language_Utils.getValue("sp.SpNumPanel.frameTitle"));
				
				setSize(1050, 680);
			    frame.setSize(1050, 680);
			    
			    JPanel center = new AssignNumber();
			    JScrollPane centerJPane = new JScrollPane(center);
			    
			    frame.getContentPane().add(centerJPane, BorderLayout.CENTER);
			     
				frame.setResizable(true);
				frame.setVisible(true);
			}
		});
	    buildPanel(panel, gridbag, c, new JComponent[] {conditionRecoveryBtn}, 0, 5, 10, 20, 2, 1);
	    conditionRecoveryBtn.addActionListener(this);
	    buildPanel(panel, gridbag, c, new JComponent[] {recoveryBtn}, 0, 6, 10, 20, 2, 1);
	    
	    buildPanel(panel, gridbag, c, new JComponent[] {errInfoLabel}, 0, 7, 100, 5, 4, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {tipInfoLabel}, 0, 8, 10, 20, 4, 1);
	    
	    //报文位置
		//message location
		buildPanel(panel, gridbag, c, new JComponent[] {new LogPanel()}, 4, 0, 10, 20, 4, 10);
	    
	    return panel;
	}
	
	private void init() 
	{
		tipInfoLabel.setForeground(Color.blue);
		//回收企业号码到提供商按钮事件
		//Recover company number to provider button event
		this.recoveryBtn.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
	        {
				showErrInfoWithColor("");
	            
	            boolean flag = validateParams(
	            		new JTextField[] {srcCorpIdField, numberListField},
	            		new String[] {"srcCorpId", "numberList"});
	                
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
            			recover();
            		}
				};
				Future future = Executors.newSingleThreadExecutor().submit(runnable);
			    if(!future.isDone()) {
			        LOGGER.error("addMouseListener fail");
			    }
	        }
		});
		        
	}
	
	//回收企业号码到提供商
	//Recover company number to provider
	private void recover()
	{
		try 
		{
			Token token = LoginUtils.getToken();
			
			// 利用token发送restful请求
			// Send a restful request using token
			RestRequest request = new RestRequest(HTTPConstant.HTTP_METHOD_PUT);
			
			//封装参数
			//Package parameters
			RecoverNumBean recoverBean = new RecoverNumBean();
			recoverBean.setSrcCorpId(srcCorpIdField.getText());
			List<String> numberList = new ArrayList<String>();
			String[] split = numberListField.getText().split(",");
			for (String number : split)
			{
				numberList.add(number);
			}
			recoverBean.setNumberList(numberList);
			
			request.setPayload(recoverBean);
			EcService.put("/spnum/recovery", request, errInfoLabel, token);
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

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		// 构造一个新的JFrame，作为新窗口。
		// Construct a new JFrame as a new window.
		JFrame frame = new JFrame(Properties_language_Utils.getValue("sp.SpNumPanel.frameTitle2"));
		setSize(1050, 680);
	    frame.setSize(1050, 680);
	    
	    JPanel center = new ConditionRecoveryPanel();
	    JScrollPane centerJPane = new JScrollPane(center);
	    
	    frame.getContentPane().add(centerJPane, BorderLayout.CENTER);
	     
		frame.setResizable(true);
		frame.setVisible(true);
	}

}
