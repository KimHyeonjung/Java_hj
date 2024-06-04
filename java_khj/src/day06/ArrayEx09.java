package day06;

import java.util.Arrays;
import java.util.Scanner;

public class ArrayEx09 {

	public static void main(String[] args) {
		String [] list = {"dog", "cat", "java", "cup", "computer"};
		Scanner scan = new Scanner(System.in);
		String str;
		boolean include = false;
		//문자열을 입력받아 입력받은 문자열이 있는지 없는지 알려주는 코드를 작성하세요.
		System.out.println(Arrays.toString(list));
		System.out.print("문자열 입력 : ");
		str = scan.next();
		//list 전체를 탐색하여 입력한 문자열과 같은 문자열이 있는지 확인
		for(String tmp : list) {
			if(tmp.equals(str)) {
				include = true; // 같은 문자열이 있는지 확인하는 boolean형 변수의 값을 true로 변경
				break;
			}
		}
		if(include) {
			System.out.println(str + "은 있습니다.");
		}
		else if(!include) {
			System.out.println(str + "은 없습니다.");
		}
	}

}
