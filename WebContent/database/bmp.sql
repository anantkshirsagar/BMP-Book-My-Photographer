create database if not exists bmp;
use bmp;
create table if not exists photographer(
id int primary key auto_increment,
name varchar(255),
city varchar(255),
email varchar(255),
password varchar(255),
mobile_no bigint,
camera_type varchar(255),
status varchar(255),
category varchar(255));
drop table photographer;
select * from photographer;

create table if not exists  photo(
id int primary key auto_increment,
photo_name varchar(255),
photo longblob,
photographer_id int references photographer on delete cascade on update cascade);
drop table photo;
select * from photo;

create table if not exists customer(
id int primary key auto_increment,
name varchar(255),
email varchar(255),
password varchar(255),
mobile_no bigint);
drop table customer;
select * from customer;

create table if not exists order_request(
id int primary key auto_increment,
title varchar(255),
order_date date,
order_time time,
address varchar(255),
camera_type  varchar(255),
note varchar(255),
customer_id int references customer on delete cascade on update cascade,   
photographer_id int references photographer on delete cascade on update cascade,
feedback_id int references feedback on delete cascade on update cascade,
status varchar(255));
drop table order_request;
select * from order_request;

create table if not exists  feedback(
id int primary key auto_increment,
feedback varchar(255),
customer_id int references customer on delete cascade on update cascade,   
photographer_id int references photographer on delete cascade on update cascade);
drop table feedback;
select * from feedback;