package day24;

public class Ex03 {

	public static void main(String[] args) {
		int num = 2;
		/* 짝수라고 판별해야 하는데 짝수, 홀수 모두 출력이 됨
		 * 원인 : if문 조건식 뒤에 ;이 와서 {}안의 실행문이 조건문과 상관 없이 실행
		 * 해결방법 : ;을 지운다
		 * */
		
		if(num % 2 == 0); {
			System.out.println("작수");
		}
		
		if(num % 2 != 0); {
			System.out.println("홀수");
		}
	}

}
