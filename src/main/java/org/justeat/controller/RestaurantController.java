package org.justeat.controller;

import org.justeat.model.Restaurant;
import org.justeat.service.RestaurantService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

    private final RestaurantService restaurantService;

    private RestaurantController(RestaurantService restaurantService){
        this.restaurantService = restaurantService;
    }

    @GetMapping("/{postcode}")
    public ResponseEntity<List<Restaurant>> getRestaurants(@PathVariable String postcode){
        try {
            List<Restaurant> restaurants = restaurantService.fetchRestaurants(postcode);
            return ResponseEntity.ok(restaurants);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
