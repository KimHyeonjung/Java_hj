# 1학년 1반 2번 둘리 학생 추가
insert student.student(grade, class, num, name) value(1, 1, 2, "둘리");
# 1학년 1학기 영어 과목 추가
# 1학년 1학기 수학 과목 추가
insert student.subject(grade, semester, name)
values(1,1, "영어"), (1,1, "수학");

# 1학년 1반 1번 학생의 영어 성적 추가(중간 : 80, 기말 : 100, 수행 : 100)
# 1학년 1반 1번 학생의 수학 성적 추가(중간 : 100, 기말 : 100, 수행 : 80)
# 1학년 1반 2번 학생의 국어 성적 추가(중간 : 50, 기말 : 100, 수행 : 80)
# 1학년 1반 2번 학생의 영어 성적 추가(중간 : 100, 기말 : 100, 수행 : 100)
# 1학년 1반 1번 학생의 수학 성적 추가(중간 : 50, 기말 : 50, 수행 : 50)
insert into student.score(midTerm, finalTerm, performance, studentNum, subjectNum)
values	
(80, 100, 100, 1, 2),
(100, 100, 80, 1, 3),
(50, 100, 80, 2, 1),
(100, 100, 100, 2, 2),
(50, 50, 50, 2, 3);
        
# 2학년 1반 1번 고길동 학생 추가
# 3학년 1반 1번 나둘리 학생 추가
insert into student.student(grade, class, num, name) values
(2,1,1, '고길동'), (3,1,1, '나둘리');
# 1학년 2반 유재석 학생 추가
insert into student.student(grade, class, num , name) values(1,2,1, '유재석');
