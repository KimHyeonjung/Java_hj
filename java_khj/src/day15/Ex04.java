package day15;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class Ex04 {

	public static void main(String[] args) {
		/* 1~45사이의 중복되지 않은 6개의 번호와 1개의 보너스 번호를 랜덤으로 생성하고,
		 * 사용자가 번호를 6개 입력해서 몇등인지 맞추는 로또 예제
		 * 1등 : 번호 6개가 일치
		 * 2등 : 번호 5개와 보너스 번호 일치
		 * 3등 : 번호 5개
		 * 4등 : 번호 4개
		 * 5등 : 번호 3개
		 * 나머진 꽝*/
		Scanner scan = new Scanner(System.in);
		int min = 1, max = 45;
		int ranNum;
		int bonusNum = 0;
		HashSet<Integer> lotto = new HashSet<Integer>();
//		ArrayList<Integer> myLotto = new ArrayList<Integer>();
		HashSet<Integer> myLotto = new HashSet<Integer>();
		int count = 0;
		
		while(lotto.size() <7) {
			ranNum = (int)(Math.random() * (max - min + 1) + min);
			/* 위와 동일한 랜덤한 수 생성 코드
			 * Random random = new Random();
			 * int ranNum = random.nextInt(min, max+1); 
			 * */
			if(lotto.size() == 6 ) {
				bonusNum = ranNum;
			}
			lotto.add(ranNum);
		}
		lotto.remove(bonusNum);
//		do {
//			bonusNum = (int)(Math.random() * (max - min + 1) + min);
//		} while(lotto.contains(bonusNum));
		
		System.out.println(lotto);
		System.out.println(bonusNum);
		System.out.print("1~45사이의 중복되지 않는 번호 입력 : ");
		for(int i = 0; i < 6; i++) {
			myLotto.add(scan.nextInt());
		}
//		for(int i = 1; i < 46; i++) {
//			if(lotto.contains(i) && myLotto.contains(i)) {
//				count++;
//			}
//		}
		
		for(Integer tmp : myLotto) {
			if(lotto.contains(tmp)) {
				count++;
			}
		}
		
/////////리스트 이용한거
//		for(int i = 0; i < 6; i++) {
//			myLotto.add(scan.nextInt());
//		}
//		for(int i = 0; i < 6; i++) {
//			if(lotto.contains(myLotto.get(i))) {
//				count++;
//			}
//		}
////////////		
		if(count == 6) {
			System.out.println("1등 입니다.");
		}
		else if(count == 5) {
			if(myLotto.contains(bonusNum)) {
				System.out.println("2등 입니다.");
			}else {
				System.out.println("3등 입니다.");
			}
		}
		else if(count == 4) {
			System.out.println("4등 입니다.");
		}
		else if(count == 3) {
			System.out.println("5등 입니다.");
		} else {
			System.out.println("꽝입니다.");
		}
		
		
	}

}
