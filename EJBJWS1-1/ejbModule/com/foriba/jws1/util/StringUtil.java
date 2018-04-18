package com.foriba.jws1.util;

public class StringUtil {
	
	public static Boolean stringNullorEmpty(String value)
	{
		if(null==value || value.equals(""))
		{
			return false;
		}
		return true;
	}

}
