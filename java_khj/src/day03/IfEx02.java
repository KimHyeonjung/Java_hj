package day03;

public class IfEx02 {

	public static void main(String[] args) {
		// if else문을 이용하여 홀짝 판별 예제
		int num = 10;
		if (num % 2 == 0) {
			System.out.println("짝수");
		} 
		//else는 현 위치에서 위에 있는 연결된 조건문들이 모두 거짓이면
		else {
			System.out.println("홀수");
		}
	}

}
