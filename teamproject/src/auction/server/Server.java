package auction.server;

import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {

	private List<ObjectOutputStream> list = new ArrayList<ObjectOutputStream>();
	
	private Socket socket;
	
}
