package com.huawei.esdk.ec.view.enterprise;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.huawei.esdk.ec.bean.common.RestRequest;
import com.huawei.esdk.ec.bean.common.Token;
import com.huawei.esdk.ec.bean.enterprise.Employee;
import com.huawei.esdk.ec.bean.enterprise.MemberInfo;
import com.huawei.esdk.ec.constant.HTTPConstant;
import com.huawei.esdk.ec.service.EcService;
import com.huawei.esdk.ec.utils.LoginUtils;
import com.huawei.esdk.ec.utils.Properties_language_Utils;
import com.huawei.esdk.ec.utils.StringUtils;
import com.huawei.esdk.ec.view.common.LogPanel;
import com.huawei.esdk.ec.view.common.MyButton;

/**
 * 增加企业成员视图
 * Increase company member view
 * @author wWX534262
 * 
 */
@SuppressWarnings("all")
public class AddCorpMemberPanel extends JPanel implements ActionListener 
{
	private static final long serialVersionUID = -8285213367222038440L;
	
	private static final Logger LOGGER = LogManager.getLogger(AddCorpMemberPanel.class);
	
	private JLabel paramLabel = new JLabel(Properties_language_Utils.getValue("reqParams"));
	
	private JLabel errInfoLabel = new JLabel();
	
	private JLabel corpIdLabel = new JLabel("corpId：");
	private JTextField corpIdField = new JTextField(10);

	private JLabel userAccountLabel = new JLabel("userAccount：");
	private JTextField userAccountField = new JTextField(10);

	private JLabel passwordLabel = new JLabel("password：");
	private JPasswordField passwordField = new JPasswordField(10);

	private JLabel personRoleLabel = new JLabel("personRole：");
	private JTextField personRoleField = new JTextField(10);

	private JLabel userTypeLabel = new JLabel("userType：");
	private JTextField userTypeField = new JTextField(10);
	
	private JLabel adminTypeLabel = new JLabel("adminType:");
	private JTextField adminTypeField = new JTextField(10);

	private JLabel ucServerNumberLabel = new JLabel("ucServerNumber：");
	private JTextField ucServerNumberField = new JTextField(10);
	
	private JLabel softNumberTypeLabel = new JLabel("softNumberType");
	private JTextField softNumberTypeField = new JTextField(10);

	private JLabel shortNumLabel = new JLabel("shortNum：");
	private JTextField shortNumField = new JTextField(10);
	
	private JLabel isActiveLabel = new JLabel("isActive：");
	private JTextField isActiveField = new JTextField(10);

	private JLabel ucFunctionLabel = new JLabel("ucFunction：");
	private JTextField ucFunctionField = new JTextField(10);

	private JLabel ucFunctionNameLabel = new JLabel("ucFunctionName：");
	private JTextField ucFunctionNameField = new JTextField(10);

	// 员工信息
	// employee information
	private JLabel nameLabel = new JLabel("name：");
	private JTextField nameField = new JTextField(10);

	private JLabel genderLabel = new JLabel("gender：");
	private JTextField genderField = new JTextField(10);

	private JLabel titleLabel = new JLabel("title：");
	private JTextField titleField = new JTextField(10);
	
	private JLabel mobilePhoneLabel = new JLabel("mobilePhone：");
	private JTextField mobilePhoneField = new JTextField(10);

	private JLabel fixedPhoneLabel = new JLabel("fixedPhone：");
	private JTextField fixedPhoneField = new JTextField(10);

	private JLabel homePhoneLabel = new JLabel("homePhone：");
	private JTextField homePhoneField = new JTextField(10);

	private JLabel otherPhoneLabel = new JLabel("otherPhone：");
	private JTextField otherPhoneField = new JTextField(10);

	private JLabel otherPhone2Label = new JLabel("otherPhone2：");
	private JTextField otherPhone2Field = new JTextField(10);

	private JLabel faxLabel = new JLabel("fax：");
	private JTextField faxField = new JTextField(10);

	private JLabel emailLabel = new JLabel("email：");
	private JTextField emailField = new JTextField(10);

	private JLabel zipCodeLabel = new JLabel("zipCode：");
	private JTextField zipCodeField = new JTextField(10);

	private JLabel addressLabel = new JLabel("address：");
	private JTextField addressField = new JTextField(10);

	private JLabel websiteLabel = new JLabel("website：");
	private JTextField websiteField = new JTextField(10);

	private JLabel accessLevelLabel = new JLabel("accessLevel：");
	private JTextField accessLevelField = new JTextField(10);

	private JLabel confidentialLabel = new JLabel("confidential：");
	private JTextField confidentialField = new JTextField(10);

	private JLabel customOrderLabel = new JLabel("customOrder：");
	private JTextField customOrderField = new JTextField(10);

