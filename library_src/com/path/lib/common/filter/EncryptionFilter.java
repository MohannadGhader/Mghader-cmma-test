package com.path.lib.common.filter;

import java.io.IOException;
import java.io.OutputStream;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Base64;
import java.util.Enumeration;
import java.util.Map;
import java.util.Properties;
import java.util.TimeZone;

import javax.crypto.SecretKey;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.path.bo.omni.common.base.OmniBaseBO;
import com.path.lib.common.base.BaseDAO;
import com.path.lib.common.base.ibatis3.SpringManagedTransaction;
import com.path.lib.common.util.SecurityUtilsExt;
import com.path.lib.log.Log;
import com.path.ws.omni.vo.common.ResponseBaseObject;

public class EncryptionFilter implements Filter {
	protected final static Log log = Log.getInstance();

	SecurityUtilsExt securityUtilsExt;
	static SpringManagedTransaction springManagedTransaction;

	String frString;
	int language_id;
	String RequestToken;
	String token;
	String secretKeyToken;
	String device_UUID;
	String res;
	String encryptedData;
	String encryptedKey;
	String secretKeyString;
	String initVector;
	String decryptedData;
	String responseData;
	String encryptedSecretKey;
	String encryptedInitVector;
	String decryptedSecretKey;
	String decryptedInitVector;
	String client_id;
	String driver_id_string;
	int driver_id;
	int deviceStatus;

	String admin_id_string;
	int admin_id;

	int app;
	byte[] initVectorByte;

	PrivateKey privateKey;
	PublicKey frontEndPublicKey;
	SecretKey secretKey;

	org.json.JSONObject newJsonObject;

	int VALID = 0;
	int EXPIRED = 3;
	int oldDevice = 1;

	DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

//	Connection conn = null;

	String url = "jdbc:mysql://localhost:3307";
	String dbName = "oadmmigdb";
	String driver = "com.mysql.jdbc.Driver";
	String databaseUserName = "root";
	String databasePassword = "1234";
	String privateKeyStr;
	String publicKeystrStr;

