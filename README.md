
# R2DBC Client for YugabyteDB SQL (YSQL)

This app is a fully reactive Spring Boot implementation backed by YugabyteDB SQL API  (YSQL). [R2DBC](r2dbc.io) provides support for reactive streams, a non-blocking way of accessing data from relational databases, alternative to using blocking JDBC drivers. [R2DBC Specification](https://r2dbc.io/spec/0.8.0.M8/spec/html/#introduction.r2dbc-spi) provides a list of top level APIs for driver vendors to implement, we are using R2DBC driver implementation of PostgreSQL for accessing relational data from YugabyteDB. 

YugabyteDB YSQL API provides PostgreSQL compatible Distributed SQL APIs for microservices applications. Read more about YugabyteDB Architecture [here](https://docs.yugabyte.com/latest/architecture/layered-architecture/)

`r2dbc-ycql-client` app uses the following components:

- Spring WebFlux
- Spring Data R2DBC
- PostgreSQL R2DBC Driver
- Three Node YugabyteDB cluster (v2.1.4.0)

App is a REST based application which exposes Reactive APIs for CRUD operations on Products and Inventory table . The sample application will be implementing below table schema.

![Table Schema](table_diagram.png)


# Environment Setup


## Step 1: Start the YugabyteDB cluster

You can do so using following command from YugabyteDB installation directory,

```
$ ./bin/yb-ctl destroy && ./bin/yb-ctl --rf 3 create --tserver_flags="cql_nodelist_refresh_interval_secs=10" --master_flags="tserver_unresponsive_timeout_ms=10000"
```

This will start a 3-node local cluster with replication factor (RF) 3. The flag cql_nodelist_refresh_interval_secs configures how often the drivers will get notified of cluster topology changes and the following flag tserver_unresponsive_timeout_ms is for the master to mark a node as dead after it stops responding (heartbeats) for 10 seconds.

Note: (Detailed installation instructions)[https://docs.yugabyte.com/latest/quick-start/install/#macos] for YugabyteDB on local workstation.

## Step 2: Initialize YugabyteDB

you can do so by executing the following command:

```
$ ./bin/ysqlsh -f products.sql
$ ./bin/ysqlsh -f inventory.sql
```

Note: You can find the schema file in following [project directory](src/main/resources/sql)

# Build and Run the application

## Step 1: Build the spring boot application

To build the app, execute the following maven command from the project base directory:

```
$ ./mvnw clean package -DskipTests
```

## Step 2: Start the application

you can do so by running the following command:

```
$ java -jar target/r2dbc-ysql-client-1.0.0.jar
```

# Working with REST endpoints

## Create a product

You can create a product listing as follows:
```
$ curl \
  --data '{ "productName": "Notebook", "description": "200 page notebook", "price": 7.50 }' \
  -v -X POST -H 'Content-Type:application/json' http://localhost:8081/products/save
```

You should see the following return value:
```
{
  "productId": "1",
  "productName": "Notebook",
  "description": "200 page, hardbound, blank notebook",
  "price": 7.5}
```
You can connect to YugaByte DB using `ysqlsh` and select these records:

```
yugabyte=# select * from products;
 product_id | product_name |    description    | price
------------+--------------+-------------------+-------
          1 | Notebook     | 200 page notebook |     8
(1 row)
```

## Retrieve the product using ID

You can retrieve a product using ID as follows:

```
$ curl http://localhost:8081/products/1
```

You should see the following:
```
{
  "productId": "1",
  "productName": "Notebook",
  "description": "200 page, hardbound, blank notebook",
  "price": 7.5}
```

## Add inventory details for a product

You can insert inventory details as follows:

```
$ curl \
  --data '{ "productId": 1, "quantity": 100 }' \
  -v -X POST -H 'Content-Type:application/json' http://localhost:8081/inventory/add
```

## SQL Join: Retrieve product information along with inventory details

now, you can join the two tables and retrieve the product information with inventory details

```
$ curl http://localhost:8081/products/join/1
```
You should see the following:

```
{
    "product_id":1,
    "product_name":"Notebook",
    "description":"200 page notebook",
    "price":8,
    "inventory_id":1,
    "inventory_count":100
}
```

## Delete the product using ID 

You can delete a product using ID as follows:

```
$ curl http://localhost:8081/products/delete/1
```
You should see the following:
```
{
  "productId": "1",
  "productName": "Notebook",
  "description": "200 page, hardbound, blank notebook",
  "price": 7.5}
```
