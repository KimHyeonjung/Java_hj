package db.student.main;

import java.util.InputMismatchException;
import java.util.Scanner;

import db.student.controller.StudentController;
import db.student.controller.SubjectController;
import program.Program;

public class StudentManager implements Program{
	
	private Scanner scan = new Scanner(System.in);
	
	private StudentController studentController = new StudentController(scan); 
	private SubjectController subjectController = new SubjectController(scan); 
//////////////////////////////////////////////////////////////////////////////////////상위 메뉴
	@Override
	public void printMenu() {
		printBar();
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
			//defaultPrint();
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
//			studentInsert();
			studentController.insertStudent();
			break;
		case 2 : 
			studentUpdate();			
			break;
		case 3 : 
//			studentDelete();
			studentController.deleteStudent();
			break;
		case 4 : 
//			studentSearch();
			studentController.selectStudent();
			break;
		case 5 : 
//			prev();
			break;
		default:
//			defaultPrint();
		}
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
		switch (menu) {
		case 1 : 
			studentController.updateStudent();
			break;
		case 2 :
//			insertSubjectScore();
			break;
		case 3:
//			updateSubjectScore();
			break;
		case 4 : 
//			deleteSubjectScore();
			break;
		case 5 :
			prev();
			break;
		default :
			defaultPrint();
		}
	}
	
	private void subject() {
		int menu;
		do {
			printSubjectMenu();
			menu = nextInt();
			runSubjectMenu(menu);
		}while(menu != 5);
	}


	private void printSubjectMenu() {
		printBar();
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
			subjectController.insertSubject();
			break;
		case 2 : 
			subjectController.updateSubject();
			break;
		case 3 : 
			subjectController.deleteSubject();
			break;
		case 4 : 
//			subjectSearch();
			subjectController.searchSubject();			
			break;
		case 5 : 
			prev();
			break;
		default :
			defaultPrint();
		}
	}

	
	
	/*
	private void studentInfoUpdate() {
		printBar();
		//수정하려는 학년, 반, 번호를 입력
		System.out.println("[수정할 학생 정보 입력]");
		//입력한 정보를 이용해서 학생 객체를 생성=> indexOf 또는 contains등을 이용해서 객체를 쉽게 비교하기 위해서
		StudentVO std = inputStdInfo();
		//생성한 학생객체를 이용해서 리스트에 몇번지에 있는지 번지를 가져옴
		int index = list.indexOf(std);
		//번지가 유효하지 않은 번지이면 -> 번지가 0보다 작으면 알림문구 출력 후 종료
		if(index < 0) {
			System.out.println("입력과 일치하는 학생이 없습니다.");
			return;
		} 
		//유효한 번지이면 수정할 학년, 반, 번호, 이름을 입력
		//위에서 입력한 학년, 반, 번호, 이름으로 객체를 생성
			System.out.print("수정 할 ");
			StudentVO newStd = inputStdExpand();
		//수정할 객체를 리스트에서 번지로 객체를 삭제해서 가져옴 => 번지를 이용해서 삭제하면 삭제된 객체를 반환
		std = list.remove(index);
		//생성한 객체가 리스트에 있는지 확인해서 있으면 알림문구 출력 후 종료
		if(list.contains(newStd)) {
			System.out.println("이미 등록된 학생정보로 수정할 수 없습니다.");
			list.add(std); //수정이 취소됬으므로 삭제했던 학생정보를 다시 추가해줌
			return;
		}
		//삭제된 객체의 update 메소드를 이용해서 학년, 반, 번호, 이름을 수정
		//update메소드는 Student 클래스에서 새로 추가해야 함
		std.update(newStd);
		list.add(std);
		printBar();
		System.out.println("학생 정보가 수정되었습니다.");
		System.out.println(list); //테스트용
	}

	private void insertSubjectScore() {
		printBar();
		//등록된 과목이 없으면 알림문구 출력 후 종료
		if(subjectList.size() == 0) {
			System.out.println("등록된 과목이 없어서 추가할 수 없습니다. 과목을 등록해주세요.");
			return;
		}
		//학생 정보 입력(학년, 반, 번호)를 입력해서 학생 객체를 생성
		System.out.println("[학생 정보 입력]");
		StudentVO std = inputStdInfo();
		//리스트에 입력한 학생 객체가 몇번지에 있는지 번지를 가져옴
		int index = list.indexOf(std);
		//번지가 유효하지 않으면 안내문구 출력 후 종료
		if(index < 0) {
			System.out.println("일치하는 학생이 없습니다.");
			return;
		}
		//리스트에서 번지에 있는 학생 정보를 가져옴
		std = list.get(index);
		System.out.println(std); //테스트용////////////
		//등록된 과목 리스트를 출력
		subjectSearch();
		//학년, 학기, 과목명, 중간, 기말, 수행평가를 입력한 후 과목 객체를 생성
		System.out.println("[성적 입력]");
		SubjectVO subject = inputScore();
		//입력한 과목이 과목 리스트에 없으면 안내문구 출력 후 종료
		if(!subjectList.contains(subject.getSubName())) {
			System.out.println("등록되지 않은 과목이여서 성적을 추가할 수 없습니다.");
			return;
		}
		
		//학생의 과목 리스트를 가져옴
		List<SubjectVO> stdSubList = std.getSubjectList();
		//학생의 과목 리스트에 생성한 과목 객체가 있으면 안내문구 출력 후 종료
		if(stdSubList.contains(subject)) {
			printBar();
			System.out.println("입력한 성적이 있습니다.");
			return;
		}
		//없으면 학생의 과목 리스트에 추가
		stdSubList.add(subject);
		printBar();
		System.out.println("성적이 등록 되었습니다.");
	}
	private SubjectVO inputScore() {
		SubjectVO subject = inputRequiredSubject();
		System.out.print("중간 : ");
		int midterm = scan.nextInt();
		System.out.print("기말 : ");
		int finals = scan.nextInt();
		System.out.print("수행평가 : ");
		int performance = scan.nextInt();
		subject.update(midterm, finals, performance);		
		return subject;
	}
	private void updateSubjectScore() {
		printBar();
		//학생 정보를 입력하여 객체를 생성
		StudentVO std = inputStdInfo();
		//학생 리스트에서 학생객체가 몇번지에 있는지 번지를 가져옴
		int index = list.indexOf(std);
		//번지가 유효하지 않으면 알림문구 출력 후 종료
		if(index < 0) {
			printBar();
			System.out.println("일치하는 학생이 없습니다.");
			return;
		}
		//번지에 있는 학생 객체를 가져옴
		std = list.get(index);
		//학생의 과목 리스트를 가져옴
		List<SubjectVO> tmpList = std.getSubjectList();
		//수정할 과목, 학년, 학기 정보를 입력
		SubjectVO subject = inputRequiredSubject();
		//과목이 과목리스트에 없으면 안내문구 출력 후 종료
		if(!subjectList.contains(subject.getSubName())) {
			printBar();
			System.out.println("등록되지 않은 과목입니다. 수정 불가");
			return;
		}		
		
		//과목 객체가 학생 성적 리스트에 없으면 안내문구 출력 후 종료
		if(!tmpList.contains(subject)) {
			printBar();
			System.out.println("일치하는 성적이 없음");
			return;
		}
		//중간, 기말, 수행평가를 입력
		printBar();
		System.out.print("수정할 점수 입력 : ");
		System.out.print("중간 : ");
		int midterm = scan.nextInt();
		System.out.print("기말 : ");
		int finals = scan.nextInt();
		System.out.print("수행평가 : ");
		int performance = scan.nextInt();
		//과목 객체의 성적을 수정
		subject.update(midterm, finals, performance);
		//제거하고 추가하는 이유는 이렇게 하지 않으면 subject에서 해당 과목이 몇번지에 있는지 확인해서 해당 과목 정보를 가져오고
		//성적을 수정해야 하는데 번거롭기 때문에 아래와 같이 작성
		//리스트에서 과목객체를 제거
		tmpList.remove(subject);
		//리스트에서 과목 객체를 추가
		tmpList.add(subject);	
		printBar();
		System.out.println("성적을 수정하였습니다.");
	}
	private void deleteSubjectScore() {
		printBar();
		//학생 정보를 입력하여 객체를 생성
		StudentVO std = inputStdInfo();
		//학생 리스트에서 학생객체가 몇번지에 있는지 번지를 가져옴
		int index = list.indexOf(std);
		//번지가 유효하지 않으면 알림문구 출력 후 종료
		if(index < 0) {
			printBar();
			System.out.println("일치하는 학생이 없습니다.");
			return;
		}
		//번지에 있는 학생 객체를 가져옴
		std = list.get(index);
		//학생의 과목 리스트를 가져옴
		List<SubjectVO> tmpList = std.getSubjectList();
		//삭제할 과목, 학년, 학기 정보를 입력
		SubjectVO subject = inputRequiredSubject();
		//과목이 과목리스트에 없으면 안내문구 출력 후 종료
		if(!subjectList.contains(subject.getSubName())) {
			printBar();
			System.out.println("등록되지 않은 과목 성적입니다. 삭제 불가");
			return;
		}
		
		//학생 과목 리스트에서 과목 객체를 삭제하여 성공하면 안내문구 출력 후 종료
		if(tmpList.remove(subject)) {
			printBar();
			System.out.println(subject.getGrade()+"학년 "+subject.getSemester()+"학기 "
								+subject.getSubName()+" 성적을 삭제했습니다.");
			return;
		}
		//실패하면 안내문구 출력 후 종료
		printBar();
		System.out.println("미등록. 삭제불가");
	}
	
	public SubjectVO inputRequiredSubject() {
		System.out.print("과목 : ");
		scan.nextLine();
		String subName = scan.nextLine();
		System.out.print("학년 : ");
		int grade = scan.nextInt();
		System.out.print("학기 : ");
		int semester = scan.nextInt();
		return new SubjectVO(subName, grade, semester, 0, 0, 0);
	}
	
	
	
	
	private void studentSearch() {
		
		//학년 반 번호를 입력 후 객체를 생성
		StudentVO std = inputStdInfo();
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
		printBar();
		std.print();
	}

////////////////////////////////////////////////////////////////////////////////학생 관리>>/////
/////<<과목 관리///////////////////////////////////////////////////////////////////////////////
	

	
	
	private void subjectUpdate() {
		printBar();
		//수정할 과목을 입력
		System.out.print("수정할 기존 과목명 입력 : ");
		scan.nextLine();
		String subject = scan.nextLine();
		int index = subjectList.indexOf(subject);
		if(index < 0 ) {
			printBar();
			System.out.println("일치하는 과목이 없습니다.");
			return;
		}
		//새 과목명 입력
		printBar();
		System.out.print("수정할 새로운 과목명 입력 : ");
		subject = scan.nextLine();
		//새 과목명이 이미 등록되어 있으면 안내문구 출력 후 종료
		if(subjectList.contains(subject)) {
			printBar();
			System.out.println("이미 등록된 과목으로 수정 할 수 없습니다.");
			return;
		} else {
		//아니면 수정할 과목명 삭제
			subjectList.remove(index);
		}
		//새 과목명 추가
		subjectList.add(subject);
		System.out.println("과목명이 수정되었습니다.");
		System.out.println(subjectList);//테스트용/////////////////////
		
	}
	private void subjectDelete() {
		printBar();
		//삭제할 과목명을 입력
		System.out.print("삭제할 과목명 입력 : ");
		scan.nextLine();
		String subject = scan.nextLine();
		//리스트에서 과목을 삭제해서 성공하면 알림문구 출력 후 종료
		if(subjectList.remove(subject)) {
			printBar();
			System.out.println("과목을 삭제했습니다.");
			return;
		} else {
		//실패하면 알림문구 출력
		printBar();
		System.out.println("일치하는 과목이 없습니다.");
		}
	}
	private void subjectSearch() {
		printBar();
		System.out.println("[과목 목록]");
		for(String subject : subjectList) {
			System.out.println(subject);
		}
	}
	
////////////////////////////////////////////////////////////////////////////////과목 관리>>/////	
	
*/
	private void printBar() {
		System.out.println("-------------------------");
	}
	
	public int nextInt() {
		try {
		return scan.nextInt();
		}catch(InputMismatchException e) {
			scan.nextLine(); //잘못 입력한 값을 입력 버퍼에서 비워줌
			return Integer.MIN_VALUE;//int의 가장 작은 수를 리턴
		}
	}
	private void defaultPrint() {
		printBar();
		System.out.println("올바른 메뉴를 선택하세요.");
	}

	private void prev() {
		printBar();
		System.out.println("이전으로 돌아갑니다.");
		return;
	}
	private void exit() {
		printBar();
		System.out.println("프로그램을 종료합니다.");
	}
}
