package api.motortracker.motortracker.endpoint;

import api.motortracker.motortracker.resource.CarResource;
import api.motortracker.motortracker.resource.CarStatsResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = CarEndpoint.BASE_ENDPOINT, produces = MediaType.APPLICATION_JSON_VALUE)
public class CarEndpoint {

    Logger logger = LoggerFactory.getLogger(CarEndpoint.class);

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

    @PostMapping(value = "/edit", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CarResource> editCar(@RequestBody CarResource carResource) {
        return helper.editCar(carResource);
    }

    /**
     * Endpoint used to search for car statistics for the provided search criteria.
     * @param carResource
     * @return the list of car statistics resources matching the provided criteria.
     */
    @PostMapping(value = "/stats/search", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<CarStatsResource>> findCarStats(@RequestBody CarResource carResource,
                                                               Pageable pageable) {

        logger.info("hello");

        return helper.findCarStats(carResource, pageable);
    }

    /**
     * Endpoint used by all clients (webapp, mobile app, etc) to post statistics for a car.
     * @param carStatsResource
     * @return the response entity with the saved car statistics resource.
     */
    @PostMapping(value = "/stats", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CarStatsResource> saveCarStats(@RequestBody CarStatsResource carStatsResource) {
        return helper.saveCarStats(carStatsResource);
    }
}