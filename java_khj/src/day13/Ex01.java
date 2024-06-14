package day13;

import java.util.Scanner;

public class Ex01 {

	public static void main(String[] args) {
		/* 다음과 같은 책 제목들이 있을 때 원하는 단어를 검색해서 해당 단어가 있는 책을 출력하는 코드를 작성하세요.
		 * 일치하는 책이 없는 경우 일치하는 책이 없습니다라고 출력.*/
		String [] books = {"자바의 정석", "혼자하는 자바", "혼자하는 C", "수학의 정석", "누구나 하는 C"};
		String searchWord;
		Scanner scan = new Scanner(System.in);
		int bookCount = 0; //
		System.out.print("책 제목 검색 : ");
		searchWord = scan.next();
		for(int i = 0; i < books.length; i++) {
			if(books[i].contains(searchWord)) {
				System.out.println(books[i]);
				bookCount++;
			}
		}
		if(bookCount == 0) {
			System.out.println("일치하는 책이 없습니다.");
		}
	
	}

}
