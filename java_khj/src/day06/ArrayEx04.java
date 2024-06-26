package day06;

import java.util.Scanner;

public class ArrayEx04 {

	public static void main(String[] args) {
		//4자리 정수를 입력받아 입력받은 정수를 역순으로 배열에 저장하고, 출력하는 코드를 작성하세요.
		Scanner scan = new Scanner(System.in);
		//1234 => 0번지에 4, 1번지에 3, 2번지에 2, ... 저장하고, 4,3,2,1 순으로 출력
		int [] arr1 = new int[4];
		int num;
		//4자리 정수 입력
		System.out.print("정수 입력 : ");
		num = scan.nextInt();
		//4자리 정수가 아니면 잘못 입력됐다고 출력
		if(num < 1000 || num > 9999) {
			System.out.println("4자리 정수가 아닙니다.");
		}
		//4자리 정수이면 
		else {			
			//역순으로 배열에 저장, %연산과 /연산을 이용
			//1234 =>4를 추출해서 배열에 저정하고, 1234 =>123으로 만듬
			//123 => 3을 추출해서 배열에 저장하고, 123 =>12로 만듬
			//...
			//1 => 1을 추출해서 배열에 저장하고, 1 =>0로 만듬
			
			int tmp = num;
			int i = 0;
			while (tmp !=0) {
				arr1[i++] = tmp % 10;
				tmp = tmp / 10;				
			}	
			//배열에 값을 순차적으로 출력
			System.out.print("정수 역순 : ");
			for(int tmp2 : arr1) {
				System.out.print(tmp2);
			}
		}
	}

}
