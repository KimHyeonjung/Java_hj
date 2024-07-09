package auction;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Item implements Serializable{
	
	private static final long serialVersionUID = 276242421431949884L;
	private String name;
	private int price;
	private Date time;
	private String bidder;
	
	@Override
	public String toString() {
		return "진행중인 경매 [물품명: " + name + ", 현재 입찰가: " + price + ", 종료시간: " + time + ", 입찰자: " + bidder + "]";
	}
	
	
}
