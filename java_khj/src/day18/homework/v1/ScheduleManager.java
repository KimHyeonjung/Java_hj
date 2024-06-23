package day18.homework.v1;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import program.Program;

public class ScheduleManager implements Program{


	private Scanner scan = new Scanner(System.in);
	private List<Schedule> scList = new ArrayList<Schedule>();
	private final int INSERT = 1;
	private final int UPDATE = 2;
	private final int DELETE = 3;
	private final int SEARCH = 4;
	private final int EXIT = 5;
	private String scFormat = "[{0}] {1} / {2} / {3}";

	@Override
	public void printMenu() {
		System.out.println("----------------------\n"
				+ "메뉴\r\n"
				+ "1. 일정 추가\r\n"
				+ "2. 일정 수정\r\n"
				+ "3. 일정 삭제\r\n"
				+ "4. 일정 확인\r\n"
				+ "5. 프로그램 종료\r\n"
				+ "----------------------\n"
				+ "메뉴 선택 :");
	}

	@Override
	public void runMenu(int menu) throws Exception {
		switch (menu) {
		case INSERT :
			insertSchedule();
			System.out.println(scList);
			break;
		case UPDATE :
			updateSchedule();
			break;
		case DELETE :
			deleteSchedule();
			break;
		case SEARCH :
			searchSchedule();
			break;
		case EXIT :
			break;
		default :
			break;


		}


	}
	private boolean dateFormatChk(String date) {
		String regex = "^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}$";
		if(date.equals("")) {
			return true;
		}
		if(!Pattern.matches(regex, date)) {
			System.out.println("날짜 형식이 잘못됨(yyyy-MM-dd)");
			return false;
		}
		return true;
	}
	

	private void searchSchedule() throws ParseException {
		//조회할 날짜를 입력받고
		System.out.print("날짜를 입력(yyyy-MM-dd)(엔터만 - 전체 조회) :");
		scan.nextLine();
		String searchDate = scan.nextLine();
		if(dateFormatChk(searchDate)) {
			//해당 날짜의 일정 목록을 번호를 매겨서 보여줌
			for(int i = 0 ; i < scList.size(); i++) {
				if(scList.get(i).getDate().contains(searchDate)) {
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
					Date date = format.parse(scList.get(i).getDate());
					SimpleDateFormat format2 = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분");
					String nDate = format2.format(date);
					System.out.println(MessageFormat.format(scFormat,i+1, nDate
							, scList.get(i).getSchedule(), scList.get(i).getDetail()));
				}
			}
			return;
		}
		

	}

	private void deleteSchedule() throws ParseException {
		searchSchedule();
		System.out.print("삭제할 일정[번호]를 선택 : ");
		int num = scan.nextInt() -1;
		scList.remove(scList.get(num));
		Collections.sort(scList);
		System.out.println("일정이 삭제되었습니다.");
	}

	private void updateSchedule() throws Exception {
		//수정할 날짜를 입력받고
		System.out.print("날짜를 입력(yyyy-MM-dd) :");
		scan.nextLine();
		String searchDate = scan.nextLine();
		//해당 날짜의 일정 목록을 번호를 매겨서 보여줌
		for(int i = 0 ; i < scList.size(); i++) {
			if(scList.get(i).getDate().contains(searchDate)) {
				System.out.println(MessageFormat.format(scFormat,i+1, scList.get(i).getDate()
						, scList.get(i).getSchedule(), scList.get(i).getDetail()));
			}
		}
		System.out.print("수정할 일정[번호]를 선택 : ");
		int num = scan.nextInt() -1;
		scList.remove(scList.get(num));
		scList.add(inputSchedule());
		Collections.sort(scList);
		System.out.println("일정이 수정되었습니다.");

	}

	/**기능 : 날짜와 시간, 일정, 상세 내용을 입력받아 일정리스트에 저장하는 메서드
	 * 
	 * 날짜 형식(yyyy-MM-dd hh:mm)
	 * @throws Exception 
	 */
	private void insertSchedule() throws Exception {

		scList.add(inputSchedule());
		Collections.sort(scList);
		System.out.println("일정이 추가되었습니다.");
	}
	/**
	 * 기능 : 입력받은 날짜, 일정, 상세 정보를 일정 객체로 생성해서 반환해주는 메서드
	 * @return
	 * @throws Exception 
	 */
	private Schedule inputSchedule() throws Exception  {

		scan.nextLine();
		System.out.print("날짜(yyyy-MM-dd hh:mm) : ");
		String date = scan.nextLine();
		System.out.print("일정 : ");
		String schedule = scan.nextLine();
		System.out.print("상세 : ");
		String detail = scan.nextLine();
		return new Schedule(date, schedule, detail);
	}


	@Override
	public void run() {
		String fileName = "src/day18/homework/v1/schedule.txt";

		load(fileName);

		int menu = INSERT;
		do {
			printMenu();
			try {
				menu = scan.nextInt();
				runMenu(menu);
			} catch(InputMismatchException e) {
				System.out.println("올바른 타입을 입력하세요");
				scan.next();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

		}while(menu != 5);

		save(fileName);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void load(String fileName) {
		try(FileInputStream fis = new FileInputStream(fileName);
				ObjectInputStream ois = new ObjectInputStream(fis)) {
			scList = (List<Schedule>)ois.readObject();
		} catch (Exception e) {
			System.out.println("불러오기에 실패했습니다.");
		}
	}

	@Override
	public void save(String fileName) {
		try(FileOutputStream fos = new FileOutputStream(fileName);
				ObjectOutputStream oos = new ObjectOutputStream(fos)) {
			oos.writeObject(scList);
		} catch (Exception e) {
			System.out.println("저장에 실패했습니다.");
		}
	}

}
