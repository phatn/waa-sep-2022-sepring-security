-- CATEGORY
INSERT INTO category(name)
VALUES
    ('Computer'),
    ('Mobile Phone'),
    ('Laptop'),
    ('Accessory');


-- USERS
INSERT INTO users(email, first_name, last_name, password)
VALUES
    ('phat@gmail.com', 'Phat', 'Nguyen', '$2a$10$u5xPEnqTU3lcPPItCotEf./kp4k2zj4Q9Mnko5W4nBca4pvhLs7Zq'),
    ('john@gmail.com', 'John', 'Smith', '$2a$10$u5xPEnqTU3lcPPItCotEf./kp4k2zj4Q9Mnko5W4nBca4pvhLs7Zq'),
    ('david@gmail.com', 'David', 'Nguyen', '$2a$10$u5xPEnqTU3lcPPItCotEf./kp4k2zj4Q9Mnko5W4nBca4pvhLs7Zq'),
    ('smith@gmail.com', 'Smith', 'Nguyen', '$2a$10$u5xPEnqTU3lcPPItCotEf./kp4k2zj4Q9Mnko5W4nBca4pvhLs7Zq'),
    ('adam@gmail.com', 'Adam', 'John', '$2a$10$u5xPEnqTU3lcPPItCotEf./kp4k2zj4Q9Mnko5W4nBca4pvhLs7Zq'),
    ('michael@gmail.com', 'Michael', 'Mike', '$2a$10$u5xPEnqTU3lcPPItCotEf./kp4k2zj4Q9Mnko5W4nBca4pvhLs7Zq');

-- ROLE
INSERT INTO role(role)
VALUES
    ('ADMIN'),
    ('USER');

INSERT INTO users_roles(user_id, roles_id)
VALUES
    (1, 1),
    (1, 2),
    (2, 1),
    (3, 1),
    (4, 2),
    (5, 2),
    (5, 1),
    (6, 2);

-- PRODUCT
INSERT INTO product(name, price, rating, category_id, user_id)
VALUES
    ('Product-name-1', 20, 4, 1, 1),
    ('Product-name-2', 30, 5, 1, 1),
    ('Product-name-3', 40, 6, 1, 1),
    ('Product-name-4', 50, 4, 1, 2),
    ('Product-name-5', 30.3, 1, 1, 2),
    ('Product-name-6', 20, 4, 2, 3),
    ('Product-name-7', 30, 5, 2, 3),
    ('Product-name-8', 40, 6, 2, 4),
    ('Product-name-9', 50, 4, 2, 5),
    ('Product-name-10', 30.3, 1, 2, 6);