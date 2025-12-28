-- before changes (this query is performed every single time)

select ol1_0.order_header_id,
       ol1_0.id,
       ol1_0.created_date,
       ol1_0.last_modified_date,
       p1_0.id,
       p1_0.created_date,
       p1_0.description,
       p1_0.last_modified_date,
       p1_0.product_status,
       ol1_0.quantity_ordered
from order_line ol1_0
         left join product p1_0 on p1_0.id = ol1_0.product_id
where ol1_0.order_header_id = ?

-- after changes

select c1_0.id,
       c1_0.address,
       c1_0.city,
       c1_0.state,
       c1_0.zip_code,
       c1_0.created_date,
       c1_0.customer_name,
       c1_0.email,
       c1_0.last_modified_date,
       c1_0.phone
from customer c1_0
where upper(c1_0.customer_name) = upper(?)

select oh1_0.id,
       oh1_0.bill_to_address,
       oh1_0.bill_to_city,
       oh1_0.bill_to_state,
       oh1_0.bill_to_zip_code,
       oh1_0.created_date,
       oh1_0.customer_id,
       oh1_0.last_modified_date,
       oh1_0.order_approval_id,
       oh1_0.order_status,
       oh1_0.shipping_address,
       oh1_0.shipping_city,
       oh1_0.shipping_state,
       oh1_0.shipping_zip_code
from order_header oh1_0
where oh1_0.customer_id = ?

select ol1_0.order_header_id,
       ol1_0.id,
       ol1_0.created_date,
       ol1_0.last_modified_date,
       p1_0.id,
       p1_0.created_date,
       p1_0.description,
       p1_0.last_modified_date,
       p1_0.product_status,
       ol1_0.quantity_ordered
from order_line ol1_0
         left join product p1_0 on p1_0.id = ol1_0.product_id
where ol1_0.order_header_id in (select oh1_0.id from order_header oh1_0 where oh1_0.customer_id = ?)
