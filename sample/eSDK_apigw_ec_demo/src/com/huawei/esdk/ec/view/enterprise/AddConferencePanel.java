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
import javax.swing.JTextField;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.huawei.esdk.ec.bean.common.RestRequest;
import com.huawei.esdk.ec.bean.common.Token;
import com.huawei.esdk.ec.bean.enterprise.AddressType;
import com.huawei.esdk.ec.bean.enterprise.Attendee;
import com.huawei.esdk.ec.bean.enterprise.CycleParams;
import com.huawei.esdk.ec.bean.enterprise.Reminder;
import com.huawei.esdk.ec.bean.enterprise.ScheduleMeetingBean;
import com.huawei.esdk.ec.bean.enterprise.WeekDayParam;
import com.huawei.esdk.ec.constant.HTTPConstant;
import com.huawei.esdk.ec.service.EcService;
import com.huawei.esdk.ec.utils.LoginUtils;
import com.huawei.esdk.ec.utils.Properties_language_Utils;
import com.huawei.esdk.ec.view.common.LogPanel;
import com.huawei.esdk.ec.view.common.MyButton;
import com.huawei.esdk.ec.utils.StringUtils;
/**
 * 预约会议视图
 * @author wWX534262
 *
 */
@SuppressWarnings("all")
public class AddConferencePanel extends JPanel implements ActionListener
{

	private static final long serialVersionUID = -6690404559732736822L;
	
	private static final Logger LOGGER = LogManager.getLogger(AddConferencePanel.class);
	
	private JLabel reqParams = new JLabel(Properties_language_Utils.getValue("reqParams"));
	
	private JLabel errInfoLabel = new JLabel();
	
	private JLabel conferenceidLabel = new JLabel("conferenceid:");
	private JTextField conferenceidField = new JTextField(10);
	
	//预定会议
	//Book a meeting
	private JLabel conferenceTypeLabel = new JLabel("conferenceType:");
	private JTextField conferenceTypeField = new JTextField(10);
	
	private JLabel sizeLabel = new JLabel("size:");
	private JTextField sizeField = new JTextField(10);
	
	private JLabel startTimeLabel = new JLabel("startTime:");
	private JTextField startTimeField = new JTextField(10);
	
	private JLabel lengthLabel = new JLabel("length:");
	private JTextField lengthField = new JTextField(10);
	
	private JLabel subjectLabel = new JLabel("subject:");
	private JTextField subjectField = new JTextField(10);
	
	private JLabel mediaTypesLabel = new JLabel("mediaTypes:");
	private JTextField mediaTypesField = new JTextField(10);
	
	private JLabel enterPromptLabel = new JLabel("enterPrompt:");
	private JTextField enterPromptField = new JTextField(10);
	
	private JLabel leavePromptLabel = new JLabel("leavePrompt:");
	private JTextField leavePromptField = new JTextField(10);
	
	private JLabel isAllowRecordLabel = new JLabel("isAllowRecord:");
	private JTextField isAllowRecordField = new JTextField(10);
	
	private JLabel isAutoRecordLabel = new JLabel("isAutoRecord:");
	private JTextField isAutoRecordField = new JTextField(10);
	
	//录播类型
	private JLabel recordTypeLabel = new JLabel("recordType:");
	private JTextField recordTypeField = new JTextField(10);
	
	//主流录播地址
	private JLabel liveAddressLabel = new JLabel("liveAddress:");
	private JTextField liveAddressField = new JTextField(10);
	
	//辅流录播地址
	private JLabel auxAddressLabel = new JLabel("auxAddress:");
	private JTextField auxAddressField = new JTextField(10);
	
	//是否录制辅流
	private JLabel recordAuxStreamLabel = new JLabel("recordAuxStream:");
	private JTextField recordAuxStreamField = new JTextField(10);
	
	//与会者列表
	//attendee
	private JLabel accountIdLabel = new JLabel("accountId:");
	private JTextField accountIdField = new JTextField(10);
	
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
	
	private JLabel descLabel = new JLabel(Properties_language_Utils.getValue("enter.AddConferencePanel.descLabel"));
	
	//会议提醒
	//Conference reminder
	private JLabel addressTypeLabel = new JLabel("addressType:");
	private JTextField addressTypeField = new JTextField(10);
	
