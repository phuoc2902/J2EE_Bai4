# 🛍️ Ứng dụng Quản lý Sản phẩm - Thymeleaf + JPA + MySQL

## 📋 Mô tả Dự án

Ứng dụng web quản lý sản phẩm được xây dựng bằng:
- **Framework**: Spring Boot 3.5.11
- **Template Engine**: Thymeleaf
- **ORM**: Spring Data JPA + Hibernate
- **Database**: MySQL 8.0+
- **Frontend**: Bootstrap 5 + Font Awesome + CSS3

### 🎯 Chức năng chính:
- ✅ Hiển thị danh sách sản phẩm
- ✅ Thêm sản phẩm mới
- ✅ Sửa thông tin sản phẩm
- ✅ Xóa sản phẩm
- ✅ Phân loại sản phẩm theo danh mục
- ✅ Upload hình ảnh sản phẩm
- ✅ Validation dữ liệu đầu vào

---

## 🏗️ Cấu trúc Dự án

```
J2EE_Bai4/
├── src/main/
│   ├── java/com/example/demo/
│   │   ├── DemoApplication.java              # Main class
│   │   ├── controller/
│   │   │   └── ProductController.java        # Controller CRUD sản phẩm
│   │   ├── model/
│   │   │   ├── Product.java                  # Entity sản phẩm
│   │   │   └── Category.java                 # Entity danh mục
│   │   ├── repository/
│   │   │   ├── ProductRepository.java        # JPA Repository for Product
│   │   │   └── CategoryRepository.java       # JPA Repository for Category
│   │   └── service/
│   │       ├── ProductService.java           # Business logic - sản phẩm
│   │       └── CategoryService.java          # Business logic - danh mục
│   ├── resources/
│   │   ├── application.properties            # Cấu hình ứng dụng
│   │   ├── static/
│   │   │   ├── css/
│   │   │   │   └── styles.css
│   │   │   └── images/                       # Thư mục lưu hình ảnh upload
│   │   └── templates/
│   │       ├── _layout.html                  # Layout chung
│   │       └── product/
│   │           ├── list.html                 # Trang danh sách sản phẩm
│   │           └── add.html                  # Form thêm/sửa sản phẩm
├── pom.xml                                   # Maven dependencies
├── setup_database.sql                        # Script tạo database
├── mvnw & mvnw.cmd                           # Maven wrapper
└── README.md                                 # File này
```

---

## 🔧 Yêu cầu Hệ thống

- **Java**: JDK 17+
- **Maven**: 3.6+
- **MySQL**: 8.0+
- **IDE**: VS Code, IntelliJ IDEA, Eclipse,...

---

## 📥 Hướng dẫn Cài đặt

### 1️⃣ Tạo Database MySQL

**Cách 1: Sử dụng file SQL script**

```bash
# Mở MySQL Command Line hoặc MySQL Workbench
# Import file setup_database.sql
mysql -u root -p < setup_database.sql

# Hoặc copy-paste các câu lệnh từ setup_database.sql vào MySQL Command Line
```

**Cách 2: Tạo thủ công**

Chạy các câu lệnh SQL sau:

```sql
-- Tạo database
CREATE DATABASE thymeleaf_app;
USE thymeleaf_app;

-- Tạo table categories
CREATE TABLE categories (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Tạo table products
CREATE TABLE products (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    image VARCHAR(500),
    price DOUBLE NOT NULL,
    category_id INT NOT NULL,
    FOREIGN KEY (category_id) REFERENCES categories(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Insert danh mục mẫu
INSERT INTO categories (name) VALUES ('Laptop'), ('Điện thoại'), ('Tablet'), ('Phụ kiện');
```

### 2️⃣ Cấu hình application.properties

Mở file `src/main/resources/application.properties` và cập nhật:

```properties
# MySQL Database
spring.datasource.url=jdbc:mysql://localhost:3306/thymeleaf_app?useSSL=false&serverTimezone=Asia/Ho_Chi_Minh&allowPublicKeyRetrieval=true
spring.datasource.username=root
spring.datasource.password=YOUR_PASSWORD_HERE    # ← Thay mật khẩu MySQL của bạn
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# JPA Configuration
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
spring.jpa.properties.hibernate.format_sql=true

# Server
server.port=8080
```

### 3️⃣ Build & Run Project

**Cách 1: Dùng Maven Command**

```bash
# Compile project
mvn clean compile

# Build project
mvn clean install

# Run ứng dụng
mvn spring-boot:run
```

**Cách 2: Dùng IDE**

- **VS Code**: 
  - Cài extension "Spring Boot Extension Pack"
  - Nhấn F5 để run hoặc Ctrl+Shift+D
  - Chọn "Java" khi được yêu cầu

- **IntelliJ IDEA**:
  - Open project → Build → Build Project
  - Right click DemoApplication.java → Run (hoặc Shift+F10)

- **Eclipse**:
  - File → Open Projects from File System → Chọn folder project
  - Right click project → Run As → Spring Boot App

