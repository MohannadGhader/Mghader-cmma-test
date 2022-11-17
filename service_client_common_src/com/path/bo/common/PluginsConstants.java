package com.path.bo.common;

public class PluginsConstants
{
    // ActiveX for Application Launcher
    public static final String APPLNCHR_AX_VERSION = "1,0,0,0";
    public static final String APPLNCHR_CLSID = "CLSID:04962E9F-7FFF-4196-92EE-C0A680404F41";
	
	// ActiveX for TWAIN object version number
    public static final String TWAIN_AX_VERSION = "1,1,2,0";
    public static final String TWAIN_CLSID = "CLSID:4AF174BE-3D74-4570-B3DE-731FC23C96F6";

    // ActiveX for PRINT object version number
    public static final String PRINT_AX_VERSION = "1,0,0,0";
    public static final String PRINT_CLSID = "CLSID:405D4D7F-0832-4372-9501-03E11FEAF435";

    // ActiveX for PACI object version number
    public static final String PACI_AX_VERSION = "1,1,1,0";
    public static final String PACI_CLSID = "CLSID:CE31262E-11F1-4652-9193-560E2A0B6200";
    
    // ActiveX for MagTek object version number
    public static final String MAGTEK_AX_VERSION = "1,0,0,0";
    public static final String MAGTEK_CLSID = "CLSID:F4337B30-370A-4F79-8EA8-7F4067BB03AA";
    
    // ActiveX for pathCtrl object version number
    public static final String PATHCTRL_AX_VERSION = "1,0,1,0";
    public static final String NOTIF_CLSID = "CLSID:3A49D8B7-A0B4-4970-8DFD-039A6A04A49B";
    public static final String PATHCLIP_CLSID = "CLSID:5B95368C-EA5B-467E-831F-6F968AACF4E5";
    
    // ActiveX for IRIS scanning device
    public static final String IRIS_AX_VERSION = "1,1,0,0";
    public static final String IRIS_CLSID = "CLSID:E38DFA33-20FF-4F59-91CB-F27034A6E118";
    
    // ActiveX for PaniniWrapper object version number
    public static final String PANINI_AX_VERSION = "1,0,5,0";
    public static final String PANINI_CLSID = "CLSID:7F0D66A5-36E8-4358-A80E-8BC04C0946C7";
    
    // Chrome Extension for TWAIN object version number
    public static final String SCAN_EX_VERSION = "pathTwainChrome-1.0.6";
    
    // Chrome Extension for pathCtrl object version
    public static final String PATHCTRL_EX_VERSION = "pathCtrlChrome-1.0.3";

    /**
     * Method needed to return the APPLNCHR_CLSID, to avoid including static version upon compilation into Files 
     * @return
     */
    public static String returnAppLnchrCLSID()
    {
	return APPLNCHR_CLSID;
    }
    /**
     * Method needed to return the APPLNCHR_AX_VERSION, to avoid including static version upon compilation into Files 
     * @return
     */
    public static String returnAppLnchrVersion()
    {
	return APPLNCHR_AX_VERSION;
    }
}

