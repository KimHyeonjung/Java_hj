package day24;

import java.io.FileWriter;

public class Ex17 {

	public static void main(String[] args) {
		try(FileWriter fw = new FileWriter("src/day24/ex17.txt", true)) {//이어쓰기 할때는 , true
			fw.write('a');
			fw.write('b');
			fw.write('c');
			fw.flush();
			
		} catch (Exception e) {
		}
		
	}

}
