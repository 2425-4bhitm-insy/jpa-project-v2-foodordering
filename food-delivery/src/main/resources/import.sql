-- Users table (need to create these first since other tables reference them)
INSERT INTO users (id, dtype, address, email, firstname, lastname, phonenumber) VALUES (1, 'customer', '123 Main St, City1', 'customer1@email.com', 'John', 'Doe', '555-111-1001');
INSERT INTO users (id, dtype, address, email, firstname, lastname, phonenumber) VALUES (2, 'customer', '456 Oak St, City1', 'customer2@email.com', 'Jane', 'Smith', '555-111-1002');
INSERT INTO users (id, dtype, address, email, firstname, lastname, phonenumber) VALUES (3, 'customer', '789 Pine St, City1', 'customer3@email.com', 'Michael', 'Johnson', '555-111-1003');
INSERT INTO users (id, dtype, address, email, firstname, lastname, phonenumber) VALUES (4, 'customer', '101 Elm St, City2', 'customer4@email.com', 'Emily', 'Williams', '555-111-1004');
INSERT INTO users (id, dtype, address, email, firstname, lastname, phonenumber) VALUES (5, 'customer', '202 Cedar St, City2', 'customer5@email.com', 'David', 'Brown', '555-111-1005');
INSERT INTO users (id, dtype, address, email, firstname, lastname, phonenumber) VALUES (6, 'deliveryperson', '303 Maple St, City1', 'driver1@email.com', 'Robert', 'Jones', '555-222-2001');
INSERT INTO users (id, dtype, address, email, firstname, lastname, phonenumber) VALUES (7, 'deliveryperson', '404 Birch St, City1', 'driver2@email.com', 'Sarah', 'Miller', '555-222-2002');
INSERT INTO users (id, dtype, address, email, firstname, lastname, phonenumber) VALUES (8, 'deliveryperson', '505 Spruce St, City2', 'driver3@email.com', 'James', 'Davis', '555-222-2003');
INSERT INTO users (id, dtype, address, email, firstname, lastname, phonenumber) VALUES (9, 'restaurantowner', '606 Walnut St, City1', 'owner1@email.com', 'Patricia', 'Garcia', '555-333-3001');
INSERT INTO users (id, dtype, address, email, firstname, lastname, phonenumber) VALUES (10, 'restaurantowner', '707 Cherry St, City2', 'owner2@email.com', 'Thomas', 'Rodriguez', '555-333-3002');
INSERT INTO users (id, dtype, address, email, firstname, lastname, phonenumber) VALUES (11, 'restaurantowner', '808 Apple St, City2', 'owner3@email.com', 'Jennifer', 'Martinez', '555-333-3003');
INSERT INTO users (id, dtype, address, email, firstname, lastname, phonenumber) VALUES (12, 'customer', '909 Peach St, City3', 'customer6@email.com', 'Charles', 'Hernandez', '555-111-1006');
INSERT INTO users (id, dtype, address, email, firstname, lastname, phonenumber) VALUES (13, 'customer', '1010 Plum St, City3', 'customer7@email.com', 'Jessica', 'Lopez', '555-111-1007');
INSERT INTO users (id, dtype, address, email, firstname, lastname, phonenumber) VALUES (14, 'customer', '1111 Orange St, City3', 'customer8@email.com', 'Daniel', 'Gonzalez', '555-111-1008');
INSERT INTO users (id, dtype, address, email, firstname, lastname, phonenumber) VALUES (15, 'deliveryperson', '1212 Lemon St, City3', 'driver4@email.com', 'Nancy', 'Wilson', '555-222-2004');

-- Customer table (using user IDs 1-5 and 12-14)
INSERT INTO customer (id) VALUES (1);
INSERT INTO customer (id) VALUES (2);
INSERT INTO customer (id) VALUES (3);
INSERT INTO customer (id) VALUES (4);
INSERT INTO customer (id) VALUES (5);
INSERT INTO customer (id) VALUES (12);
INSERT INTO customer (id) VALUES (13);
INSERT INTO customer (id) VALUES (14);

