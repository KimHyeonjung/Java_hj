package day24;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Ex14 {

	public static void main(String[] args) {
		List<PointD> list = new ArrayList<PointD>();
		list.add(new PointD(1, 1));
		list.add(new PointD(1, 10));
		/* 리스트에 좌표를 저장하려고 했다
		 * 원인 : 올바른 객체가 아님. PointD 클래스의 객체가 필요
		 * 해결방법 : 올바르게 변경
		 * */
//		list.add("1,1");
		list.add(new PointD(1, 1));
//		list.add(1);
		list.add(new PointD(1, 0));
		
	}

}

class PointD{
	int x,y;
	public PointD(int x, int y) {
		this.x = x;
		this.y = y;
	}
}