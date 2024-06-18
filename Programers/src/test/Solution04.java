package test;

public class Solution04 {

	public static void main(String[] args) {
		Solution sol = new Solution();
		sol.solution(9, 2, 1, 3);
	}

}
/* 첫 번째 분수의 분자와 분모를 뜻하는 numer1, denom1, 두 번째 분수의 분자와 분모를 뜻하는 numer2, denom2가 매개변수로 주어집니다. 
 * 두 분수를 더한 값을 기약 분수로 나타냈을 때 분자와 분모를 순서대로 담은 배열을 return 하도록 solution 함수를 완성해보세요.
 * 	1	2	3	4	[5, 4]
	9	2	1	3	[29, 6]
	1/2 3/4 =>	1 3
				2 4
 */
class Solution {
    public int[] solution(int numer1, int denom1, int numer2, int denom2) {
    	int[] answer = new int[2];
        int numerSum, denomSum;
        numerSum = (numer1 * denom2) + (numer2 * denom1);
        denomSum = (denom1 * denom2) + (denom2 * denom1);
        
        int i = 1;
		int gcd = 0;
		while(i <= numerSum) {
			if(numerSum % i == 0 && denomSum % i == 0) {				
				gcd = i;
			}
			i++;
		}	
		System.out.println(gcd);
    
    	return answer;
    }
}