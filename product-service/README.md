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
    "subCategories": [
        {
            "name": "Laptop"
        },
        {
            "name": "Mobile"
        },
        {
            "name": "Computer",
            "subCategories": [
                {
                    "name": "All In One"
                },
                {
                    "name": "Desktop Computer"
                },
                {
                    "name": "Gaming Computer"
                }
            ]
        }
    ]
}'


curl --location 'http://localhost:8080/mac-emarket/api/categories' \
--header 'Content-Type: application/json' \
--data '{
    "name": "Beauty",
    "subCategories": [
        {
            "name": "Face",
            "subCategories": [
                {
                    "name": "Serum"
                },
                {
                    "name": "Moisturiser"
                },
                {
                    "name": "Cleanser"
                }
            ]
        },
        {
            "name": "Body",
            "subCategories": [
                {
                    "name": "Soap"
                },
                {
                    "name": "Sun Block"
                }
            ]
        },
        {
            "name": "Hair",
            "subCategories": [
                {
                    "name": "Shampoo"
                },
                {
                    "name": "Hair Treatment"
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
