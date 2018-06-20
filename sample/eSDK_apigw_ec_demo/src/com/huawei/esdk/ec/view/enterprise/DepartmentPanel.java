package com.huawei.esdk.ec.view.enterprise;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;
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
import com.huawei.esdk.ec.bean.enterprise.DeptInfo;
import com.huawei.esdk.ec.constant.HTTPConstant;
import com.huawei.esdk.ec.service.EcService;
import com.huawei.esdk.ec.utils.LoginUtils;
import com.huawei.esdk.ec.utils.Properties_language_Utils;
import com.huawei.esdk.ec.utils.StringUtils;
import com.huawei.esdk.ec.view.common.LogPanel;
import com.huawei.esdk.ec.view.common.MyButton;
/**
 * 企业部门管理视图
 * Enterprise department management view
 * @author wwx534262
 *
 */
@SuppressWarnings("all")
public class DepartmentPanel extends JPanel
{
	private static final Logger LOGGER = LogManager.getLogger(DepartmentPanel.class);
	
    private static final long serialVersionUID = -8285213367222038440L;
    
    private JLabel errInfoLabel = new JLabel();
    
    private JLabel requestLabel = new JLabel(Properties_language_Utils.getValue("reqParams"));
    
    private JLabel corpIdLabel = new JLabel("corpId:");
    private JTextField corpIdField = new JTextField(10);
    
    private JLabel departmentCodeLabel = new JLabel("departmentCode:");
    private JTextField departmentCodeField = new JTextField(10);
    
    private JLabel withSubDepartmentLabel = new JLabel("withSubDepartment:");
    private JTextField withSubDepartmentField = new JTextField(10);
    
    private JLabel deptCodeLabel = new JLabel("deptCode:");
    private JTextField deptCodeField = new JTextField(10);
    
    private JLabel deptNameLabel = new JLabel("deptName:");
    private JTextField deptNameField = new JTextField(10);
    
    private JLabel parentDeptCodeLabel = new JLabel("parentDeptCode:");
    private JTextField parentDeptCodeField = new JTextField(10);
    
    private JLabel mgrNameLabel = new JLabel("mgrName:");
    private JTextField mgrNameField = new JTextField(10);
    
    private JLabel mgrCodeLabel = new JLabel("mgrCode:");
    private JTextField mgrCodeField = new JTextField(10);
    
    private JLabel secNameLabel = new JLabel("secName:");
    private JTextField secNameField = new JTextField(10);
    
    private JLabel secCodeLabel = new JLabel("secCode:");
    private JTextField secCodeField = new JTextField(10);
    
    private JButton adddpBtn = new MyButton(Properties_language_Utils.
    		getValue("enter.DepartmentPanel.adddpBtn"),120,35);
    
    private JButton modifyBtn = new MyButton(Properties_language_Utils.
    		getValue("enter.DepartmentPanel.modifyBtn"),120,35);
    
    private JButton deletedpBtn = new MyButton(Properties_language_Utils.
    		getValue("enter.DepartmentPanel.deletedpBtn"),120,35);
    
    private JButton serchdpBtn = new MyButton(Properties_language_Utils
    		.getValue("enter.DepartmentPanel.serchdpBtn"),120,35);
    
    
    public DepartmentPanel()
    {
        add(getPanels());
        init();
    }
    
