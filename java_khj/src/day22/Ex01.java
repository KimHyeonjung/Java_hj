package day22;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Ex01 {

	public static void main(String[] args) {
		
		/* 콘솔에서 한 문장(영어)을 입력하고 한 문장에 몇 개의 단어로 구성되어 있는지 확인하는 
		 * 코드를 작성하세요.
		 */
		Scanner scan = new Scanner("Hi my name is hyenonjung goodbye");
		System.out.print("영어 문장 입력 : ");
		String str = scan.nextLine();
		String[] words = str.split(" ");;
		System.out.println("단어의 수는 : " + words.length);
		
		StringTokenizer st = new StringTokenizer(str, " ");
		int count = 0;
		while(st.hasMoreTokens()) {
			st.nextToken();
			count++;
		}
		System.out.println("단어의 수는 : " + count);
	}

}
