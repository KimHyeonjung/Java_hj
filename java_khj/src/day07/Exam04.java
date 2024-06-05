package day07;

import java.util.Arrays;
import java.util.Scanner;

public class Exam04 {

	public static void main(String[] args) {
		/* Exam01~03기능을 하나로 합쳐서 다음 기능을 구현하시오.
		 *  메뉴
		 * 1. 추가
		 * 2. 검색
		 * 3. 종료
		 * 메뉴 선택 : 1
		 * -----------
		 * 단어 입력 (종료: -1) : abc
		 * 단어를 추가했습니다. / 단어를 추가하지 못했습니다.
		 * 단어 입력 (종료: -1) : -1
		 * 메뉴로 돌아갑니다.
		 * -----------
		 * 메뉴 선택 : 2
		 * -----------
		 * 단어 입력 (종료: -1) : abc
		 * 있는 단어입니다. / 없는 단어입니다.
		 * 단어 입력 (종료: -1) : -1
		 * 메뉴로 돌아갑니다.
		 * -----------
		 * */
		Scanner scan = new Scanner(System.in);
		int menuNum;
		String [] wordList = new String[10];
		String word;
		int wordCount = 0;
		do {
			System.out.println("메뉴");
			System.out.println("1. 추가");
			System.out.println("2. 검색");
			System.out.println("3. 종료");
			System.out.println("메뉴 선택 : ");
			menuNum = scan.nextInt();
			switch (menuNum) {
			case 1 :
				System.out.println("----------------");
				while(true) {
					System.out.print("단어입력 (종료: -1) : ");
					word = scan.next();
					//▼ 입력된 단어 -1이면 종료합니다 문구 출력 후 반복문을 종료
					if(word.equals("-1")) {
						System.out.println("\n메뉴로 돌아갑니다.");
						break;
					}
					else {
						//▼ wordList가 꽉 찼으면 추가하지 못했다고 알림
						if(wordCount >= wordList.length) {
							System.out.println("단어를 추가하지 못했습니다.");
						}
						//▼ 아니면 추가하고 추가했다고 알림
						else {
							wordList[wordCount++] = word;
							System.out.println("단어를 추가했습니다.");
						}
					}
				}
				System.out.println("----------------");
				System.out.println(Arrays.toString(wordList)); //확인용
				break;
			case 2 :
				System.out.println("----------------");
				boolean isInc = false;
				while(true) {			
					System.out.print("단어 입력(종료: -1) : ");
					word = scan.next();
					if(word.equals("-1")) {
						System.out.println("\n메뉴로 돌아갑니다.");
						break;
					}
					for(int i = 0; i < wordCount; i++) {
						if(word.equals(wordList[i])) {
							isInc = true;
							break;
						}
						else {
							isInc = false;
						}
					}
					if(isInc) {
						System.out.println("있는 단어입니다.");
					}
					else if(!isInc) {
						System.out.println("없는 단어입니다.");
					}
				}
				System.out.println("----------------");
				break;
			case 3 :
				System.out.println("----------------");
				System.out.println("프로그램을 종료합니다.");
				System.out.println("----------------");
				break;
			default :
				System.out.println("----------------");
				System.out.println("잘못된 메뉴입니다.");
				System.out.println("----------------");
				break;
			}
		} while(menuNum != 3);
	
	}
}