    private JPanel getPanels()
    {
        
        GridBagLayout gridbag = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        JPanel panel = new JPanel(gridbag);
        
        buildPanel(panel, gridbag, c, new JComponent[] {requestLabel}, 0, 0, 10, 40, 1, 1);
        buildPanel(panel, gridbag, c, new JComponent[] {corpIdLabel}, 0, 2, 10, 20, 1, 1);
        buildPanel(panel, gridbag, c, new JComponent[] {corpIdField}, 1, 2, 10, 20, 2, 1);
        buildPanel(panel, gridbag, c, new JComponent[] {departmentCodeLabel}, 2, 2, 10, 20, 1, 1);
        buildPanel(panel, gridbag, c, new JComponent[] {departmentCodeField}, 3, 2, 10, 20, 1, 1);
        buildPanel(panel, gridbag, c, new JComponent[] {withSubDepartmentLabel}, 0, 3, 10, 20, 1, 1);
        buildPanel(panel, gridbag, c, new JComponent[] {withSubDepartmentField}, 1, 3, 10, 20, 1, 1);
        buildPanel(panel, gridbag, c, new JComponent[] {deptCodeLabel}, 2, 3, 10, 20, 1, 1);
        buildPanel(panel, gridbag, c, new JComponent[] {deptCodeField}, 3, 3, 10, 20, 1, 1);
        buildPanel(panel, gridbag, c, new JComponent[] {deptNameLabel}, 0, 4, 10, 20, 1, 1);
        buildPanel(panel, gridbag, c, new JComponent[] {deptNameField}, 1, 4, 10, 20, 1, 1);
        buildPanel(panel, gridbag, c, new JComponent[] {parentDeptCodeLabel}, 2, 4, 10, 20, 1, 1);
        buildPanel(panel, gridbag, c, new JComponent[] {parentDeptCodeField}, 3, 4, 10, 20, 1, 1);
        buildPanel(panel, gridbag, c, new JComponent[] {mgrNameLabel}, 0, 5, 10, 20, 1, 1);
        buildPanel(panel, gridbag, c, new JComponent[] {mgrNameField}, 1, 5, 10, 20, 1, 1);
        buildPanel(panel, gridbag, c, new JComponent[] {mgrCodeLabel}, 2, 5, 10, 20, 1, 1);
        buildPanel(panel, gridbag, c, new JComponent[] {mgrCodeField}, 3, 5, 10, 20, 1, 1);
        buildPanel(panel, gridbag, c, new JComponent[] {secNameLabel}, 0, 6, 10, 20, 1, 1);
        buildPanel(panel, gridbag, c, new JComponent[] {secNameField}, 1, 6, 10, 20, 1, 1);
        buildPanel(panel, gridbag, c, new JComponent[] {secCodeLabel}, 2, 6, 10, 20, 1, 1);
        buildPanel(panel, gridbag, c, new JComponent[] {secCodeField}, 3, 6, 10, 20, 1, 1);
        
        buildPanel(panel, gridbag, c, new JComponent[] {adddpBtn}, 0, 7, 100, 30, 2, 1);
        buildPanel(panel, gridbag, c, new JComponent[] {modifyBtn}, 2, 7, 100, 30, 2, 1);
        
        buildPanel(panel, gridbag, c, new JComponent[] {deletedpBtn}, 0, 8, 100, 30, 2, 1);
        buildPanel(panel, gridbag, c, new JComponent[] {serchdpBtn}, 2, 8, 100, 30, 2, 1);
        
        buildPanel(panel, gridbag, c, new JComponent[] {errInfoLabel}, 0, 9, 100, 5, 4, 1);
        
	    //报文位置
		//message location
		buildPanel(panel, gridbag, c, new JComponent[] {new LogPanel()}, 4, 0, 10, 20, 4, 10);
        
        return panel;
        
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
    
    private void init()
    {

    	//增加企业部门按钮
    	//Increase company department button event
        this.adddpBtn.addMouseListener(new MouseAdapter()
        {
            public void mouseClicked(MouseEvent e)
            {
                showErrInfoWithColor("");
                
                boolean flag = validateParams(
                    new JTextField[] {corpIdField, deptCodeField, deptNameField, parentDeptCodeField},
                    new String[] {"corpId", "deptCode", "deptName", "parentDeptCode"});
                    
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
	            		addDept();
	            	}
	            };
	            Future future = Executors.newSingleThreadExecutor().submit(runnable);
		        if(!future.isDone()) {
		            LOGGER.error("addMouseListener fail");
		        }
            }
        });
        
