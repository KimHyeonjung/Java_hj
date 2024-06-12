package day10;

import java.util.Scanner;

public class VocabularyEx01 {

	private static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {
		/* 메뉴
		 * 1. 단어 등록
		 * 2. 단어 수정
		 * 3. 단어 검색
		 * 4. 단어 삭제
		 * 5. 종료
		 * 메뉴 선택 : 
		 */
		int menu;
		int wordCount = 0; //저장된 단어 갯수
		final int WORD_MAX = 10;
		Word []wordList = new Word[WORD_MAX];

		do {
			//메뉴를 출력해주는 메서드
			printMenu();
			menu = scan.nextInt();
			switch (menu) {
			case 1 :
				//단어 등록 insertWord
				wordCount = insertWord(wordList, wordCount);
				if(wordCount == wordList.length) {
					wordList = expandWordList(wordList, wordList.length + 10);
				}
				break;
			case 2 :
				updateWord(wordList, wordCount);
				break;
			case 3 :
				//단어 리스트에서 입력한 단어를 검색해 있으면 단어정보를 출력, 없으면 없다고 출력 searchWord
				searchWord(wordList, wordCount);
				break;
			case 4 :
				wordCount = deleteWord(wordList, wordCount);

				break;
			case 5 :
				System.out.println("프로그램 종료.");
				break;
			default :
				for(int i = 0; i < wordCount; i++) {
					System.out.println("["+ i + "] 단어:" + wordList[i].getWord() + " 품사:" + wordList[i].getWordClass() +
							" 의미:" + wordList[i].getMeaning());
				}
				//System.out.println("잘못된 메뉴입니다.");
				break;
			}
		} while(menu != 5); // 메뉴 입력이 5가 아닐동안 메뉴를 반복해서 출력
	}
	
	/** 기능 : wordList에 index 번지에 있는 단어가 word인지 아닌지 알려주는 메소드
	 * @param wordList 단어 리스트
	 * @param word 검색할 단어
	 * @param i 해당 단어의 번지
	 * @return index 번지에 word가 있으면 true, 없으면 false
	 */
	public static boolean checkWord(Word[] wordList, String word, int index) {
		if(wordList.length <= index || index < 0) {
			return false;
		}
		if(wordList[index] == null) {
			return false;
		}
		return wordList[index].getWord().equals(word);
	}
	/**기능 : 메뉴를 출력해주는 메서드
	 */
	public static void printMenu() {
		System.out.println("메뉴");
		System.out.println("1. 단어 등록");
		System.out.println("2. 단어 수정");
		System.out.println("3. 단어 검색");
		System.out.println("4. 단어 삭제");
		System.out.println("5. 종료");
		System.out.print("메뉴 선택 : ");
	}

	/**기능 : 단어 리스트에 단어가 있으면 해당 단어를 출력하고 없으면 없다고 출력하는 메소드
	 * @param wordList 단어 리스트
	 * @param word 단어
	 * @param wordCount 저장된 단어 수
	 */
	public static void printSearchWord(Word[] wordList, String word, int wordCount) {
		int count = 0; //일치하는 단어가 몇개 있는지 확인하는 변수
		//단어 리스트에 수정할 단어와 일치하는 단어들을 번호와 함께 출력
		for(int i = 0; i < wordCount; i++) {
			if(word.equals(wordList[i].getWord())) {
				System.out.println("["+ i + "] 단어:" + wordList[i].getWord() + " 품사:" + wordList[i].getWordClass() +
						" 의미:" + wordList[i].getMeaning());
				count++;
			}
		}
		if(count == 0) { //찾는 단어가 없으면 안내문구 출력 후 종료
			System.out.println("입력한 단어가 없습니다.");
		}
	}

