package kr.kh.spring3.model.vo;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostVO {
	private int po_num;
	private String po_title; 
	private String po_sub; 
	private int po_views; 
	private int po_report; 
	private int po_co_num; 
	private String po_me_id; 
	private Date po_date;
}
