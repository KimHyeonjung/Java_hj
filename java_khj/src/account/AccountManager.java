package account;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import program.Program;

public class AccountManager implements Program{

	private Scanner scan = new Scanner(System.in);
	private List<AccountBook> list = new ArrayList<AccountBook>();
	private List<String> incomeCategory = Arrays.asList("월급", "용돈", "부수입");
	private List<String> expenseCategory = Arrays.asList("교통비", "식비", "쇼핑", "자기관리", "월세", "통신비", "보험");
	private String fileName = "src/account/accountBook.txt";

	@Override
	public void runMenu(int menu) throws Exception {
		switch (menu) {
		case 1 : 
			insertAccount();
			break;
		case 2 :
			updateAccount();
			break;
		case 3 : 
			deleteAccount();
			break;
		case 4 :
			searchAccount();
			break;
		case 5 :
			exit();
			break;
		default : 
			System.out.println("잘못된 메뉴를 입력했습니다.");
		}
	}
	private String printCategory() {
		String ck = null;
		do {
			System.out.print("수입/지출 :");
			ck = scan.next();
			if(ck.equals("수입")) {
				System.out.println(incomeCategory);
				return ck;
			}
			else if(ck.equals("지출")) {
				System.out.println(expenseCategory);
				return ck;
			} else {
				printbar();
				System.out.println("'수입' 또는 '지출'로 입력해주세요");
				ck = null;
			}
		}while(ck == null);
		return null;
	}

	private String selectCategory(String inExStatus) {
		String category = scan.next();
		if(inExStatus.equals("수입")) {
			if(incomeCategory.contains(category)) {
				return category;
			}else {
				printbar();
				System.out.print("수입 ");
			}
		}
		if(inExStatus.equals("지출")) {
			if(expenseCategory.contains(category)) {
				return category;
			}else {
				printbar();
				System.out.print("지출 ");
			}
		}
		return null;
	}

	private String inputDate() {
		String regex = "^[\\d]{4} (0[1-9]|1[12]) (0[1-9]|[12][0-9]|3[01])$";
		String date = null;
		scan.nextLine();
		do {
			System.out.print("날짜(yyyy MM dd) :"); //날짜 입력 포맷 제한
			date = scan.nextLine();
			if(!Pattern.matches(regex, date)) {
				System.out.println("날짜 형태가 잘못되었습니다.");
				date = null;
			}
		}while(date == null);
		return date;
	}
	private AccountBook inputAccount() throws ParseException {
		String date = inputDate();
		String inExStatus = printCategory();
		if(inExStatus == null) {
			printbar();
			System.out.println("분류 입력이 잘못되었습니다.");
			return null;
		}
		String category = null;
		do {
			System.out.print("분류 :"); 
			category = selectCategory(inExStatus);
			if(category == null) {
				System.out.println("목록에 없는 분류입니다."); 
			}
		}while(category == null);
		System.out.print("금액 :"); 
		int amount = scan.nextInt();
		System.out.print("내용 :"); 
		scan.nextLine();
		String detail = scan.nextLine();
		return new AccountBook(date, inExStatus, category, detail, amount);
	}
	
	private int printSearchAccount(AccountBook ab) {
		int count = -1;
		for(int i=0; i < list.size(); i++) {
			if(list.get(i).getDate().equals(ab.getDate())) {
				System.out.println("[" + (i+1) + "]  " + list.get(i));
				count = i;
			}
		}
		return count;
	}

	private void insertAccount() throws ParseException {
		printbar();
		AccountBook ab = inputAccount();
		if(ab == null) {
			printbar();
			System.out.println("내역 입력이 잘못되었습니다.");
			return;
		}
		if(list.contains(ab)) {
			printbar();
			System.out.println("동일한 내역이 있습니다.");
			return;
		}
		list.add(ab);
		Collections.sort(list);
		printbar();
		System.out.println("추가 완료!");
	}

	private void updateAccount() throws ParseException {
		printbar();
		//삭제할 날짜 입력
		String date = inputDate();
		//객체로 생성
		AccountBook ab = new AccountBook(date, "", "", "", 0);
		//가게부에 작성한 내역이 없으면 아내후 종료
		if(list.size() < 0) {
			printbar();
			System.out.println("등록된 내역이 없습니다.");
			return;
		}
		//해당 날짜와 일치하는 내역들 출력하고
		int count = printSearchAccount(ab);
		if(count == -1) {
			printbar();
			System.out.println("해당 날짜에 등록된 내역이 없습니다.");
			return;
		}
		System.out.print("내역 [번호] 선택 : ");
		int index = scan.nextInt() - 1;
		if(index < 0 || index > count || !list.get(index).getDate().equals(ab.getDate())) {
			printbar();
			System.out.println("잘못된 [번호]를 선택했습니다.");
			return;
		}
		ab = inputAccount();
		if(list.contains(ab)) {
			printbar();
			System.out.println("동일한 내역이 있습니다.");
			return;
		}
		list.remove(index);
		list.add(ab);
		Collections.sort(list);
	}
	//	private Date stringToDate(String date) throws ParseException {
	//		SimpleDateFormat format = new SimpleDateFormat("yyyy MM dd");
	//		return format.parse(date);
	//	}

	private void deleteAccount() throws ParseException {
		//삭제할 날짜 입력
		String date = inputDate();
		//객체로 생성
		AccountBook ab = new AccountBook(date, "", "", "", 0);
		//가게부에 작성한 내역이 없으면 아내후 종료
		if(list.size() < 0) {
			printbar();
			System.out.println("등록된 내역이 없습니다.");
			return;
		}
		//해당 날짜와 일치하는 내역들 출력
		int count = printSearchAccount(ab);
		if(count == -1) {
			printbar();
			System.out.println("해당 날짜에 등록된 내역이 없습니다.");
			return;
		}
		System.out.print("내역 [번호] 선택 : ");
		int index = scan.nextInt() - 1;
		if(index < 0 || index > count || !list.get(index).getDate().equals(ab.getDate())) {
			printbar();
			System.out.println("잘못된 [번호]를 선택했습니다.");
			return;
		}
		list.remove(index);
		Collections.sort(list);
		printbar();
		System.out.println("삭제 완료!");
	}

	private void searchAccount() {
		printbar();
		for(AccountBook ab : list) {
			System.out.println(ab);
		}

	}

	private void exit() {
		System.out.println("프로그램을 종료합니다.");
	}

	@Override
	public void save(String fileName) {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))){
			oos.writeObject(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void load(String fileName) {
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))){
			list = (List<AccountBook>)ois.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void printbar() {
		System.out.println("-------------------------------");
	}

	@Override
	public void printMenu() {
		printbar();
		System.out.println("메뉴\r\n"
				+ "1. 내역 입력\r\n"
				+ "2. 내역 수정\r\n"
				+ "3. 내역 삭제\r\n"
				+ "4. 내역 조회\r\n"
				+ "5. 종료\r\n"
				+ "메뉴 선택 :");
	}
	@Override
	public void run() {
		load(fileName);
		int menu = 1;
		do {
			printMenu();
			menu = nextInt();
			try {
				runMenu(menu);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}while(menu != 5);
		save(fileName);
	}

	public int nextInt() {
		try {
			return scan.nextInt();
		}catch(InputMismatchException e) {
			scan.nextLine(); //잘못 입력한 값을 입력 버퍼에서 비워줌
			return Integer.MIN_VALUE;//int의 가장 작은 수를 리턴
		}
	}

}
