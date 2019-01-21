package com.huawei.esdk.ec.view.admin;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
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

import com.huawei.esdk.ec.bean.admin.NumIndexIssueCorpBean;
import com.huawei.esdk.ec.bean.admin.NumIndexIssueSpBean;
import com.huawei.esdk.ec.bean.admin.RecyleSysnumFromCorpBean;
import com.huawei.esdk.ec.bean.admin.RecyleSysnumFromSpBean;
import com.huawei.esdk.ec.bean.admin.SysnumToCorpBean;
import com.huawei.esdk.ec.bean.admin.SysnumToSpBean;
import com.huawei.esdk.ec.bean.common.RestRequest;
import com.huawei.esdk.ec.bean.common.Token;
import com.huawei.esdk.ec.bean.enterprise.MMTelExtensionBean;
import com.huawei.esdk.ec.bean.sp.NumberParamBean;
import com.huawei.esdk.ec.constant.HTTPConstant;
import com.huawei.esdk.ec.service.EcService;
import com.huawei.esdk.ec.utils.LoginUtils;
import com.huawei.esdk.ec.utils.Properties_language_Utils;
import com.huawei.esdk.ec.utils.StringUtils;
import com.huawei.esdk.ec.view.common.LogPanel;
import com.huawei.esdk.ec.view.common.MyButton;
/**
 * 系统号段发放和回收接口视图
 * @author wWX534262
 *
 */
public class IssueAndRecoverPanel extends JPanel
{
	private static final long serialVersionUID = -4351253161196058779L;
	
	private static final Logger LOGGER = LogManager.getLogger(IssueAndRecoverPanel.class);
	
	private JLabel errInfoLabel = new JLabel();
	
	private JLabel tipInfoLabel = new JLabel(Properties_language_Utils.getValue("admin.IssueAndRecoverPanel.tip"));
	
	private JLabel reqParams = new JLabel(Properties_language_Utils.getValue("reqParams"));
	
	private JLabel spIdLabel = new JLabel("spId:");
	private JTextField spIdField = new JTextField(10);
	
	private JLabel startNumberLabel = new JLabel("startNumber:");
	private JTextField startNumberField = new JTextField(10);
	
	private JLabel endNumberLabel = new JLabel("endNumber:");
	private JTextField endNumberField = new JTextField(10);
	
	private JLabel cxTypeLabel = new JLabel("cxType:");
	private JTextField cxTypeField = new JTextField(10);
	
	private JLabel attendantFlagLabel = new JLabel("attendantFlag:");
	private JTextField attendantFlagField = new JTextField(10);
	
	private JLabel corpIdLabel = new JLabel("corpId:");
	private JTextField corpIdField = new JTextField(10);
	
	private JLabel startNumLabel = new JLabel("startNum:");
	private JTextField startNumField = new JTextField(10);
	
	private JLabel endNumLabel = new JLabel("endNum:");
	private JTextField endNumField = new JTextField(10);
	
	private JLabel isConfAccessNumberLabel = new JLabel("isConfAccessNumber:");
	private JTextField isConfAccessNumberField = new JTextField(10);
	
	//号码参数
	private JLabel odspModeLabel = new JLabel("odspMode:");
	private JTextField odspModeField = new JTextField(10);
	
	private JLabel outOcxPfxLabel = new JLabel("outOcxPfx:");
	private JTextField outOcxPfxField = new JTextField(10);
	
	//号码参数中的呼叫权限
	private JLabel rightsEnumLabel = new JLabel("RightsEnum:");
	private JTextField rightsEnumField = new JTextField(10);
	
	private JLabel rightsStatusEnumLabel = new JLabel("RightsStatusEnum:");
	private JTextField rightsStatusEnumField = new JTextField(10);
	
	//号码参数中的用户扩展业务数据
	private JLabel callSrcCodeLabel = new JLabel("callSrcCode:");
	private JTextField callSrcCodeField = new JTextField(10);
	
	private JLabel iupSubscribeLabel = new JLabel("iupSubscribe:");
	private JTextField iupSubscribeField = new JTextField(10);
	
	private JLabel numberListLabel = new JLabel("numberList:");
	private JTextField numberListField = new JTextField(10);
	
	private JLabel srcCorpIdLabel = new JLabel("srcCorpId:");
	private JTextField srcCorpIdField = new JTextField(10);
	
	//分配号段给服务提供商按钮
	private JButton assignNumToSpBtn = new MyButton(Properties_language_Utils.
			getValue("admin.IssueAndRecoverPanel.assignNumToSpBtn"),200,35);
	
