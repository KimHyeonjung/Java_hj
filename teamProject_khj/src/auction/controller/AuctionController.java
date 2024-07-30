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
//	경매품명, 시작가, 입찰 유효 시간, 인상액 을 입력하여 등록하는 기능
	public PresentCondition insertItem() {
		try {
			String regexItemName = getRegex("itemName");
			
			System.out.print("경매품명 입력 > ");
			scan.nextLine();
			String name = scan.nextLine();
			if(!Pattern.matches(regexItemName, name)) {
				System.out.println("[20자 초과]");
				return null;
			}
			System.out.print("시작가 입력 > ");
			int startPrice;
			while((startPrice = scan.nextInt()) < 0) {
				System.out.println("[음수 입력]");
				System.out.print("시작가 입력 > ");
				startPrice = scan.nextInt();
			}
			System.out.print("유효시간 입력(분) > ");
			int validityPeriod = scan.nextInt();
			while(validityPeriod < 1 || validityPeriod > 120) {
				System.out.println("[1~120분]");
				System.out.print("유효시간 입력(분) > ");
				validityPeriod = scan.nextInt();
			}
			System.out.print("인상액 입력 > ");
			int increment = scan.nextInt();
			while(increment < 100 || increment > 1000000) {
				System.out.println("[100~1,000,000]");
				System.out.print("인상액 > ");
				increment = scan.nextInt();
			}
			//현재 시간에 입력한 유효시간을 더함
			LocalTime endTime = LocalTime.now().plusMinutes(validityPeriod);
			int highestBid = startPrice;
			//경매현황에는 경매품명, 시작가, 최고입찰가, 종료시간, 입찰 가능액 있다
			PresentCondition presentCondition = new PresentCondition(name, startPrice, highestBid, endTime, increment);
			System.out.println("-[등록완료]-");
			return presentCondition;
			//경매기록에는 날짜, 경매품명, 시작가, 낙찰가, 낙찰자 아이디가 있다.
		} catch (InputMismatchException e) {
			System.out.println("[입력이 올바르지 않음]");
			scan.nextLine();
			return null;
		}
	}
	
	//정규표현식들
	private String getRegex(String regex) {
		if(regex.equals("itemName")) {
			return "^[a-zA-Z0-9가-힣]{1,20}$";
		}
//		if(regex.equals("validityPeriod")) {
//			return "";
//		}
//		if(regex.equals("increment")) {
//			return "";
//		}
		return null;
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
