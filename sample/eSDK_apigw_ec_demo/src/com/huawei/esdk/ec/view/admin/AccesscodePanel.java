package com.huawei.esdk.ec.view.admin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
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

import com.huawei.esdk.ec.bean.admin.AssignAccesscodeToSpBean;
import com.huawei.esdk.ec.bean.admin.DeleteAccesscodeBean;
import com.huawei.esdk.ec.bean.common.RestRequest;
import com.huawei.esdk.ec.bean.common.Token;
import com.huawei.esdk.ec.constant.HTTPConstant;
import com.huawei.esdk.ec.service.EcService;
import com.huawei.esdk.ec.utils.LoginUtils;
import com.huawei.esdk.ec.utils.Properties_language_Utils;
import com.huawei.esdk.ec.view.common.LogPanel;
import com.huawei.esdk.ec.view.common.MyButton;
/**
 * 接入码视图
 * @author wWX534262
 *
 */
public class AccesscodePanel extends JPanel implements ActionListener
{
	private static final long serialVersionUID = 5608323692183317041L;

	private static final Logger LOGGER = LogManager.getLogger(AccesscodePanel.class);
	
	private JLabel errInfoLabel = new JLabel();
	
	private JLabel tipInfoLabel = new JLabel(Properties_language_Utils.
			getValue("admin.AccesscodePanel.tipInfoLabel"));
	
	private JLabel reqParams = new JLabel(Properties_language_Utils.getValue("reqParams"));
	
	private JLabel orgIDLabel = new JLabel("orgID:");
	private JTextField orgIDField = new JTextField(10);
	
	private JLabel isAssignReqQryLabel = new JLabel("isAssignReqQry:");
	private JTextField isAssignReqQryField = new JTextField(10);
	
	private JLabel confcodeListLabel = new JLabel("confcodeList:");
	private JTextField confcodeListField = new JTextField(10);
	
	private JLabel orgIdLabel = new JLabel("orgId:");
	private JTextField orgIdField = new JTextField(10);
	
	private JButton addAccesscodeBtn = new MyButton(Properties_language_Utils.
			getValue("admin.AccesscodePanel.addAccesscodeBtn"), 240, 35);
	
	private JButton modifyAccesscodeBtn = new MyButton(Properties_language_Utils.
			getValue("admin.AccesscodePanel.modifyAccesscodeBtn"), 240, 35);
	
	private JButton delAccesscodeBtn = new MyButton(Properties_language_Utils.
			getValue("admin.AccesscodePanel.delAccesscodeBtn"), 240, 35);
	
	private JButton assignAccesscodeBtn = new MyButton(Properties_language_Utils.
			getValue("admin.AccesscodePanel.assignAccesscodeBtn"), 240, 35);
	
	private JButton recoverAccesscodeBtn = new MyButton(Properties_language_Utils.
			getValue("admin.AccesscodePanel.recoverAccesscodeBtn"), 240, 35);
	
	public AccesscodePanel() 
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
	    
	    buildPanel(panel, gridbag, c, new JComponent[] {orgIDLabel}, 0, 1, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {orgIDField}, 1, 1, 10, 20, 2, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {isAssignReqQryLabel}, 2, 1, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {isAssignReqQryField}, 3, 1, 10, 20, 1, 1);
	    
	    buildPanel(panel, gridbag, c, new JComponent[] {confcodeListLabel}, 0, 2, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {confcodeListField}, 1, 2, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {orgIdLabel}, 2, 2, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {orgIdField}, 3, 2, 10, 20, 1, 1);
	    
	    buildPanel(panel, gridbag, c, new JComponent[] {addAccesscodeBtn}, 0, 3, 10, 20, 2, 1);
	    addAccesscodeBtn.addActionListener(this);
	    buildPanel(panel, gridbag, c, new JComponent[] {modifyAccesscodeBtn}, 2, 3, 10, 20, 2, 1);
	    modifyAccesscodeBtn.addActionListener(this);
	    buildPanel(panel, gridbag, c, new JComponent[] {delAccesscodeBtn}, 0, 4, 10, 20, 2, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {assignAccesscodeBtn}, 2, 4, 10, 20, 2, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {recoverAccesscodeBtn}, 0, 5, 10, 20, 2, 1);
	    
	    buildPanel(panel, gridbag, c, new JComponent[] {errInfoLabel}, 0, 6, 100, 5, 4, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {tipInfoLabel}, 0, 7, 100, 5, 4, 1);
	    