	//分配号段给企业按钮
	private JButton assignNumToCorpBtn = new MyButton(Properties_language_Utils.
			getValue("admin.IssueAndRecoverPanel.assignNumToCorpBtn"),200,35);
	
	//从提供商回收系统号段按钮
	private JButton recoverNumfromSpBtn = new MyButton(Properties_language_Utils.
			getValue("admin.IssueAndRecoverPanel.recoverNumfromSpBtn"),200,35);
	
	//从企业回收系统号段按钮
	private JButton recoverNumfromCorpBtn = new MyButton(Properties_language_Utils.
			getValue("admin.IssueAndRecoverPanel.recoverNumfromCorpBtn"),200,35);
	
	//设置呼叫权限按钮
	private JButton setCallPermissionBtn = new MyButton(Properties_language_Utils.
			getValue("sp.AssignNumber.setCallPermissionBtn"),200,35);
	
	private Map<String,String> callRights = new HashMap<>();
	
	//待分配号段给提供商信息
	private List<NumIndexIssueSpBean> numIndexIssueBeanLst = new ArrayList<NumIndexIssueSpBean>();
	
	//待分配号段给企业信息
	private List<NumIndexIssueCorpBean> numIndexIssueCorpBeanLst = new ArrayList<NumIndexIssueCorpBean>();
	
	//标记设置呼叫权限的次数
	private int i = 0;
	
	public IssueAndRecoverPanel() 
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
	    
	    buildPanel(panel, gridbag, c, new JComponent[] {spIdLabel}, 0, 1, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {spIdField}, 1, 1, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {startNumberLabel}, 2, 1, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {startNumberField}, 3, 1, 10, 20, 1, 1);
	    
	    buildPanel(panel, gridbag, c, new JComponent[] {endNumberLabel}, 0, 2, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {endNumberField}, 1, 2, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {cxTypeLabel}, 2, 2, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {cxTypeField}, 3, 2, 10, 20, 1, 1);
	    
	    buildPanel(panel, gridbag, c, new JComponent[] {callSrcCodeLabel}, 0, 3, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {callSrcCodeField}, 1, 3, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {attendantFlagLabel}, 2, 3, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {attendantFlagField}, 3, 3, 10, 20, 1, 1);
	    
	    buildPanel(panel, gridbag, c, new JComponent[] {corpIdLabel}, 0, 4, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {corpIdField}, 1, 4, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {startNumLabel}, 2, 4, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {startNumField}, 3, 4, 10, 20, 1, 1);
	    
	    buildPanel(panel, gridbag, c, new JComponent[] {endNumLabel}, 0, 5, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {endNumField}, 1, 5, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {isConfAccessNumberLabel}, 2, 5, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {isConfAccessNumberField}, 3, 5, 10, 20, 1, 1);
	    
	    buildPanel(panel, gridbag, c, new JComponent[] {odspModeLabel}, 0, 6, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {odspModeField}, 1, 6, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {outOcxPfxLabel}, 2, 6, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {outOcxPfxField}, 3, 6, 10, 20, 1, 1);
	    
	    buildPanel(panel, gridbag, c, new JComponent[] {rightsEnumLabel}, 0, 7, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {rightsEnumField}, 1, 7, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {rightsStatusEnumLabel}, 2, 7, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {rightsStatusEnumField}, 3, 7, 10, 20, 1, 1);
	    
	    buildPanel(panel, gridbag, c, new JComponent[] {iupSubscribeLabel}, 0, 8, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {iupSubscribeField}, 1, 8, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {numberListLabel}, 2, 8, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {numberListField}, 3, 8, 10, 20, 1, 1);
	    
	    buildPanel(panel, gridbag, c, new JComponent[] {srcCorpIdLabel}, 0, 9, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {srcCorpIdField}, 1, 9, 10, 20, 1, 1);
	    
	    buildPanel(panel, gridbag, c, new JComponent[] {setCallPermissionBtn}, 0, 10, 10, 20, 2, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {assignNumToSpBtn}, 2, 10, 10, 20, 2, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {assignNumToCorpBtn}, 0, 11, 10, 20, 2, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {recoverNumfromSpBtn}, 2, 11, 10, 20, 2, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {recoverNumfromCorpBtn}, 0, 13, 10, 20, 2, 1);
	    
	    buildPanel(panel, gridbag, c, new JComponent[] {errInfoLabel}, 0, 14, 10, 5, 2, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {tipInfoLabel}, 0, 15, 10, 5, 4, 1);
	    
