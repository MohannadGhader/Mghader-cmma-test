package com.path.lib.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.MGF1ParameterSpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.sql.Time;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;
import java.util.Random;
import java.util.regex.Pattern;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SealedObject;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.OAEPParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PSource.PSpecified;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base32;
import org.apache.commons.codec.binary.Hex;
import org.springframework.util.Base64Utils;

import com.path.dao.omni.common.push.OmniCommonPushDAO;
import com.path.dbmaps.vo.DeviceKeysSC;
import com.path.lib.log.Log;
import com.path.ws.omni.vo.common.Notification;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;

import de.taimos.totp.TOTP;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import net.sf.json.JSONObject;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

//import de.taimos.totp.TOTP;

/**
 * DENISK_LATEST_VERS_UPDATED Copyright 2013, Path Solutions Path Solutions
 * retains all ownership rights to this source code
 * 
 * @author: deniskhaddad
 * 
 *          SecurityUtilsExt.java used to provide methods for security Used By
 *          PB code, and within Java aslo
 */
public final class SecurityUtilsExt {
	public final static String ALGORITHM = "AES";
	public final static String ALGORITHM_CBC_NOPADDING = "AES/CBC/NoPadding";
	private final static String ALGORITHM_CBC_NOPADDING_PWD = "PATHSOLUTIONS123";
	private final static String ALGORITHM_SESSION_TOKEN_PWD = "123PATHSOLUTIONS";
	public final static String DEFAULT_ENCODING = "UTF-8";
	public final static String SESSION_TOKEN_KEY = "PATH_SESSION_TOKEN";
	public final static String PATH_ENC_PWD = "PATH_ENC_PWD";
	private final static int ITERATIONS = 20 * 1000;
	private final static int saltLen = 32;
	private final static int DESIREDKEYLEN = 256;
	private static final Random RANDOM = new SecureRandom();
	static Properties prop = new Properties();
//	public final static String AUTH_KEY_FCM = "AAAA5k1x9Q4:APA91bGLKs1MNTDQ1rwgZ5htnheBKbR8aEq13G1Rgvs4U8oWpTcrJMhUwtCd9uHtv5cFqcvvDmzEmqGhgTVPMjQB9kI7V6_Xt4VBos7vC9bchyu65v4APw7ofNkjthdaSVcoVmHVIwFR";
//	public final static String API_URL_FCM = "https://fcm.googleapis.com/fcm/send";

	protected final static Log log = Log.getInstance();
	static org.json.JSONObject newJsonObject;
	static int VALID = 0;

	static OmniCommonPushDAO omniCommonPushDAO;

	/**
	 * Private constructor to prevent class from instantiation
	 */
	private SecurityUtilsExt() {
		// no need to include anything since this class is used for PB to call
		// security encryption process
	}

	/**
	 * 
	 * Used for encryption using AES Java Algorithm
	 * 
	 * @param theKey
	 * @param theValue
	 * @return
	 */
	public static String encryptAES(String theKey, String theValue) throws Exception {
		if (theKey != null && theKey.length() != 16) {
			throw new Exception("ERROR: Key should be exact of 16 Character Length");
		}
		Cipher c = Cipher.getInstance(ALGORITHM);
		Key aesKey = new SecretKeySpec(theKey.getBytes(DEFAULT_ENCODING), ALGORITHM);
		c.init(Cipher.ENCRYPT_MODE, aesKey);
		byte[] encVal = c.doFinal(theValue.getBytes(DEFAULT_ENCODING));
		return encodeB64(encVal);
	}

	/**
	 * Method that perform Cypher Java standard encryption
	 * 
	 * @param theAlgorithm the Algorithm used for Encryption Example AES,
	 *                     AES/CBC/PKCS5Padding
	 * @param theKey       The secret Key to encrypt with
	 * @param theIv        THe Initialization Vector to use in encryption
	 * @param theValue     Value to encrypt
	 * @return encrypted value
	 * @throws Exception
	 */
	public static String encryptCustom(String theAlgorithm, String theKey, String theIv, String theValue)
			throws Exception {
		Cipher cipher = Cipher.getInstance(theAlgorithm);
		cipher.init(Cipher.ENCRYPT_MODE, generateKey(theKey), generateIv(theIv));
		return encodeB64(cipher.doFinal(theValue.getBytes()));
	}

