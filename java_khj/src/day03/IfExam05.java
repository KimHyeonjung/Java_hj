package day03;

import java.util.Scanner;

public class IfExam05 {

	public static void main(String[] args) {
		//정수를 입력 받아 2,3,6의  배수인지 아닌지 판별하는 코드를 작성
		Scanner scan = new Scanner(System.in);
		int num;
		
		System.out.print("정수 입력 : ");
		num = scan.nextInt();
		/*
		if (num % 6 == 0) {
			System.out.println(num + "는 6의 배수입니다.");
		}
		else if (num % 3 == 0) {
			System.out.println(num + "는 3의 배수입니다.");
		}
		else if (num % 2 == 0) {
			System.out.println(num + "는 2의 배수입니다.");
		}
		else {
			System.out.println(num + "은 2,3,6의 배수가 아닙니다.");
		}
		*/
		
		if (num % 2 == 0 && num % 3 != 0) {
			System.out.println(num + "는 2의 배수입니다.");
		}
		else if (num % 3 == 0 && num % 2 != 0) {
			System.out.println(num + "는 3의 배수입니다.");
		}
		else if (num % 6 == 0) {
			System.out.println(num + "는 2의 배수입니다.");
		}
		else {
			System.out.println(num + "은 2,3,6의 배수가 아닙니다.");
		}
	}

}