	    //报文位置
	    //message location
		buildPanel(panel, gridbag, c, new JComponent[] {new LogPanel()}, 4, 0, 10, 20, 4, 10);
		return panel;
	}
	
	private void init()
	{
		tipInfoLabel.setForeground(Color.BLUE);
		//点击删除接入号按钮事件
		this.delAccesscodeBtn.addMouseListener(new MouseAdapter() 
		{
			public void mouseClicked(MouseEvent e) 
			{
				showErrInfoWithColor("");
			             
				boolean flag = validateParams(
			            new JTextField[] {confcodeListField},
			            new String[] {"confcodeList"});
			             
				if(!flag) 
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
	            		delAccesscode();
	            	}
	            };
	            Future<?> future = Executors.newSingleThreadExecutor().submit(runnable);
	            if(future.isDone()) 
	            {
	            	LOGGER.info("future.isDone() is true");
	            }
			}
		});
		
		
		//点击给SP分配接入号按钮事件
		this.assignAccesscodeBtn.addMouseListener(new MouseAdapter() 
		{
			public void mouseClicked(MouseEvent e) 
			{
				showErrInfoWithColor("");
			             
				boolean flag = validateParams(
			            new JTextField[] {orgIdField, confcodeListField},
			            new String[] {"orgId", "confcodeList"});
			             
				if(!flag) 
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
	            		assignAccesscode();
	            	}
	            };
	            Future<?> future = Executors.newSingleThreadExecutor().submit(runnable);
	            if(future.isDone()) 
	            {
	            	LOGGER.info("future.isDone() is true");
	            }
			}
		});
		
		//点击从SP回收接入号按钮事件
		this.recoverAccesscodeBtn.addMouseListener(new MouseAdapter() 
		{
			public void mouseClicked(MouseEvent e) 
			{
				showErrInfoWithColor("");
			             
				boolean flag = validateParams(
			            new JTextField[] {orgIdField, confcodeListField},
			            new String[] {"orgId", "confcodeList"});
			             
				if(!flag) 
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
	            		recoverAccesscode();
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
	 * 给sp分配接入号
	 */
	private void assignAccesscode() 
	{
		Token token = LoginUtils.getToken();
		
		RestRequest request = new RestRequest(HTTPConstant.HTTP_METHOD_POST);
		request.setPayload(addAssignAccesscodeToSpBean());
		
		EcService.post("/conference/accesscode/assigntosp", request, errInfoLabel, token);
		
		EcService.finish();
	}
	
	/**
	 * 从sp回收接入号
	 */
	private void recoverAccesscode() 
	{
		Token token = LoginUtils.getToken();
		
		RestRequest request = new RestRequest(HTTPConstant.HTTP_METHOD_POST);
		request.setPayload(addAssignAccesscodeToSpBean());
		
		EcService.post("/conference/accesscode/recyclefromsp", request, errInfoLabel, token);
		
		EcService.finish();
	}
	
	/**
	 * 系统管理员删除接入号
	 */
	private void delAccesscode() 
	{
		Token token = LoginUtils.getToken();
		
		RestRequest request = new RestRequest(HTTPConstant.HTTP_METHOD_POST);
		request.setPayload(addDeleteAccesscodeBean());
		
		EcService.post("/conference/accesscode/delete", request, errInfoLabel, token);
		
		EcService.finish();
	}
	
	/**
	 * 接收删除会议号参数并封装
	 * @return
	 */
	private DeleteAccesscodeBean addDeleteAccesscodeBean() 
	{
		DeleteAccesscodeBean deleteAccesscodeBean = new DeleteAccesscodeBean();
		
		//利用正则，区分中英文逗号都可分割
		String deleRegex = ",|，";
		String[] deleConfcodeArray = confcodeListField.getText().split(deleRegex);
		List<String> deleConfcodeList = Arrays.asList(deleConfcodeArray);
		
		deleteAccesscodeBean.setConfCodeList(deleConfcodeList);
		
		return deleteAccesscodeBean;
	}
	
	/**
	 * 给sp分配接入号或者从sp回收接入号参数接收并封装
	 * @return
	 */
	private AssignAccesscodeToSpBean addAssignAccesscodeToSpBean() 
	{
		AssignAccesscodeToSpBean assignAccesscodeToSpBean = new AssignAccesscodeToSpBean();
		
		//利用正则，区分中英文逗号都可分割
		String adminRegex = ",|，";
		String[] adminConfcodeArray = confcodeListField.getText().split(adminRegex);
		List<String> adminConfcodeList = Arrays.asList(adminConfcodeArray);
		
		assignAccesscodeToSpBean.setConfcodeList(adminConfcodeList);
		
		assignAccesscodeToSpBean.setOrgId(orgIdField.getText());
		
		return assignAccesscodeToSpBean;
		
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
	 * 判断参数为空性
	 * Judgment parameter is empty
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

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		// 构造一个新的JFrame，作为新窗口。
		// Construct a new JFrame as a new window
		JFrame frame = new JFrame(Properties_language_Utils.getValue("admin.AccesscodePanel.frameTitle"));
		
		setSize(1050, 680);
	    frame.setSize(1050, 680);
	    
	    JPanel center = new CreateAccesscodePanel();
	    JScrollPane centerJPane = new JScrollPane(center);
	    
	    frame.getContentPane().add(centerJPane, BorderLayout.CENTER);
        frame.setResizable(true);
        frame.setVisible(true);
	}
	
}
