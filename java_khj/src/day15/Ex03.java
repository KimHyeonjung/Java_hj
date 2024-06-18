package day15;

import java.util.HashSet;

public class Ex03 {

	public static void main(String[] args) {
		/* 1~10사이의 랜덤한 수 6개를 중복되지 않게 생성해서 저장하고 출력하는 코드를 작성하세요
		 * set을 활용
		 * size() 활용*/
		
		int min = 1, max = 45;
		int ranNum;
		HashSet<Integer> ranSet = new HashSet<Integer>();
		
		while(ranSet.size() <6) {
			ranNum = (int)(Math.random() * (max - min + 1) + min);
			/* 위와 동일한 랜덤한 수 생성 코드
			 * Random random = new Random();
			 * int ranNum = random.nextInt(min, max+1); 
			 * */
			ranSet.add(ranNum);
		}
		
		System.out.println(ranSet);
		
	}

}
