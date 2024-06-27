package student;

import java.util.Objects;

import lombok.Data;

@Data
public class Subject {

	private String subName;
	private int grade;
	private int semester;
	private int midterm;
	private int finals;
	private int performance;
	
	
	//한 학생에게 같은 과목명, 학년, 학기 성적이 여러개 있을 수 없기 때문에
	//equals를 과목명, 학년, 학기를 이용하여 오버라이딩
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Subject other = (Subject) obj;
		return grade == other.grade && semester == other.semester && Objects.equals(subName, other.subName);
	}
	@Override
	public int hashCode() {
		return Objects.hash(grade, semester, subName);
	}
	public Subject(String subName, int grade, int semester) {
		super();
		this.subName = subName;
		this.grade = grade;
		this.semester = semester;
	}
	
	
	
	
}
