package com.huawei.esdk.ec.demo;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import com.huawei.esdk.ec.utils.Properties_language_Utils;
import com.huawei.esdk.ec.view.admin.SpNumPanel;
import com.huawei.esdk.ec.view.admin.SysnumPanel;
import com.huawei.esdk.ec.view.admin.AccesscodePanel;
import com.huawei.esdk.ec.view.admin.SpManagePanel;
import com.huawei.esdk.ec.view.admin.SpManagerPanel;
import com.huawei.esdk.ec.view.common.LanguagePanel;
import com.huawei.esdk.ec.view.common.LoginPanel;
import com.huawei.esdk.ec.view.enterprise.AccesscodeBindShortNumPanel;
import com.huawei.esdk.ec.view.enterprise.BasicMeetingPanel;
import com.huawei.esdk.ec.view.enterprise.CTDPanel;
import com.huawei.esdk.ec.view.enterprise.ConferenceAuthenticationPanel;
import com.huawei.esdk.ec.view.enterprise.ConferenceManagerPanel;
import com.huawei.esdk.ec.view.enterprise.CorpManagerPanel;
import com.huawei.esdk.ec.view.enterprise.CorpMemberPanel;
import com.huawei.esdk.ec.view.enterprise.CorpNumPanel;
import com.huawei.esdk.ec.view.enterprise.DepartmentPanel;
import com.huawei.esdk.ec.view.enterprise.DeviceManagementPanel;
import com.huawei.esdk.ec.view.enterprise.IcxpfxPanel;
import com.huawei.esdk.ec.view.enterprise.MemberLevelPanel;
import com.huawei.esdk.ec.view.enterprise.OcxpfxPanel;
import com.huawei.esdk.ec.view.enterprise.QueryUCListPresence;
import com.huawei.esdk.ec.view.enterprise.RecordlistPanel;
import com.huawei.esdk.ec.view.enterprise.ResetPasswordPanel;
import com.huawei.esdk.ec.view.enterprise.SendIMsgPanel;
import com.huawei.esdk.ec.view.enterprise.StateSubscriptionPanel;
import com.huawei.esdk.ec.view.enterprise.UcRolePanel;
import com.huawei.esdk.ec.view.enterprise.UserpackManagePanel;
import com.huawei.esdk.ec.view.enterprise.VideoConferencePanel;
import com.huawei.esdk.ec.view.sp.AccesscodeSpPanel;
import com.huawei.esdk.ec.view.sp.CorpManagementPanel;
import com.huawei.esdk.ec.view.sp.ServicePackPanel;


public class Runme extends JFrame
{
   
	private static final long serialVersionUID = -8796615467509878017L;
	
