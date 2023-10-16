use a0622i1_coffee;
-- Quản lý phản hồi
select f.id, f.fb_id , f.name, f.email, ft.type, f.date from feedback f
left join feedback_type ft on f.type_id = ft.id
-- where f.date = '2022-06-01'
order by f.id asc;
-- chi tiết phản hồi
select f.rate, ft.type ,f.name, f.content from feedback f
left join feedback_type ft on f.type_id = ft.id
where f.id = 3;
-- get img url
select f.id , fi.imgUrl from feedback f
left join feedback_img fi on f.id= fi.feedback_id
where f.id = 2
order by fi.id asc;
-- order
select t.id as table_id, s.imgUrl ,s.name, sum(bd.quantity) quantity, s.price, t.name, quantity*s.price as sum, b.payment_status 
from bill b
join `table` t on b.table_id = t.id
join bill_detail bd on b.id = bd.bill_id
join service s on bd.service_id = s.id
where payment_status = 0 and table_id = 2
group by s.id
order by table_id asc;
-- quản lý bill
select b.id, b.user_id, b.table_id, sum(bd.quantity*s.price) sum from bill b
join bill_detail bd on bd.bill_id = b.id
join service s on s.id = bd.service_id
group by b.id;
-- feedback
select * from feedback;
-- payment
select b.id as bill_id, sum(bd.quantity*s.price) as sum from bill b
join `table` t on b.table_id = t.id
join bill_detail bd on b.id = bd.bill_id
join service s on bd.service_id = s.id
where bill_id = 1
group by bill_id;
-- tạo sp để insert bill và bill_detail mỗi lần submit event order
drop procedure sp_create_new_order;
delimiter //
create procedure sp_create_new_order(IN table_id int, IN service_id int, IN quantity int)
begin
DECLARE bill_id INT;
    DECLARE newId INT ;
	DECLARE nowtime nvarchar (30) ;
    DECLARE sql_insert nvarchar (1000) ;
    DECLARE sql_insert2 nvarchar (1000) ;
    SET bill_id = (select id from bill order by id desc limit 1);
    SET newId = bill_id + 1;
    SET nowtime = NOW();
    SET sql_insert = CONCAT( 'INSERT INTO bill (created_time,user_id,table_id,payment_status,payment_time) values ("',nowtime,'",6,',table_id,',0,"',nowtime,'");');
    SET sql_insert2 = CONCAT( 'INSERT INTO bill_detail (bill_id,service_id,quantity) values(',newId,',',service_id,',',quantity,');');
select sql_insert,sql_insert2;
end//
delimiter ;
call sp_create_new_order(3, 5, 2);
truncate table feedback;
-- get list user STT	Tài Khoản	Họ tên	Địa chỉ	SDT	Giới tính	Ngày sinh	Lương	Chức vụ	
use a0622i1_coffee;

select * from account;
select * from user;
select u.id, a.user_name account, u.name userName, u.address, u.phone_number PhoneNumber, u.gender, u.birthday,
u.salary, p.name position, u.enable_flag  from user u 
join account a on u.account_id = a.id
join position p on p.id = u.position_id
-- and u.birthday = "1994-01-01" and u.name like "%%"
order by u.id;


drop procedure sp_deleteUser;
delimiter //
create procedure sp_deleteUser(IN deleteId int)
begin
	SET FOREIGN_KEY_CHECKS=0;
	delete from user where id = deleteId;
end//
delimiter ;
call sp_deleteUser(2);
select * from user;
update user set enable_flag = 1 


