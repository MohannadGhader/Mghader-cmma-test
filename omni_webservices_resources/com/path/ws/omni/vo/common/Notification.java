package com.path.ws.omni.vo.common;

public class Notification {
	String API_URL_FCM;
	String AUTH_KEY_FCM;

	public String getAPI_URL_FCM() {
		return API_URL_FCM;
	}

	public void setAPI_URL_FCM(String aPI_URL_FCM) {
		API_URL_FCM = aPI_URL_FCM;
	}

	public String getAUTH_KEY_FCM() {
		return AUTH_KEY_FCM;
	}

	public void setAUTH_KEY_FCM(String aUTH_KEY_FCM) {
		AUTH_KEY_FCM = aUTH_KEY_FCM;
	}

	public Notification(String aPI_URL_FCM, String aUTH_KEY_FCM) {
		super();
		API_URL_FCM = aPI_URL_FCM;
		AUTH_KEY_FCM = aUTH_KEY_FCM;
	}

}
