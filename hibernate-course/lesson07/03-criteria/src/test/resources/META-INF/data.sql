insert into Product(id, name) values (1, 'Alma');
insert into Product(id, name) values (2, 'Körte');
insert into Product(id, name) values (3, 'Tej');
insert into Product(id, name) values (4, 'Vaj');
insert into Product(id, name) values (5, 'Kenyér');

insert into ORDERS (id, orderNumber) values (1, 'AAA123');
insert into OrderItem (id, quantity, order_id, product_id) values (1,1,1,1);
insert into OrderItem (id, quantity, order_id, product_id) values (2,1,1,2);
insert into OrderItem (id, quantity, order_id, product_id) values (3,2,1,3);

insert into ORDERS (id, orderNumber) values (2, 'BBB456');
insert into OrderItem (id, quantity, order_id, product_id) values (4,1,2,5);
insert into OrderItem (id, quantity, order_id, product_id) values (5,2,2,3);

insert into ORDERS (id, orderNumber) values (3, 'CCC678');
insert into OrderItem (id, quantity, order_id, product_id) values (6,10,3,4);

insert into ORDERS (id, orderNumber) values (4, 'TESZT-1');
insert into OrderItem (id, quantity, order_id, product_id) values (7, 1,4,3);
insert into OrderItem (id, quantity, order_id, product_id) values (8, 1,4,4);
insert into OrderItem (id, quantity, order_id, product_id) values (9, 1,4,5);

insert into ORDERS (id, orderNumber) values (5, 'TESZT-2');
insert into OrderItem (id, quantity, order_id, product_id) values (10,2,5,1);
insert into OrderItem (id, quantity, order_id, product_id) values (11,1,5,3);

insert into Department(id, location) values (1, 'Budapest');
insert into Department(id, location) values (2, 'Szeged');
insert into Department(id, location) values (3, 'London');

insert into Employee (id, name, salary, department_id) values (1, 'Mikkamakka', 1500, 1);
insert into Employee (id, name, salary, department_id) values (2, 'Pampalini', 1500, 2);
insert into Employee (id, name, salary, department_id) values (3, 'Mézga Aladár', 1000, 3);
insert into Employee (id, name, salary, department_id) values (4, 'Mézga Géza', 1500, 3);
insert into Employee (id, name, salary, department_id) values (5, 'Tiszta Gy. Kriszta', 3000, 3);
