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
public class AppUserResource implements Serializable {

    private static final long serialVersionUID = 1L;

    private String displayName;

    private String email;

    private String uid;
}
