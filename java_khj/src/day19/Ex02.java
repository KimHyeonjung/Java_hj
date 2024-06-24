package day19;

import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Ex02 {

	public static void main(String[] args) {
		/* 주민번호를 입력받아 입력받은 주민번호의 생일을 출력하는 코드를 작성하세요.
		 * 
		 */
		String format = "{0}년 {1}월 {2}일";
		Scanner scan = new Scanner(System.in);
		System.out.print("주민등록 번호 입력 : ");
		String rrn = scan.nextLine();
		//주민번호 형식이 맞는지 확인
		boolean res = checkRrn(rrn);
		if(!res) {
			System.out.println("올바른 주민등록 번호가 아닙니다.");
			return;
		}
		String yOb = "";
		
		int num = Integer.parseInt(rrn.substring(0, 2));
		if(num < 25) {
			yOb = "20" + rrn.substring(0, 6);
		}else {
			yOb = "19" + rrn.substring(0, 6);
		}
		System.out.print("생년월일은 : ");
		System.out.println(MessageFormat.format(format, yOb.substring(0, 4), yOb.substring(4, 6), yOb.substring(6)));
		String result = convert(yOb);
		String result2;
		try {
			result2 = convert2(yOb);
		} catch (ParseException e) {
			System.out.println("잘못된 주민번호입니다.");
			return;
		}
		System.out.println(result);
		System.out.println(result2);
		
	}
	private static String convert(String yOb) {
		String year = yOb.substring(0, 4);
		String month = yOb.substring(4, 6);
		String day = yOb.substring(6);
		
		return year + "-" + month + "-" + day;
	}
	private static String convert2(String yOb) throws ParseException {
		//yyyyMMdd 문자열을 날짜 객체로 변환
		SimpleDateFormat format1 = new SimpleDateFormat("yyyyMMdd");
		Date date = format1.parse(yOb);
		//변환된 날짜 객체를 yyyy-MM-dd형태로 변환
		SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
		return format2.format(date);
	}
	
	private static boolean checkRrn(String rrn) {
		String regex = "^\\d{6}-\\d{7}$";
		if(Pattern.matches(regex, rrn)) {
			return true;
		}
		return false;
	}

}
