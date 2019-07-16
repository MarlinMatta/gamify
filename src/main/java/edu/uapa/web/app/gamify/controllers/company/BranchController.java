package edu.uapa.web.app.gamify.controllers.company;

import edu.uapa.web.app.gamify.services.company.BranchService;
import edu.uapa.web.app.gamify.utils.Urls;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = Urls.APP_BRANCH)
public class BranchController {

    private final BranchService service;

    public BranchController(BranchService service) {
        this.service = service;
    }
}
