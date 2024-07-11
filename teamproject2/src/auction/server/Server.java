package auction.server;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import auction.Item;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Server {

	private List<ObjectOutputStream> list = Collections.synchronizedList(new ArrayList<ObjectOutputStream>());
	private List<Item> itemList = Collections.synchronizedList(new ArrayList<Item>());
	private Socket socket;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	public final static String EXIT = "-quit";


	public Server(List<ObjectOutputStream> list, Socket socket) {
		this.list = list;
		this.socket = socket;
		try { // Server 객체 생성시 input, output 스트림도 같이 생성해줌
			this.oos = new ObjectOutputStream(socket.getOutputStream());
			this.ois = new ObjectInputStream(socket.getInputStream());
			list.add(oos); //연결중인 모든 클라이언트들에게 보내주기 위해 생성된 socket의 output스트림을 저장

			//			try {
			//				File file = new File("src/auction/server/itemData.txt");
			//				ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
			//				oos.writeObject(itemList);
			//				oos.close();
			//			} catch (Exception e) {
			//				System.out.println("파일 저장 실패");
			//			}

			File file = new File("src/auction/server/itemData.txt");
			loadItemList(file);

		} catch (Exception e) {
		}
	}

	public void receive(Item item, int period) {
		Thread thread = new Thread(()->{
			String id = "";
			try {
				id = ois.readUTF();
				System.out.println("[ "+ id + "님 입장 ]");
				oos.writeObject(item);
				oos.writeByte(period);				
				oos.flush();
				System.out.println("경매정보 전송");
				
				while(true) { // true 대신 경매 시간 비교식 넣으면 될듯
					id = ois.readUTF();
					String str = ois.readUTF();
					int price = Integer.parseInt(str);
					System.out.println(id + ": " + str);
					//최고입찰가와 입찰자를 아이템 등록
					Item updateBid = new Item(item.getName(), price, id);
						//메세지를 보낸 소켓을 제외한 다른 소켓에 메세지를 전송
							sendAll(updateBid);
				}
				//				System.out.println("[ 경매종료 ]");

			} catch (IOException e) {
				System.out.println("[ "+id + "님 퇴장 ]");
			}
		});
		thread.start();
	}

	public void sendAll(Item updateBid) {
		for(ObjectOutputStream tmp : list) {
			//메세지를 보낸 소켓을 제외한 다른 소켓에 메세지를 전송
//			if(tmp != oos) { //본인도 확인 가능하도록 함
				try {
					System.out.println(updateBid);
					tmp.writeObject(updateBid);
					tmp.flush();
				} catch (IOException e) {
					e.printStackTrace();
				}
//			}
		}
	}

	@SuppressWarnings("unchecked")
	private void loadItemList(File file) {
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))){
			itemList = (List<Item>)ois.readObject();
		} catch (Exception e) {
			System.out.println("파일 불러오기 실패");
		}
	}

}


