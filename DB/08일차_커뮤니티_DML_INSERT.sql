# 회원 상태를 추가 : 기간 정지, 영구 정지, 사용
INSERT INTO MEMBER_STATE(MS_NAME) VALUES('기간 정지'), ('영구 정지'), ('사용');
# 신고 유형을 추가 욕설, 허위사실유포, 광고, 음란, 커뮤니티에 맞지 않음, 기타
INSERT INTO REPORT_TYPE(RT_NAME) 
VALUES('욕설'), ('허위사실유포'), ('광고'), ('음란'), ('커뮤니티에 맞지 않음'), ('기타');
# 공지 커뮤니티를 등록
INSERT INTO COMMUNITY(CO_NAME) VALUE('공지 커뮤니티');

# 회원가입 : id : abc123, pw : abc123 email : abc123@naver.com
# 회원가입 : id : qwe123, pw : qwe123 email : qwe123@naver.com
# 회원가입 : id : def123, pw : def123 email : def123@naver.com
# 관리자 : id : admin123, pw : admin123 email : admin123@naver.com
INSERT INTO member(ME_ID, ME_PW, ME_EMAIL, ME_MS_NAME)
VALUES('abc123', 'abc123', 'abc123@naver.com','사용');
INSERT INTO member(ME_ID, ME_PW, ME_EMAIL, ME_MS_NAME)
VALUES('qwe123', 'qwe123', 'qwe123@naver.com','사용');
INSERT INTO member(ME_ID, ME_PW, ME_EMAIL, ME_MS_NAME)
VALUES('def123', 'def123', 'def123@naver.com','사용');
INSERT INTO member(ME_ID, ME_PW, ME_EMAIL, ME_AUTHORITY, ME_MS_NAME)
VALUES('admin123', 'admin123', 'admin123@naver.com', 'ADMIN', '사용');

# 관리자가 '축구', '야구', '배구', '올림픽' 커뮤니티를 추가했을 때 필요한 쿼리
INSERT INTO COMMUNITY(CO_NAME) VALUES
('축구'),
('야구'),
('배구'),
('올림픽');

# abc123회원이 축구 커뮤니티에 게시글을 2개 작성했습니다. (제목과 내용은 알아서)
INSERT INTO POST (PO_TITLE, PO_SUB, PO_CO_NUM, PO_ME_ID)
SELECT '한국 축구 이대로 괜찮나?', '알빠노', CO_NUM, 'abc123' 
FROM COMMUNITY WHERE CO_NAME = '축구'; # INSERT SELECT 문 사용
INSERT INTO POST (PO_TITLE, PO_SUB, PO_CO_NUM, PO_ME_ID)
VALUES('축구 타이틀2', '축구 내용2', 2, 'abc123');
# abc123회원이 야구 커뮤니티에 게시글을 1개 작성했습니다. (제목과 내용은 알아서)
INSERT INTO POST (PO_TITLE, PO_SUB, PO_CO_NUM, PO_ME_ID)
VALUES('프로야구 노잼', 'ㅇㅈ', 3, 'abc123');
# abc123회원이 배구 커뮤니티에 게시글을 1개 작성했습니다. (제목과 내용은 알아서)
INSERT INTO POST (PO_TITLE, PO_SUB, PO_CO_NUM, PO_ME_ID)
VALUES('배구', '배구', 4, 'abc123');
# abc123회원이 올림픽 커뮤니티에 게시글을 3개 작성했습니다. (제목과 내용은 알아서)
INSERT INTO POST (PO_TITLE, PO_SUB, PO_CO_NUM, PO_ME_ID)
VALUES('한국 메달 몇 개나 땃나?', '알려줘', 5, 'abc123');
INSERT INTO POST (PO_TITLE, PO_SUB, PO_CO_NUM, PO_ME_ID)
VALUES('양궁 남자 단체 우승', '일까?', 5, 'abc123');
INSERT INTO POST (PO_TITLE, PO_SUB, PO_CO_NUM, PO_ME_ID)
VALUES('양궁 여자 단체 우승', '일까?', 5, 'abc123');
# qwe123회원이 축구 커뮤니티에 2개 올림픽 커뮤에 1개 게시글을 작성했습니다. (제목과 내용은 알아서)
INSERT INTO POST (PO_TITLE, PO_SUB, PO_CO_NUM, PO_ME_ID)
VALUES('올해 우승은?', '어느팀이', 2, 'qwe123');
INSERT INTO POST (PO_TITLE, PO_SUB, PO_CO_NUM, PO_ME_ID)
VALUES('K리그 잘부탁', '드려요', 2, 'qwe123');
INSERT INTO POST (PO_TITLE, PO_SUB, PO_CO_NUM, PO_ME_ID)
VALUES('대한민국 파이팅', '파이팅', 5, 'qwe123');
# qwe123회원이 1번 게시글을 클릭해서 상세를 확인했을 때 쿼리
UPDATE POST SET PO_VIEWS = PO_VIEWS + 1 WHERE PO_NUM = 1;
# 1번 게시글에 qwe123회원이 추천을 눌렀을 때 쿼리
INSERT INTO RECOMMEND(RE_STATE, RE_PO_NUM, RE_ME_ID) VALUES (1, 1, 'qwe123');
# 1번 게시글에 qwe123회원이 추천을 눌렀을 때 쿼리
UPDATE RECOMMEND SET RE_STATE = 0 WHERE RE_PO_NUM = 1 AND RE_ME_ID = 'qwe123';# 수정버전
DELETE FROM RECOMMEND WHERE RE_PO_NUM = 1 AND RE_ME_ID = 'qwe123';# 삭제버전

