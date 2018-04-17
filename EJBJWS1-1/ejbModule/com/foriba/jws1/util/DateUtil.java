package com.foriba.jws1.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.XMLGregorianCalendar;


public class DateUtil {

	public static final SimpleDateFormat FORMAT_MID = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	public static final SimpleDateFormat FORMAT_DATE = new SimpleDateFormat("dd/MM/yyyy");
	public static final SimpleDateFormat FORMAT_TIMESTAMP = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
	public static final SimpleDateFormat FORMAT_SHORT = new SimpleDateFormat("yyyy-MM-dd");

	public Date toDate(String date) throws ParseException {
		Date getDate = FORMAT_DATE.parse(date);
		return getDate;
	}

	public Date toDateShort(String date) throws ParseException {
		Date getDate = FORMAT_SHORT.parse(date);
		return getDate;
	}

	public Timestamp toTimeStampDate(String date) throws ParseException {
		Date getDate = FORMAT_TIMESTAMP.parse(date);
		Timestamp timestamp = new Timestamp(getDate.getTime());
		return timestamp;
	}
	
	public String toDateString(Date date) throws ParseException {
		String getDate = FORMAT_MID.format(date);
		return getDate;
	}
	
	public Date toDateMID(String date) throws ParseException {
		Date getDate = FORMAT_MID.parse(date);
		return getDate;
	}
	
	public static Date toDateWithGivenTime(XMLGregorianCalendar calendar, XMLGregorianCalendar issueTime) throws DatatypeConfigurationException {
	    if (calendar == null) {
	      return null;
	    }
	    calendar.setHour(issueTime.getHour());
	    calendar.setMinute(issueTime.getMinute());
	    calendar.setSecond(issueTime.getSecond());
	    calendar.setMillisecond(issueTime.getMillisecond());
	    return calendar.toGregorianCalendar().getTime();
	  }
	
	public static Date toDate(XMLGregorianCalendar calendar) {
	    if (calendar == null) {
	      return null;
	    }
	    return calendar.toGregorianCalendar().getTime();
	  }
	
	public static Timestamp toTimestamp(XMLGregorianCalendar calendar) throws ParseException {
		Date date=toDate(calendar);
		Timestamp timestamp = new Timestamp(date.getTime());
		return timestamp;
		
	}

}
