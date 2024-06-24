package day19.board;

public enum BoardType {
	INSERT(1),
	UPDATE(2),
	DELETE(3),
	SEARCH(4),
	EXIT(5);
	
	private final int value;

	BoardType(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
	
	public static BoardType fromValue(int value) {
		
		for(BoardType tmp : BoardType.values()) {
			if(tmp.getValue() == value) {
				return tmp;
			}
		}
		throw new IllegalArgumentException("게시판 기능을 잘못 선택했습니다.");
	}
	
}
