package com.path.bo.common.smart;

import java.util.HashMap;

/**
 * 
 * Copyright 2012, Path Solutions Path Solutions retains all ownership rights to
 * this source code
 * 
 * @author: RabihElKhatib
 * 
 *          SmartConstant.java used to store the constant values of
 *          applications' names, windows ....
 */
public class SmartConstant
{
    // Constants holding values of the Smart Option Types
    public static final int PARAMETER = 0;
    public static final int SCANNEDDOCUMENT = 1;
    public static final int ADDITIONALTEXT = 2;
    public static final int ADDITIONALDATE = 3;
    public static final int ADDITIONALNUMBER = 4;
    public static final int EXTERNALPROGRAM = 5;
    public static final int HYPERLINK = 6;
    //ENH 519846 
    public static final int UPLOADED_FILE = 7;
    

    // Constants holding values of the Smart User/Window OPTs
    public static final String PREVIEW = "SRV";
    public static final String SCAN = "SAS";
    public static final String DETACH = "SDI";
    public static final String EDIT = "SED";
    public static final String UPDATE = "SUI";
    
    //ENH 519846 Media chosen to save the file on
    public static final String SAVE_TO_DB = "D";
    public static final String SAVE_TO_FILE = "F";

    //613682
    //BMO160192 - SMART to be Able to Have Expression for Mandatory Functionality
    public static final String IS_MANDATORY = "Y";

    static

    {
	// Create and put the specific windows' names and their conditions for
	// RET application
	HashMap<String, String> retMAP = new HashMap<String, String>();
	retMAP.put("Q000", "smartRet01");

	retMAP.put("M001", "smartRet02");

	retMAP.put("C002B", "smartRet03");
	retMAP.put("C002C", "smartRet03");
	retMAP.put("C015", "smartRet03");
	retMAP.put("C016B", "smartRet03");
	retMAP.put("S016C", "smartRet03");
	retMAP.put("P0044", "smartRet03");
	retMAP.put("P0048", "smartRet03");
	retMAP.put("INC01", "smartRet03");

	retMAP.put("A001", "smartRet04");
	retMAP.put("A002", "smartRet04");
	retMAP.put("A0012", "smartRet04");

	retMAP.put("UR00T", "smartRet05");

	// Create and put the specific windows' names and their conditions for
	// PROC application
	HashMap<String, String> radmMAP = new HashMap<String, String>();

	radmMAP.put("E000", "smartRadm01");
	radmMAP.put("V000", "smartRadm01");
	radmMAP.put("P031", "smartRadm01");
	radmMAP.put("P024", "smartRadm01");

	radmMAP.put("B001", "smartRadm02");

	radmMAP.put("M001", "smartRadm03");
	radmMAP.put("C002B", "smartRadm03");
	radmMAP.put("C002C", "smartRadm03");
	radmMAP.put("Y00", "smartRadm03");
	radmMAP.put("L0VT", "smartRadm03");
	radmMAP.put("BL001", "smartRadm03");
	radmMAP.put("F00B", "smartRadm03");
	radmMAP.put("P0062", "smartRadm03");
	radmMAP.put("P047", "smartRadm03");
	radmMAP.put("P020", "smartRadm03");
	radmMAP.put("ET000", "smartRadm03");
	radmMAP.put("P031", "smartRadm03");

	// Create and put the specific windows' names and their conditions for
	// PMS/DINV/TRS application
	HashMap<String, String> pmsMAP = new HashMap<String, String>();

	pmsMAP.put("P0033", "COND01");

	pmsMAP.put("P0031", "COND02");
	pmsMAP.put("P0041", "COND02");

	pmsMAP.put("P0032", "COND03");

	// Create and put the specific windows' names and their conditions for
	// ACC application
	HashMap<String, String> accMAP = new HashMap<String, String>();

	accMAP.put("C002B", "COND01");
	accMAP.put("C002C", "COND01");
	accMAP.put("C014B", "COND01");
	accMAP.put("C014C", "COND01");

	accMAP.put("A0019", "COND02");
	accMAP.put("A0030", "COND02");
	accMAP.put("A0031", "COND02");

	accMAP.put("A0032", "COND03");
	accMAP.put("A0033", "COND03");

	accMAP.put("B001", "COND04");
	accMAP.put("B008", "COND04");
	accMAP.put("B009", "COND04");

	accMAP.put("E00", "COND05");

	accMAP.put("A008", "COND06");

	// Create and put the specific windows' names and their conditions for
	// PROC application
	HashMap<String, String> procMAP = new HashMap<String, String>();

	procMAP.put("M001", "COND01");
	procMAP.put("P0002", "COND01");

	// Create and put the specific windows' names and their conditions for
	// NV application
	HashMap<String, String> nvMAP = new HashMap<String, String>();

	nvMAP.put("B00", "COND01");
	nvMAP.put("B001", "COND01");
	nvMAP.put("B002", "COND01");

	nvMAP.put("E00", "COND02");

	nvMAP.put("A008", "COND03");

	// Create and put the specific windows' names and their conditions for
	// TFA application
	HashMap<String, String> tfaMAP = new HashMap<String, String>();

	tfaMAP.put("OLC02", "COND01");
	tfaMAP.put("ILC01", "COND01");

	tfaMAP.put("LG02", "COND02");

	tfaMAP.put("DOM01", "COND03");
	tfaMAP.put("OLC02", "COND03");
	tfaMAP.put("AD01", "COND03");
	tfaMAP.put("ILC01", "COND03");

	// Create and put the specific windows' names and their conditions for
	// SADS application
	HashMap<String, String> sadsMAP = new HashMap<String, String>();

	sadsMAP.put("P005", "COND01");

	// Create and put the specific windows' names and their conditions for
	// CIF application
	HashMap<String, String> cifMAP = new HashMap<String, String>();

	cifMAP.put("F00I1", "COND01");
	cifMAP.put("C000", "COND01");
	cifMAP.put("N000", "COND01");

	// Create and put the specific Applications' names and their
	// Corresponding Hash maps

    }
    
}
