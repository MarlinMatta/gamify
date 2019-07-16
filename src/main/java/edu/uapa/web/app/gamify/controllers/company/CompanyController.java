package edu.uapa.web.app.gamify.controllers.company;

import edu.uapa.web.app.gamify.services.company.CompanyService;
import edu.uapa.web.app.gamify.utils.Urls;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = Urls.APP_COMPANY)
public class CompanyController {

    private final CompanyService service;

    public CompanyController(CompanyService service) {
        this.service = service;
    }
}
