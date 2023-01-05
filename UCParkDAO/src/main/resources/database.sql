create database if not exists ucpark;

use ucpark;

create table Ucpark_user (
	email varchar(100) primary key,
	password varchar(16) not null,

	constraint ck_email check (email like '%@%.%')
);

create table Vehicle (
	numberPlate char(8) primary key,
	brand varchar(10) not null,
	model varchar(10) not null,
	owner varchar(100) not null,

	constraint fk_vehicle_user foreign key (owner) references ucpark_user (email) 
);

create table Parking (
	id int primary key,
	startTime datetime not null, 
	minutes int  not null,
	price decimal(4,2),
	vehicle char(8) not null,
	
	constraint fk_parking_vehicle foreign key (vehicle) references vehicle(numberPlate)
);

create table Report (
	id int primary key,
	reportDate date not null,
	price decimal(6,2) not null,
	cause varchar(1000) not null,
	vehicle char(8) not null,
	offender varchar(100) not null,
	
	constraint fk_report_vehicle foreign key (vehicle) references vehicle(numberPlate),
	constraint fk_report_user foreign key (offender) references ucpark_user(email)
);	

create table Payment (
	id int primary key,
	assignedTo varchar(100) not null,

	constraint fk_payment_owner foreign key (assignedTo) references ucpark_user (email)
);

create table Card (
	id int,

	number char(16) not null,
	cvc char(3) not null,
	owner char(25) not null,

	constraint fk_payment foreign key (id) references payment(id)
);