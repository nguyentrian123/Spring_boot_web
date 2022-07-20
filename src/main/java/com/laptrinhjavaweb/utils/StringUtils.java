package com.laptrinhjavaweb.utils;

public class StringUtils {

	public static boolean isNull(String str)
	{
		if (str != null && str != "" ) {
			return false;
		}

		return true;
	}
}
