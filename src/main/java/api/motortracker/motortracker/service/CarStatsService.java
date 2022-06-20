package api.motortracker.motortracker.service;

import api.motortracker.motortracker.model.Car;
import api.motortracker.motortracker.model.CarStats;
import api.motortracker.motortracker.repository.CarRepository;
import api.motortracker.motortracker.repository.CarStatsRepository;
import api.motortracker.motortracker.resource.CarStatThresholdResource;
import api.motortracker.motortracker.resource.CarStatsResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    Logger log = LoggerFactory.getLogger(CarStatsService.class);

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
            log.error("Plate: {} not found", plate);
            throw new IllegalArgumentException("Invalid plate");
        }

        Page<CarStats> carStatsList = carStatsRepository.findByCar(car, pageable);

        List<CarStatsResource> carStatsResources =
                carStatsList.stream()
                            .map(stat -> buildStatsResource(stat))
                            .collect(Collectors.toList());

        return new PageImpl(carStatsResources, pageable, carStatsList.getTotalElements());
    }

    public CarStatsResource saveCarStats(CarStatsResource carStatsResource) {
        Car car = carRepository.findByPlate(carStatsResource.getPlate());

        if (car == null) {
            log.error("Plate: {} not found", carStatsResource.getPlate());

            throw new IllegalArgumentException("Invalid plate");
        }

        //validate tracker id matches
        if (!carStatsResource.getTrackerSerialNumber().equals(car.getTracker().getSerialNumber())) {
            log.error("Provided tracker: {} does not match existing tracker: {}",
                        carStatsResource.getTrackerSerialNumber(),
                        car.getTracker().getSerialNumber());

            throw new IllegalArgumentException("Tracker serial number mismatch");
        }

        //TODO: you are missing fuelPressure and airFuelRatio

        CarStats newCarStats = new CarStats();
        newCarStats.setCar(car);
        newCarStats.setTimeStamp(new Date());
        newCarStats.setCoolant(carStatsResource.getCoolant());
        newCarStats.setAirTemp(carStatsResource.getAirTemp());
        newCarStats.setBoostPressure(carStatsResource.getBoostPressure());
        newCarStats.setOilPressure(carStatsResource.getOilPressure());
        newCarStats.setOilTemp(carStatsResource.getOilTemp());

        CarStats savedCarStats = carStatsRepository.save(newCarStats);

        return CarStatsResource
                .builder()
                .id(savedCarStats.getId())
                .timeStamp(simpleDateFormat.format(savedCarStats.getTimeStamp()))
                .coolant(savedCarStats.getCoolant())
                .airTemp(savedCarStats.getAirTemp())
                .boostPressure(savedCarStats.getBoostPressure())
                .oilPressure(savedCarStats.getOilPressure())
                .oilTemp(savedCarStats.getOilTemp())
                .build();

    }

    CarStatsResource buildStatsResource(CarStats stat) {

        int coolant = stat.getCoolant();
        int oilTemp = stat.getOilTemp();
        int oilPressure = stat.getOilPressure();
        double boostPressure = stat.getBoostPressure();

        //TODO: create a utils class ThresholdCalculator
        //  isCoolantNormal
        //  isCoolantWarning
        //  isCoolantDanger
        //This way this service does not have to know how to calculate these values
        CarStatThresholdResource coolantThreshold = CarStatThresholdResource
                                                        .builder()
                                                        .normal(coolant < 220)
                                                        .warning(coolant >= 220 && coolant <= 240)
                                                        .danger(coolant > 240)
                                                        .build();

        CarStatThresholdResource oilTempThreshold = CarStatThresholdResource
                                                        .builder()
                                                        .normal(oilTemp < 220)
                                                        .warning(oilTemp >= 220 && oilTemp <= 240)
                                                        .danger(oilTemp > 240)
                                                        .build();

        CarStatThresholdResource oilPressureThreshold = CarStatThresholdResource
                                                        .builder()
                                                        .normal(oilPressure >= 19)
                                                        .danger(oilPressure < 19)
                                                        .build();

        CarStatThresholdResource boostPressureThreshold = CarStatThresholdResource
                                                        .builder()
                                                        .normal(boostPressure <= 0)
                                                        .danger(boostPressure > 0)
                                                        .build();


        //TODO: you are missing fuelPressure and airFuelRatio
        return CarStatsResource
                .builder()
                .id(stat.getId())
                .timeStamp(simpleDateFormat.format(stat.getTimeStamp()))
                .airTemp(stat.getAirTemp())
                .coolant(coolant)
                .coolantThreshold(coolantThreshold)
                .boostPressure(boostPressure)
                .boostPressureThreshold(boostPressureThreshold)
                .oilPressure(oilPressure)
                .oilPressureThreshold(oilPressureThreshold)
                .oilTemp(oilTemp)
                .oilTempThreshold(oilTempThreshold)
                .build();
    }
}
