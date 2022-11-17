package com.path.ws.omni.vo.common.ionicgrid;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import com.path.ws.omni.vo.common.RequestBaseObject;

@XmlRootElement(name = "IonicGridSortFilterCO")
public class IonicGridSortFilterCO extends RequestBaseObject
{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String colId;
    private String sort;
    private String filter;
    private String filterTo;
    private String filterType;
    private String type;
    private String dateFrom;
    private String dateTo;

    public String getColId()
    {
	return colId;
    }

    public void setColId(String colId)
    {
	this.colId = colId;
    }

    public String getSort()
    {
	return sort;
    }

    public void setSort(String sort)
    {
	this.sort = sort;
    }

    public String getFilter()
    {
	return filter;
    }

    public void setFilter(String filter)
    {
	this.filter = filter;
    }

    public String getFilterType()
    {
	return filterType;
    }

    public void setFilterType(String filterType)
    {
	this.filterType = filterType;
    }

    public String getType()
    {
	return type;
    }

    public void setType(String type)
    {
	this.type = type;
    }

    public String getFilterTo()
    {
	return filterTo;
    }

    public void setFilterTo(String filterTo)
    {
	this.filterTo = filterTo;
    }

    public String getDateFrom()
    {
	return dateFrom;
    }

    public void setDateFrom(String dateFrom)
    {
	this.dateFrom = dateFrom;
    }

    public String getDateTo()
    {
	return dateTo;
    }

    public void setDateTo(String dateTo)
    {
	this.dateTo = dateTo;
    }

}
