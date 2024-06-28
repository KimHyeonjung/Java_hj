package student;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class Student implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//- 학년, 반, 번호, 이름
	private int grade;
	private int classNum;
	private int num;
	private String name;
	//- 각 과목의 점수
	private List<Subject> subjectList = new ArrayList<Subject>();
	@Override
	public int hashCode() {
		return Objects.hash(classNum, grade, num);
	}
	
	//같은 학교에 특정년도에 같은 학년, 반, 번호를 가진 학생은 1명이기 때문에 
	//equals를 오버라이딩
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		return classNum == other.classNum && grade == other.grade && num == other.num;
	}

	@Override
	public String toString() {
		return grade + "학년 " + classNum + "반 " + num + "번 " + name
				+ ", " + subjectList;
	}

	public Student(int grade, int classNum, int num, String name) {
		this.grade = grade;
		this.classNum = classNum;
		this.num = num;
		this.name = name;
	}

	public void print() {
		System.out.println(grade + "학년 " + classNum + "반 " + num + "번 " + name);
		System.out.println("[과목 성적]");
		if(subjectList.size() == 0) {
			System.out.println("등록된 과목이 없습니다.");
			return;
		}
		for(Subject tmp : subjectList) {
			System.out.println(tmp);
		}
	}
	
	public void update(Student std) {
		this.grade = std.grade;
		this.classNum = std.classNum;
		this.num = std.num;
		this.name = std.name;
	}
	
	
	
	
}
