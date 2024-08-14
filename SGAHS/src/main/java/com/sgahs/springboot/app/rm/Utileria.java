package com.sgahs.springboot.app.rm;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Utileria {
	
	public String localDateToString(LocalDate localDate) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        return localDate.format(formatter);
    }

}
