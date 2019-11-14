package br.edu.ifpb.pweb2.cardeneta.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class TurmaController {

	@RequestMapping("/turmas")
	public String teste() {
		return "todas-turmas";
	}
}
