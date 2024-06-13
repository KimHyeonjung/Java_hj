package test;

import java.util.Scanner;

public class Solution01 {

	public static void main(String[] args) {
		//영어 알파벳으로 이루어진 문자열 str이 주어집니다. 
		//각 알파벳을 대문자는 소문자로 소문자는 대문자로 변환해서 출력하는 코드를 작성해 보세요.
//	    Scanner sc = new Scanner(System.in);
//        String str = sc.next();
//        String str2 = "";
//        
//        for(int i = 0; i < str.length(); i++) {
//            if(Character.isUpperCase(str.charAt(i))) {
//                str2 += Character.toLowerCase(str.charAt(i));
//            }
//            else {
//                str2 += Character.toUpperCase(str.charAt(i));
//            }
//        }
//        System.out.println(str2);
        
        Scanner sc = new Scanner(System.in);
        String a = sc.next();
        String answer = "";

        //Stack <Character> stack = new Stack <> ();

        for(Character c : a.toCharArray()){
            if(Character.isUpperCase(c)){
                //stack.push(Character.toLowerCase(c));
                answer += Character.toLowerCase(c);
            }
            else if(Character.isLowerCase(c)){
                //stack.push(Character.toUpperCase(c));
                answer += Character.toUpperCase(c);
            }
        } 
        System.out.println(answer);
        
	}

}
