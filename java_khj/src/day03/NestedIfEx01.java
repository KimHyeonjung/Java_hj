package day03;

public class NestedIfEx01 {

	public static void main(String[] args) {
		/* 6의 배수를 중첩 if문을 이용하여 작성*/
		int num = 14;
		
		if(num % 6 == 0) {
			System.out.println("6의 배수입니다.");
		} else {
			System.out.println("6의 배수가 아닙니다.");
		}
		
		if(num % 2 == 0 && num % 3 == 0) {
			System.out.println("6의 배수입니다.");
		} else {
			System.out.println("6의 배수가 아닙니다.");
		}
		//위 코드를 중첩 if문을 이용한
		if(num % 2 == 0) {
			if(num % 3 == 0) {
			System.out.println("6의 배수입니다.");
			}else {
				System.out.println("6의 배수가 아닙니다.");
			}
		} else {
			System.out.println("6의 배수가 아닙니다.");
		}
		
	}

}
