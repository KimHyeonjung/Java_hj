# 사용자가 아이디 abc123, 비번 abc123으로 회원 가입을 진행했을 때 사용하는 쿼리 #권한은 관리자인 경우 'ADMIN', 사용자인 경우 'USER'
INSERT INTO MEMBER(ME_ID, ME_PW, ME_AUTHORITY) VALUES ('abc123', 'abc123', 'USER');
# 관리자가 아이디 admin1, 비번 admin1로 회원 가입을 진행했을 때 사용하는 쿼리 
INSERT INTO MEMBER VALUES('admin1', 'admin1', 'ADMIN');

# 영화 정보를 추가하는 작업을 해보세요. (데드풀과 울버린)
#1. 영화 정보를 추가 : 제목, 내용, 시간, 연령대, 개봉일
INSERT INTO MOVIE(MO_TITLE, MO_CONTENT, MO_TIME, MO_AGE, MO_DATE) VALUES
('데드풀과 울버린', '히어로 생활에서 은퇴한 후,
평범한 중고차 딜러로 살아가던 ‘데드풀’이
예상치 못한 거대한 위기를 맞아
모든 면에서 상극인 ‘울버린’을 찾아가게 되며 펼쳐지는
도파민 폭발 액션 블록버스터', 127, '청소년관람불가', 20240724);
#2. 국가를 등록. 한국, 미국, 영국, 일본, 중국
INSERT INTO COUNTRY(CO_NAME) VALUES 
('한국'),('미국'),('영국'),('일본'),('중국');
#3. 영화 참여 국가를 추가 : 데드풀과 울버린 영화의 참여 국가
INSERT INTO MOVIE_COUNTRY(MC_MO_NUM, MC_CO_NAME) VALUE(1, '미국');
#4. 장르를 추가. 액션, 코미디, 멜로, 공포, 스릴러
INSERT INTO GENRE(GE_NAME) VALUES
('액션'),('코미디'),('멜로'),('공포'),('스릴러'),('SF');
#5. 영화 장르를 추가 : 데드풀과 울버린의 장르
INSERT INTO MOVIE_GENRE(MG_GE_NAME, MG_MO_NUM) VALUES
('액션', 1), ('코미디', 1), ('SF', 1);
#6. 영화인을 등록 : 데드풀과 울버린의 나오는 모든 영화인
INSERT INTO PERSON VALUES
(NULL, '숀 레비', 19680723, '', '캐나다'),
(NULL, '라이언 레이놀즈', 19761023, '<엑스맨 탄생: 울버린> 등의 액션 블록버스터와 호러, 로맨틱', '캐나다'),
(NULL, '휴 잭맨', 19681012, '호주 출신 배우인 휴 잭맨은 TV연속극 [이웃들]에 출연하면서 연기자로 데뷔식을 치렀다. 원래', '오스트레일리아'),
(NULL, '엠마 코린', 19951213, '', ''),
(NULL, '모레나 바카린', 19790602, '', '브라질'),
(NULL, '롭 딜레이니', 19770119, '', '미국'),
(NULL, '레슬리 우감스', 19430525, '', ''),
(NULL, '카란 소니', 19850108, '', '인도'),
(NULL, '매튜 맥퍼딘', 19741017, '', '영국');
#7. 캐릭터를 등록 : 데드풀과 울버린에 나오는 영화인의 역할을 등록. 사진은 NULL로
INSERT INTO `CHARACTER` VALUES
(NULL, '감독', NULL, '1'),
(NULL, '배우', NULL, '2'),
(NULL, '배우', NULL, '3'),
(NULL, '배우', NULL, '4'),
(NULL, '배우', NULL, '5'),
(NULL, '배우', NULL, '6'),
(NULL, '배우', NULL, '7'),
(NULL, '배우', NULL, '8'),
(NULL, '배우', NULL, '9');
#8. 캐스팅을 등록 : 데드풀과 울버린에 나오는 캐릭터들의 캐스팅 이름을 등록. 감독은 감독으로
INSERT INTO CASTING VALUES
(NULL, '감독', 1, 1),
(NULL, '데드불', 1, 2),
(NULL, '로건', 1, 3),
(NULL, '카산드라 노바', 1, 4),
(NULL, '바네사 칼라일', 1, 5),
(NULL, '피터', 1, 6),
(NULL, '블라인드 앨', 1, 7),
(NULL, '도핀더', 1, 8),
(NULL, '패러독스', 1, 9);

# 강남 CGV를 등록. 상영관 : 3, 좌석 : 30
# 1관 : A1~A3, B1~B3, C1~C4
# 2관 : A1~A4, B1~B4, C1~C2
# 3관 : A1~A2, B1~B2, C1~C2, D1~D2, E1~E2
INSERT INTO THEATER VALUES
(NULL, 'CGV강남', 3, 30, '서울특별시 강남구 역삼동 814-6 스타플렉스', '서울');
INSERT INTO SCREEN VALUES
(NULL, '1관', 10, 1),(NULL, '2관', 10, 1),(NULL, '3관', 10, 1); 
INSERT INTO SEAT VALUES
(NULL, 'A1', 1), (NULL, 'A2', 1), (NULL, 'A3', 1),
(NULL, 'B1', 1), (NULL, 'B2', 1), (NULL, 'B3', 1),
(NULL, 'C1', 1), (NULL, 'C2', 1), (NULL, 'C3', 1),(NULL, 'C4', 1);
INSERT INTO SEAT VALUES
(NULL, 'A1', 2), (NULL, 'A2', 2), (NULL, 'A3', 2),(NULL, 'A4', 2),
(NULL, 'B1', 2), (NULL, 'B2', 2), (NULL, 'B3', 2),(NULL, 'B4', 2),
(NULL, 'C1', 2), (NULL, 'C2', 2); 
INSERT INTO SEAT VALUES
(NULL, 'A1', 3), (NULL, 'A2', 3),
(NULL, 'B1', 3), (NULL, 'B2', 3),
(NULL, 'C1', 3), (NULL, 'C2', 3),
(NULL, 'D1', 3), (NULL, 'D2', 3), 
(NULL, 'E1', 3), (NULL, 'E2', 3);
 
# 영등포 CGV를 등록. 상영관 : 4, 좌석 : 44
# 1관 : A1~A3, B1~B3, C1~C4
# 2관 : A1~A4, B1~B4, C1~C2
# 3관 : A1~A3, B1~B3, C1~C3, D1~D3
# 4관 : A1~A6, B1~B6
INSERT INTO THEATER VALUES
(NULL, 'CGV영등포', 4, 44, '서울특별시 영등포구 영등포동 4가 441-10번지 경방 타임스퀘어 4~7층', '서울');
INSERT INTO SCREEN VALUES
(NULL, '1관', 10, 2),(NULL, '2관', 10, 2),(NULL, '3관', 12, 2),(NULL, '4관', 12, 2); 
INSERT INTO SEAT VALUES
(NULL, 'A1', 4), (NULL, 'A2', 4), (NULL, 'A3', 4),
(NULL, 'B1', 4), (NULL, 'B2', 4), (NULL, 'B3', 4),
(NULL, 'C1', 4), (NULL, 'C2', 4), (NULL, 'C3', 4),(NULL, 'C4', 4);
INSERT INTO SEAT VALUES
(NULL, 'A1', 5), (NULL, 'A2', 5), (NULL, 'A3', 5),(NULL, 'A4', 5),
(NULL, 'B1', 5), (NULL, 'B2', 5), (NULL, 'B3', 5),(NULL, 'B4', 5),
(NULL, 'C1', 5), (NULL, 'C2', 5); 
INSERT INTO SEAT VALUES
(NULL, 'A1', 6), (NULL, 'A2', 6), (NULL, 'A3', 6), 
(NULL, 'B1', 6), (NULL, 'B2', 6), (NULL, 'B3', 6),
(NULL, 'C1', 6), (NULL, 'C2', 6), (NULL, 'C3', 6),
(NULL, 'D1', 6), (NULL, 'D2', 6), (NULL, 'D3', 6);
INSERT INTO SEAT VALUES
(NULL, 'A1', 7), (NULL, 'A2', 7), (NULL, 'A3', 7), 
(NULL, 'A4', 7), (NULL, 'A5', 7), (NULL, 'A6', 7),
(NULL, 'B1', 7), (NULL, 'B2', 7), (NULL, 'B3', 7),
(NULL, 'B4', 7), (NULL, 'B5', 7), (NULL, 'B6', 7);

# 데드풀과 울버린 영화 상영시간을 등록
# CGV강남 - 7/27
# 3관 - 09:00(조조), 11:35, 14:10, 16:45, 19:20
# 1관 - 10:00(조조), 12:35, 15:10, 17:45, 20:20
# 2관 - 10:30(조조), 15:00, 20:00, 23:00
DELETE FROM `SCHEDULE`;
INSERT INTO `SCHEDULE` VALUES  
(NULL, '09:00', 20240727, 1, 1, 3),(NULL, '11:35', 20240727, 0, 1, 3), 
(NULL, '14:10', 20240727, 0, 1, 3),(NULL, '16:45', 20240727, 0, 1, 3),
(NULL, '19:20', 20240727, 0, 1, 3), -- 3관

(NULL, '10:00', 20240727, 1, 1, 1),(NULL, '12:35', 20240727, 0, 1, 1), 
(NULL, '15:10', 20240727, 0, 1, 1),(NULL, '17:45', 20240727, 0, 1, 1),
(NULL, '20:20', 20240727, 0, 1, 1), -- 1관

(NULL, '10:30', 20240727, 1, 1, 2),(NULL, '15:00', 20240727, 0, 1, 2), 
(NULL, '20:00', 20240727, 0, 1, 2),(NULL, '23:00', 20240727, 0, 1, 2); -- 2관
# CGV영등포 - 7/27
# 1관 - 09:00(조조), 10:40, 16:00, 18:40
# 2관 - 09:40(조조), 15:00, 23:00
# 3관 - 08:40(조조), 17:00
INSERT INTO `SCHEDULE` VALUES  
(NULL, '09:00', 20240727, 1, 1, 4),(NULL, '10:40', 20240727, 0, 1, 4), 
(NULL, '16:00', 20240727, 0, 1, 4),(NULL, '18:40', 20240727, 0, 1, 4), -- 1관

(NULL, '09:40', 20240727, 1, 1, 5),(NULL, '15:00', 20240727, 0, 1, 5), 
(NULL, '23:00', 20240727, 0, 1, 5), -- 2관

(NULL, '08:40', 20240727, 1, 1, 6),(NULL, '17:00', 20240727, 0, 1, 6); -- 3관

# 성인 : 14000원, 청소년 : 10000원, 조조성인 : 12000원, 조조청소년 : 8000원
INSERT INTO PRICE VALUES
(NULL, '성인', 14000),
(NULL, '청소년', 10000),
(NULL, '조조 성인', 12000),
(NULL, '조조 청소년', 8000);
# abc123회원이 CGV강남 1관 10:00에 사영하는 데드풀과 울버린 성인2장을 예매하는 쿼리
# 좌석은 A1, A2
INSERT INTO TICKETING VALUES
(NULL, 2, 0, 24000, 6, 'abc123');
INSERT INTO TICKETING_LIST VALUES
(NULL, 1, 1), (NULL, 1, 2);


SELECT * FROM SEAT;


