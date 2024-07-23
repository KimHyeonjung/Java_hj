# 컴퓨터공학 고길동 학생이 수강 신청한 강의의 개수를 조회하는 쿼리
select st_name, st_major, count(*) from course
join student
on st_num = co_st_num
where 
st_name = '고길동' and st_major = '컴퓨터공학';

# 신입생을 조회하는 쿼리
select * from student where st_num like '2024%' and st_grade = 1;

# 각 전공별 학생수를 조회하는 쿼리
select st_major 전공, count(*) 학생수 from student group by st_major;

# 컴퓨터공학 고길동 학생이 수강 신청한 학점을 조회하는 쿼리
SELECT
    st_name 이름, st_major 전공, SUM(LE_POINT) 신청학점
FROM
    course
        JOIN
    student ON co_st_num = st_num
		JOIN
	LECTURE ON CO_LE_NUM = LE_NUM
WHERE
    st_name = '고길동' AND st_major = '컴퓨터공학';

# 강의별 수강 신청한 학생수를 조회하는 쿼리
SELECT LE_TITLE, COUNT(*) '신청 학생수' FROM COURSE
course
        JOIN
    student ON co_st_num = st_num
		JOIN
	LECTURE ON CO_LE_NUM = LE_NUM
GROUP BY LE_TITLE;

# 학생이 있는 학과 이름을 조회하는 쿼리
SELECT DISTINCT ST_MAJOR FROM STUDENT ;
SELECT ST_MAJOR FROM STUDENT GROUP BY ST_MAJOR;

# 홍길동 학생이 수강하는 강의 목록을 조회하는 쿼리
SELECT ST_NAME 이름, LE_TITLE '수강 과목' FROM COURSE
    JOIN
    STUDENT ON CO_ST_NUM = ST_NUM
		JOIN
	LECTURE ON CO_LE_NUM = LE_NUM
WHERE ST_NAME = '홍길동';

# 김교수가 강의하는 강의명을 조회하는 쿼리
SELECT 
    PR_NAME 이름, LE_TITLE 강의명
FROM
    LECTURE
        JOIN
    PROFESSOR ON LE_PR_NUM = PR_NUM
WHERE
    PR_NAME = '김교수';


select * from course;
select * from PROFESSOR;
select * from student;
select * from lecture;

