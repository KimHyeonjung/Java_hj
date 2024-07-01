package day24;

import lombok.Getter;
import lombok.Setter;

public class Ex10 {

	public static void main(String[] args) {
		PointB p1 = new PointB();
		p1.setX(100);
		/* 원인 : 생성자 매개변수 자료형이 맞지 않음
		 * 해결방법 : 매개변수 자료형을 맞춰줌 ,메소드를 오버로딩해서 새메소드를 추가
		 * */
		p1.print(10.5, 20.5);
	}

}

class PointB{
	@Setter
	@Getter
	private int x, y;
	/* 메소드 오버로딩을 이용하여 print메소드를 여러개 생성하려고 했다.
	 * 원인 : 동일한 매개변수를 갖는 메소드를 오버로딩함
	 * 해결방법 : 매개변수를 다르게 해주거나 메소드명을 변경하거나 
	 * */
	
	public void print() {
		System.out.println(x + "," + y);
	}
//	public void print(int x, int y) {
//		System.out.println(x + "," + y);
//	}
	public void print(double x, double y) {
		System.out.println(x + "," + y);
	}
	/*public int print() {
		System.out.println(x + "," + y);
		return x + y;
	}*/
}