-- Deliveryperson table (using user IDs 6-8 and 15)
INSERT INTO deliveryperson (id) VALUES (6);
INSERT INTO deliveryperson (id) VALUES (7);
INSERT INTO deliveryperson (id) VALUES (8);
INSERT INTO deliveryperson (id) VALUES (15);

-- Restaurantowner table (using user IDs 9-11)
INSERT INTO restaurantowner (id) VALUES (9);
INSERT INTO restaurantowner (id) VALUES (10);
INSERT INTO restaurantowner (id) VALUES (11);

-- Restaurant table
INSERT INTO restaurant (id, restaurantowner_id, description, address, name, rating) VALUES (1, 9, 'Authentic Italian cuisine', '123 Food Ave, City1', 'Mama Mia', '4.7');
INSERT INTO restaurant (id, restaurantowner_id, description, address, name, rating) VALUES (2, 9, 'Fresh seafood restaurant', '456 Ocean Dr, City1', 'Ocean Delights', '4.5');
INSERT INTO restaurant (id, restaurantowner_id, description, address, name, rating) VALUES (3, 10, 'Gourmet burgers and shakes', '789 Burger Ln, City2', 'Burger Heaven', '4.6');
INSERT INTO restaurant (id, restaurantowner_id, description, address, name, rating) VALUES (4, 10, 'Authentic Mexican cuisine', '101 Taco St, City2', 'El Sombrero', '4.4');
INSERT INTO restaurant (id, restaurantowner_id, description, address, name, rating) VALUES (5, 11, 'Traditional Japanese sushi', '202 Sushi Rd, City2', 'Sakura Sushi', '4.8');
INSERT INTO restaurant (id, restaurantowner_id, description, address, name, rating) VALUES (6, 11, 'Vegetarian and vegan options', '303 Green Ln, City3', 'Green Eats', '4.3');
INSERT INTO restaurant (id, restaurantowner_id, description, address, name, rating) VALUES (7, 9, 'Wood-fired pizzas', '404 Pizza Rd, City1', 'Pizza Palace', '4.5');
INSERT INTO restaurant (id, restaurantowner_id, description, address, name, rating) VALUES (8, 10, 'Classic American diner', '505 Diner Ave, City2', 'Star Diner', '4.2');
INSERT INTO restaurant (id, restaurantowner_id, description, address, name, rating) VALUES (9, 11, 'Authentic Thai cuisine', '606 Spice St, City3', 'Thai Spice', '4.6');
INSERT INTO restaurant (id, restaurantowner_id, description, address, name, rating) VALUES (10, 9, 'Mediterranean delicacies', '707 Med Ave, City1', 'Mediterranean Dreams', '4.7');

-- Restaurantowner_restaurant table
INSERT INTO restaurantowner_restaurant (restaurantowner_id, restaurants_id) VALUES (9, 1);
INSERT INTO restaurantowner_restaurant (restaurantowner_id, restaurants_id) VALUES (9, 2);
INSERT INTO restaurantowner_restaurant (restaurantowner_id, restaurants_id) VALUES (10, 3);
INSERT INTO restaurantowner_restaurant (restaurantowner_id, restaurants_id) VALUES (10, 4);
INSERT INTO restaurantowner_restaurant (restaurantowner_id, restaurants_id) VALUES (11, 5);
INSERT INTO restaurantowner_restaurant (restaurantowner_id, restaurants_id) VALUES (11, 6);
INSERT INTO restaurantowner_restaurant (restaurantowner_id, restaurants_id) VALUES (9, 7);
INSERT INTO restaurantowner_restaurant (restaurantowner_id, restaurants_id) VALUES (10, 8);
INSERT INTO restaurantowner_restaurant (restaurantowner_id, restaurants_id) VALUES (11, 9);
INSERT INTO restaurantowner_restaurant (restaurantowner_id, restaurants_id) VALUES (9, 10);

