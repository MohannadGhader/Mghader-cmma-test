package com.path.dbmaps.vo;

public class VersionCO {

	private String version;
	private int app;
	private int status;
	private int versionResult;

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public int getApp() {
		return app;
	}

	public void setApp(int app) {
		this.app = app;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getVersionResult() {
		return versionResult;
	}

	public void setVersionResult(int versionResult) {
		this.versionResult = versionResult;
	}

	public VersionCO(int app, int status, int versionResult) {
		super();
		this.app = app;
		this.status = status;
		this.versionResult = versionResult;
	}

}
