package day04;

public class WhileEx09 {

	public static void main(String[] args) {
		/* a부터 z까지 출력하는 코드를 작성하세요.
		 * abcdefg...xyz
		 * 반복횟수 : i는 0부터 25까지 1씩 증가
		 * 규칙성 : 'a' + i를 문자로 출력
		 * */
				int i = 0;
		while(i <= 25) {			
			System.out.print((char)('a' + i)+" ");
			i++;
		}	
		
		System.out.println();
				/* a부터 z까지 출력하는 코드를 작성하세요.
		 * abcdefg...xyz
		 * 반복횟수 :ch는 a부터 z까지 1씩 증가
		 * 규칙성 : ch를 출력
		 * */
		char ch = 'a';
		while(ch <= 'z') {
			System.out.print(ch + " ");
			ch++;
		}	
		
	}

}
