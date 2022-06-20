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
public class CarStatThresholdResource implements Serializable {

    private static final long serialVersionUID = 1L;

    private boolean normal;
    private boolean warning;
    private boolean danger;
}
