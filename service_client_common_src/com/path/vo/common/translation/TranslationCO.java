/**
 * 
 */
package com.path.vo.common.translation;

import java.util.ArrayList;

//import com.path.dbmaps.vo.SYS_PARAM_KEY_GROUPVO;
//import com.path.dbmaps.vo.SYS_PARAM_KEY_LABELVO;
//import com.path.dbmaps.vo.SYS_PARAM_KEY_LABEL_TRANSVO;
//import com.path.dbmaps.vo.SYS_PARAM_LOV_TRANSVO;
import com.path.lib.vo.BaseVO;

/**
 * Copyright 2013, Path Solutions Path Solutions retains all ownership rights to
 * this source code
 * 
 * @author: rabihelkhatib
 *
 *          TranslationCO.java used to store the Label translation Value objects
 */
@SuppressWarnings("serial")
public class TranslationCO extends BaseVO {
	private String progRefDesc;
	private String langDesc;
	private String prefTrans;
	private String lblValueTrans;
	private String grpValueTrans;
	private String keyLabelTextDesc;
	private String keyGroupTextDesc;
	private String globalRef;
	// added for import process
	private ArrayList<TranslationSC> impTransDet = new ArrayList<TranslationSC>();
	private String overwriteGroup;
	private String overwriteLabel;
	private String overwriteClient;
	private String isClientModified;

	private byte[] importedBytes;
	private String importWarningMsg; // Warning message of duplicate entries during import process

	public String getLangDesc() {
		return langDesc;
	}

	public void setLangDesc(String langDesc) {
		this.langDesc = langDesc;
	}

	public String getPrefTrans() {
		return prefTrans;
	}

	public void setPrefTrans(String prefTrans) {
		this.prefTrans = prefTrans;
	}

	public String getLblValueTrans() {
		return lblValueTrans;
	}

	public void setLblValueTrans(String lblValueTrans) {
		this.lblValueTrans = lblValueTrans;
	}

	public String getGrpValueTrans() {
		return grpValueTrans;
	}

	public void setGrpValueTrans(String grpValueTrans) {
		this.grpValueTrans = grpValueTrans;
	}

	public String getKeyLabelTextDesc() {
		return keyLabelTextDesc;
	}

	public void setKeyLabelTextDesc(String keyLabelTextDesc) {
		this.keyLabelTextDesc = keyLabelTextDesc;
	}

	public String getKeyGroupTextDesc() {
		return keyGroupTextDesc;
	}

	public void setKeyGroupTextDesc(String keyGroupTextDesc) {
		this.keyGroupTextDesc = keyGroupTextDesc;
	}

	public String getProgRefDesc() {
		return progRefDesc;
	}

	public void setProgRefDesc(String progRefDesc) {
		this.progRefDesc = progRefDesc;
	}

	public String getGlobalRef() {
		return globalRef;
	}

	public void setGlobalRef(String globalRef) {
		this.globalRef = globalRef;
	}

	public ArrayList<TranslationSC> getImpTransDet() {
		return impTransDet;
	}

	public void setImpTransDet(ArrayList<TranslationSC> impTransDet) {
		this.impTransDet = impTransDet;
	}

	public byte[] getImportedBytes() {
		return importedBytes;
	}

	public void setImportedBytes(byte[] importedBytes) {
		this.importedBytes = importedBytes;
	}

	public String getOverwriteGroup() {
		return overwriteGroup;
	}

	public void setOverwriteGroup(String overwriteGroup) {
		this.overwriteGroup = overwriteGroup;
	}

	public String getOverwriteLabel() {
		return overwriteLabel;
	}

	public void setOverwriteLabel(String overwriteLabel) {
		this.overwriteLabel = overwriteLabel;
	}

	public String getImportWarningMsg() {
		return importWarningMsg;
	}

	public void setImportWarningMsg(String importWarningMsg) {
		this.importWarningMsg = importWarningMsg;
	}

	public String getOverwriteClient() {
		return overwriteClient;
	}

	public void setOverwriteClient(String overwriteClient) {
		this.overwriteClient = overwriteClient;
	}

	public String getIsClientModified() {
		return isClientModified;
	}

	public void setIsClientModified(String isClientModified) {
		this.isClientModified = isClientModified;
	}

}
