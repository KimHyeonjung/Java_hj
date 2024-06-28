package student;

import java.io.Serializable;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor	
public class Subject implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
	@Override
	public String toString() {
		return subName + "  " + grade + "학년 " + semester + "학기  중간:" + midterm
				+ ", 기말:" + finals + ", 수행평가:" + performance + "  총점:" + getTotal();
	}
	private double getTotal() {
		return midterm * 0.4 + finals * 0.5 + performance * 0.1;
	}
	public void update(int midterm, int finals, int performance) {
		this.midterm = midterm;
		this.finals = finals;
		this.performance = performance;
	}
	
	
	
	
}