-- Menu table
INSERT INTO menu (id, name) VALUES (1, 'Lunch Menu - Mama Mia');
INSERT INTO menu (id, name) VALUES (2, 'Dinner Menu - Mama Mia');
INSERT INTO menu (id, name) VALUES (3, 'Seafood Special - Ocean Delights');
INSERT INTO menu (id, name) VALUES (4, 'Burger Menu - Burger Heaven');
INSERT INTO menu (id, name) VALUES (5, 'Mexican Fiesta - El Sombrero');
INSERT INTO menu (id, name) VALUES (6, 'Sushi Selection - Sakura Sushi');
INSERT INTO menu (id, name) VALUES (7, 'Vegan Delights - Green Eats');
INSERT INTO menu (id, name) VALUES (8, 'Pizza Menu - Pizza Palace');
INSERT INTO menu (id, name) VALUES (9, 'Classic Diner - Star Diner');
INSERT INTO menu (id, name) VALUES (10, 'Thai Favorites - Thai Spice');

-- Dish table
INSERT INTO dish (id, menu_id, isavailable, price, category, name) VALUES (1, 1, true, 12.99, 'Pasta', 'Spaghetti Carbonara');
INSERT INTO dish (id, menu_id, isavailable, price, category, name) VALUES (2, 1, true, 14.99, 'Pasta', 'Fettuccine Alfredo');
INSERT INTO dish (id, menu_id, isavailable, price, category, name) VALUES (3, 2, true, 18.99, 'Main', 'Chicken Parmesan');
INSERT INTO dish (id, menu_id, isavailable, price, category, name) VALUES (4, 2, true, 22.99, 'Main', 'Veal Marsala');
INSERT INTO dish (id, menu_id, isavailable, price, category, name) VALUES (5, 3, true, 24.99, 'Seafood', 'Grilled Salmon');
INSERT INTO dish (id, menu_id, isavailable, price, category, name) VALUES (6, 3, true, 26.99, 'Seafood', 'Lobster Tail');
INSERT INTO dish (id, menu_id, isavailable, price, category, name) VALUES (7, 4, true, 15.99, 'Burger', 'Classic Cheeseburger');
INSERT INTO dish (id, menu_id, isavailable, price, category, name) VALUES (8, 4, true, 17.99, 'Burger', 'Bacon Avocado Burger');
INSERT INTO dish (id, menu_id, isavailable, price, category, name) VALUES (9, 5, true, 14.99, 'Mexican', 'Beef Tacos');
INSERT INTO dish (id, menu_id, isavailable, price, category, name) VALUES (10, 5, false, 16.99, 'Mexican', 'Chicken Enchiladas');

-- Menu_dish table
INSERT INTO menu_dish (menu_id, dishes_id) VALUES (1, 1);
INSERT INTO menu_dish (menu_id, dishes_id) VALUES (1, 2);
INSERT INTO menu_dish (menu_id, dishes_id) VALUES (2, 3);
INSERT INTO menu_dish (menu_id, dishes_id) VALUES (2, 4);
INSERT INTO menu_dish (menu_id, dishes_id) VALUES (3, 5);
INSERT INTO menu_dish (menu_id, dishes_id) VALUES (3, 6);
INSERT INTO menu_dish (menu_id, dishes_id) VALUES (4, 7);
INSERT INTO menu_dish (menu_id, dishes_id) VALUES (4, 8);
INSERT INTO menu_dish (menu_id, dishes_id) VALUES (5, 9);
INSERT INTO menu_dish (menu_id, dishes_id) VALUES (5, 10);

