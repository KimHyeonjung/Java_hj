package day04;

public class WhileEx03 {

	public static void main(String[] args) {
		/* 구구단 2단을 출력하는 코드를 작성하세요
		 * 반복횟수 : i는 1부터 9까지 1씩 증가
		 * 규칙성 : 2 x i = (2*i)을 출력
		 * 반복문 종료 후 : 없음
		 * */
		int i = 1, result = 0;
		int num = 2;
		while (i <= 9) {
			result = num * i;
			System.out.println(num + " x " + i + " = " + result);
			i++;
		}
		
	}

}
