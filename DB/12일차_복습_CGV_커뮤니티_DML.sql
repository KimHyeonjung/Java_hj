# 등록된 모든 영화를 조회하는 쿼리
select * from movie;

# 등록된 모든 영화를 조회하는 쿼리(장르도 함께 검색)
select * from movie 
join movie_genre on mg_mo_num = mo_num;

# 장르가 액션인 영화를 조회하는 쿼리
select * from movie 
join movie_genre on mg_mo_num = mo_num
where mg_ge_name = '액션';
select * from movie where mo_num in (select mg_mo_num from movie_genre where mg_ge_name = '액션');

# 장르가 액션으로 등록된 영화 수를 조회하는 쿼리
select mg_ge_name, count(mg_mo_num) from movie_genre
where mg_ge_name = 'SF'
group by mg_ge_name; 

# 모든 장르에 등록된 영화 수를 조회하는 쿼리(단, 영화가 등록되지 않은 장르들은 조회가 x)
select mg_ge_name 장르, count(mg_mo_num) from movie_genre group by mg_ge_name;
# 모든 장르에 등록된 영화 수를 조회하는 쿼리(단, 영화가 등록되지 않은 장르들은 조회가 o)
select ge_name, count(mg_mo_num) from genre left join movie_genre on mg_ge_name = ge_name group by ge_name;

# 등록된 영화를 이름순으로 6개 조회하는 쿼리(내림차순, 1페이지)
select * from movie order by mo_title desc limit 0, 6;

# 등록된 영화를 이름순으로 6개 조회하는 쿼리(내림차순, 2페이지)
select * from movie order by mo_title desc limit 6, 6;

# 데드풀과 울버린을 관람한 관객수를 조회하는 쿼리
select '데드풀과 울버린' 영화제목, SUM(ti_adult + ti_teenager) 관객수 from ticketing join schedule on ti_sd_num = sd_num
where sd_mo_num = (select mo_num from movie where mo_title = '데드풀과 울버린')
group by sd_mo_num;

# 영화별 관객수를 조회하는 쿼리
SELECT 
    mo_title 영화제목, SUM(ti_adult + ti_teenager) 관객수
FROM
    movie
        LEFT JOIN
    schedule ON sd_mo_num = mo_num
        left JOIN
    ticketing ON ti_sd_num = sd_num
group by mo_num;

# 데드풀과 울버린 영화 상영시간을 조회하는 쿼리
select '데드풀과 울버린', sd_time 상영시간 from schedule 
where sd_mo_num in (select mo_num from movie where mo_title = '데드풀과 울버린')
order by sd_time;

# 데드풀과 울버린 영화 상영시간을 조회하는 쿼리( 7/27 오후 상영시간을 조회)
select '데드풀과 울버린', sd_time 상영시간 from schedule 
where sd_mo_num in (select mo_num from movie where mo_title = '데드풀과 울버린')
and sd_date in ('2024-07-27') and sd_time > '12:00:00'
order by sd_time;

# 2번 상영시간에 예약된 좌석을 조회하는 쿼리
select se_name from ticketing_list
join ticketing on tl_ti_num = ti_num
join seat on tl_se_num = se_num
where ti_sd_num = 7;

# 2번 상영시간에 예약 가능한 좌석을 조회하는 쿼리
select se_name from schedule 
join screen on sd_sc_num = sc_num 
join seat on sc_num = se_sc_num
where sd_num = 7 and se_name not in(select se_name from ticketing_list
join ticketing on tl_ti_num = ti_num
join seat on tl_se_num = se_num
where ti_sd_num = 7);

# 휴 잭맨이 참여한 영화 목록을 조회하는 쿼리
select mo_title from casting 
join movie on ca_mo_num = mo_num
join `character` on ca_ch_num = ch_num
where ch_pe_num = (select pe_num from person where ps_name = '휴 잭맨');

# CGV강남에서 상영했거나 상영중, 상영 예정인 영화들을 조회하는 쿼리
select distinct mo_title from movie 
join schedule on mo_num = sd_mo_num
join screen on sc_num = sd_sc_num
join theater on sc_th_num = th_num
where th_name = 'CGV강남';
select distinct mo_title from movie 
join schedule on mo_num = sd_mo_num
join (select * from screen 
	where sc_th_num = (select th_num from theater where th_name ='CGV강남')) sc 
		on sd_sc_num = sc_num;
        
# 게시글을 조회하는 쿼리(번호 내림차순, 3개, 1페이지)
select * from post order by po_num desc limit 0, 3;

# 게시글 제목에 '구'가 들어가는 게시글을 조회하는 쿼리(번호 내림차순, 3개, 1페이지)
select * from post where po_title like '%구%' order by po_num desc limit 0, 3;

# 축구 커뮤니티에 등록된 게시글을 조회하는 쿼리(커뮤 번호 2번)
select * from post where po_co_num = 2;

# 1번 게시글의 댓글들을 조회하는 쿼리(단, 대댓은 댓글 다음에 와야 함)
select * from comment where cm_po_num = 1 order by cm_ori_num asc, cm_num asc;

# 1번 게시글의 댓글 1페이지를 조회하는 쿼리(한 페이지에 5개)
select * from comment where cm_po_num = 1 order by cm_ori_num asc, cm_num asc limit 0, 5;

# 게시글을 조회(조회수가 많은 순으로)
select * from post order by po_views desc;

# 게시글 조회(조회수가 많은 순으로 조회: 조회순으로 했을 때 3위 게시글까지 조회)
# 윈도우 함수를 where에 직접 사용할 수 없어서 검색 결과를 하나의 테이블처럼 처리해야 함
select * from (select rank() over(order by po_views desc) as `순위`, post.* from post) as po
where `순위` <= 3;