	/**
	 * Method that perform Cypher Java standard encryption
	 * 
	 * @param theAlgorithm the Algorithm used for Encryption Example AES,
	 *                     AES/CBC/PKCS5Padding
	 * @param theKey       The secret Key to encrypt with
	 * @param theIv        THe Initialization Vector to use in encryption
	 * @param theValue     Value to encrypt
	 * @return encrypted value
	 * @throws Exception
	 */
	public static String encryptCustom(String theAlgorithm, byte[] theKey, byte[] theIv, byte[] theValue)
			throws Exception {
		Cipher cipher = Cipher.getInstance(theAlgorithm);
		cipher.init(Cipher.ENCRYPT_MODE, generateKey(theKey), generateIv(theIv));
		return encodeB64(cipher.doFinal(theValue));
	}

	/**
	 * Method that perform Cypher Java standard decryption
	 * 
	 * @param theAlgorithm the Algorithm used for decryption Example AES,
	 *                     AES/CBC/PKCS5Padding
	 * @param theKey       The secret Key to derypt with
	 * @param theIv        THe Initialization Vector to use in decryption
	 * @param theValue     Value to derypt
	 * @return decrypted value
	 * @throws Exception
	 */
	public static String decryptCustom(String theAlgorithm, String theKey, String theIv, String theValue)
			throws Exception {
		Cipher cipher = Cipher.getInstance(theAlgorithm);
		cipher.init(Cipher.DECRYPT_MODE, generateKey(theKey), generateIv(theIv));
		return new String(cipher.doFinal(decodeB64(theValue)));
	}

	/**
	 * Method that perform Cypher Java standard decryption
	 * 
	 * @param theAlgorithm the Algorithm used for decryption Example AES,
	 *                     AES/CBC/PKCS5Padding
	 * @param theKey       The secret Key to derypt with
	 * @param theIv        THe Initialization Vector to use in decryption
	 * @param theValue     Value to derypt
	 * @return decrypted value
	 * @throws Exception
	 */
	public static String decryptCustom(String theAlgorithm, byte[] theKey, byte[] theIv, byte[] theValue)
			throws Exception {
		Cipher cipher = Cipher.getInstance(theAlgorithm);
		cipher.init(Cipher.DECRYPT_MODE, generateKey(theKey), generateIv(theIv));
		return new String(cipher.doFinal(SecurityUtils.decodeB64NoCharEncoding(theValue)));
	}

	private static Key generateKey(String theKeyValue) throws Exception {
		return generateKey(theKeyValue.getBytes(DEFAULT_ENCODING));

	}

	private static Key generateKey(byte[] theKeyValue) throws Exception {
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		byte[] key = md.digest(theKeyValue);
		return new SecretKeySpec(key, ALGORITHM);

	}

	private static AlgorithmParameterSpec generateIv(String theIv) throws Exception {
		return generateIv(theIv.getBytes(DEFAULT_ENCODING));
	}

	private static AlgorithmParameterSpec generateIv(byte[] theIv) throws Exception {
		return new IvParameterSpec(theIv);
	}

	/**
	 * 
	 * Used for Base 64 encoding
	 * 
	 * @param theBytes
	 * @return
	 */
	public static String encodeB64(byte[] theBytes) throws Exception {
		String result = "";
		if (theBytes != null) {
			result = new String(Base64Utils.encode(theBytes), DEFAULT_ENCODING);
		}
		return result;
	}

	/**
	 * Method to decode BAse64 encoded String as UTF-8 encoding and return related
	 * bytes
	 * 
	 * @param theEncodedValue, the Encoded Values
	 * @return Byte array of the decoded result
	 * @throws Exception
	 */
	public static byte[] decodeB64(String theEncodedValue) throws Exception {
		byte[] result = null;
		if (theEncodedValue != null) {
			result = Base64Utils.decode(theEncodedValue.getBytes(DEFAULT_ENCODING));
		}
		return result;
	}

