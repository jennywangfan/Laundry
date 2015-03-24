package com.triplexilaundry.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Verification {
	private final static String regex_CellPhone ="^1[3458]\\d{9}$";
	
	private static Pattern patternCellPhone;
	
	static {
		patternCellPhone = Pattern.compile(regex_CellPhone);
	}
	public static boolean isCellNumber(String phoneNum){
		Matcher match = patternCellPhone.matcher(phoneNum);
		return match.matches();
	}

}
