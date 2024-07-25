DROP DATABASE IF EXISTS CGV;

CREATE DATABASE CGV;

USE CGV;

DROP TABLE IF EXISTS `movie`;

CREATE TABLE `movie` (
	`mo_num`	int primary key auto_increment	NOT NULL,
	`mo_title`	varchar(255)	NOT NULL,
	`mo_content`	longtext	NOT NULL,
	`mo_time`	int	NOT NULL,
	`mo_age`	varchar(20)	NOT NULL,
	`mo_date`	date	NOT NULL
);

DROP TABLE IF EXISTS `genre`;

CREATE TABLE `genre` (
	`ge_name`	varchar(10) primary key	NOT NULL
);

DROP TABLE IF EXISTS `movie_genre`;

CREATE TABLE `movie_genre` (
	`mg_num`	int primary key auto_increment	NOT NULL,
	`mg_ge_name`	varchar(10)	NOT NULL,
	`mg_mo_num`	int	NOT NULL
);

DROP TABLE IF EXISTS `country`;

CREATE TABLE `country` (
	`co_name`	varchar(20) primary key	NOT NULL
);

DROP TABLE IF EXISTS `movie_country`;

CREATE TABLE `movie_country` (
	`mc_num`	int primary key auto_increment	NOT NULL,
	`mc_mo_num`	int	NOT NULL,
	`mc_co_name`	varchar(20)	NOT NULL
);

DROP TABLE IF EXISTS `casting`;

CREATE TABLE `casting` (
	`ca_num`	int primary key auto_increment	NOT NULL,
	`ca_name`	varchar(20)	NOT NULL,
	`ca_mo_num`	int	NOT NULL,
	`ca_ch_num`	int	NOT NULL
);

DROP TABLE IF EXISTS `person`;

CREATE TABLE `person` (
	`pe_num`	int primary key auto_increment	NOT NULL,
	`ps_name`	varchar(30)	NOT NULL,
	`ps_birth`	date	NOT NULL,
	`ps_detail`	longtext	NOT NULL,
	`ps_country`	varchar(30)	NOT NULL
);

DROP TABLE IF EXISTS `file`;

CREATE TABLE `file` (
	`fi_num`	int primary key auto_increment	NOT NULL,
	`fi_name`	varchar(255)	NOT NULL,
	`fi_type`	varchar(10)	NOT NULL,
	`fi_mo_num`	int	NOT NULL
);

DROP TABLE IF EXISTS `theater`;

CREATE TABLE `theater` (
	`th_num`	int primary key auto_increment	NOT NULL,
	`th_name`	varchar(30)	NOT NULL,
	`th_screen`	int	NOT NULL,
	`th_seat`	int	NOT NULL,
	`th_address`	varchar(255)	NOT NULL,
	`th_region`	varchar(10)	NOT NULL
);

DROP TABLE IF EXISTS `schedule`;

CREATE TABLE `schedule` (
	`sd_num`	int primary key auto_increment	NOT NULL,
	`sd_time`	time	NOT NULL,
	`sd_date`	date	NOT NULL,
	`sd_early`	int	NOT NULL,
	`sd_mo_num`	int	NOT NULL,
	`sd_sc_num`	int	NOT NULL
);

DROP TABLE IF EXISTS `screen`;

CREATE TABLE `screen` (
	`sc_num`	int primary key auto_increment	NOT NULL,
	`sc_name`	varchar(30)	NOT NULL,
	`sc_seat`	int	NOT NULL,
	`sc_th_num`	int	NOT NULL
);

DROP TABLE IF EXISTS `seat`;

CREATE TABLE `seat` (
	`se_num`	int primary key auto_increment	NOT NULL,
	`se_name`	varchar(3)	NOT NULL,
	`se_sc_num`	int	NOT NULL
);

DROP TABLE IF EXISTS `member`;

CREATE TABLE `member` (
	`me_id`	varchar(15) primary key	NOT NULL,
	`me_pw`	varchar(255)	NOT NULL,
	`me_authority`	varchar(5)	NOT NULL
);

DROP TABLE IF EXISTS `ticketing`;

CREATE TABLE `ticketing` (
	`ti_num`	int primary key auto_increment	NOT NULL,
	`ti_adult`	int	NOT NULL,
	`ti_teenager`	int	NOT NULL,
	`ti_total`	int	NOT NULL,
	`ti_sd_num`	int	NOT NULL,
	`ti_me_id`	varchar(15)	NOT NULL
);

