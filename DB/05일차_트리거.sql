
USE CGV;

# 상영시간에 예약 가능한 초기 좌석 수를 상영관 좌석수로 수정
UPDATE SCHEDULE JOIN SCREEN ON SC_NUM = SD_SC_NUM
SET
	SD_POSSIBLE = SC_SEAT;


# 예약된 좌석만큼 상영시간에서 예약 가능한 좌석수를 빼서 수정하는 쿼리

# 6번 스케줄에 예매된 좌석 수만큼 6번 스케쥴의 좌석을 수정하는 쿼리
UPDATE SCHEDULE JOIN TICKETING ON TI_SD_NUM = SD_NUM
SET
	SD_POSSIBLE = SD_POSSIBLE - (TI_ADULT + TI_TEENAGER);
# OR
UPDATE SCHEDULE 
SET
	SD_POSSIBLE = SD_POSSIBLE - (SELECT SUM(TI_ADULT + TI_TEENAGER) FROM TICKETING WHERE TI_SD_NUM = 6);

# 예매가 발생하면 예매한 상영에서 예약 가능한 좌석수를 수정하는 트리거
DROP TRIGGER IF EXISTS TICKETING_INSERT;
DELIMITER //
CREATE TRIGGER TICKETING_INSERT
AFTER INSERT ON TICKETING
FOR EACH ROW
BEGIN
	# 예매된 성인수와 청소년수만큼 좌석수를 수정
    UPDATE SCHEDULE 
SET
	SD_POSSIBLE = SD_POSSIBLE - (NEW.TI_ADULT + NEW.TI_TEENAGER) 
    WHERE 
		SD_NUM = NEW.TI_SD_NUM;
END //
DELIMITER ;

INSERT INTO TICKETING VALUES (NULL, 2, 2, 48000, 7, 'abc123');
INSERT INTO TICKETING_LIST VALUES(NULL, 2, 1), (NULL, 2, 2), (NULL, 2, 3), (NULL, 2, 4);

SELECT * FROM TICKETING;
SELECT * FROM cgv.schedule;

# 등록된 트리거 조회
SHOW TRIGGERS;



