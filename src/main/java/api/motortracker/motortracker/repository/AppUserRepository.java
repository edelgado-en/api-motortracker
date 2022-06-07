package api.motortracker.motortracker.repository;

import api.motortracker.motortracker.model.AppUser;
import api.motortracker.motortracker.model.Car;
import org.springframework.data.repository.CrudRepository;

public interface AppUserRepository extends CrudRepository<AppUser, Integer> {

    AppUser findByUid(String userName);
}
