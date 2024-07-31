package db.community.main;

import java.util.Scanner;

import db.community.controller.MemberController;
import db.community.controller.PrintController;
import db.community.model.vo.MemberVO;
import program.Program;

public class ProgramImp implements Program{

	private Scanner scan = new Scanner(System.in);
	private MemberVO member = null;
	private MemberController memberController = new MemberController(scan);
	
	@Override
	public void printMenu() {
		PrintController.printMainMenu();
	}

	@Override
	public void run() {
		char menu = '0';
		do {
			printMenu();
			try {
				menu = scan.next().charAt(0);
				PrintController.printBar();
				runMenu(menu);
				PrintController.printBar();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}while(menu != '3');
	}
	@Override
	public void runMenu(int menu) throws Exception {
		char ch = (char)menu;
		switch(ch) {
		case '1' :
			login();
			break;
		case '2' :
			signup();
			break;
		case '3' :
			System.out.println("프로그램을 종료합니다.");
			break;
		default :
			PrintController.wrongMenu();
		}
	}


	private void login() {
		//회원정보를 입력받아 회원정보와 일치하는 회원의 정보를 가져옴
		member = memberController.login();
		System.out.println(member);
		//회원정보가 없으면
		if(member == null) {
			PrintController.loginFail();
			return;
		}
		//관리자이면 관리자 기능을 실행
		if(member.getMe_authority().equals("ADMIN")) {
			admin();
			return;
		}
		//회원이면 회원 기능을 실행
		user();
	}

	private void user() {
		// TODO Auto-generated method stub
		System.out.println("사용자입니다.");
		
	}

	private void admin() {
		// TODO Auto-generated method stub
		System.out.println("관리자입니다.");
		
	}
	
	private void signup() {
		
		if(memberController.signup()) {
			PrintController.signupSuccess();
		}else {
			PrintController.signupFail();
		}
	}

}
