package com.path.vo.common;

import com.path.dbmaps.vo.CURRENCIESVO;
import com.path.dbmaps.vo.SYS_CONVERT_CURRENCY_TO_WORDSVO;
import com.path.dbmaps.vo.SYS_PARAM_ISO_CURRENCIESVO;
import com.path.struts2.lib.common.BaseObject;

public class CurrencyToWordsCO extends BaseObject {
	private SYS_CONVERT_CURRENCY_TO_WORDSVO currToWordsVO;
	private SYS_PARAM_ISO_CURRENCIESVO isoCurrVO;
	private CURRENCIESVO currVO;

	public SYS_CONVERT_CURRENCY_TO_WORDSVO getCurrToWordsVO() {
		return currToWordsVO;
	}

	public void setCurrToWordsVO(SYS_CONVERT_CURRENCY_TO_WORDSVO currToWordsVO) {
		this.currToWordsVO = currToWordsVO;
	}

	public SYS_PARAM_ISO_CURRENCIESVO getIsoCurrVO() {
		return isoCurrVO;
	}

	public void setIsoCurrVO(SYS_PARAM_ISO_CURRENCIESVO isoCurrVO) {
		this.isoCurrVO = isoCurrVO;
	}

	public CURRENCIESVO getCurrVO() {
		return currVO;
	}

	public void setCurrVO(CURRENCIESVO currVO) {
		this.currVO = currVO;
	}
}
