package auction.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import auction.controller.AuctionController;
import auction.controller.MemberController;
import auction.model.vo.MemberVO;

/* 1. 회원정보 관리
 * 2. 경매 관리
 * */

public class Auctioneer {

	private Scanner scan = new Scanner(System.in);
	MemberController memberController = new MemberController(scan);
	AuctionController auctionController = new AuctionController(scan);
	OpenServer serverStart;
	List<PrintWriter> clients;
	private BufferedReader in;
	private PrintWriter out;
	boolean auctionState = false; // 경매 시작 상태
	boolean firstState = true; // 경매 시작 후 처음인지 아닌지
	PresentCondition presentCondition;
	public Auctioneer(){
		clients = new ArrayList<PrintWriter>();
		Collections.synchronizedList(clients); //리스트 동기화
	}

	public void start() throws InterruptedException {
		serverStart = new OpenServer();
		serverStart.start();		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
		}
		int menu = 0;
		do {
			System.out.println("1. 회원 관리");
			System.out.println("2. 경매 관리");
			System.out.println("3. 종료");
			System.out.print("메뉴 선택 : ");

			menu = nextInt();
			runMenu(menu);

		} while(menu != 3);
	}


	public void runMenu(int menu) throws InterruptedException {
		switch (menu) {
		case 1 : 
			memberMenuList();
			break;
		case 2 :
			auctionMenuList();
			break;
		case 3 :

			System.out.println("[프로그램 종료]");
			break;
		default :
			System.out.println(menu);
			System.out.println("잘못된 메뉴 입니다1.");
		}
	}
	public void auctionMenuList() {
		int menu = 0;
		do {
			System.out.println("1. 경매정보 등록");
			System.out.println("2. 경매 시작");
			System.out.println("3. 경매기록 조회");
			System.out.println("4. 입찰기록 조회");
			System.out.println("5. 이전 메뉴");
			System.out.print("메뉴 선택 : ");

			menu = nextInt();
			runAuctionMenu(menu);

		}while(menu != 5);
	}	

	public void memberMenuList() {
		int menu = 0;
		while(menu != 5) {
			System.out.println("1. 회원 추가");
			System.out.println("2. 회원 수정");
			System.out.println("3. 회원 삭제");
			System.out.println("4. 회원 조회");
			System.out.println("5. 이전 메뉴");
			System.out.print("메뉴 선택 : ");

			menu = nextInt();
			runMemberMenu(menu);
		}
	}	

	private void runAuctionMenu(int menu) {
		switch (menu) {
		case 1 : 
			presentCondition = auctionController.insertItem();
			break;
		case 2 :
			if(presentCondition == null) {
				System.out.println("먼저 경매정보를 입력해주세요");				
				break;
			}
			auctionState = true;
			firstState = true;
			auctionController.startAuction(presentCondition);
			sendAll(presentCondition);
			firstState = false;
			break;
		case 3 :
			//			searchAuctionDB();
			break;
		case 4 :
			//			searchBidDB();
			break;
		default :
			System.out.println("잘못된 메뉴 입니다.");
		}		
	}
	private void runMemberMenu(int menu) {
		switch (menu) {
		case 1 : 
			memberController.insertMember();
			break;
		case 2 :
			memberController.updateMember();
			break;
		case 3 :
			memberController.deleteMember();
			break;
		case 4 :
			memberController.searchMember();
			break;
		default :
			System.out.println("잘못된 메뉴 입니다.");
		}
	}


	// 
	class OpenServer extends Thread{

		public void run() {
			int port = 6006; // 서버 포트번호
			try {
				String ip = InetAddress.getLocalHost().getHostAddress();
				System.out.println(ip + "  " + port);
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
			try(ServerSocket serverSocket = new ServerSocket(port)) {
				while(true) {
					Socket clientSocket = serverSocket.accept();
					if(clientSocket.isConnected()) {
						System.out.println("[" + clientSocket + "에서 접속]");
					}				
					ClientHandler clientHandler = new ClientHandler(clientSocket);
					clientHandler.start();
				}
			}  catch (IOException e) {
				e.printStackTrace();
			} 
		}
	}


	// 클라이언트 요청 처리를 담당하는 쓰레드
	class ClientHandler extends Thread {
		private final Socket clientSocket;

		public ClientHandler(Socket socket) {
			this.clientSocket = socket;
		}

		@Override
		public void run() {
			String id = null;
			try {
				in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				out = new PrintWriter(clientSocket.getOutputStream(), true);
				clients.add(out);
				String request;
				while ((request = in.readLine()) != null) {
						if (request.startsWith("BID")) {
							handleBid(request);
						} else if (request.startsWith("REGISTER")) {
							handleRegister(request);
						} else if (request.startsWith("LOGIN")) {
							String[] parts = request.split(":");
							id = parts[1];
							handleLogin(request);
						} else if (request.equals("EXIT")) {
							break;
						}						
					
				}

				System.out.println("클라이언트 연결 종료: " + clientSocket);
				clientSocket.close();
			} catch (IOException e) {
//				e.printStackTrace();
			}
			finally {
				if(id != null) {
					System.out.println("[나감 : " + id + "]");
				} else {
					System.out.println("[나감 : " + clientSocket + "]");
				}
				clients.remove(out);
				try {
					clientSocket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}		
		private void handleBid(String request) {
			//들어온 입찰 처리
			if(auctionState) {
				String[] parts = request.split(":");
				String bid = parts[2];
				presentCondition.setHighestBidToInt(bid);
				sendAll(presentCondition);
				
			} else {
				out.println("진행중인 경매가 없습니다.");
			}
		}

		// 회원가입 요청 처리
		public void handleRegister(String request) throws IOException {
			String[] parts = request.split(":");
			String id = parts[1];
			String password = parts[2];
			String name = parts[3];
			String address = parts[4];
			String contact = parts[5];
			MemberVO member = new MemberVO(id, password, name, address, contact);
			boolean notExists = memberController.insertMember(member);
			if(!notExists) {
				out.println("ALREADY_EXISTS");
			} else {	            	
				out.println("REGISTER_SUCCESS");
			}
		}


		// 로그인 요청 처리
		public void handleLogin(String request) throws IOException {
			String[] parts = request.split(":");
			String id = parts[1];
			String password = parts[2];

			if (memberController.checkIdPw(id, password)) {
				System.out.println("[로그인 : "+id+"] ");
				out.println("LOGIN_SUCCESS");
				if(auctionState) {
					sendOne(presentCondition);
				} else {
					out.println("진행중인 경매가 없습니다.");
				}
			} else {
				out.println("LOGIN_FAIL");
			}            		
			
		}

	}	
	// 로그인 중인 회원들에게 경매현황을 전송하는 기능
	public void sendAll(PresentCondition presentCondition) {
		String name = presentCondition.getName();
		String startPrice = presentCondition.getStartPriceWon();
		String highestPrice = presentCondition.getHighestBidWon();
		String endTime = presentCondition.getEndTimeToString();
		String increment = presentCondition.getIncrementWon();
		for(PrintWriter out : clients) {
			if(firstState) {
				out.println("경매를 시작합니다.");
			}
			out.println(name + ":" + startPrice +":" + highestPrice + ":" + endTime + ":" + increment);
		}
	}
	// 경매현황을 전송하는 기능 
	public void sendOne(PresentCondition presentCondition) {
		String name = presentCondition.getName();
		String startPrice = presentCondition.getStartPriceWon();
		String highestPrice = presentCondition.getHighestBidWon();
		String endTime = presentCondition.getEndTimeToString();
		String increment = presentCondition.getIncrementWon();
		
		out.println(name + ":" + startPrice +":" + highestPrice + ":" + endTime + ":" + increment);
		
	}
	
	public int nextInt() {
		try {
			return scan.nextInt();
		} catch (InputMismatchException e) {
			scan.nextLine();
			System.out.println();
			return Integer.MIN_VALUE;
		}
	}
}
