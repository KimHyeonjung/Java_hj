package day03;

public class SwitchExam01 {

	public static void main(String[] args) {
		//switch문을 이용한 홀 짝 예제
		/* switch(변수) {
		 * case 값1:		// if(변수 == 값1)
		 * 		실행문1;
		 * 		break;
		 * case 값2:		// else if(변수 == 값2)
		 * 		실행문2;
		 * 		break;
		 * default :	// else		
		 * 		실행문3;
		 * */
		int num =2;
		
		switch(num % 2) {
		case 0:
			System.out.println(num + "는 짝수");
			break;
		default :
			System.out.println(num + "는 홀수");
		}
	}

}
