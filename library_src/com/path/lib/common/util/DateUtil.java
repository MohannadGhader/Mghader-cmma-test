package com.path.lib.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import com.path.bo.common.ConstantsCommon;
import com.path.lib.common.exception.BaseException;
import com.path.lib.log.Log;
import com.path.vo.common.SessionCO;

public final class DateUtil {
	public static final int DATE = 1, TIME = 2, DATETIME = 3;
	private static final Log log = Log.getInstance();
	public static final String DEFAULT_DATE_FORMAT = "dd/MM/yyyy";
	public static final String DATE_MASK_ATTRIBUTE = "dateMask";
	public static final String DAY = "D";
	public static final String MONTH = "M";
	public static final String WEEK = "W";
	public static final String QUARTER = "Q";
	public static final String SEMI_ANNUALY = "S";
	public static final String YEAR = "Y";
	public static final String DAY_OF_YEAR = "DY";
	public static final String DAY_OF_WEEK = "DW";
	public static final String DAY_OF_MONTH = "DM";

	/**
	 * Private constructor only to prevent instantiation in the class
	 */
	private DateUtil() {
		log.error("This Class Should not be Instantiated");
	}

	/**
	 * Create date using day year and month
	 * 
	 * @param year
	 * @param month
	 * @param day
	 * @return date
	 */
	public static Date createDate(int year, int month, int day) {
		Calendar cal = Calendar.getInstance();
		// cal.setTime(date);
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month);
		cal.set(Calendar.DAY_OF_MONTH, day);

		cal.set(Calendar.HOUR, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		cal.set(Calendar.AM_PM, 0);

		return cal.getTime();
	}

	/**
	 * @author mvalappil
	 * @Date 17/03/2010
	 * @param dateFrom
	 * @param dateTo
	 * @param toTruncate
	 * @param toRound
	 * @use To get the total months between 2 date (return number of Month) will not
	 *      concidor the date part
	 */
	public static double getMonthsDifference(Date dateFrom, Date dateTo) {

		double monthsNumber = 0;

		Calendar cal = Calendar.getInstance();
		// Calendar cal = new GregorianCalendar();

		cal.setTime(dateFrom);
		int yearFrom = cal.get(Calendar.YEAR);
		int monthFrom = cal.get(Calendar.MONTH) + 1;

		cal.setTime(dateTo);
		int yearTo = cal.get(Calendar.YEAR);
		int monthTo = cal.get(Calendar.MONTH) + 1;

		if (yearFrom == yearTo) {
			monthsNumber = monthTo - monthFrom;
		} else {
			monthsNumber = 12 - monthFrom + monthTo;
			monthsNumber = monthsNumber + ((yearTo - yearFrom - 1) * 12);
		}

		return monthsNumber;
	}

