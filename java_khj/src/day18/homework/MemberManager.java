package day18.homework;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import program.Program;

public class MemberManager implements Program{

	private Scanner scan = new Scanner(System.in);
	private List<Member> memList = new ArrayList<Member>();
	private final int INSERT = 1;
	private final int UPDATE = 2;
	private final int DELETE = 3;
	private final int PREV = 4;
	
	@Override
	public void printMenu() {
		System.out.println("----------------------\n"
				+ "메뉴\r\n"
				+ "1. 회원 추가\r\n"
				+ "2. 회원 수정\r\n"
				+ "3. 회원 삭제\r\n"
				+ "4. 이전으로\r\n"
				+ "----------------------\n"
				+ "메뉴 선택 :");
	}

	@Override
	public void runMenu(int menu) throws Exception {
		switch (menu ) {
		case INSERT :
			insertMember();
			System.out.println(memList);
			break;
		case UPDATE :
			updateMember();
			break;
		case DELETE :
			deleteMember();
			break;
		case PREV :
			prevMenu();
			break;
		default :
			System.out.println("잘못된 입력입니다.");
		}
	}

	private void prevMenu() {
		// TODO Auto-generated method stub
		
	}

	private void deleteMember() {
		// TODO Auto-generated method stub
		
	}

	private void updateMember() {
		// TODO Auto-generated method stub
		
	}

	private void insertMember() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void run() {
		
		int menu = INSERT;
		do {
			printMenu();
			try {
				menu = scan.nextInt();
				runMenu(menu);
			} catch(InputMismatchException e) {
				System.out.println("올바른 타입을 입력하세요");
				scan.next();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			
		}while(menu != 4);
		
	}

}
