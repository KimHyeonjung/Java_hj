package day04;

import java.util.Scanner;

public class ForEx03 {

	public static void main(String[] args) {
		/* 문자를 입력받고 입력받은 문자가 y이면 종료하는 코드를 작성하세요(for문 이용)
		 * */
		Scanner scan = new Scanner(System.in);
		
		for (char ch ; ; ) { //대체적으로 순차적으로 증가할 때는 for문 그렇지 않으면 while문 사용
			System.out.print("반복을 종료하려면 y를 입력하세요 : ");
			ch = scan.next().charAt(0);
			if(ch == 'y') {
				break;
			}
		}
	}

}
