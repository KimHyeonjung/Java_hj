package servlet1.model.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RecommendVO {

	private int re_num;
	private int re_state;
	private int re_po_num;
	private String re_me_id;
	
	public RecommendVO(int po_num, int state, String me_id) {
	
		this.re_po_num = po_num;
		this.re_state = state;
		this.re_me_id = me_id;
	}
}