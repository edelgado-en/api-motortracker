package api.motortracker.motortracker.service;

import api.motortracker.motortracker.model.Car;
import api.motortracker.motortracker.model.CarStats;
import api.motortracker.motortracker.repository.CarRepository;
import api.motortracker.motortracker.repository.CarStatsRepository;
import api.motortracker.motortracker.resource.CarStatsResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarStatsService {

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Autowired
    private CarStatsRepository carStatsRepository;

    @Autowired
    private CarRepository carRepository;

    /**
     * Find the car statistics for the provided car using the provided pageable.
     *
     * @param plate the car plate used to fetch and validate car
     * @param pageable th pageable coming from the client
     * @return the pageable list of CarStatsResource
     */
    public Page<CarStatsResource> findCarStats(String plate, Pageable pageable) {
        Car car = carRepository.findByPlate(plate);

        if (car == null) {
            throw new IllegalArgumentException("Invalid plate");
        }

        Page<CarStats> carStatsList = carStatsRepository.findByCar(car, pageable);

        List<CarStatsResource> carStatsResources =
                carStatsList.stream()
                            .map(stat -> CarStatsResource
                                        .builder()
                                        .id(stat.getId())
                                        .timeStamp(simpleDateFormat.format(stat.getTimeStamp()))
                                        .airTemp(stat.getAirTemp())
                                        .coolant(stat.getCoolant())
                                        .boostPressure(stat.getBoostPressure())
                                        .oilPressure(stat.getOilPressure())
                                        .oilTemp(stat.getOilTemp())
                                        .build())
                            .collect(Collectors.toList());

        return new PageImpl(carStatsResources, pageable, carStatsList.getTotalElements());
    }


    public CarStatsResource saveCarStats(CarStatsResource carStatsResource) {

        //validate plate
        Car car = carRepository.findByPlate(carStatsResource.getPlate());

        if (car == null) {
            throw new IllegalArgumentException("Invalid plate");
        }

        //validate tracker id matches
        if (!carStatsResource.getTrackerSerialNumber().equals(car.getTracker().getSerialNumber())) {
            throw new IllegalArgumentException("Tracker serial number mismatch");
        }

        CarStats newCarStats = new CarStats();
        newCarStats.setCar(car);
        newCarStats.setTimeStamp(new Date());
        newCarStats.setCoolant(carStatsResource.getCoolant());
        newCarStats.setAirTemp(carStatsResource.getAirTemp());
        newCarStats.setBoostPressure(carStatsResource.getBoostPressure());
        newCarStats.setOilPressure(carStatsResource.getOilPressure());
        newCarStats.setOilTemp(carStatsResource.getOilTemp());

        CarStats savedCarStats = carStatsRepository.save(newCarStats);

        CarStatsResource savedResource = new CarStatsResource();
        savedResource.setId(savedCarStats.getId());
        savedResource.setTimeStamp(simpleDateFormat.format(savedCarStats.getTimeStamp()));
        savedResource.setCoolant(savedCarStats.getCoolant());
        savedResource.setAirTemp(savedCarStats.getAirTemp());
        savedResource.setBoostPressure(savedCarStats.getBoostPressure());
        savedResource.setOilPressure(savedCarStats.getOilPressure());
        savedResource.setOilTemp(savedCarStats.getOilTemp());

        return savedResource;

    }
}
