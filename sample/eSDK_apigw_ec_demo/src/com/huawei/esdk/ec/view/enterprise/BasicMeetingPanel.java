package com.huawei.esdk.ec.view.enterprise;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
import com.huawei.esdk.ec.bean.enterprise.AddressType;
import com.huawei.esdk.ec.bean.enterprise.ApplyOrReleaseChairmanBean;
import com.huawei.esdk.ec.bean.enterprise.Attendee;
import com.huawei.esdk.ec.bean.enterprise.DurationBean;
import com.huawei.esdk.ec.bean.enterprise.HandsStateBean;
import com.huawei.esdk.ec.bean.enterprise.InvitingParticipantsBean;
import com.huawei.esdk.ec.bean.enterprise.IsDeafBean;
import com.huawei.esdk.ec.bean.enterprise.IsLockBean;
import com.huawei.esdk.ec.bean.enterprise.IsMuteBean;
import com.huawei.esdk.ec.bean.enterprise.OperationBean;
import com.huawei.esdk.ec.constant.HTTPConstant;
import com.huawei.esdk.ec.service.EcService;
import com.huawei.esdk.ec.utils.LoginUtils;
import com.huawei.esdk.ec.utils.MeetingTokenUtils;
import com.huawei.esdk.ec.utils.Properties_language_Utils;
import com.huawei.esdk.ec.utils.StringUtils;
import com.huawei.esdk.ec.view.common.LogPanel;
import com.huawei.esdk.ec.view.common.MyButton;

/**
 * 基本会控的视图
 * Basic conference view
 * @author wWX534262
 *
 */
@SuppressWarnings("all")
public class BasicMeetingPanel extends JPanel
{
	private static final long serialVersionUID = -4759701901496585644L;
	
	private static final Logger LOGGER = LogManager.getLogger(BasicMeetingPanel.class);
	
	private JLabel reqParams = new JLabel(Properties_language_Utils.getValue("reqParams"));
	
	private JLabel errInfoLabel = new JLabel();
	
	private JLabel conferenceidLabel = new JLabel("conferenceid:");
	private JTextField conferenceidField = new JTextField(10);
	
	private JLabel participantIDLabel = new JLabel("participantID:");
	private JTextField participantIDField = new JTextField(10);
	
	private JLabel nameLabel = new JLabel("name:");
	private JTextField nameField = new JTextField(10);
	
	private JLabel roleLabel = new JLabel("role:");
	private JTextField roleField = new JTextField(10);
	
	private JLabel phoneLabel = new JLabel("phone:");
	private JTextField phoneField = new JTextField(10);
	
	private JLabel emailLabel = new JLabel("email:");
	private JTextField emailField = new JTextField(10);
	
	private JLabel smsLabel = new JLabel("sms:");
	private JTextField smsField = new JTextField(10);
	
	private JLabel typeLabel = new JLabel("type:");
	private JTextField typeField = new JTextField(10);
	
	private JLabel numberLabel = new JLabel("number:");
	private JTextField numberField = new JTextField(10);
	
	private JLabel applyChairLabel = new JLabel("applyChair:");
	private JTextField applyChairField = new JTextField(10);
	
	private JLabel chairmanPwdLabel = new JLabel("chairmanPwd:");
	private JPasswordField chairmanPwdField = new JPasswordField(10);
	
	private JLabel handsStateLabel = new JLabel("handsState:");
	private JTextField handsStateField = new JTextField(10);
	
	private JLabel isMuteLabel = new JLabel("isMute:");
	private JTextField isMuteField = new JTextField(10);
	
	private JLabel isDeafLabel = new JLabel("isDeaf:");
	private JTextField isDeafField = new JTextField(10);
	
	private JLabel isLockLabel = new JLabel("isLock:");
	private JTextField isLockField = new JTextField(10);
	
	private JLabel operationLabel = new JLabel("operation:");
	private JTextField operationField = new JTextField(10);
	
	private JLabel durationLabel = new JLabel("duration:");
	private JTextField durationField = new JTextField(10);
	
	private JLabel descLabel = new JLabel(Properties_language_Utils.
			getValue("enter.BasicMeetingPanel.descLabel"));
	
