create database inside;

use inside;

CREATE TABLE users (
	id int auto_increment primary key,
	firstName varchar(255) not null,
	user_password varchar(255) not null
);

CREATE TABLE message (
	id int auto_increment primary key,
    message varchar(255) not null,
	userId INT not null,
    FOREIGN KEY (userId) REFERENCES users(id)
);

INSERT INTO users (firstName, user_password)
VALUES ('Andre', '123'), ('Peter', '333'), ('Semen', '222');

INSERT INTO message (message, userId)
VALUES ('Hell Andre', 2), ('Hello Peter', 3), ('Hello Semen', 1),
	('Hell Andre', 3), ('Hello Peter', 2), ('Hello Semen', 2),
    ('Hell Andre', 1), ('Hello Peter', 3), ('Hello Semen', 1),
    ('Hell Andre', 1), ('Hello Peter', 3), ('Hello Semen', 2),
    ('Hell Andre', 1), ('Hello Peter', 1), ('Hello Semen', 1),
    ('Hell Andre', 1), ('Hello Peter', 3), ('Hello Semen', 2),
    ('Hell Andre', 1), ('Hello Peter', 3), ('Hello Semen', 1);
    
SELECT * FROM users;

SELECT * FROM message;