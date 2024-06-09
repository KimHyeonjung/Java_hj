package day08.homework;

import java.util.Scanner;

public class MethodEx01 {
	
	/* 정수 n을 입력받아 입력받은 n 크기를 가지는 배열을 생성하는 코드를 작성하세요.
	 * 단 메서드 이용
	 * */
	public static void main(String[] args) {
		
		int [] arr1;
		int num;
		Scanner scan = new Scanner(System.in);
		System.out.print("배열의 길이 입력 : ");
		num = scan.nextInt();
		arr1 = createArray(num);
		System.out.println(arr1.length);
	}
	
	/* 기능 : 배열의 크기가 주어지면 주어진 배열 크기의 정수 배열을 생성해서 알려주는 메서드
	 * 매개변수 : 배열의 크기 num => 정수 => int num
	 * 리턴타입 : 정수 배열  => int []
	 * 메서드명 : createArray
	 * */
	public static int[] createArray(int num) {
		int [] arr = new int[num];
		return arr;
	}

}
