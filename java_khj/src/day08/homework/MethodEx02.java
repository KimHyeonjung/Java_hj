package day08.homework;

import java.util.Arrays;

public class MethodEx02 {
	
	/* 1~9사이의 랜덤한 수를 배열에 저장하여 콘솔에 출력하는 코드를 작성하세요.
	 * 메서드 이용 (2개)
	 * */
	public static void main(String[] args) {
		int n = 5;
		int [] arr1 = new int[n];
		addRnumArray(arr1, n);
		showArray(arr1);
	}
	/* 기능 : 1~9사이의 랜덤한 수를 생성하는 메서드
	 * 매개변수 : 최소값과 최대값 =>  => int max, int min 
	 * 리턴타입 : 랜덤한 수 => 정수 => int
	 * 메서드명 : getRandomNum
	 * */
	public static int getRandomNum(int max, int min) {
		int random = (int)(Math.random() * (max - min +1) + min);
		return random;
	}
	/* 기능 : 랜덤한 수를 배열에 저장하는 메서드
	 * 매개변수 : 정수 배열과 배열의 길이 => int 배열, int 배열길이 => int []arr, int n
	 * 리턴타입 : 정수 배열 => int[]
	 * 메서드명 : addRnumArray
	 * */
	public static int[] addRnumArray(int []arr, int n) {
		for(int i = 0; i < n; i++) {
			arr[i] = getRandomNum(9, 1);
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
