package br.edu.ifpb.pweb2.cardeneta.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/alunos")
public class AlunoController {

	@RequestMapping
	public String listar(String turma) {
		return "aulaRegistro";
	}
}