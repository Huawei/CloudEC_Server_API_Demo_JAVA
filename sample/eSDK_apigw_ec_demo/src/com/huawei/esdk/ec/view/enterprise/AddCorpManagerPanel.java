package com.huawei.esdk.ec.view.enterprise;

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
import com.huawei.esdk.ec.bean.enterprise.AddCorpManagerBean;
import com.huawei.esdk.ec.bean.enterprise.DeptMgr;
import com.huawei.esdk.ec.bean.enterprise.NumSegmentMgr;
import com.huawei.esdk.ec.bean.enterprise.UserCompetence;
import com.huawei.esdk.ec.bean.enterprise.UserContactInfo;
import com.huawei.esdk.ec.bean.enterprise.UserIndividual;
import com.huawei.esdk.ec.constant.HTTPConstant;
import com.huawei.esdk.ec.service.EcService;
import com.huawei.esdk.ec.utils.LoginUtils;
import com.huawei.esdk.ec.utils.Properties_language_Utils;
import com.huawei.esdk.ec.utils.StringUtils;
import com.huawei.esdk.ec.view.common.LogPanel;
import com.huawei.esdk.ec.view.common.MyButton;


/**
 * 增加企业管理员视图
 * Increase the enterprise administrator view
 * @author wWX534262
 *
 */
@SuppressWarnings("all")
public class AddCorpManagerPanel extends JPanel implements ActionListener
{
	private static final long serialVersionUID = -2641952198994253845L;
	
	private static final Logger LOGGER = LogManager.getLogger(AddCorpManagerPanel.class);
	
	private JLabel paramLabel = new JLabel(Properties_language_Utils.getValue("reqParams"));
	
	private JLabel errInfoLabel = new JLabel();
	
	private JLabel infoLabel = new JLabel(Properties_language_Utils.
			getValue("enter.AddCorpManagerPanel.infoLabel"));
	
	private JLabel corpIdLabel = new JLabel("corpId:");
	private JTextField corpIdField = new JTextField(10);

	private JLabel userAccountLabel = new JLabel("userAccount:");
	private JTextField userAccountField = new JTextField(10);
	
	private JLabel userTypeLabel = new JLabel("userType:");
	private JTextField userTypeField = new JTextField(10);
	
	private JLabel isActiveLabel = new JLabel("isActive:");
	private JTextField isActiveField = new JTextField(10);
	
	private JLabel adminTypeLabel = new JLabel("adminType:");
	private JTextField adminTypeField = new JTextField(10);
	
	private JLabel roleNameLabel = new JLabel("roleName:");
	private JTextField roleNameField = new JTextField(10);
	
	private JLabel emailAddrLabel = new JLabel("emailAddr:");
	private JTextField emailAddrField = new JTextField(10);
	
	private JLabel pwdLabel = new JLabel("pwd:");
	private JPasswordField pwdField = new JPasswordField(10);
	
	private JLabel personRoleNameLabel = new JLabel("personRoleName:");
	private JTextField personRoleNameField = new JTextField(10);
	
	private JLabel aliasLabel = new JLabel("alias:");
	private JTextField aliasField = new JTextField(10);
	
	//企业成员的个人信息
	//Personal information of corporate members
	private JLabel nameLabel = new JLabel("name:");
	private JTextField nameField = new JTextField(10);
	
	private JLabel deptCodeLabel = new JLabel("deptCode:");
	private JTextField deptCodeField = new JTextField(10);
	
	private JLabel deptNameLabel = new JLabel("deptName:");
	private JTextField deptNameField = new JTextField(10);
	
	private JLabel titleLabel = new JLabel("title:");
	private JTextField titleField = new JTextField(10);
	
	//企业成员的联系信息
	//Contact information for company members
	private JLabel mobilePhoneLabel = new JLabel("mobilePhone:");
	private JTextField mobilePhoneField = new JTextField(10);
	
	private JLabel faxLabel = new JLabel("fax:");
	private JTextField faxField = new JTextField(10);
	
	private JLabel officePhoneLabel = new JLabel("officePhone:");
	private JTextField officePhoneField = new JTextField(10);
	
	private JLabel homePhoneLabel = new JLabel("homePhone:");
	private JTextField homePhoneField = new JTextField(10);
	
	private JLabel zipCodeLabel = new JLabel("zipCode:");
	private JTextField zipCodeField = new JTextField(10);
	
