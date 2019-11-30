package br.edu.ifpb.pweb2.cardeneta.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/logout")
public class LogoutController {

	@RequestMapping(method=RequestMethod.POST)
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/login";
	}

}
