drop table if exists book cascade;
drop table if exists author;

create table book
(
    id        bigint not null auto_increment primary key,
    isbn      varchar(255),
    publisher varchar(255),
    title     varchar(255),
    author_id BIGINT
) engine = InnoDB;

create table author
(
    id         bigint not null auto_increment primary key,
    first_name varchar(255),
    last_name  varchar(255)
) engine = InnoDB;

alter table book
    add constraint book_author_fk foreign key (author_id) references author (id);

insert into author (first_name, last_name) values ('Craig', 'Walls');

insert into book (isbn, publisher, title, author_id) values ('978-1617294945', 'Simon & Schuster',
                                                             'Spring in Action, 5th Edition',(select id from author where first_name = 'Craig' and last_name = 'Walls') );

insert into book (isbn, publisher, title, author_id) values ('978-1617292545', 'Simon & Schuster',
                                                             'Spring Boot in Action, 1st Edition',(select id from author where first_name = 'Craig' and last_name = 'Walls') );

insert into book (isbn, publisher, title, author_id) values ('978-1617297571', 'Simon & Schuster',
                                                             'Spring in Action, 6th Edition',(select id from author where first_name = 'Craig' and last_name = 'Walls') );

insert into author (first_name, last_name) values ('Eric', 'Evans');

insert into book (isbn, publisher, title, author_id) values ('978-0321125217', 'Addison Wesley',
                                                             'Domain-Driven Design',(select id from author where first_name = 'Eric' and last_name = 'Evans') );

insert into author (first_name, last_name) values ('Hugo', 'Martin');

insert into book (isbn, publisher, title, author_id) values ('978-0134494166', 'Addison Wesley',
                                                             'Clean Code',(select id from author where first_name = 'Hugo' and last_name = 'Martin') );

-- to test pagination and sorting

INSERT INTO book (isbn, publisher, title, author_id) VALUES
                                                         ('978-1491950357', 'O’Reilly Media', 'Learning Python', 1),
                                                         ('978-0134685991', 'Addison-Wesley', 'Effective Java', 2),
                                                         ('978-0596007126', 'O’Reilly Media', 'Head First Design Patterns', 3),
                                                         ('978-1617294945', 'Manning', 'Spring in Action', 1),
                                                         ('978-0132350884', 'Prentice Hall', 'Clean Codes', 2),
                                                         ('978-1492078005', 'O’Reilly Media', 'Kubernetes Up and Running', 3),
                                                         ('978-1788627992', 'Packt', 'Hands-On Machine Learning', 1),
                                                         ('978-0134494166', 'Addison-Wesley', 'Clean Architecture', 2),
                                                         ('978-0596517748', 'O’Reilly Media', 'JavaScript: The Good Parts', 3),
                                                         ('978-1617293986', 'Manning', 'Microservices Patterns', 1),

                                                         ('978-0134853987', 'Addison-Wesley', 'Refactoring', 2),
                                                         ('978-1491904244', 'O’Reilly Media', 'You Don’t Know JS Yet', 3),
                                                         ('978-1788476248', 'Packt', 'Mastering React', 1),
                                                         ('978-0134190440', 'Prentice Hall', 'The Go Programming Language', 2),
                                                         ('978-1492045526', 'O’Reilly Media', 'Designing Data-Intensive Applications', 3),
                                                         ('978-1617294136', 'Manning', 'Docker in Action', 1),
                                                         ('978-0134757599', 'Addison-Wesley', 'Core Java Volume I', 2),
                                                         ('978-0596805524', 'O’Reilly Media', 'Programming Perl', 3),
                                                         ('978-1788992748', 'Packt', 'Python Deep Learning', 1),
                                                         ('978-0133594140', 'Prentice Hall', 'Agile Software Development', 2),

                                                         ('978-1491962299', 'O’Reilly Media', 'Fluent Python', 3),
                                                         ('978-1617294143', 'Manning', 'Soft Skills for Developers', 1),
                                                         ('978-0131177055', 'Addison-Wesley', 'Patterns of Enterprise Application Architecture', 2),
                                                         ('978-1789349916', 'Packt', 'Learning DevOps', 3),
                                                         ('978-0596009205', 'O’Reilly Media', 'Head First Java', 1),
                                                         ('978-0132354790', 'Prentice Hall', 'Clean Coder', 2),
                                                         ('978-1617295546', 'Manning', 'Grokking Algorithms', 3),
                                                         ('978-1789613475', 'Packt', 'Data Science with Python', 1),
                                                         ('978-0135166307', 'Addison-Wesley', 'Effective Modern C++', 2),
                                                         ('978-1492056355', 'O’Reilly Media', 'Cloud Native Patterns', 3),

                                                         ('978-1617292828', 'Manning', 'Java 8 in Action', 1),
                                                         ('978-1788839449', 'Packt', 'Hands-On RESTful APIs', 2),
                                                         ('978-0596101015', 'O’Reilly Media', 'Programming PHP', 3),
                                                         ('978-0135974445', 'Addison-Wesley', 'Practical Object-Oriented Design', 1),
                                                         ('978-1617294532', 'Manning', 'Deep Learning with Java', 2),
                                                         ('978-1789955759', 'Packt', 'Learning SQL', 3),
                                                         ('978-0596529321', 'O’Reilly Media', 'Linux Pocket Guide', 1),
                                                         ('978-0137081073', 'Addison-Wesley', 'The Mythical Man-Month', 2),
                                                         ('978-1617295850', 'Manning', 'AI Basics for Developers', 3),
                                                         ('978-1789533643', 'Packt', 'Modern Web Development', 1),

                                                         ('978-0596516178', 'O’Reilly Media', 'Java Concurrency in Practice', 2),
                                                         ('978-0134494326', 'Addison-Wesley', 'Domain-Driven Design', 3);


insert into author (first_name, last_name) values ('John', 'Walls');
insert into author (first_name, last_name) values ('Sonny', 'Walls');
insert into author (first_name, last_name) values ('Emily', 'Walls');
insert into author (first_name, last_name) values ('Michael', 'Walls');

insert into author (first_name, last_name) values ('James', 'Martin');
insert into author (first_name, last_name) values ('Laura', 'Martin');
insert into author (first_name, last_name) values ('Beatriz', 'Martin');

insert into author (first_name, last_name) values ('Joshua', 'Martin');
insert into author (first_name, last_name) values ('Samuel', 'Martin');
insert into author (first_name, last_name) values ('Daniel', 'Martin');

insert into author (first_name, last_name) values ('Martin', 'Fowler');
insert into author (first_name, last_name) values ('Rebecca', 'Fowler');
insert into author (first_name, last_name) values ('Thomas', 'Fowler');

insert into author (first_name, last_name) values ('Kent', 'Beck');
insert into author (first_name, last_name) values ('Eric', 'Beck');
insert into author (first_name, last_name) values ('Susan', 'Beck');

insert into author (first_name, last_name) values ('Brian', 'Martin');
insert into author (first_name, last_name) values ('Kevin', 'Martin');

insert into author (first_name, last_name) values ('Linus', 'Torvalds');
insert into author (first_name, last_name) values ('Maria', 'Torvalds');
