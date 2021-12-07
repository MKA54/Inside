create database inside;

use inside;

CREATE TABLE users (
	id int auto_increment primary key,
	firstName varchar(255) not null,
	user_password varchar(255) not null
);

CREATE TABLE message (
	id int auto_increment primary key,
    message varchar(255),
	userId INT,
    FOREIGN KEY (userId) REFERENCES users(id)
);

INSERT INTO users (firstName, user_password)
VALUES ('Andre', '123'), ('Peter', '333'), ('Semen', '222');

SELECT * FROM users;

SELECT * FROM message;

drop table users;

drop table message;

drop database inside;