        // 修改部门，发起PUT请求
        // Modify department to initiate PUT request
        this.modifyBtn.addMouseListener(new MouseAdapter()
        {
            public void mouseClicked(MouseEvent e)
            {
                showErrInfoWithColor("");
                
                boolean flag = validateParams(
                        new JTextField[] {corpIdField, deptCodeField, deptNameField},
                        new String[] {"corpId", "deptCode", "deptName"});
                        
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
	            		modifyDept();
	            	}
	            };
	            Future future = Executors.newSingleThreadExecutor().submit(runnable);
		        if(!future.isDone()) {
		            LOGGER.error("addMouseListener fail");
		        }
            }
        });
        
        // 删除部门，发起DELET请求
        // Delete department, initiate DELET request
        this.deletedpBtn.addMouseListener(new MouseAdapter()
        {
            public void mouseClicked(MouseEvent e)
            {
                showErrInfoWithColor("");
                
                boolean flag = validateParams(
                        new JTextField[] {corpIdField, departmentCodeField},
                        new String[] {"corpId", "departmentCode"});
                    
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
	            		deleteDept();
	            	}
	            };
	            Future future = Executors.newSingleThreadExecutor().submit(runnable);
		        if(!future.isDone()) {
		            LOGGER.error("addMouseListener fail");
		        }
            }
        });
        // 查询部门，发起GET请求
        // Query department, initiate GET request
        this.serchdpBtn.addMouseListener(new MouseAdapter()
        {
            public void mouseClicked(MouseEvent e)
            {
                showErrInfoWithColor("");
                
                boolean flag = validateParams(
                        new JTextField[] {corpIdField},
                        new String[] {"corpId"});
                    
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
	            		serchDept();
	            	}
	            };
	            Future future = Executors.newSingleThreadExecutor().submit(runnable);
		        if(!future.isDone()) {
		            LOGGER.error("addMouseListener fail");
		        }
            }
        });
    }
    
    // 添加部门
    // Add department
    private void addDept()
    {
        // 拼装请求参数，发送请求
    	// Assembly request parameters, send request
        DeptInfo payload = new DeptInfo();
        payload.setDeptCode(deptCodeField.getText());
        payload.setDeptName(deptNameField.getText());
        payload.setParentDeptCode(parentDeptCodeField.getText());
        
        if (StringUtils.isNotEmpty(mgrNameField.getText())) 
        {
        	payload.setMgrCode(mgrNameField.getText());
		}
        if (StringUtils.isNotEmpty(mgrCodeField.getText())) 
        {
        	payload.setMgrName(mgrCodeField.getText());
        }
        if (StringUtils.isNotEmpty(secNameField.getText())) 
        {
        	payload.setSecName(secNameField.getText());
        }
        if (StringUtils.isNotEmpty(secCodeField.getText())) 
        {
        	 payload.setSecCode(secCodeField.getText());
        }
        
        RestRequest request = new RestRequest(HTTPConstant.HTTP_METHOD_POST);
        request.setPayload(payload);
        try
        {
        	Token token = LoginUtils.getToken();
        	EcService.post("/corp/" + corpIdField.getText() + "/department", 
        			request, errInfoLabel, token);
        	
        }
        catch (Exception e)
        {
            showErrInfoWithColor(Properties_language_Utils.getValue("resultTip"));
            LOGGER.error("get Token error:" , e);
        }finally 
        {
        	EcService.finish();
        }
    }
    
    // 修改部门
    // modify department
    private void modifyDept()
    {
        // 拼装请求参数，发送请求
    	// Assembly request parameters, send request
        DeptInfo payload = new DeptInfo();
        payload.setDeptCode(deptCodeField.getText());
        payload.setDeptName(deptNameField.getText());
        if (StringUtils.isNotEmpty(mgrNameField.getText())) 
        {
        	payload.setMgrCode(mgrNameField.getText());
		}
        if (StringUtils.isNotEmpty(mgrCodeField.getText())) 
        {
        	payload.setMgrName(mgrCodeField.getText());
        }
        if (StringUtils.isNotEmpty(secNameField.getText())) 
        {
        	payload.setSecName(secNameField.getText());
        }
        if (StringUtils.isNotEmpty(secCodeField.getText())) 
        {
        	 payload.setSecCode(secCodeField.getText());
        }
        RestRequest request = new RestRequest(HTTPConstant.HTTP_METHOD_PUT);
        request.setPayload(payload);
        try
        {
        	Token token = LoginUtils.getToken();
        	EcService.put("/corp/" + corpIdField.getText() + "/department", 
        			request, errInfoLabel, token);
        	
			EcService.finish();
        }
        catch (Exception e)
        {
            showErrInfoWithColor(Properties_language_Utils.getValue("resultTip"));
            LOGGER.error("get Token error:" + e);
        }
        
    }
    
    // 查询部门
    // query department
    private void serchDept()
    {
        // 拼装请求参数，发送请求
    	// Assembly request parameters, send request
        Map<String, String> params = new HashMap<String, String>();
        
        RestRequest request = new RestRequest(HTTPConstant.HTTP_METHOD_GET);
        
        if (StringUtils.isNotEmpty(departmentCodeField.getText()))
        {
        	params.put("departmentCode", departmentCodeField.getText());
		}
        if (StringUtils.isNotEmpty(withSubDepartmentField.getText()))
        {
        	params.put("withSubDepartment", withSubDepartmentField.getText());
		}
        request.setParameters(params);
        try
        {
        	Token token = LoginUtils.getToken();
        	
        	EcService.get("/corp/" + corpIdField.getText() + "/department", 
        			request, errInfoLabel, token);
        	
			EcService.finish();
        }
        catch (Exception e)
        {
            showErrInfoWithColor(Properties_language_Utils.getValue("resultTip"));
            LOGGER.error("get Token error:" + e);
        }
    }
    
    // 删除部门
    // delete department
    private void deleteDept()
    {
        try
        {
        	Token token = LoginUtils.getToken();
        	
        	RestRequest request = new RestRequest(HTTPConstant.HTTP_METHOD_DELETE);
        	EcService.delete("/corp/" + corpIdField.getText() + "/department/" + departmentCodeField.getText(), 
        			request, errInfoLabel, token);
        	
        	EcService.finish();
        }
        catch (Exception e)
        {
            showErrInfoWithColor(Properties_language_Utils.getValue("resultTip"));
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
    
}