	private JButton addAttendeesBtn = new MyButton(Properties_language_Utils.
			getValue("enter.BasicMeetingPanel.addAttendeesBtn"),230,35);
	
	private JButton invitingParticipantsBtn = new MyButton(Properties_language_Utils.
			getValue("enter.BasicMeetingPanel.invitingParticipantsBtn"),230,35);
	
	private JButton participantsLeaveBtn = new MyButton(Properties_language_Utils.
			getValue("enter.BasicMeetingPanel.participantsLeaveBtn"),230,35);
	
	private JButton deleParticipantsBtn = new MyButton(Properties_language_Utils.
			getValue("enter.BasicMeetingPanel.deleParticipantsBtn"),230,35);
	
	private JButton applyOrReleaseChairmanBtn = new MyButton(Properties_language_Utils.
			getValue("enter.BasicMeetingPanel.applyOrReleaseChairmanBtn"),230,35);
	
	private JButton putHandsUpOrDownBtn = new MyButton(Properties_language_Utils.
			getValue("enter.BasicMeetingPanel.putHandsUpOrDownBtn"),230,35);
	
	private JButton closeOrCancleVoiceBtn = new MyButton(Properties_language_Utils.
			getValue("enter.BasicMeetingPanel.closeOrCancleVoiceBtn"),230,35);
	
	private JButton muteOrCancleBtn = new MyButton(Properties_language_Utils.
			getValue("enter.BasicMeetingPanel.muteOrCancleBtn"),230,35);
	
	private JButton allCloseOrCancleVoiceBtn = new MyButton(Properties_language_Utils.
			getValue("enter.BasicMeetingPanel.allCloseOrCancleVoiceBtn"),230,35);
	
	private JButton lockOrUnlockMeetingBtn = new MyButton(Properties_language_Utils.
			getValue("enter.BasicMeetingPanel.lockOrUnlockMeetingBtn"),230,35);
	
	private JButton startOrEndMeetingBtn = new MyButton(Properties_language_Utils.
			getValue("enter.BasicMeetingPanel.startOrEndMeetingBtn"),230,35);
	
	private JButton extendMeetingBtn = new MyButton(Properties_language_Utils.
			getValue("enter.BasicMeetingPanel.extendMeetingBtn"),230,35);
	
	private List<Attendee> attendees = new ArrayList<Attendee>();
	

	
	public BasicMeetingPanel() 
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
		
		buildPanel(panel, gridbag, c, new JComponent[] {conferenceidLabel}, 0, 1, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {conferenceidField}, 1, 1, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {participantIDLabel}, 2, 1, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {participantIDField}, 3, 1, 6, 20, 1, 1);
		
		buildPanel(panel, gridbag, c, new JComponent[] {nameLabel}, 0, 2, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {nameField}, 1, 2, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {roleLabel}, 2, 2, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {roleField}, 3, 2, 6, 20, 1, 1);
		
		buildPanel(panel, gridbag, c, new JComponent[] {phoneLabel}, 0, 3, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {phoneField}, 1, 3, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {emailLabel}, 2, 3, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {emailField}, 3, 3, 6, 20, 1, 1);
		
		buildPanel(panel, gridbag, c, new JComponent[] {smsLabel}, 0, 4, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {smsField}, 1, 4, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {typeLabel}, 2, 4, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {typeField}, 3, 4, 6, 20, 1, 1);
		
		buildPanel(panel, gridbag, c, new JComponent[] {numberLabel}, 0, 5, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {numberField}, 1, 5, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {applyChairLabel}, 2, 5, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {applyChairField}, 3, 5, 6, 20, 1, 1);
		
		buildPanel(panel, gridbag, c, new JComponent[] {chairmanPwdLabel}, 0, 6, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {chairmanPwdField}, 1, 6, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {handsStateLabel}, 2, 6, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {handsStateField}, 3, 6, 6, 20, 1, 1);
		
		buildPanel(panel, gridbag, c, new JComponent[] {isMuteLabel}, 0, 7, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {isMuteField}, 1, 7, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {isDeafLabel}, 2, 7, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {isDeafField}, 3, 7, 6, 20, 1, 1);
		
