package day03;

import java.util.Scanner;

public class Exam02 {

	public static void main(String[] args) {
		//학생 3명의 성적을 입력받아 총점과 평균을 구하는 코드를 작성하세요
		int stud1, stud2, stud3;
		int totalScore;
		double avg;
		
		Scanner scan = new Scanner(System.in);
		
		System.out.print("학생1 성적 입력 : ");
		stud1 = scan.nextInt();
		System.out.print("학생2 성적 입력 : ");
		stud2 = scan.nextInt();
		System.out.print("학생3 성적 입력 : ");
		stud3 = scan.nextInt();
		
		totalScore = stud1 + stud2 + stud3;
		avg = (double)totalScore/3;
		System.out.print("총점 : " + totalScore+".  평균 : " + avg);
	}

}
