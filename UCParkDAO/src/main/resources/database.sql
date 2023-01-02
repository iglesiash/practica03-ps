create database ucpark

create table ucpark_user (
	email varchar(100) primary key,
	password varchar(16) not null,

	constraint ck_email check (email like '%@%.%')
)

create table vehicle (
	numberPlate char(8) primary key,
	brand varchar(10) not null,
	model varchar(10) not null,
	owner varchar(100) not null,

	constraint fk_user foreign key (owner) references ucpark_user (email) 
)

create table parking (
	id int primary key,
	startTime datetime not null, 
	endTime datetime, 
	minutes int, --TODO: alter table
	price decimal(4,2), --TODO: alter table
	vehicle char(8) not null,
	
	constraint fk_vehicle foreign key (vehicle) references vehicle(numberPlate)
)

create table report (
	id int primary key,
	reportDate date not null,
	price decimal(6,2) not null,
	cause varchar(1000) not null,
	vehicle char(8) not null,
	
	constraint fk_vehicle foreign key (vehicle) references vehicle(numberPlate)
)	

create table payment (
	id int primary key,
	assignedTo varchar(100) not null,

	constraint fk_payment_owner foreign key (assignedTo) references ucpark_user (email)
)

create table card (
	id int,

	number char(16) not null,
	cvc char(3) not null,
	owner char(25) not null,

	constraint fk_payment foreign key (id) references payment(id)
)

