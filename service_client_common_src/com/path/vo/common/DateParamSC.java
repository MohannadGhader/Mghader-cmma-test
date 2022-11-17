package com.path.vo.common;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import com.path.struts2.lib.common.BaseSC;

public class DateParamSC extends BaseSC implements Serializable
{

    private static final long serialVersionUID = 1L;
    private Date fromDate;
    private Date valueDate;
    private Date currentDate;
    private Calendar toDateCalendar = Calendar.getInstance();
    private BigDecimal periodicity;
    private String periodicityType;
    private BigDecimal compCountryCode;
    private BigDecimal dayNum;
    private String applyYearHoliday;
    private String type;
    private String dateIncluded;
    private String errorMessage;

    /**
     * used to return the error code
     */
    private BigDecimal errorCode;
    /**
     * used to return the error parameters in an array
     */
    private String[] errorParams;
    
    //TP 794422 attribute to specify whether to exclude the flag checking of CTSCONTROL.ADD_HOLIDAYS_TO_SB_RENEW to apply Country holidays
    private String excludeBranchFlagChecking;

    public void setFromDate(Date fromDate)
    {
	this.fromDate = fromDate;
    }

    public Date getFromDate()
    {
	return fromDate;
    }

    public void setCompCountryCode(BigDecimal compCountryCode)
    {
	this.compCountryCode = compCountryCode;
    }

    public BigDecimal getCompCountryCode()
    {
	return compCountryCode;
    }

    public Date getToDate()
    {
	return toDateCalendar.getTime();
    }

    public Integer getToDateMonth()
    {
	return toDateCalendar.get(Calendar.MONTH) + 1;

    }

    public Integer getToDateDay()
    {
	return toDateCalendar.get(Calendar.DATE);
    }

    public Integer getToDateYear()
    {
	return toDateCalendar.get(Calendar.YEAR);
    }

    public void setToDateCalendar(Calendar toDateCalendar)
    {
	this.toDateCalendar = toDateCalendar;
    }

    public Calendar getToDateCalendar()
    {
	return toDateCalendar;
    }

    public void setPeriodicityType(String periodicityType)
    {
	this.periodicityType = periodicityType;
    }

    public String getPeriodicityType()
    {
	return periodicityType;
    }

    public void setPeriodicity(BigDecimal periodicity)
    {
	this.periodicity = periodicity;
    }

    public BigDecimal getPeriodicity()
    {
	return periodicity;
    }

    public String getApplyYearHoliday()
    {
	return applyYearHoliday;
    }

    public void setApplyYearHoliday(String applyYearHoliday)
    {
	this.applyYearHoliday = applyYearHoliday;
    }

    public BigDecimal getErrorCode()
    {
	return errorCode;
    }

    public void setErrorCode(BigDecimal errorCode)
    {
	this.errorCode = errorCode;
    }

    public String[] getErrorParams()
    {
	return errorParams;
    }

    public void setErrorParams(String... errorParams)
    {
	this.errorParams = errorParams;
    }

    public String getErrorMessage()
    {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage)
    {
        this.errorMessage = errorMessage;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public BigDecimal getDayNum()
    {
        return dayNum;
    }

    public void setDayNum(BigDecimal dayNum)
    {
        this.dayNum = dayNum;
    }

    public Date getValueDate()
    {
        return valueDate;
    }

    public void setValueDate(Date valueDate)
    {
        this.valueDate = valueDate;
    }

    public Date getCurrentDate()
    {
        return currentDate;
    }

    public void setCurrentDate(Date currentDate)
    {
        this.currentDate = currentDate;
    }

    public String getDateIncluded()
    {
        return dateIncluded;
    }

    public void setDateIncluded(String dateIncluded)
    {
        this.dateIncluded = dateIncluded;
    }

    public String getExcludeBranchFlagChecking()
    {
        return excludeBranchFlagChecking;
    }

    public void setExcludeBranchFlagChecking(String excludeBranchFlagChecking)
    {
        this.excludeBranchFlagChecking = excludeBranchFlagChecking;
    }

}
