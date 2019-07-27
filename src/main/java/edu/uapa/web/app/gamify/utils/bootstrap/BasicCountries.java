package edu.uapa.web.app.gamify.utils.bootstrap;

import edu.uapa.web.app.gamify.domains.locations.Country;
import edu.uapa.web.app.gamify.models.interfaces.BootStrapInsert;
import edu.uapa.web.app.gamify.services.address.CountryService;
import edu.utesa.lib.models.enums.location.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
class BasicCountries implements BootStrapInsert {

    private final CountryService countryService;

    @Autowired
    public BasicCountries(CountryService countryService) {
        this.countryService = countryService;
    }

    @Override
    public void insert() {
        createAmericanCountries();
        createEuropeanCountries();
        createOceanicCountries();
        createAfricanCountries();
        createAsianCountries();
    }

    private void createAmericanCountries() {
//        Arrays.stream(AmericanCountries.values()).forEach(countries -> countryService.findByName(AmericanCountries.getCaption(countries)).orElseGet(() -> countryService.bootStrap(new Country(AmericanCountries.getCaption(countries)))));
    }

    private void createEuropeanCountries() {
//        Arrays.stream(EuropeanCountries.values()).forEach(countries -> countryService.findByName(EuropeanCountries.getCaption(countries)).orElseGet(() -> countryService.bootStrap(new Country(EuropeanCountries.getCaption(countries)))));
    }

    private void createOceanicCountries() {
//        Arrays.stream(OceanicCountries.values()).forEach(countries -> countryService.findByName(OceanicCountries.getCaption(countries)).orElseGet(() -> countryService.bootStrap(new Country(OceanicCountries.getCaption(countries)))));
    }

    private void createAfricanCountries() {
//        Arrays.stream(AfricanCountries.values()).forEach(countries -> countryService.findByName(AfricanCountries.getCaption(countries)).orElseGet(() -> countryService.bootStrap(new Country(AfricanCountries.getCaption(countries)))));
    }

    private void createAsianCountries() {
//        Arrays.stream(AsianCountries.values()).forEachOrdered(countries -> countryService.findByName(AsianCountries.getCaption(countries)).orElseGet(() -> countryService.bootStrap(new Country(AsianCountries.getCaption(countries)))));
    }
}