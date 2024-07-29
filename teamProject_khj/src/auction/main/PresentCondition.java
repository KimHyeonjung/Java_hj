package auction.main;

import java.text.DecimalFormat;
import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PresentCondition{
	//경매현황에는 경매품명, 시작가, 최고입찰가, 종료시간, 입찰 가능액 있다
	private String name;
	private int startPrice;
	private int highestBid;
	private LocalTime endTime;
	private int possibleAmount;
	
	public String getStartPriceWon() {
		DecimalFormat format = new DecimalFormat("\u00A4 ###,###,###");
		return format.format(startPrice);
	}
	public String getHighestBidWon() {
		DecimalFormat format = new DecimalFormat("\u00A4 ###,###,###");
		return format.format(highestBid);
	}
	public String getPossibleAmountWon() {
		DecimalFormat format = new DecimalFormat("\u00A4 ###,###,###");
		return format.format(possibleAmount);
	}
	
	@Override
	public String toString() {
		return "진행중인 경매 [경매품: " + name + "] [시작가: " + startPrice + "] [최고입찰가: " 
	+ highestBid + "] [종료시간: " + endTime + "] [입찰 가능액: " + possibleAmount + "]";
	}
	
//	public void updateBid(String id, int price) {
//		this.bidder = id;
//		this.price = price;
//	}
	
}
