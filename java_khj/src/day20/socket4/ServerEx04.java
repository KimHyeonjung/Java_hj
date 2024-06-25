package day20.socket4;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerEx04 {

	public static void main(String[] args) {
		int port = 6006;
		
		try(ServerSocket serverSocket = new ServerSocket(port)) {
			Socket socket = serverSocket.accept();
			System.out.println("[연결 성공]");
			Client client = new Client(socket);
			client.send();
			client.receive();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
	}

}
