package day22;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Ex03 {

	public static void main(String[] args) {
		/* 리스트에 숫자들을 저장하고, 저장된 숫자들을 정렬하는 코드를 작성하세요. */
		List<Integer> list = new ArrayList<Integer>();
		list.add(5);
		list.add(3);
		list.add(10);
		list.add(1);
		System.out.println(list);
		Collections.sort(list);
		System.out.println(list);
		// 역순으로 정렬
//		Collections.reverse(list);// 요소들을 역순으로 배치
		Collections.sort(list, (o1, o2)->o2 - o1); // 람다식 이용
		System.out.println(list);
		
		
		
		
	}
	

}
