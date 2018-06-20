package com.huawei.esdk.ec.bean.enterprise;
/**
 * 周期性会议召开点参数
 * Periodic meeting point parameters
 * @author wwx534262
 *
 */
public class WeekDayParam 
{
	private Integer weekth;
	
	private int[] point;

	public Integer getWeekth() 
	{
		return weekth;
	}

	public void setWeekth(Integer weekth) 
	{
		this.weekth = weekth;
	}

	public int[] getPoint() 
	{
		if(null == point){
			return new int[]{};
		}
		return point.clone();
	}

	public void setPoint(int[] point) 
	{
		this.point = point.clone();
	}
	
	
}
