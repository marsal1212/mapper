drop table user if exists;

create table user
(
    id      INTEGER GENERATED BY DEFAULT AS IDENTITY (START WITH 1) PRIMARY KEY,
    name    VARCHAR(32) DEFAULT 'DEFAULT',
    role_id INTEGER
);

insert into user(id, name, role_id)
values (1, 'admin', 1),
       (2, 'guest', 2);