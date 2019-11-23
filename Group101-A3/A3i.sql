insert into VehicleType values ('Economy','comfort',3,3,3,3,3,3,3);
insert into VehicleType values ('SUV','comfort',3,3,3,3,3,3,3);
insert into VehicleType values ('Compact','Luxury',3,3,3,3,3,3,3);
insert into VehicleType values ('Mid-size','Very Luxury',6,6,6,6,6,6,6);
insert into VehicleType values ('Full-size','Luxury',10,10,10,10,10,10,10);
insert into VehicleType values ('Truck','',10,10,10,8,8,8,8);

insert into Reservations values (1, 'Full-size', 11111,TO_DATE('01-JUN-2019 12:30', 'DD-MON-YYYY HH24:MI'),TO_DATE('01-JAN-1995 13:30', 'DD-MON-YYYY HH24:MI'));
insert into Reservations values (2, 'Compact', 2222,TO_DATE('01-JAN-1995 12:30', 'DD-MON-YYYY HH24:MI'),TO_DATE('01-JAN-1995 11:30', 'DD-MON-YYYY HH24:MI'));
insert into Reservations values (3, 'Truck', 3333, TO_DATE('01-SEP-2018 08:30', 'DD-MON-YYYY HH24:MI'),TO_DATE('01-JAN-1994 11:30', 'DD-MON-YYYY HH24:MI'));
insert into Reservations values (4, 'Economy', 44444,TO_DATE('01-JAN-1995 12:30', 'DD-MON-YYYY HH24:MI'),TO_DATE('01-JAN-1996 11:30', 'DD-MON-YYYY HH24:MI'));

insert into Vehicles values (1,'VV888','Lamborghini','Aventador',TO_DATE('2019','YYYY'),'Green',30000,'for_ rent','Full-size','UBC','Vancouver');
insert into Vehicles values (2,'ABC66','Benz','S800',TO_DATE('2010','YYYY'),'Black',84560,'for_ rent','Economy','UT','Toronto');
insert into Vehicles values (3,'QQ909','Ford','F-150',TO_DATE('2019','YYYY'),'Silver',30000,'for_ rent','Truck','UBC','Vancouver');
insert into Vehicles values (4,'V8P88','Volvo','XC60 Hbybrid',TO_DATE('2017','YYYY'),'White',80000,'for_ rent','SUV','Richmond','Vancouver');
insert into Vehicles values (5,'B6232','Lamborghini','Aventador',TO_DATE('2017','YYYY'),'Purple',0,'for_ rent','Full-size','Fairmount','Halifax');

insert into Rent values (1,1,6040008900,TO_DATE('01-NOV-2019 12:30', 'DD-MON-YYYY HH24:MI'),TO_DATE('02-NOV-2019 12:30', 'DD-MON-YYYY HH24:MI'),30000,'Albert',4536580098563256,TO_DATE('01-NOV-2021','DD-MON-YYYY'),1);
insert into Rent values (2,3,6040000000,TO_DATE('06-NOV-2019 12:30', 'DD-MON-YYYY HH24:MI'),TO_DATE('07-NOV-2019 12:30', 'DD-MON-YYYY HH24:MI'),8000,'Hugo',4585695584555555,TO_DATE('01-JAN-2021','DD-MON-YYYY'),null);

commit work;