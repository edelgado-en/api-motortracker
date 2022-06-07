package api.motortracker.motortracker.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "CAR")
public class Car implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "PLATE", nullable = false)
    private String plate;

    @JoinColumn(name = "TRACKER", referencedColumnName = "ID")
    @ManyToOne
    private Tracker tracker;

    @JoinColumn(name = "APP_USER", referencedColumnName = "ID")
    @ManyToOne
    private AppUser appUser;

}
