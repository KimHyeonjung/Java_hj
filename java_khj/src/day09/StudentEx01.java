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
		int menuNum = 0;
		int stdCount = 0; //저장된 학생 수
		boolean isNot = false; 
		Student [] stdList = new Student[100];
		do {
			printMenu();
			menuNum = scan.nextInt();
			//			runMenu(menuNum);
			switch (menuNum) {
			case 1 :
				if(stdCount == stdList.length) {
					System.out.println("다 찼습니다.");
					break;
				}
				System.out.println("등록할 학생 정보를 입력하세요.(ex 학년 반 번호 이름) : ");
				int grade = scan.nextInt();
				int classNum = scan.nextInt();
				int num = scan.nextInt();
				String name = scan.next();

				stdList[stdCount] = new Student(grade, classNum, num, name);
				//배열이 꽉 차지 않으면 생성한 학생 객체를 배열에 저장

				//					stdList[stdCount].printStdInfo();
				stdCount++;
				for(Student tmp : stdList) {
					if(tmp != null) {
						tmp.printStdInfo();
					}
				}
				break;
			case 2 :
				//학년, 반, 번호를 입력
				System.out.println("학생 정보를 입력하세요.(ex 학년 반 번호) : ");
				grade = scan.nextInt();
				classNum = scan.nextInt();
				num = scan.nextInt();
				//입력받은 학년, 반, 번호를 이용하여 일치하는 학생이 있는지 확인
				for(int i = 0; i < stdCount; i++) {
					//없다면 일치하는 학생이 없다고 출력하고 종료
					if(stdList[i].getGrade() != grade || stdList[i].getClassNum() != classNum || stdList[i].getNum() != num) {
						isNot = true;
					}
					//있다면 국어, 영어, 수학 성적을 입력받아 학생 성적을 수정
					if(stdList[i].getGrade() == grade && stdList[i].getClassNum() == classNum && stdList[i].getNum() == num) {
						System.out.print(stdList[i].getName() + "의 국어, 영어, 수학 성적을 입력 : ");
						stdList[i].setKorScore(scan.nextInt());
						stdList[i].setEngScore(scan.nextInt());
						stdList[i].setMathScore(scan.nextInt());
						isNot = false;
						stdList[i].printStdInfo();
						break;
					}	
				}
				if(isNot) {
					System.out.println("일치하는 학생이 없음");
				}
				break;
			case 3 :
				//학년, 반, 번호를 입력
				System.out.println("학생 정보를 입력하세요.(ex 학년 반 번호) : ");
				grade = scan.nextInt();
				classNum = scan.nextInt();
				num = scan.nextInt();
				//입력받은 학년, 반, 번호를 이용하여 일치하는 학생이 있는지 확인
				for(int i = 0; i < stdCount; i++) {
					//없다면 일치하는 학생이 없다고 출력하고 종료
					if(stdList[i].getGrade() != grade || stdList[i].getClassNum() != classNum || stdList[i].getNum() != num) {
						isNot = true;
					}
					//있다면 국어, 영어, 수학 성적을 입력받아 학생 성적을 확인
					if(stdList[i].getGrade() == grade && stdList[i].getClassNum() == classNum && stdList[i].getNum() == num) {
//						stdList[i].getKorScore();
//						stdList[i].getEngScore();
//						stdList[i].getMathScore();
						isNot = false;
						stdList[i].printStdInfo();
						break;
					}	
				}
				if(isNot) {
					System.out.println("일치하는 학생이 없음");
				}
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
	public void setScore(int korScore, int engScore, int mathScore) {
		this.korScore = korScore;
		this.engScore = engScore;
		this.mathScore = mathScore;
	}

	public void printStdInfo() {
		System.out.println(grade + "학년 " + classNum + "반 " + num + "번 " + name + " 국어: " 
				+ korScore + " 영어: " + engScore + " 수학: " + mathScore);
	}

}
