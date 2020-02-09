package com.attacomsian.response;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Set;

import org.springframework.stereotype.Component;

import de.jollyday.Holiday;
import de.jollyday.HolidayCalendar;
import de.jollyday.HolidayManager;

@Component
public class Jollyday {
/*	public static void main(String[] args) {

		Jollyday oTest = new Jollyday();

		// Print out all holiday dates of each year.
		for (int i = 2020; i <= 2020; i++) {
			ArrayList<String> lHolidays = oTest.getHolidays(i);
			for (String sHoliday : lHolidays) {
				System.out.print(sHoliday + " | ");
			}
			System.out.println();
		}

	} */

	/**
	 * Get a list of holiday dates of a given year.
	 * 
	 * @param iYear
	 * @return List of holiday dates of the whole year.
	 */
	public HolidayVo getNextMonthDueDate() {
		HolidayVo vo = new HolidayVo();
		LocalDate startDate = LocalDate.now();
		String formattedDate = startDate.format(DateTimeFormatter.ofPattern("MM/dd/uuuu"));
		LocalDate endDate = LocalDate.parse(formattedDate, DateTimeFormatter.ofPattern("MM/dd/uuuu"))
				.with(TemporalAdjusters.firstDayOfNextMonth()) // Stop here for first of next month.
				.with(TemporalAdjusters.firstDayOfMonth()); // Include this for last day of next month.
		endDate = endDate.plusDays(9);
		vo.setStartDate(startDate); 
		vo.setDueDate(endDate); 
		return vo;
	}
	
	public ArrayList<String> getHolidays(int iYear) {
		ArrayList<String> oSortedHolidays = new ArrayList<String>();
		try {
			HolidayManager oManager = HolidayManager.getInstance(HolidayCalendar.AUSTRALIA);

			Set<Holiday> oHolidays = oManager.getHolidays(iYear); // Quebec province
			for (Holiday oHoliday : oHolidays) {
				oSortedHolidays.add(oHoliday.toString());
			}
			// Sorted holiday dates.
			Collections.sort(oSortedHolidays);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}

		return oSortedHolidays;
	}
	
	  public static Long getWorkingDaysBetweenTwoDates(Date startDate, Date endDate,ArrayList<String> holidayList) {  
		    Calendar startCal;  
		    Calendar endCal;  
		    startCal = Calendar.getInstance();  
		    startCal.setTime(startDate);  
		    endCal = Calendar.getInstance();  
		    endCal.setTime(endDate);  
		    Long workDays = new Long(0);   

		    //Return 0 if start and end are the same  
		    if (startCal.getTimeInMillis() == endCal.getTimeInMillis()) {  
		        return 0L;  
		    }  

		    if (startCal.getTimeInMillis() > endCal.getTimeInMillis()) {  
		        startCal.setTime(endDate);  
		        endCal.setTime(startDate);  
		    }  

		/*    do {  
		        startCal.add(Calendar.DAY_OF_MONTH, 1);  
		        if (startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY   
		       && startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {  
		            ++workDays;  
		        }  
		    } while (startCal.getTimeInMillis() < endCal.getTimeInMillis());   */
		    do {
		          startCal.add(Calendar.DAY_OF_MONTH, 1);
		          if (startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY
		          && startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY
		          && !holidayList.contains((Integer) startCal.get(Calendar.DAY_OF_YEAR))) {
		              ++workDays;
		          }
		} while (startCal.getTimeInMillis() < endCal.getTimeInMillis()); 


		    return workDays;  
		}

	public Date convertLocateDateToDate(LocalDate input) {
		ZoneId defaultZoneId = ZoneId.systemDefault();
		// local date + atStartOfDay() + default time zone + toInstant() = Date
		Date date = Date.from(input.atStartOfDay(defaultZoneId).toInstant());
		return date;

	}

}