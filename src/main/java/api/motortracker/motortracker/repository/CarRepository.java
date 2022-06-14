package api.motortracker.motortracker.repository;

import api.motortracker.motortracker.model.Car;
import org.springframework.data.repository.CrudRepository;

public interface CarRepository extends CrudRepository<Car, Long> {

    Car findByPlate(String plate);
}
