package day04.homework;

import java.util.Scanner;

public class UpDownGame {

	public static void main(String[] args) {
		/* 1~100사이의 랜덤한 수를 생성하여 해당 숫자를 맞추는 게임을 작성하세요.
		 * 예 (랜덤한 수가 30) 일 경우
		 * 정수 입력 : 50
		 * DOWN! 출력
		 * 정수 입력 : 20
		 * UP!
		 * 정수 입력 : 30
		 * 정답입니다!		
		 * 		
		 * */
		Scanner scan = new Scanner(System.in);
		int num;
		int min = 1, max = 100;				//↓ 랜덤 함수의 리턴값이 0.0나올 경우 최소 값을 넣어주기 위함
		int random = (int)(Math.random() * (max - min + 1) + min);		
		System.out.println("랜덤한 수 : " + random);
		
		do {
			System.out.print("정수 입력 : ");
			num = scan.nextInt();
			if(num < random) {
				System.out.println("Up!");
			}
			else if(num > random) {
				System.out.println("Down!");
			}
		} while (num != random);
		System.out.println("정답입니다.");		
	}

}
