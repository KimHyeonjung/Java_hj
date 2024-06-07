package day08.homework;

import java.util.Scanner;

public class MethodEx01 {
	
	/* 정수 n을 입력받아 입력받은 n 크기를 가지는 배열을 생성하는 코드를 작성하세요.
	 * 단 메서드 이용
	 * */
	public static void main(String[] args) {
		
		int [] arr1;
		int n;
		Scanner scan = new Scanner(System.in);
		System.out.print("배열의 길이 입력 : ");
		n = scan.nextInt();
		arr1 = arraySetLength(n);
		System.out.println(arr1.length);
	}
	
	/* 기능 : 정수 n을 입력받아 길이가 n인 배열을 생성
	 * 매개변수 : 배열의 길이 n => 정수 => int n
	 * 리턴타입 : int[] 
	 * 메서드명 : arraySetLength
	 * */
	public static int[] arraySetLength(int n) {
		int [] arr = new int[n];
		return arr;
	}

}