	private JLabel otherPhoneLabel = new JLabel("otherPhone:");
	private JTextField otherPhoneField = new JTextField(10);
	
	private JLabel otherPhone2Label = new JLabel("otherPhone2:");
	private JTextField otherPhone2Field = new JTextField(10);
	
	private JLabel addressLabel = new JLabel("address:");
	private JTextField addressField = new JTextField(10);
	
	private JLabel webSiteLabel = new JLabel("webSite:");
	private JTextField webSiteField = new JTextField(10);
	
	//企业管理员管理的号码段
	//Number segment managed by the enterprise administrator
	private JLabel startNumLabel = new JLabel("startNum:");
	private JTextField startNumField = new JTextField(10);
	
	private JLabel endNumLabel = new JLabel("endNum:");
	private JTextField endNumField = new JTextField(10);
	
	//企业账号权限信息
	//Enterprise account permission information
	private JLabel customOrderLabel = new JLabel("customOrder:");
	private JTextField customOrderField = new JTextField(10);
	
	private JLabel accessLevelNameLabel = new JLabel("accessLevelName:");
	private JTextField accessLevelNameField = new JTextField(10);
	
	private JLabel accessLevelLabel = new JLabel("accessLevel:");
	private JTextField accessLevelField = new JTextField(10);
	
	private JLabel isMeetingLabel = new JLabel("isMeeting:");
	private JTextField isMeetingField = new JTextField(10);
	
	private JButton addDeptBtn = new MyButton(Properties_language_Utils.
			getValue("enter.AddCorpManagerPanel.addDeptBtn"));
	
	private JButton addCorpManagerBtn = new MyButton(Properties_language_Utils.
			getValue("enter.AddCorpManagerPanel.addCorpManagerBtn"));
	
	private JButton modifyCorpManagerBtn = new MyButton(Properties_language_Utils.
			getValue("enter.AddCorpManagerPanel.modifyCorpManagerBtn"));
	
	private JButton cancelBtn = new MyButton(Properties_language_Utils.getValue("enter.AddCorpManagerPanel.cancelBtn"));
	

	
	
	//普通企业管理员所能管理的部门编码列表
	//List of department codes that can be managed by common enterprise administrators
	List<DeptMgr> deptMgrLst = new ArrayList<>();
	
	public AddCorpManagerPanel() 
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
		
		buildPanel(panel, gridbag, c, new JComponent[] {corpIdLabel}, 0, 3, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {corpIdField}, 1, 3, 10, 20, 3, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {userAccountLabel}, 2, 3, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {userAccountField}, 3, 3, 6, 20, 2, 1);
		
		buildPanel(panel, gridbag, c, new JComponent[] {userTypeLabel}, 0, 4, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {userTypeField}, 1, 4, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {isActiveLabel}, 2, 4, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {isActiveField}, 3, 4, 10, 20, 1, 1);
		
		buildPanel(panel, gridbag, c, new JComponent[] {adminTypeLabel}, 0, 5, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {adminTypeField}, 1, 5, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {roleNameLabel}, 2, 5, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {roleNameField}, 3, 5, 10, 20, 1, 1);
		
		buildPanel(panel, gridbag, c, new JComponent[] {emailAddrLabel}, 0, 6, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {emailAddrField}, 1, 6, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {pwdLabel}, 2, 6, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {pwdField}, 3, 6, 10, 20, 1, 1);
		
		buildPanel(panel, gridbag, c, new JComponent[] {personRoleNameLabel}, 0, 7, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {personRoleNameField}, 1, 7, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {aliasLabel}, 2, 7, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {aliasField}, 3, 7, 10, 20, 1, 1);
		
		buildPanel(panel, gridbag, c, new JComponent[] {nameLabel}, 0, 8, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {nameField}, 1, 8, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {deptCodeLabel}, 2, 8, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {deptCodeField}, 3, 8, 10, 20, 1, 1);
		
		buildPanel(panel, gridbag, c, new JComponent[] {deptNameLabel}, 0, 9, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {deptNameField}, 1, 9, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {titleLabel}, 2, 9, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {titleField}, 3, 9, 10, 20, 1, 1);
		
		buildPanel(panel, gridbag, c, new JComponent[] {mobilePhoneLabel}, 0, 10, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {mobilePhoneField}, 1, 10, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {officePhoneLabel}, 2, 10, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {officePhoneField}, 3, 10, 10, 20, 1, 1);
		
