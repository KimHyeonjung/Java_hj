package student;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import program.Program;

public class StudentManager implements Program{
	
	//학생 성적 관리를 위한 리스트
	private List<Student> list = new ArrayList<Student>();
	//과목 관리를 위한 리스트
	private List<Subject> subjectList = new ArrayList<Subject>();
	private Scanner scan = new Scanner(System.in);
//////////////////////////////////////////////////////////////////////////////////////상위 메뉴
	@Override
	public void printMenu() {
		System.out.println(
				"메뉴\r\n"
				+ "1. 학생 관리\r\n"
				+ "2. 과목 관리\r\n"
				+ "3. 종료\r\n"
				+ "메뉴 선택 : ");
	}
	@Override
	public void runMenu(int menu) throws Exception {
		switch(menu) {
		case 1 :
			student();
			break;
		case 2 :
			subject();
			break;
		case 3 :
			exit();
			break;
		default :
		}
	}
	@Override
	public void run() {
		int menu;
		do {
			printMenu();
			menu = nextInt();
			try {
				runMenu(menu);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}while(menu != 3);
	}
///////////////////////////////상위 메뉴///////////////////////////////////////////////////////	
	
//<<학생 관리/////////////////////////////////////////////////////////////////////////////////
	private void student() {
		int menu;
		do {
			printBar();
			printStudentMenu();
			menu = nextInt();
			runStudentMenu(menu);
		}while(menu != 5);
	}
	
	private void printStudentMenu() {
		System.out.println(
				"학생 관리 메뉴\r\n"
				+ "1. 학생 추가\r\n"
				+ "2. 학생 수정\r\n"
				+ "3. 학생 삭제\r\n"
				+ "4. 학생 조회\r\n"
				+ "5. 이전으로\r\n"
				+ "메뉴 선택 : "
				);
	}

	private void runStudentMenu(int menu) {
		switch (menu) {
		case 1 : 
			studentInsert();
			break;
		case 2 : 
			studentUpdate();
			break;
		case 3 : 
			studentDelete();
			break;
		case 4 : 
			studentSearch();
			break;
		case 5 : 
			prev();
			break;
		
		default:
		}
	}

	private void studentInsert() {
		//학생을 추가 하는 기능을 구현하세요
		Student std = inputStdExpand();
		//객체가 리스트에 있으면 안내문구 출력 후 종료
		if(list.contains(std)) {
			System.out.println("이미 등록된 학년, 반, 번호입니다.");
			return;
		}
		//없으면 추가 후 안내문구 출력
		list.add(std);
		System.out.println("학생이 추가되었습니다.");
	}

	/**
	 * 학년 반 번호를 입력받아 학생 객체를 만들어서 반환하는 메소드
	 * @return 학년, 반, 번호가 있는 만들어진 학생 객체
	 */
	private Student inputStdInfo() {
		System.out.print("학년 입력 : ");
		int grade = scan.nextInt();
		System.out.print("반 입력 : ");
		int classNum = scan.nextInt();
		System.out.print("번호 입력 : ");
		int num = scan.nextInt();;
		
		return new Student(grade, classNum, num, "");
	}
	
	/**
	 * 학년 반 번호 이름을 입력받아 학생 객체를 만들어서 반환하는 메소드
	 * @return 학년, 반, 번호, 이름이 있는 만들어진 학생 객체
	 */
	private Student inputStdExpand() {
		Student std = inputStdInfo();
		System.out.print("이름 입력 : ");
		scan.nextLine();
		String name = scan.nextLine();
		std.setName(name);
		return std;
	}


	private void studentUpdate() {
		int menu;
		do {
			printStudentUpdateMenu();
			menu = nextInt();
			runStudentUpdateMenu(menu);
			
		}while(menu !=5 );
	}


	private void printStudentUpdateMenu() {
		System.out.println(
				"학생 수정 메뉴\r\n"
				+ "1. 학생 정보 수정\r\n"
				+ "2. 성적 추가\r\n"
				+ "3. 성적 수정\r\n"
				+ "4. 성적 삭제\r\n"
				+ "5. 이전으로\r\n"
				+ "메뉴 선택 : ");
		
	}


	private void runStudentUpdateMenu(int menu) {
		
	}
	
	private void studentDelete() {
		//학년, 반, 번호를 입력해서 학생 객체를 생성
		Student std = inputStdInfo();
		//리스트에서 학생 객체를 삭제하고 삭제에 성공하면 알림문구 출력 후 종료
		if(list.remove(std)) {
			System.out.println("학생을 삭제했습니다.");
			System.out.println(list);
			return;
		}
		printBar();
		//아니면 알림문구 출력
		System.out.println("일치하는 학생이 없습니다.");
		
	}
	
	private void studentSearch() {
		
		//학년 반 번호를 입력 후 객체를 생성
		Student std = inputStdInfo();
		//생성된 객체와 일치하는 객체를 가져옴
		
		//리스트에서 객체와 일치하는 번지를 가져옴
		int index = list.indexOf(std);
		//번지가 0보다 작으면 객체에 null을 저장
		if(index < 0) {
			std = null;
		} else {
		//아니면 객체에 번지에 있는 객체를 가져옴
		std = list.get(index);
		}
		//가져온 객체가 null이면 안내문구 출력 후 종료
		if(std == null) {
			System.out.println("일치하는 학생이 없습니다.");
			return;
		}
		//null이 아니면 학생 정보를 출력
		std.print();
	}

////////////////////////////////////////////////////////////////////////////////학생 관리>>/////
/////<<과목 관리///////////////////////////////////////////////////////////////////////////////
	private void subject() {
		int menu;
		do {
			printSubjectMenu();
			menu = nextInt();
			runSubjectMenu(menu);
		}while(menu != 5);
	}


	private void printSubjectMenu() {
		System.out.println(
				"과목 관리 메뉴\r\n"
				+ "1. 과목 추가\r\n"
				+ "2. 과목 수정\r\n"
				+ "3. 과목 삭제\r\n"
				+ "4. 과목 확인\r\n"
				+ "5. 이전으로\r\n"
				+ "메뉴 선택 : "
				);
	}


	private void runSubjectMenu(int menu) {
		switch (menu) {
		case 1 : 
			subjectInsert();
			break;
		case 2 : 
			subjectUpdate();
			break;
		case 3 : 
			subjectDelete();
			break;
		case 4 : 
			subjectSearch();
			break;
		case 5 : 
			prev();
			break;
		default :
		}
	}


	private void subjectInsert() {
		
		Subject subject = inputSubject();
		if(subjectList.contains(subject)) {
			System.out.println("이미 등록된 과목입니다.");
			return;
		}
		subjectList.add(subject);
		System.out.println("과목을 추가했습니다.");
	}
	private Subject inputSubject() {
		System.out.print("과목명 입력 : ");
		scan.nextLine();
		String name = scan.nextLine();
		System.out.print("학년 입력 : ");
		int grade = scan.nextInt();
		System.out.print("학기 입력 : ");
		int semester = scan.nextInt();
		return new Subject(name, grade, semester);
	}
	private void subjectUpdate() {
		printBar();
		Subject subject = inputSubject();
		int index = subjectList.indexOf(subject);
		if(index < 0 ) {
			subject = null;
		}
		subject = subjectList.get(index);
		if(subject == null) {
			printBar();
			System.out.println("일치하는 과목이 없습니다.");
			return;
		}
		
	}
	private void subjectDelete() {
		printBar();
		Subject subject = inputSubject();
		if(subjectList.remove(subject)) {
			printBar();
			System.out.println("과목을 삭제했습니다.");
			return;
		}
		printBar();
		System.out.println("일치하는 과목이 없습니다.");
	}
	private void subjectSearch() {
		printBar();
		Subject subject = inputSubject();
		int index = subjectList.indexOf(subject);
		if(index < 0 ) {
			subject = null;
		} else {
			subject = subjectList.get(index);
		}
		if(subject == null) {
			printBar();
			System.out.println("일치하는 과목이 없습니다.");
			return;
		}
		System.out.println(subject);
		
	}
	private void exit() {
		// TODO Auto-generated method stub
		
	}
////////////////////////////////////////////////////////////////////////////////과목 관리>>/////	
	private void prev() {
		return;
	}
	
	private void printBar() {
		System.out.println("-------------------------");
	}
	/**
	 * 콘솔에서 정수를 입력받아 반환하는 메소드로 잘못입력하면(문자열) 정수의 가장 작은 수를 반환
	 * @return 입력한 정수 또는 정수의 가장 작은 수 
	 */
	public int nextInt() {
		try {
		return scan.nextInt();
		}catch(InputMismatchException e) {
			scan.nextLine(); //잘못 입력한 값을 입력 버퍼에서 비워줌
			return Integer.MIN_VALUE;//int의 가장 작은 수를 리턴
		}
	}

}
