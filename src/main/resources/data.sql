insert into degrees (id, rank)
values (1, 'assistant'),
       (2, 'associate professor'),
       (3, 'professor');

insert into lectors (id, name, degree, SALARY)
values (0, 'Dony', 1, 1000),
       (1, 'Dan', 2, 2000),
       (2, 'Anastasiya', 3, 4000),
       (3, 'Elena', 1, 1500),
       (4, 'Liliya', 2, 10000),
       (5, 'Roman', 3, 20000);


insert into departments (id, name, head_id)
values (1, 'Joy Gangsters', 4),
       (2, 'Dog Lovers', 2),
       (3, 'Sex Addicted', 5);


insert into departments_lectors (lector_id, department_id)
values (5, 3),
       (5, 1),
       (4, 3),
       (3, 2),
       (3, 1),
       (2, 2),
       (1, 2),
       (0, 1);