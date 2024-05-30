package day03;

import java.util.Scanner;

public class SwitchExam03 {

	public static void main(String[] args) {
		/* //산술 연산자와 두 정수를 입력받아 산술연산자에 맞는 연산 결과를 출력하는 코드를 작성하세요 (switch문 사용)
		 * */
		Scanner scan = new Scanner(System.in);
		int num1, num2; 
		double result = 0;
		char op;
		
		System.out.print("두 정수와 산술 연산자를 입력하세요(예 : 1 + 2) : ");
		num1 = scan.nextInt();
		op = scan.next().charAt(0);
		num2 = scan.nextInt();	
		
		switch (op) {
		case '+' :
			result = num1 + num2;
			System.out.println(num1 + " " + op + " " + num2 + " = " + (int)result);
			break;
		case '-' :
			result = num1 - num2;
			System.out.println(num1 + " " + op + " " + num2 + " = " + (int)result);
			break;
		case '*' :
			result = num1 * num2;
			System.out.println(num1 + " " + op + " " + num2 + " = " + (int)result);
			break;
		case '/' :
			result = num1 / (double)num2;
			System.out.println(num1 + " " + op + " " + num2 + " = " + result);
			break;
		case '%' :
			result = num1 % num2;
			System.out.println(num1 + " " + op + " " + num2 + " = " + (int)result);
			break;
		default : 
			System.out.println(op + "는 잘못된 산술 연산자입니다.");
		}
	}

}
