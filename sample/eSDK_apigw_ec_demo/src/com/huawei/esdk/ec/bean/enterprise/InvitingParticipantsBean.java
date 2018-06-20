package com.huawei.esdk.ec.bean.enterprise;

import java.util.List;
/**
 * 邀请与会者实体类
 * Invite attendee entity
 * @author wwx534262
 *
 */
public class InvitingParticipantsBean 
{
	private List<Attendee> attendees;

	public List<Attendee> getAttendees() 
	{
		return attendees;
	}

	public void setAttendees(List<Attendee> attendees) 
	{
		this.attendees = attendees;
	}
	
	
}
