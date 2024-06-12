package day11;

public class SuperEx01 {

	public static void main(String[] args) {
		Child1 c = new Child1(10, 20);
		c.printchild();
	}
	
}

class Parent1{
	int num = 1;
	
	public void print() {
		System.out.println("num : " + num);
	}
	public Parent1(int num) { // 생성자 추가시 기본생성자가 없어졌기 때문에 자식 클래스에서 문제가 생길 수 있음
		this.num = num;
	}
}

class Child1 extends Parent1{
	int num2 = 2;
	
	public void printchild() {
		//부모클래스에 있는 print를 호출
		super.print();
		System.out.println("num2 : " + num2);
	}
	public Child1(int num1, int num2) {
		//부모 클래스의 기본 생성자가 없어서 있는 생성자를 호출
		super(num1);
		this.num2 = num2;
	}
}