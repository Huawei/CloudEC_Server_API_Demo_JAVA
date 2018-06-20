package com.huawei.esdk.ec.bean.enterprise;
/**
 * 周期会议参数 
 * Recurrent meetings parameter entity
 * @author wWX534262
 *
 */
public class CycleParams 
{
	private String startDate;
	
	private String endDate;
	
	private Integer cycleCount;
	
	private String cycle;
	
	private Integer interval;
	
	private WeekDayParam[] weekDays;

	public String getStartDate() 
	{
		return startDate;
	}

	public void setStartDate(String startDate) 
	{
		this.startDate = startDate;
	}

	public String getEndDate()
	{
		return endDate;
	}

	public void setEndDate(String endDate) 
	{
		this.endDate = endDate;
	}

	public Integer getCycleCount() 
	{
		return cycleCount;
	}

	public void setCycleCount(Integer cycleCount) 
	{
		this.cycleCount = cycleCount;
	}

	public String getCycle()
	{
		return cycle;
	}

	public void setCycle(String cycle) 
	{
		this.cycle = cycle;
	}

	public Integer getInterval() 
	{
		return interval;
	}

	public void setInterval(Integer interval) 
	{
		this.interval = interval;
	}

	public WeekDayParam[] getWeekDays() 
	{
		if(null == weekDays)
		{
			return new WeekDayParam[] {};
		}
		return weekDays.clone();
	}

	public void setWeekDays(WeekDayParam[] weekDays) 
	{
		this.weekDays = weekDays.clone();
	}
	
	
}
