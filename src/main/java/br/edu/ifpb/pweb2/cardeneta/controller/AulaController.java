package br.edu.ifpb.pweb2.cardeneta.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/aula")
public class AulaController {

	@RequestMapping("/registrar")
	public String teste() {
		return "aulaRegistro";
	}
}