	private JLabel timeLabel = new JLabel("time:");
	private JTextField timeField = new JTextField(10);
	
	//周期会议参数
	//Periodic meeting parameters
	private JLabel startDateLabel = new JLabel("startDate:");
	private JTextField startDateField = new JTextField(10);
	
	private JLabel endDateLabel = new JLabel("endDate:");
	private JTextField endDateField = new JTextField(10);
	
	private JLabel cycleCountLabel = new JLabel("cycleCount:");
	private JTextField cycleCountField = new JTextField(10);
	
	private JLabel cycleLabel = new JLabel("cycle:");
	private JTextField cycleField = new JTextField(10);
	
	private JLabel intervalLabel = new JLabel("interval:");
	private JTextField intervalField = new JTextField(10);
	
	//周期性会议召开点参数
	//Periodic meeting point parameters
	private JLabel weekthLabel = new JLabel("weekth:");
	private JTextField weekthField = new JTextField(10);
	
	private JLabel pointLabel = new JLabel("point:");
	private JTextField pointField = new JTextField(10);
	
	//按钮
	//button
	private JButton addAttendeesBtn = new MyButton(Properties_language_Utils.
			getValue("enter.AddConferencePanel.addAttendeesBtn"));
    
    private JButton scheduledMeetingBtn = new MyButton(Properties_language_Utils.
    		getValue("enter.AddConferencePanel.scheduledMeetingBtn"));
    
    private JButton modifyMeetingBtn = new MyButton(Properties_language_Utils.
    		getValue("enter.AddConferencePanel.modifyMeetingBtn"));
    
    private JButton cancleBtn = new MyButton(Properties_language_Utils.
    		getValue("enter.AddConferencePanel.cancleBtn"));
    
    private List<Attendee> attendeesList = new ArrayList<Attendee>();
    
    private List<Reminder> remindersList = new ArrayList<Reminder>();
	//初始化与会者为0，每点击一次与会者累加就加1
    //The initial participant is 0. Each time the participant accumulates, it adds 1
    int number = 0;
    
    public AddConferencePanel() 
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
        
        buildPanel(panel, gridbag, c, new JComponent[] {conferenceidLabel}, 0, 1, 5, 10, 1, 1);
        buildPanel(panel, gridbag, c, new JComponent[] {conferenceidField}, 1, 1, 5, 10, 1, 1);
        buildPanel(panel, gridbag, c, new JComponent[] {conferenceTypeLabel}, 2, 1, 5, 10, 1, 1);
        buildPanel(panel, gridbag, c, new JComponent[] {conferenceTypeField}, 3, 1, 5, 10, 1, 1);
        
        buildPanel(panel, gridbag, c, new JComponent[] {sizeLabel}, 0, 2, 5, 10, 1, 1);
        buildPanel(panel, gridbag, c, new JComponent[] {sizeField}, 1, 2, 5, 10, 1, 1);
        buildPanel(panel, gridbag, c, new JComponent[] {startTimeLabel}, 2, 2, 5, 10, 1, 1);
        buildPanel(panel, gridbag, c, new JComponent[] {startTimeField}, 3, 2, 5, 10, 1, 1);
        
        buildPanel(panel, gridbag, c, new JComponent[] {lengthLabel}, 0, 3, 5, 10, 1, 1);
        buildPanel(panel, gridbag, c, new JComponent[] {lengthField}, 1, 3, 5, 10, 1, 1);
        buildPanel(panel, gridbag, c, new JComponent[] {subjectLabel}, 2, 3, 5, 10, 1, 1);
        buildPanel(panel, gridbag, c, new JComponent[] {subjectField}, 3, 3, 5, 10, 1, 1);
        
        buildPanel(panel, gridbag, c, new JComponent[] {mediaTypesLabel}, 0, 4, 5, 10, 1, 1);
        buildPanel(panel, gridbag, c, new JComponent[] {mediaTypesField}, 1, 4, 5, 10, 1, 1);
        buildPanel(panel, gridbag, c, new JComponent[] {enterPromptLabel}, 2, 4, 5, 10, 1, 1);
        buildPanel(panel, gridbag, c, new JComponent[] {enterPromptField}, 3, 4, 5, 10, 1, 1);
        
