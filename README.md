# REST API

The REST API of servlet usage example

## Register new user

### Request

`POST /user/`

    curl --location 'localhost:8080/user' --header 'Content-Type: application/json' --data '{"userName": "Test Name"}'

### Response

    HTTP/1.1 200 OK
    Date: Sat, 02 Sep 2023 14:22:01 GMT
    Content-Length: 87
    Server: Jetty(11.0.15)
    
    {
        "name" : "Test Name",
        "cardNumber" : "0000 0000 0000 0000",
        "pin" : "0000"
    }

## Change user pin

### Request

`PATCH /user/`

    curl --location --request PATCH 'localhost:8080/user' \
    --header 'Content-Type: application/json' \
    --data '{
                "user": {
                    "name" : "Test Name",
                    "cardNumber" : "0000 0000 0000 0000",
                    "pin" : "0000"
                },
                "newPin": "0001"
            }'

### Response

    HTTP/1.1 200 OK
    Date: Sat, 02 Sep 2023 14:53:30 GMT
    Content-Length: 0
    Server: Jetty(11.0.15)

## Check user balance

### Request

`GET /user/balance?name=UserName&cardNumber=CardNumber&pin=CardPin`

    curl --location 'localhost:8080/user/balance?name=Test%20Name&cardNumber=0000%200000%200000%200000&pin=0001'

### Response

    HTTP/1.1 200 OK
    Date: Sat, 02 Sep 2023 14:50:07 GMT
    Content-Length: 23
    Server: Jetty(11.0.15)
    
    {
        "balance" : 0
    }

## Top up user account

### Request

`PATCH /user/account/add`

    curl --location --request PATCH 'localhost:8080/user/account/add' \
    --header 'Content-Type: application/json' \
    --data '{
                "user": {
                    "name" : "Test Name",
                    "cardNumber" : "0000 0000 0000 0000",
                    "pin" : "0001"
                },
                "money": {
                    "counter5000" : 1,
                    "counter1000" : 1,
                    "counter500" : 1,
                    "counter100" : 1,
                    "counter50" : 1,
                    "counter10" : 1
                }
            }'

### Response

    HTTP/1.1 200 OK
    Date: Sat, 02 Sep 2023 14:56:19 GMT
    Content-Length: 0
    Server: Jetty(11.0.15)

## Withdraw money from the account

### Request

`PATCH /user/account/sub`

    curl --location --request PATCH 'localhost:8080/user/account/sub' \
    --header 'Content-Type: application/json' \
    --data '{
                "user": {
                    "name" : "Test Name",
                    "cardNumber" : "0000 0000 0000 0000",
                    "pin" : "0001"
                },
                "amount": 1000
            }'

### Response

    HTTP/1.1 200 OK
    Date: Sat, 02 Sep 2023 14:57:18 GMT
    Content-Length: 131
    Server: Jetty(11.0.15)
    
    {
        "counter5000" : 0,
        "counter1000" : 1,
        "counter500" : 0,
        "counter100" : 0,
        "counter50" : 0,
        "counter10" : 0
    }

## Delete account and get all remaining money

### Request

`DELETE /user`

    curl --location --request DELETE 'localhost:8080/user' \
    --header 'Content-Type: application/json' \
    --data '{
                "name" : "Test Name",
                "cardNumber" : "0000 0000 0000 0000",
                "pin" : "0001"
            }'

### Response

    HTTP/1.1 200 OK
    Date: Sat, 02 Sep 2023 14:59:26 GMT
    Content-Length: 131
    Server: Jetty(11.0.15)
    
    {
        "counter5000" : 1,
        "counter1000" : 0,
        "counter500" : 1,
        "counter100" : 1,
        "counter50" : 1,
        "counter10" : 1
    }