	/**기능 : 단어 리스트를 늘려서 돌려주는 메소드
	 * @param wordList 단어 리스트
	 * @param size 늘려줄 크기
	 * @return 늘어난 단어 리스트 	*/
	public static Word[] expandWordList(Word[] wordList, int size) {
		if(wordList.length >= size) {
			return wordList;
		}
		Word [] tmp = new Word[size];
		//wordList 0번지부터 wordList.length까지를 tmp의 0번지부터 복붙
		System.arraycopy(wordList, 0, tmp, 0, wordList.length);
		return tmp;
	}

	/**기능 : 단어 리스트를 알파벳순으로 정렬해주는 메소드
	 * @param wordList 단어 리스트
	 * @param wordCount 현재 저장된 단어 게수
	 */
	public static void wordSort(Word[] wordList, int wordCount ) {
		for(int i = 0; i < wordCount -1; i++) {
			for(int j = 0; j < wordCount -1; j++) {
				if(wordList[j].getWord().compareTo(wordList[j+1].getWord()) > 0) {
					Word tmp = wordList[j];
					wordList[j] =wordList[j+1];
					wordList[j+1] =tmp;
				}
			}
		}
	}

	/**기능 : 단어 정보를 입력받아 단어 객체로 돌려주는 메소드
	 * @param scan 단어 정보를 입력받기 위한 Scanner
	 * @return 입력한 단어 정보를 이용하여 생성한 단어 객체
	 */
	public static Word inputWord() {
		System.out.print("단어 : ");
		String word = scan.next();
		System.out.print("품사 : ");
		String wordClass = scan.next();
		System.out.print("의미 : ");
		scan.nextLine(); //공백처리
		String meaning = scan.nextLine();
		return new Word(word, wordClass, meaning);
	}

	/**기능 : 단어 정보를 입력받아 단어리스트에 저장하고 등록된 단어의 수를 알려주는 메소드
	 * @param wordList 단어 리스트
	 * @param wordCount 현재 단어 숫자
	 * @return 등록된 단어 숫자
	 */
	public static int insertWord(Word [] wordList, int wordCount) {

		Word tmp = inputWord();
		wordList[wordCount] = tmp;
		wordCount++;
		//		wordList[wordCount].print(); //확인용
		//저장된 단어 리스트를 정렬(알파벳 순으로) arraySort (단어 리스트, 단어 숫자)
		wordSort(wordList, wordCount);
		return wordCount;		
	}
	public static void updateWord(Word[] wordList, int wordCount) {
		//수정할 단어를 입력
		System.out.print("수정할 단어 입력 : ");
		String word = scan.next();
		printSearchWord(wordList, word, wordCount);
		//있으면 수정할 단어를 선택
		System.out.print("수정할 단어 번호 선택 : ");
		int num = scan.nextInt();
		boolean res = checkWord(wordList, word, num);
		if(!res) {
			System.out.println("잘못된 번호를 선택했습니다.");
			return;
		}
		//수정할 단어, 품사, 의미를 입력
		Word tmp = inputWord();
		System.out.println("------------------------");
		System.out.println("단어 수정을 완료했습니다.");
		System.out.println("------------------------");
		//입력한 정보로 단어를 수정
		wordList[num].updateWord(tmp);
		wordList[num].print();
		//단어리스트 정렬
		wordSort(wordList, wordCount);


	}

	/**기능 : 입력한 단어를 단어 리스트에서 찾아 단어 정보를 출력하는 메소드
	 * @param wordList 단어 리스트
	 * @param wordCount 저장된 단어 숫자
	 */
	public static void searchWord(Word[] wordList, int wordCount) {
		//검색할 단어를 입력
		System.out.print("검색할 단어 입력 : ");
		String word = scan.next();
		System.out.println("------------------------");
		System.out.println("단어 검색을 완료했습니다.");
		System.out.println("------------------------");
		printSearchWord(wordList, word, wordCount);
	}

