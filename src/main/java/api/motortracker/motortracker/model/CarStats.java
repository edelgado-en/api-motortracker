package api.motortracker.motortracker.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "CAR_STATS")
public class CarStats implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "TIMESTAMP", nullable = false)
    private Date timeStamp;

    @JoinColumn(name = "CAR", referencedColumnName = "ID")
    @ManyToOne
    private Car car;

    @Column(name = "COOLANT")
    private int coolant;

    @Column(name = "OILTEMP")
    private int oilTemp;

    @Column(name = "AIRTEMP")
    private int airTemp;

    @Column(name = "BOOSTPRESSURE")
    private double boostPressure;

    @Column(name = "OILPRESSURE")
    private int oilPressure;

}
