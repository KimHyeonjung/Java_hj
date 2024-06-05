package day07;

import java.util.Scanner;

public class Exam02 {

	public static void main(String[] args) {
		/* 최대 단어 10개를 저장할 수 있는 배열을 이용하여
		 * 원하는 단어를 입력하면 단어를 배열에 추가하는 코드를 작성하세요.
		 * 단어입력 (종료: -1) : cat
		 * 단어를 추가했습니다.
		 * 단어입력 (종료: -1) : dog 
		 * 단어를 추가했습니다.
		 * 단어입력 (종료: -1) : good 
		 * 단어를 추가하지 못했습니다.
		 * 단어입력 (종료: -1) : -1
		 * 종료합니다.
		 * */
		Scanner scan = new Scanner(System.in);
		String [] wordList = new String[2];
		String word;
		int wordCount = 0;
		while(true) {
			System.out.print("단어입력 (종료: -1) : ");
			word = scan.next();
			//▼ 입력된 단어 -1이면 종료합니다 문구 출력 후 반복문을 종료
			if(word.equals("-1")) {
				System.out.println("종료합니다.");
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
				
	}

}
