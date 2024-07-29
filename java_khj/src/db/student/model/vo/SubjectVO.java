package db.student.model.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SubjectVO{
	
	private int su_key; //과목 기본키
	private int su_grade;
	private int su_semester;
	private String su_name;
	
	public SubjectVO(String subName, int grade, int semester) {
		this.su_name = subName;
		this.su_grade = grade;
		this.su_semester = semester;
	}
	
}
