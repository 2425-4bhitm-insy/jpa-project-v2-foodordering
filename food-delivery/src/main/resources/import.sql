-- Users (base table for all user types)
INSERT INTO USERS (id, firstname, lastname, email, phonenumber, address, dtype)
VALUES (nextval('user_id_seq'), 'John', 'Doe', 'john.doe@example.com', '+43 660 1234567', 'Hauptstrasse 1, Linz', 'CUSTOMER');

INSERT INTO USERS (id, firstname, lastname, email, phonenumber, address, dtype)
VALUES (nextval('user_id_seq'), 'Jane', 'Smith', 'jane.smith@example.com', '+43 660 7654321', 'Bahnhofstrasse 2, Linz', 'CUSTOMER');

INSERT INTO USERS (id, firstname, lastname, email, phonenumber, address, dtype)
VALUES (nextval('user_id_seq'), 'Max', 'Mustermann', 'max.mustermann@example.com', '+43 650 1122334', 'Landstrasse 3, Linz', 'DELIVERYPERSON');

INSERT INTO USERS (id, firstname, lastname, email, phonenumber, address, dtype)
VALUES (nextval('user_id_seq'), 'Maria', 'Musterfrau', 'maria.musterfrau@example.com', '+43 650 5566778', 'Mozartstrasse 4, Linz', 'DELIVERYPERSON');

INSERT INTO USERS (id, firstname, lastname, email, phonenumber, address, dtype)
VALUES (nextval('user_id_seq'), 'Hans', 'Schmidt', 'hans.schmidt@example.com', '+43 664 9988776', 'Wiener Strasse 5, Linz', 'RESTAURANTOWNER');

INSERT INTO USERS (id, firstname, lastname, email, phonenumber, address, dtype)
VALUES (nextval('user_id_seq'), 'Anna', 'Bauer', 'anna.bauer@example.com', '+43 664 5544332', 'Pragerstrasse 6, Linz', 'RESTAURANTOWNER');

-- Customer table (extends Users)
INSERT INTO CUSTOMER (id) VALUES (1);
INSERT INTO CUSTOMER (id) VALUES (2);

-- DeliveryPerson table (extends Users)
INSERT INTO DELIVERYPERSON (id) VALUES (3);
INSERT INTO DELIVERYPERSON (id) VALUES (4);

-- RestaurantOwner table (extends Users)
INSERT INTO RESTAURANTOWNER (id) VALUES (5);
INSERT INTO RESTAURANTOWNER (id) VALUES (6);

-- Restaurant table
INSERT INTO RESTAURANT (id, name, address, description, rating, restaurantowner_id)
VALUES (nextval('restaurant_id_seq'), 'Pizzeria Roma', 'Hauptplatz 1, Linz', 'Authentic Italian Pizza', '4.5', 5);

INSERT INTO RESTAURANT (id, name, address, description, rating, restaurantowner_id)
VALUES (nextval('restaurant_id_seq'), 'Asia Wok', 'Landstrasse 10, Linz', 'Asian cuisine with fresh ingredients', '4.3', 6);

-- Ownership table (connects RestaurantOwner and Restaurant)
INSERT INTO OWNERSHIP (id, restaurant_id, restaurantowner_id)
VALUES (nextval('ownership_id_seq'), 1, 5);

INSERT INTO OWNERSHIP (id, restaurant_id, restaurantowner_id)
VALUES (nextval('ownership_id_seq'), 2, 6);

-- Menu table
INSERT INTO MENU (id, name, restaurant_id)
VALUES (nextval('menu_id_seq'), 'Pizza Menu', 1);

INSERT INTO MENU (id, name, restaurant_id)
VALUES (nextval('menu_id_seq'), 'Asian Menu', 2);

-- Dish table
INSERT INTO DISH (id, name, price, category, isavailable, menu_id)
VALUES (nextval('dish_id_seq'), 'Margherita', 8.90, 'Pizza', true, 1);

INSERT INTO DISH (id, name, price, category, isavailable, menu_id)
VALUES (nextval('dish_id_seq'), 'Salami', 9.90, 'Pizza', true, 1);

INSERT INTO DISH (id, name, price, category, isavailable, menu_id)
VALUES (nextval('dish_id_seq'), 'Tiramisu', 4.50, 'Dessert', true, 1);

INSERT INTO DISH (id, name, price, category, isavailable, menu_id)
VALUES (nextval('dish_id_seq'), 'Chicken Curry', 10.90, 'Main Course', true, 2);

INSERT INTO DISH (id, name, price, category, isavailable, menu_id)
VALUES (nextval('dish_id_seq'), 'Spring Rolls', 5.90, 'Appetizer', true, 2);

INSERT INTO DISH (id, name, price, category, isavailable, menu_id)
VALUES (nextval('dish_id_seq'), 'Mango Pudding', 4.90, 'Dessert', true, 2);

-- Orders table (named ORDERS because ORDER is a reserved keyword)
INSERT INTO ORDERS (id, orderdate, deliveryaddress, totalprice, status, customer_id, restaurant_id)
VALUES (nextval('order_id_seq'), '2025-04-25 18:30:00', 'Hauptstrasse 1, Linz', 18.80, 'DELIVERED', 1, 1);

INSERT INTO ORDERS (id, orderdate, deliveryaddress, totalprice, status, customer_id, restaurant_id)
VALUES (nextval('order_id_seq'), '2025-04-26 12:15:00', 'Bahnhofstrasse 2, Linz', 16.80, 'PENDING', 2, 2);

-- OrderItem table
INSERT INTO ORDERITEM (id, order_id, dish_id)
VALUES (nextval('order_item_id_seq'), 1, 1);

INSERT INTO ORDERITEM (id, order_id, dish_id)
VALUES (nextval('order_item_id_seq'), 1, 3);

INSERT INTO ORDERITEM (id, order_id, dish_id)
VALUES (nextval('order_item_id_seq'), 2, 4);

INSERT INTO ORDERITEM (id, order_id, dish_id)
VALUES (nextval('order_item_id_seq'), 2, 5);

-- Payment table
INSERT INTO PAYMENT (id, amount, paymentmethod, status, order_id)
VALUES (nextval('payment_id_seq'), 18.80, 'Credit Card', 'COMPLETED', 1);

INSERT INTO PAYMENT (id, amount, paymentmethod, status, order_id)
VALUES (nextval('payment_id_seq'), 16.80, 'PayPal', 'PENDING', 2);

-- Delivery table
INSERT INTO DELIVERY (id, estimatedtime, status, deliveryperson_id, order_id)
VALUES (nextval('delivery_id_seq'), '2025-04-25 19:00:00', 'DELIVERED', 3, 1);

INSERT INTO DELIVERY (id, estimatedtime, status, deliveryperson_id, order_id)
VALUES (nextval('delivery_id_seq'), '2025-04-26 13:00:00', 'PENDING', 4, 2);

-- Review table
INSERT INTO REVIEW (id, rating, customer_id, restaurant_id)
VALUES (nextval('review_id_seq'), '5', 1, 1);

INSERT INTO REVIEW (id, rating, customer_id, restaurant_id)
VALUES (nextval('review_id_seq'), '4', 2, 2);