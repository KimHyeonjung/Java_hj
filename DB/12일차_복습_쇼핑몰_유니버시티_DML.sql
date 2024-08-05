# 모든 제품을 조회하는 쿼리
select * from product;
# 금액이 1만원 이상인 제품을 조회하는 쿼리
select * from product where pr_price >= 10000;
# 제품명에 시원한이 들어간 제품을 조회하는 쿼리
select * from product where pr_name like '%시원한%';
# 카테고리가 옷인 제품을 조회하는 쿼리
select * from product where pr_ca_num in (select ca_num from category where ca_name = '옷');

# 금액과 제품명을 이용해서 검색하는 쿼리를 일반화해서 만들어보세요.
select * from product 
where pr_name like concat('%','','%')
and  pr_price >= 0;
select * from product 
where pr_name like concat('%','','%')
and  pr_price >= 0
and pr_ca_num = 1;

# 카테고리별 제품의 평균가격을 조회하는 쿼리
select ca_name, format(ifnull(avg(pr_price), 0),0) 평균가격 from category 
left join product on ca_num = pr_ca_num
group by ca_num;

# 카테고리별 제품의 평균 가격이 3만원 이상인 카테고리를 조회
select ca_name, format(ifnull(avg(pr_price), 0),0) `평균가격` from category 
left join product on ca_num = pr_ca_num
group by ca_num
having ifnull(avg(pr_price), 0) >= 30000;

# 케테고리별 등록된 제품수를 조회
select ca_name 카테고리, count(pr_ca_num) 제품수 from category
join product on pr_ca_num = ca_num 
group by ca_num;

# abc123 회원이 구매한 제품 정보를 조회
select product.* from product join buy on bu_pr_code = pr_code
where bu_me_id = 'abc123';
# abc123 회원의 누적 구매 금액을 조회하는
select format(sum(pr_price * bu_amount), 0) 누적금액 from product join buy on bu_pr_code = pr_code
where bu_me_id = 'abc123';

# abc123 회원이 장바구니에 1번 제품 3개를 담았을 때 쿼리
insert into basket values(null, 3, 'clo001', 'abc123');
# abc123 회원이 장바구니에 1번 제품 2개를 담았을 때 쿼리
update basket set ba_amount = ba_amount + 2 where ba_pr_code = 'clo001' and ba_me_id = 'abc123';

# abc123 회원이 장바구니에 있는 'CLO001' 제품을 구매했을 때 쿼리
insert into buy(bu_pr_code, bu_me_id, bu_amount, bu_state)
select 'CLO001', 'abc123', 
	(select ba_amount from basket where ba_pr_code = 'CLO001' and ba_me_id = 'abc123'), '구매';
delete from basket where ba_me_id = 'abc123' and ba_pr_code = 'clo001';
select * from buy;

# 등록된 강의를 조회하는 쿼리
select * from lecture;
# 교수 번호가 2024123001인 교수님이 강의하는 강의를 조회하는 쿼리
select * from lecture where le_pr_num = '2024123001';
# 학번이 2024123001인 학생이 수강하는 강의를 조회하는 쿼리
select co_st_num 학번, le_title 수강과목 from lecture join course on co_le_num = le_num
where co_st_num = '2024123001';
 
 # 전공 필수인 강의를 조회하는 쿼리
 select * from lecture where le_major = '전공 필수';
 # 컴퓨터공학 학생들이 수강하는 강의들을 조회하는 쿼리
 select distinct lecture.* from course join lecture on co_le_num = le_num
 where co_st_num in (select st_num from student where st_major = '컴퓨터공학');
 