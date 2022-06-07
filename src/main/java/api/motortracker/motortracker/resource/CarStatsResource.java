package api.motortracker.motortracker.resource;

import lombok.Data;

import java.io.Serializable;

@Data
public class CarStatsResource implements Serializable {

    private static final long serialVersionUID = 1L;

    private long id;

    private String timeStamp;

    private String plate;

    private String trackerSerialNumber;

    private int coolant;

    private int oilTemp;

    private int airTemp;

    private double boostPressure;

    private int oilPressure;
}
