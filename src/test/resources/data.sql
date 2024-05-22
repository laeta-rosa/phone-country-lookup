CREATE TABLE country (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255)
);

CREATE TABLE phone_code (
    id INT PRIMARY KEY AUTO_INCREMENT,
    code VARCHAR(10)
);

CREATE TABLE country_phone_code (
    country_id INT,
    phone_code_id INT,
    PRIMARY KEY (country_id, phone_code_id),
    FOREIGN KEY (country_id) REFERENCES country(id),
    FOREIGN KEY (phone_code_id) REFERENCES phone_code(id)
);
