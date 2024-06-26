package day13;

import java.text.MessageFormat;
import java.util.Scanner;

public class Ex02 {

	public static void main(String[] args) {
		/* 두 정수와 산술 연산자를 입력받아 산술연산결과를 알려주는 메소드를 정의 및 구현하고
		 * main 메소드에서 실행하는 코드를 작성하세요.
		 * 단 예외 발생 시 예외 처리하는 코드를 추가하세요.
		 */
		Scanner scan = new Scanner(System.in);
		int num1, num2;
		System.out.print("정수와 산술 연산자 입력(예:3 + 3) : ");
		num1 = scan.nextInt();
		char operator = scan.next().charAt(0);
		num2 = scan.nextInt();
		try {
			double answer = calculator(num1, operator, num2);
			String format = "{0} {1} {2} = {3}";
			System.out.println(MessageFormat.format(format, num1, operator, num2, answer));
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	/**기능 : 두 정수와 산술 연산자가 주어지면 산술 연산 결과를 알려주는 메소드
	 * @param num1
	 * @param operator
	 * @param num2
	 * @return 산술 연산 결과
	 * @throws ArithmeticException 0으로 나눌 수 없다.
	 * @throws IllegalArgumentException 0으로 나눌 수 없다.
	 */
	public static double calculator(int num1, char operator, int num2) {
		double answer = 0;
		switch (operator) {
		case '+' :
			answer = num1 + num2;
			break;
		case '-' :
			answer = num1 - num2;
			break;
		case '*' :
			answer = num1 * num2;
			break;
		case '/' :
			if(num2 == 0) {
				throw new ArithmeticException("0으로 나눌 수 없습니다.");
			}
			answer = num1 / (double)num2;
			break;
		case '%' :
			if(num2 == 0) {
				throw new ArithmeticException("0으로 나눌 수 없습니다.");
			}
			answer = num1 % num2;
			break;
		default:
			throw new IllegalArgumentException("잘못된 연산자 : " + operator);
		}
		return answer;
	}

}
