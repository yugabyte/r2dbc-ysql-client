\c yugabyte
CREATE TABLE inventory (
	
	inventory_id SERIAL NOT NULL ,
	product_id INT NOT NULL ,
	inventory_count INT ,
	PRIMARY KEY (product_id HASH, inventory_id ASC)
);

ALTER TABLE inventory ADD FOREIGN KEY (product_id) REFERENCES products(product_id);