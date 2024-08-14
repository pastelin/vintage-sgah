package com.sgahs.springboot.app.rm;

import java.time.LocalDate;

public class LocalDateUtil {

	
	public static int getCurrentMonth() {
		LocalDate currentDate = LocalDate.now();
		
		return currentDate.getMonthValue();
	}

	public static int getCurrentYear() {
		LocalDate currentDate = LocalDate.now();
		
		return currentDate.getYear();
	}
	
}
