SET FOREIGN_KEY_CHECKS = 0;

drop table if exists product_category;
drop table if exists order_line;
drop table if exists order_approval;
drop table if exists order_header;
drop table if exists product;
drop table if exists category;
drop table if exists customer;


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

-- add customer entity

create table customer
(
    id                 bigint not null auto_increment primary key,
    customer_name      varchar(50),
    address            varchar(30),
    city      varchar(30),
    state     varchar(30),
    zip_code  varchar(30),
    phone              varchar(20),
    email              varchar(255),
    created_date       timestamp,
    last_modified_date timestamp
);

alter table order_header
    add column customer_id bigint;

alter table order_header
    add constraint order_customer_fk
        foreign key (customer_id) references customer (id);

alter table order_header drop column customer;

insert into customer (customer_name, address, city, state, zip_code, phone, email)
values ('Customer 1', '123 Duval', 'Key West', 'FL', '33040', '305.292.1435',
        'cheeseburger@margaritville.com' );

update order_header set order_header.customer_id = (select id from customer limit 1);

-- one to one relationship
create table order_approval
(
    id                 bigint not null auto_increment primary key,
    approved_by        varchar(50),
    created_date       timestamp,
    last_modified_date timestamp
);

alter table order_header
    add column order_approval_id bigint;

alter table order_header
    add constraint order_approval_fk
        foreign key (order_approval_id) references order_approval (id);

-- one2one bidirectional
alter table order_approval
    add column order_header_id bigint;

alter table order_approval
    add constraint order_hdr_fk
        foreign key (order_header_id) references order_header (id);