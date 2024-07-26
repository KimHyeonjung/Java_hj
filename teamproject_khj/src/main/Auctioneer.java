package main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import auction.Item;
import auction.Server;

/* 1. 회원정보 관리
 * 2. 경매 관리
 * */
public class Auctioneer {

	static Scanner scan = new Scanner(System.in);

	public void start() {
		int port = 6006;
		try {
			String ip = InetAddress.getLocalHost().getHostAddress();
			System.out.println(ip + "  " + port);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}


		try(ServerSocket serverSocket = new ServerSocket(port)) {
			System.out.println("<< 경매 서버 오픈 >>");
			while(true) {
				Socket socket = serverSocket.accept();
				if(socket.isConnected()) {
					System.out.println("[" + socket.getLocalAddress() + " : " + socket.getPort() + "에서 접속]");
				}				
				Server server = new Server(socket);
				//				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	public static void mainMenuList() {
		int menu;
		System.out.println("1. 회원 관리");
		System.out.println("2. 경매 관리");
		System.out.print("메뉴 선택 : ");

		menu = nextInt();
		runMenu(menu);


	}


	public static void runMenu(int menu) {
		switch (menu) {
		case 1 : 
			memberMenuList();
			break;
		case 2 :
			auctionMenuList();
			break;
		default :
			System.out.println("잘못된 메뉴 입니다.");

		}
	}
	
	public static void memberMenuList() {
		int menu;
		System.out.println("1. 회원 추가");
		System.out.println("2. 회원 수정");
		System.out.println("3. 회원 삭제");
		System.out.println("4. 회원 조회");
		System.out.print("메뉴 선택 : ");

		menu = nextInt();
		runMemberMenu(menu);
	}	
	public static void auctionMenuList() {
		int menu;
		System.out.println("1. 경매정보 등록");
		System.out.println("2. 경매 시작");
		System.out.println("3. 경매기록 조회");
		System.out.println("4. 입찰기록 조회");
		System.out.print("메뉴 선택 : ");

		menu = nextInt();
		runAuctionMenu(menu);
	}
	
	private static void runAuctionMenu(int menu) {
		switch (menu) {
		case 1 : 
			insertItem();
			break;
		case 2 :
			startAuction();
			break;
		case 3 :
			searchAuctionDB();
			break;
		case 4 :
			searchBidDB();
			break;
		default :
			System.out.println("잘못된 메뉴 입니다.");
		}		
	}
	private static void runMemberMenu(int menu) {
		switch (menu) {
		case 1 : 
			insertMember();
			break;
		case 2 :
			updateMember();
			break;
		case 3 :
			deleteMember();
			break;
		case 4 :
			searchMember();
			break;
		default :
			System.out.println("잘못된 메뉴 입니다.");
		}
	}
		/*	
	public static void addItem() {
		item = setItem();
		System.out.print("진행 시간(분) : ");
		int period = (scan.nextInt() * 60);
		finish = Instant.now().plusSeconds(period);
		ZonedDateTime  auctionFinish = finish.atZone(ZoneId.of("Asia/Seoul"));
		System.out.println("종료 시간: " + auctionFinish.format(DateTimeFormatter.ofPattern("HH시 mm분 ss초")));
	}

	public static Item setItem() {
		System.out.println("[경매 물품 등록]");
		String name;
		int price;
		System.out.print("물품명: ");
		scan.nextLine();
		name = scan.nextLine();
		System.out.print("시작가 : ");
		price = scan.nextInt();

		return new Item(name, price, "");
	}

	public static void showItemList() {
		for(Item tmp : itemList) {
			if(tmp != null) {
				System.out.println("[ " + tmp.getName() + " | " +  tmp.getPrice() + " | " + tmp.getBidder() + " ]");
			}
		}
	}
		 */
		public static int nextInt() {
			try {
				return scan.nextInt();
			} catch (InputMismatchException e) {
				scan.nextLine();
				return Integer.MIN_VALUE;
			}
		}
	}
