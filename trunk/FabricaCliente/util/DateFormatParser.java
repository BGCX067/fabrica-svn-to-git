package util;

import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class DateFormatParser {

	private final static String PATTERN= "dd/MM/yyyy";
	
	public static String parseDate(Date date)
	{
		SimpleDateFormat format = new SimpleDateFormat(PATTERN);
		return format.format(date);
	}

}
