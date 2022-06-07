package api.motortracker.motortracker.endpoint;

import api.motortracker.motortracker.resource.AppUserResource;
import api.motortracker.motortracker.service.AppUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AppUserEndpointHelper {

    private AppUserService appUserService;

    public AppUserEndpointHelper(AppUserService service) {
        this.appUserService = service;
    }

    public ResponseEntity<AppUserResource> signin(AppUserResource appUserResource) {
        AppUserResource result = appUserService.signin(appUserResource);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
