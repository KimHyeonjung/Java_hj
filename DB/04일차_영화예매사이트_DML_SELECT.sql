# 데드풀과 울버린 영화의 CGV강남에서 7/27에 상영하는 상영시간표를 조회하는 쿼리
SELECT TH_NAME 영화관, SC_NAME 상영관, MO_TITLE 영화제목, SD_DATE 상영일, SD_TIME 상영시간 FROM `SCHEDULE`
	JOIN MOVIE
		ON SD_MO_NUM = MO_NUM
	JOIN SCREEN 
		ON SD_SC_NUM = SC_NUM
	JOIN THEATER
		ON TH_NUM = SC_TH_NUM
WHERE TH_NAME = 'CGV강남' AND `SD_DATE` = '2024-07-27' AND MO_TITLE = '데드풀과 울버린'
	ORDER BY SC_NAME;
# 서브 쿼리를 이용
SELECT 
    TH_NAME 영화관, SC_NAME 상영관, MO_TITLE 영화제목, SD_DATE 상영일, SD_TIME 상영시간
FROM
    `SCHEDULE`
        JOIN
    SCREEN ON SD_SC_NUM = SC_NUM
        JOIN
    (SELECT * FROM THEATER WHERE TH_NAME = 'CGV강남') TH 
		ON SC_TH_NUM = TH_NUM
        JOIN 
	(SELECT * FROM MOVIE WHERE MO_TITLE = '데드풀과 울버린') MO
		ON MO_NUM = SD_MO_NUM
WHERE
	SD_DATE = '2024-07-27'
	ORDER BY SC_NAME;

# CGV강남 1관에 등록된 좌석을 조회하는 쿼리
SELECT 
    SC_NAME 상영관, SE_NAME 좌석
FROM
    SEAT
        JOIN
    SCREEN ON SC_NUM = SE_SC_NUM
        JOIN
    THEATER ON SC_TH_NUM = TH_NUM
WHERE
    TH_NAME = 'CGV강남' AND SC_NAME = '1관';
    
# abc123회원이 예약한 예매 내역을 조회하는 쿼리(영화 제목, 극장이름, 상여관 이름, 시간, 좌석번호)
SELECT MO_TITLE 영화제목, TH_NAME 영화관, SC_NAME 상영관, SD_DATE 상영일, 
SD_TIME 상영시간, SE_NAME 좌석번호
FROM TICKETING_LIST
	JOIN TICKETING
		ON tl_ti_num = ti_num
	JOIN SEAT
		ON SE_NUM = TL_SE_NUM
	JOIN `SCHEDULE`
		ON SD_NUM = TI_SD_NUM
	JOIN MOVIE
		ON SD_MO_NUM = MO_NUM
	JOIN SCREEN 
		ON SD_SC_NUM = SC_NUM
	JOIN THEATER
		ON TH_NUM = SC_TH_NUM	
WHERE TI_ME_ID = 'abc123'
	ORDER BY SC_NAME;
    
 
# 데드풀과 울버린 CGV강남점 1관 7/27 10:00에 예약된 좌석을 조회하는 쿼리
# 6번 스케쥴에 예약된 좌석을 조회하는 쿼리
SELECT 
	SE_NAME '예약된 좌석번호'
FROM
	(SELECT * FROM TICKETING WHERE TI_SD_NUM = 6) TT	
	JOIN 
		TICKETING_LIST ON TI_NUM = TL_TI_NUM
	JOIN
		SEAT ON TL_SE_NUM = SE_NUM;

# CGV강남점 1관의 좌석을 조회하는 쿼리
# 1번 사영관에 좌석들을 조회하는 쿼리
SELECT * FROM SEAT WHERE SE_SC_NUM = 1;
    
# 데드풀과 울버린 CGV강남점 1관 7/27 10:00에 예약 가능한 좌석을 조회하는 쿼리
# 6번 스케쥴에 예약 가능한 1번 상영관 좌석을 조회하는 쿼리
# 6번 스케쥴에 예약된 좌석이 아닌  1번 상영관 좌석
SELECT 
	SE_NAME '예약 가능 좌석'
FROM
	SEAT
WHERE
	SE_SC_NUM = 1
    AND SE_NAME NOT IN (SELECT 
	SE_NAME
FROM
	(SELECT * FROM TICKETING WHERE TI_SD_NUM = 6) TT	
	JOIN 
		TICKETING_LIST ON TI_NUM = TL_TI_NUM
	JOIN
		SEAT ON TL_SE_NUM = SE_NUM);

# 6번 스케줄에서 예약 가능한 좌석들의 수를 조회
SELECT 
	COUNT(SE_NAME) '좌석수'
FROM
	SEAT
WHERE
	SE_SC_NUM = 1 AND
	SE_NAME NOT IN (SELECT 
	SE_NAME
FROM
	(SELECT * FROM TICKETING WHERE TI_SD_NUM = 6) TT	
	JOIN 
		TICKETING_LIST ON TI_NUM = TL_TI_NUM
	JOIN
		SEAT ON TL_SE_NUM = SE_NUM);
       
# 장르별 등록된 영화 개수를 조회하는 쿼리
SELECT GE_NAME 장르, COUNT(MG_NUM) '영화 수' FROM MOVIE_GENRE
	RIGHT JOIN GENRE
		ON MG_GE_NAME = GE_NAME	
	GROUP BY GE_NAME;
	
# 개봉한 영화를 조회하는 쿼리
SELECT * FROM MOVIE 
WHERE
	 MO_DATE <= NOW();
     
#오늘부터 한달 사이에 개봉한 영화를 조회하는 쿼리
SELECT * FROM MOVIE
WHERE
	MO_DATE BETWEEN DATE_SUB(NOW(), INTERVAL 1 MONTH) AND NOW();
