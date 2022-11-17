package com.path.bo.common;

import java.math.BigDecimal;

import com.path.lib.log.Log;

public final class FormatUtils {
    /**
     * Private constructor to prevent class from instantiation
     */
    private FormatUtils()
    {
	Log.getInstance().warning("This class is utility and cannot be instantiated");
    }
	/**
	 * Format Account no by padding zeros to the left and concatenating with delimiter "-" 
	 * Eg : 0001-011-002102-00108101-001
	 * 		 
	 * @param accBR
	 * @param accCY
	 * @param accGL
	 * @param accCIF
	 * @param accSL
	 * @return
	 */
	
	public static String formatAccount(BigDecimal accBR, BigDecimal accCY,
			BigDecimal accGL, BigDecimal accCIF, BigDecimal accSL) {
		String formatAcc = null;
		try {
			formatAcc = String.format("%04d-", accBR.longValue())
					+ String.format("%03d-", accCY.longValue())
					+ String.format("%06d-", accGL.longValue())
					+ String.format("%08d-", accCIF.longValue())
					+ String.format("%03d", accSL.longValue());
		} catch (Exception ex) {
			Log.getInstance().error(ex, "Exception occured while formatting account");			 
		}
		return formatAcc;
	}

	/**
	 * Format CIF no by padding zeros to the left
	 * 
	 * @param cifNo
	 * @return
	 */
	 
	public static String formatCIF(BigDecimal cifNo) {
		String formatCIF = null;
		try {
			formatCIF = String.format("%08d", cifNo.longValue());
		} catch (Exception ex) {
			Log.getInstance().error(ex, "Exception occured while formatting CIF");			 
		}
		return formatCIF;
	}

}
