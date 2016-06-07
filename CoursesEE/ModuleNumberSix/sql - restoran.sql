﻿CREATE TABLE INGREDIENTS
(
    ID SERIAL PRIMARY KEY,
    NAME_INGREDIENT TEXT NOT NULL
)

INSERT INTO ingredients (NAME_INGREDIENT)
VALUES ('meat (beef)');
INSERT INTO ingredients (NAME_INGREDIENT)
VALUES ('mushroom');
INSERT INTO ingredients (NAME_INGREDIENT)
VALUES ('egg');
INSERT INTO ingredients (NAME_INGREDIENT)
VALUES ('cheese');
INSERT INTO ingredients (NAME_INGREDIENT)
VALUES ('parsley');
INSERT INTO ingredients (NAME_INGREDIENT)
VALUES ('bow');
INSERT INTO ingredients (NAME_INGREDIENT)
VALUES ('potato');
INSERT INTO ingredients (NAME_INGREDIENT)
VALUES ('buckwheat');

SELECT * FROM ingredients;

UPDATE ingredients SET NAME_INGREDIENT = 'potato' WHERE id=2;

DELETE FROM ingredients WHERE ID = 5;

CREATE TABLE WAREHOUSE
(
    ID SERIAL PRIMARY KEY,
    ID_INGREDIENT INT NOT NULL,
    QUANTITY DECIMAL NOT NULL,
    UNIT CHAR(5) NOT NULL
);

ALTER TABLE WAREHOUSE ADD COLUMN quantity DECIMAL;
ALTER TABLE WAREHOUSE ADD COLUMN UNIT CHAR(5);
ALTER TABLE WAREHOUSE DROP COLUMN NUMBER_INGREDIENT;

UPDATE WAREHOUSE SET QUANTITY = 2.0, UNIT='kg' WHERE id=1;
UPDATE WAREHOUSE SET QUANTITY = 1, UNIT='kg' WHERE id=2;
UPDATE WAREHOUSE SET QUANTITY = 10, UNIT='kg' WHERE id=3;
UPDATE WAREHOUSE SET QUANTITY = 20, UNIT='kg' WHERE id=4;
UPDATE WAREHOUSE SET QUANTITY = 0.5, UNIT='kg' WHERE id=5;
UPDATE WAREHOUSE SET QUANTITY = 0.2, UNIT='kg' WHERE id=6;
UPDATE WAREHOUSE SET QUANTITY = 5, UNIT='kg' WHERE id=7;
UPDATE WAREHOUSE SET QUANTITY = 2.2, UNIT='kg' WHERE id=8;


ALTER TABLE WAREHOUSE
  ADD CONSTRAINT WAREHOUSE_id_ingredient_fkey FOREIGN KEY (id_ingredient)
      REFERENCES INGREDIENTS (id);


INSERT INTO WAREHOUSE ( ID_INGREDIENT, QUANTITY, UNIT)
VALUES (1, 2.0, 'kg');
INSERT INTO WAREHOUSE ( ID_INGREDIENT, NUMBER_INGREDIENT)
VALUES (2, 1, 'kg');
INSERT INTO WAREHOUSE ( ID_INGREDIENT, NUMBER_INGREDIENT)
VALUES (3, 10, 'kg');
INSERT INTO WAREHOUSE ( ID_INGREDIENT, NUMBER_INGREDIENT)
VALUES (4, 20, 'kg');
INSERT INTO WAREHOUSE ( ID_INGREDIENT, NUMBER_INGREDIENT)
VALUES (5, 0.5, 'kg');
INSERT INTO WAREHOUSE ( ID_INGREDIENT, NUMBER_INGREDIENT)
VALUES (6, 0.2, 'kg');
INSERT INTO WAREHOUSE ( ID_INGREDIENT, NUMBER_INGREDIENT)
VALUES (7, 5, 'kg');
INSERT INTO WAREHOUSE ( ID_INGREDIENT, NUMBER_INGREDIENT)
VALUES (8, 2.2, 'kg');

