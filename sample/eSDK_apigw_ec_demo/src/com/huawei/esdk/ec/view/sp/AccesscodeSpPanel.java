package com.huawei.esdk.ec.view.sp;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.List;
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
import com.huawei.esdk.ec.bean.sp.AccesscodeListBean;
import com.huawei.esdk.ec.bean.sp.ActivateAccesscodeBean;
import com.huawei.esdk.ec.bean.sp.AssignAccesscodeToCorpBean;
import com.huawei.esdk.ec.constant.HTTPConstant;
import com.huawei.esdk.ec.service.EcService;
import com.huawei.esdk.ec.utils.LoginUtils;
import com.huawei.esdk.ec.utils.Properties_language_Utils;
import com.huawei.esdk.ec.utils.StringUtils;
import com.huawei.esdk.ec.view.common.LogPanel;
import com.huawei.esdk.ec.view.common.MyButton;

public class AccesscodeSpPanel extends JPanel
{
	private static final long serialVersionUID = -3979677738123657029L;

	private static final Logger LOGGER = LogManager.getLogger(AccesscodeSpPanel.class);
	
	private JLabel errInfoLabel = new JLabel();
	
	private JLabel tipInfoLabel = new JLabel(Properties_language_Utils.
			getValue("sp.AccesscodeSpPanel.tipInfoLabel"));
	
	private JLabel reqParams = new JLabel(Properties_language_Utils.getValue("reqParams"));
	
	private JLabel accesscodeidLabel = new JLabel("accesscodeid:");
	private JTextField accesscodeidField = new JTextField(10);
	
	private JLabel searchKeyLabel = new JLabel("searchKey:");
	private JTextField searchKeyField = new JTextField(10);
	
	private JLabel pageIndexLabel = new JLabel("pageIndex:");
	private JTextField pageIndexField = new JTextField(10);
	
	private JLabel pageSizeLabel = new JLabel("pageSize:");
	private JTextField pageSizeField = new JTextField(10);
	
	private JLabel confTypeLabel = new JLabel("confType:");
	private JTextField confTypeField = new JTextField(10);
	
	private JLabel isShareLabel = new JLabel("isShare:");
	private JTextField isShareField = new JTextField(10);
	
	private JLabel isAssignReqQryLabel = new JLabel("isAssignReqQry:");
	private JTextField isAssignReqQryField = new JTextField(10);
	
	private JLabel orgIdLabel = new JLabel("orgId:");
	private JTextField orgIdField = new JTextField(10);
	
	private JLabel confcodeListLabel = new JLabel("confcodeList:");
	private JTextField confcodeListField = new JTextField(10);
	
	private JLabel confCodeIDLabel = new JLabel("confCodeID:");
	private JTextField confCodeIDField = new JTextField(10);
	
	private JLabel orgIDLabel = new JLabel("orgID:");
	private JTextField orgIDField = new JTextField(10);
	
	//查询接入号列表按钮
	private JButton queryAccesscodeListBtn = new MyButton(Properties_language_Utils.
			getValue("sp.AccesscodeSpPanel.queryAccesscodeListBtn"), 260, 35);
	
	//查询接入号详情按钮
	private JButton queryAccesscodeBtn = new MyButton(Properties_language_Utils.
			getValue("sp.AccesscodeSpPanel.queryAccesscodeBtn"), 260, 35);
	
	//给企业分配接入号按钮
	private JButton assignAccesscodeSpBtn = new MyButton(Properties_language_Utils.
			getValue("sp.AccesscodeSpPanel.assignAccesscodeSpBtn"), 260, 35);
	
	//从企业回收接入号按钮
	private JButton recoverAccesscodeSpBtn = new MyButton(Properties_language_Utils.
			getValue("sp.AccesscodeSpPanel.recoverAccesscodeSpBtn"), 260, 35);
	
	//激活接入号按钮
	private JButton activateAccesscodeBtn = new MyButton(Properties_language_Utils.
			getValue("sp.AccesscodeSpPanel.activateAccesscodeBtn"), 260, 35);
	
