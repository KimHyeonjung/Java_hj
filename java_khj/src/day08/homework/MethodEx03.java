package day08.homework;

import java.util.Arrays;
import java.util.Scanner;

public class MethodEx03 {

	/* 배열에 랜덤으로 1~9사이의 중복되지 않은 배열을 생성하고 콘솔에 출력하는 코드를 작성하세요.
	 * 단 메서드 이용 
	 * day08.homework.MethodEx01, day08.homework.MethodEx02, day08.MethodEx05를 합친 예제
	 * */
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int size;
		int min;
		int max;
		int [] rNumList;
		do {
			System.out.print("배열의 크기, 최소값, 최대값 입력 : ");
			size = scan.nextInt();
			min = scan.nextInt();
			max = scan.nextInt();
			if(size > max - min + 1) {
				System.out.println("랜덤 숫자의 범위가 배열 크기보다 작습니다.");	
			}
		} while(size > max - min + 1);
		rNumList = createRandomArray(size, min, max);
		System.out.println(Arrays.toString(rNumList));
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
	public static int[] createRandomArray(int size, int min, int max) {
		if(size < 0) {
			return null;
		}
		if(min > max) {
			int tmp = min;
			min = max;
			max = tmp;
		}
		//랜덤 숫자의 범위가 배열의 크기보다 작은 경우.
		if(size > max - min + 1) {
			return null;
		}
		int [] arr = new int[size];
		int rNum;
		for(int i = 0; i < size; i++) {			
			rNum = getRandomNum(min, max);
			while(contains(arr, i, rNum)) {
				rNum = getRandomNum(min, max);
			}
			arr[i] = rNum;
		}
		return arr;
	}
	
	/**기능 : 배열에 0번지부터 count-1번지까지 확인하려는 정수가 있는지 확인해서 있는지 없는지를 알려주는 메서드 
	 * 매개변수 : 배열, 확인할 갯수 count, 확인하려는 정수 =>
	 * 리턴타입 : 있는지 없는지 => 
	 * 메서드명 : contains
	 * */
	public static boolean contains(int [] arr, int count, int num) {
		if(arr == null) {
			return false;
		}
		if(count > arr.length) {
			count = arr.length;
		}
		for(int i = 0; i < count; i++) {
			if(arr[i] == num) {
				return true;
			}
		}
		return false;
	}
	
	/* 기능 : 배열에 중복된 값이 있는지 없는지 검사하는 메서드
	 * 매개변수 : 검사할 배열 => 정수 배열 => int []arr 
	 * 리턴타입 : 있는지 없는지 => boolean
	 * 메서드명 : duplicationCheck
	 * */
	public static boolean duplicationCheck(int []arr) {
		for(int i = 0; i < arr.length; i++) {
			for(int j =0; j < i ; j++) {
				if(arr[i] == arr[j]) {
					return true;
				}
			}
		}
		return false;
	}

}
