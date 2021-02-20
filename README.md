## Introduction 
Airport details provides REST API to get the following information
- Runways for each airport given a country code or country name. 
- Top 10 countries with highest number of airports.
- It also supports retrieving the information given a partial/fuzzy country code/name as input parameter, e.g. entering 'zimb' will result in 'Zimbabwe'. 
The api fetches and returns the data based os csv files present in src/main/resorces folder in the application.

## Getting Started
Guide users through getting understand pattern initializr and helps to make contribution in the project.

1.  [Installation process](#installation-process)
2.  [Starting application](#starting-application)
2.	[API references](#api-reference)

## Installation process
### Build

   This project is using maven for building. To build the jar locally use the following command.

   ```bash
   mvn clean install
   ```
### Unit Tests
   To run the Junit tests.
   ```bash
    mvn test
   ```
## Starting application
   To start the application run the below command in the root folder once the application is build successfully.
   ```bash
    java -jar ./target/airportdetails-0.0.1-SNAPSHOT.jar
   ```

## API Reference
Application has endpoints to query information regarding countries with maximum number of airports and runway details for a given country.

### Get Endpoint

**Request**

`GET /maximum/airports`

    GET "http://localhost:8080/maximum/airports" -H  "accept: application/json"

**Response**

response header

      connection: keep-alive 
      content-type: application/json 
      date: Sat,20 Feb 2021 13:09:51 GMT 
      keep-alive: timeout=60 
      transfer-encoding: chunked                  

response body 

      {
        ["countryName": Integer]
      }
      
**Request**

`GET /runways/{countryName}`

    GET "http://localhost:8080/runways/AU" -H  "accept: application/json"

**Response**

response header

      connection: keep-alive 
      content-type: application/json 
      date: Sat,20 Feb 2021 13:17:37 GMT 
      keep-alive: timeout=60 
      transfer-encoding: chunked                  

response body 
    
      [
        {
          "runwayId": "string",
          "airportId": "string",
          "airportIdentity": "string"
        }
      ]
      
      
**Request**

`GET /`

    GET GET "http://localhost:8080/" -H  "accept: */*"

**Response**

response header

      connection: keep-alive 
      content-length: 46 
      content-type: text/plain;charset=UTF-8 
      date: Sat,20 Feb 2021 13:21:19 GMT 
      keep-alive: timeout=60                   

response body 

      string
      

### API Specification 
Airport deatils API details can be found [here](http://localhost:8080/swagger-ui/index.html?configUrl=/com.application.airportdetails.api/swagger-config) after running the application.