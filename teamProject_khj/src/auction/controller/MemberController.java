package auction.controller;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

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
	// 회원 정보 입력하는 기능
	private MemberVO inputMemberInfo() {
		System.out.print("아이디 > ");
		String id = scan.next();
		if(!Pattern.matches(getRegex("id"), id)) {
			System.out.println("[영문+숫자 3~12자 첫글자는 영문만 가능]");
			return null;
		}
		System.out.print("비밀번호 > ");
		String password = scan.next();
		if(!Pattern.matches(getRegex("password"), password)) {
			System.out.println("[영문+숫자 4~14자 첫글자는 영문만 가능]");
			return null;
		}
		System.out.print("이름 > ");
		String name = scan.next();
		if(!Pattern.matches(getRegex("name"), name)) {
			System.out.println("[영문 2~10자, 한글 2~5자]");
			return null;
		}
		System.out.print("주소 > ");
		scan.nextLine();
		String address = scan.nextLine();
		if(!Pattern.matches(getRegex("address"), address)) {
			System.out.println("[최대 35자까지만 가능]");
			return null;
		}
		System.out.print("전화번호 > ");
		String contact = scan.next();;
		if(!Pattern.matches(getRegex("contact"), contact)) {
			System.out.println("[전화번호 형식이 잘못됨]");
			return null;
		}
		return new MemberVO(id, password, name, address, contact);
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
		ArrayList<MemberVO> searchList = memberService.searchMemberList(searchMemberInfo);
		if(searchList == null) {
			System.out.println("[검색 실패 : 일치하는 회원 정보 없음]");
			return;
		}
		for(MemberVO member : searchList) {
			System.out.println(member);
		}
	}
	
	// 로그인을 하기 위한 기능
		public MemberVO logIn(){
			// 로그인
			System.out.print("아이디 > ");
			String id = scan.next();
			System.out.print("비밀번호 > ");
			String password = scan.next();

			MemberVO user = memberService.logIn(id, password);
			if (user != null) {
				System.out.println("[로그인 성공]");
				return user;
			} else {
				System.out.println("[로그인 실패: 아이디 또는 비밀번호가 잘못되었습니다.]");
				return null;
			}
		}
		// 회원 가입 요청 하는 기능
		public MemberVO register() {
			MemberVO member = inputMemberInfo();
			if(memberService.insertMember(member)) {
				System.out.println("[회원 가입 성공]");
				return member;
			} else {
				System.out.println("[가입 실패 : 등록된 아이디]");
				return null;
			}
		}
		
		//정규표현식 모음
		private String getRegex(String regex) {
			if(regex.equals("id")) {
				return "^[a-zA-Z][a-zA-Z0-9]{2,11}$";
			}
			if(regex.equals("password")) {
				return "^[a-zA-Z][a-zA-Z0-9]{3,13}$\"";
			}
			if(regex.equals("name")) {
				return "^([가-힣]{2,5}|[a-zA-Z]{2,10})$";
			}
			if(regex.equals("address")) {
				return "^[a-zA-Z0-9가-힣]{1,35}$";
			}
			if(regex.equals("contact")) {
				return "^[0-9]{3}-[0-9]{3,4}-[0-9]{4}$";
			}
			return null;
		}
		public String getFormatWon(int price) {
			DecimalFormat format = new DecimalFormat("###,###,###,###");
			return format.format(price);
		}
		public String getFormatWon(String price) {
			int priceInt = Integer.parseInt(price);
			DecimalFormat format = new DecimalFormat("###,###,###,###");
			return format.format(priceInt);
		}
}
