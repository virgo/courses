insert into InSample(id, foo) values (1, 'Egyes');
insert into InSample(id, foo) values (2, 'Kettes');
insert into InSample(id, foo) values (3, 'Hármas');
insert into InSample(id, foo) values (4, 'Négyes');
insert into InSample(id, foo) values (5, 'Ötös');
insert into InSample(id, foo) values (6, 'Hatos');
insert into InSample(id, foo) values (7, 'Hetes');
insert into InSample(id, foo) values (8, 'Nyolcas');


insert into Project (id, name) values (1,'EÉR');
insert into Project (id, name) values (2,'FNYR');
insert into Project (id, name) values (3,'Police');
insert into Project (id, name) values (4,'OSzámla');
insert into Project (id, name) values (5,'DPD');

insert into Employee(id, name) values (1, 'Dub Spancer');
insert into Employee(id, name) values (2, 'Trance Hill');

-- insert into Employee_Project(Employee_id, projects_id, project_index) values (1,1,1);
-- insert into Employee_Project(Employee_id, projects_id, project_index) values (1,2,2);
-- insert into Employee_Project(Employee_id, projects_id, project_index) values (1,3,3);
--
-- insert into Employee_Project(Employee_id, projects_id, project_index) values (2,3,1);
-- insert into Employee_Project(Employee_id, projects_id, project_index) values (2,4,2);

insert into Employee_Project(Employee_id, projects_id) values (1,1);
insert into Employee_Project(Employee_id, projects_id) values (1,2);
insert into Employee_Project(Employee_id, projects_id) values (1,3);
--
insert into Employee_Project(Employee_id, projects_id) values (2,3);
insert into Employee_Project(Employee_id, projects_id) values (2,4);


insert into ORDERS (id, orderNumber) values (1, 'AAA123');
insert into OrderItem(id, product, quantity) VALUES (100,'Tej', 1);
insert into OrderItem(id, product, quantity) VALUES (200,'Kenyér', 2);
insert into OrderItem(id, product, quantity) VALUES (300,'Vaj', 1);
insert into ORDERS_OrderItem(Order_id, items_id) values (1,100);
insert into ORDERS_OrderItem(Order_id, items_id) values (1,200);
insert into ORDERS_OrderItem(Order_id, items_id) values (1,300);

insert into ORDERS (id, orderNumber) values (2, 'BBB456');
insert into OrderItem(id, product, quantity) VALUES (400,'Sör', 1);
insert into OrderItem(id, product, quantity) VALUES (500,'Bor', 1);
insert into ORDERS_OrderItem(Order_id, items_id) values (2,400);
insert into ORDERS_OrderItem(Order_id, items_id) values (2,500);

insert into ORDERS (id, orderNumber) values (3, 'CCC678');
insert into OrderItem(id, product, quantity) VALUES (600,'Sör', 1);
insert into ORDERS_OrderItem(Order_id, items_id) values (3,600);
