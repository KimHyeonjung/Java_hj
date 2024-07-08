package auction.client;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;


public class Bidder {

	public static void main(String[] args) {
//		Scanner scan = new Scanner(System.in);
//		System.out.print("접속할 IP와 port번호 입력 : ");
//		String ip = scan.next();
//		int port = scan.nextInt();
//		
		
		try {
			
			Socket socket = new Socket("192.168.30.209", 6006);
			System.out.println("[경매 서버에 연결]");
			Client client = new Client(socket);
			client.start();
//			client.send();
		} catch (Exception e) {
			System.out.println("[연결에 실패하였습니다.]");
		}
	}

}