        buildPanel(panel, gridbag, c, new JComponent[] {leavePromptLabel}, 0, 5, 5, 10, 1, 1);
        buildPanel(panel, gridbag, c, new JComponent[] {leavePromptField}, 1, 5, 5, 10, 1, 1);
        buildPanel(panel, gridbag, c, new JComponent[] {isAllowRecordLabel}, 2, 5, 5, 10, 1, 1);
        buildPanel(panel, gridbag, c, new JComponent[] {isAllowRecordField}, 3, 5, 5, 10, 1, 1);
        
        buildPanel(panel, gridbag, c, new JComponent[] {isAutoRecordLabel}, 0, 6, 5, 10, 1, 1);
        buildPanel(panel, gridbag, c, new JComponent[] {isAutoRecordField}, 1, 6, 5, 10, 1, 1);
        buildPanel(panel, gridbag, c, new JComponent[] {accountIdLabel}, 2, 6, 5, 10, 1, 1);
        buildPanel(panel, gridbag, c, new JComponent[] {accountIdField}, 3, 6, 5, 10, 1, 1);
        
        buildPanel(panel, gridbag, c, new JComponent[] {nameLabel}, 0, 7, 5, 10, 1, 1);
        buildPanel(panel, gridbag, c, new JComponent[] {nameField}, 1, 7, 5, 10, 1, 1);
        buildPanel(panel, gridbag, c, new JComponent[] {roleLabel}, 2, 7, 5, 10, 1, 1);
        buildPanel(panel, gridbag, c, new JComponent[] {roleField}, 3, 7, 5, 10, 1, 1);
        
        buildPanel(panel, gridbag, c, new JComponent[] {phoneLabel}, 0, 8, 5, 10, 1, 1);
        buildPanel(panel, gridbag, c, new JComponent[] {phoneField}, 1, 8, 5, 10, 1, 1);
        buildPanel(panel, gridbag, c, new JComponent[] {emailLabel}, 2, 8, 5, 10, 1, 1);
        buildPanel(panel, gridbag, c, new JComponent[] {emailField}, 3, 8, 5, 10, 1, 1);
        
        buildPanel(panel, gridbag, c, new JComponent[] {smsLabel}, 0, 9, 5, 10, 1, 1);
        buildPanel(panel, gridbag, c, new JComponent[] {smsField}, 1, 9, 5, 10, 1, 1);
        buildPanel(panel, gridbag, c, new JComponent[] {typeLabel}, 2, 9, 5, 10, 1, 1);
        buildPanel(panel, gridbag, c, new JComponent[] {typeField}, 3, 9, 5, 10, 1, 1);
        
        buildPanel(panel, gridbag, c, new JComponent[] {addressTypeLabel}, 0, 10, 5, 10, 1, 1);
        buildPanel(panel, gridbag, c, new JComponent[] {addressTypeField}, 1, 10, 5, 10, 1, 1);
        buildPanel(panel, gridbag, c, new JComponent[] {timeLabel}, 2, 10, 5, 10, 1, 1);
        buildPanel(panel, gridbag, c, new JComponent[] {timeField}, 3, 10, 5, 10, 1, 1);
        
        buildPanel(panel, gridbag, c, new JComponent[] {startDateLabel}, 0, 11, 5, 10, 1, 1);
        buildPanel(panel, gridbag, c, new JComponent[] {startDateField}, 1, 11, 5, 10, 1, 1);
        buildPanel(panel, gridbag, c, new JComponent[] {endDateLabel}, 2, 11, 5, 10, 1, 1);
        buildPanel(panel, gridbag, c, new JComponent[] {endDateField}, 3, 11, 5, 10, 1, 1);
        
        buildPanel(panel, gridbag, c, new JComponent[] {cycleCountLabel}, 0, 12, 5, 10, 1, 1);
        buildPanel(panel, gridbag, c, new JComponent[] {cycleCountField}, 1, 12, 5, 10, 1, 1);
        buildPanel(panel, gridbag, c, new JComponent[] {cycleLabel}, 2, 12, 5, 10, 1, 1);
        buildPanel(panel, gridbag, c, new JComponent[] {cycleField}, 3, 12, 5, 10, 1, 1);
        
