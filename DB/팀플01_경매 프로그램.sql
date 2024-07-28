DROP DATABASE IF EXISTS AUCTION;
CREATE DATABASE AUCTION;

USE AUCTION;

DROP TABLE IF EXISTS `member`;

CREATE TABLE `member` (
	`me_id`	varchar(12) primary key	NOT NULL,
	`me_password`	varchar(14)	NOT NULL,
	`me_name`	varchar(10)	NOT NULL,
	`me_address`	varchar(35)	NOT NULL,
	`me_contact`	varchar(13)	NOT NULL
);

DROP TABLE IF EXISTS `auction`;

CREATE TABLE `auction` (
	`au_num`	int primary key auto_increment	NOT NULL,
	`au_date`	datetime default current_timestamp	NOT NULL,
	`au_name`	varchar(20)	NOT NULL,
	`au_start_price`	int	NOT NULL,
	`au_winning_bid`	int	NULL,
	`au_me_id`	varchar(12)	NULL
);

DROP TABLE IF EXISTS `bid`;

CREATE TABLE `bid` (
	`bi_num`	int primary key auto_increment	NOT NULL,
	`bi_price`	int	NOT NULL,
	`bi_au_num`	int	NOT NULL,
	`bi_me_id`	varchar(12)	NOT NULL
);


ALTER TABLE `auction` ADD CONSTRAINT `FK_member_TO_auction_1` FOREIGN KEY (
	`au_me_id`
)
REFERENCES `member` (
	`me_id`
);

ALTER TABLE `bid` ADD CONSTRAINT `FK_auction_TO_bid_1` FOREIGN KEY (
	`bi_au_num`
)
REFERENCES `auction` (
	`au_num`
);

ALTER TABLE `bid` ADD CONSTRAINT `FK_member_TO_bid_1` FOREIGN KEY (
	`bi_me_id`
)
REFERENCES `member` (
	`me_id`
);

