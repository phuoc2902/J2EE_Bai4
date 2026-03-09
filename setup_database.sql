-- Script tạo Database MySQL cho ứng dụng Thymeleaf + JPA + MySQL
-- Chạy các câu lệnh sau trên MySQL Command Line hoặc MySQL Workbench

-- 1. Tạo database
CREATE DATABASE IF NOT EXISTS thymeleaf_app;
USE thymeleaf_app;

-- 2. Tạo table categories
CREATE TABLE IF NOT EXISTS categories (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 3. Tạo table products
CREATE TABLE IF NOT EXISTS products (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    image VARCHAR(500),
    price DOUBLE NOT NULL,
    category_id INT NOT NULL,
    FOREIGN KEY (category_id) REFERENCES categories(id),
    CONSTRAINT chk_price CHECK (price >= 1)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 4. Insert sample categories
INSERT INTO categories (name) VALUES 
('Laptop'),
('Điện thoại'),
('Tablet'),
('Phụ kiện');

-- 5. Insert sample products (Optional)
INSERT INTO products (name, price, category_id) VALUES 
('MacBook Pro 14-inch', 29990000, 1),
('Asus VivoBook 15', 12990000, 1),
('iPhone 15 Pro', 29990000, 2),
('Samsung Galaxy S24', 22990000, 2),
('iPad Air 2024', 19990000, 3),
('AirPods Pro 2', 6490000, 4);

-- 6. Xem dữ liệu
SELECT * FROM categories;
SELECT p.*, c.name as category_name FROM products p 
JOIN categories c ON p.category_id = c.id;