-- Orders table
INSERT INTO orders (id, customer_id, restaurant_id, totalprice, orderdate, deliveryaddress, status) VALUES (1, 1, 1, 27.98, '2025-04-20 12:30:00', '123 Main St, City1', 'Delivered');
INSERT INTO orders (id, customer_id, restaurant_id, totalprice, orderdate, deliveryaddress, status) VALUES (2, 2, 3, 33.98, '2025-04-20 13:15:00', '456 Oak St, City1', 'Delivered');
INSERT INTO orders (id, customer_id, restaurant_id, totalprice, orderdate, deliveryaddress, status) VALUES (3, 3, 5, 24.99, '2025-04-21 18:45:00', '789 Pine St, City1', 'Delivered');
INSERT INTO orders (id, customer_id, restaurant_id, totalprice, orderdate, deliveryaddress, status) VALUES (4, 4, 2, 51.98, '2025-04-22 19:20:00', '101 Elm St, City2', 'Delivered');
INSERT INTO orders (id, customer_id, restaurant_id, totalprice, orderdate, deliveryaddress, status) VALUES (5, 5, 4, 31.98, '2025-04-23 20:10:00', '202 Cedar St, City2', 'Delivered');
INSERT INTO orders (id, customer_id, restaurant_id, totalprice, orderdate, deliveryaddress, status) VALUES (6, 1, 6, 45.99, '2025-04-24 12:45:00', '123 Main St, City1', 'In Transit');
INSERT INTO orders (id, customer_id, restaurant_id, totalprice, orderdate, deliveryaddress, status) VALUES (7, 2, 7, 37.98, '2025-04-25 13:30:00', '456 Oak St, City1', 'In Transit');
INSERT INTO orders (id, customer_id, restaurant_id, totalprice, orderdate, deliveryaddress, status) VALUES (8, 3, 8, 29.99, '2025-04-26 18:15:00', '789 Pine St, City1', 'Processing');
INSERT INTO orders (id, customer_id, restaurant_id, totalprice, orderdate, deliveryaddress, status) VALUES (9, 4, 9, 42.98, '2025-04-27 19:05:00', '101 Elm St, City2', 'Processing');
INSERT INTO orders (id, customer_id, restaurant_id, totalprice, orderdate, deliveryaddress, status) VALUES (10, 5, 10, 38.97, '2025-04-28 11:25:00', '202 Cedar St, City2', 'Received');

-- Restaurant_orders table
INSERT INTO restaurant_orders (restaurant_id, orders_id) VALUES (1, 1);
INSERT INTO restaurant_orders (restaurant_id, orders_id) VALUES (3, 2);
INSERT INTO restaurant_orders (restaurant_id, orders_id) VALUES (5, 3);
INSERT INTO restaurant_orders (restaurant_id, orders_id) VALUES (2, 4);
INSERT INTO restaurant_orders (restaurant_id, orders_id) VALUES (4, 5);
INSERT INTO restaurant_orders (restaurant_id, orders_id) VALUES (6, 6);
INSERT INTO restaurant_orders (restaurant_id, orders_id) VALUES (7, 7);
INSERT INTO restaurant_orders (restaurant_id, orders_id) VALUES (8, 8);
INSERT INTO restaurant_orders (restaurant_id, orders_id) VALUES (9, 9);
INSERT INTO restaurant_orders (restaurant_id, orders_id) VALUES (10, 10);

-- Customer_orders table
INSERT INTO customer_orders (customer_id, orders_id) VALUES (1, 1);
INSERT INTO customer_orders (customer_id, orders_id) VALUES (2, 2);
INSERT INTO customer_orders (customer_id, orders_id) VALUES (3, 3);
INSERT INTO customer_orders (customer_id, orders_id) VALUES (4, 4);
INSERT INTO customer_orders (customer_id, orders_id) VALUES (5, 5);
INSERT INTO customer_orders (customer_id, orders_id) VALUES (1, 6);
INSERT INTO customer_orders (customer_id, orders_id) VALUES (2, 7);
INSERT INTO customer_orders (customer_id, orders_id) VALUES (3, 8);
INSERT INTO customer_orders (customer_id, orders_id) VALUES (4, 9);
INSERT INTO customer_orders (customer_id, orders_id) VALUES (5, 10);

