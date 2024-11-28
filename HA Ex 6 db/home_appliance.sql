CREATE TABLE home_appliances (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    type VARCHAR(50),
    price DECIMAL(10, 2)
);

INSERT INTO home_appliances (name, type, price) VALUES
('Washing Machine', 'Laundry', 350.00),
('Refrigerator', 'Kitchen', 500.00),
('Microwave Oven', 'Kitchen', 120.00);
