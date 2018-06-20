package com.huawei.esdk.ec.view.sp;

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
import com.huawei.esdk.ec.bean.sp.AddCorpBean;
import com.huawei.esdk.ec.bean.sp.UserPack;
import com.huawei.esdk.ec.constant.HTTPConstant;
import com.huawei.esdk.ec.service.EcService;
import com.huawei.esdk.ec.utils.LoginUtils;
import com.huawei.esdk.ec.utils.Properties_language_Utils;
import com.huawei.esdk.ec.utils.StringUtils;
import com.huawei.esdk.ec.view.common.LogPanel;
import com.huawei.esdk.ec.view.common.MyButton;

/**
 * 增加企业视图
 * Add Corp View
 * @author wWX534262
 *
 */
@SuppressWarnings("all")
public class AddCorpPanel extends JPanel
{

	private static final long serialVersionUID = 783021074448577234L;
	
	private static final Logger LOGGER = LogManager.getLogger(AddCorpPanel.class);
	
	private JLabel paramLabel = new JLabel(Properties_language_Utils.getValue("reqParams"));
	
	private JLabel errInfoLabel = new JLabel();
	
	private JLabel spIdLabel = new JLabel("spId:");
	private JTextField spIdField = new JTextField(10);
	
	private JLabel corpIdLabel = new JLabel("corpId:");
	private JTextField corpIdField = new JTextField(10);

	private JLabel corpNameLabel = new JLabel("corpName:");
	private JTextField corpNameField = new JTextField(10);
	
	private JLabel domainLabel = new JLabel("domain:");
	private JTextField domainField = new JTextField(10);
	
	private JLabel primeNumLabel = new JLabel("primeNum:");
	private JTextField primeNumField = new JTextField(10);
	
	private JLabel smsrvNumberLabel = new JLabel("smsrvNumber:");
	private JTextField smsrvNumberField = new JTextField(10);
	
	private JLabel corpFaxLabel = new JLabel("corpFax:");
	private JTextField corpFaxField = new JTextField(10);
	
	private JLabel hangUpSmLabel = new JLabel("hangUpSm:");
	private JTextField hangUpSmField = new JTextField(10);
	
	private JLabel corpAddrLabel = new JLabel("corpAddr:");
	private JTextField corpAddrField = new JTextField(10);
	
	private JLabel emailLabel = new JLabel("email:");
	private JTextField emailField = new JTextField(10);
	
	private JLabel resPackNameLabel = new JLabel("resPackName:");
	private JTextField resPackNameField = new JTextField(10);
	
	private JLabel ucCountLabel = new JLabel("ucCount:");
	private JTextField ucCountField = new JTextField(10);
	
	private JLabel confCountLabel = new JLabel("confCount:");
	private JTextField confCountField = new JTextField(10);
	
	private JLabel pmsiTypeLabel = new JLabel("pmsiType:");
	private JTextField pmsiTypeField = new JTextField(10); 
	
	private JLabel useFixedTPLabel = new JLabel("useFixedTP:");
	private JTextField useFixedTPField = new JTextField(10);
	
	private JLabel serviceTpIdLabel = new JLabel("serviceTpId:");
	private JTextField serviceTpIdField = new JTextField(10);
	
	private JLabel mgrDomainLabel = new JLabel("mgrDomain:");
	private JTextField mgrDomainField = new JTextField(10);
	
	private JLabel servCodeLabel = new JLabel("servCode:");
	private JTextField servCodeField = new JTextField(10);
	
	private JLabel subServcodeLabel = new JLabel("subServcode:");
	private JTextField subServcodeField = new JTextField(10);
	
	//用户包信息
	//User package information
	private JLabel infoLabel = new JLabel(Properties_language_Utils.getValue("sp.AddCorpPanel.infoLabel"));
	private JLabel packNameLabel = new JLabel("packName:");
	private JTextField packNameField = new JTextField(10);
	
	private JLabel packCountLabel = new JLabel("packCount:");
	private JTextField packCountField = new JTextField(10);
	
	private JButton addUserPackBtn = new MyButton(Properties_language_Utils.getValue("sp.AddCorpPanel.addUserPackBtn"),140,35);
	
	private JButton addCorpBtn = new MyButton(Properties_language_Utils.getValue("sp.AddCorpPanel.addCorpBtn"),140,35);
	
	private JButton modifyCorpBtn = new MyButton(Properties_language_Utils.getValue("sp.AddCorpPanel.modifyCorpBtn"),140,35);
	
	private JButton cancelBtn = new MyButton(Properties_language_Utils.getValue("sp.AddCorpPanel.cancelBtn"));
	
