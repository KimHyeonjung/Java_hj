package day03;

import java.util.Scanner;

public class Exam01 {

	public static void main(String[] args) {
		//성적을 입력 받아 60점 이상이면 Pass , 아니면 Fail이 출력 되도록 코드를 작성하세요
		int score;
		String result;
		
		Scanner scan = new Scanner(System.in);
		
		System.out.print("성적 입력 : ");
		score = scan.nextInt();
		result = (score>=60) ? "Pass" : "Fail";
		System.out.println(score + "점은 " + result);		
		
	}

}
