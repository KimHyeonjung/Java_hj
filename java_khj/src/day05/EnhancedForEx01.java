package day05;

public class EnhancedForEx01 {

	public static void main(String[] args) {
		// 배열에 1,2,3,4,5를 저장한 후 향상된 for문을 이용하여 조회하는 예제
		int [] arr = new int[] {1,2,3,4,5};
		
		for(int tmp : arr) { //탐색만 할 경우 간단하게 사용 할 수 있다
			System.out.println(tmp);
		}
		//위 향상된 for문과 같은 결과
		for(int i = 0 ; i < arr.length; i++) {
			int tmp = arr[i];
			System.out.println(tmp);
		}
	}

}
