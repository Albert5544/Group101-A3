create table VehicleType(
	vtname varchar(20) PRIMARY KEY,
	features varchar(20), 
	wrate integer, 
	drate integer, 
	hrate integer, 
	wirate integer, 
	dirate integer, 
	hirate integer, 
	krate integer
);

create table Reservations (
               confNo integer PRIMARY KEY,
               vtname varchar(20),
               cellphone integer not null,
               fromDateTime date not null,
               toDateTime date not null,
			   foreign key (vtname) references VehicleType
);

create table Vehicles (
               vid integer PRIMARY KEY,
			   vlicense varchar(20),
			   make varchar2(20),
			   model varchar2(20),
			   year date,
			   color varchar2(20),
			   odometer integer not null,
			   status varchar(10),
			   vtname varchar2(20),
			   location varchar2(20) not null,
			   city varchar2(30),
			   foreign key (vtname) references VehicleType
);

create table Rent(
	rid integer PRIMARY KEY,
	vid integer not null,
	cellphone integer not null,
	fromDateTime date not null,
	toDateTime date not null,
	odometer integer not null,
	cardName varchar2(30) not null,
	cardNo integer not null,
	ExpDate date not null,
    confNo integer,
	foreign key(confNo) references Reservations
);

create table Customer (
	cellphone integer,
    name varchar2(30),
    address varchar(40),
	dlicense integer
 );


commit;
