package test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Food implements Serializable{

	private static final long serialVersionUID = -6384282872517934570L;

	private String name;
	private int kcal;
	
	
	public Food() {}
	
	public Food(String name, int kcal) {
		this.name = name;
		this.kcal = kcal;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getKcal() {
		return kcal;
	}

	public void setKcal(int kcal) {
		this.kcal = kcal;
	}

	@Override
	public String toString() {
		return "Food [name=" + name + ", kcal=" + kcal + "]";
	}
	
	/* fileSave() 메소드를 작성하시오. : public void fileSave(String fileName){}

	- 전달받은 fileName 으로 파일 객체를 생성한다.

	- 파일 객체와 연결하는 파일 출력스트림을 생성한다.

	- 파일에 Food 객체를 기록할 수 있는 객체 출력스트림을 추가한다.

	- 파일에 Food 객체 정보를 기록하고 스트림들을 닫는다.

	- Food 객체 샘플 : "사과", 20*/
	public void fileSave(String fileName) {
		File file = new File(fileName);
		Food food = new Food("사과", 20);
		try(FileOutputStream fos = new FileOutputStream(file);
				ObjectOutputStream oos = new ObjectOutputStream(fos)) {
			oos.writeObject(food);
			oos.flush();
		} catch (Exception e) {
		}
	}
}