	public static int getYearDifference(Date date1, Date date2) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date1);
		int year1 = cal.get(Calendar.YEAR);

		cal.setTime(date2);
		int year2 = cal.get(Calendar.YEAR);
		return year2 - year1;

	}

	/**
	 * To get the weak days name
	 * 
	 * @param date
	 * @return
	 */
	public static String getDayShortName(Date date) {
		String dayName = "";
		Calendar cal = Calendar.getInstance();

		cal.setTime(date);
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
		switch (dayOfWeek) {
		case 1:
			dayName = "SUN";
			break;
		case 2:
			dayName = "MON";
			break;
		case 3:
			dayName = "TUE";
			break;
		case 4:
			dayName = "WED";
			break;
		case 5:
			dayName = "THU";
			break;
		case 6:
			dayName = "FRI";
			break;
		case 7:
			dayName = "SAT";
			break;
		default:
			break;
		}
		return dayName;
	}

	/**
	 * used to check if the date string is valid woth Format MM/dd/yyyy
	 * 
	 * @author Bejoy Kodiyan
	 * @Date 04/07/2012 (MM/dd/yyy)
	 * @param input date
	 * @use To check whether a date is valid or not
	 */
	public static boolean isValidDate(String input) {
		return isValidDate(input, "MM/dd/yyyy");
	}

	/****
	 * Method for getting the Date part (trimming the time part)
	 * 
	 * @param date
	 * @return
	 */
	public static Calendar getDateWithoutTime(Date date) {
		Calendar calendarObj = Calendar.getInstance();
		if (date != null) {
			calendarObj.setTime(date);
		}
		calendarObj.set(Calendar.HOUR_OF_DAY, 0);
		calendarObj.set(Calendar.MINUTE, 0);
		calendarObj.set(Calendar.SECOND, 0);
		calendarObj.set(Calendar.MILLISECOND, 0);
		return calendarObj;
	}

	/**
	 * 
	 * Used for checking if Valid Date with given Format
	 * 
	 * @param input
	 * @param format
	 * @return
	 */
	public static boolean isValidDate(String input, String format) {

		try {
			SimpleDateFormat sdformat = new SimpleDateFormat(format, Locale.ENGLISH);
			sdformat.setLenient(false);
			sdformat.parse(input);
		} catch (ParseException e) {
			return false;
		} catch (IllegalArgumentException e) {
			return false;
		}

		return true;
	}

	/**
	 * Returns the inputed date in the given format
	 * 
	 * @param zdate   Date
	 * @param pattern String
	 * @return Date
	 */
	public static String format(Date zdate, String pattern) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat();
			sdf.applyPattern(pattern);
			if (zdate == null) {
				return "";
			} else {
				return sdf.format(zdate);
			}
		} catch (Exception ex) {
			log.error(ex, "[DateUtil] Error caught in method format");
			return "";
		}
	}

	/**
	 * Returns the date and time format from string Returned format examples
	 * dd/MM/yyyy, dd/MM/yyyy hh:mm:ss, dd/MM/yyyy HH:mm:ss, dd/MM/yyyy hh:mm,
	 * dd/MM/yyyy HH:mm
	 * 
	 * @param date String: Date value
	 * @return String
	 */
	public static String getDatePattern(String date) {

		String datePattern = "\\d\\d/\\d\\d/\\d\\d\\d\\d";// Ex: dd/MM/yyyy

		String datePattern1 = "\\d\\d/\\d\\d/\\d\\d";// Ex: dd/MM/yy
		String datePattern2 = "\\d\\d/\\d\\d\\d\\d";// Ex: MM/yyyy
		String datePattern3 = "\\d\\d/\\d\\d\\d\\d";// Ex: MM/yy

		String DTPattern1 = "\\d\\d/\\d\\d/\\d\\d\\d\\d(\\b\\s*)\\d?\\d:\\d?\\d:\\d?\\d \\w\\w";// Ex: dd/MM/yyyy
																								// hh:mm:ss (am or pm)
		String DTPattern2 = "\\d\\d/\\d\\d/\\d\\d\\d\\d(\\b\\s*)\\d?\\d:\\d?\\d:\\d?\\d";// Ex: dd/MM/yyyy HH:mm:ss
		String DTPattern3 = "\\d\\d/\\d\\d/\\d\\d\\d\\d(\\b\\s*)\\d?\\d:\\d?\\d \\w\\w";// Ex: dd/MM/yyyy hh:mm (am or
																						// pm)
		String DTPattern4 = "\\d\\d/\\d\\d/\\d\\d\\d\\d(\\b\\s*)\\d?\\d:\\d?\\d";// Ex: dd/MM/yyyy HH:mm
		String DTPattern5 = "\\d\\d/\\d\\d/\\d\\d\\d\\d(\\b\\s*)\\d?\\d:\\d?\\d:\\d?\\d:\\d\\d\\d";// Ex: dd/MM/yyyy
																									// HH:mm:ss:SSS
		String DTPattern51 = "\\d\\d/\\d\\d/\\d\\d\\d\\d(\\b\\s*)\\d?\\d:\\d?\\d:\\d?\\d:\\d\\d";// Ex: dd/MM/yyyy
																									// HH:mm:ss:SS
		String DTPattern52 = "\\d\\d/\\d\\d/\\d\\d\\d\\d(\\b\\s*)\\d?\\d:\\d?\\d:\\d?\\d:\\d";// Ex: dd/MM/yyyy
																								// HH:mm:ss:S

		// 2 digits year
		String DTPattern6 = "\\d\\d/\\d\\d/\\d\\d(\\b\\s*)\\d?\\d:\\d?\\d:\\d?\\d \\w\\w";// Ex: dd/MM/yy hh:mm:ss (am
																							// or pm)
		String DTPattern7 = "\\d\\d/\\d\\d/\\d\\d(\\b\\s*)\\d?\\d:\\d?\\d:\\d?\\d";// Ex: dd/MM/yy HH:mm:ss
		String DTPattern8 = "\\d\\d/\\d\\d/\\d\\d(\\b\\s*)\\d?\\d:\\d?\\d \\w\\w";// Ex: dd/MM/yy hh:mm (am or pm)
		String DTPattern9 = "\\d\\d/\\d\\d/\\d\\d(\\b\\s*)\\d?\\d:\\d?\\d";// Ex: dd/MM/yy HH:mm
		String DTPattern10 = "\\d\\d/\\d\\d/\\d\\d(\\b\\s*)\\d?\\d:\\d?\\d:\\d?\\d:\\d\\d\\d";// Ex: dd/MM/yy
																								// HH:mm:ss:SSS

		String DTPattern11 = "\\d\\d/\\d\\d/\\d\\d\\d\\d(\\b\\s*)\\d?\\d:";// Ex: dd/MM/yyyy HH:
		String DTPattern12 = "\\d\\d/\\d\\d/\\d\\d\\d\\d(\\b\\s*)\\d?\\d";// Ex: dd/MM/yyyy HH
		String DTPattern13 = "\\d\\d/\\d\\d/\\d\\d\\d\\d(\\b\\s*)\\d?\\d:\\d?\\d:";// Ex: dd/MM/yyyy HH:mm:
		String DTPattern14 = "\\d\\d/\\d\\d/\\d\\d\\d\\d(\\b\\s*)\\d?\\d:\\d?\\d";// Ex: dd/MM/yyyy HH:mm

		String timePattern1 = "\\d?\\d:\\d?\\d:\\d?\\d \\w\\w";// Ex: hh:mm:ss (am or pm)
		String timePattern2 = "\\d?\\d:\\d?\\d \\w\\w";// Ex: hh:mm (am or pm)
		String timePattern3 = "\\d?\\d:\\d?\\d:\\d?\\d";// Ex: HH:mm:ss
		String timePattern4 = "\\d?\\d:\\d?\\d";// Ex: HH:mm
		String timePattern5 = "\\d?\\d:\\d?\\d:\\d?\\d:\\d+";// Ex: HH:mm:ss:[Any millisecond]
		String timePattern6 = "\\d?\\d";// Ex: HH
		String timePattern7 = "\\d?\\d:";// Ex: HH:

		String DTPattern15 = "\\d\\d\\d\\d-\\d\\d-\\d\\d(T)\\d?\\d:\\d?\\d:\\d?\\d:\\d\\d\\d";// yyyy-MM-dd'T'HH:mm:ss.SSS
		String DTPattern16 = "\\d\\d\\d\\d-\\d\\d-\\d\\d";// yyyy-MM-dd

		String choice = null;
		if (date.matches(DTPattern1)) {
			choice = "dd/MM/yyyy hh:mm:ss aaa";
		} else if (date.matches(DTPattern2)) {
			choice = "dd/MM/yyyy HH:mm:ss";
		} else if (date.matches(DTPattern3)) {
			choice = "dd/MM/yyyy hh:mm aaa";
		} else if (date.matches(DTPattern4)) {
			choice = "dd/MM/yyyy HH:mm";
		} else if (date.matches(DTPattern5)) {
			choice = "dd/MM/yyyy HH:mm:ss:SSS";
		} else if (date.matches(DTPattern51)) {
			choice = "dd/MM/yyyy HH:mm:ss:SS";
		} else if (date.matches(DTPattern52)) {
			choice = "dd/MM/yyyy HH:mm:ss:S";
		} else if (date.matches(DTPattern6)) {
			choice = "dd/MM/yy hh:mm:ss";
		} else if (date.matches(DTPattern7)) {
			choice = "dd/MM/yy HH:mm:ss";
		} else if (date.matches(DTPattern8)) {
			choice = "dd/MM/yy hh:mm";
		} else if (date.matches(DTPattern9)) {
			choice = "dd/MM/yy HH:mm";
		} else if (date.matches(DTPattern10)) {
			choice = "dd/MM/yy HH:mm:ss:SSS";
		} else if (date.matches(datePattern)) {
			choice = "dd/MM/yyyy";
		} else if (date.matches(datePattern1)) {
			choice = "dd/MM/yy";
		} else if (date.matches(datePattern2)) {
			choice = "MM/yyyy";
		} else if (date.matches(datePattern3)) {
			choice = "MM/yy";
		} else if (date.matches(DTPattern13)) {
			choice = "dd/MM/yyyy HH:mm:";
		} else if (date.matches(DTPattern14)) {
			choice = "dd/MM/yyyy HH:mm";
		} else if (date.matches(DTPattern11)) {
			choice = "dd/MM/yyyy HH:";
		} else if (date.matches(DTPattern12)) {
			choice = "dd/MM/yyyy HH";
		} else if (date.matches(timePattern1)) {
			choice = "hh:mm:ss aaa";
		} else if (date.matches(timePattern2)) {
			choice = "hh:mm aaa";
		} else if (date.matches(timePattern3)) {
			choice = "HH:mm:ss";
		} else if (date.matches(timePattern5)) {
			choice = "HH:mm:ss:SSS";
		} else if (date.matches(timePattern4)) {
			choice = "HH:mm";
		} else if (date.matches(timePattern7)) {
			choice = "HH:";
		} else if (date.matches(timePattern6)) {
			choice = "HH";
		}
		// try to check iso format at the end, because some times hidden date element in
		// JSP received as ISO format
		else if (date.matches(DTPattern15)) {
			choice = "yyyy-MM-dd'T'HH:mm:ss:SSS";
		}
		// try to yyyy-MM-dd pattern
		else if (date.matches(DTPattern16)) {
			choice = "yyyy-MM-dd";
		}

		return choice;
	}

	/**
	 * Returns a Date object formated according to the string date value Returned
	 * Date format examples dd/MM/yyyy, dd/MM/yyyy hh:mm:ss, dd/MM/yyyy HH:mm:ss,
	 * dd/MM/yyyy hh:mm, dd/MM/yyyy HH:mm
	 * 
	 * @param date String
	 * @return Date
	 */
	public static Date getFormatedDate(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat();
		String pattern = getDatePattern(date);
		sdf.applyPattern(pattern);
		Date dateObj = null;
		try {
			dateObj = sdf.parse(date);
		} catch (Exception ex) {
			log.error(ex, "");
		}
		return dateObj;
	}

	/**
	 * Returns the number of Days between two dates on the format dateFormat
	 *
	 * @param strtDate
	 * @param endDate
	 * @param dateFormat
	 * @return long
	 */
	public static long numberOfDays(Date strtDate, Date endDate, String dateFormat) {
		long daysNumber = 0;
		// used to take only date in case the date contain date and time
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat, Locale.ENGLISH);
		Date date1 = strtDate, date2 = endDate;
		try {
			date1 = simpleDateFormat.parse(simpleDateFormat.format(date1));
			date2 = simpleDateFormat.parse(simpleDateFormat.format(date2));
			Calendar c1 = Calendar.getInstance();
			Calendar c2 = Calendar.getInstance();
			c1.setTime(date1);
			c2.setTime(date2);
			daysNumber = daysBetween(c1, c2);
		} catch (Exception exception) {
			log.error(exception, "Error in date Parsing");
		}
		return daysNumber;
	}

	/**
	 * calculates the different between two dates regardless of timezones
	 * 
	 * @param startDate start date
	 * @param endDate   end date
	 * @return number of days
	 * @throws Exception
	 */
	private static long daysBetween(Calendar startDate, Calendar endDate) throws Exception {
		if (startDate.after(endDate)) {
			return -daysBetween(endDate, startDate);
		}

		Calendar date = (Calendar) startDate.clone();
		// difference between end date and start date in days (fraction of milliseconds
		// 0.xx are considered 0 in this case)
		long bigLeap = (endDate.getTimeInMillis() - startDate.getTimeInMillis()) / 1000 / 60 / 60 / 24;
		// adding the number of days to start date
		date.add(Calendar.DAY_OF_YEAR, (int) bigLeap);

		long smallStep = 0;
		// check if there is still difference between the two dates ( due to timezone
		// differences and the fractions created by the milliseconds conversion)
		// add the remaining days (fraction to be added or substracted)
		if (date.before(endDate)) {
			while (date.before(endDate)) {
				date.add(Calendar.DAY_OF_MONTH, 1);
				smallStep++;
			}
		} else if (endDate.before(date)) {
			while (endDate.before(date)) {
				date.add(Calendar.DAY_OF_MONTH, -1);
				smallStep--;
			}
		}
		return smallStep + bigLeap;
	}

	/**
	 * Returns number of day between two dates on the format dd/MM/yyyy
	 *
	 * @param date1
	 * @param date2
	 * @return long
	 */
	public static long numberOfDays(Date date1, Date date2) {
		return numberOfDays(date1, date2, "dd/MM/yyyy");
	}

	/**
	 * Returns day of week for path means 1 is for Monday
	 * 
	 * @param date1
	 * @return long
	 */
	public static long pathDayOfWeek(Date date1) {
		Calendar currentCal = Calendar.getInstance();
		currentCal.setTime(date1);
		int dayOfWeek = currentCal.get(Calendar.DAY_OF_WEEK) - 1;
		if (dayOfWeek == 0) {
			dayOfWeek = 7;
		}
		return dayOfWeek;
	}

	/**
	 * Returns month where 1 is for January ... 12 for December
	 * 
	 * @param date1
	 * @return Integer
	 */
	public static Integer pathMonth(Date date1) throws BaseException {
		Integer month = getDayYearMonth(date1, DateUtil.MONTH);
		return month + 1;
	}

	/**
	 * Formats a given date with the common formatter dd/MM/yyyy
	 * 
	 * @param zdate
	 * @return
	 */
	public static String format(Date zdate) {
		return format(zdate, "dd/MM/yyyy");
	}

	/**
	 * Formats a given string using a given pattern and returns a date instance
	 * 
	 * @param date
	 * @param pattern
	 * @return
	 * @throws Exception
	 */
	public static Date parseDate(String date, String pattern) throws BaseException {

		SimpleDateFormat df = new SimpleDateFormat(pattern, Locale.ENGLISH);
		try {
			return df.parse(date);
		} catch (ParseException e) {
			log.error(e, "ERROR in DateUtil class parseDate[Contact Administrator] while parsing date " + date);
			throw new BaseException("ERROR in DateUtil class parseDate while parsing date " + date, e);
		}

	}

	/**
	 * returns the date after the [date] parameter with nb [days]
	 * 
	 * @param date the date
	 * @param days nb of days to add or substract if negative
	 * @return
	 * @throws Exception
	 */
	public static Date relativeDate(Date date, Integer days) throws BaseException {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, days);
		return cal.getTime();
	}

	/**
	 * returns the date after the [date] parameter with nb [days]
	 * 
	 * @param date  the date
	 * @param month nb of month to add or substract if negative
	 * @return
	 * @throws Exception
	 */
	public static Date relativeMonth(Date date, Integer month) throws BaseException {
		return relativeMonth(date, month, false);
	}

	/**
	 * returns the date after the [date] parameter with nb [months]
	 * 
	 * @param date            the date
	 * @param month           nb of month to add or subtract if negative
	 * @param considerLastDay set to true if you want to consider last day of month
	 *                        if date provided is last date of month
	 * @return
	 * @throws Exception
	 */
	public static Date relativeMonth(Date date, Integer month, boolean considerLastDay) throws BaseException {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int maxDaysInMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		int currentDate = cal.get(Calendar.DATE);
		cal.add(Calendar.MONTH, month);
		if (currentDate == maxDaysInMonth) {
			cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DATE));
		}
		return cal.getTime();
	}

	/**
	 * returns the Day or Year or month of given Date
	 * 
	 * @param date the date
	 * @param What to return M for month ,Y for Year ,D for Day ,DY for indicate the
	 *             day number within the current year ,DW for indicate the day
	 *             number within the current week ,DM for indicate the day number
	 *             within the current month
	 * @return
	 * @throws Exception
	 */
	public static Integer getDayYearMonth(Date date, String what) throws BaseException {
		if (date == null) {
			return null;
		}

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int toRet = cal.DATE;
		if (what != null) {
			if (what.equalsIgnoreCase(DateUtil.YEAR)) {
				toRet = cal.YEAR;
			} else if (what.equalsIgnoreCase(DateUtil.MONTH)) {
				toRet = cal.MONTH;
			} else if (what.equalsIgnoreCase(DateUtil.DAY_OF_YEAR)) {
				toRet = cal.DAY_OF_YEAR;
			} else if (what.equalsIgnoreCase(DateUtil.DAY_OF_WEEK)) {
				toRet = cal.DAY_OF_WEEK;
			} else if (what.equalsIgnoreCase(DateUtil.DAY_OF_MONTH)) {
				toRet = cal.DAY_OF_MONTH;
			}
		}

		return cal.get(toRet);
	}

	/**
	 * Add a time to a given Date, if time already exists then it will be over
	 * written
	 * 
	 * @param theDate Date to which to time to be added
	 * @return
	 * @throws BaseException
	 */
	public static Date addTimeToDate(Date theDate) throws BaseException {
		Calendar cal = Calendar.getInstance();
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		int min = cal.get(Calendar.MINUTE);
		int sec = cal.get(Calendar.SECOND);

		cal.setTime(theDate);
		cal.set(Calendar.HOUR_OF_DAY, hour);
		cal.set(Calendar.MINUTE, min);
		cal.set(Calendar.SECOND, sec);
		return cal.getTime();
	}

	/**
	 * Remove time from date by setting hour,minute,second and millisecond to zeros
	 * 
	 * @author nabil feghali
	 * @param date
	 * @return
	 */
	public static Date removeTimeFromDate(Date date) {
		if (date == null) {
			return null;
		} else {
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.MILLISECOND, 0);
			return cal.getTime();
		}
	}

	/**
	 * Add hour and minutes to Date
	 * 
	 * @author nabil feghali
	 * @param date
	 * @return
	 */
	public static Date addHourAndMinutesToDate(Date theDate) throws BaseException {
		if (theDate == null) {
			return null;
		} else {
			Calendar cal = Calendar.getInstance();
			int hour = cal.get(Calendar.HOUR_OF_DAY);
			int min = cal.get(Calendar.MINUTE);
			cal.setTime(theDate);
			cal.set(Calendar.HOUR_OF_DAY, hour);
			cal.set(Calendar.MINUTE, min);
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.MILLISECOND, 0);
			return cal.getTime();
		}
	}

	/**
	 * 
	 * Used for set the time of timeToAdd to Date to the dateToSetTime Date
	 * 
	 * @param dateToSetTime the date to which the time to be set
	 * @param timeToAdd     the date from which the time to be set
	 * @return
	 */
	public static Date addDateTimeToDate(Date dateToSetTime, Date timeToAdd) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(timeToAdd);
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		int min = cal.get(Calendar.MINUTE);
		int sec = cal.get(Calendar.SECOND);

		cal.setTime(dateToSetTime);
		cal.set(Calendar.HOUR_OF_DAY, hour);
		cal.set(Calendar.MINUTE, min);
		cal.set(Calendar.SECOND, sec);

		return cal.getTime();
	}

	/**
	 * 
	 * Used for set the time of hour minute and secind to Date to the dateToSetTime
	 * Date
	 * 
	 * @param dateToSetTime the date to which the time to be set
	 * @param int           hour
	 * @param int           minute
	 * @param int           second
	 * @return
	 */
	public static Date addTimeToDate(Date dateToSetTime, int hour, int minute, int second) {

		Calendar cal = Calendar.getInstance();
		if (dateToSetTime != null) {
			cal.setTime(dateToSetTime);
		}
		cal.set(Calendar.HOUR_OF_DAY, hour);
		cal.set(Calendar.MINUTE, minute);
		cal.set(Calendar.SECOND, second);

		return cal.getTime();
	}

	/**
	 * in case the datestring does not matche the the date mask in the session for
	 * the logged in user or the default date a pattern date mask will be checked
	 * and returned
	 * 
	 * @param session    Session Map
	 * @param dateString the date value
	 * @return String
	 */
	public static String returnMatchedDateMask(Map<String, Object> session, String dateString) {
		String dateMask = returnDateMask(session);
		if (dateMask.length() == dateString.length()) {
			SimpleDateFormat sdf = new SimpleDateFormat(dateMask, Locale.ENGLISH);
			try {
				sdf.parse(dateString);
			} catch (ParseException e) {
				// exception means mask is not parsable with this date string, different formats
				dateMask = getDatePattern(dateString);
			}
		} else // difference in length means difference in formats
		{
			dateMask = getDatePattern(dateString);
		}
		return dateMask;
	}

	/**
	 * Returns the date mask in the session for the logged in user. If there is no
	 * date mask then the default date mask will be returned.
	 * 
	 * @param session Session Map
	 * @return String
	 */
	public static String returnDateMask(Map<String, Object> session) {

		HashMap numFormatObj = null;
		SessionCO sesCO = (SessionCO) session.get(ConstantsCommon.SESSION_VO_ATTR);
		if (sesCO != null && sesCO.getUserNbFormats() != null) {
			numFormatObj = ((SessionCO) session.get(ConstantsCommon.SESSION_VO_ATTR)).getUserNbFormats();
		}

		return returnDateFormat(numFormatObj);
	}

	/**
	 * 
	 * Used for returning date format from HashMap of userFormats
	 * 
	 * @param numberFormats Map of User Formats
	 * @return date Format
	 */
	public static String returnDateFormat(Map userFormats) {
		String dateMask = DEFAULT_DATE_FORMAT;
		if (userFormats != null) {
			dateMask = (String) userFormats.get(DATE_MASK_ATTRIBUTE);
			if (dateMask == null) {
				dateMask = DEFAULT_DATE_FORMAT;
			}
		}
		return dateMask;
	}

	/**
	 * 
	 * Used for returning 1900 1 January Date if date i null
	 * 
	 * @param datParam
	 * @return
	 */
	public static Date nullToInitDate(Date datParam) {
		if (datParam == null) {
			Calendar theInitDate = Calendar.getInstance();
			theInitDate.set(Calendar.YEAR, 1900);
			theInitDate.set(Calendar.MONTH, 0);
			theInitDate.set(Calendar.DAY_OF_MONTH, 1);

			theInitDate.set(Calendar.HOUR, 0);
			theInitDate.set(Calendar.MINUTE, 0);
			theInitDate.set(Calendar.SECOND, 0);
			theInitDate.set(Calendar.MILLISECOND, 0);
			return theInitDate.getTime();

		} else {
			return datParam;
		}
	}

	/**
	 * Returns the date object when date string is passed. The date is parsed
	 * according to the format specified in the session and the time is formatted
	 * according the time pattern that is with the date string.
	 * 
	 * @param dateString
	 * @param session
	 * @return
	 * @throws Exception
	 */
	public static Date returnDateObj(String dateString, Map<String, Object> session) throws Exception {
		try {
			String parseFormat = null;
			int colonIndx = dateString.indexOf(":");
			// check if date contains only Time means Colon(:) should be
			// located in first 3 characters, so that only Time Format to be
			// Considered

			if (colonIndx < 4 && colonIndx >= 0) {
				parseFormat = getDatePattern(dateString);
			} else {
				parseFormat = returnMatchedDateMask(session, dateString);// new method
			}
			SimpleDateFormat sdf = new SimpleDateFormat(parseFormat, Locale.ENGLISH);
			return sdf.parse(dateString);
		} catch (Exception e) {
			log.error(e, "ERROR in DateUtil class for getting parse format for date " + dateString);
			throw new BaseException("ERROR in DateUtil class parseDate while parsing date ", e);
		}
	}

	/**
	 * Return the Date Part when data and date part is passed. if pass date and 'MM'
	 * will return the Month
	 * 
	 * @param DatePart - 'MM'/'DD'/'YY'
	 * @param date     - date
	 * @return day/month/year
	 */
	public static Long getDatePart(String DatePart, Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		Integer day;
		if ("DD".equalsIgnoreCase(DatePart)) {
			day = calendar.get(Calendar.DAY_OF_MONTH);
		} else if ("MM".equalsIgnoreCase(DatePart)) {
			day = calendar.get(Calendar.MONTH) + 1;// Added 1 to get the correct month
		} else {
			day = calendar.get(Calendar.YEAR);
		}

		return day.longValue();

	}

	/**
	 * Return date after adding the no of days/month/Quarter/semi-annauly/yearly
	 * with the date passed.
	 * 
	 * @param dateField(this is used to specify the periodicity)
	 * @param theValue       (this is used to specify the number of periodicity)
	 * @param date
	 * @return Date
	 */
	public static Date dateAdd(String dateField, Long theValue, Date date) {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		Long value = theValue;

		int periodicity = 0; // initialize the variable
		if ("dd".equalsIgnoreCase(dateField) || dateField.equalsIgnoreCase(DAY)) // if the periodicityt is daily
		{
			periodicity = Calendar.DATE;// count the periodicity based on days
			// value=valueL; //set the periodicity as 1 day
		}
		if ("yy".equalsIgnoreCase(dateField) || dateField.equalsIgnoreCase(YEAR)) // if the periodicityt is yearly
		{
			periodicity = Calendar.MONTH;// count the periodicity based on Months
			value = (12 * value);// set the periodicity as 12 month
		} else if (dateField.equalsIgnoreCase(SEMI_ANNUALY)) {
			periodicity = Calendar.MONTH;// count the periodicity based on Months
			value = 6 * value;// set the periodicity as 6 months
		} else if (dateField.equalsIgnoreCase(QUARTER)) {
			periodicity = Calendar.MONTH;// count the periodicity based on Months
			value = 3 * value;// set the periodicity as 3 months
		} else if ("mm".equalsIgnoreCase(dateField) || dateField.equalsIgnoreCase(MONTH)) {
			periodicity = Calendar.MONTH;// count the periodicity based on Months
			// value=1L;//set the periodicity as 6 months
		}
		// calculate the date by adding to the date entered the term multiply by the
		// periodicity
		calendar.add(periodicity, value.intValue());

		// returning the new date calculated
		return calendar.getTime();
	}

	/**
	 * Return the month end date
	 * 
	 * @param date
	 * @return
	 */
	public static Date getMonthLastDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		return calendar.getTime();
	}

	/**
	 * Return the month first date
	 * 
	 * @param date
	 * @return
	 */
	public static Date getMonthFirstDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return calendar.getTime();
	}

}
