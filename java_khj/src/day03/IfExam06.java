package day03;

import java.util.Scanner;

public class IfExam06 {

	public static void main(String[] args) {
		//성적을 입력받아 성적에 맞는 학점을 출력하는 코드를 작성하세요
		Scanner scan = new Scanner(System.in);
		int score;
		
		System.out.print("성적을 입력하세요 : ");
		score = scan.nextInt();
		/*
		if (score >= 90 && score <= 100) {
			System.out.println("학점은 A입니다.");
		}
		else if (score >= 80 && score < 90) {
			System.out.println("학점은 B입니다.");
		}
		else if (score >= 70 && score < 80) {
			System.out.println("학점은 C입니다.");
		}
		else if (score >= 60 && score < 70) {
			System.out.println("학점은 D입니다.");
		}
		else if (score < 60) {
			System.out.println("학점은 F입니다.");
		}
		else {
			System.out.println("성적 입력이 잘못되었습니다.");
		}
		*/
		if (score < 0 || score > 100) {
			System.out.println("잘못된 성적입니다.");
		}
		//여기까지 왔다면 score는 0이상 100이하
		else if (score >= 90) {
			System.out.println("학점은 A입니다.");
		}
		//여기까지 왔다면 score는 0이상 90미만
		else if (score >= 80) {
			System.out.println("학점은 B입니다.");
		}
		else if (score >= 70) {
			System.out.println("학점은 C입니다.");
		}
		else if (score >= 60) {
			System.out.println("학점은 D입니다.");
		}
		else  {
			System.out.println("학점은 F입니다.");
		}		
	}

}
