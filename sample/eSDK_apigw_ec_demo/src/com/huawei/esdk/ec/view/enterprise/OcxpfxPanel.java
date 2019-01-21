package com.huawei.esdk.ec.view.enterprise;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
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
import com.huawei.esdk.ec.bean.enterprise.OcxPfxRestBean;
import com.huawei.esdk.ec.bean.enterprise.OcxPfxRmvBean;
import com.huawei.esdk.ec.bean.enterprise.OcxPfxRmvRestBean;
import com.huawei.esdk.ec.bean.enterprise.OcxPfxSearchRestBean;
import com.huawei.esdk.ec.constant.HTTPConstant;
import com.huawei.esdk.ec.service.EcService;
import com.huawei.esdk.ec.utils.LoginUtils;
import com.huawei.esdk.ec.utils.Properties_language_Utils;
import com.huawei.esdk.ec.utils.StringUtils;
import com.huawei.esdk.ec.view.common.LogPanel;
import com.huawei.esdk.ec.view.common.MyButton;
/**
 * 出企业字冠视图
 * @author wWX534262
 *
 */
public class OcxpfxPanel extends JPanel
{
	private static final long serialVersionUID = 1944888069007535722L;

	private static final Logger LOGGER = LogManager.getLogger(OcxpfxPanel.class);
	
	private JLabel errInfoLabel = new JLabel();
	
	private JLabel reqParams = new JLabel(Properties_language_Utils.getValue("reqParams"));
	
	private JLabel corpIdLabel = new JLabel("corpId:");
	private JTextField corpIdField = new JTextField(10);
	
	private JLabel wpfLabel = new JLabel("wpf:");
	private JTextField wpfField = new JTextField(10);
	
	private JLabel outGrpPfxLabel = new JLabel("outGrpPfx:");
	private JTextField outGrpPfxField = new JTextField(10);
	
	private JLabel outGrpTypeLabel = new JLabel("outGrpType:");
	private JTextField outGrpTypeField = new JTextField(10);
	
	private JLabel deleteLengthLabel = new JLabel("deleteLength:");
	private JTextField deleteLengthField = new JTextField(10);
	
	private JLabel pageIndexLabel = new JLabel("pageIndex:");
	private JTextField pageIndexField = new JTextField(10);
	
	private JLabel pageSizeLabel = new JLabel("pageSize:");
	private JTextField pageSizeField = new JTextField(10);
	
	private JButton addOutGrpPfxBtn = new MyButton(Properties_language_Utils.
			getValue("enter.OcxpfxPanel.addOutGrpPfxBtn"),150,35);
	
	private JButton modifyOutGrpPfxBtn = new MyButton(Properties_language_Utils.
			getValue("enter.OcxpfxPanel.modifyOutGrpPfxBtn"),150,35);
	
	//累加删除参数按钮
	private JButton addOutDelParams = new MyButton(Properties_language_Utils.
			getValue("enter.OcxpfxPanel.addOutDelParams"), 150, 35);
	
	private JButton delOutGrpPfxBtn = new MyButton(Properties_language_Utils.
			getValue("enter.OcxpfxPanel.delOutGrpPfxBtn"),150,35);
	
	private JButton queryOutGrpPfxBtn = new MyButton(Properties_language_Utils.
			getValue("enter.OcxpfxPanel.queryOutGrpPfxBtn"),150,35);
	
	//删除数据内容，不超过30条
	List<OcxPfxRmvBean> ocxPfxRmvBeanList = new ArrayList<>();
	
	//点击累加删除会议后变为true，用于保证不点“击累加删除参数按钮”则删除一条数据，点击则控制删除一条或多条数据
	private boolean flag = false;
	
	public OcxpfxPanel() 
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
	    
	    buildPanel(panel, gridbag, c, new JComponent[] {corpIdLabel}, 0, 1, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {corpIdField}, 1, 1, 10, 20, 2, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {wpfLabel}, 2, 1, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {wpfField}, 3, 1, 6, 20, 2, 1);
	    
	    buildPanel(panel, gridbag, c, new JComponent[] {outGrpPfxLabel}, 0, 3, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {outGrpPfxField}, 1, 3, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {outGrpTypeLabel}, 2, 3, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {outGrpTypeField}, 3, 3, 10, 20, 1, 1);
	    
	    buildPanel(panel, gridbag, c, new JComponent[] {deleteLengthLabel}, 0, 4, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {deleteLengthField}, 1, 4, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {pageIndexLabel}, 2, 4, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {pageIndexField}, 3, 4, 10, 20, 1, 1);
	    
