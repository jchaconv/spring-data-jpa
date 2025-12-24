drop table if exists book;
drop table if exists author;
drop table if exists author_uuid;
drop table if exists book_uuid;
drop table if exists book_natural;
drop table if exists author_composite;
--drop table if exists author_seq;
--drop table if exists book_seq;

create table book (
                      id bigint not null auto_increment,
                      isbn varchar(255),
                      publisher varchar(255),
                      title varchar(255),
                      author_id bigint,
                      primary key (id)
) engine=InnoDB;


/*
create table book_seq (
                          next_val bigint
) engine=InnoDB;

insert into book_seq values ( 1 );*/

/* to avoid error creation because don't have liquibase and flyway configurations */
create table author (
                      id bigint not null auto_increment,
                      first_name varchar(255),
                      last_name varchar(255),
                      primary key (id)
) engine=InnoDB;

/*
create table author_seq (
                          next_val bigint
) engine=InnoDB;

insert into author_seq values ( 1 );*/

create table author_uuid
(
    id varchar(36) not null,
    first_name varchar(255),
    last_name varchar(255),
    primary key (id)
) engine=InnoDB;


create table book_uuid
(
    id binary(16) not null,
    isbn varchar(255),
    publisher varchar(255),
    title varchar(255),
    primary key (id)
) engine=InnoDB;


create table book_natural
(
    title varchar(255) not null,
    isbn varchar(255),
    publisher varchar(255),
    primary key (title)
) engine=InnoDB;


create table author_composite
(
    first_name varchar(255),
    last_name varchar(255),
    country varchar(255),
    primary key (first_name, last_name)
) engine=InnoDB;

