CREATE DATABASE dating_app;
\c dating_app;
CREATE TABLE users(id SERIAL PRIMARY KEY, name VARCHAR, bio VARCHAR, age INTEGER, orientation VARCHAR, imageUrl VARCHAR, interests VARCHAR);
CREATE DATABASE dating_app_test WITH TEMPLATE dating_app;