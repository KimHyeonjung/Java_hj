package day06;

public class ArrayEx01 {

	public static void main(String[] args) {
		//배열 선언 방법에 따른 차이점을 보여주는 예제
		int [] arr1, arr2;	//int형 배열arr1과 arr2를 선언 
		int arr3 [], arr4 ; //int형 배열arr3과 int형 변수arr4를 선언
		
		arr1 = new int[3]; //arr1은 배열
		arr2 = new int[3]; //arr2은 배열
		arr3 = new int[3]; //arr3은 배열
		//arr4 = new int[3]; //arr4은 배열이 아님
		arr4 = 10; //arr4는 변수
	}

}
