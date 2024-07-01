package day24;

public class Ex12 {

	public static void main(String[] args) {
		/* 1 + 2의 결과를 출력하기 위해 다음과 같이 작성했다.
		 * 원인 : sum 메소드의 리턴타입을 정의해주지 않고서 반환값이 있는것 처럼 사용
		 * 해결방법 : 변수에 저장하지 않거나 sum 메소드에 반환값을 정의해준다.
		 * */
//		int res = sum(1,2);
		sum(1,2);
	}
	
	public static void sum(int num1, int num2) {
		System.out.println(num1 + num2);
	}

}
