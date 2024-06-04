package day06;

public class ArrayEx05 {

	public static void main(String[] args) {
		// 100이하의 소수를 찾는 예제 : 에라토스테네스의 체		
		// 1(x) 2 3 4(x) 5 6(x) 7 8(x) 9(x) 10(x)
		int [] arr = new int[101]; //x: 1, o: 0 으로 처리
		arr[1] = 1;
		//2부터 에라토스테네스의 체를 이용하여 배열에 x체크
		/* 반복횟수 : i는 2부터 100까지 1씩 증가
		 * 규칙성 : i번지에 있는 값이 1이면 건너뛰고 아니면 반복문
		 * 반복문 종료 후 : 없음
		 * */
			/* 반복횟수 : j는 i*2부터 100까지 i씩 증가
			 * 규칙성 : j번지에 있는 값을 1로 변경
			 * 반복문 종료 후 : 없음
			 * */
		for(int i = 2; i < arr.length; i++) {
			if(arr[i] == 1) { //이미 1(x)가 되어 있으면 다음 숫자로
				continue;
			}
			for(int j = (i*2); j < arr.length; j+=i) {
				arr[j] = 1;
			}
		}
		//2부터 100까지 x체크 안된 수(값이 0인 수)를 출력
		System.out.print("100 이하의 소수 출력 : ");
		for(int i = 2; i < arr.length; i++) {
			if(arr[i] == 0) {
				System.out.print(i + " ");
			}
		}
	}

}
