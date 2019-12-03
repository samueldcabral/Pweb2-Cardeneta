package br.edu.ifpb.pweb2.cardeneta.controller;

import org.springframework.stereotype.Controller;;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class IniciarController {

    @RequestMapping(method = RequestMethod.GET)
    public String loginForm() {
        return "redirect:/login";
}

}
