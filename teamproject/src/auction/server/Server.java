package auction.server;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import auction.Member;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Server {

	private List<ObjectOutputStream> list = new ArrayList<ObjectOutputStream>();
	private List<Member> memberList = new ArrayList<Member>();	
	private Socket socket;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	public final static String EXIT = "-quit";
	public final static String LOGIN = "-login";
	public final static String REGIST = "-regist";

	public Server(List<ObjectOutputStream> list, List<Member> memberList, Socket socket) {
		this.list = list;
		this.socket = socket;
		try { // Server 객체 생성시 input, output 스트림도 같이 생성해줌
			this.oos = new ObjectOutputStream(socket.getOutputStream());
			this.ois = new ObjectInputStream(socket.getInputStream());
			list.add(oos); //연결중인 모든 클라이언트들에게 보내주기 위해 생성된 socket의 output스트림을 저장
			File file = new File("memberData.txt");
			loadMemberList(file);
			Collections.synchronizedList(memberList);
		} catch (Exception e) {
		}
	}

	public void receive() {
		Thread thread = new Thread(()->{
			try {
				String menu ="";
				menu = ois.readUTF();
				if(menu.equals(LOGIN)) {
					oos.writeUTF(LOGIN);
					while(true) {
						Member login;
						try {
							login = (Member)ois.readObject();
							for(Member tmp : memberList) {
								if(tmp.equals(login)) {
									oos.writeUTF("-identify");
									break;
								} else {
									oos.writeUTF("-no");
								}
							}
							break;
						} catch (ClassNotFoundException e) {
							e.printStackTrace();
						}

					}
				}
				if(menu.equals(REGIST)) {
					oos.writeUTF(REGIST);
				}

				while(true) { // true 대신 경매 시간 비교식 넣으면 될듯
					//					if(ois.getClass()) {
					//						
					//					}
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}

	@SuppressWarnings("unchecked")
	private void loadMemberList(File file) {
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))){
			memberList = (List<Member>)ois.readObject();
		} catch (Exception e) {
			System.out.println("파일 불러오기 실패");
		}
	}

}


