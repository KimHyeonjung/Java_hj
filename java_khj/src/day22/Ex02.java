package day22;

import java.util.Scanner;
import java.util.StringTokenizer;

public class Ex02 {

	public static void main(String[] args) {
		/* 콘솔에서 정수로 이루어진 문자열에서 각 정수들의 합을 구하는 코드를 작성하세요.
		 * 예
		 * 1 23 45 3 9 7 5
		 */
		Scanner scan = new Scanner(System.in);
		System.out.print("정수 입력 : ");
		String str = scan.nextLine();
		StringTokenizer st = new StringTokenizer(str, " ");
		int sum = 0;
		while(st.hasMoreTokens()) {
			String token = st.nextToken();
			sum += Integer.parseInt(token);
		}
		System.out.println("정수의 합은 : " + sum);
		
		//split을 이용
		int sum1 = sumStrToInt(str);
		System.out.println("정수의 합은 : " + sum1);
		
		
		
	}
	/* 위에서 작성한 코드 중 일부를 메소드로 만든다고 했을 때 필요하다고 생각하는 코드를 메소드로 만들어보세요.*/
	/**
	 * 주어진 문자열에 있는 정수들을 더해서 더한 결과를 반환하는 메소드
	 * @param nums
	 * @return 정수들의 합
	 */
	public static int sumStrToInt(String str) {
		int sum = 0;
		String [] nums = str.split(" ");
		for(String num : nums) {
			sum += Integer.parseInt(num);
		}
		return sum;
	}

}
