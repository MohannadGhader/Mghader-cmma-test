package com.path.lib.common.util;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.TimeZone;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import com.path.bo.common.ConstantsCommon;
import com.path.lib.common.exception.BaseException;
import com.path.lib.log.Log;
import com.path.struts2.lib.common.BaseObject;
import com.path.struts2.lib.common.BaseSC;
import com.path.struts2.lib.common.GridParamsSC;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.basic.DateConverter;

/**
 * 
 * Copyright 2013, Path Solutions Path Solutions retains all ownership rights to
 * this source code
 * 
 * @author: DeniskHaddad
 * 
 *          PathXMLUtil.java used to for XML manipulation
 */
public final class PathXMLUtil {
	private static String defaultTimeZone;
	private static final Log log = Log.getInstance();

	static {
		try {
			defaultTimeZone = PathPropertyUtil.getPathRemotingProp("PathRemoting", "default.timezone.objecttoxml");
		} catch (Exception e) {
			log.error(e, "Error reading default.timezone.objecttoxml from PathRemorting.properties");
		}
	}

	/**
	 * Private constructor to prevent class from instantiation
	 */
	private PathXMLUtil() {
		Log.getInstance().warning("This class is utility and cannot be instantiated");
	}

	private static void commonXstreamOmitFields(XStream xstream) {
		xstream.omitField(BaseObject.class, "isOracle");
		xstream.omitField(BaseObject.class, "isSybase");
		xstream.omitField(BaseObject.class, "emptyBigDecimalValue");
		xstream.omitField(GridParamsSC.class, "searchCols");
		xstream.omitField(GridParamsSC.class, "nbRec");
		xstream.omitField(GridParamsSC.class, "recToskip");
		xstream.omitField(BaseSC.class, "isRTLDir");
		xstream.omitField(BaseObject.class, "connCO");
	}

	/**
	 * Converts Object to XML String
	 * 
	 * @param myObject Object to be converted.
	 * @return
	 * @throws BaseException
	 */
	public static String objectToXML(Object myObject) throws BaseException {
		String xml = null;
		try {
			XStream xstream = new XStream();
			xstream.setMode(XStream.NO_REFERENCES);
			commonXstreamOmitFields(xstream);
			xstream.alias(myObject.getClass().getSimpleName(), myObject.getClass());
			if (ConstantsCommon.API_APP_NAME.equals(ConstantsCommon.returnCurrentAppName()) && defaultTimeZone != null
					&& !defaultTimeZone.isEmpty()) {
				DateConverter dateConverter = new DateConverter(TimeZone.getTimeZone(defaultTimeZone));
				xstream.registerConverter(dateConverter);
			}
			xml = xstream.toXML(myObject);
		} catch (Exception e) {
			throw new BaseException(e);
		}
		return xml;

	}

	/**
	 * Converts Object to XML String
	 * 
	 * @param myObject Object to be converted.
	 * @return
	 * @throws BaseException
	 */
	public static String objectToXML(Object myObject, String dateFormat) throws BaseException {
		String xml = null;
		try {
			XStream xstream = new XStream();
			xstream.setMode(XStream.NO_REFERENCES);
			commonXstreamOmitFields(xstream);
			xstream.alias(myObject.getClass().getSimpleName(), myObject.getClass());
			if (dateFormat != null && !dateFormat.isEmpty()) {
				DateConverter dateConverter = null;
				if (ConstantsCommon.API_APP_NAME.equals(ConstantsCommon.returnCurrentAppName())
						&& defaultTimeZone != null && !defaultTimeZone.isEmpty()) {
					dateConverter = new DateConverter(dateFormat, new String[] { dateFormat },
							TimeZone.getTimeZone(defaultTimeZone));
				} else {
					dateConverter = new DateConverter(dateFormat, new String[] { dateFormat });
				}
				xstream.registerConverter(dateConverter);
			}
			xml = xstream.toXML(myObject);
		} catch (Exception e) {
			throw new BaseException(e);
		}
		return xml;

	}

