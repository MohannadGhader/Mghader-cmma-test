package com.path.ws.omni.vo.common.ionicgrid;

import javax.xml.bind.annotation.XmlRootElement;

import com.path.ws.omni.vo.common.RequestBaseObject;

@XmlRootElement(name = "IonicGridSortFilterCO")
public class IonicGridFilterCO extends RequestBaseObject
{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String colName;
    private IonicGridSortFilterCO filterOptions = new IonicGridSortFilterCO();
    private String operator;

    public String getColName()
    {
	return colName;
    }

    public void setColName(String colName)
    {
	this.colName = colName;
    }

    public IonicGridSortFilterCO getFilterOptions()
    {
	return filterOptions;
    }

    public void setFilterOptions(IonicGridSortFilterCO filterOptions)
    {
	this.filterOptions = filterOptions;
    }

    public String getOperator()
    {
	return operator;
    }

    public void setOperator(String operator)
    {
	this.operator = operator;
    }

}
