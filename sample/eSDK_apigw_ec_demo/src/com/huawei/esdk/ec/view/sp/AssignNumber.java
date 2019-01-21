package com.huawei.esdk.ec.view.sp;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
import com.huawei.esdk.ec.bean.enterprise.MMTelExtensionBean;
import com.huawei.esdk.ec.bean.sp.AssignNumberBean;
import com.huawei.esdk.ec.bean.sp.NumIndexBean;
import com.huawei.esdk.ec.bean.sp.NumberParamBean;
import com.huawei.esdk.ec.bean.sp.RightsEnum;
import com.huawei.esdk.ec.bean.sp.RightsStatusEnum;
import com.huawei.esdk.ec.constant.HTTPConstant;
import com.huawei.esdk.ec.service.EcService;
import com.huawei.esdk.ec.utils.LoginUtils;
import com.huawei.esdk.ec.utils.Properties_language_Utils;
import com.huawei.esdk.ec.utils.StringUtils;
import com.huawei.esdk.ec.view.common.LogPanel;
import com.huawei.esdk.ec.view.common.MyButton;

/**
 * 分配服务商号段给企业的视图
 * Assign a service provider segment to the company view
 * @author wwx534262
 *
 */
@SuppressWarnings("all")
public class AssignNumber extends JPanel
{

	private static final long serialVersionUID = 3509720201366774979L;
	
	private static final Logger LOGGER = LogManager.getLogger(AssignNumber.class);
	
	private JLabel errInfoLabel = new JLabel();
	
	private JLabel reqParams = new JLabel(Properties_language_Utils.getValue("reqParams"));
	
	//分配号码参数体（包含下面待分配号段信息，号码参数）
	//Assigned number parameter body (contains the following number information to be assigned, number parameter)
	private JLabel srcOrgIdLabel = new JLabel("srcOrgId:");
	private JTextField srcOrgIdField = new JTextField(10);
	
	private JLabel corpIdLabel = new JLabel("corpId:");
	private JTextField corpIdField = new JTextField(10);
	
	private JLabel operateUserLabel = new JLabel("operateUser:");
	private JTextField operateUserField = new JTextField(10);
	
	//待分配号段信息
	//To be allocated number segment information
	private JLabel startNumLabel = new JLabel("startNum:");
	private JTextField startNumField = new JTextField(10);
	
	private JLabel endNumLabel = new JLabel("endNum:");
	private JTextField endNumField = new JTextField(10);
	
	private JLabel isConfAccessNumberLabel = new JLabel("isConfAccessNumber:");
	private JTextField isConfAccessNumberField = new JTextField(10);
	
	//号码参数（包括以下呼叫权限，用户业务扩展业务数据）
	//Number parameters (including the following call rights, user service extension service data)
	private JLabel statusLabel = new JLabel("status:");
	private JTextField statusField = new JTextField(10);
	
	private JLabel odspModeLabel = new JLabel("odspMode:");
	private JTextField odspModeField = new JTextField(10);
	
	private JLabel outOcxPfxLabel = new JLabel("outOcxPfx:");
	private JTextField outOcxPfxField = new JTextField(10);
	
	private JLabel userPriorityLabel = new JLabel("userPriority:");
	private JTextField userPriorityField = new JTextField(10);
	
	//呼叫权限Map<RightsEnum,RightsStatusEnum>
	//Call permission Map<RightsEnum,RightsStatusEnum>
	private JLabel rightsEnumLabel = new JLabel("rightsEnum:");
	private JTextField rightsEnumField = new JTextField(10);
	
	private JLabel rightsStatusEnumLabel = new JLabel("rightsStatusEnum:");
	private JTextField rightsStatusEnumField = new JTextField(10);
	
	
	//用户业务扩展业务数据
	//User service extension service data
	private JLabel callSrcCodeLabel = new JLabel("callSrcCode:");
	private JTextField callSrcCodeField = new JTextField(10);
	
	private JLabel iupSubscribeLabel = new JLabel("iupSubscribe:");
	private JTextField iupSubscribeField = new JTextField(10);
	
