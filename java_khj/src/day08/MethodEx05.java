package day08;

public class MethodEx05 {

	public static void main(String[] args) {
		/* 정수 배열이 주어지고 주어진 배열에 원하는 정수가 있는지 없는지 확인하는 코드를 작성하세요
		 * 단 메서드를 이용하여*/
		int [] arr = new int[] {1,2,3,4,5,6,7,8,9};
		String str = "abc";
		System.out.println(str.compareTo("a"));
		int num = 10;
		if(contains(arr, num)) {
			System.out.println("arr 배열에는 " + num + "가 있습니다.");
		}
		else {
			System.out.println("arr 배열에는 " + num + "가 없습니다.");
		}
	}
	
	/* 기능 : 정수배열이 주어지면 배열에 정수 num이 있는지 없는지 알려주는 메서드
	 * 매개변수 : 정수배열 arr과 정수 num => int []arr, int num
	 * 리턴타입 : 있는지 없는지 => boolean
	 * 메서드명 : contains
	 * */
	public static boolean contains(int [] arr, int num) {
		if(arr == null) {
//			System.out.println("배열 null");
			return false;
		}
		for(int tmp : arr) {
			if(tmp == num) {
				return true;
			}
		}
		return false;
	}

}
