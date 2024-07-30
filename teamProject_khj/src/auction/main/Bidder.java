package auction.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.DecimalFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;


public class Bidder {

	private String SERVER_IP = "localhost";
	private int SERVER_PORT = 6006;
	Socket socket;
	BufferedReader in;
	PrintWriter out;
	BufferedReader consoleInput;
	int possibleMinBid; 
	 
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
				if(Integer.parseInt(bid) >= possibleMinBid) {
					out.println("BID:" + id + ":" + bid);
				} else {
					System.out.println(getFormatWon(possibleMinBid) + "원 이상만 입찰 가능합니다.");
				}
					


			} else if (choice.equals("2")) {
			} else if (choice.equals("3")) {
				break;
			} else {
				System.out.println("잘못된 선택입니다.");
			}
		}
	}

	// 경매현황 수신
	private void bidderReceiver() {
		Thread thread = new Thread(()->{
			while(true) {
				try {
					synchronized(in) {
						String response = in.readLine();
						if (response.startsWith("PRESENT_CONDITION")) {
							printAuctionPc(response);
						} else if (response.startsWith("FINISH")) {
							String[] parts = response.split(":");
							String notify = parts[1];
							System.out.println(notify);
							break;
						} else {
							System.out.println(response);
						}

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
		String[] parts = response.split("::");
		String name = parts[1];
		String startPrice = parts[2];
		String highestPrice = parts[3];
		String endTime = parts[4];
		String increment = parts[5];
		int highestPriceInt = Integer.parseInt(highestPrice);
		int incrementInt = Integer.parseInt(increment);		
		possibleMinBid = highestPriceInt + incrementInt; // 입찰 가능 금액
		System.out.println("진행중인 경매 [경매품: " + name + "][시작가: " + getFormatWon(startPrice) + "][최고입찰가: " 
				+ getFormatWon(highestPrice) +"][종료시간: " + endTime + "][최소 입찰 가능액: " + getFormatWon(possibleMinBid) + "]");	
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
			bidderReceiver(); 
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
		if(!Pattern.matches(getRegex("id"), id)) {
			System.out.println("[영문+숫자 3~12자 첫글자는 영문만 가능]");
			return;
		}
		System.out.print("비밀번호 > ");
		String password = consoleInput.readLine();
		if(!Pattern.matches(getRegex("password"), password)) {
			System.out.println("[영문+숫자 4~14자 첫글자는 영문만 가능]");
			return;
		}
		System.out.print("이름 > ");
		String name = consoleInput.readLine();
		if(!Pattern.matches(getRegex("name"), name)) {
			System.out.println("[영문 2~10자, 한글 2~5자]");
			return;
		}
		System.out.print("주소 > ");
		String address = consoleInput.readLine();
		if(!Pattern.matches(getRegex("address"), address)) {
			System.out.println("[최대 35자까지만 가능]");
			return;
		}
		System.out.print("전화번호 > ");
		String contact = consoleInput.readLine();
		if(!Pattern.matches(getRegex("contact"), contact)) {
			System.out.println("[전화번호 형식이 잘못됨]");
			return;
		}

		out.println("REGISTER:" + id + ":" + password + ":" + name + ":" + address + ":" + contact);

		String response = in.readLine();
		if (response.equals("REGISTER_SUCCESS")) {
			System.out.println("회원가입 성공!");
		} else {
			System.out.println("회원가입 실패: 이미 존재하는 사용자 이름입니다.");
		}
	}
	//정규표현식 모음
	private String getRegex(String regex) {
		if(regex.equals("id")) {
			return "^[a-zA-Z][a-zA-Z0-9]{2,11}$";
		}
		if(regex.equals("password")) {
			return "^[a-zA-Z][a-zA-Z0-9]{3,13}$\"";
		}
		if(regex.equals("name")) {
			return "^([가-힣]{2,5}|[a-zA-Z]{2,10})$";
		}
		if(regex.equals("address")) {
			return "^[a-zA-Z0-9가-힣]{1,35}$";
		}
		if(regex.equals("contact")) {
			return "^[0-9]{3}-[0-9]{3,4}-[0-9]{4}$";
		}
		return null;
	}
	public String getFormatWon(int price) {
		DecimalFormat format = new DecimalFormat("###,###,###,###");
		return format.format(price);
	}
	public String getFormatWon(String price) {
		int priceInt = Integer.parseInt(price);
		DecimalFormat format = new DecimalFormat("###,###,###,###");
		return format.format(priceInt);
	}
}
