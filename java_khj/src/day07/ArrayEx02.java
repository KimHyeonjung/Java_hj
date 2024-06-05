package day07;

import java.util.Arrays;
import java.util.Scanner;

public class ArrayEx02 {

	public static void main(String[] args) {
		/* 1~9사이의 랜덤한 수 3개를 배열에 저장하는데,
		 * 중복되지 않게 저장하는 코드를 작성해보세요.
		 * */
		
		Scanner scan = new Scanner(System.in);
		int max = 9, min = 1;
		int rnList [] = new int[9];
		int random;
		int sCount= 0;
		int i;
		/* 반복횟수 : 저장된 숫자가 3개가 아닐 때 반복 => 저장된 숫자가 3보다 작을때
		 * 규칙성 : 랜덤한 수를 생성해서 중복되지 않으면 저장
		 * 반복문 종료 후 : 배열에 저장된 숫자 출력
		 * */
		while(sCount < rnList.length) {
			//랜덤한 수를 생성하여 변수에 저장
			random = (int)(Math.random() * (max - min +1) + min);
			
			//배열에 랜덤한 수가 있는지 확인
			/* 반복횟수 : i는 0부터 저장된 개수보다 작을 때까지 1씩 증가
			 * 규칙성 : i번지에 있는 값과 랜덤한 수가 같으면 반복문을 종료
			 * 반복문 종료 후 : 없음
			 * */
			for(i = 0; i < sCount; i++) {
				if(random == rnList[i]) {
					break; //break를 만나면 i는 count보다 작을수밖에 없음
				}
			}
			//없으면 배열에 추가한 후 저장된 개수를 1증가
			if(sCount == i) {
				rnList[sCount++] = random;
			}
		}
		System.out.println(Arrays.toString(rnList));
	}

}
