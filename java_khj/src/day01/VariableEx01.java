package day01;

public class VariableEx01 {

	public static void main(String[] args) {
		
		char ch1; // 문자형 변수 ch1를 선언
		char ch2 = 'A'; //문자형 변수 ch2를 선언하고 A로 초기화
		char ch3, ch4; // 같은 자료형인 경우 ,를 통해 동시에 선언 할 수 있다.
		char ch5 = '+', ch6 = '\\'; //문자형 변수 ch5를 선언하고 +로 초기화, 문자형 변수 ch6을 선언하고 \n으로 초기화
		System.out.println(ch2);
		System.out.println(ch6);
		System.out.println(ch5);
		// 정수 리터럴은 기본이 int 고 실수 리터럴 기본이 double
		long num1 = 1234567890123L; //정수값이 int형으로 표현되지 않으면 접미사 L를 반드시 붙여야 합니다.
		float num2 = 3.14F; //3.14는 double형이기 때문에 반드시 접미사 F를 붙여야 합니다. 
		double num3 = 3.14;
		boolean isEven = true; //소문자만 가능		
	}

}
