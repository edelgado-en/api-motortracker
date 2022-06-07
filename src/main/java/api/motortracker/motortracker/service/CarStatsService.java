package api.motortracker.motortracker.service;

import api.motortracker.motortracker.model.Car;
import api.motortracker.motortracker.model.CarStats;
import api.motortracker.motortracker.repository.CarRepository;
import api.motortracker.motortracker.repository.CarStatsRepository;
import api.motortracker.motortracker.resource.CarResource;
import api.motortracker.motortracker.resource.CarStatsResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CarStatsService {

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Autowired
    private CarStatsRepository carStatsRepository;

    @Autowired
    private CarRepository carRepository;

    public List<CarStatsResource> findCarStats(String plate) {

        //validate
        Car car = carRepository.findByPlate(plate);

        if (car == null) {
            throw new IllegalArgumentException("Invalid plate");
        }

        List<CarStats> carStatsList = carStatsRepository.findByCar(car);

        List<CarStatsResource> carStatsResources = new ArrayList<>();

        for (CarStats stat : carStatsList) {
            CarStatsResource carStatsResource = new CarStatsResource();
            carStatsResource.setId(stat.getId());
            carStatsResource.setTimeStamp(simpleDateFormat.format(stat.getTimeStamp()));
            carStatsResource.setAirTemp(stat.getAirTemp());
            carStatsResource.setCoolant(stat.getCoolant());
            carStatsResource.setBoostPressure(stat.getBoostPressure());
            carStatsResource.setOilPressure(stat.getOilPressure());
            carStatsResource.setOilTemp(stat.getOilTemp());

            carStatsResources.add(carStatsResource);
        }

        return carStatsResources;
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
