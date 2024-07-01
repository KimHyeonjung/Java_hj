package day24;

import java.util.Scanner;

public class Ex05 {

	public static void main(String[] args) {
		/* 문자열을 입력받아 exit가 아니면 입력한 문자열을 출력하고, exit이면 종료하는 코드를 작성하려고 했다.
		 * 원인 : res 초기값이 false > 반복문 실행 안됨/ 문자열을 ==로 비교 / continue
		 * 해결방법 : res 초기값 true / str.equals("exit") / continue 대신 break;
		 * */
//		Scanner scan = new Scanner(System.in);
//		boolean res = false;
//		while(res) {
//			System.out.print("문자열 입력 :");
//			String str = scan.next();
//			if(str == "exit") {
//				continue;
//			}
//			System.out.println(str);
//		}
		Scanner scan = new Scanner(System.in);
		boolean res = true;
		while(res) {
			System.out.print("문자열 입력 :");
			String str = scan.next();
			if(str.equals("exit")) {
				break;
			}
			System.out.println(str);
		}
		
		
	}

}
