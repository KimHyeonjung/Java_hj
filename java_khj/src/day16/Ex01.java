package day16;

import java.util.Scanner;

public class Ex01 {

	public static void main(String[] args) {
		/* Up Down 게임 예제를 구현하세요
		 * 랜덤으로 숫자를 만들고 (1~100) 만들어진 숫자를 맞추는 게임
		 * 랜덤 : 33
		 * 입력 : 50
		 * 출력 : DOWN!
		 * 입력 : 25
		 * 출력 : UP!
		 * 입력 : 33
		 * 출력 : 정답!
		 * */
		Scanner scan = new Scanner(System.in);
		int min = 1, max = 100;
		int num;
		int random = random(min, max);
		System.out.println(random);
		do {
			System.out.print("입력 : ");
			num = scan.nextInt();
			upNdownCheck(num, random);
			
		}while(num != random);
		System.out.println("정답!");
		
	}
	public static int random(int min, int max) {
		if(min > max) {
			int tmp = min;
			min = max;
			max = tmp;
		}
		return (int)(Math.random() * (max - min + 1) + min);
	}
	
	public static void upNdownCheck(int num, int random) {
		if(num > random) {
			System.out.println("DOWN!");
		}
		else if(num < random) {
			System.out.println("UP!");
		}
		else {
			System.out.println("정답!");
		}
	}

}
