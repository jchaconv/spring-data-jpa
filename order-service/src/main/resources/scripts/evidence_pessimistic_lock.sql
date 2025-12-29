-- before @Lock annotation
insert into product (created_date, description, last_modified_date, product_status, quantity_on_hand)
values (?, ?, ?, ?, ?)

update product
set description=?,
    last_modified_date=?,
    product_status=?,
    quantity_on_hand=?
where id = ?

-- after @Lock annotation
insert into product (created_date, description, last_modified_date, product_status, quantity_on_hand)
values (?, ?, ?, ?, ?)

update product
set description=?,
    last_modified_date=?,
    product_status=?,
    quantity_on_hand=?
where id = ?