package api.motortracker.motortracker.service;

import api.motortracker.motortracker.model.AppUser;
import api.motortracker.motortracker.repository.AppUserRepository;
import api.motortracker.motortracker.resource.AppUserResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppUserService {

    @Autowired
    private AppUserRepository appUserRepository;

    public AppUserResource signin(AppUserResource appUserResource) {
        AppUser appUser = appUserRepository.findByUid(appUserResource.getUid());

        if (appUser == null) {
            appUser = new AppUser();
            appUser.setDisplayName(appUserResource.getDisplayName());
            appUser.setEmail(appUserResource.getEmail());
            appUser.setUid(appUserResource.getUid());

            appUserRepository.save(appUser);
        }

        return AppUserResource.builder()
                .displayName(appUser.getDisplayName())
                .email(appUser.getEmail())
                .uid(appUser.getUid())
                .isFirstTime(false)// TODO do this for Zach
                .build();
    }
}
