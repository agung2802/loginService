# Contact API Spec

## Create Contact

Endpoint API: POST /api/contacts

Request Header:
- X-API-TOKEN: "Token" (Mandatory)

Request Body:
```json
{
    "firstName": "Agung",
    "lastName": "Rizki",
    "email": "agungrizki2802@gmail.com",
    "phone": "081297651725"
}
```

Response Body(success):
```json
{
    "data": {
        "id": 1,
        "firstName": "Agung",
        "lastName": "Rizki",
        "email": "agungrizki2802@gmail.com",
        "phone": "081297651725"
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

## Get Contact

endpoint API: GET api/contacts/{idContact}

Request Header:
- X-API-TOKEN: "Token" (Mandatory)

Response Body(success):
```json
{
    "data": {
            "id": 1,
            "firstName": "Agung",
            "lastName": "Rizki",
            "email": "agungrizki2802@gmail.com",
            "phone": "081297651725"
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

## Update Contact

endpoint API: PUT api/contacts/{id_contact}

Request Header:
- X-API-TOKEN: "Token" (Mandatory)

Request Body:
```json
{
    "firstName": "Agung",
    "lastName": "Rizki",
    "email": "agungrizki2802@gmail.com",
    "phone": "081297651725"
}
```

Response Body(success):
```json
{
    "data": {
        "id": 1,
        "firstName": "Agung",
        "lastName": "Rizki",
        "email": "agungrizki2802@gmail.com",
        "phone": "081297651725"
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

## Search Contact

endpoint API: GET api/contacts

Query Param :

- name: String, contact firstname or lastname, using like query, optional
- email: String, contact email, using like query, optional
- phone: String, contact phone, using like query, optional
- page: Integer, start from 0, default 0
- size: Integer, default 10

Request Header:
- X-API-TOKEN: "Token" (Mandatory)

Response Body(success):
```json
{
    "data": [
        {
        "id": 1,
        "firstName": "Agung",
        "lastName": "Rizki",
        "email": "agungrizki2802@gmail.com",
        "phone": "081297651725"
        }
    ],
    "paging": {
        "currentPage": 0,
        "totalPage": 10,
        "size": 10
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

## Remove Contact

endpoint API: DELETE api/contacts/{id_contact}

Request Header:
- X-API-TOKEN: "Token" (Mandatory)

Response Body(success):
```json
{
    "data": "OK"
}
```

Response Body (error):
```json
{
    "error": "unauthorize",
    "error_code": 401
}
```