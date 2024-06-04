package day06;

import java.util.Arrays;
import java.util.Scanner;

public class ArrayEx06 {

	public static void main(String[] args) {
		/* 4과목의 성적을 입력받아 배열에 저정하고, 과락(40점 미만)이 없고 평균이 60점이 넘으면 Pass, 아니면 Fail이라고 출력하세요
		 * */
		
		//성적 4개를 입력 받아 배열에 저장
		Scanner scan = new Scanner(System.in);
		int [] score = new int[4];
		System.out.print("4 과목의 성적을 입력(예 : 00 00 00 00) : ");
		for(int i = 0; i < score.length; i++) {
			score[i] = scan.nextInt();
		}
		//입력한 값이 배열에 잘 들어갔는지 확인
		System.out.println(Arrays.toString(score));
		
		int sum = 0;
		boolean isFail = false;
		double avg;
		//향상된 for문을 이용한 총합 계산
		for(int tmp : score) {
			sum += tmp;
		}
		//향상된 for문을 이용한 과락여부 확인
		for(int tmp : score) {
			if(tmp < 40) {
				isFail = true;
				break; //break 없어도 결과는 같지만, 조금이라도 덜 비교하기 위해 쓰는게 좋음
			}
		}
		/*// 내가 짠 코드
		for(int i = 0; i < score.length; i++) {
			if(score[i] < 40) { // 과락 여부 확인하여
				isFail = true;	// 변수에 저장
			}
			sum += score[i]; // 입력받은 성적의 총합을 계산
		}
		*/	
		avg = (double)sum / score.length; // 총합을 이용하여 평균을 계산		
		
		//과락(40점 미만)이 없고 평균이 60점이 넘으면 Pass, 아니면 Fail이라고 출력
		if(!isFail && avg > 60) {
			System.out.println(score.length + "과목 평균 : " + avg + " Pass");
		}
		else {
			System.out.println(score.length + "과목 평균 : " + avg + " Fail");			
		}
		
//		int sum = 0, i;
//		double avg;
//		for(i = 0; i < score.length; i++) {
//			if(score[i] < 40) {
//				break;
//			}
//			sum += score[i];
//		}
//		avg = sum / score.length;
//		if(i == score.length && avg > 60) {
//			System.out.println(score.length + "과목의 평균은 : " + avg + " Pass");
//		}
//		else if(i == score.length && avg <= 60) {
//			System.out.println(score.length + "과목의 평균은 : " + avg +" Fail");			
//		}
//		else {
//			System.out.println("Fail");			
//		}
	}

}
