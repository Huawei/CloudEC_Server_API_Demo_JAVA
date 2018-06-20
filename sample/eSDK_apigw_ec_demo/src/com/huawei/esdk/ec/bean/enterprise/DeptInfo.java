package com.huawei.esdk.ec.bean.enterprise;
/**
 * 增加企业部门请求实体参数
 * Increase the enterprise department request entity parameters
 * @author wwx534262
 *
 */
public class DeptInfo
{
    private String deptCode;
    
    private String deptName;
    
    private String parentDeptCode;
    
    private String mgrName;
    
    private String mgrCode;
    
    private String secName;
    
    private String secCode;
    
    public String getDeptCode()
    {
        return deptCode;
    }
    
    public void setDeptCode(String deptCode)
    {
        this.deptCode = deptCode;
    }
    
    public String getDeptName()
    {
        return deptName;
    }
    
    public void setDeptName(String deptName)
    {
        this.deptName = deptName;
    }
    
    public String getParentDeptCode()
    {
        return parentDeptCode;
    }
    
    public void setParentDeptCode(String parentDeptCode)
    {
        this.parentDeptCode = parentDeptCode;
    }
    
    public String getMgrName()
    {
        return mgrName;
    }
    
    public void setMgrName(String mgrName)
    {
        this.mgrName = mgrName;
    }
    
    public String getMgrCode()
    {
        return mgrCode;
    }
    
    public void setMgrCode(String mgrCode)
    {
        this.mgrCode = mgrCode;
    }
    
    public String getSecName()
    {
        return secName;
    }
    
    public void setSecName(String secName)
    {
        this.secName = secName;
    }
    
    public String getSecCode()
    {
        return secCode;
    }
    
    public void setSecCode(String secCode)
    {
        this.secCode = secCode;
    }
}