	//去激活接入号按钮
	private JButton deactivateAccesscodeBtn = new MyButton(Properties_language_Utils.
			getValue("sp.AccesscodeSpPanel.deactivateAccesscodeBtn"), 260, 35);
	
	public AccesscodeSpPanel() 
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
	    
	    buildPanel(panel, gridbag, c, new JComponent[] {accesscodeidLabel}, 0, 6, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {accesscodeidField}, 1, 6, 10, 20, 2, 1);
	    
	    buildPanel(panel, gridbag, c, new JComponent[] {searchKeyLabel}, 0, 1, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {searchKeyField}, 1, 1, 10, 20, 2, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {pageIndexLabel}, 2, 1, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {pageIndexField}, 3, 1, 6, 20, 2, 1);
	    
	    buildPanel(panel, gridbag, c, new JComponent[] {pageSizeLabel}, 0, 2, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {pageSizeField}, 1, 2, 10, 20, 2, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {confTypeLabel}, 2, 2, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {confTypeField}, 3, 2, 6, 20, 2, 1);
	    
	    buildPanel(panel, gridbag, c, new JComponent[] {isShareLabel}, 0, 3, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {isShareField}, 1, 3, 10, 20, 2, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {isAssignReqQryLabel}, 2, 3, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {isAssignReqQryField}, 3, 3, 6, 20, 2, 1);
	    
	    buildPanel(panel, gridbag, c, new JComponent[] {orgIdLabel}, 0, 4, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {orgIdField}, 1, 4, 10, 20, 2, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {confcodeListLabel}, 2, 4, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {confcodeListField}, 3, 4, 6, 20, 2, 1);
	    
	    buildPanel(panel, gridbag, c, new JComponent[] {confCodeIDLabel}, 0, 5, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {confCodeIDField}, 1, 5, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {orgIDLabel}, 2, 5, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {orgIDField}, 3, 5, 10, 20, 1, 1);
	    
	  
	    buildPanel(panel, gridbag, c, new JComponent[] {queryAccesscodeListBtn}, 0, 7, 10, 20, 2, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {queryAccesscodeBtn}, 2, 7, 10, 20, 2, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {assignAccesscodeSpBtn}, 0, 8, 10, 20, 2, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {recoverAccesscodeSpBtn}, 2, 8, 10, 20, 2, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {activateAccesscodeBtn}, 0, 9, 10, 20, 2, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {deactivateAccesscodeBtn}, 2, 9, 10, 20, 2, 1);
	    
	    buildPanel(panel, gridbag, c, new JComponent[] {errInfoLabel}, 0, 10, 100, 5, 4, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {tipInfoLabel}, 0, 11, 100, 5, 4, 1);
	    
