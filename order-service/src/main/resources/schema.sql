
drop table if exists order_line cascade;
drop table if exists order_header cascade;

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