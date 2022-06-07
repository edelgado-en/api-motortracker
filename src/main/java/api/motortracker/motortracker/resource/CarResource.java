package api.motortracker.motortracker.resource;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CarResource implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

    private String plate;

    private String trackerSerialNumber;
}
