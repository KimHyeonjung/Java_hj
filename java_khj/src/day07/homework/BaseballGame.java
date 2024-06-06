package day07.homework;

import java.util.Arrays;
import java.util.Scanner;

public class BaseballGame {

	public static void main(String[] args) {
		/* 숫자 야구 게임을 구현하세요.
		 * 규칙
		 * - 중복되지 않은 1~9사이의 랜덤한 수 3개를 생성
		 * - 안타 : 해당 숫자가 있고, 위치도 같은 경우
		 * - 볼 : 숫자가 있지만 위치가 다른 경우
		 * - 아웃 : 일치하는 숫자가 하나도 없는 경우
		 * 예시
		 * (3 9 7)
		 * 입력 : 1 2 3
		 * 1B
		 * 입력 : 5 6 7
		 * 1S
		 * 입력 : 4 5 6
		 * O
		 * 입력 : 3 7 9
		 * 1S2B
		 * 입력 : 3 9 7
		 * 3S
		 * */

		//중복되지 않은 랜덤한 수(1~9) 3개를 저장하는 배열과 입력 받은 수 3개를 저장하는 배열 선언, 생성
		int [] baseBallNum, iNum;		
		int arrLength = 3; //게임에 사용할 숫자 개수 (배열의 길이)
		int storedCount = 0, i;
		int random, num;
		int max = 9, min = 1;
		baseBallNum = new int[arrLength];
		iNum = new int[arrLength];
		Scanner scan = new Scanner(System.in);

		//중복되지 않는 랜덤한 수 3개 생성해 baseBallNum에 저장
		while (storedCount < arrLength) {
			random = (int)(Math.random() * (max - min +1) + min);
			//baseBallNum 배열에 새로 생성한 랜덤한 수가 존재하는지 체크
			for(i = 0; i < storedCount; i++) {
				if(random == baseBallNum[i]) {
					break;
				}
			}
			// 없으면 추가
			if(i == storedCount) {
				baseBallNum[storedCount++] = random;
			}
		}
		System.out.println(Arrays.toString(baseBallNum)); // 중복되지 않는 랜덤한 수 3개가 잘 들어갔는지 확인

		//숫자 3개 입력받아 iNum에 저장
		System.out.print("1~9까지의 숫자 3개를 중복되지 않게 입력(예 : 3 5 9) :  ");
		for(i = 0; i < arrLength; i++) {
			num = scan.nextInt();
			iNum[i] = num;
			storedCount++;
		}
		System.out.println(Arrays.toString(iNum)); // 중복되지 않는 랜덤한 수 3개가 잘 들어갔는지 확인
		/* - 스트라이크 : 해당 숫자가 있고, 위치도 같은 경우
		 * - 볼 : 숫자가 있지만 위치가 다른 경우
		 * - 아웃 : 일치하는 숫자가 하나도 없는 경우
		 */
		int strikeCount = 0, ballCount = 0;
		for(i = 0; i < arrLength ; i++) {
			//스트라이크 체크
			if(baseBallNum[i] == iNum[i]) {
				strikeCount++;
			}
			//볼 체크
			for(int j = 0; j < arrLength; j++) {
				if(i == j) {
					continue;
				}
				else if(baseBallNum[i] == iNum[j]) {
					ballCount++;
				}
			}
		}
		if (strikeCount > 0 && ballCount > 0) {
			System.out.print(strikeCount + "S" + ballCount + "B");
		}
		else if (strikeCount > 0 && ballCount == 0 ) {
			System.out.print(strikeCount + "S");
		}
		else if (ballCount > 0 && strikeCount == 0) {
			System.out.print(ballCount + "B");
		}
		else {
			System.out.print("O");
		}

	}

}
