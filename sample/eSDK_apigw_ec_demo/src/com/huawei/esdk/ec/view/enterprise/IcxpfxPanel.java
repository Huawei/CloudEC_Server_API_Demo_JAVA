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
import com.huawei.esdk.ec.bean.enterprise.IcxPfxRestBean;
import com.huawei.esdk.ec.bean.enterprise.IcxPfxRmvBean;
import com.huawei.esdk.ec.bean.enterprise.IcxPfxRmvRestBean;
import com.huawei.esdk.ec.bean.enterprise.IcxPfxSearchRestBean;
import com.huawei.esdk.ec.constant.HTTPConstant;
import com.huawei.esdk.ec.service.EcService;
import com.huawei.esdk.ec.utils.LoginUtils;
import com.huawei.esdk.ec.utils.Properties_language_Utils;
import com.huawei.esdk.ec.utils.StringUtils;
import com.huawei.esdk.ec.view.common.LogPanel;
import com.huawei.esdk.ec.view.common.MyButton;
/**
 * 企业内字冠/业务字冠视图
 * @author wWX534262
 *
 */
public class IcxpfxPanel extends JPanel
{
	private static final long serialVersionUID = 6342071967784049019L;
	
	private static final Logger LOGGER = LogManager.getLogger(IcxpfxPanel.class);
	
	private JLabel errInfoLabel = new JLabel();
	
	private JLabel reqParams = new JLabel(Properties_language_Utils.getValue("reqParams"));
	
	private JLabel corpIdLabel = new JLabel("corpId:");
	private JTextField corpIdField = new JTextField(10);
	
	private JLabel wpfLabel = new JLabel("wpf:");
	private JTextField wpfField = new JTextField(10);
	
	private JLabel intraGrpPfxLabel = new JLabel("intraGrpPfx:");
	private JTextField intraGrpPfxField = new JTextField(10);
	
	private JLabel srvNameLabel = new JLabel("srvName:");
	private JTextField srvNameField = new JTextField(10);
	
	private JLabel minNumLenLabel = new JLabel("minNumLen:");
	private JTextField minNumLenField = new JTextField(10);
	
	private JLabel maxNumLenLabel = new JLabel("maxNumLen:");
	private JTextField maxNumLenField = new JTextField(10);
	
	private JLabel pageSizeLabel = new JLabel("pageSize:");
	private JTextField pageSizeField = new JTextField(10);
	
	private JLabel pageIndexLabel = new JLabel("pageIndex:");
	private JTextField pageIndexField = new JTextField(10);
	
	private JLabel isNormalLabel = new JLabel("isNormal:");
	private JTextField isNormalField = new JTextField(10);
	
	private JButton addIcxpfxBtn = new MyButton(Properties_language_Utils.
			getValue("enter.IcxpfxPanel.addIcxpfxBtn"), 200, 35);
	
	private JButton modifyIcxpfxBtn = new MyButton(Properties_language_Utils.
			getValue("enter.IcxpfxPanel.modifyIcxpfxBtn"), 200, 35);
	//累加删除参数按钮
	private JButton addDelParams = new MyButton(Properties_language_Utils.
			getValue("enter.IcxpfxPanel.addDelParams"), 200, 35);
	
	private JButton delIcxpfxBtn = new MyButton(Properties_language_Utils.
			getValue("enter.IcxpfxPanel.delIcxpfxBtn"), 200, 35);
	
	private JButton queryIcxpfxBtn = new MyButton(Properties_language_Utils.
			getValue("enter.IcxpfxPanel.queryIcxpfxBtn"), 200, 35);
	
	private List<IcxPfxRmvBean> icxPfxRmvBeanList = new ArrayList<>();
	
	//点击累加删除会议后变为true，用于保证不点“击累加删除参数按钮”则删除一条数据，点击则控制删除一条或多条数据
	private boolean flag = false;
	
	public IcxpfxPanel() 
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
	    
	    buildPanel(panel, gridbag, c, new JComponent[] {intraGrpPfxLabel}, 0, 3, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {intraGrpPfxField}, 1, 3, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {srvNameLabel}, 2, 3, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {srvNameField}, 3, 3, 10, 20, 1, 1);
	    
	    buildPanel(panel, gridbag, c, new JComponent[] {minNumLenLabel}, 0, 4, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {minNumLenField}, 1, 4, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {maxNumLenLabel}, 2, 4, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {maxNumLenField}, 3, 4, 10, 20, 1, 1);
	    
	    buildPanel(panel, gridbag, c, new JComponent[] {pageIndexLabel}, 0, 5, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {pageIndexField}, 1, 5, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {pageSizeLabel}, 2, 5, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {pageSizeField}, 3, 5, 10, 20, 1, 1);
	    
