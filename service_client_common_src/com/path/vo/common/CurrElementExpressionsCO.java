/**
 * 
 */
package com.path.vo.common;

import java.math.BigDecimal;

import com.path.lib.vo.BaseVO;

/**
 * Copyright 2016, Path Solutions
 * Path Solutions retains all ownership rights to this source code 
 * 
 * @author: marwanmaddah
 *
 * CurrElementExpressionsCO.java used to
 */
public class CurrElementExpressionsCO extends BaseVO
{
    private BigDecimal FLD_IDENTIFIER;
    private String     currElementName;
    private String     currElementId;
    private String     currElemDisplayName;
    private String     ELEMENT_NAME;
    private String     ELEMENT_ID;
    private String     MANDATORY_EXPR;
    private String     VISIBILITY_EXPR;
    private String     READONLY_EXPR;
    private String     ZERO_NOT_ALLOWED_EXPR;
    private String     DISPLAY_FIELD_NAME;
    private String     DEFAULT_VALUE_EXPR;
    private BigDecimal DFLT_VAL_EXPR_PRIORITY_YN;
    
    public BigDecimal getFLD_IDENTIFIER()
    {
        return FLD_IDENTIFIER;
    }
    public void setFLD_IDENTIFIER(BigDecimal fLD_IDENTIFIER)
    {
        FLD_IDENTIFIER = fLD_IDENTIFIER;
    }
    public String getELEMENT_NAME()
    {
        return ELEMENT_NAME;
    }
    public void setELEMENT_NAME(String eLEMENT_NAME)
    {
        ELEMENT_NAME = eLEMENT_NAME;
    }
    public String getELEMENT_ID()
    {
        return ELEMENT_ID;
    }
    public void setELEMENT_ID(String eLEMENT_ID)
    {
        ELEMENT_ID = eLEMENT_ID;
    }
    public String getMANDATORY_EXPR()
    {
        return MANDATORY_EXPR;
    }
    public void setMANDATORY_EXPR(String mANDATORY_EXPR)
    {
        MANDATORY_EXPR = mANDATORY_EXPR;
    }
    public String getVISIBILITY_EXPR()
    {
        return VISIBILITY_EXPR;
    }
    public void setVISIBILITY_EXPR(String vISIBILITY_EXPR)
    {
        VISIBILITY_EXPR = vISIBILITY_EXPR;
    }
    public String getREADONLY_EXPR()
    {
        return READONLY_EXPR;
    }
    public void setREADONLY_EXPR(String rEADONLY_EXPR)
    {
        READONLY_EXPR = rEADONLY_EXPR;
    }
    public String getZERO_NOT_ALLOWED_EXPR()
    {
        return ZERO_NOT_ALLOWED_EXPR;
    }
    public void setZERO_NOT_ALLOWED_EXPR(String zERO_NOT_ALLOWED_EXPR)
    {
        ZERO_NOT_ALLOWED_EXPR = zERO_NOT_ALLOWED_EXPR;
    }
    public String getDISPLAY_FIELD_NAME()
    {
        return DISPLAY_FIELD_NAME;
    }
    public void setDISPLAY_FIELD_NAME(String dISPLAY_FIELD_NAME)
    {
        DISPLAY_FIELD_NAME = dISPLAY_FIELD_NAME;
    }
    public String getCurrElementName()
    {
        return currElementName;
    }
    public void setCurrElementName(String currElementName)
    {
        this.currElementName = currElementName;
    }
    public String getCurrElemDisplayName()
    {
        return currElemDisplayName;
    }
    public void setCurrElemDisplayName(String currElemDisplayName)
    {
        this.currElemDisplayName = currElemDisplayName;
    }
    public String getCurrElementId()
    {
        return currElementId;
    }
    public void setCurrElementId(String currElementId)
    {
        this.currElementId = currElementId;
    }
    public String getDEFAULT_VALUE_EXPR()
    {
        return DEFAULT_VALUE_EXPR;
    }
    public void setDEFAULT_VALUE_EXPR(String dEFAULT_VALUE_EXPR)
    {
        DEFAULT_VALUE_EXPR = dEFAULT_VALUE_EXPR;
    }
    public BigDecimal getDFLT_VAL_EXPR_PRIORITY_YN()
    {
        return DFLT_VAL_EXPR_PRIORITY_YN;
    }
    public void setDFLT_VAL_EXPR_PRIORITY_YN(BigDecimal dFLT_VAL_EXPR_PRIORITY_YN)
    {
        DFLT_VAL_EXPR_PRIORITY_YN = dFLT_VAL_EXPR_PRIORITY_YN;
    }

}
