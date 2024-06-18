package day15;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Ex05Me {
	// 내가 작성한 코드
	public static void main(String[] args) {
		/* 숫자 야구 게임을 구현하세요.  
		 * S : 숫자가 있고 위치가 같은 경우
		 * B : 숫자가 있고 위치가 다른 경우
		 * O : 일치하는 숫자가 하나도 없는 경우*/
		int min = 1, max = 10;
		int ranNum, userNum;
		int strikeCount = 0;
		int ballCount = 0;
		Scanner scan = new Scanner(System.in);
		List<Integer> baseList = new ArrayList<Integer>();
		List<Integer> userList = new ArrayList<Integer>();
		Set<Integer> baseSet = new HashSet<Integer>();
		
		//중복되지 않은 1~9사이의 3개의 숫자를 생성
		while(baseSet.size() <3) {
			ranNum = (int)(Math.random() * (max - min + 1) + min);
			baseSet.add(ranNum);
		}
		baseList.addAll(baseSet);
		Collections.shuffle(baseList);
		System.out.println(baseList);
		//반복문
		System.out.print("1~9사이의 숫자 3개를 중복되지 않게 입력 :");
		while(userList.size() < 3) {
			userNum = scan.nextInt();
			if(userList.contains(userNum)) {
				continue;
			}
			userList.add(userNum);
		}
		for(int i = 0; i < baseList.size(); i++) {
			for(int j = 0; j < userList.size(); j++) {
				if(baseList.get(i) == userList.get(j)) {
					if(i == j) {
						strikeCount++;
					}else {
						ballCount++;
					}
				}
			}
		}
		if (strikeCount == 3) {
			System.out.println("정답입니다.");
		}			
		if (strikeCount != 0) {
			System.out.print(strikeCount + "S");
		}
		if (ballCount != 0) {
			System.out.print(ballCount + "B");
		}
		if (ballCount == 0 && strikeCount == 0) {
			System.out.print("O");
		}
		System.out.println();
			//사용자가 1~9사이의 숫자 3개를 중복되지 않게 입력
			
			
			//입력한 값과 랜덤 값을 이용하여 결과를 출력
			
	}
}
