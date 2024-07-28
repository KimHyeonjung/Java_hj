package auction.main;

public class ServerMain {

	public static void main(String[] args) {
		Auctioneer auction = new Auctioneer();
		try {
			auction.start();
		} catch (InterruptedException e) {
			return;
		}
	}

}
