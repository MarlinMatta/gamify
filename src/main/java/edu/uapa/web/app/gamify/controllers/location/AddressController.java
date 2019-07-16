package edu.uapa.web.app.gamify.controllers.location;

import edu.uapa.web.app.gamify.services.address.AddressService;
import edu.uapa.web.app.gamify.utils.Urls;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = Urls.APP_ADDRESS)
public class AddressController {

    private final AddressService service;

    public AddressController(AddressService service) {
        this.service = service;
    }
}
