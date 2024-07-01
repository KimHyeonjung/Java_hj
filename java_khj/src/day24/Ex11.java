package day24;

public class Ex11 {

	public static void main(String[] args) {
		/* 1 + 2를 계산하기 위해 sum을 호출했다
		 * 원인 : 객체 생성없이 객체 메소드를 실행하려 함
		 * 해결방법 : sum 메소드에 static 추가 하던지 Ex11의 객체를 생성해서 사용하던지
		 * */
		Ex11 ex = new Ex11();
		
		int res = ex.sum(1,2);
		System.out.println(1 + " + " + 2 + " = " + res);
		
	}
	
//	public int sum(int num1, int num2) {
//		return num1 + num2;
//	}
	public int sum(int num1, int num2) {
		return num1 + num2;
	}
}