        buildPanel(panel, gridbag, c, new JComponent[] {intervalLabel}, 0, 13, 5, 10, 1, 1);
        buildPanel(panel, gridbag, c, new JComponent[] {intervalField}, 1, 13, 5, 10, 1, 1);
        buildPanel(panel, gridbag, c, new JComponent[] {weekthLabel}, 2, 13, 5, 10, 1, 1);
        buildPanel(panel, gridbag, c, new JComponent[] {weekthField}, 3, 13, 5, 10, 1, 1);
        
        buildPanel(panel, gridbag, c, new JComponent[] {recordTypeLabel}, 0, 14, 5, 10, 1, 1);
        buildPanel(panel, gridbag, c, new JComponent[] {recordTypeField}, 1, 14, 5, 10, 1, 1);
        buildPanel(panel, gridbag, c, new JComponent[] {liveAddressLabel}, 2, 14, 5, 10, 1, 1);
        buildPanel(panel, gridbag, c, new JComponent[] {liveAddressField}, 3, 14, 5, 10, 1, 1);
        
        buildPanel(panel, gridbag, c, new JComponent[] {auxAddressLabel}, 0, 15, 5, 10, 1, 1);
        buildPanel(panel, gridbag, c, new JComponent[] {auxAddressField}, 1, 15, 5, 10, 1, 1);
        buildPanel(panel, gridbag, c, new JComponent[] {recordAuxStreamLabel}, 2, 15, 5, 10, 1, 1);
        buildPanel(panel, gridbag, c, new JComponent[] {recordAuxStreamField}, 3, 15, 5, 10, 1, 1);
        
        buildPanel(panel, gridbag, c, new JComponent[] {pointLabel}, 0, 16, 5, 10, 1, 1);
        buildPanel(panel, gridbag, c, new JComponent[] {pointField}, 1, 16, 5, 10, 1, 1);
        
        buildPanel(panel, gridbag, c, new JComponent[] {addAttendeesBtn}, 0, 17, 5, 10, 1, 1);
        buildPanel(panel, gridbag, c, new JComponent[] {scheduledMeetingBtn}, 1, 17, 5, 10, 1, 1);
        buildPanel(panel, gridbag, c, new JComponent[] {modifyMeetingBtn}, 2, 17, 5, 10, 1, 1);
        buildPanel(panel, gridbag, c, new JComponent[] {cancleBtn}, 3, 17, 5, 10, 1, 1);
        cancleBtn.addActionListener(this);
        
        buildPanel(panel, gridbag, c, new JComponent[] {errInfoLabel}, 2, 16, 5, 10, 2, 1);
        buildPanel(panel, gridbag, c, new JComponent[] {descLabel}, 0, 18, 5, 10, 6, 1);
        
        //报文位置
        //message location
        buildPanel(panel, gridbag, c, new JComponent[] {new LogPanel()}, 4, 0, 10, 20, 4, 10);
        
