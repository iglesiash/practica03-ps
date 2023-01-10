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
	owner_fk varchar(100) not null,

	foreign key (owner_fk) references ucpark_user (email) 
);

create table Parking (
	id int primary key,
	startTime datetime not null, 
	minutes int  not null,
	price decimal(4,2),
	vehicle_fk char(8) not null,
	
	foreign key (vehicle_fk) references vehicle(numberPlate)
);

create table Report (
	id int primary key,
	reportDate date not null,
	price decimal(6,2) not null,
	cause varchar(1000) not null,
	vehicle_fk char(8) not null,
	offender_fk varchar(100) not null,
	
	foreign key (vehicle_fk) references vehicle(numberPlate),
	foreign key (offender_fk) references ucpark_user(email)
);

create table Card (
	id int primary key,

	number char(16) not null,
	cvc char(3) not null,
	owner char(25) not null,

);