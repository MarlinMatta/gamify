package edu.uapa.web.app.gamify.utils.bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

public class BootStrap implements CommandLineRunner {

    @Autowired
    private BasicCountries basicCountries;
    @Autowired
    private BasicParameter basicParameter;
    @Autowired
    private BasicPermission basicPermission;
    @Autowired
    private BasicPermissionGroup basicPermissionGroup;
    @Autowired
    private BasicUser basicUser;

    private void productive() {
        basicCountries.insert();
        basicParameter.insert();
        basicPermission.insert();
        basicPermissionGroup.insert();
        basicUser.insert();
    }

    private void development() {
        productive();
    }

    @Override
    public void run(String... args) {
        development();
    }
}