	/**
	 * Converting String to Object
	 * 
	 * @param myXml       xml String to Convert to Object.
	 * @param classCastTo class to cast to.
	 * @return
	 * @throws BaseException
	 */
	@SuppressWarnings("unchecked")
	public static <T> T xmlToObject(String myXml, Class<T> classCastTo) throws BaseException {
		Object obj = null;
		try {
			XStream xstream = new XStream();
			xstream.alias(classCastTo.getSimpleName(), classCastTo);
			obj = xstream.fromXML(myXml);
		} catch (Exception e) {
			throw new BaseException(e);
		}
		return (T) obj;
	}

	/**
	 * @description : this is used to validate an xml against an xsd
	 * @param xml_input (String(base64)) : xml file send as String base64
	 * @param xsd_input (String(base64)) : xsd file send as String base64
	 * 
	 * @output : error_message (String) : in case the the xml or XSD not provided or
	 *         empty or in case the xml elements are not formal with xsd definition
	 * @output : is_valid (boolean) : true/false
	 */
	public static HashMap<String, Object> validateXml(HashMap<String, Object> hm) throws Exception {
		String xmlStr = (String) hm.get("xml_input");
		String xsdStr = (String) hm.get("xsd_input");

		String errorMessage = null;
		boolean isValid = false;
		try {
			if (StringUtil.isEmptyString(xmlStr)) {
				errorMessage = "Xml content is empty";
			} else if (StringUtil.isEmptyString(xmlStr)) {
				errorMessage = "Xsd content is empty";
			}

			if (errorMessage == null) {
				xmlStr = SecurityUtils.decodeB64(xmlStr);
				xsdStr = SecurityUtils.decodeB64(xsdStr);

				InputStream xmlStream = new ByteArrayInputStream(xmlStr.getBytes(FileUtil.DEFAULT_FILE_ENCODING));
				InputStream xsdStream = new ByteArrayInputStream(xsdStr.getBytes(FileUtil.DEFAULT_FILE_ENCODING));

				// validate the xml v/s xsd
				String validationErrMsg = validateXmlXsd(xmlStream, xsdStream);
				errorMessage = validationErrMsg;

				isValid = validationErrMsg == null;
			}
		} catch (Exception e) {
			errorMessage = e.getMessage();
			log.error(e, "Error in PathXMLUtil.validateXml() ");
		}
		hm.put("is_valid", isValid);
		hm.put("error_message", errorMessage);
		return hm;
	}

	/**
	 * validate XML document must conform in order to be considered "valid"
	 * according to XSD schema
	 * 
	 * @param xmlStream : xml file send as InputStream
	 * @param xsdStream : xsd file send as InputStream
	 * @return null : success, otherwise return the validation message
	 * @throws Exception
	 */
	public static String validateXmlXsd(InputStream xmlStream, InputStream xsdStream) throws Exception {
		try {
			SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			Schema schema = factory.newSchema(new StreamSource(xsdStream));
			Validator validator = schema.newValidator();

			List<SAXParseException> exceptions = new LinkedList<SAXParseException>();
			validator.setErrorHandler(new XmlErrorHandler(exceptions));

			StreamSource xmlFile = new StreamSource(xmlStream);
			validator.validate(xmlFile);
			return returnValidationMessage(exceptions);
		} catch (Exception e) {
			log.error(e, "Error in validateXmlXsd PathXMLUtil");
			throw e;
		}
	}

	private static String returnValidationMessage(List<SAXParseException> exceptions) {
		if (exceptions != null) {
			StringBuilder sb = new StringBuilder();
			for (SAXParseException ex : exceptions) {
				sb.append(ex.getMessage()).append(System.lineSeparator());
			}
			return sb.toString();
		}
		return null;
	}

	private static class XmlErrorHandler implements ErrorHandler {
		List<SAXParseException> exceptions = new LinkedList<SAXParseException>();

		public XmlErrorHandler(List<SAXParseException> lst) {
			this.exceptions = lst;
		}

		@Override
		public void warning(SAXParseException exception) throws SAXException {
			exceptions.add(exception);
		}

		@Override
		public void fatalError(SAXParseException exception) throws SAXException {
			exceptions.add(exception);
		}

		@Override
		public void error(SAXParseException exception) throws SAXException {
			exceptions.add(exception);
		}
	}

}
