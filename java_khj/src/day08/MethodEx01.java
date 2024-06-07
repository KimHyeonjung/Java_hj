package day08;

import java.util.Scanner;

public class MethodEx01 {

	public static void main(String[] args) {
		/* 주어진 num가 소수인지 아닌지 판별하는 코드를 작성하세요.
		 * 단, 메서드를 이용해서
		 * */
		Scanner scan = new Scanner(System.in);		
		int num;
		
		do {
			System.out.print("(종료:-1) 정수 입력 : ");
			num = scan.nextInt();
			boolean result = primeNumberReader(num);
			if(num == -1) {
				break;
			}
			if(result) {
				System.out.println(num + "는 소수 입니다.");
			}
			else {
				System.out.println(num + "는 소수가 아닙니다.");
			}
		} while(true);
		//100이하의 소수를 판별하는 코드를 작성하세요.  primeNumberReader 메서드를 이용하여
		for(int i = 1; i <= 100; i++) {
			if(primeNumberReader(i)) {
				System.out.print(i + " ");
			}
		}
		System.out.println();
		//100이하의 소수를 판별하는 코드를 작성하세요.  primeNumberReader2 메서드를 이용하여
		for(int i = 1; i <= 100; i++) {
			if(primeNumberReader2(i)) {
				System.out.print(i + " ");
			}
		}
	}
	/* 매개변수 : 정수 하나 => int num
	 * 리턴타입 : 소수인지 아닌지 => boolean
	 * 메서드명 : primeNumberReader 
	 * */
	public static boolean primeNumberReader(int num) {
		int count = 0;
		for(int i = 1; i <= num; i++) {
			if(num % i == 0) {
				count++;
			}
		}
		//1, 2, 3 다 결과는 동일
		//1
		return count == 2 ? true : false;
		
		//2
//		if(count == 2) {
//			return  true;
//		}
//		return  false;
		
		//3
//		if(count == 2) {
//			return  true;
//		}
//		else {
//			return  false;
//		}
	}
	public static boolean primeNumberReader2(int num) {
		if(num == 1) {
			return false;
		}
		//1과 자기자신을 제외한 수 중에서 약수를 반복문을 이용하여 찾음
		for(int i = 2; i <num; i++) {
			//1과 자기자신을 제외한 수 중에서 약수가 있으면
			if(num % i == 0) {
				//소수가 아님
				return false;
			}
		}
		//반복문이 끝날때까지 약수를 못찾았다 => 약수가 1과 자기자신뿐이다 => 소수이다.
		return true;
	}

}