	Properties prop = new Properties();
	Connection conn;
	BaseDAO baseDAO = new BaseDAO();

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		try {
//		 TODO Auto-generated method stub
//			TimeZone.setDefault(TimeZone.getTimeZone("Asia/beirut"));
			HttpServletRequest httpServletRequest = (HttpServletRequest) request;

			HttpServletResponse httpServletResponse = (HttpServletResponse) response;
			BufferedServletResponseWrapper bufferedResponse = new BufferedServletResponseWrapper(httpServletResponse);
			HttpServletRequest orginalRequest = (HttpServletRequest) request;
			HttpServletResponse originalResponse = (HttpServletResponse) response;
			ModifyResponseBodyWrapper responseWrapper = new ModifyResponseBodyWrapper(originalResponse);

			MultiReadHttpServletRequest multiReadRequest = new MultiReadHttpServletRequest(
					(HttpServletRequest) request);
			OutputStream outputStream = response.getOutputStream();

			String pathInfo = httpServletRequest.getPathInfo();

			String method = multiReadRequest.getMethod();
			Map<String, String[]> parameters = httpServletRequest.getParameterMap();
			String u = multiReadRequest.getContentType();
			int l = multiReadRequest.getContentLength();

			Enumeration<String> headerNames = multiReadRequest.getHeaderNames();
			if (method.equals("POST")) {
				if (httpServletRequest.getPathInfo().equals("/rest/omniCommon/test")
//						|| (httpServletRequest.getPathInfo().equals("/rest/omniCommon/updateDriverLocation"))
				) {
					chain.doFilter(multiReadRequest, responseWrapper);
					responseData = responseWrapper.getResponseBody("utf-8");
					res = "{\"test\":\"" + responseData + "\"}";
					outputStream.write(res.getBytes());
					outputStream.flush();
					outputStream.close();
				} else {
					// get the request body as string
					String requestBody = IOUtils.toString(multiReadRequest.getInputStream());
					JsonObject convertedObject = new Gson().fromJson(requestBody, JsonObject.class);
					System.out.println("the request is ");
					System.out.println(convertedObject);
					frString = convertedObject.get("frontEndPublicKey").getAsString();
					frontEndPublicKey = SecurityUtilsExt.convertStringToPublicKey(frString);
					if (!httpServletRequest.getPathInfo().equals("/rest/omniCommon/handShake")) {
						// convert the string to gson then get the frontEndPublicKey from the gson
						log.info("get keys from db " + format.format(new java.util.Date()));

						try {
							prop = PropertiesLoaderUtils.loadAllProperties("db.properties");
							String url = prop.getProperty("url");
							String dbName = prop.getProperty("dbName");
							String d = dbName
									+ "?useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=Asia/Beirut";
							String databaseUserName = prop.getProperty("databaseUserName");
							String databasePassword = prop.getProperty("databasePassword");
							conn = DriverManager.getConnection(url + dbName, databaseUserName, databasePassword);
//							Statement stmt = conn.createStatement();
						} catch (Exception e) {
							e.printStackTrace();
						}
						try {
							String sql = "SELECT bPKey , bPrKey FROM edkeys WHERE fPKey =?";
//							conn = SpringManagedTransaction.getConnection();
//							conn = baseDAO.getSqlMap().returnConnection();
//							conn = OmniBaseBO.getConn();
							PreparedStatement s = conn.prepareStatement(sql);
							s.setString(1, frString);
							ResultSet result = s.executeQuery();

//							 PreparedStatement pst = conn.prepareStatement(sql);
//							 ResultSet result = pst.executeQuery();
//							  while (result.next()) {
							if (result.next()) {
								privateKeyStr = result.getString("bPrKey");
								publicKeystrStr = result.getString("bPKey");
							}
							conn.close();
						} catch (Exception e) {
							log.info(e.getMessage());
						}
						log.info(" fin get keys from db " + format.format(new java.util.Date()));
						log.info(" debut decryption " + format.format(new java.util.Date()));

						PrivateKey privateKey = securityUtilsExt.convertStringToPrivateKey(privateKeyStr);

						encryptedData = convertedObject.get("encryptedData").getAsString();
						encryptedSecretKey = convertedObject.get("encryptedSecretKey").getAsString();
						encryptedInitVector = convertedObject.get("encryptedInitVector").getAsString();
//
////						String secretKeyString = OmniBaseBO.getBackEndKeyHashMap().get(publicKey).getSecretKeyString();
////						initVectorByte = OmniBaseBO.getBackEndKeyHashMap().get(publicKey).getInitVector();
//						privateKey = OmniBaseBO.getBackEndKeyHashMap().get(publicKey).getBackEndPrivateKey();
						decryptedSecretKey = SecurityUtilsExt.decryptWithPrivateKeyRSA_OAEP(encryptedSecretKey,
								privateKey);
						decryptedInitVector = SecurityUtilsExt.decryptWithPrivateKeyRSA_OAEP(encryptedInitVector,
								privateKey);
						initVectorByte = Base64.getDecoder().decode(decryptedInitVector.getBytes("UTF-8"));
//						decryptedData = SecurityUtilsExt.decrypt(encryptedData, initVectorByte, secretKeyString);
						decryptedData = SecurityUtilsExt.decrypt(encryptedData, initVectorByte, decryptedSecretKey);
//
						newJsonObject = new org.json.JSONObject(decryptedData);

						newJsonObject.put("frontEndPublicKey", frString);
						String data = newJsonObject.toString();
						device_UUID = newJsonObject.get("device_UUID").toString();
						if (newJsonObject.get("app").toString().equals("0"))
						client_id = newJsonObject.get("client_id").toString();
						ModifyRequestBodyWrapper requestWrapper = new ModifyRequestBodyWrapper(orginalRequest, data);
						log.info(" fin decryption " + format.format(new java.util.Date()));
						log.info(" do filter " + format.format(new java.util.Date()));

						chain.doFilter(requestWrapper, responseWrapper);
//						chain.doFilter(multiReadRequest, responseWrapper);
						log.info(" fin do filter " + format.format(new java.util.Date()));

					}
					// chain.doFilter(multiReadRequest, responseWrapper);
					// pass the wrappers on to the next entry
					else {
						log.info(" debut do filter" + format.format(new java.util.Date()));

						chain.doFilter(multiReadRequest, responseWrapper);
						log.info(" fin do filter" + format.format(new java.util.Date()));
					}
					log.info(" generate secret key" + format.format(new java.util.Date()));

					secretKey = securityUtilsExt.generateSecretKey();
					initVectorByte = securityUtilsExt.generateinitVector();
					secretKeyString = Base64.getEncoder().encodeToString(secretKey.getEncoded());
					String initVecString = Base64.getEncoder().encodeToString(initVectorByte);
					log.info(" fin generate secret key " + format.format(new java.util.Date()));

					responseData = responseWrapper.getResponseBody("utf-8");

					if (httpServletRequest.getPathInfo().equals("/rest/omniCommon/handShake")) {
//					httpServletResponse.setHeader("Content-Type", "application/json;charset=UTF-8");
						String backEndPublicKeyString = OmniBaseBO.getBackEndKeyHashMap().get(frontEndPublicKey)
								.getBackEndPublicKeyString();

//						PublicKey backEndPulicKey = OmniBaseBO.getBackEndKeyHashMap().get(publicKey)
//								.getBackEndPublicKey();

//						PrivateKey backEndPrivateKey = OmniBaseBO.getBackEndKeyHashMap().get(frontEndPublicKey)
//								.getBackEndPrivateKey();

//						String encryptedSecretKeyTa3ule = SecurityUtilsExt.encryptRSA_OAEP(backEndPulicKey,
//								secretKey.getEncoded());
//						String decryptSecretKeyTa3ule = SecurityUtilsExt
//								.decryptWithPrivateKeyRSA_OAEP(encryptedSecretKeyTa3ule, backEndPrivateKey);
						log.info(" debut encryption " + format.format(new java.util.Date()));

						encryptedKey = SecurityUtilsExt.encryptRSA_OAEP(frontEndPublicKey, secretKey.getEncoded());
						initVector = SecurityUtilsExt.encryptRSA_OAEP(frontEndPublicKey, initVectorByte);

						res = "{\"backEndPublicKeyString\":\"" + backEndPublicKeyString + "\"" + " , "
								+ "\"encryptedKey\":\"" + encryptedKey + "\"" + "," + "\"initVector\":\"" + initVector
								+ "\"}";
//						res = "{\"backEndPublicKeyString\":\"" + publicKeystrStr + "\"" + " , "
//								+ "\"encryptedKey\":\"" + encryptedKey + "\"" + "," + "\"initVector\":\"" + initVector
//								+ "\"}";
						log.info(" fin encryption " + format.format(new java.util.Date()));

					}

					else {
						if (newJsonObject.get("app").toString().equals("2")) {
							admin_id_string = newJsonObject.get("admin_id").toString();

							admin_id = Integer.parseInt(admin_id_string);

							if (OmniBaseBO.getAdminStatusHashMap().containsKey(admin_id)) {
								if (OmniBaseBO.getAdminStatusHashMap().get(admin_id) == 0) {
									ResponseBaseObject responseBaseObject = new ResponseBaseObject();
									responseBaseObject.setErrorCode(9038);
									responseBaseObject.setErrorDesc("you are removed, you can't use the app");
									responseData = "{\"errorCodeAdminStatus\":" + 9038 + " , " + "\"errorDesc\":\""
											+ "you are removed, you can't use the app" + "\"}";
									OmniBaseBO.getAdminStatusHashMap().remove(admin_id);
								}
							}
						}
						if (newJsonObject.get("app").toString().equals("0")) {
							if (OmniBaseBO.getClientKeysHashMap().containsKey(device_UUID)) {
								if (OmniBaseBO.getClientKeysHashMap().get(device_UUID).getStatus() == EXPIRED) {
									token = OmniBaseBO.getClientKeysHashMap().get(device_UUID).getToken();
									responseData = securityUtilsExt.addTokenToResponse(token, responseData);
									OmniBaseBO.getClientKeysHashMap().get(device_UUID).setStatus(VALID);

								}
							}
						}
						if (newJsonObject.get("app").toString().equals("1")) {

							if (OmniBaseBO.getDriverKeysHashMap().containsKey(device_UUID)) {
								if (OmniBaseBO.getDriverKeysHashMap().get(device_UUID).getStatus() == EXPIRED) {
									token = OmniBaseBO.getDriverKeysHashMap().get(device_UUID).getToken();
									responseData = securityUtilsExt.addTokenToResponse(token, responseData);
									OmniBaseBO.getDriverKeysHashMap().get(device_UUID).setStatus(VALID);
								}
							}
						}
						if (newJsonObject.get("app").toString().equals("2")) {
							if (OmniBaseBO.getAdminKeysHashMap().containsKey(device_UUID)) {
								if (OmniBaseBO.getAdminKeysHashMap().get(device_UUID).getStatus() == EXPIRED) {
									token = OmniBaseBO.getAdminKeysHashMap().get(device_UUID).getToken();
									responseData = securityUtilsExt.addTokenToResponse(token, responseData);
									OmniBaseBO.getAdminKeysHashMap().get(device_UUID).setStatus(VALID);
								}
							}
						}
						if (newJsonObject.get("app").toString().equals("0")) {
							if (OmniBaseBO.getClientDeviceHashMap().containsKey(device_UUID)
									&& !httpServletRequest.getPathInfo().equals("/rest/omniCommon/validateOTP")) {
//								if (OmniBaseBO.getClientDeviceHashMap().get(device_UUID).getClient_id() == Integer
//										.parseInt(client_id)
//										&& OmniBaseBO.getClientDeviceHashMap().get(device_UUID)
//												.getClient_status() == oldDevice) {

								responseData = "{\"errorCodeAccountDevice\":" + 9043 + " , " + "\"errorDesc\":\""
										+ "A new device use this account" + "\"}";

//								}
								OmniBaseBO.getClientDeviceHashMap().remove(device_UUID);
							}

							if (OmniBaseBO.getBlockClientHashmap().containsKey(device_UUID)) {
								if (OmniBaseBO.getBlockClientHashmap().get(device_UUID).getStatus() == 1) {
									ResponseBaseObject responseBaseObject = new ResponseBaseObject();
									responseBaseObject.setErrorCode(9027);
									responseBaseObject
											.setErrorDesc("You are blocked , you are reached possible attemps");
									responseData = "{\"errorCode\":" + 9027 + " , " + "\"errorDesc\":\""
											+ "You are blocked" + "\"}";
									OmniBaseBO.getBlockClientHashmap().get(device_UUID).setStatus(0);
								}
							}
						}
						if (newJsonObject.get("app").toString().equals("1")) {
							if (OmniBaseBO.getBlockDriverHashmap().containsKey(device_UUID)) {
								if (OmniBaseBO.getBlockDriverHashmap().get(device_UUID).getStatus() == 1) {
									ResponseBaseObject responseBaseObject = new ResponseBaseObject();
									responseBaseObject.setErrorCode(9027);
									responseBaseObject
											.setErrorDesc("You are blocked , you are reached possible attemps");
									responseData = "{\"errorCode\":" + 9027 + " , " + "\"errorDesc\":\""
											+ "You are blocked" + "\"}";
									OmniBaseBO.getBlockDriverHashmap().get(device_UUID).setStatus(0);

								}
							}
							driver_id_string = newJsonObject.get("driver_id").toString();

							driver_id = Integer.parseInt(driver_id_string);

							if (OmniBaseBO.getDriverAvailabilityHashMap().containsKey(driver_id)) {
								if (OmniBaseBO.getDriverAvailabilityHashMap().get(driver_id) == 0) {
									ResponseBaseObject responseBaseObject = new ResponseBaseObject();
									responseBaseObject.setErrorCode(9038);
									responseBaseObject.setErrorDesc("you are removed, you can't use the app");
									responseData = "{\"errorCodeDriverAvailability\":" + 9038 + " , "
											+ "\"errorDesc\":\"" + "you are removed, you can't use the app" + "\"}";
									OmniBaseBO.getDriverAvailabilityHashMap().remove(driver_id);
								}
							}
						}
						if (OmniBaseBO.getVersionHashMap().containsKey(device_UUID)) {
							if (OmniBaseBO.getVersionHashMap().get(device_UUID).getApp() == Integer
									.parseInt(newJsonObject.get("app").toString())) {
								if (OmniBaseBO.getVersionHashMap().get(device_UUID).getVersionResult() == -1) {
									ResponseBaseObject responseBaseObject = new ResponseBaseObject();
									if (OmniBaseBO.getVersionHashMap().get(device_UUID).getStatus() == 1) {

										responseBaseObject.setErrorCode(9034);
										responseBaseObject.setErrorDesc("update mandatory");
										responseData = responseData + "{\"errorUpdateCode\":" + 9034 + " , "
												+ "\"errorUpdateDesc\":\"" + "update mandatory" + "\"}";
									} else {
										responseBaseObject.setErrorCode(9035);
										responseBaseObject.setErrorDesc("update optional");
//										String[] strSplit = responseData.split("}", 0);
//										String str = strSplit[0];
										String str = responseData.substring(1);
										responseData = "{\"errorUpdateCode\":" + 9035 + " , " + "\"errorUpdateDesc\":\""
												+ "update optional\"" + "," + str;
									}
									OmniBaseBO.getVersionHashMap().remove(device_UUID);

									httpServletResponse.setHeader("Content-Type", "application/json");
									int le = responseData.length();
									responseWrapper.setStatus(200);

								}

							}
						}
						int q = secretKey.toString().getBytes().length;

						String encryptedData = securityUtilsExt.encrypt(responseData, initVectorByte, secretKeyString);
						encryptedKey = SecurityUtilsExt.encryptRSA_OAEP(frontEndPublicKey, secretKey.getEncoded());
						initVector = SecurityUtilsExt.encryptRSA_OAEP(frontEndPublicKey, initVectorByte);

						res = "{\"encryptedKey\":\"" + encryptedKey + "\"" + " , " + "\"encryptedData\":\""
								+ encryptedData + "\"" + "," + "\"initVector\":\"" + initVector + "\"}";
						int length = res.length();
//						httpServletResponse.setContentLength(length);
						String s = String.valueOf(length);
//						httpServletResponse.setHeader("Content-length",s );
						responseWrapper.setHeader("Content-length", s);
						responseWrapper.setStatus(200);
					}
//					Collection<String> headerNamesRessponse = responseWrapper.getHeaderNames();
//					String ur = responseWrapper.getContentType();
//					String m = responseWrapper.getHeader("Content-length");
//					httpServletResponse.getHeader("Content-length");
//					responseWrapper.getStatus();
//					
//
//					httpServletResponse.getContentType();
					log.info(" debut response " + format.format(new java.util.Date()));

					outputStream.write(res.getBytes());
					outputStream.flush();
					outputStream.close();
					log.info(" fin response " + format.format(new java.util.Date()));

				}
			} else {
				try {
					chain.doFilter(multiReadRequest, responseWrapper);
				} catch (Exception e) {
					log.info(e.getMessage());
				}
			}

		} catch (Exception e) {
			log.info(e.getMessage());
			e.printStackTrace();
		}

	}

	public void init(FilterConfig filterConfig) {
	}

	/**
	 * exists in the interface
	 */
	public void destroy() {
		log.warning("inherited from the parent class");
	}

}