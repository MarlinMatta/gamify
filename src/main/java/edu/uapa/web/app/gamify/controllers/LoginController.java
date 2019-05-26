package edu.uapa.web.app.gamify.controllers;

import edu.uapa.web.app.gamify.domains.securities.User;
import edu.uapa.web.app.gamify.repositories.securities.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/api/login")
public class LoginController {

    @Autowired
    UserRepo userRepo;

    @RequestMapping(value = "/up", method = RequestMethod.GET)
    String up() {
        return "Login OK";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    User login(HttpServletRequest httpServletRequest) {
        String userName = httpServletRequest.getParameter("userName");
        String password = httpServletRequest.getParameter("password");
        return userRepo.findById(1L).get();
    }
}
