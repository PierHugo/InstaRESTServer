create table user
(
    id       bigint auto_increment primary key,
    username varchar(255) not null,
    password varchar(255) not null
) engine = InnoDB;

create table post
(
    id          bigint auto_increment primary key,
    image_url   varchar(255),
    description varchar(255),
    user_id     bigint not null
) engine = InnoDB;

create table comment
(
    id      bigint auto_increment primary key,
    comment varchar(255),
    user_id bigint not null,
    post_id bigint not null
) engine = InnoDB;

ALTER TABLE post
    ADD CONSTRAINT FOREIGN KEY (user_id) REFERENCES user (id);

ALTER TABLE comment
    ADD CONSTRAINT FOREIGN KEY (user_id) REFERENCES user (id),
    ADD CONSTRAINT FOREIGN KEY (post_id) REFERENCES post (id);

INSERT INTO user
VALUES (0, 'user1', '5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8');
-- password : password
INSERT INTO user
VALUES (0, 'user2', '5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8');
-- password : password

INSERT INTO post
VALUES (0, 'https://www.gstatic.com/webp/gallery/1.png', 'desc1', '1');

INSERT INTO post
VALUES (0, 'https://www.gstatic.com/webp/gallery/2.png', 'desc2', '2');

INSERT INTO comment
VALUES (0, 'com1', '1', '1');
INSERT INTO comment
VALUES (0, 'com2', '2', '2');