SELECT * FROM WAREHOUSE;

UPDATE WAREHOUSE SET ID_INGREDIENT = 5 WHERE id=2;

SELECT WAREHOUSE.NUMBER_INGREDIENT, INGREDIENTS.NAME_INGREDIENT
FROM  WAREHOUSE INNER JOIN INGREDIENTS
ON WAREHOUSE.ID_INGREDIENT = ingredients.ID;

CREATE TABLE DISH
(
    ID SERIAL PRIMARY KEY,
    NAME CHAR(30) NOT NULL,
    ID_CATEGORY INT NOT NULL,
    IDS_INGREDIENTS_DISH TEXT NOT NULL,
    COST INT,
    WEIGHT INT
);
ALTER TABLE DISH   --------
  ADD CONSTRAINT WAREHOUSE_id_ingredient_fkey FOREIGN KEY (id_ingredient)
      REFERENCES INGREDIENTS (id);


INSERT INTO DISH (NAME, ID_CATEGORY, IDS_INGREDIENTS_DISH, COST, WEIGHT)
VALUES ('meat in French', 1, '1 3 4 6', 50, 200);
INSERT INTO DISH (NAME, ID_CATEGORY, IDS_INGREDIENTS_DISH, COST, WEIGHT)
VALUES ('boiled potatoes', 2, '1 2 7', 20, 400);
INSERT INTO DISH (NAME, ID_CATEGORY, IDS_INGREDIENTS_DISH, COST, WEIGHT)
VALUES ('boiled buckwheat', 2, '1 4 5 8', 25, 400);

SELECT * FROM DISH;

UPDATE DISH SET ID_CATEGORY = 2 WHERE id=2;

CREATE TABLE MENU
(
    ID SERIAL PRIMARY KEY,
    NAME_CATEGORY CHAR(30) NOT NULL,
);

ALTER TABLE MENU DROP COLUMN IDS_DISHES;
INSERT INTO MENU (ID, NAME_CATEGORY) VALUES (1, 'NOT CATEGORY');
INSERT INTO MENU (NAME_CATEGORY) VALUES ('Second course');
INSERT INTO MENU (NAME_CATEGORY) VALUES ('Garnish');

SELECT * FROM MENU;

