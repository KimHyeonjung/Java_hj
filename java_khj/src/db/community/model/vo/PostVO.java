package db.community.model.vo;

import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostVO {
	private int po_num ;
	private String po_title; 
	private String  po_sub;
	private int po_views; 
	private int po_report; 
	private int po_co_num;
	private String po_me_id;
	private Date po_date;
	
	public PostVO(int coNum, String title, String content, String me_id) {
		this.po_co_num = coNum;
		this.po_title = title;
		this.po_sub = content;
		this.po_me_id = me_id;
	}
	public PostVO(int po_num, String title, String content) {
		this.po_num = po_num;
		this.po_title = title;
		this.po_sub = content;
	}
	
	@Override
	public String toString() {
		return "게시글 번호:" + po_num + " | 제목:" + po_title + " | 작성자:" + po_me_id + " | 날짜:" 
	+ getPo_date() + " | 조회수:" + po_views + "\n------------------------------------------------\n";
	}
	
	public void print() {
		System.out.println("------------------------------");
		System.out.println("번호 : " + po_num);
		System.out.println("제목 : " + po_title);
		System.out.println("작성자 : " + po_me_id);
		System.out.println("작성일 : " + getPo_date());
		System.out.println("조회수 : " + po_views);
		System.out.println("내용 : " + po_sub);
		System.out.println("------------------------------");
	}
	
	public String getPo_date() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		return format.format(po_date);
	}
	
}