-- Payment table
INSERT INTO payment (id, order_id, amount, paymentmethod, status) VALUES (1, 1, 27.98, 'Credit Card', 'Completed');
INSERT INTO payment (id, order_id, amount, paymentmethod, status) VALUES (2, 2, 33.98, 'Credit Card', 'Completed');
INSERT INTO payment (id, order_id, amount, paymentmethod, status) VALUES (3, 3, 24.99, 'PayPal', 'Completed');
INSERT INTO payment (id, order_id, amount, paymentmethod, status) VALUES (4, 4, 51.98, 'Credit Card', 'Completed');
INSERT INTO payment (id, order_id, amount, paymentmethod, status) VALUES (5, 5, 31.98, 'Debit Card', 'Completed');
INSERT INTO payment (id, order_id, amount, paymentmethod, status) VALUES (6, 6, 45.99, 'Credit Card', 'Processing');
INSERT INTO payment (id, order_id, amount, paymentmethod, status) VALUES (7, 7, 37.98, 'PayPal', 'Processing');
INSERT INTO payment (id, order_id, amount, paymentmethod, status) VALUES (8, 8, 29.99, 'Debit Card', 'Pending');
INSERT INTO payment (id, order_id, amount, paymentmethod, status) VALUES (9, 9, 42.98, 'Credit Card', 'Pending');
INSERT INTO payment (id, order_id, amount, paymentmethod, status) VALUES (10, 10, 38.97, 'PayPal', 'Pending');

-- Orders_payment table
INSERT INTO orders_payment (order_id, payments_id) VALUES (1, 1);
INSERT INTO orders_payment (order_id, payments_id) VALUES (2, 2);
INSERT INTO orders_payment (order_id, payments_id) VALUES (3, 3);
INSERT INTO orders_payment (order_id, payments_id) VALUES (4, 4);
INSERT INTO orders_payment (order_id, payments_id) VALUES (5, 5);
INSERT INTO orders_payment (order_id, payments_id) VALUES (6, 6);
INSERT INTO orders_payment (order_id, payments_id) VALUES (7, 7);
INSERT INTO orders_payment (order_id, payments_id) VALUES (8, 8);
INSERT INTO orders_payment (order_id, payments_id) VALUES (9, 9);
INSERT INTO orders_payment (order_id, payments_id) VALUES (10, 10);

-- Review table
INSERT INTO review (id, customer_id, restaurant_id, rating) VALUES (1, 1, 1, '5');
INSERT INTO review (id, customer_id, restaurant_id, rating) VALUES (2, 2, 3, '4');
INSERT INTO review (id, customer_id, restaurant_id, rating) VALUES (3, 3, 5, '5');
INSERT INTO review (id, customer_id, restaurant_id, rating) VALUES (4, 4, 2, '4');
INSERT INTO review (id, customer_id, restaurant_id, rating) VALUES (5, 5, 4, '3');
INSERT INTO review (id, customer_id, restaurant_id, rating) VALUES (6, 1, 6, '5');
INSERT INTO review (id, customer_id, restaurant_id, rating) VALUES (7, 2, 7, '4');
INSERT INTO review (id, customer_id, restaurant_id, rating) VALUES (8, 3, 8, '4');
INSERT INTO review (id, customer_id, restaurant_id, rating) VALUES (9, 4, 9, '5');
INSERT INTO review (id, customer_id, restaurant_id, rating) VALUES (10, 5, 10, '4');

-- Restaurant_review table
INSERT INTO restaurant_review (restaurant_id, reviews_id) VALUES (1, 1);
INSERT INTO restaurant_review (restaurant_id, reviews_id) VALUES (3, 2);
INSERT INTO restaurant_review (restaurant_id, reviews_id) VALUES (5, 3);
INSERT INTO restaurant_review (restaurant_id, reviews_id) VALUES (2, 4);
INSERT INTO restaurant_review (restaurant_id, reviews_id) VALUES (4, 5);
INSERT INTO restaurant_review (restaurant_id, reviews_id) VALUES (6, 6);
INSERT INTO restaurant_review (restaurant_id, reviews_id) VALUES (7, 7);
INSERT INTO restaurant_review (restaurant_id, reviews_id) VALUES (8, 8);
INSERT INTO restaurant_review (restaurant_id, reviews_id) VALUES (9, 9);
INSERT INTO restaurant_review (restaurant_id, reviews_id) VALUES (10, 10);

