package com.path.ws.omni.vo.common.ionicgrid;

import java.util.List;

import com.path.ws.omni.vo.common.RequestBaseObject;

public class OmniGridParamRequest extends RequestBaseObject
{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private List<IonicGridSortFilterCO> sortModel;
    private List<IonicGridFilterCO> filterModel;
    private String sortOrder, whereQuery;
    /**
     * number of records of grid page
     */
    private int nbRec;
    /**
     * total number of records of grid
     */
    private int totalNbRec;
    /**
     * nb of records to skip when using pagination
     */
    private int recToskip;

    public List<IonicGridSortFilterCO> getSortModel()
    {
	return sortModel;
    }

    public void setSortModel(List<IonicGridSortFilterCO> sortModel)
    {
	this.sortModel = sortModel;
    }

    public List<IonicGridFilterCO> getFilterModel()
    {
	return filterModel;
    }

    public void setFilterModel(List<IonicGridFilterCO> filterModel)
    {
	this.filterModel = filterModel;
    }

    public int getNbRec()
    {
	return nbRec;
    }

    public void setNbRec(int nbRec)
    {
	this.nbRec = nbRec;
    }

    public int getRecToskip()
    {
	return recToskip;
    }

    public void setRecToskip(int recToskip)
    {
	this.recToskip = recToskip;
    }

    public String getSortOrder()
    {
	return sortOrder;
    }

    public void setSortOrder(String sortOrder)
    {
	if(sortOrder != null && sortOrder.trim().length() != 0)
	{
	    this.sortOrder = sortOrder;
	}
    }

    public String getWhereQuery()
    {
	return whereQuery;
    }

    public void setWhereQuery(String whereQuery)
    {
	if(whereQuery != null && whereQuery.trim().length() != 0)
	{
	    this.whereQuery = whereQuery;
	}
    }

    public int getTotalNbRec()
    {
	return totalNbRec;
    }

    public void setTotalNbRec(int totalNbRec)
    {
	this.totalNbRec = totalNbRec;
    }

}
