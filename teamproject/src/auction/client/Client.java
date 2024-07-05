package auction.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.InputMismatchException;
import java.util.Scanner;

import auction.Member;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Client {
	
	private Socket socket;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	public final static String EXIT = "-quit";
	public final static String LOGIN = "-login";
	public final static String REGIST = "-regist";
	public static Scanner scan = new Scanner(System.in);
	public Client(Socket socket) {
		this.socket = socket;
		try {
			ois = new ObjectInputStream(socket.getInputStream());
			oos = new ObjectOutputStream(socket.getOutputStream());
		} catch (Exception e) {
		}
	}
	
	public void start() {
		int menu = 1;
		System.out.println("1. 로그인");
		System.out.println("2. 회원가입");
		System.out.println("3. 종료");
		System.out.print("메뉴 선택 : ");
		menu = nextInt();
		runMenu(menu);
	}
	
	public void runMenu(int menu) {
		switch (menu) {
		case 1 : 
			try {
				oos.writeUTF(LOGIN);
			} catch (IOException e) {}
			receive();
			break;
		case 2 :
			try {
				oos.writeUTF(REGIST);
			} catch (IOException e) {}
			receive();
			break;
		case 3 :
			break;
		default :
			System.out.println("잘못된 메뉴 입니다.");
			
		}
	}
	
	public void registration() {
		
	}
	
	public void logIn() {
		System.out.print("아이디 입력 : ");
		String id = scan.next();
		System.out.print("비밀번호 입력 : ");
		String pw = scan.next();
		Member login = new Member(id, pw, null, false);
		try {
			oos.writeObject(login);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	// 로그인 하고서 수신 가능
	public void receive() {
		Thread t = new Thread(()->{
			try {
				
				while(true) {
					String so = ois.readUTF();
					if(so.equals(EXIT)) {
						break;
					}
					if(so.equals(LOGIN)) {
						logIn();
					}
					if(so.equals(REGIST)) {
						registration();
					}
						System.out.println(so);
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
					String str = scan.nextLine();
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