# 1번 게시글에 qwe123회원이 추천을 눌렀을 때 쿼리 => 추천을 수정하는 버전
UPDATE RECOMMEND SET RE_STATE = 1 WHERE RE_PO_NUM = 1 AND RE_ME_ID = 'qwe123';
# 1번 게시글에 qwe123회원이 추천을 눌렀을 때 쿼리 => 추천을 삭제하는 버전
INSERT INTO RECOMMEND(RE_STATE, RE_PO_NUM, RE_ME_ID) VALUES (1, 1, 'qwe123');

# qwe123회원이 1,2,3게시글은 추천을, 4,5,6번 게시글은 비추천을 누름
INSERT INTO RECOMMEND(RE_STATE, RE_PO_NUM, RE_ME_ID) VALUES
(1, 1, 'qwe123'),(1, 2, 'qwe123'),(1, 3, 'qwe123'),
(-1, 4, 'qwe123'),(-1, 5, 'qwe123'),(-1, 6, 'qwe123');
# def123회원이 3,4,5번 게시글을 추천, 7,8번 게시글을 비추천을 누름
INSERT INTO RECOMMEND(RE_STATE, RE_PO_NUM, RE_ME_ID) VALUES
(1, 3, 'def123'),(1, 4, 'def123'),(1, 5, 'def123'),
(-1, 7, 'def123'),(-1, 8, 'def123');
# abc123회원이 1~8번 게시글을 추천
INSERT INTO RECOMMEND(RE_STATE, RE_PO_NUM, RE_ME_ID) VALUES
(1, 1, 'abc123'),(1, 2, 'abc123'),(1, 3, 'abc123'),
(1, 4, 'abc123'),(1, 5, 'abc123'),(1, 6, 'abc123'),
(1, 7, 'abc123'),(1, 8, 'abc123');

# 1번 게시글에 각 회원이 다음 순서로 댓글을 작성. -는 대댓
# abc123 : 작성자입니다.
INSERT INTO COMMENT(CM_NUM, CM_CONTENT, CM_ME_ID, CM_PO_NUM, CM_ORI_NUM)
SELECT IFNULL(MAX(CM_NUM),0) + 1, '작성자입니다.', 'abc123', 1, IFNULL(MAX(CM_NUM),0) + 1 FROM COMMENT;
# - qwe123 : 반가워요
INSERT INTO COMMENT(CM_NUM, CM_CONTENT, CM_ME_ID, CM_PO_NUM, CM_ORI_NUM)
SELECT IFNULL(MAX(CM_NUM),0) + 1, '반가워요.', 'qwe123', 1, 1 FROM COMMENT;
# - def123 : 저도 반가워요
INSERT INTO COMMENT(CM_NUM, CM_CONTENT, CM_ME_ID, CM_PO_NUM, CM_ORI_NUM)
SELECT IFNULL(MAX(CM_NUM),0) + 1, '저도 반가워요', 'def123', 1, 1 FROM COMMENT;
# qwe123 : 어떻게 활성화 시킬가요?
INSERT INTO COMMENT(CM_NUM, CM_CONTENT, CM_ME_ID, CM_PO_NUM, CM_ORI_NUM)
SELECT IFNULL(MAX(CM_NUM),0) + 1, '어떻게 활성화 시킬가요?', 'qwe123', 1, IFNULL(MAX(CM_NUM),0) + 1 FROM COMMENT;
# def123 : 모르겠어요
INSERT INTO COMMENT(CM_NUM, CM_CONTENT, CM_ME_ID, CM_PO_NUM, CM_ORI_NUM)
SELECT IFNULL(MAX(CM_NUM),0) + 1, '모르겠어요', 'def123', 1, IFNULL(MAX(CM_NUM),0) + 1 FROM COMMENT;
# abc123 : 노력해봐요.
INSERT INTO COMMENT(CM_NUM, CM_CONTENT, CM_ME_ID, CM_PO_NUM, CM_ORI_NUM)
SELECT IFNULL(MAX(CM_NUM),0) + 1, '노력해봐요.', 'abc123', 1, IFNULL(MAX(CM_NUM),0) + 1 FROM COMMENT;

# 1번 게시글에서 6번 댓글을 기타로 qwe123회원이 신고함
INSERT INTO REPORT (RP_ME_ID, RP_TABLE, RP_TARGET, RP_RT_NAME)
VALUE ('qwe123', 'COMMENT', 6, '기타');
# 2번 게시글을 def123 회원이 기타로 신고함
INSERT INTO REPORT (RP_ME_ID, RP_TABLE, RP_TARGET, RP_RT_NAME)
VALUE ('def123', 'POST', 2, '기타');

SELECT * FROM REPORT;

