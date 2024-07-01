package day24;

public class Ex07 {

	public static void main(String[] args) {
		/* 1부터 10까지의 합을 구하려고 했다
		 * 원인 : 조건식 끝에 ;
		 * 해결방법 : ; 제거
		 * */
//		
//		int i;
//		int sum = 0;
//		for(i = 1; i<=10; i++); {
//			sum += i;
//		}
//		System.out.println("1부터 10까지 합 : " + sum);
//		
		int i;
		int sum = 0;
		for(i = 1; i<=10; i++) {
			sum += i;
		}
		System.out.println("1부터 10까지 합 : " + sum);
	}

}