	/**
	 * 
	 * Used for decryption of encrypted Data using particular Key
	 * 
	 * @param theKey
	 * @param encryptedData
	 * @return
	 * @throws Exception
	 */
	public static String decryptAES(String theKey, String encryptedData) throws Exception {
		if (theKey != null && theKey.length() != 16) {
			throw new Exception("ERROR: Key should be of 16 Character Length");
		}
		Key aesKey = new SecretKeySpec(theKey.getBytes(DEFAULT_ENCODING), ALGORITHM);
		Cipher c = Cipher.getInstance(ALGORITHM);
		c.init(Cipher.DECRYPT_MODE, aesKey);
		byte[] decodedValue = Base64Utils.decode(encryptedData.getBytes(DEFAULT_ENCODING));
		byte[] decValue = c.doFinal(decodedValue);
		return new String(decValue, DEFAULT_ENCODING);
	}

	public static byte[] encrypt(String response, Key frontEndPublicKey) throws IllegalBlockSizeException,
			BadPaddingException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException {
		Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		cipher.init(Cipher.ENCRYPT_MODE, frontEndPublicKey);
		byte[] input = response.getBytes();
		cipher.update(input);
		byte[] cipherText = cipher.doFinal();
		return cipherText;
	}

	/**
	 * generate the secret key for enryption
	 * 
	 * @return
	 * 
	 */
	public static SecretKey generateSecretKey() throws NoSuchAlgorithmException {
		KeyGenerator keyGen = KeyGenerator.getInstance("AES");
		keyGen.init(128);// 128
		SecretKey secretKey = keyGen.generateKey();
		byte[] byteSecretKey = secretKey.getEncoded();
		return secretKey;
	}

	/**
	 * method that generate initial vector
	 * 
	 * @return
	 */
	public static byte[] generateinitVector() {
		SecureRandom randomSecureRandom = new SecureRandom();
		byte[] iv = new byte[16];
		randomSecureRandom.nextBytes(iv);
		return iv;
	}

