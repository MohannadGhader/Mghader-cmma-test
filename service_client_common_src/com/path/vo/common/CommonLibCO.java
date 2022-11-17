package com.path.vo.common;

import java.math.BigDecimal;
import java.util.Date;
import com.path.lib.vo.BaseVO;
/**
 * 
 * Copyright 2014, Path Solutions
 * Path Solutions retains all ownership rights to this source code 
 * 
 * @author: deniskhaddad
 *
 * CommonLibCO.java used to return Common details from database
 */
public class CommonLibCO extends BaseVO
{
    private static final long serialVersionUID = 1L;
    /**
     * used for database session ID
     */
    private BigDecimal dbSessionId;
    /**
     * used for database Node in clustered Environment
     */
    private String dbNode;
    /**
     * check if user is still logged in
     */
    private Boolean checkStillLoggedIn;
    /**
     * flag to indicate if access rights on opt has been changed from SADS
     */
    private BigDecimal axsChanged;
    
    //RKFHK190067--No warning message if running date not same as Phoenix running date
    private String validPhoenixExtDate;
    private Date phoenixExtDate;
    
    public BigDecimal getDbSessionId()
    {
        return dbSessionId;
    }
    public void setDbSessionId(BigDecimal dbSessionId)
    {
        this.dbSessionId = dbSessionId;
    }
    public String getDbNode()
    {
        return dbNode;
    }
    public void setDbNode(String dbNode)
    {
        this.dbNode = dbNode;
    }
    public Boolean getCheckStillLoggedIn()
    {
        return checkStillLoggedIn;
    }
    public void setCheckStillLoggedIn(Boolean checkStillLoggedIn)
    {
        this.checkStillLoggedIn = checkStillLoggedIn;
    }
    public BigDecimal getAxsChanged()
    {
        return axsChanged;
    }
    public void setAxsChanged(BigDecimal axsChanged)
    {
        this.axsChanged = axsChanged;
    }
	
	public String getValidPhoenixExtDate()
	{
		return validPhoenixExtDate;
	}
	
	public void setValidPhoenixExtDate(String validPhoenixExtDate)
	{
		this.validPhoenixExtDate = validPhoenixExtDate;
	}
	
	public Date getPhoenixExtDate()
	{
		return phoenixExtDate;
	}
	
	public void setPhoenixExtDate(Date phoenixExtDate)
	{
		this.phoenixExtDate = phoenixExtDate;
	}
    
}
