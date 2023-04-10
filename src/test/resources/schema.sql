DROP TABLE IF EXISTS product_category;
DROP TABLE IF EXISTS product;
DROP TABLE IF EXISTS state;
DROP TABLE IF EXISTS customer;
DROP  TABLE IF EXISTS ORDERS;
-- -----------------------------------------------------
-- Table product_category`
-- -----------------------------------------------------
CREATE TABLE PRODUCT_CATEGORY (
    ID int not null AUTO_INCREMENT,
    NAME varchar(255) not null,
    PRIMARY KEY (id));

-- -----------------------------------------------------
-- Table product
-- -----------------------------------------------------
CREATE TABLE  PRODUCT (
    id INT AUTO_INCREMENT NOT NULL,
    sku VARCHAR(255)  NULL,
    name VARCHAR(255)  NULL,
    description VARCHAR(255)  NULL,
    unit_price NUMERIC(13,2)  NULL,
    image_url VARCHAR(255)  NULL,
    units_in_stock INT  NULL,
    active BIT,
    date_created DATE NULL,
    last_updated DATE NULL,
    category_id INT NOT NULL,
    foreign key (category_id) references PRODUCT_CATEGORY(id),
    PRIMARY KEY(id));

-- -----------------------------------------------------
-- Table country
-- -----------------------------------------------------
CREATE TABLE COUNTRY (
    ID int not null AUTO_INCREMENT,
    CODE VARCHAR(5) NOT NULL,
    NAME varchar(80) NOT NULL,
    PRIMARY KEY (id));

-- -----------------------------------------------------
-- Table state
-- -----------------------------------------------------
CREATE TABLE STATE (
    ID int not null AUTO_INCREMENT,
    NAME varchar(80) NOT NULL,
    COUNTRY_ID INT NOT NULL,
    foreign key (COUNTRY_ID) references COUNTRY(ID),
    PRIMARY KEY (ID));

-- -----------------------------------------------------
-- Table state
-- -----------------------------------------------------
CREATE TABLE CUSTOMER (
                       ID int not null AUTO_INCREMENT,
                       NAME varchar(80) NOT NULL,
                       COUNTRY_ID INT NOT NULL,
                       foreign key (COUNTRY_ID) references COUNTRY(ID),
                       PRIMARY KEY (ID));

-- -----------------------------------------------------
-- Table order
-- -----------------------------------------------------
CREATE TABLE ORDER (
                          ID int not null AUTO_INCREMENT,
                          ORDER_TRACKING_NUMBER VARCHAR(80) NOT NULL,
                          TOTAL_QUANTITY INT NOT NULL,
                          TOTAL_PRICE FLOAT NOT NULL,
                          STATUS VARCHAR,
                          CREATED_DATE DATE NULL,
                          LAST_UPDATED DATE
                          foreign key (COUNTRY_ID) references COUNTRY(ID),
                          PRIMARY KEY (ID));
