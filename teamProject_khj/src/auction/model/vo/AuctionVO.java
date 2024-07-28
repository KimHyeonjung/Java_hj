package auction.model.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AuctionVO {
	private int au_num;
	private String au_date;
	private String au_name;
	private int au_start_price;
	private int au_winning_bid;
	private String au_me_id;
	
	public AuctionVO(String au_name, int au_start_price, int au_winning_bid) {
		
	}
		
}
