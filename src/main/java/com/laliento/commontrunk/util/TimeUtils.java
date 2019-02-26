package com.laliento.commontrunk.util;

import java.util.Calendar;
import java.util.Date;

public final class TimeUtils {

	public static Date calculaTiempoatras(Integer minutos) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MINUTE, -minutos);
		return calendar.getTime();
	}
}
