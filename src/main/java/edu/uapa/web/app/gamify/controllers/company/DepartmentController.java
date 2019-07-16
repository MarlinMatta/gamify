package edu.uapa.web.app.gamify.controllers.company;

import edu.uapa.web.app.gamify.services.company.DepartmentService;
import edu.uapa.web.app.gamify.utils.Urls;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = Urls.APP_DEPARTMENT)
public class DepartmentController {

    private final DepartmentService service;

    public DepartmentController(DepartmentService service) {
        this.service = service;
    }
}