	public Runme()
    {
        /**
         * UI
         */
        super("CloudEC_Server_API_Demo_Overall_Java(HTTPS Only)");
        
        getContentPane().setLayout(new BorderLayout());
        JPanel north = new LoginPanel();
        JTabbedPane center = new JTabbedPane();
        
        center.setFont(new Font("宋体", Font.CENTER_BASELINE, 20));
        
        //adminTab
        JTabbedPane adminTab = new JTabbedPane();
        adminTab.setFont(new Font("宋体", Font.CENTER_BASELINE, 15));
        
        JScrollPane spNumJsp = new JScrollPane(new SpNumPanel());
        adminTab.addTab(Properties_language_Utils.getValue("Runme.adminTab.spNum"), null, spNumJsp, 
        		Properties_language_Utils.getValue("Runme.adminTab.spNum"));
        
        JScrollPane spManageJsp = new JScrollPane(new SpManagePanel());
        adminTab.addTab(Properties_language_Utils.getValue("Runme.adminTab.spManage"), null, spManageJsp, 
        		Properties_language_Utils.getValue("Runme.adminTab.spManage"));
        
        JScrollPane spManagerJsp = new JScrollPane(new SpManagerPanel());
        adminTab.addTab(Properties_language_Utils.getValue("Runme.adminTab.spManager"), null, spManagerJsp, 
        		Properties_language_Utils.getValue("Runme.adminTab.spManager"));
        
        JScrollPane sysnumJsp = new JScrollPane(new SysnumPanel());
        adminTab.addTab(Properties_language_Utils.getValue("Runme.adminTab.sysnum"), null, sysnumJsp, 
        		Properties_language_Utils.getValue("Runme.adminTab.sysnum"));
        
        JScrollPane accesscodeJsp = new JScrollPane(new AccesscodePanel());
        adminTab.addTab(Properties_language_Utils.getValue("Runme.adminTab.accesscode"), null, accesscodeJsp, 
        		Properties_language_Utils.getValue("Runme.adminTab.accesscode"));
        
        //spTab
        JTabbedPane spTab = new JTabbedPane();
        spTab.setFont(new Font("宋体", Font.CENTER_BASELINE, 15));
        
        JScrollPane corpManagementJsp = new JScrollPane(new CorpManagementPanel());
        spTab.addTab(Properties_language_Utils.getValue("Runme.spTab.corpManagement"), null, corpManagementJsp, 
        		Properties_language_Utils.getValue("Runme.spTab.corpManagement"));
        
        JScrollPane spNumJsp_sp = new JScrollPane(new com.huawei.esdk.ec.view.sp.SpNumPanel());
        spTab.addTab(Properties_language_Utils.getValue("Runme.spTab.spNum"), null, spNumJsp_sp, 
        		Properties_language_Utils.getValue("Runme.spTab.spNum"));
        
        JScrollPane servicePackJsp = new JScrollPane(new ServicePackPanel());
        spTab.addTab(Properties_language_Utils.getValue("Runme.spTab.servicePack"), null, servicePackJsp, 
        		Properties_language_Utils.getValue("Runme.spTab.servicePack"));
        
        JScrollPane accesscodeSpJsp = new JScrollPane(new AccesscodeSpPanel());
        spTab.addTab(Properties_language_Utils.getValue("Runme.spTab.accesscodeSp"), null, accesscodeSpJsp, 
        		Properties_language_Utils.getValue("Runme.spTab.accesscodeSp"));
        
        //enterpriseTab
        JTabbedPane enterpriseTab = new JTabbedPane();
        enterpriseTab.setFont(new Font("宋体", Font.CENTER_BASELINE, 15));
        
        JScrollPane corpNumJsp = new JScrollPane(new CorpNumPanel());
        enterpriseTab.addTab(Properties_language_Utils.getValue("Runme.enterTab.corpNum"), null, corpNumJsp, 
        		Properties_language_Utils.getValue("Runme.enterTab.corpNum"));
        
        JScrollPane corpMemberJsp = new JScrollPane(new CorpMemberPanel());
        enterpriseTab.addTab(Properties_language_Utils.getValue("Runme.enterTab.corpMember"), null, corpMemberJsp, 
        		Properties_language_Utils.getValue("Runme.enterTab.corpMember"));
        
        JScrollPane corpManagerJsp = new JScrollPane(new CorpManagerPanel());
        enterpriseTab.addTab(Properties_language_Utils.getValue("Runme.enterTab.corpManager"), null, corpManagerJsp, 
        		Properties_language_Utils.getValue("Runme.enterTab.corpManager"));
        
        JScrollPane deviceManagementJsp = new JScrollPane(new DeviceManagementPanel());
        enterpriseTab.addTab(Properties_language_Utils.getValue("Runme.enterTab.deviceManagement"), null, deviceManagementJsp, 
        		Properties_language_Utils.getValue("Runme.enterTab.deviceManagement"));
        
        JScrollPane resetPasswordJsp = new JScrollPane(new ResetPasswordPanel());
        enterpriseTab.addTab(Properties_language_Utils.getValue("Runme.enterTab.resetPassword"), null, resetPasswordJsp, 
        		Properties_language_Utils.getValue("Runme.enterTab.resetPassword"));
        
        JScrollPane departmentJsp = new JScrollPane(new DepartmentPanel());
        enterpriseTab.addTab(Properties_language_Utils.getValue("Runme.enterTab.department"), null, departmentJsp, 
        		Properties_language_Utils.getValue("Runme.enterTab.department"));
        
        JScrollPane sendIMsgJsp = new JScrollPane(new SendIMsgPanel());
        enterpriseTab.addTab(Properties_language_Utils.getValue("Runme.enterTab.sendIMsg"), null, sendIMsgJsp, 
        		Properties_language_Utils.getValue("Runme.enterTab.sendIMsg"));
        
        JScrollPane queryUCListJsp = new JScrollPane(new QueryUCListPresence());
        enterpriseTab.addTab(Properties_language_Utils.getValue("Runme.enterTab.queryUCList"), null, queryUCListJsp, 
        		Properties_language_Utils.getValue("Runme.enterTab.queryUCList"));
        
        JScrollPane ucRoleJsp = new JScrollPane(new UcRolePanel());
        enterpriseTab.addTab(Properties_language_Utils.getValue("Runme.enterTab.ucRole"), null, ucRoleJsp, 
        		Properties_language_Utils.getValue("Runme.enterTab.ucRole"));
        
        JScrollPane memberLevelJsp = new JScrollPane(new MemberLevelPanel());
        enterpriseTab.addTab(Properties_language_Utils.getValue("Runme.enterTab.memberLevel"), null, memberLevelJsp, 
        		Properties_language_Utils.getValue("Runme.enterTab.memberLevel"));
        
        JScrollPane OcxpfxJsp = new JScrollPane(new OcxpfxPanel());
        enterpriseTab.addTab(Properties_language_Utils.getValue("Runme.enterTab.Ocxpfx"), null, OcxpfxJsp, 
        		Properties_language_Utils.getValue("Runme.enterTab.Ocxpfx"));
        
        JScrollPane IcxpfxJsp = new JScrollPane(new IcxpfxPanel());
        enterpriseTab.addTab(Properties_language_Utils.getValue("Runme.enterTab.Icxpfx"), null, IcxpfxJsp, 
        		Properties_language_Utils.getValue("Runme.enterTab.Icxpfx"));
        
        JScrollPane accesscodeCorpJsp = new JScrollPane(new AccesscodeBindShortNumPanel());
        enterpriseTab.addTab(Properties_language_Utils.getValue("Runme.enterTab.accesscodeCorp"), null, accesscodeCorpJsp, 
        		Properties_language_Utils.getValue("Runme.enterTab.accesscodeCorp"));
        
        JScrollPane userpackManageJsp = new JScrollPane(new UserpackManagePanel());
        enterpriseTab.addTab(Properties_language_Utils.getValue("Runme.enterTab.userpackManage"), null, userpackManageJsp, 
        		Properties_language_Utils.getValue("Runme.enterTab.userpackManage"));
        //IPTTable
        JTabbedPane IPTTab = new JTabbedPane();
        IPTTab.setFont(new Font("宋体", Font.CENTER_BASELINE, 15));
        
        JScrollPane CTDJsp = new JScrollPane(new CTDPanel());
        IPTTab.addTab(Properties_language_Utils.getValue("Runme.IPTTab.CTD"), null, CTDJsp, 
        		Properties_language_Utils.getValue("Runme.IPTTab.CTD"));
        
        JScrollPane RecordlistJsp = new JScrollPane(new RecordlistPanel());
        IPTTab.addTab(Properties_language_Utils.getValue("Runme.IPTTab.Recordlist"), null, RecordlistJsp, 
        		Properties_language_Utils.getValue("Runme.IPTTab.Recordlist"));
        
        //conferenceTab
        JTabbedPane conferenceTab = new JTabbedPane();
        conferenceTab.setFont(new Font("宋体", Font.CENTER_BASELINE, 15));
        
        JScrollPane coferenceManagerJsp = new JScrollPane(new ConferenceManagerPanel());
        conferenceTab.addTab(Properties_language_Utils.getValue("Runme.conferenceTab.coferenceManager"), null, coferenceManagerJsp, 
        		Properties_language_Utils.getValue("Runme.conferenceTab.coferenceManager"));
        
        JScrollPane conferenceAuthenticationJsp = new JScrollPane(new ConferenceAuthenticationPanel());
        conferenceTab.addTab(Properties_language_Utils.getValue("Runme.conferenceTab.conferenceAuthentication"), null, 
        		conferenceAuthenticationJsp, Properties_language_Utils.getValue("Runme.conferenceTab.conferenceAuthentication"));
        
        JScrollPane basicMeetingPanelJsp = new JScrollPane(new BasicMeetingPanel());
        conferenceTab.addTab(Properties_language_Utils.getValue("Runme.conferenceTab.basicMeeting"), null, basicMeetingPanelJsp,
        		Properties_language_Utils.getValue("Runme.conferenceTab.basicMeeting"));
        
        JScrollPane videoConferencePanelJsp = new JScrollPane(new VideoConferencePanel());
        conferenceTab.addTab(Properties_language_Utils.getValue("Runme.conferenceTab.videoConference"), null, videoConferencePanelJsp, 
        		Properties_language_Utils.getValue("Runme.conferenceTab.videoConference"));
        
        JScrollPane stateSubscriptionPanelJsp = new JScrollPane(new StateSubscriptionPanel());
        conferenceTab.addTab(Properties_language_Utils.getValue("Runme.conferenceTab.stateSubscription"), null, 
        		stateSubscriptionPanelJsp, Properties_language_Utils.getValue("Runme.conferenceTab.stateSubscription"));
        
        center.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        center.addTab(Properties_language_Utils.getValue("Runme.center.adminTab"), null, adminTab, 
        		Properties_language_Utils.getValue("Runme.center.adminTab"));
        center.addTab(Properties_language_Utils.getValue("Runme.center.spTab"), null, spTab, 
        		Properties_language_Utils.getValue("Runme.center.spTab"));
        center.addTab(Properties_language_Utils.getValue("Runme.center.enterpriseTab"), null, enterpriseTab, 
        		Properties_language_Utils.getValue("Runme.center.enterpriseTab"));
        center.addTab(Properties_language_Utils.getValue("Runme.center.IPTTab"), null, IPTTab, 
        		Properties_language_Utils.getValue("Runme.center.IPTTab"));
        center.addTab(Properties_language_Utils.getValue("Runme.center.conferenceTab"), null, conferenceTab, 
        		Properties_language_Utils.getValue("Runme.center.conferenceTab"));
        
        getContentPane().add(north,BorderLayout.NORTH);
        getContentPane().add(center,BorderLayout.CENTER);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        
		setSize(1100,700);
    }
    
    public static void main(String[] args)
    {
    	JFrame frame = new JFrame("语言选择窗口/language frame");
    	JLabel label = new JLabel("请选择语言/please choose language");
    	label.setFont(new Font("宋体", Font.CENTER_BASELINE, 15));
    	
    	frame.setResizable(true);
    	
    	//解决findbugs SW_SWING_METHODS_INVOKED_IN_SWING_THREAD，防止锁冲突
    	setFrame(frame);
        frame.getContentPane().add(label, BorderLayout.CENTER);
        frame.getContentPane().add(new LanguagePanel(),BorderLayout.SOUTH);
        frame.setSize(315, 140);
    	frame.setLocationRelativeTo(null);
    }

	private synchronized static void setFrame(JFrame frame)
	{
		frame.setVisible(true);
	}
    
}
