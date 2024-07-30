package test;

import java.text.DecimalFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Scanner;

public class Test {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		auctionTimer();
		
		
		
//		Instant instant = Instant.now();	
//		System.out.println(instant.atZone(ZoneId.of("Asia/Seoul")));
//		System.out.println(start);
//		while (Duration.between(start, Instant.now()).toMinutes() < 1) {
//			// 반복할 작업을 여기 추가하세요
////			System.out.println(Duration.between(start, Instant.now()));
//		}
//		System.out.println("반복문이 종료되었습니다.");
	}
	public static void auctionTimer() {
		Thread t = new Thread(()->{
			int count = 10;
			Instant finish = Instant.now().plusSeconds(15);
			System.out.println(finish + " " + finish.minusMillis(10000));
			while(true) {
				if(finish.minusSeconds(count).isBefore(Instant.now())) {
					System.out.println("경매 종료까지 " + count + "초 남았습니다.");
					count--;
				}
				if(count == 0) {
					break;
				}
			}
		});
		t.start();
	}

}
