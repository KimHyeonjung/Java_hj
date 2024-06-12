package day11.access;

import day11.Student;

public class AccessModifierEx01 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

class A{
	
	Student std = new Student();
	public void test() {
//		std.name = "abd"; //protected인데 A는 Student를 상속받지 않아서 
		std.birthday = "2000-01-01";
		System.out.println(std.birthday);
	}
}