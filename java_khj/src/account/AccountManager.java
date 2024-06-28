package account;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import program.Program;

public class AccountManager implements Program{

	private Scanner scan = new Scanner(System.in);
	private List<AccountBook> list = new ArrayList<AccountBook>();
	private List<String> incomeCategory = Arrays.asList("월급", "용돈", "부수입");
	private List<String> expenseCategory = Arrays.asList("식대", "쇼핑", "월세", "통신비", "보험");
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

	private void insertAccount() {
		// TODO Auto-generated method stub
		
	}

	private void updateAccount() {
		// TODO Auto-generated method stub
		
	}

	private void deleteAccount() {
		// TODO Auto-generated method stub
		
	}

	private void searchAccount() {
		// TODO Auto-generated method stub
		
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
