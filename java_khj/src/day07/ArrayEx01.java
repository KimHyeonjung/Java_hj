package day07;

import java.util.Arrays;
import java.util.Scanner;

public class ArrayEx01 {

	public static void main(String[] args) {
		/* 3명의 학생의 국어, 영어, 수학 성적을 입력 받고
		 * 각 학생의 평균을 구하는 코드를 작성하세요.
		 * */
		int studentCount = 3;
		int [] kor, eng, math; 
		kor = new int[studentCount];
		eng = new int[studentCount];
		math = new int[studentCount];


		Scanner scan = new Scanner(System.in);

		//반복문을 이용하여 학생 성적을 입력
		// i는 0부터 3보다 작을때까지 i씩 증가
		//학생(i+1)의 성적 입력 문구를 출력
		for(int i = 0; i < studentCount; i++) {
			System.out.print("학생" + (i+1) + "의 성적을 입력하세요(국어, 영어, 수학 순) : ");	
			kor[i] = scan.nextInt();
			eng[i] = scan.nextInt();
			math[i] = scan.nextInt();			
		}
		//각 학생의 평균을 구함
		int sum;
		for(int i = 0; i < studentCount; i++) {
			sum = kor[i] + eng[i] + math[i];
			System.out.println("학생" + (i+1) + "의 평균은 : " + ((double)sum / studentCount));	
		}
		/* 각 과목의 평균을 구하는 코드를 작성하세요.		 
		 * 국어 평균 : 93.66666
		 * 영어 평균 : 90
		 * 수학 평균 : 86.66666 
		 * */

		int korSum = 0, engSum = 0, mathSum = 0;
		int subCount = 3;
		for(int i = 0; i < subCount; i++) { //반복문 한번만 사용
			korSum += kor[i];
			engSum += eng[i];
			mathSum += math[i];
		}
		System.out.println("국어 평균 : " + ((double)korSum / subCount));
		System.out.println("영어 평균 : " + ((double)engSum / subCount));
		System.out.println("수학 평균 : " + ((double)mathSum / subCount));
		
		//향상된 for문을 써서
		sum = 0;
		for(int tmp : kor) {
			sum += tmp;
		}
		System.out.println("국어 평균 : " + ((double)sum / kor.length));
		sum = 0;
		for(int tmp : eng) {
			sum += tmp;
		}
		System.out.println("영어 평균 : " + ((double)sum / eng.length));
		sum = 0;
		for(int tmp : math) {
			sum += tmp;
		}
		System.out.println("수학 평균 : " + ((double)sum / math.length));
	}


}