	private JLabel tipInfoLabel = new JLabel(Properties_language_Utils.getValue("sp.AssignNumber.tipInfoLabel"));
	
	private JButton setCallPermissionBtn = new MyButton(Properties_language_Utils.
			getValue("sp.AssignNumber.setCallPermissionBtn"));
	
	private JButton assignBtn = new MyButton(Properties_language_Utils.getValue("sp.AssignNumber.assignBtn"));
	
	Map<String,String> callRights = new HashMap<>();
	
	public AssignNumber()
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
	    buildPanel(panel, gridbag, c, new JComponent[] {srcOrgIdLabel}, 0, 2, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {srcOrgIdField}, 1, 2, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {corpIdLabel}, 2, 2, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {corpIdField}, 3, 2, 10, 20, 1, 1);
	    
	    buildPanel(panel, gridbag, c, new JComponent[] {operateUserLabel}, 0, 3, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {operateUserField}, 1, 3, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {startNumLabel}, 2, 3, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {startNumField}, 3, 3, 10, 20, 1, 1);
	    
	    buildPanel(panel, gridbag, c, new JComponent[] {endNumLabel}, 0, 4, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {endNumField}, 1, 4, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {isConfAccessNumberLabel}, 2, 4, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {isConfAccessNumberField}, 3, 4, 10, 20, 1, 1);
	    
	    buildPanel(panel, gridbag, c, new JComponent[] {statusLabel}, 0, 5, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {statusField}, 1, 5, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {odspModeLabel}, 2, 5, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {odspModeField}, 3, 5, 10, 20, 1, 1);
	    
	    buildPanel(panel, gridbag, c, new JComponent[] {outOcxPfxLabel}, 0, 6, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {outOcxPfxField}, 1, 6, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {userPriorityLabel}, 2, 6, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {userPriorityField}, 3, 6, 10, 20, 1, 1);
	    
	    buildPanel(panel, gridbag, c, new JComponent[] {rightsEnumLabel}, 0, 7, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {rightsEnumField}, 1, 7, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {rightsStatusEnumLabel}, 2, 7, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {rightsStatusEnumField}, 3, 7, 10, 20, 1, 1);
	    
	    buildPanel(panel, gridbag, c, new JComponent[] {callSrcCodeLabel}, 0, 8, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {callSrcCodeField}, 1, 8, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {iupSubscribeLabel}, 2, 8, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {iupSubscribeField}, 3, 8, 10, 20, 1, 1);
	    
	    //按钮
	    //button
	    buildPanel(panel, gridbag, c, new JComponent[] {setCallPermissionBtn}, 0, 9, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {assignBtn}, 2, 9, 10, 20, 1, 1);
	    
	    buildPanel(panel, gridbag, c, new JComponent[] {errInfoLabel}, 0, 10, 10, 20, 4, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {tipInfoLabel}, 0, 11, 10, 20, 4, 1);
	    
	    //报文位置
		//message location
		buildPanel(panel, gridbag, c, new JComponent[] {new LogPanel()}, 4, 0, 10, 20, 4, 10);
	    
