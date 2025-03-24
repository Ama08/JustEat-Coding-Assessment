import org.ama.controller.RestaurantController;
import org.ama.model.Address;
import org.ama.model.Restaurant;
import org.ama.service.RestaurantService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.stream.IntStream;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RestaurantController.class)
public class RestaurantControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RestaurantService restaurantService;

    @Test
    void test_shouldReturnMax10Restaurants()throws Exception{
        List<Restaurant> elevenRestaurants = IntStream.rangeClosed(1,11).mapToObj(i -> new Restaurant(
                "Test Restayrant " + i,
                List.of("Cuisine " + i),
                3.6 + (i * 0.1),
                new Address("Test Address" + 1, "City " + i, "CT1 2EH")

        )).toList();

        when (restaurantService.fetchRestaurants("CT1 2EH")).thenReturn(elevenRestaurants.subList(0,10));

        mockMvc.perform(get("/discovery/uk/restaurants/enriched/bypostcode/CT1 2EH"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(10));
    }

    @Test
    void test_shouldReturnReastaurantWithAllFields() throws Exception {
        Restaurant restaurants = new Restaurant("Test Restaurant", List.of("Chicken"),4.6, new Address("London", "33 Test Drive", "CT1 2EH"));

        when(restaurantService.fetchRestaurants("CT1 2EH")).thenReturn(List.of(restaurants));

        mockMvc.perform(get("/discovery/uk/restaurants/enriched/bypostcode/CT1 2EH"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Test Restaurant"))
                .andExpect(jsonPath("$[0].cuisines").isArray())
                .andExpect(jsonPath("$[0].starRating").value(4.5))
                .andExpect(jsonPath("$[0].address.postalCode").value("CT1 2EH"));
    }

}