		buildPanel(panel, gridbag, c, new JComponent[] {isLockLabel}, 0, 8, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {isLockField}, 1, 8, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {operationLabel}, 2, 8, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {operationField}, 3, 8, 6, 20, 1, 1);
		
		buildPanel(panel, gridbag, c, new JComponent[] {durationLabel}, 0, 9, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {durationField}, 1, 9, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {errInfoLabel}, 2, 9, 10, 20, 2, 1);
		
		buildPanel(panel, gridbag, c, new JComponent[] {descLabel}, 0, 10, 10, 20, 6, 1);
		
		
		buildPanel(panel, gridbag, c, new JComponent[] {addAttendeesBtn}, 0, 11, 10, 20, 2, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {invitingParticipantsBtn}, 2, 11, 10, 20, 2, 1);
		
		buildPanel(panel, gridbag, c, new JComponent[] {participantsLeaveBtn}, 0, 12, 10, 20, 2, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {deleParticipantsBtn}, 2, 12, 6, 20, 2, 1);
		
		buildPanel(panel, gridbag, c, new JComponent[] {applyOrReleaseChairmanBtn}, 0, 13, 10, 20, 2, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {putHandsUpOrDownBtn}, 2, 13, 10, 20, 2, 1);
		
		buildPanel(panel, gridbag, c, new JComponent[] {closeOrCancleVoiceBtn}, 0, 14, 10, 20, 2, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {muteOrCancleBtn}, 2, 14, 6, 20, 2, 1);
		
		buildPanel(panel, gridbag, c, new JComponent[] {allCloseOrCancleVoiceBtn}, 0, 15, 10, 20, 2, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {lockOrUnlockMeetingBtn}, 2, 15, 10, 20, 2, 1);
		
		buildPanel(panel, gridbag, c, new JComponent[] {startOrEndMeetingBtn}, 0, 16, 10, 20, 2, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {extendMeetingBtn}, 2, 16, 6, 20, 2, 1);
		
		//报文位置
		//message location
		buildPanel(panel, gridbag, c, new JComponent[] {new LogPanel()}, 4, 0, 10, 20, 4, 10);
		
