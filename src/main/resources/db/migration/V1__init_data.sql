CREATE TABLE products (id bigserial primary key, title varchar(255), price int, description varchar(255));
INSERT INTO products (title, price, description)
VALUES
('Milk', 95, '100% cow milk'),
('Bread', 40, 'first category flour'),
('Beef', 200, 'black angus beef');
