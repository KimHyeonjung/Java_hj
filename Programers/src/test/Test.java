package test;

import java.time.Instant;
import java.time.ZoneId;
import java.util.Scanner;

public class Test {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		Instant instant = Instant.now();	
		System.out.println(instant.atZone(ZoneId.of("Asia/Seoul")));
//		System.out.println(start);
//		while (Duration.between(start, Instant.now()).toMinutes() < 1) {
//			// 반복할 작업을 여기 추가하세요
////			System.out.println(Duration.between(start, Instant.now()));
//		}
//		System.out.println("반복문이 종료되었습니다.");

	}

}
