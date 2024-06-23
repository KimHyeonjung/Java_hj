package day18.homework.v2;

import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import day18.homework.v1.Schedule;
import program.Program;

public class MemberManager implements Program{

	private Scanner scan = new Scanner(System.in);
	private List<Member> mbList = new ArrayList<Member>();
	private final int INSERT = 1;
	private final int UPDATE = 2;
	private final int DELETE = 3;
	private final int PREV = 4;
	private String mbFormat = "[{0}] {1} / {2}";
	
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
			System.out.println(mbList);
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
		return;
	}

	private void deleteMember() {
		searchId();	
		System.out.print("삭제할 회원 [번호] 선택 :");
		int num = scan.nextInt() -1;
		mbList.remove(num);
		System.out.println("회원 삭제 완료");
	}

	private void updateMember() {
		searchId();
		System.out.print("수정할 회원 [번호] 선택 :");
		int num = scan.nextInt() -1;
		System.out.print("변경 할 이름 : ");
		String name = scan.nextLine();
		mbList.get(num).setName(name);
		System.out.println("이름 수정 완료");
	}
	
	//회원 검색 기능
	private void searchId() {
		//조회할 ID를 입력받고
		System.out.print("ID 입력 :");
		scan.nextLine();
		String searchId = scan.nextLine();
		if(idFormatChk(searchId)) {
			//해당 id의 일정 목록을 번호를 매겨서 보여줌
			for(int i = 0 ; i < mbList.size(); i++) {
				if(mbList.get(i).getId().contains(searchId)) {
					System.out.println(MessageFormat.format(mbFormat, i+1, mbList.get(i).getId()
										, mbList.get(i).getName()));
				}
			}
			return;
		}
	}
	
	private void insertMember() {
		Member tmp = inputMember();
		if(tmp == null) {
			return;
		}
		mbList.add(tmp);
		System.out.println("회원 추가 완료");
	}
	
	private Member inputMember() {
		scan.nextLine();
		System.out.print("ID : ");
		String id = scan.nextLine();
		if(idFormatChk(id)) {
			System.out.print("이름 : ");
			String name = scan.nextLine();
			List<Schedule> list = new ArrayList<Schedule>();
			return new Member(id, name, list);		
		}
		return null;
		
	}
	
	private boolean idFormatChk(String id) {
		String regex = "^[\\w]{3,8}$";
		if(!Pattern.matches(regex, id)) {
			System.out.println("잘못 입력(영어,숫자 3~8글자만 가능");
			return false;
		}
		return true;
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
