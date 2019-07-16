package edu.uapa.web.app.gamify.controllers.company;

import edu.uapa.web.app.gamify.services.company.PositionService;
import edu.uapa.web.app.gamify.utils.Urls;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = Urls.APP_POSITION)
public class PositionController {

    private final PositionService service;

    public PositionController(PositionService service) {
        this.service = service;
    }
}
