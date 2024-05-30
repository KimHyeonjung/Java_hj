package day03;

import java.util.Scanner;

public class Exam03 {

	public static void main(String[] args) {
		//2개의 과목 성적을 입력받아 평균 60점 이상이고 모든 과목이 과락(40점미만)이 없으면 합격, 아니면 불합격을 출력하는 코드를 작성
		Scanner scan = new Scanner(System.in);
		int score1, score2;
		String result;
		
		//2개의 과목 성적 입력받기
		System.out.print("과목1 성적 입력 : ");
		score1 = scan.nextInt();
		System.out.print("과목2 성적 입력 : ");
		score2 = scan.nextInt();
		
		int sum = score1 + score2;
		double avg = (double)sum / 2; // 과목 성적 평균 구하고 
		/*
		boolean isFail = (score1 < 40) || (score2 < 40); //각 과목이 40점 미만이 없는지 확인
		result = (avg >= 60 &&  !isFail) ? "합격" : "불합격";
		*/
		boolean isTrue = avg >= 60 && (score1 >= 40) && (score2 >= 40); //평균이 60점 이상이고 각 과목이 40점 미만이 없는지 확인
		result = (isTrue) ? "합격" : "불합격";
		
		
		System.out.println("결과 : " + result);
		
	}

}
