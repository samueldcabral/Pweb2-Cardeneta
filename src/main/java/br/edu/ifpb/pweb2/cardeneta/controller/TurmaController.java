package br.edu.ifpb.pweb2.cardeneta.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/turmas")
public class TurmaController {

	@RequestMapping("/")
	public String teste() {
		return "todas-turmas";
	}
}
