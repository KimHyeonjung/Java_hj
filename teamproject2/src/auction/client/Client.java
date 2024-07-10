package auction.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
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
			Item item =	(Item)ois.readObject();
			System.out.println(item);
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
			receive();
			send();
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
				while(true) {
					String sd = ois.readUTF();
					System.out.println(sd);
					if(sd.equals(EXIT)) {
						break;
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
					oos.writeUTF(id);
					oos.writeUTF(str);
					oos.flush();
					if(str.equals(EXIT)) {
						break;
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
