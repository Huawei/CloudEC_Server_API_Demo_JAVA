package com.huawei.esdk.ec.bean.enterprise;
/**
 * 预约会议参数体
 * Reservation meeting parameter body
 * @author wwx534262
 *
 */
public class ScheduleMeetingBean 
{
	private Integer conferenceType;
	
	private Integer size;
	
	private String timeZoneID;
	
	private String startTime;
	
	private Integer length = 60;
	
	private String subject;
	
	private String mediaTypes;
	
	private Integer welcomeVoiceEnable = 1;
	
	private Integer enterPrompt = -2;
	
	private Integer leavePrompt = -2;
	
	private Attendee[] attendees;
	 
	private Reminder[] reminders;
	
	private CycleParams cycleParams;
	
	private Integer isAllowRecord;
	
	private Integer isAutoRecord;
	
	private Integer encryptMode;
	
	private String language;
	
	private Integer autoProlong;
	
	private Integer multiStreamFlag;
	
	//录播类型
	private Integer recordType;
	
	//主流直播地址
	private String liveAddress;
	
	//辅流直播地址
	private String auxAddress;
	
	//是否录制辅流
	private Integer recordAuxStream;
	
	public Integer getRecordType() 
	{
		return recordType;
	}

	public void setRecordType(Integer recordType) 
	{
		this.recordType = recordType;
	}

	public String getLiveAddress() 
	{
		return liveAddress;
	}

	public void setLiveAddress(String liveAddress) 
	{
		this.liveAddress = liveAddress;
	}

	public String getAuxAddress() 
	{
		return auxAddress;
	}

	public void setAuxAddress(String auxAddress) 
	{
		this.auxAddress = auxAddress;
	}

	public Integer getRecordAuxStream() 
	{
		return recordAuxStream;
	}

	public void setRecordAuxStream(Integer recordAuxStream) 
	{
		this.recordAuxStream = recordAuxStream;
	}

	public Integer getConferenceType() 
	{
		return conferenceType;
	}

	public void setConferenceType(Integer conferenceType) 
	{
		this.conferenceType = conferenceType;
	}

	public Integer getSize() 
	{
		return size;
	}

	public void setSize(Integer size) 
	{
		this.size = size;
	}

	public String getTimeZoneID() 
	{
		return timeZoneID;
	}

	public void setTimeZoneID(String timeZoneID)
	{
		this.timeZoneID = timeZoneID;
	}

	public String getStartTime() 
	{
		return startTime;
	}

	public void setStartTime(String startTime)
	{
		this.startTime = startTime;
	}

	public Integer getLength()
	{
		return length;
	}

	public void setLength(Integer length) 
	{
		this.length = length;
	}

	public String getSubject() 
	{
		return subject;
	}

	public void setSubject(String subject) 
	{
		this.subject = subject;
	}

	public String getMediaTypes() 
	{
		return mediaTypes;
	}

	public void setMediaTypes(String mediaTypes) 
	{
		this.mediaTypes = mediaTypes;
	}

	public Integer getWelcomeVoiceEnable() 
	{
		return welcomeVoiceEnable;
	}

	public void setWelcomeVoiceEnable(Integer welcomeVoiceEnable) 
	{
		this.welcomeVoiceEnable = welcomeVoiceEnable;
	}

	public Integer getEnterPrompt()
	{
		return enterPrompt;
	}

	public void setEnterPrompt(Integer enterPrompt) 
	{
		this.enterPrompt = enterPrompt;
	}

	public Integer getLeavePrompt()
	{
		return leavePrompt;
	}

	public void setLeavePrompt(Integer leavePrompt)
	{
		this.leavePrompt = leavePrompt;
	}

	public Attendee[] getAttendees() 
	{
		if(null == attendees)
		{
			return new Attendee[] {};
		}
		return attendees.clone();
	}

	public void setAttendees(Attendee[] attendees) 
	{
		this.attendees = attendees.clone();
	}

	public Reminder[] getReminders()
	{
		if(null == reminders)
		{
			return new Reminder[] {};
		}
		return reminders.clone();
	}

	public void setReminders(Reminder[] reminders)
	{
		this.reminders = reminders.clone();
	}

	public CycleParams getCycleParams() 
	{
		return cycleParams;
	}

	public void setCycleParams(CycleParams cycleParams) 
	{
		this.cycleParams = cycleParams;
	}

	public Integer getIsAllowRecord() 
	{
		return isAllowRecord;
	}

	public void setIsAllowRecord(Integer isAllowRecord)
	{
		this.isAllowRecord = isAllowRecord;
	}

	public Integer getIsAutoRecord() 
	{
		return isAutoRecord;
	}

	public void setIsAutoRecord(Integer isAutoRecord) 
	{
		this.isAutoRecord = isAutoRecord;
	}

	public Integer getEncryptMode()
	{
		return encryptMode;
	}

	public void setEncryptMode(Integer encryptMode) 
	{
		this.encryptMode = encryptMode;
	}

	public String getLanguage()
	{
		return language;
	}

	public void setLanguage(String language) 
	{
		this.language = language;
	}

	public Integer getAutoProlong()
	{
		return autoProlong;
	}

	public void setAutoProlong(Integer autoProlong)
	{
		this.autoProlong = autoProlong;
	}

	public Integer getMultiStreamFlag()
	{
		return multiStreamFlag;
	}

	public void setMultiStreamFlag(Integer multiStreamFlag) 
	{
		this.multiStreamFlag = multiStreamFlag;
	}
	
}
