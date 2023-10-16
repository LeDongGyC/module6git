use a0622i1_coffee;
SET FOREIGN_KEY_CHECKS=0;
INSERT INTO feedback (fb_id,bill_id,name,email,date,content,type_id,rate) values
('FB_001',1,'Lê Thị Hà','halt@gmail.com','01-06-2023','comment Lê Thị Hà',1,5),
('FB_002',2,'Trung Quân','halt@gmail.com','02-06-2023','comment Trung Quân',2,2),
('FB_003',3,'Lê Việt Hà','halt@gmail.com','05-06-2023','nội dung đặc biệt',2,3);
SET FOREIGN_KEY_CHECKS=0;
INSERT INTO feedback_type (type) values
('Đồ ăn'),
('Đồ uống');
SET FOREIGN_KEY_CHECKS=0;
INSERT INTO account (user_name,password,verification_code,email, enable_flag) values
('sysadmin','sysadmin','dqdsqdqsdq1dw1dw1d','admin@gmail.com',1),
('user','sysadmin','sdqsdqd121d1wdw1','user@gmail.com',1);
SET FOREIGN_KEY_CHECKS=0;
INSERT INTO role (name) values
('admin'),
('user');
SET FOREIGN_KEY_CHECKS=0;
INSERT INTO account_role (account_id,role_id) values
(1,1),
(2,2);
SET FOREIGN_KEY_CHECKS=0;
INSERT INTO user (name,address,phone_number,birthday,gender,salary,position_id,account_id,enable_flag,imgUrl) values
('Lê Gia Tú','Quảng Trị','0123456789','1991-05-10',1,10000000,1,1,0,'url img1'),
('Lê Thị Việt Hà','Quảng Trị','0123456789','1994-01-01',0,11000000,2,2,0,'url img1'),
('Huỳnh Minh Cường','Đà Nẵng','0123456789','1994-01-01',1,11000000,1,3,0,'url img2'),
('Lê Văn An','Quảng Nam','0123456789','1994-01-01',0,11000000,1,4,0,'url img3'),
('Trần Sĩ Tiến','Huế','0123456789','1994-01-01',1,11000000,1,5,0,'url img4'),
('nobody','','','',1,1,1,5,0,'url img4');
delimiter //
create procedure sp_deleteUser(IN deleteId int)
begin
	SET FOREIGN_KEY_CHECKS=0;
	delete from user where id = deleteId;
