package day15;

import java.util.ArrayList;
import java.util.Scanner;

public class Ex01 {

	public static void main(String[] args) {
		/* 전화번호를 5번 입력받아 리스트에 저장하는 코드를 작성하세요.
		 * 단, 전화번호는 올바르게 입력했다고 가정.(정규표현식 사용하지 않아도됨)*/
		Scanner scan = new Scanner(System.in);
		ArrayList<String> contact = new ArrayList<String>();
		
		for(int i = 1; i <= 5; i++) {
			System.out.print("전화번호 "+i+" 입력 : ");
			contact.add(scan.next());
		}
		System.out.println(contact);
		/* 삭제할 전화 번호를 입력 받아 삭제하는 코드를 작성하세요*/
		System.out.print("삭제할 번호를 입력 : ");
		String delNum = scan.next();
		if(contact.remove(delNum)) {
			System.out.println(delNum + "를 삭제했습니다.");
		}
		else {
			System.out.println("번호가 없습니다.");
		}
		System.out.println(contact);
	}

}
