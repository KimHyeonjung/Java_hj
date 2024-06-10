package day08.homework;

import java.util.Arrays;
import java.util.Scanner;

public class MethodEx01 {
	
	/* 정수 n을 입력받아 입력받은 n 크기를 가지는 배열을 생성하는 코드를 작성하세요.
	 * 단 메서드 이용
	 * */
	public static void main(String[] args) {
		
		
		int size;
		Scanner scan = new Scanner(System.in);
		System.out.print("배열의 길이 입력 : ");
		size = scan.nextInt();
		int [] arr1 = createArray(size);
		System.out.println(Arrays.toString(arr1));
		
		int [] arr2 = null;
		createArray2(arr2, size);
		System.out.println(Arrays.toString(arr2));
	}
	
	/* 기능 : 배열의 크기가 주어지면 주어진 배열 크기의 정수 배열을 생성해서 알려주는 메서드
	 * 매개변수 : 배열의 크기 size => 정수 => int size
	 * 리턴타입 : 정수 배열  => int []
	 * 메서드명 : createArray
	 * */
	public static int[] createArray(int size) {
		if(size < 0) {
			return null;
		}
		int [] arr = new int[size];
		return arr;
//		return new int[num]; //위의 두줄과 동일 주로 이렇게 사용
	}
	
	/**기능 : 배열과 배열의 크기가 주어지면 주어진 배열의 배열 크기의 정수 배열을 생성하는 메서드 ( 잘못된 경우)
	 * 매개변수 : 배열, 배열의 크기 => int []arr, int num
	 * 리턴타입 : 없음 => void
	 * 메서드명 : createArray2
	 * */
	public static void createArray2(int []arr, int size) {
		// =을 통해 기본형 변수나 참조형 변수를 재할당하면 main에 있는 원본이 바뀌지 않는다.
		arr = new int[size];
	}
}
