package day18.homework.v2;

import java.util.InputMismatchException;
import java.util.Scanner;

import day18.homework.v1.ScheduleManager;

public class V2Main {

	public static void main(String[] args) {
		
		int menu = 1;
		Scanner scan = new Scanner(System.in);
		do {
			printMenu();
			try {
				menu = scan.nextInt();
				runMenu(menu);
			}catch(InputMismatchException e) {
				System.err.println("올바른 타입의 입력을 하세요.");
			}
		}while(menu != 3);
		
	}
	private static void runMenu(int menu) {
		switch (menu) {
		case 1: 
			MemberManager mm = new MemberManager();
			mm.run();
			break;
		case 2:
			ScheduleManager sm = new ScheduleManager();
			sm.run();
			break;
		case 3:
			System.out.println("종료합니다.");
			break;
		default:
			System.out.println("올바른 메뉴를 선택하세요.");
		}
		
	}

	public static void printMenu() {
		System.out.println("---------프로그램 메뉴---------");
		System.out.println("1. 회원 관리");
		System.out.println("2. 일정 관리");
		System.out.println("3. 프로그램 종료");
		System.out.println("----------------------------");
		System.out.print("메뉴 선택 : ");
		
		
	
	}


}
