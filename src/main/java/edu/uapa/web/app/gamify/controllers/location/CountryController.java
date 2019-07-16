package edu.uapa.web.app.gamify.controllers.location;

import edu.uapa.web.app.gamify.services.address.CountryService;
import edu.uapa.web.app.gamify.utils.Urls;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = Urls.APP_COUNTRY)
public class CountryController {

    private final CountryService service;

    public CountryController(CountryService service) {
        this.service = service;
    }
}
