package api.motortracker.motortracker.endpoint;

import api.motortracker.motortracker.resource.AppUserResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = AppUserEndpoint.BASE_ENDPOINT, produces = MediaType.APPLICATION_JSON_VALUE)
public class AppUserEndpoint {

    public static final String BASE_ENDPOINT = "/user";

    private AppUserEndpointHelper helper;

    public AppUserEndpoint(AppUserEndpointHelper aHelper) {
        this.helper = aHelper;
    }

    @PostMapping(value = "/signin", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AppUserResource> registerCar(@RequestBody AppUserResource appUserResource) {
        return helper.signin(appUserResource);
    }

}
