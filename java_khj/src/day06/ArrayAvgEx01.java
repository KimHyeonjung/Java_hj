package day06;

import java.util.Arrays;

public class ArrayAvgEx01 {

	public static void main(String[] args) {
		/*
		 * 97,91,	01,	96,	89,	00,	97,	98,	99,	95,	02,	91,	99,	91,	99,	87,	98,	99,	98,	96,	92,	95,	00,	99,	95,	96,	02,	97 
		*/
		int [] yearOfBirth =  {97, 91, 01, 96, 89,	00,	97,	98,	99,	95,	02,	91,	99,	91,	99,	87,	98,	99,	98,	96,	92,	95,	00,	99,	95,	96,	02,	97};
		int [] arrAge = new int[yearOfBirth.length];
		int age = 0, sum = 0;		
		
		for(int i = 0; i < yearOfBirth.length; i++) {
			if(yearOfBirth[i] < 10) {
				arrAge[i] =  2000 + yearOfBirth[i];
			}
			else if(yearOfBirth[i] > 10) {
				arrAge[i] = 1900 + yearOfBirth[i];			}
		}
		for(int tmp : arrAge) {
			sum += (2024-tmp);
		}
		age = (sum / arrAge.length);
		System.out.println(age);
		
		
		Arrays.sort(arrAge);
		//System.out.println(Arrays.toString(arrAge));
	}

}
