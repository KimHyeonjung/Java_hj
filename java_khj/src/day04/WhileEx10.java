package day04;

import java.util.Scanner;

public class WhileEx10 {

	public static void main(String[] args) {
		/* 반복문을 이용하여 문자를 입력받고 입력받은 문자가 y이면 반복문을 종료하는 코드를 작성하세요.
		 * 반복횟수 : ch는 y가 아닌 문자로 시작해서 y가 아니면 반복
		 * 규칙성 : ch에 Scanner를 이용하여 문자를 저장
		 * 반복문 종료 후 : 없음
		 * */
		char inValue = 0;
		Scanner scan = new Scanner(System.in);
		
		
		while(inValue != 'y') {
			System.out.print("반복을 종료하려면 'y'를 입력하시오 : ");
			inValue = scan.next().charAt(0);			
		}
	}
}
