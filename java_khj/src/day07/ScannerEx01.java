package day07;

import java.util.Scanner;

public class ScannerEx01 {

	public static void main(String[] args) {
		/* Scanner를 이용하여 next()와 nextLine()의 차이를 살펴보는 예제 
		 * next() : 
		 * - 문자열 
		 * - 공백을 제외한 연속된 문자열.
		 * - 문자열을 입력하기 전 입력 버퍼에 엔터가 있으면 버림
		 * nextLine() : 
		 * - 문자열
		 * - 공백을 포함한  문자열
		 * - 문자열을 입력하기 전 입력 버퍼에 엔터가 있으면 가져와서 실행
		 * - 문자열 마지막에 입력한 엔터를 가져가고 가져간 엔터를 버림
		 * */
		Scanner scan = new Scanner(System.in);
		
		System.out.print("문자열 입력(next) : ");
		String str1 = scan.nextLine(); // 마지막에 입력된 엔터를 가져가서 버림
		
		System.out.print("문자열 입력(next) : ");
		String str2 = scan.next(); // 마지막에 입력된 엔터가 입력 버퍼에 남아있음
		
		System.out.print("문자열 입력(next) : ");
		scan.nextLine(); //엔터 제거용
		String str3 = scan.nextLine(); // 위에 엔터 제거를 하지 않을 경우 
									   //입력 버퍼에 남아있는 엔터에 의해 문자열 입력 없이 실행 종료
	}

}