	    //报文位置
	    //message location
		buildPanel(panel, gridbag, c, new JComponent[] {new LogPanel()}, 4, 0, 10, 20, 4, 10);
	    return panel;
	}
	
	private void init() 
	{
		tipInfoLabel.setForeground(Color.BLUE);
		//查询接入号列表按钮事件
		this.queryAccesscodeListBtn.addMouseListener(new MouseAdapter() 
		{
			public void mouseClicked(MouseEvent e) 
			{
				showErrInfoWithColor("");
			             
				boolean flag = validateParams(
			            new JTextField[] {pageIndexField, pageSizeField},
			            new String[] {"pageIndex", "pageSize"});
			             
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
	            		queryAccesscodeList();
	            	}
	            };
	            Future<?> future = Executors.newSingleThreadExecutor().submit(runnable);
	            if(future.isDone()) 
	            {
	            	LOGGER.info("future.isDone() is true");
	            }
			}
		});
		
		//查询接入号详情按钮事件
		this.queryAccesscodeBtn.addMouseListener(new MouseAdapter() 
		{
			public void mouseClicked(MouseEvent e) 
			{
				showErrInfoWithColor("");
			             
				boolean flag = validateParams(
			            new JTextField[] {accesscodeidField},
			            new String[] {"accesscodeid"});
			             
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
	            		queryAccesscode();
	            	}
	            };
	            Future<?> future = Executors.newSingleThreadExecutor().submit(runnable);
	            if(future.isDone()) 
	            {
	            	LOGGER.info("future.isDone() is true");
	            }
			}
		});
				
		//点击给企业分配接入号按钮事件
		this.assignAccesscodeSpBtn.addMouseListener(new MouseAdapter() 
		{
			public void mouseClicked(MouseEvent e) 
			{
				showErrInfoWithColor("");
			             
				boolean flag = validateParams(
			            new JTextField[] {confcodeListField, orgIdField},
			            new String[] {"confcodeList", "orgId"});
			             
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
	            		assignAccesscodeSp();
	            	}
	            };
	            Future<?> future = Executors.newSingleThreadExecutor().submit(runnable);
	            if(future.isDone()) 
	            {
	            	LOGGER.info("future.isDone() is true");
	            }
			}
		});
		
		//点击从企业回收接入号按钮事件
		this.recoverAccesscodeSpBtn.addMouseListener(new MouseAdapter() 
		{
			public void mouseClicked(MouseEvent e) 
			{
				showErrInfoWithColor("");
			             
				boolean flag = validateParams(
			            new JTextField[] {confcodeListField, orgIdField},
			            new String[] {"confcodeList", "orgId"});
			             
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
	            		recoverAccesscodeSp();
	            	}
	            };
	            Future<?> future = Executors.newSingleThreadExecutor().submit(runnable);
	            if(future.isDone()) 
	            {
	            	LOGGER.info("future.isDone() is true");
	            }
			}
		});
		
		//点击激活接入号按钮事件
		this.activateAccesscodeBtn.addMouseListener(new MouseAdapter() 
		{
			public void mouseClicked(MouseEvent e) 
			{
				showErrInfoWithColor("");
			             
				boolean flag = validateParams(
			            new JTextField[] {confCodeIDField, orgIDField},
			            new String[] {"confCodeID", "orgID"});
			             
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
	            		activateAccesscode();
	            	}
	            };
	            Future<?> future = Executors.newSingleThreadExecutor().submit(runnable);
	            if(future.isDone()) 
	            {
	            	LOGGER.info("future.isDone() is true");
	            }
			}
		});
		
		//点击去激活接入号按钮事件
		this.deactivateAccesscodeBtn.addMouseListener(new MouseAdapter() 
		{
			public void mouseClicked(MouseEvent e) 
			{
				showErrInfoWithColor("");
			             
				boolean flag = validateParams(
			            new JTextField[] {confCodeIDField, orgIDField},
			            new String[] {"confCodeID", "orgID"});
			             
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
	            		deactivateAccesscode();
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
	 * 给企业分配接入号
	 */
	private void assignAccesscodeSp() 
	{
		Token token = LoginUtils.getToken();
		
		RestRequest request = new RestRequest(HTTPConstant.HTTP_METHOD_POST);
		request.setPayload(addAssignAccesscodeToCorpBean());
		
		EcService.post("/conference/accesscode/assigntocorp", request, errInfoLabel,token);
		
		EcService.finish();
	}
	
	/**
	 * 从企业回收接入号
	 */
	private void recoverAccesscodeSp() 
	{
		Token token = LoginUtils.getToken();
		
		RestRequest request = new RestRequest(HTTPConstant.HTTP_METHOD_POST);
		request.setPayload(addAssignAccesscodeToCorpBean());
		
		EcService.post("/conference/accesscode/recyclefromcorp", request, errInfoLabel,token);
		
		EcService.finish();
	}
	
	/**
	 * 激活接入号
	 */
	private void activateAccesscode()
	{
		Token token = LoginUtils.getToken();
		
		RestRequest request = new RestRequest(HTTPConstant.HTTP_METHOD_POST);
		request.setPayload(addActivateAccesscodeBean());
		
		EcService.post("/conference/accesscode/active", request, errInfoLabel,token);
		
		EcService.finish();
	}
	
	/**
	 * 去激活接入号
	 */
	private void deactivateAccesscode() 
	{
		Token token = LoginUtils.getToken();
		
		RestRequest request = new RestRequest(HTTPConstant.HTTP_METHOD_POST);
		request.setPayload(addActivateAccesscodeBean());
		
		EcService.post("/conference/accesscode/deactive", request, errInfoLabel,token);
		
		EcService.finish();
	}
	
	/**
	 * 查询接入号列表
	 */
	private void queryAccesscodeList() 
	{
		Token token = LoginUtils.getToken();
		
		RestRequest request = new RestRequest(HTTPConstant.HTTP_METHOD_POST);
		request.setPayload(addAccesscodeListBean());
		
		EcService.post("/conference/accesscode/list", request, errInfoLabel,token);
		
		EcService.finish();
	}
	
	/**
	 * 查询接入号详情
	 */
	private void queryAccesscode() 
	{
		Token token = LoginUtils.getToken();
		
		RestRequest request = new RestRequest(HTTPConstant.HTTP_METHOD_GET);
		
		EcService.get("/conference/accesscode/" + accesscodeidField.getText().replace("+", "%2B"), request, errInfoLabel, token);
		
		EcService.finish();
	}
	
	/**
	 * 查询会议号列表参数接收并封装
	 * @return
	 */
	private AccesscodeListBean addAccesscodeListBean() 
	{
		AccesscodeListBean accesscodeListBean = new AccesscodeListBean();
		
		if (StringUtils.isNotEmpty(searchKeyField.getText()))
		{
			accesscodeListBean.setSearchKey(searchKeyField.getText());
		}
		accesscodeListBean.setPageIndex(pageIndexField.getText());
		accesscodeListBean.setPageSize(pageSizeField.getText());
		if (StringUtils.isNotEmpty(confTypeField.getText())) 
		{
			accesscodeListBean.setConfType(confTypeField.getText());
		}
		if (StringUtils.isNotEmpty(orgIDField.getText())) 
		{
			accesscodeListBean.setOrgID(orgIDField.getText());
		}
		if (StringUtils.isNotEmpty(isShareField.getText())) 
		{
			accesscodeListBean.setIsShare(Integer.parseInt(isShareField.getText()));
		}
		if (StringUtils.isNotEmpty(isAssignReqQryField.getText())) 
		{
			accesscodeListBean.setIsAssignReqQry(Integer.parseInt(isAssignReqQryField.getText()));
		}
		
		return accesscodeListBean;
	}
	
	/**
	 * 激活/去激活参数接收并封装
	 * @return
	 */
	private ActivateAccesscodeBean addActivateAccesscodeBean() 
	{
		ActivateAccesscodeBean activateAccesscodeBean = new ActivateAccesscodeBean();
		activateAccesscodeBean.setConfCodeID(confCodeIDField.getText());
		activateAccesscodeBean.setOrgID(orgIDField.getText());
		
		return activateAccesscodeBean;
	}
	
	/**
	 * 给企业分配接入号或者回收分配号参数接收并封装
	 * @return
	 */
	private AssignAccesscodeToCorpBean addAssignAccesscodeToCorpBean() 
	{
		AssignAccesscodeToCorpBean assignAccesscodeToCorpBean = new AssignAccesscodeToCorpBean();
		//利用正则，区分中英文逗号都可分割
		String regex = ",|，";
		String[] confcodeList = confcodeListField.getText().split(regex);
		List<String> confcodeLst = Arrays.asList(confcodeList);
		
		assignAccesscodeToCorpBean.setConfcodeList(confcodeLst);
		assignAccesscodeToCorpBean.setOrgId(orgIdField.getText());
		
		return assignAccesscodeToCorpBean;
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

}
