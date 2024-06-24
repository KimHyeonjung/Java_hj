package day19.board;

import java.io.Serializable;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class Board implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -9201392094285282056L;
	//제목, 내용, 작성자, 비번
	private String title;
	private String sub;
	private String id;
	private String pw;
	private int view;
	//게시글 번호
	private int num;
	
	//게시글 번호를 생성할 때 활용
	private static int count = 0;

	//이 생성자를 이용할 때만 게시글 번호를 1증가 하도록 함
	public Board(String title, String sub, String id, String pw) {
		this.title = title;
		this.sub = sub;
		this.id = id;
		this.pw = pw;
		this.num = ++count;
		
	}
	
	//번호만 있는 생성자. 수정, 삭제에서 번호를 선택했을 때 리스트에 있는 게시글을 쉽게 가져오기 위해서
		public Board(int num) {
			super();
			this.num = num;
		}
	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Board other = (Board) obj;
		return num == other.num;
	}
	
	
	
	//번호가 같아야 같은 객체로 판별
	@Override
	public int hashCode() {
		return Objects.hash(num);
	}
	
	@Override
	public String toString() {
		return num + "." + title + " / " + id + " /  [" + view + "]";
	}
	

	//제목과 내용을 수정하는 메소드
	public void update(String title, String sub) {
		this.title = title;
		this.sub = sub;
	}

	//조회수 1증가하고 게시글 상세 조회 하는 메소드
	public void print() {
		System.out.println("제목 	: " + title);
		System.out.println("작성자 	: " + id);
		System.out.println("내용 	: " + sub);
		System.out.println("조회수 	: " + ++view);
	}
	
	//아이디, 비번이 주어지면 게시글의 아이디 비번과 같은지 확인하는 메소드
	public boolean checkWriter(String id, String pw) {
		return this.id.equals(id) && this.pw.equals(pw);
	}
	
}

