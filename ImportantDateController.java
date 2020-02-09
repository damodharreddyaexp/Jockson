package com.attacomsian.controller;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.attacomsian.response.HolidayVo;
import com.attacomsian.response.Jollyday;

@RestController
public class ImportantDateController {

	@Autowired
	Jollyday jolyDay;

	@GetMapping(value = "/importantDates")
	public ResponseEntity<HolidayVo> getImportantDates() {
		HolidayVo res = null;
		ArrayList<String> holidayList = null;
		res = jolyDay.getNextMonthDueDate();
		holidayList = jolyDay.getHolidays(res.getStartDate().getDayOfYear());
		res.setHolidayList(holidayList);
		Date startDate = jolyDay.convertLocateDateToDate(res.getStartDate());
		Date endDate = jolyDay.convertLocateDateToDate(res.getDueDate());
		Long count = jolyDay.getWorkingDaysBetweenTwoDates(startDate, endDate, holidayList);
		res.setWorkingDaysCount(count); 
		res.setCount(holidayList.size()); 
		return new ResponseEntity<HolidayVo>(res, HttpStatus.OK);

	}
}