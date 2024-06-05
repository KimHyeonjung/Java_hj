package day07.homework;

import java.util.Scanner;

public class BaseballGame {

	public static void main(String[] args) {
		/* 숫자 야구 게임을 구현하세요.
		 * 규칙
		 * - 중복되지 않은 1~9사이의 랜덤한 수를 생성
		 * - 스트라이크 : 해당 숫자가 있고, 위치도 같은 경우
		 * - 볼 : 숫자가 있지만 위치가 다른 경우
		 * - 아웃 : 일치하는 숫자가 하나도 없는 경우
		 * 예시
		 * (3 9 7)
		 * 입력 : 1 2 3
		 * 1B
		 * 입력 : 5 6 7
		 * 1S
		 * 입력 : 4 5 6
		 * Out
		 * 입력 : 3 7 9
		 * 1S2B
		 * 입력 : 3 9 7
		 * HomeRun
		 * */
		
		//중복되지 않은 랜덤한 수(1~9) 3개를 저장하는 배열과 입력 받은 수 3개를 저장하는 배열 선언, 생성
		int [] baseBallNum, iNum;		
		int arrLength = 3;
		int saveCount = 0, i;
		int random;
		int max = 9, min = 1;
		baseBallNum = new int[arrLength];
		iNum = new int[arrLength];
		Scanner scan = new Scanner(System.in);
		//중복되지 않는 랜덤한 수 3개 저장
		while (saveCount < arrLength) {
			random = (int)(Math.random() * (max - min +1) + min);
			//baseBallNum 배열에 새로 생성한 랜덤한 수가 존재하는지 체크
			for(i = 0; i < saveCount; i++) {
				if(random == baseBallNum[i]) {
					break;
				}
			}
			// 없으면 추가
			if(i == saveCount) {
				baseBallNum[saveCount++] = random;
			}
		}
	}

}
