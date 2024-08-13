package servlet1.model.vo;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommentVO {

	private int cm_num;
	private String cm_content;
	private int cm_report; 
	private String cm_me_id; 
	private int cm_po_num; 
	private int cm_ori_num;
	private Date cm_date;
	private int cm_state;
}
