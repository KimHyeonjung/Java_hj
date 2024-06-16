package day13.homework;

import java.text.MessageFormat;
import java.util.Scanner;
import java.util.regex.Pattern;

public class PhoneEx01 {
	private static Scanner scan = new Scanner(System.in);
	private static String conFormat = "[{0}] 이름 : {1}	번호 : {2}";
	public static void main(String[] args) {
		/* 연락처 관리를 위한 프로그램을 만드세요.
		 * 메뉴
		 * 1. 연락처 추가  //이름은 중복되도 번호는 중복되면 안됨
		 * 2. 연락처 수정  //수정하는 번호가 이미 있으면 있다고 처리
		 * 3. 연락처 삭제 // 
		 * 4. 연락처 검색 // 엔터만 치면 전체 검색
		 * 5. 프로그램 종료
		 * 메뉴 선택 : 1
		 * -----------
		 * 이름 : 홍길동
		 * 번호 : 010-1231-2333
		 * -----------
		 * 등록이 완료됐습니다.
		 * 메뉴 선택 : 1
		 * -----------
		 * 이름 : 홍길동
		 * 번호 : 010-1234-2333
		 * -----------
		 * 등록이 완료됐습니다.
		 * 메뉴 선택 : 1
		 * -----------
		 * 이름 : 고길동
		 * 번호 : 010-1234-2333
		 * -----------
		 * 이미 등록된 번호입니다.         //번호는 중복되면 안됨
		 * 메뉴 선택 : 2
		 * 이름 : 홍
		 * 1. 홍길동 : 010-1231-2333
		 * 2. 홍길동 : 010-1234-2333
		 * 번호 선택 : 1
		 * 이름 : 홍길동
		 * 번호 : 010-1242-2345
		 * --------------------
		 * 메뉴 선택 : 3
		 * 이름 : 홍
		 * 1. 홍..
		 * 2. 홍..
		 * 삭제할 번호 선택 : 1
		 * 번호를 삭제했습니다.
		 */
		
		int menu = 0;
		final int MAX_CONTAC_LIST = 100;
		ContactInfo []contactList = new ContactInfo[MAX_CONTAC_LIST];
		int contactConut = 0;
		
		do {
			showMenu(); // 메뉴 목록 출력
			menu = inputMenu(); // 메뉴 입력
			switch (menu) {
			case 1 :
				boolean duNumCk = false;
				System.out.println("--------------------------");
				//연락처 입력
				ContactInfo conInfo = inputContact();
				if(conInfo != null) { // 정규표현식에 부합하면
					//
					duNumCk = duplicatedNumCheck(contactList, contactConut, conInfo.phoneNumber);
					if(!duNumCk) {
						contactList[contactConut++] = conInfo;
					}
				}
				// 등록 잘 되었나 확인차
				for(ContactInfo tmp : contactList) {
					if(tmp == null) {
						break;
					}
					tmp.showContactInfo();
				}
				
				System.out.println("--------------------------");
				break;
			case 2 :
				System.out.println("--------------------------");
				searchContact(contactList, contactConut);
				System.out.print("수정할 연락처 [번호]를 입력 : ");
				int updateNum = scan.nextInt();
				ContactInfo tmp = inputContact();
				contactList[updateNum].updateContact(tmp);
				
				// 수정 잘 되었나 확인차
				for(ContactInfo tmp1 : contactList) {
					if(tmp1 == null) {
						break;
					}
					tmp1.showContactInfo();
				}
				System.out.println("--------------------------");
				break;
			case 3 :
				System.out.println("--------------------------");
				System.out.println("삭제입니다.");
				System.out.println("--------------------------");
				break;
			case 4 :
				System.out.println("--------------------------");
				searchContact(contactList, contactConut);
				System.out.println("--------------------------");
				break;
			case 5 :
				System.out.println("--------------------------");
				System.out.println("종료입니다.");
				System.out.println("--------------------------");
				break;
			default :
				System.out.println("--------------------------");
				System.out.println("잘못입니다.");
				System.out.println("--------------------------");
				break;
			}
			
		} while(menu != 5);
	}
	/**기능 : 입력한 단어를 포함하고 있는 연락처 정보를 찾아서 출력해주는 메소드
	 * @param contactList
	 * @param contactConut
	 */
	public static void searchContact(ContactInfo[] contactList, int contactConut) {
		scan.nextLine();	
		System.out.print("검색 정보(이름 또는 번호) 입력 : ");
		String searchWord = scan.nextLine();
		if(searchWord.equals("")) {
			for(int i = 0; i < contactConut; i++) {
				String result = MessageFormat.format(conFormat, i, contactList[i].name, 
						contactList[i].phoneNumber);
				System.out.println(result);
			}
		} else {
			for(int i = 0; i < contactConut; i++) {
				if(contactList[i].name.contains(searchWord) || 
						contactList[i].phoneNumber.contains(searchWord)) {
					String result = MessageFormat.format(conFormat, i, contactList[i].name, 
							contactList[i].phoneNumber);
					System.out.println(result);
				}
			}
		}
	}
	/** 기능 : 메뉴 목록을 출력
	 */
	public static void showMenu() {
		System.out.println("메뉴 ───────────━");
		System.out.println("1. 연락처 추가　  │");
		System.out.println("2. 연락처 수정　  │");
		System.out.println("3. 연락처 삭제 　 │");
		System.out.println("4. 연락처 검색 　 │");
		System.out.println("5. 프로그램 종료  ┃");
		System.out.println(" ━───────────── ");
	}
	/** 기능 : 메뉴 입력
	 */
	public static int inputMenu() {
		System.out.print("메뉴 선택: ");
		int menu = scan.nextInt();		
		return menu;
	}
	/** 기능 : 연락처 정보를 입력받아 연락처 정보 객체로 돌려주는 메소드
	 * @return 번호가 정규표현식에 맞지 않으면 null, 맞으면 연락처 객체
	 */
	public static ContactInfo inputContact() {
		String name, phoneNumber;
		System.out.print("이름 : ");
		name = scan.next();
		System.out.print("번호 : ");
		phoneNumber = scan.next();
		phoneNumber = patternCheck(phoneNumber); // 입력된 번호가 올바른지 체크
		if(phoneNumber == null) {
			System.out.println("번호가 올바르지 않습니다.");
			return null;
		}
		else {
			return new ContactInfo(name, phoneNumber);
		}
		
	}
	/** 기능 : 입력한 폰 번호가 연락처에 이미 있는지 확인하는 메소드
	 * @param phoneNumber
	 * @return 있는지 없는지 boolean 값
	 */
	public static boolean duplicatedNumCheck(ContactInfo[] contactList, int contactCount, String phoneNumber) {
		for(int i=0 ; i < contactCount ; i++) {
			if(phoneNumber.equals(contactList[i].phoneNumber)) {
				System.out.println("존재하는 번호입니다.");
				return true;
			}
		}
		return false;
	}
	/** 기능 : 번호입력이 숫자로만 11자리가 맞는지 체크하는 메소드
	 * @param phoneNumber
	 * @return 번호 / 정규표현식에 맞지 않으면 null
	 */
	public static String patternCheck(String phoneNumber) {
		String regex ="\\d\\d\\d\\d\\d\\d\\d\\d\\d\\d\\d";
		if(Pattern.matches(regex, phoneNumber)) {
			return phoneNumber;
		}
		return null;
	}
	
	
}

class ContactInfo {
	String name, phoneNumber;

	public ContactInfo(String name, String phoneNumber) {
		this.name = name;
		this.phoneNumber = phoneNumber;
	}
	
	public void updateContact(ContactInfo contactList) {
		this.name = contactList.name;
		this.phoneNumber = contactList.phoneNumber;
	}
	
	public void showContactInfo() {
		System.out.println("이름: " + name + "  번호: " + phoneNumber);
	}
	
}






























