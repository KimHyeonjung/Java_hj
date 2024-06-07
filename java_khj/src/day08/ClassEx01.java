package day08;

public class ClassEx01 {

	public static void main(String[] args) {
		int result;
		int num1 = 1, num2 =2;
		int num3 = 3;
		//좋은 메서드는 재사용이 가능해야 sum O[O] / sum2[X]
		result = sum(num1, num2);
		result = sum(num3, result);
		System.out.println(result);
		sum2(num1, num2);
	}
	/* 두 정수의 합을 구해서 알려주는 메서드
	 * 매개변수 : 두 정수 => int num1, int num2
	 * 리턴타입 : 두 정수의 합 => int
	 * 메서드명 : sum
	 *             ▼리턴타입이 있는 경우 반드시 return 이 있어야 됨 */                           
	public static int sum(int num1, int num2) {
		int sum = num1 + num2;
		return sum;
	}
	/* 두 정수의 합을 콘솔에 출력하는 메서드
	 * 매개변수 : 두 정수 => int num1, int num2
	 * 리턴타입 : 없음 => void
	 * 메서드명 : sum2
	 * */
	public static void sum2(int num1, int num2) {
		int sum = num1 + num2;
		System.out.println(sum);
	}
}
//학생 클래스
class Student{
	//이름, 학번, 학기, 사는곳, 전공
	String name, num, address, major;
	int semester;	
}