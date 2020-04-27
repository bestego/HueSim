insert into lamp(id, omschrijving, aan, helderheid) values(1,'lamp-1',TRUE,75);
insert into lamp(id, omschrijving, aan, helderheid) values(2,'lamp-2',FALSE,50);
insert into lamp(id, omschrijving, aan, helderheid) values(3,'lamp-3',TRUE,25);
insert into groep(id, omschrijving, enkele_aan, alle_aan,lampen) values(0,'allemaal',FALSE,FALSE,'3,2,1');
insert into groep(id, omschrijving, enkele_aan, alle_aan,lampen) values(1,'groep-1',FALSE,FALSE,'1,3');
insert into groep(id, omschrijving, enkele_aan, alle_aan,lampen) values(2,'groep-2',FALSE,FALSE,'2');
insert into groep(id, omschrijving, enkele_aan, alle_aan,lampen) values(3,'groep-leeg',FALSE,FALSE,'88,99');