	private JLabel deptCodeLabel = new JLabel("deptCode：");
	private JTextField deptCodeField = new JTextField(10);
	
	private JLabel deptNameLabel = new JLabel("deptName");
	private JTextField deptNameField = new JTextField(10);

	private JButton addBtn = new MyButton(Properties_language_Utils.getValue("enter.AddCorpMemberPanel.addBtn"));

	private JButton modifyAcctToBtn = new MyButton(Properties_language_Utils.getValue("enter.AddCorpMemberPanel.modifyAcctToBtn"));

	private JButton cancelBtn = new MyButton(Properties_language_Utils.getValue("enter.AddCorpMemberPanel.cancelBtn"));
	
	
	
	AddCorpMemberPanel() 
	{
		add(getPanels());
		init();
	}

	//设定界面样式
	//interface design
	private JPanel getPanels() 
	{
		GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		JPanel panel = new JPanel(gridbag);

		buildPanel(panel, gridbag, c, new JComponent[] { paramLabel }, 0, 1, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] { corpIdLabel }, 0, 3, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] { corpIdField }, 1, 3, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] { userAccountLabel }, 2, 3, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] { userAccountField }, 3, 3, 10, 20, 1, 1);
		
		buildPanel(panel, gridbag, c, new JComponent[] { passwordLabel }, 0, 4, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] { passwordField }, 1, 4, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] { personRoleLabel }, 2, 4, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] { personRoleField }, 3, 4, 10, 20, 1, 1);
		
		buildPanel(panel, gridbag, c, new JComponent[] { userTypeLabel }, 0, 5, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] { userTypeField }, 1, 5, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] { adminTypeLabel }, 2, 5, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] { adminTypeField }, 3, 5, 10, 20, 1, 1);
		
		buildPanel(panel, gridbag, c, new JComponent[] { ucServerNumberLabel }, 0, 6, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] { ucServerNumberField }, 1, 6, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] { softNumberTypeLabel }, 2, 6, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] { softNumberTypeField }, 3, 6, 10, 20, 1, 1);
		
		buildPanel(panel, gridbag, c, new JComponent[] { shortNumLabel }, 0, 7, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] { shortNumField }, 1, 7, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] { isActiveLabel }, 2, 7, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] { isActiveField }, 3, 7, 10, 20, 1, 1);
		
		buildPanel(panel, gridbag, c, new JComponent[] { ucFunctionNameLabel }, 0, 8, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] { ucFunctionNameField }, 1, 8, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] { ucFunctionLabel }, 2, 8, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] { ucFunctionField }, 3, 8, 10, 20, 1, 1);
		
		buildPanel(panel, gridbag, c, new JComponent[] { nameLabel }, 0, 9, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] { nameField }, 1, 9, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] { genderLabel }, 2, 9, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] { genderField }, 3, 9, 10, 20, 1, 1);
		
		buildPanel(panel, gridbag, c, new JComponent[] { titleLabel }, 0, 10, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] { titleField }, 1, 10, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] { mobilePhoneLabel }, 2, 10, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] { mobilePhoneField }, 3, 10, 10, 20, 1, 1);
		
		buildPanel(panel, gridbag, c, new JComponent[] { fixedPhoneLabel }, 0, 11, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] { fixedPhoneField }, 1, 11, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] { homePhoneLabel }, 2, 11, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] { homePhoneField }, 3, 11, 10, 20, 1, 1);
		
		buildPanel(panel, gridbag, c, new JComponent[] { otherPhoneLabel }, 0, 12, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] { otherPhoneField }, 1, 12, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] { otherPhone2Label }, 2, 12, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] { otherPhone2Field }, 3, 12, 10, 20, 1, 1);
		
		buildPanel(panel, gridbag, c, new JComponent[] { faxLabel }, 0, 13, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] { faxField }, 1, 13, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] { emailLabel }, 2, 13, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] { emailField }, 3, 13, 10, 20, 1, 1);
		
		buildPanel(panel, gridbag, c, new JComponent[] { zipCodeLabel }, 0, 14, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] { zipCodeField }, 1, 14, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] { addressLabel }, 2, 14, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] { addressField }, 3, 14, 10, 20, 1, 1);
		
		buildPanel(panel, gridbag, c, new JComponent[] { websiteLabel }, 0, 15, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] { websiteField }, 1, 15, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] { accessLevelLabel }, 2, 15, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] { accessLevelField }, 3, 15, 10, 20, 1, 1);
		
		buildPanel(panel, gridbag, c, new JComponent[] { confidentialLabel }, 0, 16, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] { confidentialField }, 1, 16, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] { customOrderLabel }, 2, 16, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] { customOrderField }, 3, 16, 10, 20, 1, 1);
		
		buildPanel(panel, gridbag, c, new JComponent[] { deptCodeLabel }, 0, 17, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] { deptCodeField }, 1, 17, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] { deptNameLabel }, 2, 17, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] { deptNameField }, 3, 17, 10, 20, 1, 1);
		
 		buildPanel(panel, gridbag, c, new JComponent[] { addBtn }, 0, 20, 10, 20, 2, 1);
		buildPanel(panel, gridbag, c, new JComponent[] { modifyAcctToBtn }, 1, 20, 10, 20, 2, 1);

		buildPanel(panel, gridbag, c, new JComponent[] { cancelBtn }, 3, 20, 10, 20, 2, 1);
		cancelBtn.addActionListener(this);
		buildPanel(panel, gridbag, c, new JComponent[] { errInfoLabel }, 1, 21, 10, 20, 5, 1);
		
        //报文位置
        //message location
        buildPanel(panel, gridbag, c, new JComponent[] {new LogPanel()}, 4, 0, 10, 20, 4, 10);

		return panel;

	}

	private void init() 
	{
		// 增加企业成员，发起POST请求
		// Increase company members and initiate POST requests
		this.addBtn.addMouseListener(new MouseAdapter() 
		{
			public void mouseClicked(MouseEvent e) {
				showErrInfoWithColor("");
				boolean flag = validateParams(
						new JTextField[] {corpIdField, userAccountField, passwordField,
								personRoleField, userTypeField, nameField, accessLevelField, confidentialField,
								deptCodeField },
						new String[] {"corpId", "userAccount", "password", "personRole", "userType", "name", "accessLevel",
								"confidential", "deptCode" });

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
	            		addAccount();
	            	}
	            };
	            Future future = Executors.newSingleThreadExecutor().submit(runnable);
	            if(!future.isDone()) {
	            	LOGGER.error("addMouseListener fail");
	            }
			}
		});

		// 修改企业成员，发起PUT请求
		// Modify the enterprise member to initiate a PUT request
		this.modifyAcctToBtn.addMouseListener(new MouseAdapter() 
		{
			public void mouseClicked(MouseEvent e) 
			{
				showErrInfoWithColor("");
				boolean flag = validateParams(new JTextField[] {corpIdField, userAccountField },
						new String[] {"corpId", "userAccount"});

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
	            		modifyAcct();
	            	}
	            };
	            Future future = Executors.newSingleThreadExecutor().submit(runnable);
	            if(!future.isDone()) {
	            	LOGGER.error("addMouseListener fail");
	            }
			}
		});
	}

	// 添加企业成员
	// Add company members
	private void addAccount() 
	{
		// 设置参数，发送请求
		// Set parameters, send request
		RestRequest request = new RestRequest(HTTPConstant.HTTP_METHOD_POST);
		request.setPayload(addMemberInfo());
		
		try 
		{
			// 获取发送Rest消息工具类实例，并传入请求参数、添加企业成员资源路径
			// Get the Send Message tool class instance, and pass in the request parameters, 
			// add the enterprise member resource path
			Token token = LoginUtils.getToken();
			
			EcService.post("/corp/" + corpIdField.getText() + "/member", request, errInfoLabel, token);
			
			EcService.finish();
			
			addMemberInfo().setPassword(null);
		} 
		catch (Exception e) 
		{
			LOGGER.error("get Token error",e);
			showErrInfoWithColor("操作失败");
		}
	}


	// 修改企业成员
	// Modify company members
	private void modifyAcct() 
	{
		try 
		{
			Token token = LoginUtils.getToken();
			
			RestRequest request = new RestRequest(HTTPConstant.HTTP_METHOD_PUT);
			request.setPayload(addMemberInfo());
			EcService.put("/corp/" + corpIdField.getText() + "/member", request, errInfoLabel, token);
			
			EcService.finish();
			
			addMemberInfo().setPassword(null);
		}
		catch (Exception e) 
		{
			LOGGER.error("get Token error",e);
			showErrInfoWithColor(Properties_language_Utils.getValue("resultTip"));
		}
	}

	//设置增加企业成员或修改企业成员参数
	//Set up an enterprise member or modify enterprise member parameters
	private MemberInfo addMemberInfo() 
	{
		MemberInfo memberInfo = new MemberInfo();
		
		memberInfo.setUserAccount(userAccountField.getText());
		
		if (StringUtils.isNotEmpty(new String(passwordField.getPassword()))) 
		{
			memberInfo.setPassword(new String(passwordField.getPassword()));
		}
		if (StringUtils.isNotEmpty(personRoleField.getText())) 
		{
			memberInfo.setPersonRole(personRoleField.getText());
		}
		if (StringUtils.isNotEmpty(userTypeField.getText()))
		{
			memberInfo.setUserType(userTypeField.getText());
		}
		if (StringUtils.isNotEmpty(adminTypeField.getText())) 
		{
			memberInfo.setAdminType(adminTypeField.getText());
		}
		if (StringUtils.isNotEmpty(ucServerNumberField.getText())) 
		{
			memberInfo.setUcServerNumber(ucServerNumberField.getText());
		}
		if (StringUtils.isNotEmpty(shortNumField.getText()))
		{
			memberInfo.setShortNum(shortNumField.getText());
		}
		if (StringUtils.isNotEmpty(softNumberTypeField.getText()))
		{
			memberInfo.setSoftNumberType(softNumberTypeField.getText());
		}
		if (StringUtils.isNotEmpty(isActiveField.getText()))
		{
			memberInfo.setIsActive(Integer.valueOf(isActiveField.getText()));
		}
		if (StringUtils.isNotEmpty(ucFunctionField.getText())) 
		{
			memberInfo.setUcFunction(ucFunctionField.getText());
		}
		if (StringUtils.isNotEmpty(ucFunctionNameField.getText()))
		{
			memberInfo.setUcFunctionName(ucFunctionNameField.getText());
		}

		memberInfo.setEmployee(addEmployee());
		
		return memberInfo;
	}
	 
	//设置员工信息参数
	//Set employee information parameters
	private Employee addEmployee() 
	{
		Employee employee = new Employee();
		if (StringUtils.isNotEmpty(nameField.getText())) 
		{
			employee.setName(nameField.getText());
		}
		if (StringUtils.isNotEmpty(genderField.getText())) 
		{
			employee.setGender(genderField.getText());
		}
		if (StringUtils.isNotEmpty(titleField.getText())) 
		{
			employee.setTitle(titleField.getText());
		}
		if (StringUtils.isNotEmpty(mobilePhoneField.getText())) 
		{
			employee.setMobilePhone(mobilePhoneField.getText());
		}
		if (StringUtils.isNotEmpty(fixedPhoneField.getText())) 
		{
			employee.setFixedPhone(fixedPhoneField.getText());
		}

		if (StringUtils.isNotEmpty(homePhoneField.getText())) 
		{
			employee.setHomePhone(homePhoneField.getText());
		}

		if (StringUtils.isNotEmpty(otherPhoneField.getText())) 
		{
			employee.setOtherPhone(otherPhoneField.getText());
		}

		if (StringUtils.isNotEmpty(otherPhone2Field.getText())) 
		{
			employee.setOtherPhone2(otherPhone2Field.getText());
		}

		if (StringUtils.isNotEmpty(faxField.getText())) 
		{
			employee.setFax(faxField.getText());
		}

		if (StringUtils.isNotEmpty(emailField.getText())) 
		{
			employee.setEmail(emailField.getText());
		}

		if (StringUtils.isNotEmpty(zipCodeField.getText()))
		{
			employee.setZipCode(zipCodeField.getText());
		}

		if (StringUtils.isNotEmpty(addressField.getText())) 
		{
			employee.setAddress(addressField.getText());
		}

		if (StringUtils.isNotEmpty(websiteField.getText())) 
		{
			employee.setEmployeeWebsite(websiteField.getText());
		}

		if (StringUtils.isNotEmpty(accessLevelField.getText())) 
		{
			employee.setAccessLevel(Integer.valueOf(accessLevelField.getText()));
		}
		if (StringUtils.isNotEmpty(confidentialField.getText())) 
		{
			employee.setConfidential(Integer.valueOf(confidentialField.getText()));
		}

		if (StringUtils.isNotEmpty(customOrderField.getText())) 
		{
			employee.setCustomOrder(Integer.valueOf(customOrderField.getText()));
		}

		if (StringUtils.isNotEmpty(deptCodeField.getText())) 
		{
			employee.setDeptCode(deptCodeField.getText());
		}
		if (StringUtils.isNotEmpty(deptNameField.getText()))
		{
			employee.setDeptName(deptNameField.getText());
		}

		return employee;
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

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		corpIdField.setText("");
		userAccountField.setText("");
		passwordField.setText("");
		personRoleField.setText("");
		userTypeField.setText("");
		ucServerNumberField.setText("");
		isActiveField.setText("");
		ucFunctionField.setText("");
		ucFunctionNameField.setText("");
		shortNumField.setText("");
		nameField.setText("");
		genderField.setText("");
		titleField.setText("");
		mobilePhoneField.setText("");
		fixedPhoneField.setText("");
		homePhoneField.setText("");
		otherPhoneField.setText("");
		otherPhone2Field.setText("");
		faxField.setText("");
		emailField.setText("");
		zipCodeField.setText("");
		addressField.setText("");
		websiteField.setText("");
		accessLevelField.setText("");
		confidentialField.setText("");
		customOrderField.setText("");
		deptCodeField.setText("");
		deptNameField.setText("");
		adminTypeField.setText("");
	}

}
