package api.motortracker.motortracker.service;

import api.motortracker.motortracker.model.AppUser;
import api.motortracker.motortracker.model.Car;
import api.motortracker.motortracker.model.Tracker;
import api.motortracker.motortracker.repository.AppUserRepository;
import api.motortracker.motortracker.repository.CarRepository;
import api.motortracker.motortracker.repository.TrackerRepository;
import api.motortracker.motortracker.resource.CarResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarService {

    Logger log = LoggerFactory.getLogger(CarService.class);

    private CarRepository carRepository;

    private AppUserRepository appUserRepository;

    private TrackerRepository trackerRepository;

    public CarService(CarRepository carRepository, AppUserRepository appUserRepository, TrackerRepository trackerRepository) {
        this.carRepository = carRepository;
        this.appUserRepository = appUserRepository;
        this.trackerRepository = trackerRepository;
    }

    /**
     * Registers a car with the provided car resource and user id.
     *
     * @param carResource the car resource containing values for the car to be registered
     * @param uid the universal user id coming from Google auth.
     *            We use this id to validate user and map other entities
     * @return the car resource of the newly created car
     */
    public CarResource registerCar(CarResource carResource, String uid) {
        Tracker tracker = validateTracker(carResource.getTrackerSerialNumber());
        AppUser appUser = validateUser(uid);
        validateCar(carResource.getPlate());

        Car newCar = buildCar(carResource, appUser, tracker);

        carRepository.save(newCar);

        return CarResource.builder()
                .id(newCar.getId())
                .name(newCar.getName())
                .plate(newCar.getPlate())
                .trackerSerialNumber(newCar.getTracker().getSerialNumber())
                .build();
    }

    public CarResource editCar(CarResource carResource, String uid) {
        Car car = carRepository.findById(carResource.getId())
                               .orElseThrow(() ->new IllegalArgumentException("Invalid car id"));

        //get current tracker
        Tracker tracker = trackerRepository.findById(car.getTracker().getId())
                         .orElseThrow(() -> new IllegalArgumentException("Invalid Tracker"));

        validateUser(uid);

        tracker.setSerialNumber(carResource.getTrackerSerialNumber());

        car.setName(carResource.getName());
        car.setTracker(tracker);
        car.setPlate(carResource.getPlate());

        carRepository.save(car);

        return CarResource.builder()
                .id(car.getId())
                .name(car.getName())
                .plate(car.getPlate())
                .trackerSerialNumber(car.getTracker().getSerialNumber())
                .build();
    }

    Tracker validateTracker(String serialNumber) {
        Tracker tracker = trackerRepository.findBySerialNumber(serialNumber);

        if (tracker == null) {
            log.error("Tracker: {} not found in the database", serialNumber);
            throw new IllegalArgumentException("Invalid tracker serial number");
        }

        return tracker;
    }

    AppUser validateUser(String uid) {
        AppUser appUser = appUserRepository.findByUid(uid);

        if (appUser == null) {
            log.error("UID: {} not found in the database", uid);
            throw new IllegalArgumentException("Invalid user");
        }

        return appUser;
    }

    void validateCar(String plate) {
        Car car = carRepository.findByPlate(plate);

        if (car != null) {
            log.error("Plate: {} not found in the database", plate);
            throw new IllegalArgumentException("Car plate already registered");
        }
    }

    //TODO: add a builder annotation to the entity.
    Car buildCar(CarResource carResource, AppUser appUser, Tracker tracker) {
        Car newCar = new Car();
        newCar.setAppUser(appUser);
        newCar.setName(carResource.getName());
        newCar.setPlate(carResource.getPlate());
        newCar.setTracker(tracker);

        return newCar;
    }

    /**
     * Gets all the cars from the database.
     *
     * @return the list of car resources
     */
    public List<CarResource> findCars() {
        List<Car> cars = (List<Car>) carRepository.findAll();

        return cars.stream().map(c -> CarResource.builder()
                                    .id(c.getId())
                                    .name(c.getName())
                                    .plate(c.getPlate())
                                    .trackerSerialNumber(c.getTracker().getSerialNumber())
                                    .build())
                            .collect(Collectors.toList());
    }
}
