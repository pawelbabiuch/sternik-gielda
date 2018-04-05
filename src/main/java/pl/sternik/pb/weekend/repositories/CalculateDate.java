package pl.sternik.pb.weekend.repositories;

import java.util.Calendar;
import java.util.Date;

public class CalculateDate {

	@SuppressWarnings("deprecation")
	public static Date getDate() {
		
		final Date normalDate = new Date();
		final Calendar cal = Calendar.getInstance();
		
		cal.set(Calendar.YEAR, normalDate.getYear());
		cal.set(Calendar.MONTH, normalDate.getMonth());
		cal.set(Calendar.DAY_OF_MONTH, normalDate.getDay());
		
		return cal.getTime();
	}
	
	public static Date getDate(int year, int month, int dayOfMonth) {
		final Calendar cal = Calendar.getInstance();
		
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month);
		cal.set(Calendar.DAY_OF_MONTH, dayOfMonth);
		
		return cal.getTime();
	}
}
