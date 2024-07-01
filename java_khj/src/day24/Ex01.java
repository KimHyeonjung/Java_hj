package day24;

public class Ex01 {

	public static void main(String[] args) {
		int num1 = 4;
		double num2 = num1;
		/* 에러 발생
		 * 원인 : 자료형이 정수에서 실수로는 자동 형변환을 해주지만 실수에서 정수로는 해주지 않기때문에
		 * 해결방법 : 명시적 형변환을 해줘야 됨
		 * */
//		int num3 = num2;
		int num3 = (int)num2;
	}

}
