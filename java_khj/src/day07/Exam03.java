package day07;

import java.util.Scanner;

public class Exam03 {

	public static void main(String[] args) {
		/* 입력한 문자열이 배열에 있는지 없는지 확인하는 코드를 작성하세요.
		 * 단어 입력 (종료: -1) : apple
		 * 없는 단어입니다.
		 * 단어 입력 (종료: -1) : cat
		 * 있는 단어입니다.
		 * 단어 입력 (종료: -1) : -1
		 * 종료합니다.
		 * */
		String [] list = new String[] {"cat", "dog", "banana"};
		Scanner scan = new Scanner(System.in);
		String word;
		boolean isInc = false;
		while(true) {			
			System.out.print("단어 입력(종료: -1) : ");
			word = scan.next();
			if(word.equals("-1")) {
				System.out.println("종료합니다.");
				break;
			}
			for(String tmp : list) {
				if(word.equals(tmp)) {
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
	}

}