CREATE TABLE PREPARED_DISH
(
    ID SERIAL PRIMARY KEY,
    ID_DISH INT NOT NULL,
    ID_USER INT NOT NULL,
    ID_ORDER INT NOT NULL,
    DATA_DISH TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
ALTER TABLE PREPARED_DISH
  ADD CONSTRAINT PREPARED_DISH_ID_DISH_fkey FOREIGN KEY (ID_DISH)
      REFERENCES DISH (id);
      
ALTER TABLE PREPARED_DISH
  ADD CONSTRAINT PREPARED_DISH_ID_USER_fkey FOREIGN KEY (ID_USER)
      REFERENCES USERS (id);

ALTER TABLE PREPARED_DISH
  ADD CONSTRAINT PREPARED_DISH_ID_ORDER_fkey FOREIGN KEY (ID_ORDER)
      REFERENCES ORDER_WAITER (id);

UPDATE PREPARED_DISH SET ID_USER = 1 WHERE id=3;
UPDATE PREPARED_DISH SET ID_ORDER = 1 WHERE id=3;

INSERT INTO PREPARED_DISH (ID_DISH, ID_USER, ID_ORDER, DATA_DISH)
VALUES (3, 2, 3, '2011-04-04 21:28:20');
INSERT INTO PREPARED_DISH (ID_DISH, ID_USER, ID_ORDER, DATA_DISH)
VALUES (1, 3, 4, '2012-04-04 21:28:20');
INSERT INTO PREPARED_DISH (ID_DISH, ID_USER, ID_ORDER)
VALUES (2, 4, 5);

SELECT * FROM PREPARED_DISH;

DELETE FROM PREPARED_DISH WHERE ID = 24;


CREATE TABLE ORDER_WAITER
(
    ID SERIAL PRIMARY KEY,
    ID_USER INT NOT NULL,
    IDS_DISHES TEXT NOT NULL,
    NUMBER_TABLE INT NOT NULL,
    DATA_DISH TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);



ALTER TABLE ORDER_WAITER ADD COLUMN OPEN_CLOSE INT;
UPDATE ORDER_WAITER SET OPEN_CLOSE = 0 WHERE id=1;
UPDATE ORDER_WAITER SET OPEN_CLOSE = 0 WHERE id=2;
UPDATE ORDER_WAITER SET OPEN_CLOSE = 0 WHERE id=3;
UPDATE ORDER_WAITER SET OPEN_CLOSE = 0 WHERE id=4;
UPDATE ORDER_WAITER SET OPEN_CLOSE = 0 WHERE id=5;

DELETE FROM ORDER_WAITER WHERE ID = 0;

ALTER TABLE ORDER_WAITER
  ADD CONSTRAINT PREPARED_DISH_ID_USER_fkey FOREIGN KEY (ID_USER)
      REFERENCES USERS (id);
      
UPDATE ORDER_WAITER SET ID_USER = 3 WHERE id=1;
     
INSERT INTO ORDER_WAITER (ID_USER, IDS_DISHES, NUMBER_TABLE)
VALUES (2, '1', 3);
INSERT INTO ORDER_WAITER (ID_USER, IDS_DISHES, NUMBER_TABLE)
VALUES (3, '1', 3);
INSERT INTO ORDER_WAITER (ID_USER, IDS_DISHES, NUMBER_TABLE)
VALUES (1, '2', 4);
INSERT INTO ORDER_WAITER (ID_USER, IDS_DISHES, NUMBER_TABLE)
VALUES (1, '3', 5);

SELECT * FROM ORDER_WAITER;

CREATE TABLE USERS
(
    ID SERIAL PRIMARY KEY,
    FIRST_NAME CHAR(15) NOT NULL,
    LAST_NAME CHAR(15) NOT NULL,
    BIRTHDAY DATE,
    PHONE CHAR(13),
    POSITION_USER CHAR(20) NOT NULL,
    SALARY INT
);

INSERT INTO USERS (FIRST_NAME, LAST_NAME, BIRTHDAY, PHONE, POSITION_USER, SALARY)
VALUES ('Ivanov', 'Max', '1986-04-04', '069-999-55-21', 'COOK', 40000);

INSERT INTO USERS (FIRST_NAME, LAST_NAME, BIRTHDAY, PHONE, POSITION_USER, SALARY)
VALUES ('Petrov', 'Ivan', '1985-04-04', '069-444-55-21', 'WAITER', 40000);

INSERT INTO USERS (FIRST_NAME, LAST_NAME, BIRTHDAY, PHONE, POSITION_USER, SALARY)
VALUES ('FEDOROV', 'Ivan', '1981-04-04', '069-111-55-21', 'SECURITY', 20000);

SELECT * FROM USERS;


CREATE TABLE INGREDIENTS_FOR_DISH
(
    ID SERIAL PRIMARY KEY,
    ID_DISH INT NOT NULL,
    ID_INGREDIENT INT NOT NULL,
    QUANTITY DECIMAL
);
ALTER TABLE INGREDIENTS_FOR_DISH
  ADD CONSTRAINT PREPARED_DISH_ID_DISH_fkey FOREIGN KEY (ID_DISH)
      REFERENCES DISH (id);
      
INSERT INTO INGREDIENTS_FOR_DISH (ID_DISH, ID_INGREDIENT, QUANTITY)
VALUES (4, 5, 2);

SELECT * FROM INGREDIENTS_FOR_DISH;

ALTER TABLE INGREDIENTS_FOR_DISH ADD COLUMN quantity DECIMAL;
ALTER TABLE INGREDIENTS_FOR_DISH DROP COLUMN QUANTITY;