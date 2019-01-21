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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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

import com.huawei.esdk.ec.bean.admin.AddSysnumBean;
import com.huawei.esdk.ec.bean.admin.DeleSysnumBean;
import com.huawei.esdk.ec.bean.admin.ModifySysnumBean;
import com.huawei.esdk.ec.bean.admin.NumIndexDeleBean;
import com.huawei.esdk.ec.bean.admin.PageQuerySysnumBean;
import com.huawei.esdk.ec.bean.common.RestRequest;
import com.huawei.esdk.ec.bean.common.Token;
import com.huawei.esdk.ec.constant.HTTPConstant;
import com.huawei.esdk.ec.service.EcService;
import com.huawei.esdk.ec.utils.LoginUtils;
import com.huawei.esdk.ec.utils.Properties_language_Utils;
import com.huawei.esdk.ec.utils.StringUtils;
import com.huawei.esdk.ec.view.common.LogPanel;
import com.huawei.esdk.ec.view.common.MyButton;
/**
 * 系统号段接口视图
 * @author wWX534262
 *
 */
public class SysnumPanel extends JPanel implements ActionListener
{
	private static final long serialVersionUID = -6835887365515123661L;
	
	private static final Logger LOGGER = LogManager.getLogger(SysnumPanel.class);
	
	private JLabel errInfoLabel = new JLabel();
	
	private JLabel reqParams = new JLabel(Properties_language_Utils.getValue("reqParams"));
	
	private JLabel searchKeyLabel = new JLabel("searchKey:");
	private JTextField searchKeyField = new JTextField(10);
	
	private JLabel assignStatusLabel = new JLabel("assignStatus:");
	private JTextField assignStatusField = new JTextField(10);
	
	private JLabel pageIndexLabel = new JLabel("pageIndex:");
	private JTextField pageIndexField = new JTextField(10);
	
	private JLabel pageSizeLabel = new JLabel("pageSize:");
	private JTextField pageSizeField = new JTextField(10);
	
	private JLabel cxTypeLabel = new JLabel("cxType:");
	private JTextField cxTypeField = new JTextField(10);
	
	private JLabel startNumLabel = new JLabel("startNum:");
	private JTextField startNumField = new JTextField(10);
	
	private JLabel endNumLabel = new JLabel("endNum:");
	private JTextField endNumField = new JTextField(10);
	
	private JLabel callSrcCodeLabel = new JLabel("callSrcCode:");
	private JTextField callSrcCodeField = new JTextField(10);
	
	private JLabel attendantFlagLabel = new JLabel("attendantFlag");
	private JTextField attendantFlagField = new JTextField(10);
	
	private JLabel oldStartNumLabel = new JLabel("oldStartNum:");
	private JTextField oldStartNumField = new JTextField(10);
	
	private JLabel oldEndNumLabel = new JLabel("oldEndNum:");
	private JTextField oldEndNumField = new JTextField(10);
	
	private JLabel newStartNumLabel = new JLabel("newStartNum:");
	private JTextField newStartNumField = new JTextField(10);
	
	private JLabel newEndNumLabel = new JLabel("newEndNum:");
	private JTextField newEndNumField = new JTextField(10);
	
	//分页查询系统号段按钮
	private JButton pageQuerySysnumBtn = new MyButton(Properties_language_Utils.
			getValue("admin.SysnumPanel.pageQuerySysnumBtn"),200,35);
	
	//增加系统号段按钮
	private JButton addSysnumBtn = new MyButton(Properties_language_Utils.
			getValue("admin.SysnumPanel.addSysnumBtn"),200,35);
	
	//修改系统号段按钮
	private JButton modifySysnumBtn = new MyButton(Properties_language_Utils.
			getValue("admin.SysnumPanel.modifySysnumBtn"),200,35);
	
	//删除系统号段按钮
	private JButton deleSysnumBtn = new MyButton(Properties_language_Utils.
			getValue("admin.SysnumPanel.deleSysnumBtn"),200,35);
	
	//查询系统号段按钮
	private JButton querySysnumBtn = new MyButton(Properties_language_Utils.
			getValue("admin.SysnumPanel.querySysnumBtn"),200,35);
	
	//发放和回收按钮
	private JButton issureAndRecoverBtn = new MyButton(Properties_language_Utils.
			getValue("admin.SysnumPanel.issureAndRecoverBtn"),200,35);
	
	
	private List<NumIndexDeleBean> numIndexDeleBeanLst = new ArrayList<>();
			
	
	public SysnumPanel() 
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
	    
	    buildPanel(panel, gridbag, c, new JComponent[] {searchKeyLabel}, 0, 1, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {searchKeyField}, 1, 1, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {assignStatusLabel}, 2, 1, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {assignStatusField}, 3, 1, 10, 20, 1, 1);
	    
	    buildPanel(panel, gridbag, c, new JComponent[] {pageIndexLabel}, 0, 2, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {pageIndexField}, 1, 2, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {pageSizeLabel}, 2, 2, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {pageSizeField}, 3, 2, 10, 20, 1, 1);
	    
