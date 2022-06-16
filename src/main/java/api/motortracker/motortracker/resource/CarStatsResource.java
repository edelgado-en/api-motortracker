package api.motortracker.motortracker.resource;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CarStatsResource implements Serializable {

    private static final long serialVersionUID = 1L;

    private long id;

    private String timeStamp;

    private String plate;

    private String trackerSerialNumber;

    //coolant temp normal < 220
    //warning  220-240
    //danger > 240
    private int coolant; //coolantTemp

    //same as coolantTemp
    private int oilTemp;

    //oil pressure
    //normal > 19
    //danger < 19
    private int oilPressure;

    //Remove this one. No such thing
    private int airTemp;

    //boost normal <= 0
    //danger > 0
    private double boostPressure;

    //TODO: add to schema
    //fuel pressure normal >= 35
    //warning 35 to 30
    //danger < 30
    private int fuelPressure;

    //AFR(air fuel ratio)
    //afr normal between 12 - 16
    //danger outside that range
    private double airFuelRatio;

    //search by one value at a time in the UI

}
