package api.motortracker.motortracker.repository;

import api.motortracker.motortracker.model.Tracker;
import org.springframework.data.repository.CrudRepository;

public interface TrackerRepository extends CrudRepository<Tracker, Integer> {

    Tracker findBySerialNumber(String serialNumber);
}
