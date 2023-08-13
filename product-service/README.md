# product-service 

## Setup 

### Required 
* Java 11
* Docker 

### MongoDB 
```
docker run --name mac-mongo -p 27017:27017 --env=MONGO_INITDB_ROOT_USERNAME=root --env=MONGO_INITDB_ROOT_PASSWORD=password -d mongo:latest

use mac-emarket
db.createCollection('products')
db.createCollection('categories')
```

## Run 

### Init Data 
```

curl --location 'http://localhost:8080/mac-emarket/api/categories' \
--header 'Content-Type: application/json' \
--data '{
    "name": "Electronic",
    "active": true, 
    "subCategories": [
        {
            "name": "Laptop",
            "active": true
        },
        {
            "name": "Mobile",
            "active": true
        },
        {
            "name": "Computer",
            "active": true,
            "subCategories": [
                {
                    "name": "All In One",
                    "active": true
                },
                {
                    "name": "Desktop Computer",
                    "active": true
                },
                {
                    "name": "Gaming Computer",
                    "active": true
                }
            ]
        }
    ]
}'


curl --location 'http://localhost:8080/mac-emarket/api/categories' \
--header 'Content-Type: application/json' \
--data '{
    "name": "Beauty",
    "active": true,
    "subCategories": [
        {
            "name": "Face",
            "active": true,
            "subCategories": [
                {
                    "name": "Serum",
                    "active": true
                },
                {
                    "name": "Moisturiser",
                    "active": true
                },
                {
                    "name": "Cleanser",
                    "active": true
                }
            ]
        },
        {
            "name": "Body",
            "active": true,
            "subCategories": [
                {
                    "name": "Soap",
                    "active": true
                },
                {
                    "name": "Sun Block",
                    "active": true
                }
            ]
        },
        {
            "name": "Hair",
            "active": true,
            "subCategories": [
                {
                    "name": "Shampoo",
                    "active": true
                },
                {
                    "name": "Hair Treatment",
                    "active": true
                }
            ]
        }
    ]
}'

```

### API Docs
* Using https://springdoc.org/v1/
* Open API Docs
  * http://localhost:8080/mac-emarket/api/v3/api-docs
  * http://localhost:8080/mac-emarket/api/webjars/swagger-ui/index.html

```shell script

curl --location 'http://localhost:8080/mac-emarket/api/products' \
--header 'Content-Type: application/json' \
--data '{
    "sku": "sku2",
    "name": "MacBook Pro 2023",
    "description": "Latest MacBook Prod 2023.",
    "price": 79000
}'
```
