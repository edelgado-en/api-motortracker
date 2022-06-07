package api.motortracker.motortracker.endpoint;

import api.motortracker.motortracker.resource.CarResource;
import api.motortracker.motortracker.resource.CarStatsResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = CarEndpoint.BASE_ENDPOINT, produces = MediaType.APPLICATION_JSON_VALUE)
public class CarEndpoint {

    public static final String BASE_ENDPOINT = "/car";

    private CarEndpointHelper helper;

    public CarEndpoint(CarEndpointHelper aHelper) {
        this.helper = aHelper;
    }

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CarResource> registerCar(@RequestBody CarResource carResource) {
        return helper.registerCar(carResource);
    }

    @GetMapping()
    public ResponseEntity<List<CarResource>> findCars() {
        return helper.findCars();
    }

/*    @PostMapping(value = "/edit", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CarResource> editCar(@RequestBody CarResource carResource) {
        return helper.editCar(carResource);
    }*/

    //POST so that we can pass a search object
    @PostMapping(value = "/stats/search", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CarStatsResource>> findCarStats(@RequestBody CarResource carResource) {
        return helper.findCarStats(carResource);
    }

    @PostMapping(value = "/stats", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CarStatsResource> saveCarStats(@RequestBody CarStatsResource carStatsResource) {
        return helper.saveCarStats(carStatsResource);
    }
}