end//
delimiter ;
SET FOREIGN_KEY_CHECKS=0;
INSERT INTO `position` (name) values
('Chủ tiệm'),
('phó chủ tiệm');
SET FOREIGN_KEY_CHECKS=0;
INSERT INTO service (name,price,type_id,enable_flag,imgUrl) values
('Caffee 1',50000,1,1,'img 1'),
('Caffee 2',50000,1,1,'img 2'),
('cake 1',60000,2,1,'img 3'),
('cake 2',60000,2,1,'img 4'),
('cake 3',60000,2,1,'img 5');
SET FOREIGN_KEY_CHECKS=0;
INSERT INTO service_type (name) values
('đồ uống'),
('đồ ăn'),
('Trái cây');
SET FOREIGN_KEY_CHECKS=0;
INSERT INTO `table` (name,status,enable_flag) values
('bàn vuông','tốt',1),
('bàn tròn','tốt',1),
('bàn cam','xấu',0),
('bàn đỏ','xấu',1),
('bàn lục','tốt',1),
('bàn lam','tốt',0),
('bàn tím','xấu',1),
('bàn vàng','tốt',0),
('bàng đen','xấu',0),
('bàn nâu','tốt',1);
SET FOREIGN_KEY_CHECKS=0;
INSERT INTO bill (created_time,user_id,table_id,payment_status,payment_time) values
('01-06-2023 09:50:30',1,2,1,'01-06-2023 11:10:30'),
('01-06-2023 10:50:30',1,1,1,'01-06-2023 11:10:30'),
('01-06-2023 11:00:30',1,2,1,'01-06-2023 11:10:30'),
('01-06-2023 12:50:30',1,1,1,'01-06-2023 15:10:30'),
('01-06-2023 10:40:30',1,2,1,'01-06-2023 12:10:30'),
('02-06-2023 10:40:30',2,10,1,'02-06-2023 11:40:30'),
('03-06-2023 10:40:30',3,2,0,'03-06-2023 11:40:30'),
('04-06-2023 10:40:30',5,4,0,'04-06-2023 11:40:30'),
('05-06-2023 10:40:30',4,5,0,'05-06-2023 11:40:30'),
('06-06-2023 10:40:30',1,3,0,'06-06-2023 15:10:30'),
('07-06-2023 10:40:30',2,2,1,'07-06-2023 15:10:30'),
('08-06-2023 10:40:30',3,5,1,'08-06-2023 15:10:30'),
('09-06-2023 10:40:30',4,5,1,'09-06-2023 15:10:30'),
('10-06-2023 10:40:30',5,2,0,'10-06-2023 15:10:30'),
('11-06-2023 10:40:30',5,7,1,'01-06-2023 15:10:30'),
('12-06-2023 10:40:30',1,8,0,'01-06-2023 15:10:30'),
('01-06-2023 10:40:30',2,4,1,'01-06-2023 15:10:30'),
('01-06-2023 10:40:30',4,9,0,'01-06-2023 15:10:30'),
('01-06-2023 10:40:30',3,3,1,'01-06-2023 15:10:30'),
('01-06-2023 10:40:30',2,6,0,'01-06-2023 15:10:30'),
('01-06-2023 10:40:30',4,7,0,'01-06-2023 15:10:30'),
('01-06-2023 10:40:30',2,1,1,'01-06-2023 15:10:30'),
('01-06-2023 10:40:30',5,4,0,'01-06-2023 15:10:30'),
('01-06-2023 10:40:30',5,2,0,'01-06-2023 15:10:30'),
('01-06-2023 10:40:30',3,7,0,'01-06-2023 15:10:30'),
('01-06-2023 10:40:30',5,8,1,'01-06-2023 15:10:30'),
('01-06-2023 10:40:30',1,9,1,'01-06-2023 15:10:30'),
('01-06-2023 10:40:30',4,10,0,'01-06-2023 15:10:30'),
('01-06-2023 10:40:30',2,5,1,'01-06-2023 15:10:30'),
('01-06-2023 10:40:30',5,3,0,'01-06-2023 15:10:30'),
('01-06-2023 10:40:30',2,6,1,'01-06-2023 15:10:30'),
('01-06-2023 10:40:30',3,8,1,'01-06-2023 15:10:30'),
('01-06-2023 10:40:30',5,5,0,'01-06-2023 15:10:30'),
('01-06-2023 10:40:30',3,3,1,'01-06-2023 15:10:30');
SET FOREIGN_KEY_CHECKS=0;
INSERT INTO bill_detail (bill_id,service_id,quantity) values
(1,1,5),
(1,2,1),
(2,1,3),
(2,3,3),
(2,2,10),
(4,1,2),
(4,2,4),
(4,3,3),
(5,5,3),
(6,1,5),
(7,2,2),
(8,3,9),
(9,4,7),
(10,5,2),
(11,3,6),
(12,1,3),
(13,1,3),
(14,2,5),
(15,3,4),
(15,4,5),
(16,1,3),
(16,5,2),
(17,4,1),
(18,2,3),
(19,1,3),
(20,3,2),
(21,1,3),
(22,2,2),
(23,3,8),
(24,4,4),
(25,5,3),
(26,2,5),
(27,1,7),
(28,3,6),
(29,4,5),
(30,5,3),
(31,3,3),
(32,1,2),
(33,1,3),
(34,5,4),
(5,4,1),
(22,1,3),
(11,5,4),
(34,4,5),
(5,1,6),
(6,2,3),
(4,4,4),
(2,5,3),
(1,5,3),
(33,3,7),
(11,2,3),
(23,1,5),
(11,2,3),
(25,4,2),
(26,5,1),
(27,3,6),
(28,1,4);
SET FOREIGN_KEY_CHECKS=0;
INSERT INTO feedback_img (feedback_id, imgUrl) values
(1,'kjhqdkjhsqkjhd'),
(1,'kjhqdkjhsqkjhd'),
(1,'qksjdhkqhsdjqsd'),
(1,'kqjhdqsjkhdkjhqsd'),
(1,'qkjdshqjshdkqsd');
