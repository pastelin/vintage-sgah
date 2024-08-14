package mx.com.sgah.utileria;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Fecha {
	
	private Fecha() {
		
	}

	public static String getCurrentDate() {
		LocalDate currentDay = LocalDate.now();
		return currentDay.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	}
	
	public static String getCurrenDateYMD() {
		LocalDate currentDay = LocalDate.now();
		String fecha = currentDay.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
		fecha = fecha.substring(0, fecha.length() -2);
		
		return fecha + "01";
	}
}
