# Just Eat Restaurant Search API

This is a Spring Boot application that fetches restaurant data from Just Eat's public API and displays key details for a given postcode.

---

## How to Build, Compile & Run
### Prerequisites 
- Java 17
- Maven 3.X
- Postman (or any other HTTP client)

> You may be prompted to download ```corretto-17.0.14``` once you open the application , please download this in order for the application to run

### Build the project 
Clone the repository:
```bash
git clone https://github.com/Ama08/JustEat-Coding-Assessment.git
cd JustEat-Coding-Assessment
```
Once you have cloned the repository and opened the application up, it is time to build the project (in terminal):
```bash
mvn clean install
```

### Run the application
In terminal run:
```bash
mvn spring-boot:run
```
## Postman Collection
By default, the server runs on ``` http://localhost:8080 ```

Endpoint:
```bash
GET /restaurants/{postcode}
```
You can test the API using the included Postman collection:

**File**: `postman/just-eat-api-postman_collection.json`

### Steps:
1. Open Postman → Click **Import**
2. Select the JSON file from this repo
3. Run the request:
```GET``` with the url ``` http://localhost:8080/restaurants/SS14 3AF ```

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

## Displaying Restaurant data
#### The application will display the below data about the restaurants:
- Name
- Cuisines
- Rating (as a number)
- Address

Only the first 10 restaurants will be shown.

---
## Assumptions made
- Assumed that rating is taken from rating.starRating
- Assumed that address includes city, firstLine and postalCode
- Assumed test cases needed because testing is important for reliable coding
- Assumed rating as a number could mean either an int or double. I used double as that was what was shown in the API
- Assumed that the API accepts either outcodes (e.g. ```SS14```) or full UK postcodes (e.g. ```SS14 3AF```). Both were tested, and they work for the new API endpoint.

---
## Improvements and future work
- Add custome exceptions
- Add web-based UI
- Add other types of filtering (eg by cuisine and rating) as it gives more flexibility
- Error handling which returns helpful HTTP error codes. This would improve user experience