		return panel;
	}
	
	private void init() 
	{
		descLabel.setForeground(Color.BLUE);
		
		//与会者累加按钮事件
		//attendee accumulate button events
		this.addAttendeesBtn.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e) 
			{
				showErrInfoWithColor("");
				boolean flag = validateParams(new JTextField[] {phoneField}, 
						new String[] {"phone"});
				 
				if (!flag)
	            {
					return;
	            }
				
				Runnable runnable = new Runnable()
	            {
	            	@Override
	            	public void run()
	            	{
	            		attendees.add(addAttendees());
	            		
	            		showErrInfoWithColor(Properties_language_Utils.getValue("enter.BasicMeetingPanel.addAttendeesTip"));
	            	}
	            };
	            Future future = Executors.newSingleThreadExecutor().submit(runnable);
	            if(!future.isDone()) {
	            	LOGGER.error("addMouseListener fail");
	            }
			}
		});
		
		//邀请与会者按钮事件
		//Invite Attendee Button Event
		this.invitingParticipantsBtn.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e) 
			{
				showErrInfoWithColor("");
				boolean flag = validateParams(new JTextField[] {conferenceidField}, 
						new String[] {"conferenceid"});
				 
				if (!flag || attendees.size() == 0)
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
	            		invitingParticipants();
	            	}
	            };
	            Future future = Executors.newSingleThreadExecutor().submit(runnable);
	            if(!future.isDone()) {
	            	LOGGER.error("addMouseListener fail");
	            }
			}
		});
		
		//与会者离开会议按钮事件
		//Participants leave meeting button event
		this.participantsLeaveBtn.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e) 
			{
				showErrInfoWithColor("");
				boolean flag = validateParams(new JTextField[] {conferenceidField, participantIDField}, 
						new String[] {"conferenceid","participantID"});
				 
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
	            		participantsLeave();
	            	}
	            };
	            Future future = Executors.newSingleThreadExecutor().submit(runnable);
	            if(!future.isDone()) {
	            	LOGGER.error("addMouseListener fail");
	            }
			}
		});
		
		//删除与会者按钮事件
		//Delete participant button event
		this.deleParticipantsBtn.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e) 
			{
				showErrInfoWithColor("");
				boolean flag = validateParams(new JTextField[] {conferenceidField, numberField}, 
						new String[] {"conferenceid","number"});
				 
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
	            		deleParticipants();
	            	}
	            };
	            Future future = Executors.newSingleThreadExecutor().submit(runnable);
	            if(!future.isDone()) {
	            	LOGGER.error("addMouseListener fail");
	            }
			}
		});
		
		//申请或释放主席按钮事件
		//Apply for or release a chair button event
		this.applyOrReleaseChairmanBtn.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e) 
			{
				showErrInfoWithColor("");
				boolean flag = validateParams(new JTextField[] {conferenceidField, participantIDField, applyChairField}, 
						new String[] {"conferenceid","participantID", "applyChair"});
				 
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
	            		applyOrReleaseChairman();
	            	}
	            };
	            Future future = Executors.newSingleThreadExecutor().submit(runnable);
	            if(!future.isDone()) {
	            	LOGGER.error("addMouseListener fail");
	            }
			}
		});
		
		//举手或者取消举手按钮事件
		//Raise a hand or cancel a raise hand button event
		this.putHandsUpOrDownBtn.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e) 
			{
				showErrInfoWithColor("");
				boolean flag = validateParams(new JTextField[] {conferenceidField, participantIDField, handsStateField}, 
						new String[] {"conferenceid","participantID", "handsState"});
				 
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
	            		putHandsUpOrDown();
	            	}
	            };
	            Future future = Executors.newSingleThreadExecutor().submit(runnable);
	            if(!future.isDone()) {
	            	LOGGER.error("addMouseListener fail");
	            }
			}
		});
		
		//闭音或取消闭音按钮事件 
		//Close sound or cancel mute button event
		this.closeOrCancleVoiceBtn.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e) 
			{
				showErrInfoWithColor("");
				boolean flag = validateParams(new JTextField[] {conferenceidField, participantIDField, isMuteField}, 
						new String[] {"conferenceid","participantID", "isMute"});
				 
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
	            		closeOrCancleVoice();
	            	}
	            };
	            Future future = Executors.newSingleThreadExecutor().submit(runnable);
	            if(!future.isDone()) {
	            	LOGGER.error("addMouseListener fail");
	            }
			}
		});
		
		//静音或取消静音按钮事件
		//Mute or unmute button event
		this.muteOrCancleBtn.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e) 
			{
				showErrInfoWithColor("");
				boolean flag = validateParams(new JTextField[] {conferenceidField, participantIDField, isDeafField}, 
						new String[] {"conferenceid","participantID", "isDeaf"});
				 
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
	            		muteOrCancle();
	            	}
	            };
	            Future future = Executors.newSingleThreadExecutor().submit(runnable);
	            if(!future.isDone()) {
	            	LOGGER.error("addMouseListener fail");
	            }
			}
		});
		
		//全场闭音或者取消全场闭音按钮事件
		//The audience muted or canceled the audience closed button event
		this.allCloseOrCancleVoiceBtn.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e) 
			{
				showErrInfoWithColor("");
				boolean flag = validateParams(new JTextField[] {conferenceidField, isMuteField}, 
						new String[] {"conferenceid", "isMute"});
				 
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
	            		allCloseOrCancleVoice();
	            	}
	            };
	            Future future = Executors.newSingleThreadExecutor().submit(runnable);
	            if(!future.isDone()) {
	            	LOGGER.error("addMouseListener fail");
	            }
			}
		});
		
		//锁定或者解锁会议按钮事件
		//Lock or unlock conference button events
		this.lockOrUnlockMeetingBtn.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e) 
			{
				showErrInfoWithColor("");
				boolean flag = validateParams(new JTextField[] {conferenceidField, isLockField}, 
						new String[] {"conferenceid", "isLock"});
				 
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
	            		lockOrUnlockMeeting();
	            	}
	            };
	            Future future = Executors.newSingleThreadExecutor().submit(runnable);
	            if(!future.isDone()) {
	            	LOGGER.error("addMouseListener fail");
	            }
			}
		});
		
		//开始或者结束会议
		//Start or end the meeting
		this.startOrEndMeetingBtn.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e) 
			{
				showErrInfoWithColor("");
				boolean flag = validateParams(new JTextField[] {conferenceidField, operationField}, 
						new String[] {"conferenceid", "operation"});
				 
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
	            		startOrEndMeeting();
	            	}
	            };
	            Future future = Executors.newSingleThreadExecutor().submit(runnable);
	            if(!future.isDone()) {
	            	LOGGER.error("addMouseListener fail");
	            }
			}
		});
		
		//延长会议按钮事件
		//Extend conference button events
		this.extendMeetingBtn.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e) 
			{
				showErrInfoWithColor("");
				boolean flag = validateParams(new JTextField[] {conferenceidField, durationField}, 
						new String[] {"conferenceid", "duration"});
				 
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
	            		extendMeeting();
	            	}
	            };
	            Future future = Executors.newSingleThreadExecutor().submit(runnable);
	            if(!future.isDone()) {
	            	LOGGER.error("addMouseListener fail");
	            }
			}
		});
	}
	
	//延长会议
	//Extend the meeting
	private void extendMeeting()
	{
		try 
		{
			Token token = LoginUtils.getToken();
			
			// 利用token发送restful请求
			// Send a restful request using token
			RestRequest request = new RestRequest(HTTPConstant.HTTP_METHOD_PUT);
			
			Map<String, String> httpHeaders = request.getHttpHeaders();
			httpHeaders.put("Conference-Authorization", MeetingTokenUtils.getMeetingToken());
			DurationBean durationBean = new DurationBean();
			if (StringUtils.isNotEmpty(durationField.getText())) 
			{
				durationBean.setDuration(Integer.parseInt(durationField.getText()));
			}
			
			request.setPayload(durationBean);
			
			EcService.put("/conferences/" + conferenceidField.getText() + "/duration", 
					request, errInfoLabel, token);
			
			EcService.finish();
			
		} 
		catch (RuntimeException e) 
		{
			LOGGER.error("get token error");
			showErrInfoWithColor("操作失败");
		}
		catch (Exception e) 
		{
			LOGGER.error("get token error");
			showErrInfoWithColor(Properties_language_Utils.getValue("resultTip"));
		}
	}
	
	//开始或者结束会议
	//Start or end the meeting
	private void startOrEndMeeting() 
	{
		try 
		{
			Token token = LoginUtils.getToken();
			
			// 利用token发送restful请求
			// Send a restful request using token
			RestRequest request = new RestRequest(HTTPConstant.HTTP_METHOD_PUT);
			
			Map<String, String> httpHeaders = request.getHttpHeaders();
			httpHeaders.put("Conference-Authorization", MeetingTokenUtils.getMeetingToken());
			
			OperationBean operationBean = new OperationBean();
			operationBean.setOperation(Integer.parseInt(operationField.getText()));
			
			request.setPayload(operationBean);
			
			EcService.put("/conferences/" + conferenceidField.getText() + "/state", 
					request, errInfoLabel, token);
			
			EcService.finish();
			
		} 
		catch (RuntimeException e) 
		{
			LOGGER.error("get token error");
			showErrInfoWithColor("操作失败");
		}
		catch (Exception e) 
		{
			LOGGER.error("get token error");
			showErrInfoWithColor(Properties_language_Utils.getValue("resultTip"));
		}
	}
	
	//锁定或者解锁会议
	//Lock or unlock the meeting
	private void lockOrUnlockMeeting() 
	{
		try 
		{
			Token token = LoginUtils.getToken();
			
			// 利用token发送restful请求
			// Send a restful request using token
			RestRequest request = new RestRequest(HTTPConstant.HTTP_METHOD_PUT);
			
			Map<String, String> httpHeaders = request.getHttpHeaders();
			httpHeaders.put("Conference-Authorization", MeetingTokenUtils.getMeetingToken());
			
			IsLockBean isLockBean = new IsLockBean();
			isLockBean.setIsLock(Integer.parseInt(isLockField.getText()));
			
			request.setPayload(isLockBean);
			
			EcService.put("/conferences/" + conferenceidField.getText() + "/lock", 
					request, errInfoLabel, token);
			
			EcService.finish();
			
		} 
		catch (RuntimeException e) 
		{
			LOGGER.error("get token error");
			showErrInfoWithColor("操作失败");
		}
		catch (Exception e) 
		{
			LOGGER.error("get token error");
			showErrInfoWithColor(Properties_language_Utils.getValue("resultTip"));
		}
	}
	
	//全场闭音或者取消全场闭音
	//The audience closed or canceled the audience
	private void allCloseOrCancleVoice() 
	{
		try 
		{
			Token token = LoginUtils.getToken();
			
			// 利用token发送restful请求
			// Send a restful request using token
			RestRequest request = new RestRequest(HTTPConstant.HTTP_METHOD_PUT);
			
			Map<String, String> httpHeaders = request.getHttpHeaders();
			httpHeaders.put("Conference-Authorization", MeetingTokenUtils.getMeetingToken());
			
			IsMuteBean isMuteBean = new IsMuteBean();
			isMuteBean.setIsMute(Integer.parseInt(isMuteField.getText()));
			
			request.setPayload(isMuteBean);
			
			EcService.put("/conferences/" + conferenceidField.getText() + "/mute", 
					request, errInfoLabel, token);
			
			EcService.finish();
			
		} 
		catch (RuntimeException e) 
		{
			LOGGER.error("get token error");
			showErrInfoWithColor("操作失败");
		}
		catch (Exception e) 
		{
			LOGGER.error("get token error");
			showErrInfoWithColor(Properties_language_Utils.getValue("resultTip"));
		}
	}
	
	//静音或取消静音
	//Mute or unmute
	private void muteOrCancle() 
	{
		try 
		{
			Token token = LoginUtils.getToken();
			
			// 利用token发送restful请求
			// Send a restful request using token
			RestRequest request = new RestRequest(HTTPConstant.HTTP_METHOD_PUT);
			
			Map<String, String> httpHeaders = request.getHttpHeaders();
			httpHeaders.put("Conference-Authorization", MeetingTokenUtils.getMeetingToken());
			
			IsDeafBean isDeafBean = new IsDeafBean();
			isDeafBean.setIsDeaf(Integer.parseInt(isDeafField.getText()));
			
			request.setPayload(isDeafBean);
			
			String participantID = participantIDField.getText();
			if (participantID.contains("#")) 
			{
				participantID = participantIDField.getText().replaceAll("#", "%23");
			}
			
			if (participantID.contains("@")) 
			{
				participantID = participantID.replace("@", "%40");
			}
			
			EcService.put("/conferences/" + conferenceidField.getText() + "/participants/" + participantID + "/deaf", 
					request, errInfoLabel, token);
			
			EcService.finish();
			
		} 
		catch (RuntimeException e) 
		{
			LOGGER.error("get token error");
			showErrInfoWithColor("操作失败");
		}
		catch (Exception e) 
		{
			LOGGER.error("get token error");
			showErrInfoWithColor(Properties_language_Utils.getValue("resultTip"));
		}
	}
	
	//闭音或取消闭音
	//Closed sound or cancle
	private void closeOrCancleVoice() 
	{
		try 
		{
			Token token = LoginUtils.getToken();
			
			// 利用token发送restful请求
			// Send a restful request using token
			RestRequest request = new RestRequest(HTTPConstant.HTTP_METHOD_PUT);
			
			Map<String, String> httpHeaders = request.getHttpHeaders();
			httpHeaders.put("Conference-Authorization", MeetingTokenUtils.getMeetingToken());
			
			IsMuteBean isMuteBean = new IsMuteBean();
			isMuteBean.setIsMute(Integer.parseInt(isMuteField.getText()));
			
			request.setPayload(isMuteBean);
			
			String participantID = participantIDField.getText();
			if (participantID.contains("#")) 
			{
				participantID = participantIDField.getText().replaceAll("#", "%23");
			}
			
			if (participantID.contains("@")) 
			{
				participantID = participantID.replace("@", "%40");
			}
			
			EcService.put("/conferences/" + conferenceidField.getText() + "/participants/" + participantID + "/mute", 
					request, errInfoLabel, token);
			
			EcService.finish();
			
		} 
		catch (RuntimeException e) 
		{
			LOGGER.error("get token error");
			showErrInfoWithColor("操作失败");
		}
		catch (Exception e) 
		{
			LOGGER.error("get token error");
			showErrInfoWithColor(Properties_language_Utils.getValue("resultTip"));
		}
	}
	
	//举手或取消举手
	//Raise your hand or lift your hand
	private void putHandsUpOrDown() 
	{
		try 
		{
			Token token = LoginUtils.getToken();
			
			// 利用token发送restful请求
			// Send a restful request using token
			RestRequest request = new RestRequest(HTTPConstant.HTTP_METHOD_PUT);
			
			Map<String, String> httpHeaders = request.getHttpHeaders();
			httpHeaders.put("Conference-Authorization", MeetingTokenUtils.getMeetingToken());
			
			HandsStateBean handsStateBean = new HandsStateBean();
			
			handsStateBean.setHandsState(handsStateField.getText());
			
			request.setPayload(handsStateBean);
			
			String participantID = participantIDField.getText();
			if (participantID.contains("#")) 
			{
				participantID = participantIDField.getText().replaceAll("#", "%23");
				
			}
			
			if (participantID.contains("@")) 
			{
				participantID = participantID.replace("@", "%40");
			}
			
			EcService.post("/conferences/" + conferenceidField.getText() + "/participants/" + participantID + "/hands",
					request, errInfoLabel, token);
			
			EcService.finish();
			
		} 
		catch (RuntimeException e) 
		{
			LOGGER.error("get token error");
			showErrInfoWithColor("操作失败");
		}
		catch (Exception e) 
		{
			LOGGER.error("get token error");
			showErrInfoWithColor(Properties_language_Utils.getValue("resultTip"));
		}
	}
	
	//申请或释放主席
	//Apply for or release the chair
	private void applyOrReleaseChairman()
	{
		try 
		{
			Token token = LoginUtils.getToken();
			
			// 利用token发送restful请求
			// Send a restful request using token
			RestRequest request = new RestRequest(HTTPConstant.HTTP_METHOD_PUT);
			
			Map<String, String> httpHeaders = request.getHttpHeaders();
			httpHeaders.put("Conference-Authorization", MeetingTokenUtils.getMeetingToken());
			
			ApplyOrReleaseChairmanBean applyOrReleaseChairmanBean = new ApplyOrReleaseChairmanBean();
			applyOrReleaseChairmanBean.setApplyChair(Integer.parseInt(applyChairField.getText()));
			if (StringUtils.isNotEmpty(new String(chairmanPwdField.getPassword())) 
					&& null != new String(chairmanPwdField.getPassword()))
			{
				applyOrReleaseChairmanBean.setChairmanPwd(new String(chairmanPwdField.getPassword()));
			}
			
			request.setPayload(applyOrReleaseChairmanBean);
			
			String participantID = participantIDField.getText();
			if (participantID.contains("#")) 
			{
				participantID = participantIDField.getText().replaceAll("#", "%23");
			}
			
			if (participantID.contains("@")) 
			{
				participantID = participantID.replace("@", "%40");
			}
			
			EcService.put("/conferences/" + conferenceidField.getText() + "/participants/" + participantID + "/role",
					request, errInfoLabel, token);
			
			EcService.finish();
			
			applyOrReleaseChairmanBean.setChairmanPwd(null);
			
		} 
		catch (RuntimeException e)
		{
			LOGGER.error("get token error");
			showErrInfoWithColor("操作失败");
		}
		catch (Exception e)
		{
			LOGGER.error("get token error");
			showErrInfoWithColor(Properties_language_Utils.getValue("resultTip"));
		}
	}
	
	//删除与会者
	//Remove participants
	private void deleParticipants() 
	{
		try 
		{
			Token token = LoginUtils.getToken();
			
			// 利用token发送restful请求
			// Send a restful request using token
			RestRequest request = new RestRequest(HTTPConstant.HTTP_METHOD_DELETE);
			
			Map<String, String> httpHeaders = request.getHttpHeaders();
			httpHeaders.put("Conference-Authorization", MeetingTokenUtils.getMeetingToken());
			
			String participantID = participantIDField.getText();
			if (participantID.contains("#")) 
			{
				participantID = participantIDField.getText().replaceAll("#", "%23");
			}
			
			if (participantID.contains("@")) 
			{
				participantID = participantID.replace("@", "%40");
			}
			EcService.delete("/conferences/" + conferenceidField.getText() + "/participants/" + participantID, 
					request, errInfoLabel, token);
			
			EcService.finish();
		} 
		catch (RuntimeException e) 
		{
			LOGGER.error("get token error");
			showErrInfoWithColor("操作失败");
		}
		catch (Exception e) 
		{
			LOGGER.error("get token error");
			showErrInfoWithColor(Properties_language_Utils.getValue("resultTip"));
		}
	}
	
	//与会者离开会议
	//Participants leave the meeting
	private void participantsLeave() 
	{
		try 
		{
			Token token = LoginUtils.getToken();
			
			// 利用token发送restful请求
			// Send a restful request using token
			RestRequest request = new RestRequest(HTTPConstant.HTTP_METHOD_DELETE);
			
			Map<String, String> httpHeaders = request.getHttpHeaders();
			httpHeaders.put("Conference-Authorization", MeetingTokenUtils.getMeetingToken());
			
			String participantID = participantIDField.getText();
			if (participantID.contains("#")) 
			{
				participantID = participantIDField.getText().replaceAll("#", "%23");
			}
			
			if (participantID.contains("@")) 
			{
				participantID = participantID.replace("@", "%40");
			}
			
			EcService.delete("/conferences/" + conferenceidField.getText() + "/participants/" + participantID + "/status", 
					request, errInfoLabel, token);
			
			EcService.finish();
			
		}
		catch (RuntimeException e) 
		{
			LOGGER.error("get token error");
			showErrInfoWithColor("操作失败");
		}
		catch (Exception e) 
		{
			LOGGER.error("get token error");
			showErrInfoWithColor(Properties_language_Utils.getValue("resultTip"));
		}
	}
	
	//邀请与会者
	//Invite attendees
	private void invitingParticipants() 
	{
		try 
		{
			Token token = LoginUtils.getToken();
			
			// 利用token发送restful请求
			// Send a restful request using token
			RestRequest request = new RestRequest(HTTPConstant.HTTP_METHOD_POST);
			
			Map<String, String> httpHeaders = request.getHttpHeaders();
			httpHeaders.put("Conference-Authorization", MeetingTokenUtils.getMeetingToken());
			
			InvitingParticipantsBean invitingParticipantsBean = new InvitingParticipantsBean();
			invitingParticipantsBean.setAttendees(attendees);
			request.setPayload(invitingParticipantsBean);
			
			EcService.post("/conferences/" + conferenceidField.getText() + "/participants", request, errInfoLabel, token);
			
			EcService.finish();
			
		} 
		catch (RuntimeException e) 
		{
			LOGGER.error("get token error");
			showErrInfoWithColor("操作失败");
		}
		catch (Exception e) 
		{
			LOGGER.error("get token error");
			showErrInfoWithColor(Properties_language_Utils.getValue("resultTip"));
		}
	}
	
	//设置与会者参数
	//Set attendee parameters
	private Attendee addAttendees() 
	{
		Attendee atted = new Attendee();
		if (StringUtils.isNotEmpty(nameField.getText())) 
		{
			atted.setName(nameField.getText());
		}
		if (StringUtils.isNotEmpty(roleField.getText())) 
		{
			atted.setRole(roleField.getText());
		}
		
		atted.setPhone(phoneField.getText());
		
		if (StringUtils.isNotEmpty(emailField.getText())) 
		{
			atted.setEmail(emailField.getText());
		}
		if (StringUtils.isNotEmpty(smsField.getText())) 
		{
			atted.setSms(smsField.getText());
		}
		if (StringUtils.isNotEmpty(isMuteField.getText())) 
		{
			atted.setIsMute(Integer.parseInt(isMuteField.getText()));
		}
		if (StringUtils.isNotEmpty(typeField.getText())) 
		{
			atted.setType(AddressType.valueOf(typeField.getText()));
		}
		
		atted.setIsAutoInvite(null);
		
		return atted;
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
