create database if not exists bmp;
use bmp;
create table if not exists photographer(
id bigint primary key auto_increment,
name varchar(255),
city varchar(255),
email varchar(255),
password varchar(255),
mobile_no bigint,
camera_type varchar(255),
is_approved boolean,
category varchar(255));
drop table photographer;
select * from photographer;

create table if not exists customer(
id bigint primary key,
name varchar(255),
email varchar(255),
password varchar(255),
mobile_no bigint);

create table if not exists order_request(
id bigint primary key,
order_date date,
order_time time,
address varchar(255),
category  varchar(255),
camera_type  varchar(255),
note varchar(255),
user_id bigint references user on delete cascade on update cascade,   
photographer_id bigint references photographer on delete cascade on update cascade,
status varchar(255));

create table if not exists  order_request(
id bigint primary key,
feedback varchar(255),
user_id bigint references user on delete cascade on update cascade,   
photographer_id bigint references photographer on delete cascade on update cascade);

create table if not exists  order_request(
id bigint primary key,
photo longblob,
photographer_id bigint references photographer on delete cascade on update cascade);