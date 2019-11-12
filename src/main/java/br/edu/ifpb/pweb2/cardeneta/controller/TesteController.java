package br.edu.ifpb.pweb2.cardeneta.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class TesteController {
	
	@RequestMapping("/teste")
	public String teste() {
		return "teste/index";
	}
}
