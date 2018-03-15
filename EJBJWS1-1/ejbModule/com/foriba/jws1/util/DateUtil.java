package com.foriba.jws1.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class DateUtil {
	public static final SimpleDateFormat FORMAT_MID = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	public static final SimpleDateFormat FORMAT_DATE = new SimpleDateFormat("dd/MM/yyyy");
	public static final SimpleDateFormat FORMAT_TIMESTAMP = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
	
	public Date toDate(String date) throws ParseException
	{
		Date getDate = FORMAT_DATE.parse(date);
		return getDate;
	}
	public Timestamp toTimeStampDate(String date) throws ParseException
	{
		Date getDate = FORMAT_TIMESTAMP.parse(date);
		Timestamp timestamp = new Timestamp(getDate.getTime());
		return timestamp;
	}
	
}
