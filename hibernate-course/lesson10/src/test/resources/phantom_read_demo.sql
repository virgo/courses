truncate employee;
insert into employee(id, name, version) values (1,'First', 1);
insert into employee(id, name, version) values (2,'Second', 1);
insert into employee(id, name, version) values (3,'Third', 2);
select * from employee;

-- demonstrate phantom read
begin transaction ISOLATION LEVEL READ COMMITTED ;
select * from employee where version = 1;
select pg_sleep(10);
select * from employee where version = 1;
commit;

-- prevent phantom read
begin transaction ISOLATION LEVEL REPEATABLE READ;
select * from employee where version = 1;
select pg_sleep(10);
select * from employee where version = 1;
commit;

-- insert into employee(id, name, version) values (4,'Fourth', 1);
