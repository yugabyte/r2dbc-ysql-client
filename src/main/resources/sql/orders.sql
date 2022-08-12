CREATE TABLE orders (
   order_id SERIAL NOT NULL ,
   product_id INT NOT NULL ,
   quantity INT ,
   order_date DATE,
   PRIMARY KEY (order_id DESC)
);

ALTER TABLE orders ADD FOREIGN KEY (product_id) REFERENCES products(product_id);
