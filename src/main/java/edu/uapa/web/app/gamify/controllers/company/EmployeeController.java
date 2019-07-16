package edu.uapa.web.app.gamify.controllers.company;

import edu.uapa.web.app.gamify.services.company.EmployeeService;
import edu.uapa.web.app.gamify.utils.Urls;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = Urls.APP_EMPLOYEE)
public class EmployeeController {

    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }
}