	private List<UserPack> usrPacks = new ArrayList<>();
	public AddCorpPanel() 
	{
		add(getPanels());
		init();
	}
	
	private JPanel getPanels() 
	{
		GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		JPanel panel = new JPanel(gridbag);
		
		buildPanel(panel, gridbag, c, new JComponent[] {paramLabel}, 0, 0, 10, 20, 1, 1);
		
		buildPanel(panel, gridbag, c, new JComponent[] {spIdLabel}, 0, 3, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {spIdField}, 1, 3, 10, 20, 3, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {corpIdLabel}, 2, 3, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {corpIdField}, 3, 3, 6, 20, 2, 1);
		
		buildPanel(panel, gridbag, c, new JComponent[] {corpNameLabel}, 0, 4, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {corpNameField}, 1, 4, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {domainLabel}, 2, 4, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {domainField}, 3, 4, 10, 20, 1, 1);
		
		buildPanel(panel, gridbag, c, new JComponent[] {primeNumLabel}, 0, 5, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {primeNumField}, 1, 5, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {smsrvNumberLabel}, 2, 5, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {smsrvNumberField}, 3, 5, 10, 20, 1, 1);
		
		buildPanel(panel, gridbag, c, new JComponent[] {corpFaxLabel}, 0, 6, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {corpFaxField}, 1, 6, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {hangUpSmLabel}, 2, 6, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {hangUpSmField}, 3, 6, 10, 20, 1, 1);
		
		buildPanel(panel, gridbag, c, new JComponent[] {corpAddrLabel}, 0, 7, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {corpAddrField}, 1, 7, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {emailLabel}, 2, 7, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {emailField}, 3, 7, 10, 20, 1, 1);
		
		buildPanel(panel, gridbag, c, new JComponent[] {resPackNameLabel}, 0, 8, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {resPackNameField}, 1, 8, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {ucCountLabel}, 2, 8, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {ucCountField}, 3, 8, 10, 20, 1, 1);
		
		buildPanel(panel, gridbag, c, new JComponent[] {confCountLabel}, 0, 9, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {confCountField}, 1, 9, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {pmsiTypeLabel}, 2, 9, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {pmsiTypeField}, 3, 9, 10, 20, 1, 1);
		
		buildPanel(panel, gridbag, c, new JComponent[] {useFixedTPLabel}, 0, 10, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {useFixedTPField}, 1, 10, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {serviceTpIdLabel}, 2, 10, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {serviceTpIdField}, 3, 10, 10, 20, 1, 1);
		
		buildPanel(panel, gridbag, c, new JComponent[] {mgrDomainLabel}, 0, 11, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {mgrDomainField}, 1, 11, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {servCodeLabel}, 2, 11, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {servCodeField}, 3, 11, 10, 20, 1, 1);
		
		buildPanel(panel, gridbag, c, new JComponent[] {subServcodeLabel}, 0, 12, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {subServcodeField}, 1, 12, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {packNameLabel}, 2, 12, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {packNameField}, 3, 12, 10, 20, 1, 1);
		
		buildPanel(panel, gridbag, c, new JComponent[] {packCountLabel}, 0, 13, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {packCountField}, 1, 13, 10, 20, 1, 1);
		
		buildPanel(panel, gridbag, c, new JComponent[] {addUserPackBtn}, 0, 14, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {addCorpBtn}, 1, 14, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {modifyCorpBtn}, 2, 14, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {cancelBtn}, 2, 13, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {errInfoLabel}, 1, 15, 10, 20, 2, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {infoLabel}, 1, 16, 10, 20, 4, 1);
		
	    //报文位置
		//message location
		buildPanel(panel, gridbag, c, new JComponent[] {new LogPanel()}, 4, 0, 10, 20, 4, 10);
		
		return panel;
	}
	
