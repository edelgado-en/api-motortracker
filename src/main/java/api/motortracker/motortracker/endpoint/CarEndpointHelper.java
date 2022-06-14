package api.motortracker.motortracker.endpoint;

import api.motortracker.motortracker.resource.CarResource;
import api.motortracker.motortracker.resource.CarStatsResource;
import api.motortracker.motortracker.service.CarService;
import api.motortracker.motortracker.service.CarStatsService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarEndpointHelper {

    private CarService carService;
    private CarStatsService carStatsService;

    public CarEndpointHelper(CarService aCarService, CarStatsService statsService) {
        this.carService = aCarService;
        this.carStatsService = statsService;
    }

    public ResponseEntity<CarResource> registerCar(CarResource carResource) {
        CarResource result = carService.registerCar(carResource, "SaH1MjKpfVUD4EmmiGS2tzWnvzD3");

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    public ResponseEntity<CarResource> editCar(CarResource carResource) {
        CarResource result = carService.editCar(carResource, "SaH1MjKpfVUD4EmmiGS2tzWnvzD3");

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    public ResponseEntity<List<CarResource>> findCars() {

        List<CarResource> carResources = carService.findCars();

        return new ResponseEntity<>(carResources, HttpStatus.OK);
    }

    public ResponseEntity<Page<CarStatsResource>> findCarStats(CarResource carResource, Pageable pageable) {

        Page<CarStatsResource> carStatsResources = carStatsService.findCarStats(carResource.getPlate(), pageable);

        return new ResponseEntity<>(carStatsResources, HttpStatus.OK);
    }

    public ResponseEntity<CarStatsResource> saveCarStats(CarStatsResource carStatsResource) {

        CarStatsResource savedCarStatsResource = carStatsService.saveCarStats(carStatsResource);

        return new ResponseEntity<>(savedCarStatsResource, HttpStatus.OK);
    }

    /*boolean isUserAuthenticated = cognitoClientSService.isUserAuthenticated(userName, accessToken);

        if (!isUserAuthenticated) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }*/
}
