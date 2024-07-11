package test;

import java.util.Date;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class Test {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		   Timer timer = new Timer();
	        TimerTask task = new TimerTask() {
	            public void run() {
	                System.out.println("Task executed!");
	            }
	        };

	        // Schedule the task to run every 5 seconds starting from the current time
	        Date startTime = new Date();
	        timer.schedule(task, startTime, 1000);
		
//		Instant start = Instant.now();
//		System.out.println(start);
//		while (Duration.between(start, Instant.now()).toMinutes() < 1) {
//			// 반복할 작업을 여기 추가하세요
////			System.out.println(Duration.between(start, Instant.now()));
//		}
//		System.out.println("반복문이 종료되었습니다.");

	}

}
