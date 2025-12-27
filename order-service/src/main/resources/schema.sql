
drop table if exists order_line cascade;
drop table if exists order_header cascade;
drop table if exists product_category cascade;
drop table if exists category cascade;
drop table if exists product cascade;

create table order_header
(
    id        bigint not null auto_increment primary key,
    customer      varchar(255)
) engine = InnoDB;

alter table order_header
    add column shipping_address varchar(30),
    add column shipping_city varchar(30),
    add column shipping_state varchar(30),
    add column shipping_zip_code varchar(30),
    add column bill_to_address varchar(30),
    add column bill_to_city varchar(30),
    add column bill_to_state varchar(30),
    add column bill_to_zip_code varchar(30);

alter table order_header
    add column order_status varchar(30);

alter table order_header
    add column created_date timestamp;

alter table order_header
    add column last_modified_date timestamp;

create table order_line
(
    id bigint not null auto_increment primary key,
    quantity_ordered int,
    order_header_id bigint,
    created_date timestamp,
    last_modified_date timestamp,
    constraint order_header_pk FOREIGN KEY (order_header_id) references order_header(id)
);

create table product
(
    id bigint not null auto_increment primary key,
    description varchar(100),
    product_status varchar(20),
    created_date timestamp,
    last_modified_date timestamp
) engine = InnoDB;

alter table order_line add column product_id bigint;
alter table order_line add constraint order_line_product_fk
    foreign key (product_id) references product(id);

-- many to many

create table category (
                          id bigint not null auto_increment primary key,
                          description varchar(50),
                          created_date timestamp,
                          last_modified_date timestamp
);

create table product_category (
                                  product_id bigint not null,
                                  category_id bigint not null,
                                  primary key (product_id, category_id),
                                  constraint pc_product_id_fk FOREIGN KEY (product_id) references product(id),
                                  constraint pc_category_id_fk FOREIGN KEY (category_id) references category(id)
);

insert into product (description, product_status, created_date, last_modified_date)
values ('PRODUCT1', 'NEW', now(), now());

insert into product (description, product_status, created_date, last_modified_date)
values ('PRODUCT2', 'NEW', now(), now());

insert into product (description, product_status, created_date, last_modified_date)
values ('PRODUCT3', 'NEW', now(), now());

insert into product (description, product_status, created_date, last_modified_date)
values ('PRODUCT4', 'NEW', now(), now());

insert into category (description, created_date, last_modified_date) VALUES
    ('CAT1', now(), now());

insert into category (description, created_date, last_modified_date) VALUES
    ('CAT2', now(), now());

insert into category (description, created_date, last_modified_date) VALUES
    ('CAT3', now(), now());

insert into product_category (product_id, category_id)
SELECT p.id, c.id FROM product p, category c
where p.description = 'PRODUCT1' and c.description = 'CAT1';

insert into product_category (product_id, category_id)
SELECT p.id, c.id FROM product p, category c
where p.description = 'PRODUCT2' and c.description = 'CAT1';

insert into product_category (product_id, category_id)
SELECT p.id, c.id FROM product p, category c
where p.description = 'PRODUCT1' and c.description = 'CAT3';

insert into product_category (product_id, category_id)
SELECT p.id, c.id FROM product p, category c
where p.description = 'PRODUCT4' and c.description = 'CAT3';