		buildPanel(panel, gridbag, c, new JComponent[] {faxLabel}, 0, 11, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {faxField}, 1, 11, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {homePhoneLabel}, 2, 11, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {homePhoneField}, 3, 11, 10, 20, 1, 1);
		
		buildPanel(panel, gridbag, c, new JComponent[] {otherPhoneLabel}, 0, 12, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {otherPhoneField}, 1, 12, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {otherPhone2Label}, 2, 12, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {otherPhone2Field}, 3, 12, 10, 20, 1, 1);
		
		buildPanel(panel, gridbag, c, new JComponent[] {zipCodeLabel}, 0, 13, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {zipCodeField}, 1, 13, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {addressLabel}, 2, 13, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {addressField}, 3, 13, 10, 20, 1, 1);
		
		buildPanel(panel, gridbag, c, new JComponent[] {webSiteLabel}, 0, 14, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {webSiteField}, 1, 14, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {startNumLabel}, 2, 14, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {startNumField}, 3, 14, 10, 20, 1, 1);
		
		buildPanel(panel, gridbag, c, new JComponent[] {endNumLabel}, 0, 15, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {endNumField}, 1, 15, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {customOrderLabel}, 2, 15, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {customOrderField}, 3, 15, 10, 20, 1, 1);
		
		buildPanel(panel, gridbag, c, new JComponent[] {accessLevelNameLabel}, 0, 16, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {accessLevelNameField}, 1, 16, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {accessLevelLabel}, 2, 16, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {accessLevelField}, 3, 16, 10, 20, 1, 1);
		
		buildPanel(panel, gridbag, c, new JComponent[] {isMeetingLabel}, 0, 17, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {isMeetingField}, 1, 17, 10, 20, 1, 1);
		
		//按钮
		buildPanel(panel, gridbag, c, new JComponent[] {addDeptBtn}, 0, 18, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {addCorpManagerBtn}, 1, 18, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {modifyCorpManagerBtn}, 2, 18, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {cancelBtn}, 3, 18, 10, 20, 1, 1);
		cancelBtn.addActionListener(this);
		buildPanel(panel, gridbag, c, new JComponent[] {errInfoLabel}, 2, 17, 10, 20, 2, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {infoLabel}, 0, 19, 10, 20, 4, 1);
		
        //报文位置
        //message location
        buildPanel(panel, gridbag, c, new JComponent[] {new LogPanel()}, 4, 0, 10, 20, 4, 10);
		
