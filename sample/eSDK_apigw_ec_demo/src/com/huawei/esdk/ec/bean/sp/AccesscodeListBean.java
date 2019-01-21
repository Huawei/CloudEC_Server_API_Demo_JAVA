package com.huawei.esdk.ec.bean.sp;
/**
 * 查询接入号列表参数实体
 * @author wWX534262
 *
 */
public class AccesscodeListBean 
{
	//搜索关键字
	private String searchKey;
	
	//每页大小
	private String pageSize;
	
	//第几页
	private String pageIndex;
	
	//会议类型，不带表示查询所有会议
	private String confType;
	
	//组织ID
	private String orgID;
	
	//接入号共享类型,0 非共享 1 共享 -1表示查询所有
	private Integer isShare;
	
	//分配状态， 0表示已分配给指定组织的接入号列表， 1表示查询可分配给指定组织的接入号列表
	private Integer isAssignReqQry;

	public String getSearchKey() 
	{
		return searchKey;
	}

	public void setSearchKey(String searchKey)
	{
		this.searchKey = searchKey;
	}

	public String getPageSize()
	{
		return pageSize;
	}

	public void setPageSize(String pageSize) 
	{
		this.pageSize = pageSize;
	}

	public String getPageIndex()
	{
		return pageIndex;
	}

	public void setPageIndex(String pageIndex) 
	{
		this.pageIndex = pageIndex;
	}

	public String getConfType() 
	{
		return confType;
	}

	public void setConfType(String confType) 
	{
		this.confType = confType;
	}

	public String getOrgID()
	{
		return orgID;
	}

	public void setOrgID(String orgID)
	{
		this.orgID = orgID;
	}

	public Integer getIsShare()
	{
		return isShare;
	}

	public void setIsShare(Integer isShare) 
	{
		this.isShare = isShare;
	}

	public Integer getIsAssignReqQry() 
	{
		return isAssignReqQry;
	}

	public void setIsAssignReqQry(Integer isAssignReqQry) 
	{
		this.isAssignReqQry = isAssignReqQry;
	}
	
}
