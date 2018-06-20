package com.huawei.esdk.ec.view.sp;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import com.huawei.esdk.ec.bean.sp.ConditionRecoveryBean;
import com.huawei.esdk.ec.constant.HTTPConstant;
import com.huawei.esdk.ec.service.EcService;
import com.huawei.esdk.ec.utils.LoginUtils;
import com.huawei.esdk.ec.utils.Properties_language_Utils;
import com.huawei.esdk.ec.utils.StringUtils;
import com.huawei.esdk.ec.view.common.LogPanel;
import com.huawei.esdk.ec.view.common.MyButton;

/**
 * 服务提供商号段管理接口/Service Provider Segment Management Interface
 * 从企业按条件收回服务提供商号段视图
 * Recovery of service provider number segment view from enterprise condition
 * @author wWX534262
 *
 */
@SuppressWarnings("all")
public class ConditionRecoveryPanel extends JPanel
{
	private static final long serialVersionUID = 8223746942141530802L;
	
	private static final Logger LOGGER = LogManager.getLogger(ConditionRecoveryPanel.class);
	
	private JLabel errInfoLabel = new JLabel();
	
	private JLabel reqParams = new JLabel(Properties_language_Utils.getValue("reqParams"));
	
	private JLabel startNumLabel = new JLabel("startNum:");
	private JTextField startNumField = new JTextField(10);
	
	private JLabel numCountLabel = new JLabel("numCount:");
	private JTextField numCountField = new JTextField(10);
	
	private JLabel cxTypeLabel = new JLabel("cxType:");
	private JTextField cxTypeField = new JTextField(10);
	
	private JLabel confAcssNumTypeLabel = new JLabel("confAcssNumType:");
	private JTextField confAcssNumTypeField = new JTextField(10);
	
	private JLabel assignStatusLabel = new JLabel("assignStatus:");
	private JTextField assignStatusField = new JTextField(10);
	
	private JLabel searchKeyLabel = new JLabel("searchKey:");
	private JTextField searchKeyField = new JTextField(10);
	
	private JLabel srcCorpIdLabel = new JLabel("srcCorpId:");
	private JTextField srcCorpIdField = new JTextField(10);
	
	private JLabel localGatewayLabel = new JLabel("localGateway:");
	private JTextField localGatewayField = new JTextField(10);
	
	private JLabel callSrcCodeLabel = new JLabel("callSrcCode:");
	private JTextField callSrcCodeField = new JTextField(10);
	
	private JButton conditionRecoveryBtn = new MyButton(Properties_language_Utils.
			getValue("sp.ConditionRecoveryPanel.ConditionRecoveryBtn"));
	
	public ConditionRecoveryPanel() 
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
	    buildPanel(panel, gridbag, c, new JComponent[] {startNumLabel}, 0, 1, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {startNumField}, 1, 1, 10, 20, 2, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {numCountLabel}, 2, 1, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {numCountField}, 3, 1, 6, 20, 2, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {cxTypeLabel}, 0, 2, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {cxTypeField}, 1, 2, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {confAcssNumTypeLabel}, 2, 2, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {confAcssNumTypeField}, 3, 2, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {searchKeyLabel}, 0, 3, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {searchKeyField}, 1, 3, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {assignStatusLabel}, 2, 3, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {assignStatusField}, 3, 3, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {srcCorpIdLabel}, 0, 4, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {srcCorpIdField}, 1, 4, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {localGatewayLabel}, 2, 4, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {localGatewayField}, 3, 4, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {callSrcCodeLabel}, 0, 5, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {callSrcCodeField}, 1, 5, 10, 20, 1, 1);
	    
	    buildPanel(panel, gridbag, c, new JComponent[] {conditionRecoveryBtn}, 0, 6, 10, 20, 2, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {errInfoLabel}, 2, 6, 100, 5, 4, 1);
	    
	    //报文位置
		//message location
		buildPanel(panel, gridbag, c, new JComponent[] {new LogPanel()}, 4, 0, 10, 20, 4, 10);
	    
	    return panel;
	}
	
	private void init() 
	{
		//按条件查回收号段按钮事件
		//Check the recovery number section by condition button event
		this.conditionRecoveryBtn.addMouseListener(new MouseAdapter() 
		{
			public void mouseClicked(MouseEvent e) 
			{
				showErrInfoWithColor("");
	             
	            boolean flag = validateParams(
	            		new JTextField[] {srcCorpIdField},
	            		new String[] {"srcCorpId"});
	            
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
						conditionRecovery();
					}
			    };
			    Future future = Executors.newSingleThreadExecutor().submit(runnable);
		        if(!future.isDone()) {
		            LOGGER.error("addMouseListener fail");
		        }
			 }
		 });
	}
	
	//按条件回收号段
	//Check the recovery number section by condition
	private void conditionRecovery()
	{
		try 
		{
			Token token = LoginUtils.getToken();
			
			// 利用token发送restful请求
			// Send a restful request using token
			RestRequest request = new RestRequest(HTTPConstant.HTTP_METHOD_PUT);
			ConditionRecoveryBean conditionRecoveryBean = new ConditionRecoveryBean();
			conditionRecoveryBean.setSrcCorpId(srcCorpIdField.getText());
			if (StringUtils.isNotEmpty(startNumField.getText())) 
			{
				conditionRecoveryBean.setStartNum(startNumField.getText());
			}
			if (StringUtils.isNotEmpty(numCountField.getText())) 
			{
				conditionRecoveryBean.setStartNum(numCountField.getText());
			}
			if (StringUtils.isNotEmpty(cxTypeField.getText())) 
			{
				conditionRecoveryBean.setStartNum(cxTypeField.getText());
			}
			if (StringUtils.isNotEmpty(confAcssNumTypeField.getText())) 
			{
				conditionRecoveryBean.setStartNum(confAcssNumTypeField.getText());
			}
			if (StringUtils.isNotEmpty(assignStatusField.getText())) 
			{
				conditionRecoveryBean.setStartNum(assignStatusField.getText());
			}
			if (StringUtils.isNotEmpty(searchKeyField.getText())) 
			{
				conditionRecoveryBean.setStartNum(searchKeyField.getText());
			}
			if (StringUtils.isNotEmpty(localGatewayField.getText())) 
			{
				conditionRecoveryBean.setStartNum(localGatewayField.getText());
			}
			if (StringUtils.isNotEmpty(callSrcCodeField.getText()))
			{
				conditionRecoveryBean.setStartNum(callSrcCodeField.getText());
			}
			
			request.setPayload(conditionRecoveryBean);
			EcService.put("/spnum/recovery/condition", request, errInfoLabel, token);
			
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
