package auction.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


public class Bidder {

	private String SERVER_IP = "192.168.30.209";
	private int SERVER_PORT = 6006;
	Socket socket;
	BufferedReader in;
	PrintWriter out;
	BufferedReader consoleInput;
	int possibleMinBid; 
	LocalTime finishAuction; 
	public Bidder() {
		try {
			socket = new Socket(SERVER_IP, SERVER_PORT);
			System.out.println("- 서버에 연결되었습니다.");
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream(), true);
			consoleInput = new BufferedReader(new InputStreamReader(System.in));

		}  catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void start() throws IOException {       
		while (true) {
			System.out.println("1. 로그인");
			System.out.println("2. 회원가입");
			System.out.println("3. 종료");
			System.out.print("선택: ");
			String choice = consoleInput.readLine();

			if (choice.equals("1")) {
				logIn();
			} else if (choice.equals("2")) {
				register();
			} else if (choice.equals("3")) {
				out.println("EXIT");
				break;
			} else {
				System.out.println("잘못된 선택입니다.");
			}
		}

	}
	private void bidStart(String id) throws IOException {
		while (true) {
			System.out.println("1. 입찰하기");
			System.out.println("2. 경매기록조회");
			System.out.println("3. 이전");
			System.out.print("선택: ");
			String choice = consoleInput.readLine();

			if (choice.equals("1")) {
				System.out.print("입찰가 입력 > ");
				String bid = consoleInput.readLine();
				out.println("BID:" + id + ":" + bid);


			} else if (choice.equals("2")) {
			} else if (choice.equals("3")) {
				break;
			} else {
				System.out.println("잘못된 선택입니다.");
			}
		}
	}

	// 경매현황 수신
	private void auctionPcReceiver() {
		Thread thread = new Thread(()->{
			while(true) {
				try {
					synchronized(in) {
						String response = in.readLine();
						printAuctionPc(response);

					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		thread.start();

	}
	// 전송받은 경매현황을 출력해주는 기능
	private void printAuctionPc(String response) {
		String[] parts = response.split(":");
		String name = parts[0];
		String startPrice = parts[1];
		String highestPrice = parts[2];
		String endTime = parts[3];
		String increment = parts[4];
		finishAuction = LocalTime.parse(endTime, DateTimeFormatter.ofPattern("HH:mm:ss"));
		int highestPriceInt = Integer.parseInt(highestPrice);
		int incrementInt = Integer.parseInt(increment);		
		possibleMinBid = highestPriceInt + incrementInt;
		System.out.println("진행중인 경매 [경매품: " + name + "][시작가: " + startPrice + "][최고입찰가: " 
				+ highestPrice +"][종료시간: " + endTime + "][인상액: " + increment + "]");	
	}
	// 로그인을 하기 위한 기능
	public void logIn() throws IOException {
		// 로그인
		System.out.print("아이디 > ");
		String id = consoleInput.readLine();
		System.out.print("비밀번호 > ");
		String password = consoleInput.readLine();

		out.println("LOGIN:" + id + ":" + password);

		String response = in.readLine();
		if (response.equals("LOGIN_SUCCESS")) {
			auctionPcReceiver(); 
			System.out.println("[로그인 성공]");
			bidStart(id); 
		} else if (response.equals("LOGIN_FAIL")) {
			System.out.println("[로그인 실패: 아이디 또는 비밀번호가 잘못되었습니다.]");
		}
	}
	// 회원 가입 요청 하는 기능
	public void register() throws IOException {
		// 회원가입
		System.out.print("아이디 > ");
		String id = consoleInput.readLine();
		System.out.print("비밀번호 > ");
		String password = consoleInput.readLine();
		System.out.print("이름 > ");
		String name = consoleInput.readLine();
		System.out.print("주소 > ");
		String address = consoleInput.readLine();
		System.out.print("전화번호 > ");
		String contact = consoleInput.readLine();

		out.println("REGISTER:" + id + ":" + password + ":" + name + ":" + address + ":" + contact);

		String response = in.readLine();
		if (response.equals("REGISTER_SUCCESS")) {
			System.out.println("회원가입 성공!");
		} else {
			System.out.println("회원가입 실패: 이미 존재하는 사용자 이름입니다.");
		}
	}
}
