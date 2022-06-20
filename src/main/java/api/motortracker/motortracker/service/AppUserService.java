package api.motortracker.motortracker.service;

import api.motortracker.motortracker.model.AppUser;
import api.motortracker.motortracker.repository.AppUserRepository;
import api.motortracker.motortracker.resource.AppUserResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppUserService {

    Logger log = LoggerFactory.getLogger(AppUserService.class);

    @Autowired
    private AppUserRepository appUserRepository;

    public AppUserResource signin(AppUserResource appUserResource) {
        AppUser appUser = appUserRepository.findByUid(appUserResource.getUid());

        boolean isFirstTime = false;

        if (appUser == null) {
            appUser = new AppUser();
            appUser.setDisplayName(appUserResource.getDisplayName());
            appUser.setEmail(appUserResource.getEmail());
            appUser.setUid(appUserResource.getUid());

            appUserRepository.save(appUser);

            isFirstTime = true;

            log.info("New user created: {} with uid: {}",
                    appUserResource.getDisplayName(),
                    appUserResource.getUid());
        }

        return AppUserResource.builder()
                .displayName(appUser.getDisplayName())
                .email(appUser.getEmail())
                .uid(appUser.getUid())
                .isFirstTime(isFirstTime)
                .build();
    }
}
