package org.ama.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.ama.model.Address;
import org.ama.model.Restaurant;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RestaurantService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();


    public List<Restaurant> fetchRestaurants(String postCode){
        String url = "https://uk.api.just-eat.io/discovery/uk/restaurants/enriched/bypostcode/" + postCode;

        //gets URL, makes GET request and converts the response body into the specified type. The result is saved in the response variable
        String response = restTemplate.getForObject(url, String.class);
        List<Restaurant> result = new ArrayList<>();

        try {
            JsonNode root = objectMapper.readTree(response);
            JsonNode restaurants = root.path("restaurants");

            for(JsonNode restaurant : restaurants) {
                //get name
                String name = restaurant.path("name").asText();

                //get cuisines
                List<String> cuisines = new ArrayList<>();
                for (JsonNode cuisine : restaurant.path("cuisines")) {
                    cuisines.add((cuisine.get("name").asText()));
                }

                //get rating
                double rating = restaurant.path("rating").path("starRating").asDouble(0.0);

                //get address
                JsonNode addr = restaurant.path("address");
                String city = addr.path("city").asText();
                String firstLine = addr.path("firstLine").asText();
                String postalCode = addr.path("postalCode").asText();

                Address address = new Address(city, firstLine, postalCode);
                result.add(new Restaurant(name, cuisines, rating, address));
            }
        } catch (Exception e){
                throw new RuntimeException("Failed to parse restaurant data", e);
        }

        return result.stream().limit(10).toList();

    }

}
