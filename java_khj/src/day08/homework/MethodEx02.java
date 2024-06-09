package day08.homework;

import java.util.Arrays;

public class MethodEx02 {
	
	/* 1~9사이의 랜덤한 수를 배열에 저장하여 콘솔에 출력하는 코드를 작성하세요.
	 * 메서드 이용 (2개)
	 * */
	public static void main(String[] args) {
		int n = 5;
		int [] arr1 = new int[n];
		createRandomArray(n, 1, 9);
		showArray(arr1);
	}
	/* 기능 : 최소값과 최대값 사이의 랜덤한 수를 생성하는 메서드
	 * 매개변수 : 최소값과 최대값 =>  => int min, int max 
	 * 리턴타입 : 랜덤한 수 => 정수 => int
	 * 메서드명 : getRandomNum
	 * */
	public static int getRandomNum(int min, int max) {
		int random = (int)(Math.random() * (max - min +1) + min);
		return random;
	}
	
	/* 기능 : 배열의 크기와 최소값과 최대값이 주어졌을 때, 랜덤한 배열을 만들어서 알려주는 메서드 
	 * 매개변수 : 배열의 크기, 최소값, 최대값 => int n, int min, int max
	 * 리턴타입 : 정수 배열 => int[]
	 * 메서드명 : createRandomArray
	 * */
	public static int[] createRandomArray(int n, int min, int max) {
		int [] arr = new int[n];
		for(int i = 0; i < n; i++) {
			arr[i] = getRandomNum(min, max);
		}
		return arr;
	}
	
	/* 기능 : 배열을 콘솔에 출력해주는 메서드
	 * 매개변수 : 정수 배열 => int []arr
	 * 리턴타입 : void
	 * 메서드명 : showArray
	 * */
	public static void showArray(int []arr) {
		System.out.println(Arrays.toString(arr));
	}
}
