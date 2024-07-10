package test;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;

public class Test {

	public static void main(String[] args) {
		
		
		
		Instant start = Instant.now();
		System.out.println(start);
		while (Duration.between(start, Instant.now()).toMinutes() < 1) {
			// 반복할 작업을 여기 추가하세요
//			System.out.println(Duration.between(start, Instant.now()));
		}
		System.out.println("반복문이 종료되었습니다.");

	}

}