	public void init() 
	{
		infoLabel.setForeground(Color.blue);
		//增加业务包按钮事件
		//Add servPack button event
		this.addUserPackBtn.addMouseListener(new MouseAdapter() 
		{
			public void mouseClicked(MouseEvent e) 
			{
				showErrInfoWithColor("");
				boolean flag = validateParams(
						new JTextField[] {packNameField, packCountField},
						new String[] {"packName", "packCount"});

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
						usrPacks.add(addUsrPack());
						showErrInfoWithColor(Properties_language_Utils.getValue("sp.AddCorpPanel.addUserPackTip"));
					}
			    };
			    Future future = Executors.newSingleThreadExecutor().submit(runnable);
		        if(!future.isDone()) {
		            LOGGER.error("addMouseListener fail");
		        }
			}
		});
		
		// 增加企业按钮事件
		// Increase corp button events
		this.addCorpBtn.addMouseListener(new MouseAdapter() 
		{
			public void mouseClicked(MouseEvent e) 
			{
				showErrInfoWithColor("");
				boolean flag = validateParams(
						new JTextField[] {corpNameField, domainField},
						new String[] {"corpName", "domain"});

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
						addCorp();
					}
			    };
			    Future future = Executors.newSingleThreadExecutor().submit(runnable);
		        if(!future.isDone()) {
		            LOGGER.error("addMouseListener fail");
		        }
			}
		});
		
		// 修改企业按钮事件
		// Modify corp button event
		this.modifyCorpBtn.addMouseListener(new MouseAdapter() 
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
						modifyCorp();
					}
			    };
			    Future future = Executors.newSingleThreadExecutor().submit(runnable);
		        if(!future.isDone()) {
		            LOGGER.error("addMouseListener fail");
		        }
			}
		});
		
		//清空按钮事件
		//Clear button event
		this.cancelBtn.addMouseListener(new MouseAdapter() 
		{
			public void mouseClicked(MouseEvent e) 
			{
				spIdField.setText("");
				corpIdField.setText("");
				corpNameField.setText("");
				domainField.setText("");
				primeNumField.setText("");
				smsrvNumberField.setText("");
				corpFaxField.setText("");
				hangUpSmField.setText("");
				corpAddrField.setText("");
				emailField.setText("");
				resPackNameField.setText("");
				ucCountField.setText("");
				confCountField.setText("");
				pmsiTypeField.setText("");
				useFixedTPField.setText("");
				serviceTpIdField.setText("");
				mgrDomainField.setText("");
				servCodeField.setText("");
				subServcodeField.setText("");
				packNameField.setText("");
				packCountField.setText("");
			}
		});
	}
	
	//修改企业
	//modify corp
	private void modifyCorp() 
	{
		AddCorpBean addCorpBean = new AddCorpBean();
		if (StringUtils.isNotEmpty(spIdField.getText())) 
		{
			addCorpBean.setSpId(spIdField.getText());
		}
		
		addCorpBean.setCorpId(corpIdField.getText());
		
		if (StringUtils.isNotEmpty(primeNumField.getText())) 
		{
			addCorpBean.setPrimeNum(primeNumField.getText());
		}
		if (StringUtils.isNotEmpty(smsrvNumberField.getText())) 
		{
			addCorpBean.setSmsrvNumber(smsrvNumberField.getText());
		}
		if (StringUtils.isNotEmpty(corpFaxField.getText())) 
		{
			addCorpBean.setCorpFax(corpFaxField.getText());
		}
		if (StringUtils.isNotEmpty(hangUpSmField.getText())) 
		{
			addCorpBean.setHangUpSm(hangUpSmField.getText());
		}
		if (StringUtils.isNotEmpty(corpAddrField.getText())) 
		{
			addCorpBean.setCorpAddr(corpAddrField.getText());
		}
		if (StringUtils.isNotEmpty(emailField.getText())) 
		{
			addCorpBean.setEmail(emailField.getText());
		}
		if (StringUtils.isNotEmpty(resPackNameField.getText())) 
		{
			addCorpBean.setResPackName(resPackNameField.getText());
		}
		if (StringUtils.isNotEmpty(ucCountField.getText())) 
		{
			addCorpBean.setUcCount(Integer.parseInt(ucCountField.getText()));
		}
		if (StringUtils.isNotEmpty(confCountField.getText())) 
		{
			addCorpBean.setConfCount(Integer.parseInt(confCountField.getText()));
		}
		if (StringUtils.isNotEmpty(pmsiTypeField.getText())) 
		{
			addCorpBean.setPmsiType(Integer.parseInt(pmsiTypeField.getText()));
		}
		if (StringUtils.isNotEmpty(useFixedTPField.getText())) 
		{
			addCorpBean.setUseFixedTP(Integer.parseInt(useFixedTPField.getText()));
		}
		if (StringUtils.isNotEmpty(serviceTpIdField.getText())) 
		{
			addCorpBean.setServiceTpId(Integer.parseInt(serviceTpIdField.getText()));
		}
		if (StringUtils.isNotEmpty(mgrDomainField.getText())) 
		{
			addCorpBean.setMgrDomain(mgrDomainField.getText());
		}
		if (StringUtils.isNotEmpty(servCodeField.getText())) 
		{
			addCorpBean.setServCode(servCodeField.getText());
		}
		if (StringUtils.isNotEmpty(subServcodeField.getText())) 
		{
			addCorpBean.setSubServcode(subServcodeField.getText());
		}
		if (usrPacks.size() != 0) 
		{
			addCorpBean.setUsrPacks(usrPacks);
		}
		
		//设置请求参数
		//Set request parameters
		RestRequest request = new RestRequest(HTTPConstant.HTTP_METHOD_PUT);
		request.setPayload(addCorpBean);
		try 
		{
			Token token = LoginUtils.getToken();
			EcService.post("/sp/corp", request, errInfoLabel,token);
			
			EcService.finish();
		} catch (Exception e) 
		{
			LOGGER.error("getToken error:" + e);
		}
	}
	
	//增加企业
	//add corp
	private void addCorp()
	{
		AddCorpBean addCorpBean = new AddCorpBean();
		if (StringUtils.isNotEmpty(spIdField.getText())) 
		{
			addCorpBean.setSpId(spIdField.getText());
		}
		
		addCorpBean.setCorpName(corpNameField.getText());
		
		addCorpBean.setDomain(domainField.getText());
		
		if (StringUtils.isNotEmpty(primeNumField.getText())) 
		{
			addCorpBean.setPrimeNum(primeNumField.getText());
		}
		if (StringUtils.isNotEmpty(smsrvNumberField.getText())) 
		{
			addCorpBean.setSmsrvNumber(smsrvNumberField.getText());
		}
		if (StringUtils.isNotEmpty(corpFaxField.getText())) 
		{
			addCorpBean.setCorpFax(corpFaxField.getText());
		}
		if (StringUtils.isNotEmpty(hangUpSmField.getText())) 
		{
			addCorpBean.setHangUpSm(hangUpSmField.getText());
		}
		if (StringUtils.isNotEmpty(corpAddrField.getText())) 
		{
			addCorpBean.setCorpAddr(corpAddrField.getText());
		}
		if (StringUtils.isNotEmpty(emailField.getText())) 
		{
			addCorpBean.setEmail(emailField.getText());
		}
		if (StringUtils.isNotEmpty(resPackNameField.getText())) 
		{
			addCorpBean.setResPackName(resPackNameField.getText());
		}
		if (StringUtils.isNotEmpty(ucCountField.getText())) 
		{
			addCorpBean.setUcCount(Integer.parseInt(ucCountField.getText()));
		}
		if (StringUtils.isNotEmpty(confCountField.getText())) 
		{
			addCorpBean.setConfCount(Integer.parseInt(confCountField.getText()));
		}
		if (StringUtils.isNotEmpty(pmsiTypeField.getText())) 
		{
			addCorpBean.setPmsiType(Integer.parseInt(pmsiTypeField.getText()));
		}
		if (StringUtils.isNotEmpty(useFixedTPField.getText())) 
		{
			addCorpBean.setUseFixedTP(Integer.parseInt(useFixedTPField.getText()));
		}
		if (StringUtils.isNotEmpty(serviceTpIdField.getText())) 
		{
			addCorpBean.setServiceTpId(Integer.parseInt(serviceTpIdField.getText()));
		}
		if (StringUtils.isNotEmpty(mgrDomainField.getText())) 
		{
			addCorpBean.setMgrDomain(mgrDomainField.getText());
		}
		if (StringUtils.isNotEmpty(servCodeField.getText())) 
		{
			addCorpBean.setServCode(servCodeField.getText());
		}
		if (StringUtils.isNotEmpty(subServcodeField.getText())) 
		{
			addCorpBean.setSubServcode(subServcodeField.getText());
		}
		if (usrPacks.size() != 0) 
		{
			addCorpBean.setUsrPacks(usrPacks);
		}
			
		RestRequest request = new RestRequest(HTTPConstant.HTTP_METHOD_POST);
		request.setPayload(addCorpBean);
		try 
		{
			Token token = LoginUtils.getToken();
			EcService.post("/sp/corp", request, errInfoLabel,token);
			
			EcService.finish();
		} 
		catch (Exception e) 
		{
			LOGGER.error("getToken error:" + e);
		}
	}

	//设置用户包信息
	//Set user package information
	private UserPack addUsrPack() 
	{
		
		UserPack userPack = new UserPack();
		if (StringUtils.isNotEmpty(packNameField.getText())) 
		{
			userPack.setPackName(packNameField.getText());
		}
		if (StringUtils.isNotEmpty(packCountField.getText())) 
		{
			userPack.setPackCount(Integer.parseInt(packCountField.getText()));
		}
		
		return userPack;
	}
	
	private void buildPanel(JPanel panel, GridBagLayout gridbag, GridBagConstraints c, JComponent[] components,
			int gridx, int gridy, int ipadx, int ipady, int gridwidth, int gridheight) 
	{
		JPanel subPanel = new JPanel();

		for (JComponent component : components) {
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
			if (null == textFields[i] || null == textFields[i].getText() || "".equals(textFields[i].getText().trim())) {
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
