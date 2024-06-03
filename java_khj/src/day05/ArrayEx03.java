package day05;

public class ArrayEx03 {

	public static void main(String[] args) {
		/* 1~10사이의 랜덤한 수 3개를 저장하고 출력하는 코드를 작성하세요.
		 * 
		 */
		int min = 1, max = 10;
		int randomNum[] = new int[3];
		
		for(int i = 0 ; i < randomNum.length ; i++) {
			randomNum[i] = (int)(Math.random() * (max-min+1) + min);
		}
		
		for(int i = 0 ; i < randomNum.length ; i++) {
			System.out.print(randomNum[i] + " ");
		}
	}

}
