package db.student.service;

import java.util.ArrayList;
import java.util.List;

import db.student.model.vo.SubjectVO;

public interface SubjectService {

	boolean insertSubject(String subject);

	boolean updateSubject(String subject, String newSubject);

	boolean deleteSubject(String subject);

	ArrayList<SubjectVO> selectSubjectNameList();
}