	    buildPanel(panel, gridbag, c, new JComponent[] {startNumLabel}, 0, 3, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {startNumField}, 1, 3, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {endNumLabel}, 2, 3, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {endNumField}, 3, 3, 10, 20, 1, 1);
	    
	    buildPanel(panel, gridbag, c, new JComponent[] {callSrcCodeLabel}, 0, 4, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {callSrcCodeField}, 1, 4, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {attendantFlagLabel}, 2, 4, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {attendantFlagField}, 3, 4, 10, 20, 1, 1);
	    
	    buildPanel(panel, gridbag, c, new JComponent[] {oldStartNumLabel}, 0, 5, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {oldStartNumField}, 1, 5, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {oldEndNumLabel}, 2, 5, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {oldEndNumField}, 3, 5, 10, 20, 1, 1);
	    
	    buildPanel(panel, gridbag, c, new JComponent[] {newStartNumLabel}, 0, 6, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {newStartNumField}, 1, 6, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {newEndNumLabel}, 2, 6, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {newEndNumField}, 3, 6, 10, 20, 1, 1);
	    
	    buildPanel(panel, gridbag, c, new JComponent[] {cxTypeLabel}, 0, 7, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {cxTypeField}, 1, 7, 10, 20, 1, 1);
	    
	    buildPanel(panel, gridbag, c, new JComponent[] {pageQuerySysnumBtn}, 0, 8, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {addSysnumBtn}, 2, 8, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {modifySysnumBtn}, 0, 9, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {deleSysnumBtn}, 2, 9, 10, 20, 1, 1);
	    
	    buildPanel(panel, gridbag, c, new JComponent[] {querySysnumBtn}, 0, 10, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {issureAndRecoverBtn}, 2, 10, 10, 20, 1, 1);
	    issureAndRecoverBtn.addActionListener(this);
	    buildPanel(panel, gridbag, c, new JComponent[] {errInfoLabel}, 0, 11, 10, 5, 2, 1);
	    
	    //报文位置
	    //message location
	    buildPanel(panel, gridbag, c, new JComponent[] {new LogPanel()}, 4, 0, 10, 20, 4, 10);
	    
