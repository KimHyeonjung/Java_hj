package account;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class AccountBook implements Serializable{

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
	
	
	
}
