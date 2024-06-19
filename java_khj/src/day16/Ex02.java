package day16;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import java.util.Scanner;

import lombok.AllArgsConstructor;
import lombok.Data;

public class Ex02 {

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
		ArrayList<Player> pr = new ArrayList<Player>();
		run(pr);




	}
	private static void runMenu(int menu, ArrayList<Player> pr) {
		switch (menu) {
		case 1 :
			int count = play(pr);
			record(pr, count);
			break;
		case 2 :
			printRecord(pr);
			break;
		case 3 :
			System.out.println("종료합니다.");
			break;
		default :
			System.out.println("잘못 입력");
			break;
		}
	}
	private static void printRecord(ArrayList<Player> pr) {
		//기록이 없으면 등록된 기록이 없습니다라고 출력하고 종료
		if(pr.isEmpty()) {
			System.out.println("기록이 없습니다.");
			return;
		} 
		//반복문을 이용하여 등수, 아이디 횟수의 형태로 출력
		for(int i = 0; i < pr.size(); i++) {
			System.out.println(i+1+"."+pr.get(i));
		}
		return;

	}
	private static void record(ArrayList<Player> pr, int count) {
		String id;
		//저장된 기록이 5명 미만이면
		if(pr.size() < 5) {
			//아이디 입력받고
			System.out.print("아이디 입력 : ");
			id = scan.next();
			//아이디와 횟수를 이용해서 객체를 생성
			System.out.println("count:" + count);
			Player pl = new Player(id, count);
			System.out.println(pl.getCount());
			//리스트에 생성된 객체 추가
			pr.add(pl);
			//Collections.sort를 이용하여 정렬
			Collections.sort(pr);
			//종료
			return;
		}
		//5등 기록과 내 기록을 비교해서 내 기록이 좋으면 
		if(count < pr.get(4).getCount()) {
			//5등 위치에 내 기록을 저장
			System.out.print("아이디 입력 : ");
			id = scan.next();
			Player pl = new Player(id, count);
			pr.set(4, pl);
			//Collections.sort를 이용하여 정렬
			Collections.sort(pr);
		}
		System.out.println(pr);

	}
	private static int play(ArrayList<Player> pr) {
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

	private static void run(ArrayList<Player> pr) {
		int menu;
		do {
			printMenu();
			menu = scan.nextInt();
			runMenu(menu, pr);
		}while(menu != 3);
	}

	private static void printMenu() {
		System.out.println("메뉴");
		System.out.println("1. 플레이");
		System.out.println("2. 기록확인");
		System.out.println("3. 종료");
		System.out.println("메뉴 입력 : ");
	}

}

class Player implements Comparable<Player>{
	private String id;
	private int count;
	
	
	
	public Player(String id, int count) {
		super();
		this.id = id;
		this.count = count;
	}
	
	
	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public int getCount() {
		return count;
	}


	public void setCount(int count) {
		this.count = count;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Player other = (Player) obj;
		return count == other.count;
	}
	@Override
	public int hashCode() {
		return Objects.hash(count);
	}
	@Override
	public int compareTo(Player o) {
		return count - o.count;
	}
	@Override
	public String toString() {
		return id + " " + count + "회";
	}


}