	    return panel;
	}
	
	private void init()
	{
		//分页查询系统号段按钮事件
		this.pageQuerySysnumBtn.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
	        {
				showErrInfoWithColor("");
	            
	            boolean flag = validateParams(
	            		new JTextField[] {pageIndexField, pageSizeField},
	            		new String[] {"pageIndex", "pageSize"});
	                
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
	            		pageQuerySysnum();
	            	}
	            };
	            Future<?> future = Executors.newSingleThreadExecutor().submit(runnable);
	            if(future.isDone()) 
	            {
	            	LOGGER.info("future.isDone() is true");
	            }
	        }
		});
		
		//增加系统号段按钮事件
		this.addSysnumBtn.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
	        {
				showErrInfoWithColor("");
	            
	            boolean flag = validateParams(
	            		new JTextField[] {startNumField, endNumField},
	            		new String[] {"startNum", "endNum"});
	                
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
	            		addSysnum();
	            	}
	            };
	            Future<?> future = Executors.newSingleThreadExecutor().submit(runnable);
	            if(future.isDone()) 
	            {
	            	LOGGER.info("future.isDone() is true");
	            }
	        }
		});
		
		//修改系统号段按钮事件
		this.modifySysnumBtn.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
	        {
				showErrInfoWithColor("");
	            
	            boolean flag = validateParams(
	            		new JTextField[] {oldStartNumField, oldEndNumField, newStartNumField, newEndNumField},
	            		new String[] {"oldStartNum", "oldEndNum", "newStartNum", "newEndNum"});
	                
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
	            		modifySysnum();
	            	}
	            };
	            Future<?> future = Executors.newSingleThreadExecutor().submit(runnable);
	            if(future.isDone()) 
	            {
	            	LOGGER.info("future.isDone() is true");
	            }
	        }
		});
		
		//删除系统号段按钮事件
		this.deleSysnumBtn.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
	        {
				showErrInfoWithColor("");
	            
				boolean flag = validateParams(
	            		new JTextField[] {startNumField, endNumField},
	            		new String[] {"startNum", "endNum"});
	                
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
	            		deleSysnum();
	            	}
	            };
	            Future<?> future = Executors.newSingleThreadExecutor().submit(runnable);
	            if(future.isDone()) 
	            {
	            	LOGGER.info("future.isDone() is true");
	            }
	        }
		});
		
		//查询系统号段按钮事件
		this.querySysnumBtn.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
	        {
				showErrInfoWithColor("");
	            
				boolean flag = validateParams(
	            		new JTextField[] {startNumField, endNumField},
	            		new String[] {"startNum", "endNum"});
	                
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
	            		querySysnum();
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
	 * 查询系统号段
	 */
	private void querySysnum() 
	{
		Token token = LoginUtils.getToken();
		
		RestRequest request = new RestRequest(HTTPConstant.HTTP_METHOD_GET);
		
		Map<String, String> parameters = request.getParameters();
		parameters.put("startNum", startNumField.getText());
		parameters.put("endNum", endNumField.getText());
		
		EcService.get("/sysnum", request, errInfoLabel, token);
		
		EcService.finish();
	}
	
	/**
	 * 删除系统号段
	 */
	private void deleSysnum() 
	{
		Token token = LoginUtils.getToken();
		
		RestRequest request = new RestRequest(HTTPConstant.HTTP_METHOD_POST);
		
		request.setPayload(addDeleSysnumBean());
		
		EcService.post("/sysnum/delete", request, errInfoLabel, token);
		
		numIndexDeleBeanLst.clear();
		
		EcService.finish();
	}
	
	/**
	 * 修改系统号段
	 */
	private void modifySysnum() 
	{
		Token token = LoginUtils.getToken();
		
		RestRequest request = new RestRequest(HTTPConstant.HTTP_METHOD_PUT);
		
		request.setPayload(addModifySysnumBean());
		
		EcService.put("/sysnum", request, errInfoLabel, token);
		
		EcService.finish();
	}
	
	/**
	 * 增加系统号段
	 */
	private void addSysnum()
	{
		Token token = LoginUtils.getToken();
		
		RestRequest request = new RestRequest(HTTPConstant.HTTP_METHOD_POST);
		
		request.setPayload(addSysnumBean());
		
		EcService.post("/sysnum", request, errInfoLabel, token);
		
		EcService.finish();
	}
	
	/**
	 * 分页查询系统号段
	 */
	private void pageQuerySysnum() 
	{
		Token token = LoginUtils.getToken();
		
		RestRequest request = new RestRequest(HTTPConstant.HTTP_METHOD_POST);
		
		request.setPayload(addPageQuerySysnumBean());
		
		EcService.post("/sysnum/search", request, errInfoLabel, token);
		
		EcService.finish();
	}
	
	private DeleSysnumBean addDeleSysnumBean()
	{
		DeleSysnumBean deleSysnumBean = new DeleSysnumBean();
		NumIndexDeleBean numIndexDeleBean = new NumIndexDeleBean();
		numIndexDeleBean.setStartNum(startNumField.getText());
		numIndexDeleBean.setEndNum(endNumField.getText());
		
		numIndexDeleBeanLst.add(numIndexDeleBean);
		deleSysnumBean.setNumIndexDeleBeanLst(numIndexDeleBeanLst);
		
		return deleSysnumBean;
	}
	
	/**
	 * 设置修改系统号段参数
	 * @return
	 */
	private ModifySysnumBean addModifySysnumBean()
	{
		ModifySysnumBean modifySysnumBean = new ModifySysnumBean();
		modifySysnumBean.setOldStartNum(oldStartNumField.getText());
		modifySysnumBean.setOldEndNum(oldEndNumField.getText());
		modifySysnumBean.setNewStartNum(newStartNumField.getText());
		modifySysnumBean.setNewEndNum(newEndNumField.getText());
		
		return modifySysnumBean;
	}
	
	/**
	 * 设置增加系统号段参数
	 * @return
	 */
	private AddSysnumBean addSysnumBean() 
	{
		AddSysnumBean addSysnumBean = new AddSysnumBean();
		
		addSysnumBean.setStartNum(startNumField.getText());
		addSysnumBean.setEndNum(endNumField.getText());
		if (StringUtils.isNotEmpty(cxTypeField.getText()))
		{
			addSysnumBean.setCxType(Integer.parseInt(cxTypeField.getText()));
		}
		if (StringUtils.isNotEmpty(attendantFlagField.getText()))
		{
			addSysnumBean.setAttendantFlag(Integer.parseInt(attendantFlagField.getText()));
		}
		
		return addSysnumBean;
	}
	
	/**
	 * 设置分页查询系统号段参数
	 * @return
	 */
	private PageQuerySysnumBean addPageQuerySysnumBean() 
	{
		PageQuerySysnumBean pageQuerySysnumBean = new PageQuerySysnumBean();
		
		pageQuerySysnumBean.setPageIndex(Integer.parseInt(pageIndexField.getText()));
		pageQuerySysnumBean.setPageSize(Integer.parseInt(pageSizeField.getText()));
		if (StringUtils.isNotEmpty(cxTypeField.getText()))
		{
			pageQuerySysnumBean.setCxType(Integer.parseInt(cxTypeField.getText()));
		}
		if (StringUtils.isNotEmpty(searchKeyField.getText()))
		{
			pageQuerySysnumBean.setSearchKey(searchKeyField.getText());
		}
		if (StringUtils.isNotEmpty(assignStatusField.getText()))
		{
			pageQuerySysnumBean.setAssignStatus(Integer.parseInt(assignStatusField.getText()));
		}
		
		return pageQuerySysnumBean;
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
		// Construct a new JFrame as a new window
		JFrame frame = new JFrame(Properties_language_Utils.getValue("admin.SysnumPanel.frameTitle"));
		
		setSize(1050, 680);
	    frame.setSize(1050, 680);
	    
	    JPanel center = new IssueAndRecoverPanel();
	    JScrollPane centerJPane = new JScrollPane(center);
	    
	    frame.getContentPane().add(centerJPane, BorderLayout.CENTER);
        frame.setResizable(true);
        frame.setVisible(true);

	}
}
