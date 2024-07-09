package test;

import java.util.Date;

public class Test {

	public static void main(String[] args) {
		Date ctime = new Date(System.currentTimeMillis());
		Date ctime2 = new Date(System.currentTimeMillis() + 5000);
		
		System.out.println(ctime);
		System.out.println(ctime2);
		while(ctime2.after(new Date(System.currentTimeMillis()))) {
			
			
		}
		System.out.println("종료");
	}

}
