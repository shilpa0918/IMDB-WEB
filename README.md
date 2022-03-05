# IMDB-WEB
IMDB web app

## Steps after cloning the project. 
Since spring security is implemented, currently only **GET** requests and the **register** endpoint are permitted with authenticating. 
Hence before executing any requests other than the permitted once we need to register the user and then use the email (as username) and password in the basic auth tab to execute the APIs.

I have already created one user with username (email) - **admin@test.com** and password - **passw0rd**

For other endpoints (POST, PUT, DELETE) authentication is required. For creating a new user please use the endpoint http://imdb-clone-api.herokuapp.com/api/v1/register.
Please find a sample curl request below:

curl --location --request POST 'http://imdb-clone-api.herokuapp.com/api/v1/register' \
--header 'Content-Type: application/json' \
--header 'Accept: application/json' \
--header 'Cookie: JSESSIONID=D50D503F5DE0F98E6D6695984E7ADAB1' \
--data-raw '{  
   "customerName": "admin",  
   "email": "admin@test.com",  
   "password": "passw0rd"
 }'
 
## Postman collection Documentation
https://documenter.getpostman.com/view/10752993/UVkvJXf9#34e08f03-c5e0-4725-96b6-9fc455251db0

## Postman collection link
https://www.getpostman.com/collections/d829c593c84d72804906