	    buildPanel(panel, gridbag, c, new JComponent[] {pageSizeLabel}, 0, 5, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {pageSizeField}, 1, 5, 10, 20, 1, 1);
	  
	    buildPanel(panel, gridbag, c, new JComponent[] {addOutDelParams}, 0, 6, 10, 20, 2, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {addOutGrpPfxBtn}, 2, 6, 10, 20, 2, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {modifyOutGrpPfxBtn}, 0, 7, 10, 20, 2, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {delOutGrpPfxBtn}, 2, 7, 10, 20, 2, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {queryOutGrpPfxBtn}, 0, 8, 10, 20, 2, 1);
	    
	    buildPanel(panel, gridbag, c, new JComponent[] {errInfoLabel}, 0, 9, 100, 5, 4, 1);
	    
	    //报文位置
	    //message location
		buildPanel(panel, gridbag, c, new JComponent[] {new LogPanel()}, 4, 0, 10, 20, 4, 10);
	    return panel;
	}
	
	private void init() 
	{
		//点击删除参数累加按钮事件
		this.addOutDelParams.addMouseListener(new MouseAdapter() 
		{
			public void mouseClicked(MouseEvent e) 
			{
				flag = true;
				showErrInfoWithColor("");
				boolean flag = validateParams(new JTextField[] {outGrpPfxField}, 
						new String[] {"outGrpPfx"});
				 
				if (!flag)
	            {
					return;
	            }
				
				ocxPfxRmvBeanList.add(addOcxPfxRmvBean());
				showErrInfoWithColor(Properties_language_Utils.getValue("enter.OcxpfxPanel.resultTip"));
			}
		});
		
		//点击增加出企业字冠按钮事件
		this.addOutGrpPfxBtn.addMouseListener(new MouseAdapter() 
		{
			public void mouseClicked(MouseEvent e) 
			{
				showErrInfoWithColor("");
	             
	            boolean flag = validateParams(
	            		new JTextField[] {corpIdField, outGrpPfxField},
	            		new String[] {"corpId", "outGrpPfx"});
	             
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
	            		addOutGrpPfx();
	            	}
	            };
	            Future<?> future = Executors.newSingleThreadExecutor().submit(runnable);
	            if(future.isDone()) 
	            {
	            	LOGGER.info("future.isDone() is true");
	            }
			}
		});
		 
		//点击修改出企业字冠按钮事件
		this.modifyOutGrpPfxBtn.addMouseListener(new MouseAdapter() 
		{
			public void mouseClicked(MouseEvent e) 
			{
				showErrInfoWithColor("");
	             
	            boolean flag = validateParams(
	            		new JTextField[] {corpIdField, outGrpPfxField},
	            		new String[] {"corpId", "outGrpPfx"});
	             
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
	            		modifyOutGrpPfx();
	            	}
	            };
	            Future<?> future = Executors.newSingleThreadExecutor().submit(runnable);
	            if(future.isDone()) 
	            {
	            	LOGGER.info("future.isDone() is true");
	            }
			}
		});
		 
		//点击删除出企业字冠按钮事件
		this.delOutGrpPfxBtn.addMouseListener(new MouseAdapter() 
		{
			public void mouseClicked(MouseEvent e) 
			{
				showErrInfoWithColor("");
	             
	            boolean flag = validateParams(
	            		new JTextField[] {corpIdField, outGrpPfxField},
	            		new String[] {"corpId", "outGrpPfx"});
	             
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
	            		delOutGrpPfx();
	            	}
	            };
	            Future<?> future = Executors.newSingleThreadExecutor().submit(runnable);
	            if(future.isDone()) 
	            {
	            	LOGGER.info("future.isDone() is true");
	            }
			}
		});
		 
		//点击查询出企业字冠按钮事件
		this.queryOutGrpPfxBtn.addMouseListener(new MouseAdapter() 
		{
			public void mouseClicked(MouseEvent e) 
			{
				showErrInfoWithColor("");
	             
	            boolean flag = validateParams(
	            		new JTextField[] {corpIdField, pageIndexField, pageSizeField},
	            		new String[] {"corpId", "pageIndex", "pageSize"});
	             
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
	            		queryOutGrpPfx();
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
	 * 增加出企业字冠
	 */
	private void addOutGrpPfx() 
	{
		Token token = LoginUtils.getToken();
		
		RestRequest request = new RestRequest(HTTPConstant.HTTP_METHOD_POST);
		request.setPayload(addOcxPfxRestBean());
		
		EcService.post("/corp/" + corpIdField.getText() + "/ocxpfx", 
    			request, errInfoLabel, token);
		
		EcService.finish();
	}
	
	/**
	 * 修改出企业字冠
	 */
	private void modifyOutGrpPfx() 
	{
		Token token = LoginUtils.getToken();
		
		RestRequest request = new RestRequest(HTTPConstant.HTTP_METHOD_PUT);
		request.setPayload(addOcxPfxRestBean());
		
		EcService.put("/corp/" + corpIdField.getText() + "/ocxpfx", 
    			request, errInfoLabel, token);
		
		EcService.finish();
	}
	
	/**
	 * 删除出企业字冠
	 */
	private void delOutGrpPfx() 
	{
		Token token = LoginUtils.getToken();
		
		RestRequest request = new RestRequest(HTTPConstant.HTTP_METHOD_POST);
		request.setPayload(addOcxPfxRmvRestBean());
		
		EcService.post("/corp/" + corpIdField.getText() + "/ocxpfx/delete", 
    			request, errInfoLabel, token);
		
		EcService.finish();
	}
	
	/**
	 * 查询出企业字冠
	 */
	private void queryOutGrpPfx() 
	{
		Token token = LoginUtils.getToken();
		
		RestRequest request = new RestRequest(HTTPConstant.HTTP_METHOD_POST);
		request.setPayload(addOcxPfxSearchRestBean());
		
		EcService.post("/corp/" + corpIdField.getText() + "/ocxpfx/search", 
    			request, errInfoLabel, token);
		
		EcService.finish();
	}
	
	/**
	 * 增加企业参数接收并封装成对象
	 * @return ocxPfxRestBean
	 */
	private OcxPfxRestBean addOcxPfxRestBean() 
	{
		OcxPfxRestBean ocxPfxRestBean = new OcxPfxRestBean();
		if (StringUtils.isNotEmpty(wpfField.getText()))
		{
			ocxPfxRestBean.setWpf(Boolean.parseBoolean(wpfField.getText()));
		}
		ocxPfxRestBean.setOutGrpPfx(outGrpPfxField.getText());
		if (StringUtils.isNotEmpty(outGrpTypeField.getText())) 
		{
			ocxPfxRestBean.setOutGrpType(outGrpTypeField.getText());
		}
		if (StringUtils.isNotEmpty(deleteLengthField.getText()))
		{
			ocxPfxRestBean.setDeleteLength(Integer.parseInt(deleteLengthField.getText()));
		}
		
		return ocxPfxRestBean;
	}
	
	/**
	 * 删除出企业字冠参数接收并封装
	 * @return
	 */
	private OcxPfxRmvBean addOcxPfxRmvBean() 
	{
		OcxPfxRmvBean ocxPfxRmvBean = new OcxPfxRmvBean();
		
		ocxPfxRmvBean.setOutGrpPfx(outGrpPfxField.getText());
		if (StringUtils.isNotEmpty(wpfField.getText())) 
		{
			ocxPfxRmvBean.setWpf(Boolean.parseBoolean(wpfField.getText()));
		}
		
		return ocxPfxRmvBean;
	}
	
	/**
	 * 删除操作数据内容OcxPfxRmvRestBean参数封装
	 * @return
	 */
	private OcxPfxRmvRestBean addOcxPfxRmvRestBean() 
	{
		OcxPfxRmvRestBean ocxPfxRmvRestBean = new OcxPfxRmvRestBean();
		
		if(flag == false)
		{
			ocxPfxRmvBeanList.add(addOcxPfxRmvBean());
		}
		
		ocxPfxRmvRestBean.setOutGrpPfxLst(ocxPfxRmvBeanList);
		
		return ocxPfxRmvRestBean;
	}
	
	/**
	 * 出企业字冠查询条件参数接收并封装
	 * @return
	 */
	private OcxPfxSearchRestBean addOcxPfxSearchRestBean() 
	{
		OcxPfxSearchRestBean ocxPfxSearchRestBean =  new OcxPfxSearchRestBean();
		if (StringUtils.isNotEmpty(outGrpPfxField.getText())) 
		{
			ocxPfxSearchRestBean.setOutGrpPfx(outGrpPfxField.getText());
		}
		ocxPfxSearchRestBean.setPageIndex(Integer.parseInt(pageIndexField.getText()));
		ocxPfxSearchRestBean.setPageSize(Integer.parseInt(pageSizeField.getText()));
		if (StringUtils.isNotEmpty(wpfField.getText())) 
		{
			ocxPfxSearchRestBean.setWpf(Boolean.parseBoolean(wpfField.getText()));
		}
		
		return ocxPfxSearchRestBean;
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
