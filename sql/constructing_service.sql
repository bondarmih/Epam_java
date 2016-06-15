CREATE TABLE customer
(
id INT PRIMARY KEY,
name CHAR(40),
address CHAR(80)
);

CREATE TABLE manager
(
id INT PRIMARY KEY,
name CHAR(40),
date_applied DATE CHECK (date_applied > '2007-10-01')
);

CREATE TABLE project
(id INT PRIMARY KEY,
name CHAR(80),
customer INT REFERENCES customer (id),
manager INT REFERENCES manager (id),
location POINT,
completed BOOL
);

CREATE TABLE estimate
(
id INT PRIMARY KEY,
project INT REFERENCES project(id),
name CHAR(40),
price FLOAT8,
CHECK (price > 0)
);

INSERT INTO customer VALUES
(1, 'Icecream factory', '443001, 7 Leninskaya str, Samara'),
(2, 'Aleksander Ivanov', '40 Tsentralnaya str, Samara'),
(3, 'Engineer market', '28 Sadovaya str, Samara'),
(4, 'Oil plant', '5 Proizvodstvennaya str, Novokuibyshevsk'),
(5, 'SSM', '3 Vokzalnaya str, Novokuibyshevsk');

INSERT INTO manager VALUES
(1, 'Sergey', '2010-07-01'),
(2, 'Mikhail', '2010-10-01'),
(3, 'Andrey', '2012-06-20'),
(4, 'Vasiliy', '2008-03-02'),
(5, 'Ekaterina', '2013-01-10');

INSERT INTO project VALUES
(1, 'Icecream factory electrical input switch installation', 1, 1, POINT (52.45646, 50.64282), true),
(2, 'Oil mixing facility reconstruction', 4, 4, POINT (52.40303, 50.82313), true),
(3, 'Stockhouse repair', 5, 3, POINT (51.99732, 51.63800), true),
(4, 'Bathhouse construction', 2, 2, POINT (51.99630, 50.00301), false),
(5, 'Electrical lighting installation', 3, 5, POINT (53.40210, 50.33030), false);

INSERT INTO estimate VALUES
(1, 1, 'Input switch installation', 103500.50),
(2, 1, 'Input switch connection', 32100.00),
(3, 4, 'Electrical equipment', 130600.00),
(4, 4, 'Electrical lighting', 56820.00),
(5, 4, 'Heating', 33600.00),
(6, 4, 'Water supply', 65321.30),
(7, 3, 'Electrical lighting repair', 10320.00),
(8, 2, 'Sewerage installation', 330600.00),
(9, 2, 'Foundaments', 690300.00),
(10, 2, 'Metal frame installation', 963120.00),
(11, 2, 'Internal trim', 120300.00),
(12, 2, 'Fire alarm system', 37800.00),
(13, 5, 'Electrical lighting', 335690.00),
(14, 5, 'Outdoor cabling', 190630.00);


//Количество смет и сумма договора по сметам
SELECT 
project.name AS Project_Name, 
COUNT(estimate.name) AS Estimates_count, 
SUM(estimate.price) AS Project_price 
FROM project 
INNER JOIN estimate 
ON project.id = estimate.project 
GROUP BY project.name;

//Объем средств по менеджерам для закрытых объектов
SELECT
manager.name, SUM(estimate.price) 
FROM manager, project, estimate 
WHERE manager.id = project.manager 
AND estimate.project = project.id
AND project.completed = 'true'
GROUP BY manager.name;

//Стоимость работ по незакрытым договорам
SELECT 
project.name, SUM(estimate.price)
FROM project 
INNER JOIN estimate
ON project.id = estimate.project
WHERE project.completed = 'false'
GROUP BY project.name;

//Количетво активных договором по менеджерам
SELECT 
manager.name, COUNT(project.id)
FROM manager
LEFT JOIN project
ON project.manager = manager.id
WHERE project.completed = 'false'
GROUP BY manager.name;

//Выручка по заказчикам
SELECT
customer.name, SUM(estimate.price) as sum
FROM project, customer, estimate
WHERE customer.id = project.customer
AND project.id = estimate.project
GROUP BY customer.name
ORDER BY sum DESC;
