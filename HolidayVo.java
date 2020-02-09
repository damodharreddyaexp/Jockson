package com.attacomsian.response;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class HolidayVo {

	private Date holidayDate;
	private String day;
	private String holidayName;
	private Long workingDaysCount;
    private LocalDate dueDate;
    private LocalDate startDate;
	private ArrayList<String> holidayList;
	private int count;
	
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public ArrayList<String> getHolidayList() {
		return holidayList;
	}

	public void setHolidayList(ArrayList<String> holidayList) {
		this.holidayList = holidayList;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	public Date getHolidayDate() {
		return holidayDate;
	}

	public void setHolidayDate(Date holidayDate) {
		this.holidayDate = holidayDate;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getHolidayName() {
		return holidayName;
	}

	public void setHolidayName(String holidayName) {
		this.holidayName = holidayName;
	}

	public Long getWorkingDaysCount() {
		return workingDaysCount;
	}

	public void setWorkingDaysCount(Long workingDaysCount) {
		this.workingDaysCount = workingDaysCount;
	}
	
}
