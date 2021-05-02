# Tokonyadia App

## Database

### ERD
[**SQL FILE HERE**](https://git.enigmacamp.com/enigma-camp/class-mandiri/muhammad-kamil/java/challenge/challenge-tokonyadia-boot/-/blob/master/documentation/tokonyadia_emil.sql)

![erd_tokonyadia](<./documentation/erd.png>) 

## Guide
### Set Up
* Turn on kafka zookeper and kafka server
* Turn on redis server
* Configure redis-cli then set the key and value with ```set wallet.endpoint.url http://localhost:8090/debit```


### Send Email Asynchronous

* Use Create Purchase API
* You can use the [jar file](https://git.enigmacamp.com/enigma-camp/class-mandiri/muhammad-kamil/java/challenge/challenge-tokonyadia-boot/-/tree/email-feature/jar) to run this app 

### GET Customer Purchases By Merchant ID
* url     : localhost:8080/merchant/{merchantId}/customer
* method  : POST
    
    **Example** 
    
    url     : localhost:8080/merchant/4028abff790400d30179040349970001/customer
        
    **Result**
```
[
    {
        "customerName": "Kamil",
        "totalPurchase": 10500
    },
    {
        "customerName": "Maudy Haikal Abdilla",
        "totalPurchase": 2100000
    },
    {
        "customerName": "Melia Suspariana",
        "totalPurchase": 47040000
    }
]
```


### Customer API
* **Create Customer**

    url     : localhost:8080/customer
    
    method  : POST

    body    : 
    ```
    {
        "name": "Melia Suspariana",
        "email": "melia@gmail.com",
        "phoneNumber": "082283777098",
        "address": "Jl. Pepaya Sungai Salak",
        "status": 1
    }
    ```


* **Update Customer**

    url     : localhost:8080/customer
    
    method  : PUT

    body    : 
    ```
    {
        "id": "4028abff79026817017902682b360000",
        "name": "Melia Suspariana Edited",
        "email": "melia@gmail.com",
        "phoneNumber": "082283777098",
        "address": "Jl. Pepaya Sungai Salak",
        "status": 1
    }
    ```

* **Get All Customer w/ Pagination**

    url     : localhost:8080/customers?page=0&size=10
    
    method  : GET

    params  : 
    ```
        page    : 0
        size    : 10
    ```

* **Get Customer By ID**

    url     : localhost:8080/customer/{customerId}
    
    method  : GET

* **Delete Customer By ID**

    url     : localhost:8080/customer/{customerId}
    
    method  : DELETE

### Merchant API
* **Create Merchant**

    url     : localhost:8080/merchant
    
    method  : POST

    body    : 
    ```
    {
        "name": "Sociolla Store",
        "siup": "xxx-xxx-xxx",
        "address": "Jl. Pepaya Sungai Salak",
        "email": "socialla@gmail.com",
        "phoneNumber": "082283777098"
    }
    ```
* **Update Merchant**

    url     : localhost:8080/merchant
    
    method  : PUT

    body    : 
    ```
    {
        "id": "4028abff790400d301790403650f0002",
        "name": "Sociolla Store Edited",
        "siup": "xxx-xxx-xxx",
        "address": "Jl. Pepaya Sungai Salak",
        "email": "socialla@gmail.com",
        "phoneNumber": "082283777098"
    }
    ```
* **Get All Merchants w/ Pagination**

    url     : localhost:8080/merchants?page=0&size=10
    
    method  : GET

    params  : 
    ```
        page    : 0
        size    : 10
    ```
    
* **Get Merchant By ID**

    url     : localhost:8080/merchant/{merchantId}
    
    method  : GET

* **Get Product By Merchant**

    url     : localhost:8080/merchant/{merchantId}/product
    
    method  : GET
    
* **Delete Merchant By ID**

    url     : localhost:8080/merchant/{merchantId}
    
    method  : DELETE


### Product API
* **Create Product**

    url     : localhost:8080/product
    
    method  : POST

    body    : 
    ```
    {
        "name": "The Ordinary",
        "price": "140000",
        "status": "1",
        "stock": "20",
        "merchant": {
            "id" : "4028abff790400d30179040349970001"
        }
    }
    ```
* **Update Product**

    url     : localhost:8080/product
    
    method  : PUT

    body    : 
    ```
    {
        "id": "4028abff790400d30179040c296f0004",
        "name": "The Ordinary",
        "price": 140000,
        "status": 1,
        "stock": 25,
        "createdDate": "2021-04-24T00:00:00.000+07:00",
        "updateDate": "2021-04-24T00:00:00.000+07:00",
        "merchant": {
            "id" : "4028abff790400d30179040349970001"
        }
    }
    ```
* **Get All Products w/ Pagination**

    url     : localhost:8080/products?page=0&size=10
    
    method  : GET

    params  : 
    ```
        page    : 0
        size    : 10
    ```
* **Get Product By ID**

    url     : localhost:8080/product/{productId}
    
    method  : GET
    

* **Delete Product By ID**

    url     : localhost:8080/product/{productId}
    
    method  : DELETE



### Purchase API
* **Create Purchase**

    url     : localhost:8080/purchase
    
    method  : POST

    body    : 
    ```
    {
        "customer": {
            "id" : "4028abff790400d30179040e6d930005"
        },
        "product": {
            "id" :  "4028abff790400d30179040c296f0004"
        },
        "quantity": 5
    }
    ```
    
    **Business logic**
    - Bila Produk dibeli, maka stock produk akan berkurang berdasarkan quantity pada saat pembelian.
    ```
    {
        "id": "4028abff7909244f0179092943aa0001",
        "purchaseDate": "2021-04-25T20:12:49.829+07:00",
        "quantity": 1,
        "customer": {
            "id" : "4028abff790400d30179040e6d930005"
        },
        "product": {
            "id" :  "4028abff790400d30179040c296f0004"
        },
    }
    ```
    - Apabila stock product bernilai 0, maka tidak bisa dilakukan pembelian, dan akan diberikan message response
    ```
        {
            "timestamp": "2021-04-25T20:10:54.151+07:00",
            "status": 406,
            "error": "Not Acceptable",
            "message": "Product 'Shampoo LifeBuoy' Out Of Stock",
            "path": "/purchase"
        }
    
    ```
    - Apabila Quantity purchase yang dibeli lebih besar dari stock product, maka product tidak akan bisa dibeli, dan akan diberikan message response
     
     ```
        {
            "timestamp": "2021-04-25T20:09:12.816+07:00",
            "status": 406,
            "error": "Not Acceptable",
            "message": "Product 'The Ordinary' Not Enough",
            "path": "/purchase"
        }
    
    ```

* **Get All Purchase w/ Pagination**

    url     : localhost:8080/purchases?page=0&size=10
    
    method  : GET

    params  : 
    ```
        page    : 0
        size    : 10
    ```

* **Get Purchase By ID**

    url     : localhost:8080/purchase/{purchaseId}
    
    method  : GET
    

* **Delete Purchase By ID**

    url     : localhost:8080/purchase/{purchaseId}
    
    method  : DELETE