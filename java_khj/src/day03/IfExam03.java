package day03;

import java.util.Scanner;

public class IfExam03 {

	public static void main(String[] args) {		
		//산술 연산자와 두 정수를 입력받아 산술연산자에 맞는 연산 결과를 출력하는 코드를 작성하세요
		Scanner scan = new Scanner(System.in);
		int a, b; 
		double result = 0;
		char op;
		
		System.out.print("두 정수와 산술 연산자를 입력하세요(예 : 1 + 2) : ");
		a = scan.nextInt();
		op = scan.next().charAt(0);
		b = scan.nextInt();		
		
		if (op == '*') {
			result = a * b;
			System.out.println("" + a + " " + op + " " +b + " = " + (int)result);
		}
		else if (op == '/') {
			result = (double)a / b;
			System.out.println("" + a + " " + op + " " +b + " = " + result);
		}
		else if (op == '+') {
			result = a + b;
			System.out.println("" + a + " " + op + " " +b + " = " + (int)result);
		}
		else if (op == '-') {
			result = a - b;
			System.out.println("" + a + " " + op + " " +b + " = " + (int)result);
		}
		else if (op == '%') {
			result = a % b;	
			System.out.println("" + a + " " + op + " " +b + " = " + (int)result);
		}
		else {
			System.out.println(op + "잘못된 산술 연산자입니다");
		}		
		
	}

}
