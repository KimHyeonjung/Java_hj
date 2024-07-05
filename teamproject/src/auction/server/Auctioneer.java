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
import java.util.List;

import auction.Member;


public class Auctioneer {

	public static void main(String[] args) {
		int port = 6006;
		try {
			String ip = InetAddress.getLocalHost().getHostAddress();
			System.out.println(ip);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
		List<ObjectOutputStream> list = new ArrayList<ObjectOutputStream>();
		List<Member> memberList = new ArrayList<Member>();
		try(ServerSocket serverSocket = new ServerSocket(port)) {
			System.out.println("경매 서버 오픈");
			while(true) {
				Socket socket = serverSocket.accept();
				if(socket.isConnected()) {
					System.out.println("[" + socket.getLocalAddress() + " : " + socket.getPort() + "에서 접속]");
				}
				
				Server server = new Server(list, memberList, socket);
//				server.receive();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


}
