# Trainer Management API

Trainer Management is an API to allowed the users to create, list, get and delete objects called Trainer

## Security

Not implemented yet

## Swagger

In order to test and try it out the API, a swagger could be find in [http://localhost:8080/swagger-ui.html]([http://localhost:8080/swagger-ui.html])

## Running

__Maven__

 - Java version: 11
 Under the folder /target, run the command *java -jar trainermanagement-1.0.0.jar*
 
__Docker__

 - On the root folder of the project, run the command *docker compose up*

## Trainer Management

See further below the operations to create and get a trainer by id.

__POST /trainers__  - Creates a new trainer

*Request Body*

```
{
	"email": "trainer@campgladiator.com",
	"phone": "5125125120",
	"first_name": "Fearless",
	"last_name": "Contender"
}
```
- email: String - required
- phone: String - optional, but should have 10 digits
- first_name - String - required
- last_name - String - optional

*Response Header* - Location (e.g. /trainers/trainer-id-000001)

*Response Body*  -  __No__

201 - Trainer was created successfully

400 - It is missing one of than parameters

409 - There is another trainer already added with the same email

500 - Internal Server Error

---

__GET /trainers/{trainerId}__  - Retrieves a trainer

*PathParam* 
- trainerId - The trainer identification ( ID ) - required: true

*Request Body*  -  __No__

*Response Body*

```
{
	"id" : "trainer-id-000001"
	"email": "trainer@campgladiator.com",
	"phone": "5125125120",
	"first_name": "Fearless",
	"last_name": "Contender"
}

```

200 - Trainer is retrieved

404 - Trainer was not found

500 - Internal Server Error