		return panel;
	}
	
	private void init() 
	{
		infoLabel.setForeground(Color.BLUE);
		
		//添加管理部门按钮事件
		//Add department button event
		this.addDeptBtn.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
			{
				showErrInfoWithColor("");
			            
				boolean flag = validateParams(
						new JTextField[] {deptCodeField},
			            new String[] {"deptCode"});
			                
				if (!flag)
			    {
					return;
			    }
			            
			    Runnable runnable = new Runnable()
	            {
	            	@Override
	            	public void run()
	            	{
	            		deptMgrLst.add(addDeptMgr());
	            	}
	            };
	            Future future = Executors.newSingleThreadExecutor().submit(runnable);
	            if(!future.isDone()) {
	            	LOGGER.error("addMouseListener fail");
	            }
			}
		});
		
		//添加企业管理员按钮事件
		//Add Enterprise Manager Button Event
		this.addCorpManagerBtn.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
			{
				showErrInfoWithColor("");
			            
				boolean flag = validateParams(
						new JTextField[] {corpIdField, userAccountField, userTypeField, isActiveField, roleNameField, nameField},
			            new String[] {"corpId", "userAccount", "userType", "isActive", "roleName", "name"});
			                
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
	            		addCorpManager();
	            	}
	            };
	            Future future = Executors.newSingleThreadExecutor().submit(runnable);
	            if(!future.isDone()) {
	            	LOGGER.error("addMouseListener fail");
	            }
			}
		});
		
		//修改企业管理员按钮事件
		//Modify the enterprise administrator button events
		this.modifyCorpManagerBtn.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
			{
				showErrInfoWithColor("");
			            
				boolean flag = validateParams(
						new JTextField[] {corpIdField, userAccountField, userTypeField, isActiveField, roleNameField, nameField},
			            new String[] {"corpId", "userAccount", "userType", "isActive", "roleName", "name"});
			                
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
	            		modifyCorpManager();
	            	}
	            };
	            Future future = Executors.newSingleThreadExecutor().submit(runnable);
	            if(!future.isDone()) {
	            	LOGGER.error("addMouseListener fail");
	            }
			}
		});
		
	}
	
	//修改企业管理员
	//Modify the enterprise administrator
	private void modifyCorpManager() 
	{
		try
		{
			Token token = LoginUtils.getToken();
			
			RestRequest request = new RestRequest(HTTPConstant.HTTP_METHOD_PUT);
			
			request.setPayload(addCorpManagerBean());
			
			EcService.post("/corp/" + corpIdField.getText() + "/userAccount", request, errInfoLabel, token);
			
			addCorpManagerBean().setPwd(null);
			EcService.finish();
		} 
		catch (Exception e)
		{
			LOGGER.error("get Token error",e);
		}
	}
	
	//增加企业管理员
	//Add the enterprise administrator
	private void addCorpManager() 
	{
		try
		{
			Token token = LoginUtils.getToken();
			
			RestRequest request = new RestRequest(HTTPConstant.HTTP_METHOD_POST);
			
			request.setPayload(addCorpManagerBean());
			
			EcService.post("/corp/" + corpIdField.getText() + "/userAccount", request, errInfoLabel, token);
			
			addCorpManagerBean().setPwd(null);
			EcService.finish();
		} 
		catch (Exception e)
		{
			LOGGER.error("get Token error",e);
		}
	}
	
	//企业管理员详情信息
	//Enterprise administrator details
	private AddCorpManagerBean addCorpManagerBean() 
	{
		AddCorpManagerBean addCorpManagerBean = new AddCorpManagerBean();
		
		addCorpManagerBean.setUserAccount(userAccountField.getText());
		addCorpManagerBean.setUserType(userTypeField.getText());
		
		if (StringUtils.isNotEmpty(adminTypeField.getText())) 
		{
			addCorpManagerBean.setAdminType(adminTypeField.getText());
		}
		addCorpManagerBean.setIsActive(Integer.parseInt(isActiveField.getText()));
		addCorpManagerBean.setRoleName(roleNameField.getText());
		addCorpManagerBean.setUserIndividual(addUserIndividual());
		addCorpManagerBean.setUserContactInfo(addUserContactInfo());
		if (addNumSegmentMgr().size() != 0) 
		{
			addCorpManagerBean.setNumSegList(addNumSegmentMgr());
		}
		
		if (deptMgrLst.size() != 0) 
		{
			addCorpManagerBean.setDeptMgrList(deptMgrLst);
		}
		
		
		if (StringUtils.isNotEmpty(emailAddrField.getText())) 
		{
			addCorpManagerBean.setEmailAddr(emailAddrField.getText());
		}
		if (StringUtils.isNotEmpty(new String(pwdField.getPassword()))) 
		{
			addCorpManagerBean.setPwd(new String(pwdField.getPassword()));
		}
		if (StringUtils.isNotEmpty(personRoleNameField.getText())) 
		{
			addCorpManagerBean.setPersonRoleName(personRoleNameField.getText());
		}
		if (StringUtils.isNotEmpty(aliasField.getText())) 
		{
			addCorpManagerBean.setAlias(aliasField.getText());
		}
		
		addCorpManagerBean.setUserCompetence(addUserCompetence());
		
		return addCorpManagerBean;
	}
	
	//企业成员的个人信息
	//Personal information of corporate members
	private UserIndividual addUserIndividual() 
	{
		UserIndividual userIndividual = new UserIndividual();
		
		userIndividual.setName(nameField.getText());
		if (StringUtils.isNotEmpty(deptCodeField.getText())) 
		{
			userIndividual.setDeptCode(deptCodeField.getText());
		}
		if (StringUtils.isNotEmpty(deptNameField.getText())) 
		{
			userIndividual.setDeptName(deptNameField.getText());
		}
		
		if (StringUtils.isNotEmpty(titleField.getText())) 
		{
			userIndividual.setTitle(titleField.getText());
		}
		
		return userIndividual;
	}
	
	//企业成员的联系信息
	//Contact information for company members
	private UserContactInfo addUserContactInfo() 
	{
		UserContactInfo userContactInfo = new UserContactInfo();
		
		if (StringUtils.isNotEmpty(mobilePhoneField.getText()))
		{
			userContactInfo.setMobilePhone(mobilePhoneField.getText());
		}
		if (StringUtils.isNotEmpty(faxField.getText()))
		{
			userContactInfo.setFax(faxField.getText());
		}
		if (StringUtils.isNotEmpty(officePhoneField.getText()))
		{
			userContactInfo.setOfficePhone(officePhoneField.getText());
		}
		if (StringUtils.isNotEmpty(homePhoneField.getText()))
		{
			userContactInfo.setHomePhone(homePhoneField.getText());
		}
		if (StringUtils.isNotEmpty(zipCodeField.getText()))
		{
			userContactInfo.setZipCode(zipCodeField.getText());
		}
		if (StringUtils.isNotEmpty(otherPhoneField.getText()))
		{
			userContactInfo.setOtherPhone(otherPhoneField.getText());
		}
		if (StringUtils.isNotEmpty(otherPhone2Field.getText()))
		{
			userContactInfo.setOtherPhone2(otherPhone2Field.getText());
		}
		if (StringUtils.isNotEmpty(addressField.getText()))
		{
			userContactInfo.setAddress(addressField.getText());
		}
		if (StringUtils.isNotEmpty(webSiteField.getText()))
		{
			userContactInfo.setWebSite(webSiteField.getText());
		}
		
		return userContactInfo;
	}
	
	//普通企业管理员所能管理的号码段列表
	//List of number segments that can be managed by common corporate administrators
	private List<NumSegmentMgr> addNumSegmentMgr() 
	{
		List<NumSegmentMgr> numSegmentMgrLst = new ArrayList<>();
		
		//标记数字
		int number = 0;
		NumSegmentMgr numSegmentMgr = new NumSegmentMgr();
		if (StringUtils.isNotEmpty(startNumField.getText())) 
		{
			numSegmentMgr.setStartNum(startNumField.getText());
			number++;
		}
		if (StringUtils.isNotEmpty(endNumField.getText())) 
		{
			numSegmentMgr.setEndNum(endNumField.getText());
			number++;
		}
		
		//number==0 表示numSegmentMgr对象的属性值都为空，反之不为空，将对象设置到list中
		if (number != 0) 
		{
			numSegmentMgrLst.add(numSegmentMgr);
		}
		
		return numSegmentMgrLst;
	}
	
	//普通企业管理员所能管理的部门编码
	//A department code that can be managed by an ordinary enterprise administrator
	private DeptMgr addDeptMgr() 
	{
		DeptMgr deptMgr = new DeptMgr();
		deptMgr.setDeptCode(deptCodeField.getText());
		if (StringUtils.isNotEmpty(deptNameField.getText())) 
		{
			deptMgr.setDeptName(deptNameField.getText());
		}
		
		return deptMgr;
	}
	
	//企业账号权限信息 
	//Enterprise account permission information
	private UserCompetence addUserCompetence() 
	{
		UserCompetence userCompetence = new UserCompetence();
		
		if (StringUtils.isNotEmpty(customOrderField.getText())) 
		{
			userCompetence.setCustomOrder(customOrderField.getText());
		}
		
		if (StringUtils.isNotEmpty(accessLevelNameField.getText())) 
		{
			userCompetence.setAccessLevelName(accessLevelNameField.getText());
		}
		
		if (StringUtils.isNotEmpty(accessLevelField.getText())) 
		{
			userCompetence.setAccessLevel(accessLevelField.getText());
		}
		
		if (StringUtils.isNotEmpty(isMeetingField.getText())) 
		{
			userCompetence.setIsMeeting(isMeetingField.getText());
		}
		
		return userCompetence;
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
			int gridx, int gridy, int ipadx, int ipady, int gridwidth, int gridheight) {
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

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		corpIdField.setText("");
		userAccountField.setText("");
		userTypeField.setText("");
		isActiveField.setText("");
		adminTypeField.setText("");
		roleNameField.setText("");
		emailAddrField.setText("");
		pwdField.setText("");
		nameField.setText("");
		deptCodeField.setText("");
		deptNameField.setText("");
		titleField.setText("");
		mobilePhoneField.setText("");
		officePhoneField.setText("");
		addressField.setText("");
		webSiteField.setText("");
		startNumField.setText("");
		endNumField.setText("");
		customOrderField.setText("");
		accessLevelNameField.setText("");
		accessLevelField.setText("");
		isMeetingField.setText("");
		personRoleNameField.setText("");
		aliasField.setText("");
		faxField.setText("");
		homePhoneField.setText("");
		zipCodeField.setText("");
		otherPhoneField.setText("");
		otherPhone2Field.setText("");
	}
	
}
