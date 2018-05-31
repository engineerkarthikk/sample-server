##Description
Sample server which loads data from csv exposes an api to return number in words

##Api
/v1/convert/{value}

##Build Instructions
- git clone
- mvn clean install
- docker-compose build
- docker-compose up (Service has started running)

##Sample request

curl -X GET "http://localhost:19000/v1/convert/1"

##Sample response
{
  "key": "one",
  "value": 1
}