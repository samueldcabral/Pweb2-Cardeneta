package br.edu.ifpb.pweb2.cardeneta.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/coordenador")
public class CoordenadorController {
	
	@RequestMapping(method = RequestMethod.GET)
	public String loginForm() {
		return "coordenador/coordenador";
	}

}
