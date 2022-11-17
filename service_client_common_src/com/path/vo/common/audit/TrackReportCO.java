package com.path.vo.common.audit;

import java.io.Serializable;

/**
 * @author Rabih El Khatib
 *
 */
@SuppressWarnings("serial")
public class TrackReportCO implements Serializable
{
	private String fieldLocation;
	private String fieldLabel;
	private String oldValue;
	private String newValue;
	
	public String getFieldLocation()
	{
	    return fieldLocation;
	}
	public void setFieldLocation(String fieldLocation)
	{
	    this.fieldLocation = fieldLocation;
	}
	public String getFieldLabel()
	{
	    return fieldLabel;
	}
	public void setFieldLabel(String fieldLabel)
	{
	    this.fieldLabel = fieldLabel;
	}
	public String getOldValue()
	{
	    return oldValue;
	}
	public void setOldValue(String oldValue)
	{
	    this.oldValue = oldValue;
	}
	public String getNewValue()
	{
	    return newValue;
	}
	public void setNewValue(String newValue)
	{
	    this.newValue = newValue;
	}

}
