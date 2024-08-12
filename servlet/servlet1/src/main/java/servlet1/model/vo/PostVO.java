package servlet1.model.vo;

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

	public PostVO(int co_num, String title, String content, String id) {
		this.po_co_num = co_num;
		this.po_title =title;
		this.po_sub = content;
		this.po_me_id = id;
	}
}
