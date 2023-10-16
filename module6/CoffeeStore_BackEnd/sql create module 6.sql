drop schema a0622i1_coffee;
create schema a0622i1_coffee;
use a0622i1_coffee;
create table feedback(
id INT auto_increment primary key,
fb_id NVARCHAR(9),
bill_id INT,
name NVARCHAR(200),
email NVARCHAR(200),
date NVARCHAR(30),
content NVARCHAR(1000),
type_id INT,
rate NVARCHAR(2)
);
create table feedback_type(
id INT auto_increment primary key,
type NVARCHAR(50)
);
create table account(
id INT auto_increment primary key,
user_name NVARCHAR(50),
password NVARCHAR(255),
verification_code NVARCHAR(50),
email NVARCHAR(50),
enable_flag BIT,
change_password_date DATE
);
create table role(
id INT auto_increment primary key,
name NVARCHAR(50)
);
create table account_role(
id INT auto_increment primary key,
account_id INT,
role_id INT
);
create table user(
id INT auto_increment primary key,
name NVARCHAR(255),
address NVARCHAR(255),
phone_number NVARCHAR(13),
birthday NVARCHAR(12),
gender INT,
salary DOUBLE,
position_id INT,
account_id INT,
enable_flag BIT,
img_url NVARCHAR(1000)
);
create table `position`(
id INT auto_increment primary key,
name NVARCHAR(50)
);
create table service(
id INT auto_increment primary key,
name NVARCHAR(50),
price DOUBLE,
type_id INT,
enable_flag BIT,
img_url NVARCHAR(1000),
created_date NVARCHAR(12)
);
create table service_type(
id INT auto_increment primary key,
name NVARCHAR(50)
);
create table `table`(
id INT auto_increment primary key,
name NVARCHAR(255),
status NVARCHAR(50),
enable_flag BIT
);
create table bill(
id INT auto_increment primary key,
created_time NVARCHAR(50),
user_id INT,
table_id INT,
payment_status BIT,
payment_time NVARCHAR(50)
);
create table bill_detail(
id INT auto_increment primary key,
bill_id INT,
service_id INT,
quantity INT
);
create table feedback_img(
id INT auto_increment primary key,
feedback_id INT,
img_url NVARCHAR(1000)
);
alter table feedback add foreign key(bill_id) references bill(id);
alter table feedback add foreign key(type_id) references feedback_type(id);
alter table account_role add foreign key(account_id) references account(id);
alter table account_role add foreign key(role_id) references role(id);
alter table user add foreign key(position_id) references `position`(id);
alter table user add foreign key(account_id) references account(id);
alter table service add foreign key(type_id) references service_type(id);
alter table bill add foreign key(user_id) references user(id);
alter table bill add foreign key(table_id) references `table`(id);
alter table bill_detail add foreign key(bill_id) references bill(id);
alter table bill_detail add foreign key(service_id) references service(id);
alter table feedback_img add foreign key(feedback_id) references feedback(id);