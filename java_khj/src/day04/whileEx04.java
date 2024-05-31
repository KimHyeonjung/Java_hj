package day04;

public class whileEx04 {

	public static void main(String[] args) {
		/* 1부터 10까지 합을 구하는 코드를 작성하세요
		 * 
		 * 반복횟수 : i는 1부터 10까지 1씩 증가
		 * 규칙성 : sum = sum + i , i++
		 * 반복문 종료 후 : 1부터 10까지의 합은 : sum 출력
		 * */
		int i = 1;
		int sum = 0;
		while (i <= 10) {			
			sum +=i;
			i++;			
		}
		System.out.println("1부터 10까지의 합은 : " + sum);
	}
	

}
