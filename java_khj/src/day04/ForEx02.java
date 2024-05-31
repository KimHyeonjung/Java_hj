package day04;

public class ForEx02 {

	public static void main(String[] args) {
		//구구단 2단을 출력하는 코드를 for문을 이용하여 작성하세요
		int num = 2;
		
		for(int i = 1 ; i <= 9 ; i++) { //초기화 부분에서 선언된 i는 반복문 내에서만 사용 가능
			System.out.println(num + " x "+ i + " = " + (num*i));
		}
	}

}
