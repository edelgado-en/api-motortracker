package api.motortracker.motortracker.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "APP_USER")
public class AppUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;

    @Column(name = "DISPLAYNAME", nullable = false)
    private String displayName;

    @Column(name = "EMAIL", nullable = false)
    private String email;

    @Column(name = "UID", nullable = false)
    private String uid;

}
