package day03;

import java.util.Scanner;

public class NestedIfEx02 {

	public static void main(String[] args) {
		int num1, num2; 
		double result = 0;
		char op;
		Scanner scan = new Scanner(System.in);
		System.out.print("두 정수와 산술 연산자를 입력하세요(예 : 1 + 2) : ");
		num1 = scan.nextInt();
		op = scan.next().charAt(0);
		num2 = scan.nextInt();		
		
		if (op == '*') {
			result = num1 * num2;
			System.out.println("" + num1 + " " + op + " " +num2 + " = " + (int)result);
		}
		//num2가 0이면 0으로 나눌수 없다고 출력
		else if (op == '/') {
			if(num2 != 0) {
				result = (double)num1 / num2;
				System.out.println("" + num1 + " " + op + " " +num2 + " = " + result);
			} else {
				System.out.println("0으로 나눌 수 없습니다.");
			}
		}
		else if (op == '+') {
			result = num1 + num2;
			System.out.println("" + num1 + " " + op + " " +num2 + " = " + (int)result);
		}
		else if (op == '-') {
			result = num1 - num2;
			System.out.println("" + num1 + " " + op + " " +num2 + " = " + (int)result);
		}
		//num2가 0이면 0으로 나눌수 없다고 출력
		else if (op == '%') {
			if(num2 != 0) {
				result = num1 % num2;	
				System.out.println("" + num1 + " " + op + " " +num2 + " = " + (int)result);
			} else {
				System.out.println("0으로 나눌 수 없습니다.");
			}
		}
		else {
			System.out.println(op + "잘못된 산술 연산자입니다");
		}		
	}

}
