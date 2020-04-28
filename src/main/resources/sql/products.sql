\c yugabyte
CREATE TABLE products (
	
	product_id SERIAL NOT NULL ,
	product_name VARCHAR NOT NULL ,
	description VARCHAR ,
	price BIGINT ,
	PRIMARY KEY (product_id HASH)
);