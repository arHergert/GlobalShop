CREATE TABLE IF NOT EXISTS User (
    UserID bigint AUTO_INCREMENT PRIMARY KEY,
    Name varchar(255) NOT NULL,
    Email varchar(255) NOT NULL,
    Passwort varchar(255) NOT NULL,
    SessionID varchar(255)
);

CREATE TABLE IF NOT EXISTS Product (
    ProductID bigint AUTO_INCREMENT PRIMARY KEY,
    Name varchar(255) NOT NULL,
    Price real NOT NULL,
    Category varchar(255) NOT NULL,
    Description varchar(1024),
);

CREATE TABLE IF NOT EXISTS Transaction ( 
    TransactionID bigint AUTO_INCREMENT PRIMARY KEY, 
    UserID bigint, foreign key (UserID) references User(UserID),
    ProductID bigint, foreign key (ProductID) references Product(ProductID), 
    Quantity int NOT NULL, 
    Subtotal real NOT NULL, 
    TransactionDate DATE NOT NULL 
);