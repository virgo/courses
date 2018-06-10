insert into Department(id, location) values (1, 'Budapest');
insert into Department(id, location) values (2, 'Szeged');
insert into Department(id, location) values (3, 'London');

insert into Employee (id, name, salary, department_id) values (1, 'Mikkamakka', 1500, 1);
insert into Employee (id, name, salary, department_id) values (2, 'Pampalini', 1500, 2);
insert into Employee (id, name, salary, department_id) values (3, 'Mézga Aladár', 1000, 3);
insert into Employee (id, name, salary, department_id) values (4, 'Mézga Géza', 1500, 3);
insert into Employee (id, name, salary, department_id) values (5, 'Tiszta Gy. Kriszta', 3000, 3);

insert into Project (id, name) values (1, 'Lakásfelújítás');
insert into Project (id, name) values (2, 'Nagytakarítás');
insert into Project (id, name) values (3, 'Kertépítés');
insert into Project (id, name) values (4, 'Csatornázás');

insert into Employee_Project (employees_id, projects_id) values (4,1);
insert into Employee_Project (employees_id, projects_id) values (3,2);
insert into Employee_Project (employees_id, projects_id) values (5,2);
insert into Employee_Project (employees_id, projects_id) values (2,3);
insert into Employee_Project (employees_id, projects_id) values (3,3);
insert into Employee_Project (employees_id, projects_id) values (1,4);
