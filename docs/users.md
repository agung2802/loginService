# Users API Docs

## Register

Endpoint API: POST /api/users

Request body:

```json
{
    "username": "rizki",
    "password": "rahasia",
    "fullName": "Agung Rizki Hermawan"
}
```
Response body (Success):
``` json
{
    "data": "OK"
}
```

Response Body (error):
```json
{
    "error": "username Must Not Blank",
    "error_code": 401
}
```


## Login

Endpoint API: POST api/auth/login

Request body:

```json
{
    "username": "rizki",
    "password": "rahasia",
}
```
Response body (Success):
``` json
{
    "data": {
        "Token": "TOKEN",
        "ExpiredAt": 12313123 //millisecond
    }
}
```

Response Body (error):
```json
{
    "error": "username or password Wrong",
    "error_code": 401
}
```

## Get Users

Endpoint API: GET api/users/current

Request Header:
- X-API-TOKEN: "Token" (Mandatory)

Response body (Success):
``` json
{
    "data": {
        "username": "rizki",
        "fullName": "Agung Rizki Hermawan"
    }
}
```

Response Body (error):
```json
{
    "error": "unauthorize",
    "error_code": 401
}
```

## Update Users

Endpoint API: PATCH api/users/current

Request Header:
- X-API-TOKEN: "Token" (Mandatory)

Request body (Success):
``` json
{
    "fullName": "rizki", //put if only want to update name
    "password": "Agung Rizki Hermawan" //put if only want to update password
}
```

Response body (Success):
``` json
{
    "data": {
        "username": "rizki",
        "fullName": "Agung Rizki Hermawan" //millisecond
    }
}
```

Response Body (error):
```json
{
    "error": "unauthorize",
    "error_code": 401
}
```

## Logout

Endpoint API: DELETE api/auth/logout

Request Header:
- X-API-TOKEN: "Token" (Mandatory)


Response body (Success):
``` json
{
    "data": "OK"
}
```