	    buildPanel(panel, gridbag, c, new JComponent[] {isNormalLabel}, 0, 6, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {isNormalField}, 1, 6, 10, 20, 1, 1);
	  
	    buildPanel(panel, gridbag, c, new JComponent[] {addDelParams}, 0, 7, 10, 20, 2, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {addIcxpfxBtn}, 2, 7, 10, 20, 2, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {modifyIcxpfxBtn}, 0, 8, 10, 20, 2, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {delIcxpfxBtn}, 2, 8, 10, 20, 2, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {queryIcxpfxBtn}, 0, 9, 10, 20, 2, 1);
	    
	    buildPanel(panel, gridbag, c, new JComponent[] {errInfoLabel}, 0, 10, 100, 5, 4, 1);
	    
	    //报文位置
	    //message location
		buildPanel(panel, gridbag, c, new JComponent[] {new LogPanel()}, 4, 0, 10, 20, 4, 10);
		return panel;
	}
	
	private void init() 
	{
		//点击删除参数累加按钮事件
		this.addDelParams.addMouseListener(new MouseAdapter() 
		{
			public void mouseClicked(MouseEvent e) 
			{
				flag = true;
				showErrInfoWithColor("");
				boolean flag = validateParams(new JTextField[] {intraGrpPfxField}, 
						new String[] {"intraGrpPfx"});
				 
				if (!flag)
	            {
					return;
	            }
				
				icxPfxRmvBeanList.add(addIcxPfxRmvBean());
				showErrInfoWithColor(Properties_language_Utils.getValue("enter.IcxpfxPanel.resultTip"));
			}
		});
		
		//点击增加企业内字冠/业务字冠按钮事件
		this.addIcxpfxBtn.addMouseListener(new MouseAdapter() 
		{
			public void mouseClicked(MouseEvent e) 
			{
				showErrInfoWithColor("");
	             
	            boolean flag = validateParams(
	            		new JTextField[] {corpIdField, intraGrpPfxField, srvNameField, minNumLenField, maxNumLenField},
	            		new String[] {"corpId", "intraGrpPfx", "srvName", "minNumLen", "maxNumLen"});
	             
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
	            		addIcxpfx();
	            	}
	            };
	            Future<?> future = Executors.newSingleThreadExecutor().submit(runnable);
	            if(future.isDone()) 
	            {
	            	LOGGER.info("future.isDone() is true");
	            }
			}
		});
		 
		//点击修改企业内字冠/业务字冠按钮事件
		this.modifyIcxpfxBtn.addMouseListener(new MouseAdapter() 
		{
			public void mouseClicked(MouseEvent e) 
			{
				showErrInfoWithColor("");
	             
	            boolean flag = validateParams(
	            		new JTextField[] {corpIdField, intraGrpPfxField},
	            		new String[] {"corpId", "intraGrpPfx"});
	             
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
	            		modifyIcxpfx();
	            	}
	            };
	            Future<?> future = Executors.newSingleThreadExecutor().submit(runnable);
	            if(future.isDone()) 
	            {
	            	LOGGER.info("future.isDone() is true");
	            }
			}
		});
		 
		//点击删除企业内字冠/业务字冠按钮事件
		this.delIcxpfxBtn.addMouseListener(new MouseAdapter() 
		{
			public void mouseClicked(MouseEvent e) 
			{
				showErrInfoWithColor("");
	             
	            boolean flag = validateParams(
	            		new JTextField[] {corpIdField, intraGrpPfxField},
	            		new String[] {"corpId", "intraGrpPfx"});
	             
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
	            		delIcxpfx();
	            	}
	            };
	            Future<?> future = Executors.newSingleThreadExecutor().submit(runnable);
	            if(future.isDone()) 
	            {
	            	LOGGER.info("future.isDone() is true");
	            }
			}
		});
		 
		//点击查询企业内字冠/业务字冠按钮事件
		this.queryIcxpfxBtn.addMouseListener(new MouseAdapter() 
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
	            		queryIcxpfx();
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
	 * 增加企业内字冠/业务字冠
	 */
	private void addIcxpfx() 
	{
		Token token = LoginUtils.getToken();
		
		RestRequest request = new RestRequest(HTTPConstant.HTTP_METHOD_POST);
		//传递参数体
		request.setPayload(addIcxPfxRestBean());
		
		EcService.post("/corp/" + corpIdField.getText() + "/icxpfx", 
    			request, errInfoLabel, token);
		
		EcService.finish();
	}
	
	/**
	 *  修改企业内字冠/业务字冠
	 */
	private void modifyIcxpfx() 
	{
		Token token = LoginUtils.getToken();
		
		RestRequest request = new RestRequest(HTTPConstant.HTTP_METHOD_PUT);
		//传递参数体
		request.setPayload(addIcxPfxRestBean());
		
		EcService.put("/corp/" + corpIdField.getText() + "/icxpfx", 
    			request, errInfoLabel, token);
		
		EcService.finish();
	}
	
	/**
	 * 删除企业内字冠/业务字冠
	 */
	private void delIcxpfx() 
	{
		Token token = LoginUtils.getToken();
		
		RestRequest request = new RestRequest(HTTPConstant.HTTP_METHOD_POST);
		//传递参数体
		request.setPayload(addIcxPfxRmvRestBean());
		
		EcService.post("/corp/" + corpIdField.getText() + "/icxpfx/delete", 
    			request, errInfoLabel, token);
		
		EcService.finish();
	}
	
	/**
	 * 查询企业内字冠/业务字冠
	 */
	private void queryIcxpfx() 
	{
		Token token = LoginUtils.getToken();
		RestRequest request = new RestRequest(HTTPConstant.HTTP_METHOD_POST);
		
		request.setPayload(addIcxPfxSearchRestBean());
		
		EcService.post("/corp/" + corpIdField.getText() + "/icxpfx/search", 
    			request, errInfoLabel, token);
		
		EcService.finish();
	}
	
	//接收参数实体并封装成对象
	private IcxPfxRestBean addIcxPfxRestBean() 
	{
		IcxPfxRestBean addBean = new IcxPfxRestBean();
		if (StringUtils.isNotEmpty(wpfField.getText())) 
		{
			addBean.setWpf(Boolean.parseBoolean(wpfField.getText()));
		}
		addBean.setIntraGrpPfx(intraGrpPfxField.getText());
		addBean.setSrvName(srvNameField.getText());
		addBean.setMinNumLen(Integer.parseInt(minNumLenField.getText()));
		addBean.setMaxNumLen(Integer.parseInt(maxNumLenField.getText()));
		
		return addBean;
	}
	
	//删除企业内字冠/业务字冠参数封装
	private IcxPfxRmvBean addIcxPfxRmvBean() 
	{
		IcxPfxRmvBean icxPfxRmvBean = new IcxPfxRmvBean();
		icxPfxRmvBean.setIntraGrpPfx(intraGrpPfxField.getText());
		if (StringUtils.isNotEmpty(wpfField.getText())) 
		{
			icxPfxRmvBean.setWpf(Boolean.parseBoolean(wpfField.getText()));
		}
		
		return icxPfxRmvBean;
	}
	
	//删除操作数据内容IcxPfxRmvRestBean封装
	private IcxPfxRmvRestBean addIcxPfxRmvRestBean() 
	{
		IcxPfxRmvRestBean icxPfxRmvRestBean = new IcxPfxRmvRestBean();
		if(flag == false)
		{
			icxPfxRmvBeanList.add(addIcxPfxRmvBean());
		}
		
		icxPfxRmvRestBean.setIntraGrpPfxLst(icxPfxRmvBeanList);
		
		return icxPfxRmvRestBean;
	}
	
	//企业内字冠/业务字冠查询条件 IcxPfxSearchRestBean参数封装
	private IcxPfxSearchRestBean addIcxPfxSearchRestBean() 
	{
		IcxPfxSearchRestBean icxPfxSearchRestBean = new IcxPfxSearchRestBean();
		
		if (StringUtils.isNotEmpty(intraGrpPfxField.getText())) 
		{
			icxPfxSearchRestBean.setIntraGrpPfx(intraGrpPfxField.getText());
		}
		if (StringUtils.isNotEmpty(pageSizeField.getText())) 
		{
			icxPfxSearchRestBean.setPageSize(Integer.parseInt(pageSizeField.getText()));
		}
		if (StringUtils.isNotEmpty(pageIndexField.getText())) 
		{
			icxPfxSearchRestBean.setPageIndex(Integer.parseInt(pageIndexField.getText()));
		}
		if (StringUtils.isNotEmpty(wpfField.getText())) 
		{
			icxPfxSearchRestBean.setWpf(Boolean.parseBoolean(wpfField.getText()));
		}
		if (StringUtils.isNotEmpty(isNormalField.getText())) 
		{
			icxPfxSearchRestBean.setIsNormal(Boolean.parseBoolean(isNormalField.getText()));
		}
		
		return icxPfxSearchRestBean;
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
