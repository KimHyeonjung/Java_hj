package auction.controller;

import java.time.LocalTime;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Pattern;

import auction.main.PresentCondition;
import auction.model.vo.AuctionVO;
import auction.service.AuctionService;
import auction.service.AuctionServiceImp;

public class AuctionController {
	private Scanner scan;
	private AuctionService auctionService = new AuctionServiceImp();

	public AuctionController(Scanner scan) {
		this.scan = scan;
	}
	
	//경매기록에 날짜, 경매품명, 시작가를 저장한다.
	public void startAuction(PresentCondition presentCondition) {
		if(presentCondition == null) {
			return;
		}
		String au_name = presentCondition.getName();
		int au_start_price = presentCondition.getStartPrice();
		AuctionVO auction = new AuctionVO(au_name, au_start_price);
		if(auctionService.insertAuction(auction)) {
			System.out.println("[경매 시작]");
		} else {
			System.out.println("[기록 실패]");
		}
	}
}
