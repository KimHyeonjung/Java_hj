package day24;

public class Ex09 {

	public static void main(String[] args) {
		PointA p1 = new PointA(10,20);
		p1.print();
		/* x의 값을 100, y의 값을 200으로 수정하려 했다
		 * 원인 : 멤버변수의 접근 제어자가 private이라 그냥은 접근할수 없음 
		 * 해결방법 : setter를 만들어줌
		 * */
//		
//		p1.x = 100;
//		p1.y = 200;
		p1.setX(100);
		p1.setY(200);
		
		p1.print();
		/* 기본 생성자를 이용해서 객체를 생성하려 했다
		 * 원인 : 기본생성자가 없다
		 * 해결방법 : 기본생성자를 만들어 준다
		 * */
		PointA p2 = new PointA();
		p2.print();
		
	}

}

class PointA{
	private int x, y;
	
	public PointA() {}
	public PointA(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public void print() {
		System.out.println(x + "," + y);
	}
	
	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}
}
