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
import java.util.Date;
import java.util.List;

import auction.Item;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Server {

	private List<ObjectOutputStream> list = new ArrayList<ObjectOutputStream>();
	private List<Item> itemList = new ArrayList<Item>();
	private Item item;
	private Socket socket;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	public final static String EXIT = "-quit";
	
	
	public Server(List<ObjectOutputStream> list, Item item, Socket socket) {
		this.list = list;
		this.socket = socket;
		this.item = item;
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
			Collections.synchronizedList(itemList);
		} catch (Exception e) {
		}
	}

	public void receive() {
		Thread thread = new Thread(()->{
			try {
				oos.writeObject(item);
				oos.flush();
				System.out.println("아이템 전송");
				
				while(item.getTime().after(new Date(System.currentTimeMillis()))) { // true 대신 경매 시간 비교식 넣으면 될듯
					
					
				}
				System.out.println("경매종료");

			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		thread.start();
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


