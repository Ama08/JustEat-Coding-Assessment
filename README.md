# Just Eat Restaurant Search API

This is a Spring Boot application that fetches restautant data from Just Eat's public API and displays key details for a given postcode.

---

## How to Build, Compile & Run
### Prerequisites 
- Java 17
- Maven 3.X
- Postman (or any other HTTP client)

### Build the project 
Once you have cloned the repository, it is time to build the project:
```bash
mvn clean install
mvn spring-boot:run
```

### Run the application
By default the server runs on ``` http://localhost:8080 ```

Endpoint:
```bash
GET /restaurants/{postcode}
```

#### Open Postman or any other HTTP client
Create a new **GET** request and use the url ``` http://localhost:8080/restaurants/{postcode} ``` with the desired postcodet to get the desired data.
#### Sample response
```bash
 {
        "name": "Burger King Basildon",
        "cuisines": [
            "Burgers",
            "Chicken",
            "Deals",
            "Freebies"
        ],
        "rating": 3.6,
        "address": {
            "city": "Basildon",
            "firstLine": "Pipps Hill Retail Park, Miles Gray Rd",
            "postalCode": "SS14 3AF"
        }
    }
```
Only 10 restaurants will be shown.

The application should only display the below data about the restaurants:
- Name
- Cuisines
- Rating (as a number)
- Address

---
## Assumptions made
- Assume that rating is taken from rating.starRating
- Assume that address inclused city, firstLine and postalCode
- Assume test cases needed. All good coding needs tests to validate that the funtions are working
- Assume rating as a number could mean either an int or double. I used double as that was what was shown in the API

---
## Improvements and future work
- Add input validation for postcodes
- Add web-based UI
- Add other types of filtering