		return panel;
    }
	
	private void init() 
	{
		descLabel.setForeground(Color.BLUE);
		//与会者累加按钮事件
		//Participants accumulate button events
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
				
				attendeesList.add(addAttendees());
				showErrInfoWithColor(Properties_language_Utils.getValue("enter.AddConferencePanel.addAttendeesTip"));
				number++;
				
			}
		});
		
		//预约会议按钮事件
		//Reservation Conference Button Event
		this.scheduledMeetingBtn.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e) 
			{
				showErrInfoWithColor("");
				boolean flag = validateParams(new JTextField[] {sizeField, mediaTypesField}, 
						new String[] {"size", "mediaTypes"});
				 
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
	            		scheduledMeeting();
	            	}
	            };
	            Future future = Executors.newSingleThreadExecutor().submit(runnable);
	            if(!future.isDone()) {
	            	LOGGER.error("addMouseListener fail");
	            }
			}
		});
		
		//修改预约会议按钮事件
		//Modify appointment conference button events
		this.modifyMeetingBtn.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e) 
			{
				showErrInfoWithColor("");
				boolean flag = validateParams(new JTextField[] {conferenceidField, sizeField, mediaTypesField}, 
						new String[] {"conferenceid", "size", "mediaTypes"});
				 
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
	            		modifyMeeting();
	            	}
	            };
	            Future future = Executors.newSingleThreadExecutor().submit(runnable);
	            if(!future.isDone()) {
	            	LOGGER.error("addMouseListener fail");
	            }
			}
		});
	}
	
	//修改预约会议
	//modifyMeeting
	private void modifyMeeting() 
	{
		try 
		{
			Token token = LoginUtils.getToken();
			
			// 利用token发送restful请求
			RestRequest request = new RestRequest(HTTPConstant.HTTP_METHOD_PUT);
			
			request.setPayload(addScheduleMeetingBean());
			
			EcService.put("/conferences/" + conferenceidField.getText(), request, errInfoLabel, token);
			
			EcService.finish();
		} 
		catch (Exception e) 
		{
			LOGGER.error("get Token error" + e);
			showErrInfoWithColor(Properties_language_Utils.getValue("resultTip"));
		}
	}
	
	//预约会议
	//scheduledMeeting
	private void scheduledMeeting() 
	{
		try 
		{
			Token token = LoginUtils.getToken();
			
			// 利用token发送restful请求
			//Send a restful request using token
			RestRequest request = new RestRequest(HTTPConstant.HTTP_METHOD_POST);
			
			//参数的设定
			//Parameter setting
			request.setPayload(addScheduleMeetingBean());
			
			EcService.post("/conferences", request, errInfoLabel, token);
			
		} 
		catch (Exception e) 
		{
			LOGGER.error("get Token error:" + e);
			showErrInfoWithColor(Properties_language_Utils.getValue("resultTip"));
		}
		finally 
		{
			EcService.finish();
		}
	}
	
	private ScheduleMeetingBean addScheduleMeetingBean() 
	{
		ScheduleMeetingBean scheduleMeetingBean = new ScheduleMeetingBean();
		if (StringUtils.isNotEmpty(conferenceTypeField.getText())) 
		{
			scheduleMeetingBean.setConferenceType(Integer.parseInt(conferenceTypeField.getText()));
		}
		
		scheduleMeetingBean.setSize(Integer.parseInt(sizeField.getText()));
		if (StringUtils.isNotEmpty(startTimeField.getText())) 
		{
			scheduleMeetingBean.setStartTime(startTimeField.getText());
		}
		if (StringUtils.isNotEmpty(lengthField.getText())) 
		{
			scheduleMeetingBean.setLength(Integer.parseInt(lengthField.getText()));
		}
		if (StringUtils.isNotEmpty(subjectField.getText())) 
		{
			scheduleMeetingBean.setSubject(subjectField.getText());
		}
		
		scheduleMeetingBean.setMediaTypes(mediaTypesField.getText());
		
		if (StringUtils.isNotEmpty(enterPromptField.getText()))
		{
			scheduleMeetingBean.setEnterPrompt(Integer.parseInt(enterPromptField.getText()));
		}
		if (StringUtils.isNotEmpty(leavePromptField.getText())) 
		{
			scheduleMeetingBean.setLeavePrompt(Integer.parseInt(leavePromptField.getText()));
		}
		
		Attendee[] attendees = attendeesList.toArray(new Attendee[number]);
		scheduleMeetingBean.setAttendees(attendees);
		
		if (null != addReminders()) 
		{
			remindersList.add(addReminders());
			//假定最多为10个提醒
			//assume reminders is 10
			Reminder[] reminders = remindersList.toArray(new Reminder[10]);
			scheduleMeetingBean.setReminders(reminders);
		}
		
		if (null != addCycleParams()) 
		{
			scheduleMeetingBean.setCycleParams(addCycleParams());
		}
		
		if (StringUtils.isNotEmpty(isAllowRecordField.getText())) 
		{
			scheduleMeetingBean.setIsAllowRecord(Integer.parseInt(isAllowRecordField.getText()));
		}
		if (StringUtils.isNotEmpty(isAutoRecordField.getText())) 
		{
			scheduleMeetingBean.setIsAutoRecord(Integer.parseInt(isAutoRecordField.getText()));
		}
		if (StringUtils.isNotEmpty(recordTypeField.getText())) 
		{
			scheduleMeetingBean.setRecordType(Integer.parseInt(recordTypeField.getText()));
		}
		if (StringUtils.isNotEmpty(liveAddressField.getText())) 
		{
			scheduleMeetingBean.setLiveAddress(liveAddressField.getText());
		}
		if (StringUtils.isNotEmpty(auxAddressField.getText())) 
		{
			scheduleMeetingBean.setAuxAddress(auxAddressField.getText());
		}
		if (StringUtils.isNotEmpty(recordAuxStreamField.getText())) 
		{
			scheduleMeetingBean.setRecordAuxStream(Integer.parseInt(recordAuxStreamField.getText()));
		}
		
		return scheduleMeetingBean;
			
	}
	
	private CycleParams addCycleParams() 
	{
		CycleParams cycleParams = new CycleParams();
		int count = 0;
		
		if (StringUtils.isNotEmpty(startDateField.getText())) 
		{
			cycleParams.setStartDate(startDateField.getText());
			count++;
		}
		if (StringUtils.isNotEmpty(endDateField.getText())) 
		{
			cycleParams.setEndDate(endDateField.getText());
			count++;
		}
		if (StringUtils.isNotEmpty(cycleCountField.getText())) 
		{
			cycleParams.setCycleCount(Integer.parseInt(cycleCountField.getText()));
			count++;
		}
		if (StringUtils.isNotEmpty(cycleField.getText())) 
		{
			cycleParams.setCycle(cycleField.getText());
			count++;
		}
		if (StringUtils.isNotEmpty(intervalField.getText())) 
		{
			cycleParams.setInterval(Integer.parseInt(intervalField.getText()));
			count++;
		}
		
		if (null != addWeekDayParam()) 
		{
			WeekDayParam[] weekDays = {addWeekDayParam()};
			cycleParams.setWeekDays(weekDays);
			count++;
		}
		
		if (count == 0) 
		{
			return null;
		}else 
		{
			return cycleParams;
		}
		
		
	}
	
	private WeekDayParam addWeekDayParam() 
	{
		WeekDayParam weekDayParam = new WeekDayParam();
		int count = 0;
		if (StringUtils.isNotEmpty(weekthField.getText())) 
		{
			weekDayParam.setWeekth(Integer.parseInt(weekthField.getText()));
			count++;
		}
		if (StringUtils.isNotEmpty(pointField.getText()))
		{
			String[] points = pointField.getText().split(",");
			
			int[] intPoints = new int[points.length];
			 
			for (int i = 0; i<points.length; i++) 
			{
				intPoints[i] = Integer.parseInt(points[i]);
			}
			
			weekDayParam.setPoint(intPoints);
			count++;
		}
		if (count == 0) 
		{
			return null;
		}else 
		{
			return weekDayParam;
		}
		
	}
	
	private Reminder addReminders() 
	{
		Reminder reminder = new Reminder();
		int count = 0;
		if (StringUtils.isNotEmpty(addressTypeField.getText())) 
		{
			reminder.setType(addressTypeField.getText());
			count++;
		}
		if (StringUtils.isNotEmpty(timeField.getText()))
		{
			reminder.setTime(Long.parseLong(timeField.getText()));
			count++;
		}
		
		if (count == 0) 
		{
			return null;
		}else 
		{
			return reminder;
		}
	}
	
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
		if (StringUtils.isNotEmpty(typeField.getText())) 
		{
			atted.setType(AddressType.valueOf(typeField.getText()));
		}
		
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
            
    	c.fill = GridBagConstraints.VERTICAL;// 加高组件直到他足以在垂直方向上填满其显示区域，但不更改其高度
    	c.anchor = GridBagConstraints.WEST;// 当组件小于其显示区域时，用于确定将组件至于西面
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
            if (null == textFields[i] || null == textFields[i].getText() || "".equals(textFields[i].getText()))
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
		conferenceidField.setText("");
		conferenceTypeField.setText("");
		sizeField.setText("");
		startTimeField.setText("");
		lengthField.setText("");
		subjectField.setText("");
		mediaTypesField.setText("");
		enterPromptField.setText("");
		leavePromptField.setText("");
		isAllowRecordField.setText("");
		isAutoRecordField.setText("");
		accountIdField.setText("");
		nameField.setText("");
		roleField.setText("");
		phoneField.setText("");
		emailField.setText("");
		smsField.setText("");
		typeField.setText("");
		addressTypeField.setText("");
		timeField.setText("");
		startDateField.setText("");
		endDateField.setText("");
		cycleCountField.setText("");
		cycleField.setText("");
		intervalField.setText("");
		weekthField.setText("");
		pointField.setText("");
	}
}
