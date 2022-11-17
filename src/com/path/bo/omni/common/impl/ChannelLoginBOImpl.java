package com.path.bo.omni.common.impl;

import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;

import javax.crypto.SecretKey;

import com.path.bo.omni.common.ChannelLoginBO;
import com.path.bo.omni.common.base.OmniBaseBO;
import com.path.dao.omni.common.pull.OmniCommonPullDAO;
import com.path.dbmaps.vo.OC_APP_CHNLVO;
import com.path.lib.common.util.SecurityUtilsExt;
import com.path.ws.omni.vo.common.ChannelLoginRequest;

/**
 * 
 * 
 *
 * ChannelLoginBOImpl.java used to check on log in credentials
 */
public class ChannelLoginBOImpl extends OmniBaseBO implements ChannelLoginBO {

	private int keyMap = 1;
	public HashMap<Integer, PublicKey> frontEndPublicKeyHashMap = new HashMap();

	private OmniCommonPullDAO omniCommonPullDAO;
	private SecurityUtilsExt securityUtilsExt;

	@SuppressWarnings("unchecked")
	public boolean authenticateUser(ChannelLoginRequest criteria) throws Exception {

		OC_APP_CHNLVO appChnlVO = omniCommonPullDAO.returnAppChnlVO(criteria);
		if (null == appChnlVO) {
			System.out.println("error");
			return false;
		} else {
			return true;
		}

	}

	public boolean storeFrontEndPublicKeyKey(BigInteger frontEndPublicKey) throws Exception {
		// TODO Auto-generated method stub
		try {
			byte[] bytePublicKey = frontEndPublicKey.toByteArray();
			X509EncodedKeySpec keySpec = new X509EncodedKeySpec(bytePublicKey);
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			PublicKey publicKey = keyFactory.generatePublic(keySpec);
			System.out.println(publicKey);
			frontEndPublicKeyHashMap.put(keyMap, publicKey);
			keyMap++;
			System.out.println(getFrontEndPublicKeyHashMap());
			//PublicKey backEndPublicKey = SecurityUtilsExt.generatePairKey();
			
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	public HashMap<Integer, PublicKey> getMap() {
		return frontEndPublicKeyHashMap;
	}
	
	public void setOmniCommonPullDAO(OmniCommonPullDAO omniCommonPullDAO) {
		this.omniCommonPullDAO = omniCommonPullDAO;
	}

	public HashMap<Integer, PublicKey> getFrontEndPublicKeyHashMap() {
		return frontEndPublicKeyHashMap;
	}

	public void setFrontEndPublicKeyHashMap(HashMap<Integer, PublicKey> frontEndPublicKeyHashMap) {
		this.frontEndPublicKeyHashMap = frontEndPublicKeyHashMap;
	}

	
}
