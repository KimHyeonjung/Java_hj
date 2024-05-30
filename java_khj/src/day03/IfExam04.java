package day03;

import java.util.Scanner;

public class IfExam04 {

	public static void main(String[] args) {
		//정수를 입력 받아 3의 배수인지 아닌지 판별하는 코드를 작성하세요
		int num;
		
		Scanner scan = new Scanner(System.in);
		
		System.out.print("정수 입력 : ");
		num = scan.nextInt();
		
		if(num % 3 == 0) {
			System.out.println(num + "은 3의 배수입니다");
		}
		else {
			System.out.println(num + "은 3의 배수가 아닙니다.");
		}
	}

}
