--cleaning up tables

drop table if exists users;
drop table if exists user_device;
drop table if exists user_token;
drop table if exists user_bank_card;

--creating tables

create table users (
	id int AUTO_INCREMENT primary key,
	user_id int,
	name nvarchar(250) not null,
	email nvarchar(250) not null
);

create table user_device (
	id int AUTO_INCREMENT primary key,
	user_id int not null,
	device_hash nvarchar(250) not null
);

create table user_token (
	id int AUTO_INCREMENT primary key,
	user_id int not null,
	token nvarchar(2000) not null
);

create table user_bank_card (
	id int AUTO_INCREMENT primary key,
	user_id int not null,
	card_id nvarchar(100) not null,
	card_number nvarchar(100) not null,
	cvc nvarchar(3) not null,
	name nvarchar(250) not null,
	amount int not null,
	currency nvarchar(50) not null
);

--inserting data into tables

insert into users (user_id, name, email) values 
	(1000, 'Teszt Aladár', 'teszt.aladar@otpmobil.com'),
	(2000, 'Teszt Benedek', 'teszt.benedek@otpmobil.com'),
	(3000, 'Teszt Cecília', 'teszt.cecilia@otpmobil.com');

insert into user_device (user_id, device_hash) values 
	(1000,'F67C2BCBFCFA30FCCB36F72DCA22A817'),
	(1000,'0F1674BD19D3BBDD4C39E14734FFB876'),
	(1000,'3AE5E9658FBD7D4048BD40820B7D227D'),
	(2000,'FADDFEA562F3C914DCC81956682DB0FC'),
	(3000,'E68560872BDB2DF2FFE7ADC091755378');

insert into user_token (user_id, token) values 
	(1000,'dGVzenQuYWxhZGFyQG90cG1vYmlsLmNvbSYxMDAwJkY2N0MyQkNCRkNGQTMwRkNDQjM2RjcyRENBMjJBODE3'),
	(2000,'dGVzenQuYmVuZWRla0BvdHBtb2JpbC5jb20mMjAwMCZGQURERkVBNTYyRjNDOTE0RENDODE5NTY2ODJEQjBGQw=='),
	(3000,'dGVzenQuY2VjaWxpYUBvdHBtb2JpbC5jb20mMzAwMCZFNjg1NjA4NzJCREIyREYyRkZFN0FEQzA5MTc1NTM3OA=='),
	(1000,'dGVzenQuYWxhZGFyQG90cG1vYmlsLmNvbSYxMDAwJjBGMTY3NEJEMTlEM0JCREQ0QzM5RTE0NzM0RkZCODc2'),
	(1000,'dGVzenQuYWxhZGFyQG90cG1vYmlsLmNvbSYxMDAwJjNBRTVFOTY1OEZCRDdENDA0OEJENDA4MjBCN0QyMjdE');

insert into user_bank_card (user_id, card_id, card_number, cvc, name, amount, currency) values 
	(1000,'C0001','5299706965433676','123','Teszt Aladár',1000,'HUF'),
	(2000,'C0002','5390508354245119','456','Teszt Benedek',2000,'HUF'),
	(3000,'C0003','4929088924014470','789','Teszt Cecília',3000,'HUF');