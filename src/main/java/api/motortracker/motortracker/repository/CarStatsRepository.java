package api.motortracker.motortracker.repository;

import api.motortracker.motortracker.model.Car;
import api.motortracker.motortracker.model.CarStats;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CarStatsRepository extends CrudRepository<CarStats, Long> {

    List<CarStats> findByCar(Car car);
}