DROP TABLE IF EXISTS `price`;

CREATE TABLE `price` (
	`pr_num`	int primary key auto_increment	NOT NULL,
	`pr_type`	varchar(10)	NOT NULL,
	`pr_price`	int	NOT NULL
);

DROP TABLE IF EXISTS `character`;

CREATE TABLE `character` (
	`ch_num`	int primary key auto_increment	NOT NULL,
	`ch_role`	char(2)	NOT NULL,
	`ch_pic`	varchar(255)	NULL,
	`ch_pe_num`	int	NOT NULL
);
DROP TABLE IF EXISTS `ticketing_list`;

CREATE TABLE `ticketing_list` (
	`tl_num`	int primary key auto_increment	NOT NULL,
	`ti_num`	int	NOT NULL,
	`se_num`	int	NOT NULL
);

ALTER TABLE `ticketing_list` ADD CONSTRAINT `FK_ticketing_TO_ticketing_list_1` FOREIGN KEY (
	`ti_num`
)
REFERENCES `ticketing` (
	`ti_num`
);

ALTER TABLE `ticketing_list` ADD CONSTRAINT `FK_seat_TO_ticketing_list_1` FOREIGN KEY (
	`se_num`
)
REFERENCES `seat` (
	`se_num`
);



ALTER TABLE `movie_genre` ADD CONSTRAINT `FK_genre_TO_movie_genre_1` FOREIGN KEY (
	`mg_ge_name`
)
REFERENCES `genre` (
	`ge_name`
);

ALTER TABLE `movie_genre` ADD CONSTRAINT `FK_movie_TO_movie_genre_1` FOREIGN KEY (
	`mg_mo_num`
)
REFERENCES `movie` (
	`mo_num`
);

ALTER TABLE `movie_country` ADD CONSTRAINT `FK_movie_TO_movie_country_1` FOREIGN KEY (
	`mc_mo_num`
)
REFERENCES `movie` (
	`mo_num`
);

ALTER TABLE `movie_country` ADD CONSTRAINT `FK_country_TO_movie_country_1` FOREIGN KEY (
	`mc_co_name`
)
REFERENCES `country` (
	`co_name`
);

ALTER TABLE `casting` ADD CONSTRAINT `FK_movie_TO_casting_1` FOREIGN KEY (
	`ca_mo_num`
)
REFERENCES `movie` (
	`mo_num`
);

ALTER TABLE `casting` ADD CONSTRAINT `FK_character_TO_casting_1` FOREIGN KEY (
	`ca_ch_num`
)
REFERENCES `character` (
	`ch_num`
);

ALTER TABLE `file` ADD CONSTRAINT `FK_movie_TO_file_1` FOREIGN KEY (
	`fi_mo_num`
)
REFERENCES `movie` (
	`mo_num`
);

ALTER TABLE `schedule` ADD CONSTRAINT `FK_movie_TO_schedule_1` FOREIGN KEY (
	`sd_mo_num`
)
REFERENCES `movie` (
	`mo_num`
);

ALTER TABLE `schedule` ADD CONSTRAINT `FK_screen_TO_schedule_1` FOREIGN KEY (
	`sd_sc_num`
)
REFERENCES `screen` (
	`sc_num`
);

ALTER TABLE `screen` ADD CONSTRAINT `FK_theater_TO_screen_1` FOREIGN KEY (
	`sc_th_num`
)
REFERENCES `theater` (
	`th_num`
);

ALTER TABLE `seat` ADD CONSTRAINT `FK_screen_TO_seat_1` FOREIGN KEY (
	`se_sc_num`
)
REFERENCES `screen` (
	`sc_num`
);

ALTER TABLE `ticketing` ADD CONSTRAINT `FK_schedule_TO_ticketing_1` FOREIGN KEY (
	`ti_sd_num`
)
REFERENCES `schedule` (
	`sd_num`
);

ALTER TABLE `ticketing` ADD CONSTRAINT `FK_member_TO_ticketing_1` FOREIGN KEY (
	`ti_me_id`
)
REFERENCES `member` (
	`me_id`
);

ALTER TABLE `character` ADD CONSTRAINT `FK_person_TO_character_1` FOREIGN KEY (
	`ch_pe_num`
)
REFERENCES `person` (
	`pe_num`
);

ALTER TABLE `cgv`.`schedule` 
ADD COLUMN `sd_possible` INT NOT NULL AFTER `sd_sc_num`;


