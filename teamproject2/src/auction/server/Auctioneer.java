package auction.server;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import auction.Item;


public class Auctioneer {

	static Scanner scan = new Scanner(System.in);
	public static void main(String[] args) {
		int port = 6006;
		try {
			String ip = InetAddress.getLocalHost().getHostAddress();
			System.out.println(ip);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		Item item = addItem();
		List<ObjectOutputStream> list = new ArrayList<ObjectOutputStream>();
		
		try(ServerSocket serverSocket = new ServerSocket(port)) {
			System.out.println("경매 서버 오픈");
			while(true) {
				Socket socket = serverSocket.accept();
				if(socket.isConnected()) {
					System.out.println("[" + socket.getLocalAddress() + " : " + socket.getPort() + "에서 접속]");
				}
				
				Server server = new Server(list, item, socket);
				server.receive();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static Item addItem() {
		System.out.println("[경매 물품 등록]");
		String name;
		int price, time;
		System.out.print("물품명: ");
		name = scan.nextLine();
		System.out.print("시작가 : ");
		price = scan.nextInt();
		System.out.print("타이머(s) : ");
		time = scan.nextInt();
		Date timer = new Date(System.currentTimeMillis() + (time * 1000));
		
		
		return new Item(name, price, timer, "");
	}


}