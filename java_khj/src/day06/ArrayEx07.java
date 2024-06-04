package day06;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class ArrayEx07 {

	public static void main(String[] args) {
		/* UpDown 게임에 다음 기능을 추가하세요. 
		 * 메뉴
		 * 1. 플레이
		 * 2. 기록확인
		 * 3. 종료
		 * 진행예시
		 * 메뉴 선택 : 1 (랜덤이 35라고 가정)
		 * 정수 입력 : 50
		 * Down!
		 * 정수 입력 : 30
		 * Up!
		 * 정수 입력 : 35
		 * 정답입니다.
		 * 3번만에 맞추셨습니다.
		 * 기록이 등록됩니다.
		 * 메뉴 선택 : 2
		 * 기록확인
		 * 3회
		 * 메뉴 선택 : 3
		 * 종료됩니다.
		 *  
		 * */
		Scanner scan = new Scanner(System.in);
		int randomNum;
		int menuNum, num;
		int max = 100, min = 1;
		int count; // 몇 번만에 맞췄는지에 대한 횟수 
		int record[] = new int[5]; //기록을 저장할 배열
		int recordCount = 0; // 기록이 저장된 횟수
		// 선택한 메뉴가 3이 아닐때 실행되는 반복문
		// 메뉴 출력
		do {
			// 메뉴 입력
			System.out.println(" ▼메뉴 ");
			System.out.println("1. 플레이");
			System.out.println("2. 기록확인");
			System.out.println("3. 종료");
			//메뉴 번호 입력 받기
			System.out.print("메뉴를 선택 : ");
			menuNum = scan.nextInt(); 
			if(menuNum == 1) { // 메뉴선택 1
				count = 0;
				randomNum = (int)(Math.random() * (max - min + 1) + min); //랜덤한 수 생성
				System.out.println("< " + randomNum + " >");
				do {			
					System.out.print("정수 입력 : ");
					num = scan.nextInt();
					if(num > randomNum) {
						System.out.println("Down!");
						count++;
					}
					else if(num < randomNum) {
						System.out.println("Up!");
						count++;
					}
					
				} while(randomNum != num); //입력한 정수와 랜덤한 수가 같지 않을때까지 반복
				count++;
				System.out.println("정답입니다." + count + "번만에 맞췄습니다.");
				//기록의 개수가 5개 미만이면
				if(recordCount < record.length) {
					//기록을 등록
					//recordCount번지에 저정하고 recordCount를 1 증가
					record[recordCount++] = count;
//					recordCount++; 
				}
				else if(record[recordCount - 1] > count){ //아니면 내 기록이 5등보다 좋은지 비교해서 좋으면
					//기록을 등록
					//마지막 번지에 내 기록을 등록					
					record[recordCount - 1] = count;
				}
				//기록 정렬
				//Arrays.sort를 이용하여 0번지부터 recordCount번지전까지 정렬
				//0번지는 포함, recordCount번지는 포함을 안하고 앞 번지까지
				Arrays.sort(record, 0, recordCount);
				
			}
			else if(menuNum == 2) { // 메뉴선택 2
				//record에 있는 기록들을 recordCount만큼 순서대로 출력
				/* 반복횟수 : i는 0부터 recordCount보다 작을 때까지 1씩 증가
				 * 규칙성 : i+1, 횟수를 출력
				 * 반복문 종료 후 : 없음
				 * */
				if(recordCount == 0) {
					System.out.println("등록된 기록이 없습니다.");
					break;
				}
				for(int i = 0; i < recordCount; i++) {
					System.out.println((i+1) + ".  " + record[i]);
				}
			}
			else if(menuNum == 3) { // 메뉴선택 3	
				System.out.println("프로그램을 종료합니다.");
			}
			else {
				System.out.println("잘못된 메뉴 선택입니다.");
			}
		} while (menuNum != 3);
			
	}

}
