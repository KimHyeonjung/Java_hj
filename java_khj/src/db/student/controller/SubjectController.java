package db.student.controller;

import java.util.ArrayList;
import java.util.Scanner;

import db.student.model.vo.SubjectVO;
import db.student.service.SubjectService;
import db.student.service.SubjectServiceImp;

public class SubjectController {
	
	private Scanner scan = new Scanner(System.in);
	private SubjectService subjectService = new SubjectServiceImp();
	
	public SubjectController(Scanner scan) {
		this.scan = scan;
	}

	public void insertSubject() {
		//과목명을 입력
		System.out.print("추가할 과목명 입력 : ");
		scan.nextLine();
		String subject = scan.nextLine();
		
		if(subjectService.insertSubject(subject)) {
			System.out.println("과목을 추가했습니다.");
		} else {
			System.out.println("이미 등록된 과목입니다.");
		}
		/*
		//과목 리스트에 등록된 과목인지 확인해서 등록되었으면 안내문구 출력 후 종료
		if(subjectList.contains(subject)) {
			System.out.println("이미 등록된 과목입니다.");
			return;
		}
		//과목 리스트에 과목을 추가
		subjectList.add(subject);
		System.out.println("과목을 추가했습니다.");
		*/
	}

	public void updateSubject() {
		//수정할 과목을 입력
		System.out.print("수정할 기존 과목명 입력 : ");
		scan.nextLine();
		String subject = scan.nextLine();
		
		
		
		//새 과목명 입력
		System.out.print("새 과목명 입력 : ");
		String newSubject = scan.nextLine();
		
		if(subjectService.updateSubject(subject, newSubject)) {
			System.out.println("과목명이 수정되었습니다.");
		} else {
			System.out.println("입력한 과목이 없거나 수정하려는 새 과목이 이미 있는 과목이어서 수정 실패");
		}

		
	}

	public void deleteSubject() {
		//삭제할 과목명을 입력
		System.out.print("삭제할 과목명 입력 : ");
		scan.nextLine();
		String subject = scan.nextLine();
		
		if(subjectService.deleteSubject(subject)) {
			System.out.println("과목을 삭제했습니다.");
		} else {
			System.out.println("일치하는 과목이 없습니다.");
		}
	}

	public void searchSubject() {
		
		ArrayList<SubjectVO> list = subjectService.selectSubjectNameList();
		if(list.size() == 0) {
			System.out.println("등록된 과목이 없습니다.");
			return;
		}
		for(SubjectVO subject : list) {
			System.out.println(subject.getSu_name());
		}
		
	}
}
