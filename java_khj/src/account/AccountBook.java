package account;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.Data;


@Data

public class AccountBook implements Serializable, Comparable<AccountBook>{

	private static final long serialVersionUID = -3995952016754223086L;
//	 * 날짜 : 2024-06-10
//	 * 수입/지출 : 수입
//	 * 분류 목록 => 정해진 분류 목록만 고를수 있게 
//	 * 월급
//	 * 용돈
//	 * 부수입
//	 * 분류 : 월급
//	 * 금액 : 3000000
//	 * 내용 : 6월 월급
	private Date date;
	private String inExStatus;
	private String category;
	private String detail;
	private int amount;
	
	
	public AccountBook(String date, String inExStatus, String category, String detail, int amount) throws ParseException {
		setDate(date);
		this.inExStatus = inExStatus;
		this.category = category;
		this.detail = detail;
		this.amount = amount;
	}


	public void setDate(String date) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy MM dd");
		this.date = format.parse(date);
	}

	public String getDate(){
		SimpleDateFormat format = new SimpleDateFormat("yyyy년 MM월 dd일");
		return format.format(date);
	}

	public String getAmount(){
		DecimalFormat format = new DecimalFormat("\u00A4 ###,###,###");
		return format.format(amount);
	}
	
	@Override
	public String toString() {
		return getDate() + " | " + inExStatus + " | " + category + " |  "
				+ getAmount() + " |   " + detail;
	}


	@Override
	public int compareTo(AccountBook o) {
		if(date.equals(o.date)) {
			return inExStatus.compareTo(o.inExStatus);
		}
		return date.compareTo(o.date);
	}
	
	
	
}