	    return panel;
	    
	}
	
	private void init() 
	{
		tipInfoLabel.setForeground(Color.blue);
		
		//点击设置权限呼叫按钮事件
		//Click Set Permission Call Button Event
		this.setCallPermissionBtn.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
	        {
				showErrInfoWithColor("");
	            
	            boolean flag = validateParams(
	            		new JTextField[] {rightsEnumField, rightsStatusEnumField},
	            		new String[] {"RightsEnum", "RightsStatusEnum"});
	                
	            if (!flag)
	            {
	            	return;
	            }
	            
	        	Runnable runnable = new Runnable()
				{
            		@Override
            		public void run()
            		{
            			callRights.put(rightsEnumField.getText(), 
            					rightsStatusEnumField.getText());
            			
            			showErrInfoWithColor(Properties_language_Utils.getValue("sp.AssignNumber.setTip"));
            		}
				};
				Future future = Executors.newSingleThreadExecutor().submit(runnable);
				if(future.isDone()) 
	            {
	            	LOGGER.info("future.isDone() is true");
	            }
	        }
		});
		
		//点击分配提供商号段给企业按钮事件
		//Click Assigning Provider Segment to Enterprise Button Event
		this.assignBtn.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
	        {
				showErrInfoWithColor("");
	            
	            boolean flag = validateParams(
	            		new JTextField[] {srcOrgIdField, corpIdField, startNumField, endNumField, odspModeField, outOcxPfxField, callSrcCodeField},
	            		new String[] {"srcOrgId", "corpId", "startNum", "endNum", "odspMode", "outOcxPfx", "callSrcCode"});
	                
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
            			assignNum();
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
	
	//分配服务商号段给企业
	//Assign a service provider segment to the company
	private void assignNum() 
	{
		try
		{
			Token token = LoginUtils.getToken();
			
			RestRequest request = new RestRequest(HTTPConstant.HTTP_METHOD_PUT);
			
			request.setPayload(addAssignNumberBean());
			
			EcService.put("/spnum/distribution", request, errInfoLabel, token);
			
			EcService.finish();
		} 
		catch (Exception e)
		{
			LOGGER.error("get Token error",e);
		}
	}
	
	//设置分配服务商号段给企业参数体
	//Set the allocation service number segment to the enterprise parameter body
	private AssignNumberBean addAssignNumberBean() 
	{
		AssignNumberBean assignNumberBean = new AssignNumberBean();
		assignNumberBean.setSrcOrgId(srcOrgIdField.getText());
		assignNumberBean.setCorpId(corpIdField.getText());
		assignNumberBean.setNumAssignList(addNumIndexBean());
		assignNumberBean.setNumParam(addNumberParamBean());
		if (StringUtils.isNotEmpty(operateUserField.getText())) 
		{
			assignNumberBean.setOperateUser(operateUserField.getText());
		}
		
		return assignNumberBean;
	}
	
	//设置待分配号段信息参数
	//Set the parameter number information to be assigned
	private List<NumIndexBean> addNumIndexBean()
	{
		List<NumIndexBean> numAssignList = new ArrayList<>();
		NumIndexBean numIndexBean = new NumIndexBean();
		numIndexBean.setStartNum(startNumField.getText());
		numIndexBean.setEndNum(endNumField.getText());
		
		if (StringUtils.isNotEmpty(isConfAccessNumberField.getText()))
		{
			numIndexBean.setIsConfAccessNumber(Integer.parseInt(isConfAccessNumberField.getText()));
		}
		
		numAssignList.add(numIndexBean);
		
		return numAssignList;
	}
	
	//设置号码参数
	//Set number parameters
	private NumberParamBean addNumberParamBean() 
	{
		NumberParamBean numberParamBean = new NumberParamBean();
		
		if (StringUtils.isNotEmpty(statusField.getText())) 
		{
			numberParamBean.setStatus(statusField.getText());
		}
		numberParamBean.setOdspMode(odspModeField.getText());
		numberParamBean.setOutOcxPfx(outOcxPfxField.getText());
		if (StringUtils.isNotEmpty(userPriorityField.getText())) 
		{
			numberParamBean.setUserPriority(userPriorityField.getText());
		}
		
		numberParamBean.setCallRights(callRights);
		
		numberParamBean.setMmTelExtension(addMMTelExtensionBean());
		
		return numberParamBean;
	}
	
	//设置用户扩展业务数据
	//Set up user expansion business data
	private MMTelExtensionBean addMMTelExtensionBean() 
	{
		MMTelExtensionBean mMTelExtensionBean = new MMTelExtensionBean();
		mMTelExtensionBean.setCallSrcCode(Integer.parseInt(callSrcCodeField.getText()));
		if (StringUtils.isNotEmpty(iupSubscribeField.getText())) 
		{
			mMTelExtensionBean.setIupSubscribe(Boolean.parseBoolean(iupSubscribeField.getText()));
		}
		
		return mMTelExtensionBean;
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

}
