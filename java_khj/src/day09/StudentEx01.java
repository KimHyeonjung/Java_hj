package day09;

import java.util.Scanner;

public class StudentEx01 {
	/* 학생 성적을 관리하기 위한 프로그램 예제 : 국어, 영어, 수학 */
	public static void main(String[] args) {
		/* 1. 다음 메뉴가 출력되도록 코드를 작성하세요.
		 * 메뉴
		 * 1. 학생 등록
		 * 2. 성적 수정
		 * 3. 성적 확인
		 * 4. 종료
		 * 메뉴 선택 : 
		 */
		/* 2. 메뉴를 출력하고 메뉴를 콘솔창에서 입력 받는 코드를 작성하세요. 
		 * 단, 입력받은 메뉴가 4가 아니면 반복하도록 작성하세요.
		 */
		/* 3. 입력한 메뉴가 1이면 학생 등록입니다. 2이면 성적 수정입니다. 3이면 성적확인입니다. 4이면 프로그램 종료입니다.
		 * 라고 출력하도록 코드를 작성*/
		/* 4. 프로그램 관리를 위한 Student 클래스를 추가하세요.
		 * - 학년, 반, 번호, 이름, 국어, 영어, 수학
		 * - 멤버변수는 private으로 하고 getter / setter를 추가
		 * - 기본 생성자와 학년, 반, 번호, 이름을 이용한 생성자를 추가
		 * - 국어, 영어, 수학 성적을 변경하는 기능을 추가
		 */
		/* 5. 학생 등록 기능을 구현하세요.
		 * 학년, 반, 번호, 이름을 입력받아 학생을 추가하세요.
		 * */
		Scanner scan = new Scanner(System.in);
		int korScore, engScore, mathScore;
		int menuNum = 0;
		int stdCount = 0; //저장된 학생 수
		int index;
		Student [] stdList = new Student[3];
		Student tmpStd;
		do {
			printMenu();
			menuNum = scan.nextInt();
			//			runMenu(menuNum);
			switch (menuNum) {
			case 1 :
				stdCount = insertStudent(stdList, scan, stdCount);
				break;
			case 2 :
				updateStudent(stdList, stdCount, scan);
				break;
			case 3 :
				printStudent(stdList, stdCount, scan);
				break;
			case 4 :
				System.out.println("프로그램 종료.");
				break;
			default :
				System.out.println("잘못된 메뉴입니다.");
				break;
			}
		} while(menuNum != 4);


	}
	/*기능 : 메뉴를 출력하는 메소드
	 * */
	public static void printMenu() {
		System.out.println("메뉴");
		System.out.println("1. 학생 등록");
		System.out.println("2. 성적 수정");
		System.out.println("3. 성적 확인");
		System.out.println("4. 종료");
		System.out.print("메뉴 선택 : ");
	}

	/**기능 : 메뉴에 맞는 기능을 실행하는 메소드 
	 * @param runMenu 실행할 메뉴 
	 * */	
	public static void runMenu(int menuNum) {
		switch (menuNum) {
		case 1 :
			System.out.println("학생 등록입니다.");
			break;
		case 2 :
			System.out.println("성적 수정입니다.");
			break;
		case 3 :
			System.out.println("성적 확인입니다.");
			break;
		case 4 :
			System.out.println("프로그램 종료.");
			break;
		default :
			System.out.println("잘못된 메뉴입니다.");
			break;
		}
	}


	/**기능 : 학생 배열과 검색할 학생 정보가 주어지면 몇 번지에 있는지 알려주는 메서드 
	 * @param list 학생 배열
	 * @param std 검색할 학생 정보
	 * @return 검색한 학생 정보의 위치(번지)로 없으면 -1을 반환
	 */
	public static int indexOf(Student[] stdList, int stdCount, Student std) {
		if(stdList == null || std == null) {
			return -1;
		}
		for(int i = 0; i < stdCount; i++) {
			//학년이 다르면
			if(std.getGrade() != stdList[i].getGrade()) {
				continue;
			}
			//반이 다르면
			if(std.getClassNum() != stdList[i].getClassNum()) {
				continue;
			}
			//번호가 다르면
			if(std.getNum() != stdList[i].getNum()) {
				continue;
			}
			//학년이 같고 반이 같고 번호가 같은 경우
			return i;
		}
		return -1;
	}
	/**기능 : 학생 정보를 입력받아 학생 객체로 알려주는 메소드
	 * @param scan 콘솔에서 입력받기 위한 Scanner
	 * @return 입력받은 학생 정보를 이용하여 생성한 학생 객체
	 */
	public static Student inputStudent(Scanner scan) {
		System.out.print("학년 : ");
		int grade = scan.nextInt();
		System.out.print("반 : ");
		int classNum = scan.nextInt();
		System.out.print("번호 : ");
		int num = scan.nextInt();
		return new Student(grade, classNum, num, "");
	}
	
