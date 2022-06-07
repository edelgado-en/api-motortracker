package api.motortracker.motortracker.repository;

import api.motortracker.motortracker.model.Car;
import org.springframework.data.repository.CrudRepository;

public interface CarRepository extends CrudRepository<Car, Integer> {

    Car findByPlate(String plate);
}
