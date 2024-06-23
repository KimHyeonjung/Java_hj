package day18.homework.v1;

import java.io.Serializable;
import java.util.regex.Pattern;




public class Schedule implements Comparable<Schedule>, Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5186145646125526228L;
	
	private String date;
	private String schedule;
	private String detail;
	
	
	public Schedule(String date, String schedule, String detail) throws Exception  {
		setDate(date);
		this.schedule = schedule;
		this.detail = detail;
	}

	
	@Override
	public String toString() {
		return "[" + date + "] / 일정: " + schedule + " / 상세: " + detail;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) throws Exception {
		String regex = "^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}$";
		 if(!Pattern.matches(regex, date)) {
				throw new Exception("날짜 형식이 잘못되었습니다.");
		}
		this.date = date;
	}

	public String getSchedule() {
		return schedule;
	}

	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	@Override
	public int compareTo(Schedule o) {
		return date.compareTo(o.date);
	}
	
	
	
}
