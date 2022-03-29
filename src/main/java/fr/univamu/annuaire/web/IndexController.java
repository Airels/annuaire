package fr.univamu.annuaire.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.net.http.HttpRequest;

@Controller
@RequestMapping(value = "/")
public class IndexController {

    @RequestMapping
    public String index(HttpServletRequest request) {
        request.setAttribute("message", "Hello, world!");
        return "index";
    }
}
