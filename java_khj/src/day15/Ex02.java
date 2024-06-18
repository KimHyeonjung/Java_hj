package day15;

import java.util.ArrayList;
import java.util.Scanner;

public class Ex02 {

	private static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {
		/* 다음 기능을 가진 프로그램을 작성하세요.
		 * 메뉴
		 * 1. 번호 추가
		 * 2. 번호 삭제
		 * 3. 번호 조회 (sysout(list)로 대체
		 * 4. 종료
		 */
		final int INSERT = 1;
		final int DELETE = 2;
		final int SEARCH = 3;
		final int EXIT = 4;
		int menu = EXIT +1;
		ArrayList<String> numList = new ArrayList<String>();
		do {
			printMenu();
			menu = scan.nextInt();
			switch (menu) {
			case INSERT :
				System.out.print("추가할 번호 입력 : ");
				String addNum = scan.next();
				if(numList.contains(addNum)) {
					System.out.println("이미 등록된 번호입니다.");
				}else {
					System.out.println(addNum + "를 등록했습니다.");
					numList.add(addNum);
				}
				break;
			case DELETE :
				System.out.print("삭제할 번호 입력 : ");
				String delNum = scan.next(); 
				if(numList.remove(delNum)) {
					System.out.println(delNum + "를 삭제했습니다.");
				}else {
					System.out.println("일치하는 번호가 없습니다.");
				}
				break;
			case SEARCH :
				System.out.println(numList);
				break;
			case EXIT :
				System.out.println("프로그램을 종료합니다.");
				break;
			default :
				System.out.println("잘못된 메뉴입니다.");
			}
		}while(menu != 4);

	}
	public static void printMenu() {
		System.out.println("메뉴 ───────━");
		System.out.println("1. 번호 추가  │");
		System.out.println("2. 번호 삭제  │");
		System.out.println("3. 번호 검색  │");
		System.out.println("4. 종료      ┃");
		System.out.println(" ━────────── ");
		System.out.print("메뉴 선택: ");
	}

}





