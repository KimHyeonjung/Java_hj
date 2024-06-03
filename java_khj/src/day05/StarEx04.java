package day05;

public class StarEx04 {

	public static void main(String[] args) {
		/* *을 하나씩 출력해서 다음과 같이 출력되도록 중첩 반복문을 이용하여 작성하세요.
		 *     *	 	 i=1,	공백=4,	*=1,	추가*=0	1
		 *    *** 		 i=2, 	공백=3,	*=2,	추가*=1	3
		 *   ***** 		 i=3,	공백=2,	*=3,	추가*=2	5
		 *  ******* 	 i=4,	공백=1,	*=4,	추가*=3	7
		 * *********	 i=5,	공백=0,	*=5,	추가*=4	9
		 * 반복횟수 : i는 1부터 5까지 1씩 증가 
		 * 규칙성 : 공백을 lineNum-i개 출력하고 *을 1개 출력 후 엔터
		 * 		=> 반복횟수 : j는 5부터 1까지 1씩 감소
		 * 		   규칙성 : " "을 출력 
		 * 		   반복문 종료 후 : 없음
		 * 		=> 반복횟수 : k는 1부터 i*2-1까지 1씩 증가
		 * 		   규칙성 : *을 출력 
		 * 		   반복문 종료 후 : 없음
		 * 반복문 종료 후 : 없음	 
		 * */
		//5줄 반복
		int lineNum = 5;
		for(int i = 1 ; i <= lineNum ; i++) { 
			for(int j = 1 ; j <= (lineNum-i) ; j++) {
				System.out.print(" ");
			}
			for(int k = 1 ; k <= (i*2-1) ; k++) {
				System.out.print("*");
			}
			System.out.println(); //System.out.print("\n");  둘 다 엔터 수행
		}
		
		/* 반복횟수 : i는 1부터 5까지 1씩 증가 
		 * 규칙성 : 공백을 lineNum-i개 출력하고 *을 1개 출력 후 엔터
		 * 		=> 반복횟수 : j는 5부터 1까지 1씩 감소
		 * 		   규칙성 : " "을 출력 
		 * 		   반복문 종료 후 : 엔터를 출력
		 * 		=> 반복횟수 : j는 1부터 i까지 1씩 증가
		 * 		   규칙성 : *을 출력 
		 * 		   반복문 종료 후 : 엔터를 출력
		 * 		=> 반복횟수 : j는 1부터 i-1까지 1씩 증가
		 * 		   규칙성 : *을 출력 
		 * 		   반복문 종료 후 : 엔터를 출력
		 * 반복문 종료 후 : 없음	 
		 * */
		//5줄 반복
		lineNum = 5;
		for(int i = 1 ; i <= lineNum ; i++) { 
			for(int j = 1 ; j <= (lineNum-i) ; j++) {
				System.out.print(" ");
			}
			for(int j = 1 ; j <= i ; j++) {
				System.out.print("*");
			}
			for(int j = 1 ; j <= i-1 ; j++) {
				System.out.print("*");
			}
			System.out.println(); //System.out.print("\n");  둘 다 엔터 수행
		}
		
	}

}