	/**
	 * Used for encryption using AES Java Algorithm
	 * 
	 * @param value
	 * @param initVector
	 * @param key
	 * @return
	 */
	public static String encrypt(String value, byte[] initVector, String key) {
		try {

			IvParameterSpec iv = new IvParameterSpec(initVector);
			SecretKeySpec skeySpec = new SecretKeySpec(Base64.getDecoder().decode(key), "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
//			byte[] encrypted = cipher.doFinal(value.getBytes("utf-8"));

			byte[] encrypted = cipher.doFinal(value.getBytes(StandardCharsets.UTF_8));
//			byte[] encrypted = cipher.doFinal(value.getBytes());
			String i = new String(encrypted, "utf-8");
			return Base64.getEncoder().encodeToString(encrypted);
		} catch (Exception ex) {
			ex.printStackTrace();
			log.info(ex.getMessage());
		}
		return null;
	}

	/**
	 * Used for decryption using AES Java Algorithm
	 * 
	 * @param value
	 * @param initVector
	 * @param key
	 * @return
	 */
	public static String decrypt(String value, byte[] initVector, String key) {
		try {
			IvParameterSpec iv = new IvParameterSpec(initVector);
			SecretKeySpec skeySpec = new SecretKeySpec(Base64.getDecoder().decode(key), "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
			cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
			byte[] t = Base64.getDecoder().decode(value.getBytes("UTF-8"));
			byte[] decrypted = cipher.doFinal(t);
//			return new String(decrypted , "UTF-8");
			return new String(decrypted, StandardCharsets.UTF_8);
		} catch (Exception ex) {
			ex.printStackTrace();
			log.info(ex.getMessage());
			return null;
		}
//		return null;
	}

	/**
	 * encrypt the secret key using the public key of the front end
	 * 
	 * @param frontEndPublicKey
	 * @param secretKey
	 * @return
	 * @throws Exception
	 */
	public static String encryptRSA_OAEP(PublicKey frontEndPublicKey, byte[] secretKey) {
		try {
			Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA-256AndMGF1Padding");
			cipher.init(Cipher.ENCRYPT_MODE, frontEndPublicKey);
			byte[] cipherSecretKey = cipher.doFinal(secretKey);
			String s = new String(cipherSecretKey, "UTF-8");
			System.out.println(encodeB64(cipherSecretKey));
			return encodeB64(cipherSecretKey);
		} catch (Exception e) {
			log.info(e.getMessage());
			return null;
		}
	}

	/**
	 * method that decrypt with backEnd private key
	 * 
	 * @param text
	 * @param privateKey
	 * @return
	 * @throws Exception
	 */
	public static String decryptWithPrivateKeyRSA_OAEP(String text, PrivateKey privateKey) throws Exception {
		try {
//			Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA-256AndMGF1Padding");
//			cipher.init(Cipher.PRIVATE_KEY, privateKey);
			byte[] textByte = Base64.getDecoder().decode(text.getBytes("UTF-8"));
//			cipher.update(textByte);
			// Decrypting the text

//			byte[] decipheredText = cipher.doFinal(textByte);
//			System.out.println(new String(decipheredText));
//			encodeB64(decipheredText);
//			return new String(decipheredText,StandardCharsets.UTF_8);

//			Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPPadding");
			Cipher oaepFromInit = Cipher.getInstance("RSA/ECB/OAEPPadding");
			OAEPParameterSpec oaepParams = new OAEPParameterSpec("SHA-256", "MGF1", new MGF1ParameterSpec("SHA-1"),
					PSpecified.DEFAULT);
			oaepFromInit.init(Cipher.DECRYPT_MODE, privateKey, oaepParams);
			byte[] pt = oaepFromInit.doFinal(textByte);
			String dec = encodeB64(pt);

			return dec;
		} catch (Exception e) {
			log.info(e.getMessage());
			System.out.println(e.getMessage());
			return null;
		}

	}

	/**
	 * generate public key and private key for back end
	 * 
	 * @throws NoSuchAlgorithmException
	 * 
	 */
	public static KeyPair generatePairKey() throws NoSuchAlgorithmException {
		try {
			KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
			kpg.initialize(1024);
			return kpg.generateKeyPair();
		} catch (Exception e) {
			log.info(e.getMessage());
			return null;
		}
	}

	/**
	 * method that convert bigInteger to public key
	 * 
	 * @param publicKey as BigInteger
	 * @return publicKey
	 * @throws InvalidKeySpecException ,NoSuchAlgorithmException
	 * 
	 */

	public static PublicKey convertToPublicKey(BigInteger publicKey)
			throws InvalidKeySpecException, NoSuchAlgorithmException {
		try {
			byte[] publicKeyAsByte = publicKey.toByteArray();
			X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKeyAsByte);

			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			PublicKey pubKey = keyFactory.generatePublic(keySpec);
			return pubKey;
		} catch (Exception e) {
			// TODO: handle exception
			log.info(e.getMessage());
			return null;
		}
	}

	public static PublicKey convertStringToPublicKey(String frontEndPublicKey) {
		try {
			byte[] decoded = org.apache.commons.codec.binary.Base64.decodeBase64(frontEndPublicKey);
			org.bouncycastle.asn1.pkcs.RSAPublicKey pkcs1PublicKey = org.bouncycastle.asn1.pkcs.RSAPublicKey
					.getInstance(decoded);
			RSAPublicKeySpec keySpec = new RSAPublicKeySpec(pkcs1PublicKey.getModulus(),
					pkcs1PublicKey.getPublicExponent());
			KeyFactory kf = KeyFactory.getInstance("RSA");
			return kf.generatePublic(keySpec);
		} catch (Exception e) {
			// TODO: handle exception
			log.info(e.getMessage());
			return null;
		}
	}

	/**
	 * method that convert string to private key
	 * 
	 * @param backEndPrivateKeyStr
	 * @return
	 */
	public static PrivateKey convertStringToPrivateKey(String backEndPrivateKeyStr) {
		try {
			byte[] sigBytes = Base64.getDecoder().decode(backEndPrivateKeyStr.getBytes("UTF-8"));
			PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(sigBytes);
			KeyFactory keyFact = KeyFactory.getInstance("RSA");
			PrivateKey backEndpPivateKey = keyFact.generatePrivate(privateKeySpec); // throws exception
			return backEndpPivateKey;
		} catch (Exception e) {
			log.info(e.getMessage());
			return null;
		}
	}

	/**
	 * method that encrypt an object @param secretKey
	 * 
	 * @param secretKey
	 * @param object
	 * @throws IOException
	 * @throws IllegalBlockSizeException
	 * @throws InvalidKeyException
	 * @throws NoSuchPaddingException
	 * @throws NoSuchAlgorithmException
	 * 
	 * 
	 */

	public static void EncryptObject(byte[] secretKey, Object object) throws NoSuchAlgorithmException,
			NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, IOException {

		SecretKey originalKey = new SecretKeySpec(secretKey, 0, secretKey.length, "AES");
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.ENCRYPT_MODE, originalKey);
		SealedObject sealedObject = new SealedObject((Serializable) object, cipher);
		System.out.println(sealedObject);
	}

