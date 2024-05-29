package day02;

import java.util.Scanner;

public class ScannerEx02 {

	public static void main(String[] args) {
		/* 두 정수와 연산자를 입력 받고 입력받은 결과를 콘솔에 출력하세요
		 * 예시
		 * 정수1을 입력하세요 : 1
		 * 정수2를 입력하세요 : 2
		 * 연산자를 입력하세요 : +
		 * 입력 결과 : 1 + 2
		 */
		int num1, num2;
		char operator;
		Scanner scan = new Scanner(System.in); //import 단축키 Ctrl + Shift + O
		
		System.out.print("정수1을 입력하세요 : ");
		num1 = scan.nextInt();
		System.out.print("정수2를 입력하세요 : ");
		num2 = scan.nextInt();
		System.out.print("연산자를 입력하세요 : ");
		operator = scan.next().charAt(0);
		
		System.out.println("입력 결과 : " + num1 + " " + operator + " " + num2);
		
	}

}
