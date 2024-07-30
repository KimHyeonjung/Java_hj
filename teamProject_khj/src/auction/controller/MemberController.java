package auction.controller;

import java.util.ArrayList;
import java.util.Scanner;

import auction.model.vo.MemberVO;
import auction.service.MemberService;
import auction.service.MemberServiceImp;

public class MemberController {	
	private Scanner scan;
	private MemberService memberService = new MemberServiceImp();

	public MemberController(Scanner scan) {
		this.scan = scan;
	}
	// 회원 추가 : 클라이언트 요청 ------------------
	public boolean insertMember(MemberVO member) {
		if(memberService.insertMember(member)) {
			System.out.println("[회원 추가 완료]");
			return true;
		} else {
			System.out.println("[추가 실패 : 등록된 아이디]");
			return false;
		}
	}
	// 회원 추가 : 서버 
	public void insertMember() {

		MemberVO member = inputMemberInfo();

		if(memberService.insertMember(member)) {
			System.out.println("[회원 추가 완료]");
		} else {
			System.out.println("[추가 실패 : 등록된 아이디]");
		}

	}
	private MemberVO inputMemberInfo() {
		System.out.print("아이디 입력 > ");
		scan.nextLine();
		String me_id = scan.nextLine();
		System.out.print("비밀번호 입력 > ");
		String me_password = scan.nextLine();
		System.out.print("이름 입력 > ");
		String me_name = scan.nextLine();
		System.out.print("주소 입력 > ");
		String me_address = scan.nextLine();
		System.out.print("연락처 입력 > ");
		String me_contact = scan.nextLine();
		return new MemberVO(me_id, me_password, me_name, me_address, me_contact);
	} // -----------------------------------------------------------------------
	
	// 회원 수정 ------------------------------------------------------------------
	public void updateMember() {
		//수정하려는 회원 아이디 입력
		System.out.println("수정할 회원 아이디 입력 >");
		scan.nextLine();
		String memberId = scan.nextLine();
		//수정하려는 학생이 있는지 없는지 확인
		if(!memberService.exists(memberId)) {
			System.out.println("[등록되지 않은 아이디]");
			return;
		} 
		MemberVO newMember = inputUpdateMember();

		if(memberService.updateMember(memberId, newMember)) {
			System.out.println("[회원정보 수정]");
			return;
		}
		System.out.println("[수정 실패]");		
	}
	private MemberVO inputUpdateMember() {
		System.out.print("새 비밀번호 입력 > ");
		String me_password = scan.nextLine();
		System.out.print("새 이름 입력 > ");
		String me_name = scan.nextLine();
		System.out.print("새 주소 입력 > ");
		String me_address = scan.nextLine();
		System.out.print("새 연락처 입력 > ");
		String me_contact = scan.nextLine();
		return new MemberVO(me_password, me_name, me_address, me_contact);		
	} // -----------------------------------------------------------------------
	public void deleteMember() {
		System.out.println("삭제할 회원 아이디 입력 >");
		scan.nextLine();
		String memberId = scan.nextLine();
		if(memberService.deleteMember(memberId)) {
			System.out.println("[회원 삭제 완료]");
			return;
		}
		System.out.println("[삭제 실패 : 등록되지 않은 아이디]");
	}
	public void searchMember() {
		//찾으려는 회원 정보 입력
		System.out.println("검색할 회원 정보 입력 >");
		scan.nextLine();
		String searchMemberInfo = scan.nextLine();
		//수정하려는 학생이 있는지 없는지 확인
		ArrayList<MemberVO> searchList = memberService.searchMember(searchMemberInfo);
		if(searchList == null) {
			System.out.println("[검색 실패 : 일치하는 회원 정보 없음]");
			return;
		}
		for(MemberVO member : searchList) {
			System.out.println(member);
		}
	}
	public boolean checkIdPw(String id, String password) {
		if(!memberService.exists(id)) {
			System.out.println("[등록되지 않은 아이디로 로그인 시도]");
			return false;
		} 
		return memberService.checkIdPw(id, password);
	}
	

}
