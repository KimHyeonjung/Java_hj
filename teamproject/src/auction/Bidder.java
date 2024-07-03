package auction;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;


public class Bidder {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.print("접속할 IP와 port번호 입력 : ");
		String ip = scan.next();
		int port = scan.nextInt();
		Member me;
		
		try {
			System.out.print("아이디 입력 : ");
			String id = scan.next();
			System.out.print("비밀번호 입력 : ");
			String pw = scan.next();
//			me = new Member(id, pw, )
			Socket socket = new Socket(ip, port);
			System.out.println("[연결 성공]");
//			Client client = new Client(id, socket);
//			client.receive();
//			client.send();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
