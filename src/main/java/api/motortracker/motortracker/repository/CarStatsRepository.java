package api.motortracker.motortracker.repository;

import api.motortracker.motortracker.model.Car;
import api.motortracker.motortracker.model.CarStats;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface CarStatsRepository extends PagingAndSortingRepository<CarStats, Long> {

    Page<CarStats> findByCar(Car car, Pageable pageable);
}
