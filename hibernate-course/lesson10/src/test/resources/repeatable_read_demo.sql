truncate employee;
insert into employee(id, name, version) values (1,'First', 1);
insert into employee(id, name, version) values (2,'Second', 1);
insert into employee(id, name, version) values (3,'Third', 2);
select * from employee;

-- update employee set name = 'Valami' where id = 1;

begin transaction ISOLATION LEVEL REPEATABLE READ;
select * from employee where id = 1;

select pg_sleep(10);

select * from employee where id = 1;

commit;