### 4️⃣ Truy cập Ứng dụng

Mở browser và truy cập:

```
http://localhost:8080/products
```

---

## 📖 Hướng dẫn Sử dụng

### Trang Danh sách Sản phẩm (`/products`)
- Hiển thị tất cả sản phẩm dưới dạng card
- Có thể click **"Sửa"** để cập nhật sản phẩm
- Có thể click **"Xóa"** để xóa sản phẩm
- Click **"Thêm sản phẩm mới"** để tạo sản phẩm mới

### Form Thêm/Sửa Sản phẩm (`/products/add`, `/products/edit/{id}`)
- **Tên sản phẩm**: Bắt buộc, không được để trống
- **Giá sản phẩm**: Bắt buộc, phải ≥ 1
- **Hình ảnh**: Có thể upload JPG/PNG, không bắt buộc
- **Danh mục**: Bắt buộc phải chọn từ danh sách

### Các Route (Endpoint)

| Method | URL | Mô tả |
|--------|-----|--------|
| GET | `/products` | Hiển thị danh sách sản phẩm |
| GET | `/products/add` | Mở form thêm sản phẩm |
| POST | `/products/save` | Lưu sản phẩm mới hoặc cập nhật |
| GET | `/products/edit/{id}` | Mở form sửa sản phẩm |
| GET | `/products/delete/{id}` | Xóa sản phẩm |

---

## 🔑 Entity & Quan hệ

### Entity: Category
```java
@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @NotBlank
    @Column(nullable = false)
    private String name;
}
```

### Entity: Product
```java
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @NotBlank
    @Column(nullable = false)
    private String name;
    
    @Size(min = 0, max = 500)
    private String image;
    
    @NotNull
    @Min(1)
    @Column(nullable = false)
    private Double price;
    
    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;  // Quan hệ Many-to-One
}
```

**Quan hệ**: Một Category có nhiều Product (1:N)

---

## 📦 Dependencies (pom.xml)

```xml
<!-- Spring Boot Web & MVC -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>

<!-- Thymeleaf Template Engine -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-thymeleaf</artifactId>
</dependency>

<!-- Spring Data JPA -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>

<!-- MySQL Connector -->
<dependency>
    <groupId>com.mysql</groupId>
    <artifactId>mysql-connector-j</artifactId>
    <scope>runtime</scope>
</dependency>

<!-- Validation -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-validation</artifactId>
</dependency>

<!-- Lombok -->
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <optional>true</optional>
</dependency>
```

---

## 🎨 Công Nghệ & Thư viện CSS/JS

- **Bootstrap 5.3.3**: Responsive CSS framework
- **Font Awesome 6.5.1**: Icon library
- **Thymeleaf Layout Dialect**: Layout reuse
- **Custom CSS**: Styling bổ sung

---

## ❌ Gặp Vấn đề?

### 1. "Cannot connect to database"
- ✅ Kiểm tra MySQL có chạy không (check port 3306)
- ✅ Kiểm tra username/password trong application.properties
- ✅ Kiểm tra database `thymeleaf_app` đã được tạo chưa

### 2. Template không load
- ✅ Kiểm tra file `.html` có trong `src/main/resources/templates/` không
- ✅ Kiểm tra path trong `th:href` và layout decorator có đúng không

### 3. Validation lỗi
- ✅ Kiểm tra các annotation `@NotBlank`, `@NotNull`, `@Min` có đúng không
- ✅ Kiểm tra `BindingResult` được xử lý trong controller

### 4. Upload hình ảnh không hoạt động
- ✅ Tạo folder `static/images` nếu chưa có
- ✅ Kiểm tra quyền write folder `static/images`
- ✅ Kiểm tra form có `enctype="multipart/form-data"` không

### 5. Port 8080 đang bị sử dụng
```
# Thay đổi port trong application.properties
server.port=8081
```

---

## 💡 Các Tính Năng Nâng cao (Bổ sung tương lai)

- [ ] Phân trang danh sách sản phẩm
- [ ] Tìm kiếm sản phẩm
- [ ] Lọc theo danh mục
- [ ] Quản lý danh mục (CRUD)
- [ ] Xuất Excel/PDF
- [ ] Authentication & Authorization
- [ ] Đơn hàng & Giỏ hàng
- [ ] API REST endpoints
- [ ] Unit tests

---

## 📝 Lưu ý quan trọng

1. **Password MySQL**: Đổi password trong application.properties nếu có
2. **Folder static/images**: Tạo thư mục này nếu upload hình ảnh
3. **Các tập tin Thymeleaf layout**: Phải có fragment `content` để views có thể kế thừa
4. **JPA ddl-auto**: Dùng `update` để tự động tạo/update table, `validate` cho production

---

## 🤝 Tác giả

Phát triển cho purpose học tập J2EE - Spring Boot - Thymeleaf - JPA

---

## 📄 License

MIT License - Sử dụng tự do cho mục đích học tập

---

Happy Coding! 🚀
