# Rest Microservices
REST API designed as shop for selling cookies based on Spring, PostgreSQL &amp; Hybernate.
This was done as an Uni assignment. This API does not take into the consideration cookies in an unorganic way of thinking of it. 
The main concept behind representing cookies as such is the concept of **products** and **items**. Since cookies can be different based on what are they made of, 
there is another way of looking at it: *product* is somewhat of the material, meanwhile *item* represents good. The scheme below illustrates data relations. 
![Data Scheme](https://github.com/nikolay-egorov/Shop_Microservices/blob/master/Data.png)


# Structure
The whole service consists of microservices:
## 1. Catalog Service
Incopsulates and manages all the products and items with accordance to data scheme. Handlers:
  1. **GET** /catalog/product  
Returns a list of all products
  2. **POST** /catalog/product  
Creates a new product based on its Dto Object.
  3. **GET** /catalog/product/{product_id}  
Returns a specific product
  4. **PUT** /catalog/product  
Change particular product
  5. **GET** /catalog/product/{product_id}/items  
Returns a list of items based on a particular product
  6. **GET** /catalog/item  
  Returns a list of all items
  7. **POST** /catalog/item
  8. **GET** and **PUT** /catalog/item same as for product
  9. **DELETE** /catalog/item/{item_id}
  Deletes an item. If there is no such item or its connected with paid orderes, nothing happens.

## 2. Accounting Service
Stands for managing orders. Since each order consists of goods coming from catalog service, there several handlers:
  1. **GET** accounting/order
Returns a list of all products
  2. **POST** accounting/order 
Creates a new product based on its Dto Object.
  3. **GET** accounting/order/{order_id}   
Returns a particular order
  4. **PUT**  accounting/order  
Change particular order  
  5. **GET**, **PUT** and **POST** accounting/client  
Works as expected.
  6. **GET** accounting/client/{id}  
  Returns a parictular client based on its id
  7. **DELETE** accounting/client/{id}  
  Deletes client.
  8. **GET** /client/{id}/items  
  Returns a list of items which this client ordered
  
## 3. Payment Service  
  Has a very trivial logic, basically it is for educutainal purpose and interservice communication.  
    1. **POST** /payment/pay  
  Convents order to a payed one.
  
## 4. Gateway Service
  Made with Zuul framework. Basically just moves request further to services since it has no complex logic as microservcies communicate without any conductor.  
  Port is: 8762
## 5. Discovery Service
  Eureka as a discovery service letting every application may be refered by some url. 
  Port is: 8761

# Additional Technologies 
This project uses **RabbitMQ** and **PostgreSQL** as a message broker and database provider respectively. 

# How to run
First make sure you have PostgreSQL and RabbitMQ installed and running since they are required to obtain functionallity. Next, use that
*dropcreate.sql* file to set up proper environment and insert some test data.   
Run Discovery Service first, then others. 
