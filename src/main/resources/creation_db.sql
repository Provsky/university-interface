create table departments
(
    id      int          NOT NULL PRIMARY KEY auto_increment,
    name    VARCHAR(100) not null,
    head_id int
);

create table degrees
(
    id   integer primary key,
    rank varchar(50) not null unique
);

create table lectors
(
    id     int          NOT NULL PRIMARY KEY auto_increment,
    name   VARCHAR(100) not null,
    degree int          not null default 1,
    salary double
);
ALTER TABLE lectors
    ADD FOREIGN KEY (degree)
        REFERENCES degrees (id);

create table departments_lectors
(
    department_id int not null,
    lector_id     int not null
);