-- Customer_review table
INSERT INTO customer_review (customer_id, reviews_id) VALUES (1, 1);
INSERT INTO customer_review (customer_id, reviews_id) VALUES (2, 2);
INSERT INTO customer_review (customer_id, reviews_id) VALUES (3, 3);
INSERT INTO customer_review (customer_id, reviews_id) VALUES (4, 4);
INSERT INTO customer_review (customer_id, reviews_id) VALUES (5, 5);
INSERT INTO customer_review (customer_id, reviews_id) VALUES (1, 6);
INSERT INTO customer_review (customer_id, reviews_id) VALUES (2, 7);
INSERT INTO customer_review (customer_id, reviews_id) VALUES (3, 8);
INSERT INTO customer_review (customer_id, reviews_id) VALUES (4, 9);
INSERT INTO customer_review (customer_id, reviews_id) VALUES (5, 10);

-- Delivery table
INSERT INTO delivery (id, order_id, estimatedtime, status) VALUES (1, 1, '2025-04-20 13:15:00', 'Delivered');
INSERT INTO delivery (id, order_id, estimatedtime, status) VALUES (2, 2, '2025-04-20 14:00:00', 'Delivered');
INSERT INTO delivery (id, order_id, estimatedtime, status) VALUES (3, 3, '2025-04-21 19:30:00', 'Delivered');
INSERT INTO delivery (id, order_id, estimatedtime, status) VALUES (4, 4, '2025-04-22 20:05:00', 'Delivered');
INSERT INTO delivery (id, order_id, estimatedtime, status) VALUES (5, 5, '2025-04-23 20:55:00', 'Delivered');
INSERT INTO delivery (id, order_id, estimatedtime, status) VALUES (6, 6, '2025-04-24 13:30:00', 'In Transit');
INSERT INTO delivery (id, order_id, estimatedtime, status) VALUES (7, 7, '2025-04-25 14:15:00', 'In Transit');
INSERT INTO delivery (id, order_id, estimatedtime, status) VALUES (8, 8, '2025-04-26 19:00:00', 'Assigned');
INSERT INTO delivery (id, order_id, estimatedtime, status) VALUES (9, 9, '2025-04-27 19:50:00', 'Assigned');
INSERT INTO delivery (id, order_id, estimatedtime, status) VALUES (10, 10, '2025-04-28 12:10:00', 'Pending');

-- Deliveryperson_delivery table
INSERT INTO deliveryperson_delivery (deliveryperson_id, deliveries_id) VALUES (6, 1);
INSERT INTO deliveryperson_delivery (deliveryperson_id, deliveries_id) VALUES (7, 2);
INSERT INTO deliveryperson_delivery (deliveryperson_id, deliveries_id) VALUES (8, 3);
INSERT INTO deliveryperson_delivery (deliveryperson_id, deliveries_id) VALUES (15, 4);
INSERT INTO deliveryperson_delivery (deliveryperson_id, deliveries_id) VALUES (6, 5);
INSERT INTO deliveryperson_delivery (deliveryperson_id, deliveries_id) VALUES (7, 6);
INSERT INTO deliveryperson_delivery (deliveryperson_id, deliveries_id) VALUES (8, 7);
INSERT INTO deliveryperson_delivery (deliveryperson_id, deliveries_id) VALUES (15, 8);
INSERT INTO deliveryperson_delivery (deliveryperson_id, deliveries_id) VALUES (6, 9);
INSERT INTO deliveryperson_delivery (deliveryperson_id, deliveries_id) VALUES (7, 10);