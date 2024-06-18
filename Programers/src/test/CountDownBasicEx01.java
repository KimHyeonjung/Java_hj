package test;

import java.util.Arrays;

public class CountDownBasicEx01 {

	public static void main(String[] args) {
		int [] arr;
		arr = solution(10, 3);
		System.out.println(Arrays.toString(arr));
	}
	
	public static int[] solution(int start, int end_num) {
        int[] answer = new int[start-end_num+1];
        int start_num = start;
        for(int i = 0; i <= (start-end_num); i++) {
            answer[i] = start_num;
            start_num--;
        }
        return answer;
    }

}