	/**
	 * method that encrypt a String with secret key
	 * 
	 * @param secretKey
	 * @param message
	 * @return
	 * @throws IOException
	 * @throws IllegalBlockSizeException
	 * @throws InvalidKeyException
	 * @throws NoSuchPaddingException
	 * @throws NoSuchAlgorithmException
	 */
	public static String encryptWithSecretKeyAES(SecretKey secretKey, String data) throws NoSuchAlgorithmException,
			NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, IOException, BadPaddingException {

		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, secretKey);
		byte[] byteMessage = data.getBytes("UTF-8");
		byte[] encrypted = cipher.doFinal(byteMessage);
		return new String(encrypted);

	}

	/**
	 * method that decrypt a string with secret key
	 * 
	 * @param secretKey
	 * @param encryptedMessage
	 * @return
	 * @throws NoSuchPaddingException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeyException
	 * @throws BadPaddingException
	 * @throws IllegalBlockSizeException
	 * 
	 */
	public static String decryptWithSecretKey(SecretKey secretKey, String encryptedMessage)
			throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException,
			BadPaddingException {

		byte[] decodedBytesEncryptedText = Base64.getDecoder().decode(encryptedMessage.getBytes());
		Cipher cipher2 = Cipher.getInstance("AES");
		cipher2.init(Cipher.DECRYPT_MODE, secretKey);
		byte[] byteDecrepted = cipher2.doFinal(decodedBytesEncryptedText);
		return new String(byteDecrepted);

	}

	/**
	 * method that decrypt a front end secret key (encrypted with back end public
	 * key) with back end private key
	 * 
	 * @param backEndPrivateKey
	 * @param
	 * @return
	 * @throws InvalidKeyException
	 * @throws NoSuchPaddingException
	 * @throws NoSuchAlgorithmException
	 * @throws BadPaddingException
	 * @throws IllegalBlockSizeException
	 */
	public static byte[] decryptSecretKey(PrivateKey backEndPrivateKey, byte[] encryptedSecretKey)
			throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException,
			BadPaddingException {

		try {
			Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
			cipher.init(Cipher.PRIVATE_KEY, backEndPrivateKey);
			byte[] decryptedKey = cipher.doFinal(encryptedSecretKey);
			return decryptedKey;
		} catch (Exception e) {
			log.info(e.getMessage());
			return null;
		}
	}

	/**
	 * method that generate a secret key as string used in generation of OTP , and
	 * generate token
	 * 
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public static String generateSecretKeyAsString() throws NoSuchAlgorithmException {
		try {
			KeyGenerator keyGen = KeyGenerator.getInstance("AES");
			keyGen.init(128);
			SecretKey secretKey = keyGen.generateKey();
			String encodedSecretKey = Base64.getEncoder().encodeToString(secretKey.getEncoded());
			return encodedSecretKey;
		} catch (Exception e) {
			log.info(e.getMessage());
			return null;
		}
	}

	public static String generateSecretKeyTOTP() {
		SecureRandom random = new SecureRandom();
		byte[] bytes = new byte[20];
		random.nextBytes(bytes);
		Base32 base32 = new Base32();
		return base32.encodeToString(bytes);
	}

	/**
	 * method that generate OTP
	 * 
	 * @param secretKey
	 * @return
	 */
	public static String getTOTPCode(String secretKey, long numberOfSecond) {
		Base32 base32 = new Base32();
		byte[] bytes = base32.decode(secretKey);
		String hexKey = Hex.encodeHexString(bytes);
		return TOTP.getOTP(hexKey, numberOfSecond);
	}

	/**
	 * method that valid the otp
	 * 
	 * @param
	 * @return
	 */
	public static boolean validateOTP(String secretKey, String otp, long numberOfSecond) {
		Base32 base32 = new Base32();
		byte[] bytes = base32.decode(secretKey);
		String hexKey = Hex.encodeHexString(bytes);
		return TOTP.validate(hexKey, otp, numberOfSecond);

	}

	/**
	 * method to get salt Returns a random salt to be used to hash a password.
	 * 
	 * @return a 16 bytes random salt
	 */

	public static String getNextSalt() {
		byte[] salt = new byte[16];
		RANDOM.nextBytes(salt);
//		return new String(salt);
		String encodedSalt = Base64.getEncoder().encodeToString(salt);
		return encodedSalt;
	}

	public static String hash(String password, String salt)
			throws NoSuchAlgorithmException, UnsupportedEncodingException {
		char[] pass = password.toCharArray();
//		byte[] saltByte = salt.getBytes();
		byte[] saltByte = Base64.getDecoder().decode(salt.getBytes("UTF-8"));
		PBEKeySpec spec = new PBEKeySpec(pass, saltByte, ITERATIONS, DESIREDKEYLEN);
		try {
			SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
			byte[] hashByte = skf.generateSecret(spec).getEncoded();
//			return new String(skf.generateSecret(spec).getEncoded());
			String hash = Base64.getEncoder().encodeToString(hashByte);
			return hash;

		}
//		catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
		catch (Exception e) {
//			throw new AssertionError("Error while hashing a password: " + e.getMessage(), e);
			log.error(e.getMessage());
			log.info(e.getMessage());
			return null;
		} finally {
			spec.clearPassword();
		}
//		MessageDigest md = MessageDigest.getInstance("SHA-512");
//		md.update(salt.getBytes());
//		byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));
//		return new String(hashedPassword);
	}

	/**
	 * 
	 * method to verify the password ,returns true if the given password and salt
	 * match the hashed value, false otherwise
	 * 
	 * @param password     the password to check
	 * @param salt         the salt used to hash the password
	 * @param expectedHash the expected hashed value of the password
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws UnsupportedEncodingException
	 */
	public static boolean validatePassword(String passwordRequest, String salt, String expectedHash)
			throws NoSuchAlgorithmException, UnsupportedEncodingException {
		String hashed = hash(passwordRequest, salt);
		log.info(hashed);
		return hashed.equals(expectedHash);
	}

	public static String hashPassword(String username, String password, String value)
			throws IOException, NoSuchAlgorithmException {
//		prop = PropertiesLoaderUtils.loadAllProperties("HashPassword.properties");

//		String value = OmniBaseBO.propertiesHashMap.get("hashPassword").getProperty_value();
		String pass = username + password + value;
		String salt = SecurityUtilsExt.getNextSalt();
		String hash = SecurityUtilsExt.hash(pass, salt);
		return hash;
	}

	/**
	 * method that generate a token
	 * 
	 * @param secretKey
	 * @param device_serial
	 * @param sim_serial
	 * @return
	 */

	public static String generateToken(String secretKey, String device_UUID) {
		try {

			Instant now = Instant.now();

			return Jwts.builder().setIssuer(device_UUID)
//					claim("device_serial", device_serial).claim("username", username)
					.setIssuedAt(Date.from(now)).setExpiration(Date.from(now.plus(2l, ChronoUnit.MINUTES)))
					.signWith(SignatureAlgorithm.HS256, secretKey.getBytes()).compact();

		} catch (Exception e) {
			return null;
		}
	}

	public static int validateToken(String secretKey, String token) {
		try {
			Jwts.parser().setSigningKey(secretKey.getBytes()).parseClaimsJws(token);
			return 0;
		} catch (SignatureException e) {
			return 1;
		} catch (MalformedJwtException e) {
			return 2;
		} catch (ExpiredJwtException e) {
			return 3;
		} catch (UnsupportedJwtException e) {
			return 4;
		} catch (IllegalArgumentException e) {
			return 5;
		}

	}

	public static DeviceKeysSC refreshToken(String device_UUID, HashMap<String, DeviceKeysSC> map, int status) {
		try {
			String secretKeyToken = generateSecretKeyAsString();
//		String client_id = newJsonObject.get("device_UUID").toString();
			String token = generateToken(secretKeyToken, device_UUID);

//			map.get(device_UUID).setSecretKey(secretKeyToken);
//			map.get(device_UUID).setToken(token);
//			map.get(device_UUID).setStatus(status);

			DeviceKeysSC deviceKeysSC = new DeviceKeysSC();
			deviceKeysSC.setToken(token);
			deviceKeysSC.setSecretKey(secretKeyToken);
			deviceKeysSC.setStatus(status);
			map.put(device_UUID, deviceKeysSC);

			String addedToken = "," + "\"token\":\"" + token + "\"}";
//			String[] str = responseData.split("}", 0);
//			String splitResponse = str[0];
//			responseData = splitResponse + addedToken;
//			map.get(device_UUID).setStatus(VALID);
			return deviceKeysSC;
		} catch (Exception e) {
			log.info(e.getMessage());
			return null;
		}
	}

	public static String addTokenToResponse(String token, String responseData) {
		String addedToken = "{" + "\"token\":\"" + token + "\",";
		String[] str = responseData.split("}", 0);
		String splitResponse = responseData.substring(1);
//		String splitResponse = str[0];
		responseData = addedToken + splitResponse;
		return responseData;
	}

	public static void pushNotification(String deviceToken, String title, String msg, Notification notification,
			java.sql.Date date, Time time) throws IOException {
		byte[] msgbytes = msg.getBytes(StandardCharsets.UTF_8);
		byte[] titlebytes = title.getBytes(StandardCharsets.UTF_8);
		String msgUTF8 = new String(msgbytes, StandardCharsets.UTF_8);
		String titleUTF8 = new String(titlebytes, StandardCharsets.UTF_8);

		String result = "";
		URL url = new URL(notification.getAPI_URL_FCM());

		HttpURLConnection conn = (HttpURLConnection) url.openConnection();

		conn.setUseCaches(false);
		conn.setDoInput(true);
		conn.setDoOutput(true);

		conn.setRequestMethod("POST");
		conn.setRequestProperty("Authorization", "key=" + notification.getAUTH_KEY_FCM());
		conn.setRequestProperty("Content-Type", "application/json");

		JSONObject data = new JSONObject();

		JSONObject json = new JSONObject();
		json.put("to", deviceToken.trim());
		JSONObject info = new JSONObject();
		info.put("title", titleUTF8); // Notification title
		info.put("body", msgUTF8); // Notification body

//		DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
//		String strDate = dateFormat.format(date);
//		String strtime = time.toString();
		if (date != null && time != null) {
			String body = "Date: " + date + "\n Time: " + time;

			info.put("body", body); // Notification
									// body
		}
		json.put("notification", info);
//		json.put("details", data);
		ByteBuffer buffer = StandardCharsets.UTF_8.encode(json.toString());

		String utf8EncodedString = StandardCharsets.UTF_8.decode(buffer).toString();
		try {
			OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
			wr.write(utf8EncodedString);

			wr.flush();

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			String output;
			while ((output = br.readLine()) != null) {
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void sendTotpSMS(String mobileNumber, String totp, String user, String pass, String sid, String url,
			String hash) {
		String sms;
		String stringhash;
		try {
			// String mobile = mobileNumber.substring(1);
			// String mno = mobile.replaceAll("\\s", "");
			if (hash.contains("+")) {
				stringhash = hash.replaceAll("\\+", "%2B");

				sms = "<%23> Your code:" + "\"" + totp + "\"" + "%0A" + stringhash;
			} else {
				sms = "<%23> Your code:" + "\"" + totp + "\"" + "%0A" + hash;
			}

			// String uri = url + "?user=" + user + "&pass=" + pass + "&sid=" + sid +
			// "&mno=" + mno + "&type=1&text=" + sms
			// + "&respformat=json";

			sendSMS(mobileNumber, user, pass, sid, url, sms);
		} catch (Exception e) {
			log.info(e.getMessage());
		}

	}

	public static void sendSMS(String mobileNumber, String user, String pass, String sid, String url, String sms)
			throws IOException {
		String mobile = mobileNumber.substring(1);
		String mno = mobile.replaceAll("\\s", "");
		String uri = url + "?user=" + user + "&pass=" + pass + "&sid=" + sid + "&mno=" + mno + "&type=4&text=" + sms
				+ "&respformat=json";
		System.out.println(uri);
		OkHttpClient httpClient = new OkHttpClient();
		Request request = new Request.Builder().url(uri).build();

		try (Response response = httpClient.newCall(request).execute()) {

			if (!response.isSuccessful())
				throw new IOException("Unexpected code " + response);

			// Get response body
			System.out.println(response.body().string());
		}
	}

	/*
	 * method that send invite sms
	 */
	/*
	 * public static void sendInviteSMS(String mobileNumber, String user, String
	 * pass, String sid, String url, String sms) throws IOException {
	 * 
	 * 
	 * sms = "You are invited to download Triptuk app using the url" +
	 * "https://play.google.com/store/apps/details?id=com.cmma.fb_tkt";
	 * 
	 * // String uri = url + "?user=" + user + "&pass=" + pass + "&sid=" + sid +
	 * "&mno=" + mno + "&type=4&text=" + sms // + "&respformat=json";
	 * 
	 * /////////NO MORE NEED FOR THIS METHOD
	 * sendSMS(mobileNumber,user,pass,sid,url,sms); }
	 */

	/**
	 * return the ALGORITHM_CBC_NOPADDING_PWD
	 * 
	 * @return
	 */

	public static String returnAlgorithmCbcNopaddingPwd() {
		return ALGORITHM_CBC_NOPADDING_PWD;
	}

	/**
	 * return the ALGORITHM_SESSION_TOKEN_PWD
	 * 
	 * @return
	 */
	public static String returnAlgorithmSessionTokenPwd() {
		return ALGORITHM_SESSION_TOKEN_PWD;
	}

	public static int getNewVersion(String versionReq, String versionVO2) {
		String[] versionReqArray = versionReq.split(Pattern.quote("."));
		String[] versionVO2Array = versionVO2.split(Pattern.quote("."));

//		 versionResult;
		for (int i = 0; i < versionReqArray.length; i++) {
			if (Integer.parseInt(versionReqArray[i]) > Integer.parseInt(versionVO2Array[i])) {
//				versionResult = 1;
				return 1;
			}
			if (Integer.parseInt(versionReqArray[i]) < Integer.parseInt(versionVO2Array[i])) {
//				versionResult = -1;
				return -1;
			}
			if (i == versionReqArray.length - 1) {
				if (Integer.parseInt(versionReqArray[i]) == Integer.parseInt(versionVO2Array[i])) {
//					versionResult = 0;
					return 0;
				}
			}
		}
		return -9;

	}

	public static void sendWhatsappMessage(String mobileNumberDriver, String mobileNumberSender, String ACCOUNT_SID,
			String AUTH_TOKEN, String msgToSend, String location) {
		try {
			Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
			Message message1 = Message.creator(new com.twilio.type.PhoneNumber("whatsapp:+96179141553"),
					new com.twilio.type.PhoneNumber("whatsapp:+96176432166"),
					"Hello from your friendly neighborhood Java application!").create();
			Message message = Message.creator(new com.twilio.type.PhoneNumber("whatsapp:+96179141553"),
					new com.twilio.type.PhoneNumber("whatsapp:+96176432166"), location).create();
			System.out.println(message.getStatus());
			System.out.println(message1.getStatus());
		} catch (Exception e) {
			log.info(e.getMessage());
		}

	}

	public static void sendNewRecurringReservationSMS(String mobileNumber, String msg, String user, String pass,
			String sid, String url) {
		String mobile = mobileNumber.substring(1);
		String mno = mobile.replaceAll("\\s", "");
		String uri = url + "?user=" + user + "&pass=" + pass + "&sid=" + sid + "&mno=" + mno + "&type=1&text=" + msg
				+ "&respformat=json";
	}

}
