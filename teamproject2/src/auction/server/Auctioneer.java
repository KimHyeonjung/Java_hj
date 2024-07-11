package auction.server;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import auction.Item;


public class Auctioneer {

	static Scanner scan = new Scanner(System.in);
	static int period;
	public static void main(String[] args) {
		int port = 6006;
		try {
			String ip = InetAddress.getLocalHost().getHostAddress();
			System.out.println(ip);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
		List<ObjectOutputStream> list = new ArrayList<ObjectOutputStream>();
		Item item = addItem();
		System.out.print("진행 시간(분) : ");
		period = (scan.nextInt() * 60);
		
		 Timer timer = new Timer();
	        TimerTask task = new TimerTask() {
	            public void run() {
	                period--;
	                System.out.print(period+" ");
	            }
	        };

	        // Schedule the task to run every 5 seconds starting from the current time
	        Date startTime = new Date();
	        timer.schedule(task, startTime, 1000);
		
		try(ServerSocket serverSocket = new ServerSocket(port)) {
			System.out.println("<< 경매 서버 오픈 >>");
			while(true) {
				Socket socket = serverSocket.accept();
				if(socket.isConnected()) {
					System.out.println("[" + socket.getLocalAddress() + " : " + socket.getPort() + "에서 접속]");
				}
				
				Server server = new Server(list, socket);
				server.receive(item, period);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static Item addItem() {
		System.out.println("[경매 물품 등록]");
		String name;
		int price;
		System.out.print("물품명: ");
		name = scan.nextLine();
		System.out.print("시작가 : ");
		price = scan.nextInt();
		
		
		
		
		return new Item(name, price, "");
	}


}
