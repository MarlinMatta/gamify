package edu.uapa.web.app.gamify.controllers;

import edu.uapa.web.app.gamify.utils.Urls;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = Urls.APP)
public class UpController {

    @RequestMapping(value = Urls.UP, method = RequestMethod.GET)
    public String up() {
        return "OK";
    }
}
