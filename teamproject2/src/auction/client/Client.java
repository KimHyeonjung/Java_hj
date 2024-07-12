package auction.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.time.Instant;
import java.util.InputMismatchException;
import java.util.Scanner;

import auction.Item;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Client {
	
	private Socket socket;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	private String id;
	public final static String EXIT = "-quit";
	public static Scanner scan = new Scanner(System.in);
	public static int checkBid;
	public Client(Socket socket, String id) {
		this.id = id;
		this.socket = socket;
		try {
			ois = new ObjectInputStream(socket.getInputStream());
			oos = new ObjectOutputStream(socket.getOutputStream());
		} catch (Exception e) {
		}
	}
	
	public void start() {
		try {
			oos.writeUTF(id);
			oos.flush();
			Item item =	(Item)ois.readObject();
			Instant finish = (Instant)ois.readObject();
			System.out.println("진행중인 경매 [물품명: " + item.getName() 
			+ ", 시작가: " + item.getPriceWon() + ", 남은시간: " + finish + "]");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		int menu;
			System.out.println("1. 경매 참가");
			System.out.println("2. 종료");
			System.out.print("메뉴 선택 : ");
			menu = nextInt();
			runMenu(menu);
	}
	
	public void runMenu(int menu) {
		switch (menu) {
		case 1 : 
			send();
			receive();
			break;
		case 2 :
			break;
		default :
			System.out.println("잘못된 메뉴 입니다.");
			
		}
	}
	
	public void receive() {
		Thread t = new Thread(()->{
			try {
				Item item;
				while(true) {
					try {
						item = (Item)ois.readObject();
						System.out.println(item);
						if(item == null) {
							System.out.println("[경매가 종료되었습니다.]");
						}else {
							checkBid = item.getPrice();
							System.out.println(checkBid + "test");
						}
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}
					
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		t.start();
	}
	
	//문자열을 입력해서 소켓으로 전송하는 쓰레드를 생성하고 실행하는 메소드
	public void send() {
		Thread t = new Thread(()->{
			try {
				while(true){
					System.out.print("희망 입찰가 입력: ");
					String str = scan.next();
					int bid = Integer.parseInt(str);
					if(str.equals(EXIT)) {
						break;
					}
					if(bid < checkBid) {
						System.out.println("현 가격보다 낮습니다.");
						continue;
					} else {
						oos.writeUTF(id);
						oos.writeUTF(str);
						oos.flush();
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		t.start();
	}
	
	//정수 말고 다른거 입력했을 때 예외 처리
	public int nextInt() {
		try {
			return scan.nextInt();
		} catch (InputMismatchException e) {
			scan.nextLine();
			return Integer.MIN_VALUE;
		}
	}
}