	/**기능 : 배열의 index번지에 요소를 삭제하는 메소드
	 * @param wordList 단어 리스트
	 * @param wordCount 현재 단어 숫자
	 * @param index 삭제할 위치
	 * @return 삭제된 후 단어 수
	 */
	//	public static int deleteWord(Word[] wordList, int wordCount) {
	//		//삭제할 단어를 입력
	//		System.out.print("삭제할 단어 입력 : ");
	//		String word = scan.next();
	//		printSearchWord(wordList, word, wordCount);
	//		//있으면 삭제할 단어를 선택
	//		System.out.print("삭제할 단어 번호 선택 : ");
	//		int num = scan.nextInt();
	//		boolean res = checkWord(wordList, word, num);
	//		if(!res) {
	//			System.out.println("잘못된 번호를 선택했습니다.");
	//			return wordCount;
	//		}
	//		//단어 삭제 후 삭제된 번지부터 채워 넣기
	//		for(int i = num; i < wordCount-1; i++) {
	//			wordList[i] = wordList[i+1];
	//			wordList[i+1] = null;
	//		}
	//		
	//		System.out.println("------------------------");
	//		System.out.println("단어 삭제를 완료했습니다.");
	//		System.out.println("------------------------");
	//		return wordCount-1;
	//		
	//
	//	}
	public static int deleteWord(Word[] wordList, int wordCount) {
		//삭제할 단어를 입력
		System.out.print("삭제할 단어 입력 : ");
		String word = scan.next();
		printSearchWord(wordList, word, wordCount);
		//있으면 삭제할 단어를 선택
		System.out.print("삭제할 단어 번호 선택 : ");
		int num = scan.nextInt();
		boolean res = checkWord(wordList, word, num);
		if(!res) {
			System.out.println("잘못된 번호를 선택했습니다.");
			return wordCount;
		}
		wordCount = deleteWordList(wordList, wordCount, num);
		return wordCount;
	}
	
	public static int deleteWordList(Word[] wordList, int wordCount, int index) {

		//삭제하려는 번지 다음에 있는 단어들부터 앞으로 한칸 씩 당기고 마지막 단어를 null로
		Word[] tmp = new Word[wordList.length];
		//단어 리스트의 복사본
		System.arraycopy(wordList, 0, tmp, 0, wordCount);
		//삭제할 위치부터 하나씩 당겨오게 하기 위해 복사
		if(wordCount - index - 1 != 0) { // 삭제한 단어가 마지막 단어가 아닐 때
			System.arraycopy(tmp,  index+1, wordList, index, wordCount - index - 1);
		}
		//저장된 단어수를 1감소
		wordCount--;
		wordList[wordCount] = null;

		System.out.println("------------------------");
		System.out.println("단어 삭제를 완료했습니다.");
		System.out.println("------------------------");
		return wordCount;


	}
}

/**영어 단어를 관리하기 위한 Word 클래스를 만들고,
 * 필요한 멤버변수들을 선언해보세요.
 */
class Word{
	//단어, 뜻, 품사
	private String word, meaning, wordClass;

	//생성자
	public Word(String word, String wordClass, String meaning) {
		this.word = word;
		this.wordClass = wordClass;
		this.meaning = meaning;
	}
	//필요한 기능

	/**기능 : 단어를 출력하는 메소드
	 */
	public void print() {
		System.out.println("-------------------");
		System.out.println("단어 : " + word);
		System.out.println("품사 : " + wordClass);
		System.out.println("의미 : " + meaning);
		System.out.println("-------------------");
	}
	/**기능 : 새 단어정보가 주어지면 수정하는 메소드
	 * @param wordList 단어 리스트
	 * @param wordCount 현재 저장된 단어 개수
	 */
	public void updateWord(Word word) {
		this.word = word.word;
		this.wordClass = word.wordClass;
		this.meaning = word.meaning;
	}


	//getter와 setter
	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public String getMeaning() {
		return meaning;
	}

	public void setMeaning(String meaning) {
		this.meaning = meaning;
	}

	public String getWordClass() {
		return wordClass;
	}

	public void setWordClass(String wordClass) {
		this.wordClass = wordClass;
	}
}