	    //报文位置
	    //message location
	    buildPanel(panel, gridbag, c, new JComponent[] {new LogPanel()}, 4, 0, 10, 20, 4, 10);
	    
	    return panel;
	    
	}
	
	private void init()
	{
		tipInfoLabel.setForeground(Color.BLUE);
		
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
            			i++;
            			showErrInfoWithColor(Properties_language_Utils.getValue("admin.IssueAndRecoverPanel.setTip")+ i);
            		}
				};
				Future<?> future = Executors.newSingleThreadExecutor().submit(runnable);
				if(future.isDone()) 
	            {
	            	LOGGER.info("future.isDone() is true");
	            }
	        }
		});
		//分配系统号段给服务提供商
		this.assignNumToSpBtn.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
	        {
				showErrInfoWithColor("");
	            
	            boolean flag = validateParams(
	            		new JTextField[] {startNumField, endNumField, spIdField},
	            		new String[] {"startNum", "endNum", "spId"});
	                
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
	            		assignNumToSp();
	            	}
	            };
	            Future<?> future = Executors.newSingleThreadExecutor().submit(runnable);
	            if(future.isDone()) 
	            {
	            	LOGGER.info("future.isDone() is true");
	            }
	        }
		});
		
		//分配系统号段给企业
		this.assignNumToCorpBtn.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
	        {
				showErrInfoWithColor("");
	            
	            boolean flag = validateParams(
	            		new JTextField[] {corpIdField, startNumField, endNumField, odspModeField, outOcxPfxField, callSrcCodeField},
	            		new String[] {"corpId", "startNum", "endNum", "odspMode", "outOcxPfx", "callSrcCode"});
	                
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
	            		assignNumToCorp();
	            	}
	            };
	            Future<?> future = Executors.newSingleThreadExecutor().submit(runnable);
	            if(future.isDone()) 
	            {
	            	LOGGER.info("future.isDone() is true");
	            }
	        }
		});
		
		//从提供商回收系统号段
		this.recoverNumfromSpBtn.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
	        {
				showErrInfoWithColor("");
	            
	            boolean flag = validateParams(
	            		new JTextField[] {spIdField, startNumField, endNumField},
	            		new String[] {"spId", "startNum", "endNum"});
	                
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
	            		recoverNumfromSp();
	            	}
	            };
	            Future<?> future = Executors.newSingleThreadExecutor().submit(runnable);
	            if(future.isDone()) 
	            {
	            	LOGGER.info("future.isDone() is true");
	            }
	        }
		});
		
		//从企业回收系统号段
		this.recoverNumfromCorpBtn.addMouseListener(new MouseAdapter()
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
	            		recoverNumfromCorp();
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
	 * 从企业回收系统号段
	 */
	private void recoverNumfromCorp() 
	{
		Token token = LoginUtils.getToken();
		
		RestRequest request = new RestRequest(HTTPConstant.HTTP_METHOD_POST);
		
		request.setPayload(addRecyleSysnumFromCorpBean());
		
		EcService.post("/sysnum/recyclefromcorp", request, errInfoLabel, token);
		
		EcService.finish();
	}
	
	/**
	 * 从服务提供商回收系统号段
	 */
	private void recoverNumfromSp() 
	{
		Token token = LoginUtils.getToken();
		
		RestRequest request = new RestRequest(HTTPConstant.HTTP_METHOD_POST);
		
		request.setPayload(addRecyleSysnumFromSpBean());
		
		EcService.post("/sysnum/recyclefromsp", request, errInfoLabel, token);
		
		EcService.finish();
	}
	
	/**
	 * 分配系统号段给企业
	 */
	private void assignNumToCorp() 
	{
		Token token = LoginUtils.getToken();
		
		RestRequest request = new RestRequest(HTTPConstant.HTTP_METHOD_POST);
		
		request.setPayload(addSysnumToCorpBean());
		
		EcService.post("/sysnum/assigntocorp", request, errInfoLabel, token);
		
		numIndexIssueCorpBeanLst.clear();
		
		EcService.finish();
	}
	
	/**
	 * 分配系统号段给服务提供商
	 */
	private void assignNumToSp() 
	{
		Token token = LoginUtils.getToken();
		
		RestRequest request = new RestRequest(HTTPConstant.HTTP_METHOD_POST);
		
		request.setPayload(addSysnumToSpBean());
		
		EcService.post("/sysnum/assigntosp", request, errInfoLabel, token);
		
		numIndexIssueBeanLst.clear();
		
		EcService.finish();
	}
	
	/**
	 * 设置系统号段分配给提供商的参数
	 * @return
	 */
	private SysnumToSpBean addSysnumToSpBean() 
	{
		SysnumToSpBean sysnumToSpBean =  new SysnumToSpBean();
		
		NumIndexIssueSpBean numIndexIssueBean = new NumIndexIssueSpBean();
		numIndexIssueBean.setStartNum(startNumField.getText());
		numIndexIssueBean.setEndNum(endNumField.getText());
		if (StringUtils.isNotEmpty(cxTypeField.getText())) 
		{
			numIndexIssueBean.setCxType(Integer.parseInt(cxTypeField.getText()));
		}
		if (StringUtils.isNotEmpty(attendantFlagField.getText())) 
		{
			numIndexIssueBean.setAttendantFlag(Integer.parseInt(attendantFlagField.getText()));
		}
		
		numIndexIssueBeanLst.add(numIndexIssueBean);
		
		sysnumToSpBean.setNumAssignList(numIndexIssueBeanLst);
		sysnumToSpBean.setSpId(spIdField.getText());
		
		return sysnumToSpBean;
	}
	
	/**
	 * 设置待分配号段给企业参数
	 * @return
	 */
	private SysnumToCorpBean addSysnumToCorpBean() 
	{
		SysnumToCorpBean sysnumToCorpBean = new SysnumToCorpBean();
		
		NumIndexIssueCorpBean numIndexIssueCorpBean = new NumIndexIssueCorpBean();
		numIndexIssueCorpBean.setStartNum(startNumField.getText());
		numIndexIssueCorpBean.setEndNum(endNumField.getText());
		if (StringUtils.isNotEmpty(cxTypeField.getText())) 
		{
			numIndexIssueCorpBean.setCxType(Integer.parseInt(cxTypeField.getText()));
		}
		if (StringUtils.isNotEmpty(isConfAccessNumberField.getText()))
		{
			numIndexIssueCorpBean.setIsConfAccessNumber(Integer.parseInt(isConfAccessNumberField.getText()));
		}
		if (StringUtils.isNotEmpty(attendantFlagField.getText())) 
		{
			numIndexIssueCorpBean.setAttendantFlag(Integer.parseInt(attendantFlagField.getText()));
		}
	
		numIndexIssueCorpBeanLst.add(numIndexIssueCorpBean);
		
		//设置号段信息
		sysnumToCorpBean.setNumAssignList(numIndexIssueCorpBeanLst);
		//设置企业编码
		sysnumToCorpBean.setCorpId(corpIdField.getText());
		//设置号码参数
		sysnumToCorpBean.setNumParam(addNumberParamBean());
		
		return sysnumToCorpBean;
	
	}
	
	/**
	 * 设置号码参数
	 * @return
	 */
	private NumberParamBean addNumberParamBean() 
	{
		NumberParamBean numberParamBean = new NumberParamBean();
		
		numberParamBean.setOdspMode(odspModeField.getText());
		numberParamBean.setOutOcxPfx(outOcxPfxField.getText());
		
		numberParamBean.setCallRights(callRights);
		
		numberParamBean.setMmTelExtension(addMMTelExtensionBean());
		
		return numberParamBean;
	}
	
	
	/**
	 * 设置号码参数中的用户扩展业务数据信息
	 * @return
	 */
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
	
	/**
	 * 设置从服务提供商回收系统号段参数
	 * @return
	 */
	private RecyleSysnumFromSpBean addRecyleSysnumFromSpBean() 
	{
		RecyleSysnumFromSpBean recyleSysnumFromSpBean = new RecyleSysnumFromSpBean();
		
		recyleSysnumFromSpBean.setStartNum(startNumField.getText());
		recyleSysnumFromSpBean.setEndNum(endNumField.getText());
		recyleSysnumFromSpBean.setSpId(spIdField.getText());
		
		return recyleSysnumFromSpBean;
	}
	
	/**
	 * 设置从企业回收系统号段参数
	 * @return
	 */
	private RecyleSysnumFromCorpBean addRecyleSysnumFromCorpBean() 
	{
		RecyleSysnumFromCorpBean recyleSysnumFromCorpBean = new RecyleSysnumFromCorpBean();
		//numberList不区分中英文分割
		String regex = ",|，";
		String[] nums = numberListField.getText().split(regex);
		List<String> numLst = Arrays.asList(nums);
		
		recyleSysnumFromCorpBean.setNumberList(numLst);
		recyleSysnumFromCorpBean.setSrcCorpId(srcCorpIdField.getText());
		
		return recyleSysnumFromCorpBean;
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
