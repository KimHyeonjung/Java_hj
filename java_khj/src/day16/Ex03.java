package day16;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

public class Ex03 {

	private static Scanner scan = new Scanner(System.in);
	public static void main(String[] args) {
		/* UP DOWN 게임에 기록을 저장하는 기능을 추가
		 * - 최대 5등까지 저장하며, 먼저 등록된 순으로 저장
		 * - 아이디를 입력받아 저장
		 * 메뉴
		 * 1. 플레이
		 * 2. 기록확인
		 * 3. 종료
		 * 메뉴 선택 : */
		
		run();

	}
	public static void runMenu(int menu, Map<String, Integer> map) {
		switch (menu) {
		case 1 :
			int count = play(map);
			record(map, count);
			break;
		case 2 :
			printRecord(map);
			break;
		case 3 :
			System.out.println("종료합니다.");
			break;
		default :
			System.out.println("잘못 입력");
			break;
		}
	}
	private static void printRecord(Map<String, Integer> map) {
		//기록이 없으면 등록된 기록이 없습니다라고 출력하고 종료
		if(map.isEmpty()) {
			System.out.println("기록이 없습니다.");
			return;
		} 
		//반복문을 이용하여 등수, 아이디 횟수의 형태로 출력
		Iterator<String> it = map.keySet().iterator();
		
		while(it.hasNext()) {
			String id = it.next();
			Integer count = map.get(id);
			System.out.println(id + " " + count + "회");
		}

	}
	private static void record(Map<String, Integer> map, int count) {
		
		String id;
		//저장된 기록이 5명 미만이면
		if(map.size() < 5) {
			//아이디 입력받고
			System.out.print("아이디 입력 : ");
			id = scan.next();
			//map에 아이디와 횟수를 추가하는데 기존 기록이 있는 경우 더 좋은 기록으로 저장
			recordUser(map, id, count);
			map.put(id, count);
			//종료
			return;
		}
		//5등 기록과 내 기록을 비교해서 내 기록이 좋으면 
		String deleteId = checkRecord(map, count);
		if(deleteId != null) {
			//5등 위치에 내 기록을 저장
			System.out.print("아이디 입력 : ");
			id = scan.next();
			
			//deleteId와 id가 같지 않으면 deleteId에 있는 기록을 삭제
			if(!contains(map, id)) {
				map.remove(deleteId);
			}
			//recordUser를 실행
			recordUser(map, id, count);
		}

	}
	private static boolean contains(Map<String, Integer> map, String id) {
		
		return map.get(id) != null;
	}
	private static String checkRecord(Map<String, Integer> map, int count) {
		Iterator<String> it = map.keySet().iterator();
		String deleteId = null;
		int maxCount = 0;
		while(it.hasNext()) {
			String id = it.next();
			Integer recordCount = map.get(id);
			if(count < recordCount && maxCount < recordCount) {
				maxCount = recordCount;
				deleteId = id;
			}
		}
		return null;
	}
	private static void recordUser(Map<String, Integer> map, String id, int count) {
		Integer oldCount = map.get(id);
		if(oldCount == null || count < oldCount) {
			map.put(id, count);
		}
	}
	private static int play(Map<String, Integer> map) {
		int min = 1, max = 100;
		int num;
		int random = Ex01.random(min, max);
		int count = 0;
		System.out.println(random);
		do {
			System.out.print("입력 : ");
			num = scan.nextInt();
			count++;
			Ex01.upNdownCheck(num, random);
		}while(num != random);
		return count;
	}

	public static void run() {
		int menu;
		Map<String, Integer> map = new HashMap<String, Integer>();
		do {
			printMenu();
			menu = scan.nextInt();
			runMenu(menu, map);
		}while(menu != 3);
	}

	public static void printMenu() {
		System.out.println("메뉴");
		System.out.println("1. 플레이");
		System.out.println("2. 기록확인");
		System.out.println("3. 종료");
		System.out.println("메뉴 입력 : ");
	}

}
