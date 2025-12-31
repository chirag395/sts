package com.dtm;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;


public class Exp1 {

	public static void main(String[] args) {
		LocalDate ld = LocalDate.now();
		
		System.out.println(ld);
		
		LocalDateTime ldt = LocalDateTime.now();
		
		System.out.println(ldt);
		
		LocalDate ldspd = LocalDate.of(2033, Month.AUGUST, 12);
		
		System.out.println(ldspd);
		
		Period prd = Period.between(ldspd,  ld);
		
		System.out.println(prd);
		
		
		
		DateTimeFormatter dfmt = DateTimeFormatter.ofPattern("dd-mm-yy");
		
		String formattedDate = ldt.format(dfmt);
		System.out.println(formattedDate);
		
		ZoneId busanZone = ZoneId.of("Asia/Seoul");
		
		ZonedDateTime zdt = ZonedDateTime.now(busanZone);
		System.out.println(zdt);

	}

}
