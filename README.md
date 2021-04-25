# Tokonyadia App

## Database
### ERD
![erd_tokonyadia](<./erd.png>) 


## Guide

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

* ** Get Customer By ID**

    url     : localhost:8080/customer/{customerId}
    
    method  : GET

* ** Delete Customer By ID**

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
        "merchantId": "4028abff790400d30179040349970001"
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
        "merchantId": "4028abff790400d30179040349970001"
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
        "customerId": "4028abff790400d30179040e6d930005",
        "productId": "4028abff790400d30179040c296f0004",
        "quantity": 5
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