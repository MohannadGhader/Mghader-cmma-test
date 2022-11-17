package com.path.dbmaps.vo;

import com.path.vo.omni.common.base.OmniBaseVO;
import java.math.BigDecimal;

public class OC_USRVOKey extends OmniBaseVO {
    /**
     * This field corresponds to the database column OC_USR.USR_ID
     */
    private BigDecimal USR_ID;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column OC_USR.USR_ID
     *
     * @return the value of OC_USR.USR_ID
     */
    public BigDecimal getUSR_ID() {
        return USR_ID;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column OC_USR.USR_ID
     *
     * @param USR_ID the value for OC_USR.USR_ID
     */
    public void setUSR_ID(BigDecimal USR_ID) {
        this.USR_ID = USR_ID;
    }
}