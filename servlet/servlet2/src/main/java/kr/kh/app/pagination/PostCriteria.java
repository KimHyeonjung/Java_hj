package kr.kh.app.pagination;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostCriteria extends Criteria{
	
	private int co_num;
	private String type;
	
	public PostCriteria(int page, int perPageNum, int co_num, String search, String type) {
		this.page = page;
		this.search = search;
		this.type = type;
		this.co_num = co_num;
		this.perPageNum = perPageNum;
	}
}