	/**기능 : 학생 정보를 입력받아 학생리스트에 추가하고 등록된 학생 숫자를 알려주는 메소드
	 * @param stdList 학생 리스트
	 * @param scan 학생 정보를 입력받기 위한 Scanner
	 * @param stdCount 현재 학생 숫자
	 * @return 등록된 학생 숫자
	 */
	public static int insertStudent(Student [] stdList, Scanner scan, int stdCount) {
		if(stdCount == stdList.length) {
			System.out.println("다 찼습니다.");
			return stdCount;
		}
		Student tmpStd = inputStudent(scan);
		System.out.print("이름 : ");
		String name = scan.next();
		tmpStd.setName(name);
		int index = indexOf(stdList, stdCount, tmpStd);
		if(index != -1) {
			System.out.println("이미 등록된 학생입니다.");
			return stdCount;
		}
		//배열이 꽉 차지 않으면 생성한 학생 객체를 배열에 저장
		stdList[stdCount] = tmpStd;
		//저장된 학생수를 1증가
		return stdCount + 1;
	}
	
	/**기능 : 학생 리스트에 있는 학생 정보에서 성적을 수정하는 메소드
	 * @param stdList 학생 리스트
	 * @param stdCount 등록된 학생 숫자
	 * @param scan 콘솔에서 입력받기 위한 Scanner
	 */
	public static void updateStudent(Student[] stdList, int stdCount, Scanner scan) {
		//학년, 반, 번호를 입력
		Student tmpStd = inputStudent(scan);
		//입력받은 학년, 반, 번호를 이용하여 일치하는 학생이 있는지 확인
		int index = indexOf(stdList, stdCount, tmpStd);
		//없다면 일치하는 학생이 없다고 출력하고 종료
		if(index == -1) {
			System.out.println("일치하는 학생이 없음");
			return;
		}
		System.out.print("국어 : ");
		int korScore = scan.nextInt();
		System.out.print("영어 : ");
		int engScore = scan.nextInt();
		System.out.print("수학 : ");
		int mathScore = scan.nextInt();

		stdList[index].updateScore(korScore, engScore, mathScore);

	}
	/** 기능 : 학생 리스트에서 학생 정보를 검색해서 출력하는 메소드
	 * @param stdList 학생 리스트
	 * @param stdCount 등록된 학생 수
	 * @param scan 콘솔에서 입력받기 위한 scanner
	 */
	public static void printStudent(Student[] stdList, int stdCount, Scanner scan) {
		//학년, 반, 번호를 입력
		Student tmpStd = inputStudent(scan);
		//입력받은 학년, 반, 번호를 이용하여 일치하는 학생이 있는지 확인
		int index = indexOf(stdList, stdCount, tmpStd);
		//없다면 일치하는 학생이 없다고 출력하고 종료
		if(index == -1) {
			System.out.println("일치하는 학생이 없음");
			return;
		}
		//있다면 해당 학생의 성적을 출력
		stdList[index].printStdInfo();
	}
	
}

class Student{
	private int grade, classNum, num;
	private int korScore, engScore, mathScore;
	private String name;

	//getters와 setters
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	public int getClassNum() {
		return classNum;
	}
	public void setClassNum(int classNum) {
		this.classNum = classNum;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getKorScore() {
		return korScore;
	}
	public void setKorScore(int korScore) {
		this.korScore = korScore;
	}
	public int getEngScore() {
		return engScore;
	}
	public void setEngScore(int engScore) {
		this.engScore = engScore;
	}
	public int getMathScore() {
		return mathScore;
	}
	public void setMathScore(int mathScore) {
		this.mathScore = mathScore;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Student() {}
	public Student(int grade, int classNum, int num, String name) {
		this.grade = grade;
		this.classNum = classNum;
		this.num = num;
		this.name = name;
	}
	/** 기능 : 국어, 영어, 수학 성적이 주어지면 변경하는 메소드
	 * @param korScore 국어 성적
	 * @param engScore 영어 성적
	 * @param mathScore 수학 성적
	 * */
	public void updateScore(int korScore, int engScore, int mathScore) {
		this.korScore = korScore;
		this.engScore = engScore;
		this.mathScore = mathScore;
	}

	public void printStdInfo() {
		System.out.println(grade + "학년 " + classNum + "반 " + num + "번 " + name);
		System.out.println("국어:" + korScore + "점  영어:" + engScore + "점  수학:" + mathScore + "점");
	}

}
