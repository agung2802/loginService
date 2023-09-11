# Address API Spec

## Create Address

Endpoint: POST api/contacts/{idContact}/addresses

Request Header:
- X-API-TOKEN: "Token" (Mandatory)

Request Body:
``` json
{
    "id": "random String",
    "street": "Jalan kenangan",
    "city": "kota",
    "province": "Provinsi",
    "country": "negara",
    "postalCode": "123123"
}
```

Response Body (success):
``` json
{
    "data": {
        "id": "random String",
        "street": "Jalan kenangan",
        "city": "kota",
        "province": "Provinsi",
        "country": "negara",
        "postalCode": "123123"
    }
}
```

Response Body (error):
```json
{
    "error": "errorMessage",
    "error_code": 401
}
```

## Update Address

Endpoint: PUT api/contacts/{idContact}/addresses/{idAddress}

Request Header:
- X-API-TOKEN: "Token" (Mandatory)

Request Body:
``` json
{
    "id": "random String",
    "street": "Jalan kenangan",
    "city": "kota",
    "province": "Provinsi",
    "country": "negara",
    "postalCode": "123123"
}
```

Response Body (success):
``` json
{
    "data": {
        "id": "random String",
        "street": "Jalan kenangan",
        "city": "kota",
        "province": "Provinsi",
        "country": "negara",
        "postalCode": "123123"
    }
}
```

Response Body (error):
```json
{
    "error": "errorMessage",
    "error_code": 401
}
```

## Get Address

Endpoint: GET api/contacts/{idContact}/addresses/{idAddress}

Request Header:
- X-API-TOKEN: "Token" (Mandatory)

Response Body (success):
``` json
{
    "data": {
        "id": "random String",
        "street": "Jalan kenangan",
        "city": "kota",
        "province": "Provinsi",
        "country": "negara",
        "postalCode": "123123"
    }
}
```

Response Body (error):
```json
{
    "error": "errorMessage",
    "error_code": 401
}
```

## Remove Address

Endpoint: DELETE api/contacts/{idContact}/addresses/{idAddress}

Request Header:
- X-API-TOKEN: "Token" (Mandatory)

Response Body (success):
``` json
{
    "data": "OK"
}
```

Response Body (error):
```json
{
    "error": "errorMessage",
    "error_code": 401
}
```

## List Address
Endpoint: GET api/contacts/{idContact}/addresses

Request Header:
- X-API-TOKEN: "Token" (Mandatory)

Response Body (success):
``` json
{   "data": [
        {
            "id": "random String",
            "street": "Jalan kenangan",
            "city": "kota",
            "province": "Provinsi",
            "country": "negara",
            "postalCode": "123123"
        }
    ]
}
```

Response Body (error):
```json
{
    "error": "errorMessage",
    "error_code": 401
}
```