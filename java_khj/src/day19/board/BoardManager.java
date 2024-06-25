package day19.board;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class BoardManager {

	Scanner scan = new Scanner(System.in);
	List<Board> bdList = new ArrayList<Board>();
	private String fileName = "src/day19/board/data.txt";

	public void run() {
		BoardType bt = BoardType.INSERT;
		int menu;
		load(fileName);
		do {
			printMenu();
			try {
				menu = scan.nextInt();
				bt = BoardType.fromValue(menu);
				runMenu(bt);
			} catch (Exception e) {
				System.out.println("예외가 발생했습니다.");
				e.printStackTrace();
			}
			
		}while(bt != BoardType.EXIT);
		save(fileName);
	}

	public void save(String fileName) {
		try(FileOutputStream fos = new FileOutputStream(fileName);
			ObjectOutputStream oos = new ObjectOutputStream(fos)) {
			oos.write(Board.getCount());
			oos.writeObject(bdList);
		} catch (Exception e) {
			System.out.println("저장에 실패했습니다.");
		}
	}
	
	public void load(String fileName) {
		try(FileInputStream fis = new FileInputStream(fileName);
			ObjectInputStream ois = new ObjectInputStream(fis)) {
			int count = ois.read();
			Board.setCount(count);
			bdList = (List<Board>)ois.readObject();
		} catch (Exception e) {
			System.out.println("불러오기에 실패했습니다.");
		}
	}
	
	
	public void printMenu() {
		System.out.println("▼메뉴");
		printBar();
		System.out.println("1. 게시글 등록\r\n"
				+ "2. 게시글 수정\r\n"
				+ "3. 게시글 삭제\r\n"
				+ "4. 게시글 조회\r\n"
				+ "5. 프로그램 종료");
		printBar();
		System.out.print("메뉴 선택 :");
		
	}
	public void printBar() {
		System.out.println("-----------------------------");
	}
	public void runMenu(BoardType menu) throws Exception {
		switch (menu) {
		case INSERT :
			insert();
//			System.out.println(bdList);
			break;
		case UPDATE :
			update("수정");
			break;
		case DELETE :
			delete("삭제");
			break;
		case SEARCH :
			search();
			break;
		case EXIT :
			exit();
			break;
		}
		
	}
	private void exit() {
		printBar();
		System.out.println("프로그램을 종료합니다.");
		printBar();
	}
	/**
	 * 
	 * @param search
	 * @return
	 */
	private List<Board> getSearchList(String search) {
		
		List<Board> searchList = new ArrayList<Board>();
		//전체 게시글에서 하나씩 꺼내서 전체 탐색
		for(Board post : bdList) {
			//게시글의 제목 또는 내용에 검색어가 포함되어 있으면 검색 리스트에 추가합니다.
			if(post.getTitle().contains(search) ||	post.getSub().contains(search)) {
				searchList.add(post);
			}
			
		}
		return searchList;
		
		//스트림을 이용하여 검색어와 일치하는 게시글 리스트를 가져옴
//		return bdList.stream().filter(b->b.getTitle().contains(search) || b.getSub().contains(search))
//			    .collect(Collectors.toList());
	}
	
	private void search() {
		printBar();
		//검색어 입력
		System.out.print("검색어 입력(전체는 엔터) : ");
		scan.nextLine();
		String search = scan.nextLine();
		//게시글에서 검색어가 제목 또는 내용에 들어간 게시글리스트를 가져옴
		List<Board> searchList = getSearchList(search);
		//게시글 리스트가 비어있으면 안내문구 출력 후 종료
		if(searchList.size() == 0) {
			printBar();
			System.out.println("검색어가 포함된 게시글이 없습니다.");
			return;
		}
		//가져온 게시글 리스트를 출력
		printList(searchList);
		//게시글을 확인할건지 선택
		printBar();
		System.out.print("게시글 내용 확인할겨?(y / n)");
		char yn = scan.next().charAt(0);
		//확인하지 않겠다고 하면 종료
		if(yn == 'n') {
			return;
		}
		//확인하면 게시글 번호를 입력
		if(yn == 'y' ) {
			printBar();
			System.out.print("검색 결과 중 확인할 게시글 번호 입력 :");
			int num = scan.nextInt();
			//입력받은 게시글 번호로 객체를 생성
			Board bd = new Board(num);
			//검색된 리스트에서 생성된 객체와 일치하는 번지를 확인
			int index = searchList.indexOf(bd);
			//번지가 유효하지 않으면 안내문구 출력후 종료
			if(index < 0) {
				printBar();
				System.out.println("존재하지 않는 게시글입니다.");
				return;
			}
			//번지에 있는 게시글을 가져옴
			bd = searchList.get(index);
			//가져온 게시글을 출력
			bd.print();
		}
		//메뉴로 돌아가려면.. 문구 출력
		System.out.print("메뉴로 돌아가시려면 엔터 입력 : ");
		//엔터를 입력받도록 처리
		String str = scan.nextLine();
		if(str.equals("")) {
			return;
		}
		printBar();
	}

	private void printList(List<Board> searchList) {
		for(Board post : searchList) {
			System.out.println(post);
		}
		
	}

	private void delete(String type) {
		//삭제할 게시글 번호를 입력
		printBar();
		System.out.print(type + "할 게시글 번호를 입력 :");
		int num = scan.nextInt();
		//게시글 번호에 맞는 게시글을 가져옴
		Board tmpBd = selectPost(num);
		//게시글이 없으면 종료
//		if(tmpBd == null) {
//			return;
//		}
//		//리스트에서 게시글을 삭제
//		bdList.remove(tmpBd);
//		printBar();
//		System.out.println(tmpBd.getNum() + "번 게시글이 " + type + "되었습니다.");
//		printBar();
		
		//게시글을 리스트에서 삭제하는데 성공하면  안내 문구 출력 위 166~172 코드 대신
		if(bdList.remove(tmpBd)) {
			printBar();
			System.out.println(tmpBd.getNum() + "번 게시글이 " + type + "되었습니다.");
		}
	}

	private void update(String type) {
		//수정할 게시글 번호를 입력
		printBar();
		System.out.print(type + "할 게시글 번호를 입력 :");
		int num = scan.nextInt();
		//게시글 번호에 맞는 게시글을 가져옴
		Board tmpBd = selectPost(num);
		//게시글이 없으면 종료
		if(tmpBd == null) {
			return;
		}
		//같으면 새 제목과 내용을 입력
		scan.nextLine(); //공백처리
		System.out.print("제목 : ");
		String title = scan.nextLine();
		System.out.print("내용 : ");
		String sub = scan.nextLine();
		//가져온 객체의 제목과 내용을 입력받은 제목과 내용으로 수정하고
		tmpBd.update(title, sub);
		//안내문구 출력
		printBar();
		System.out.println(tmpBd.getNum() + "번 게시글이 " + type + "되었습니다.");
		printBar();
	}

	/**
	 * 
	 */
	private void insert() {
		//게시글 정보를 입력
		String title, sub, id, pw;
		scan.nextLine(); //공백 처리
		System.out.print("제목 : ");
		title = scan.nextLine();
		System.out.print("내용 : ");
		sub = scan.nextLine();
		System.out.print("아이디 : ");
		id = scan.next();
		System.out.print("비번 : ");
		pw = scan.next();
		//입력한 정보를 이용해서 게시글 객체를 생성
		Board tmpBd = new Board(title, sub, id, pw);
		// 생성한 게기슬 객체를 리스트에 추가
		bdList.add(tmpBd);
		printBar();
		// 알림 문구를 출력
		System.out.println(tmpBd.getNum()+"번 게시글이 추가되었습니다.");
	}
	/**
	 * 게시글 번호가 주어지면 게시글을 가져오는 메소드(아이디, 비번 확인해서)
	 * @param num
	 * @return
	 */
	public Board selectPost(int num) {
		//수정할 게시글 번호를 입력
		printBar();
				//입력한 게시글 번호를 이용하여 게시글 객체를 생성
		Board tmpBd = new Board(num);
		// 리스트에서 생성한 객체가 몇번지에 있는지 번지를 가져옴
		int index = bdList.indexOf(tmpBd);
		//일치하는 객체가 없으면(가져온 번지가 0보다 작으면) 알림문구 출력 후 종료
		if(index < 0) {
			printBar();
			System.out.println("등록되지 않거나 삭제된 게시글입니다.");
			return null;
		}
		// 아이디, 비번 입력 받음
		System.out.print("아이디 : ");
		String id = scan.next();
		System.out.print("비번 : ");
		String pw = scan.next();
		//가져온 객체의 아이디,비번과 입력받은 아이디,비번이 같은지 확인해서
		//같지 않으면 안내문구 출력 후 종료
		tmpBd = bdList.get(index);
		if(!tmpBd.checkWriter(id, pw)) {
			printBar();
			System.out.println("아이디 또는 비번이 잘못 됐습니다.");
			return null;
		}
		return tmpBd;
	}
	
	
	
	
}







