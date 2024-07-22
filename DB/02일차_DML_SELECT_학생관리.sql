/*
기본 검색
 - 테이블에 있는 모든 행(레코드)을 조회
 - 단 워크벤치 설정에 따라 최대 갯수가 조절 될 수 있다
 select * from 테이블명 
*/
# 모든 학생들을 조회
select * from student;
/*
 조건 검색
  - 테이블에 있는 행 중에서 조건을 만족하는 행을 조회
 select * from 테이블명 where 조건
*/
# 중간 고사 성적이 90점 이상인 행들을 조회
SELECT * FROM score WHERE midTerm >= 90;

# 중간 고사 성적이 90점 이상인 학생들의 학생 번호를 조회
select studentNum from score where midTerm >= 90;

/*
검색 결과 행의 중복 제거 : distinct
 - 전체 속성을 검색하는 경우 중복이 발생하지 않음
 - 일부 속성을 조회하는 경우 발생할 수 있는데 이때 사용
 select distinct 속성명1, ..., 속성명N from 테이블명 [where 조건]
*/
# 과목 중 한 과목이라도 중간 고사 성적이 90점 이상인 학생들의 학생 번호를 조회
select distinct studentNum from score where midTerm >= 90;

/*
속성명 between A and B : 속성의 값이 A이상 B이하인 행을 조회
속성명 in(a,b,...,z) : 속성의 값이 a이거나 b이거나 ... z인 행을 조회
속성명 like '패턴' : 속성의 값이 패턴과 일치하는 행을 조회
_ : 한글자
% : 0글자 이상
*/
# 성이 홍씨인 학생들을 조회
select * from student where name like '홍%';
# 이름이 2자인 학생들을 조회
select * from student where name like '__';
# 1~2학년들을 조회
select * from student where grade >= 1 and grade <= 2;
select * from student where grade between 1 and 2;
select * from student where grade in(1,2);
# 이름에 길이 포함된 학생들을 조회
select * from student where name like '%길%';
select * from student where name like concat('%','길','%');

/*
정렬
select * from 테이블명 [where 조건] order by 속성명 [desc | asc] [, 속성명 [desc | asc],...
*/
# 학년은 높은 학년부터, 반과 번호는 낮은 숫자부터 정렬하도록 조회
select * from student order by grade desc, class, num;

/*
LIMIT
검색 결과에서 원하는 위치부터 원하는 개수를 가져와서 조회
LIMIT 번지, 개수 : 번지부터 개수만큼 가져와서 조회
LIMIT 개수 : 0번지부터 개수만큼 가져와서 조회
*/
# 학생들 중에서 2명을 조회 
select * from student.student limit 2;
# 학생 조회 페이지가 있고, 학생의 학년, 반, 번호를 오름차순으로 정렬할 때
# 1페이지에 있는 학생들을 조회(단, 1페이지에는 최대 2명 조회
select * from student order by grade, class, num limit 2;
# 2페이지에 있는 학생들을 조회
select * from student order by grade, class, num limit 2, 2;
# 3페이지에 있는 학생들을 조회
select * from student order by grade, class, num limit 4, 2;

/*
# GROUP BY 
 - 그룹화를 할 때 사용
SELECT * FROM 테이블명 [WHERE 조건] 
GROUP BY 속성명1 [, 속성명2, ...]
HAVING 조건
[ORDER BY ..] [LIMIT ..]

# HAVING
 - 그룹화를 한 후 집계함수를 이용하여 조건을 걸 때 사용
HAVING 조건

집계함수
 - COUNT(속성명) : 개수
 - SUM(속성명) : 누적 합
 - AVG(속성명) : 평균
 - MAX(속성명) : 최대 값
 - MIN(속성명) : 최소 값
*/
# 각 학년의 학생 수를 조회
/*
AS : 조회시 속성 이름 수정하기. 생략 가능 (특수문자 들어갈 경우 ' ' 해줘야 됨)
SELECT 속성명1 AS 속성명1-1, ... FROM 테이블명;
*/
SELECT GRADE 학년, COUNT(GRADE) '수(명)' FROM STUDENT group by GRADE ;
# 학생수가 2명 이상인 학년을 조회
SELECT 
    GRADE 학년, COUNT(GRADE) '수(명)'
FROM
    STUDENT
GROUP BY GRADE
HAVING COUNT(GRADE) >= 2;

/*
INNER JOIN : 여러 테이블을 합쳐서 하나의 테이블로 조회하는 방법
SELECT * FROM 테이블명1
	JOIN 테이블명2 
    ON 테이블명1.속성명 = 테이블2.속성명
*/
# 성적이 있는 각 학생들의 평균을 구하는 예제
SELECT STUDENT.*, avg(0.4 * midTerm + 0.5 * finalTerm + 0.1 * performance) 성적 
FROM SCORE 
JOIN STUDENT
ON SCORE.studentNum = STUDENT.studentNum
GROUP BY studentNum;
# 각 학생의 각 과목 성적을 조회 (성적이 등록된 학생만)
SELECT student.grade 학년, class 반, student.num 번호, student.name 이름, 
subject.grade 학년, semester 학기, subject.name 과목명,
midTerm 중간, finalTerm 기말, performance 수행
FROM SCORE 
JOIN STUDENT
ON SCORE.studentNum = STUDENT.studentNum
JOIN subject
on score.subjectNum = subject.num;

/*
OUTER JOIN : LEFT, RIGHT
 - 두 테이블에 연결된 행 뿐 아니라 연결되지 않은 행들도 조회할 때 사용
 - 두 테이블에 연결된 데이터들만 조회 = > INNER JOIN
 - LEFT JOIN : 왼쪽 테이블을 기준으로 오른쪽 테이블을 연결
 - RIGHT JOIN : 오른쪽 테이블을 기준으로 왼쪽 테이블을 연결
*/
/*
USING : ON 테이블1.속성명 = 테이블2.속성명에서 속성명들의 이름이 같은 경우 USING(속성명)으로 대체
*/
# 각 학생의 각 과목 성적을 조회 (성적이 등록 안된 학생만)
SELECT student.grade 학년, class 반, student.num 번호, student.name 이름, 
subject.grade 학년, semester 학기, subject.name 과목명,
midTerm 중간, finalTerm 기말, performance 수행
FROM SCORE 
RIGHT JOIN STUDENT
USING (studentNum)
LEFT JOIN subject
on score.subjectNum = subject.num;

# 등록된 전체 학생수를 조회
SELECT count(*) 학생수 from student;
# 학생들의 국어 성적을 조회
SELECT student.grade 학년, class 반, student.num 번호, student.name 이름, 
subject.grade 학년, semester 학기, subject.name 과목명,
midTerm 중간, finalTerm 기말, performance 수행
FROM SCORE 
JOIN STUDENT
USING (studentNum)
JOIN subject
on score.subjectNum = subject.num
where subject.name = '국어';
