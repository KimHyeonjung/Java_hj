package auction.server;

import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import auction.Member;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Server {

	private List<ObjectOutputStream> list = new ArrayList<ObjectOutputStream>();
	private List<Member> memberList = new ArrayList<Member>();	
	private Socket socket;
	
	public Server(List<ObjectOutputStream> list, Socket socket) {
		this.list = list;
		this.socket = socket;
	}
	
	public void receive() {
		
	}
	
}
