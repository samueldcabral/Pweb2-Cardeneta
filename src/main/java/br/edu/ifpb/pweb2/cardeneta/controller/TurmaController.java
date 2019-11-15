package br.edu.ifpb.pweb2.cardeneta.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.edu.ifpb.pweb2.cardeneta.entity.Turma;
import br.edu.ifpb.pweb2.cardeneta.repository.TurmaRepository;

@Controller
@RequestMapping("/turmas")
public class TurmaController {
	
	@Autowired
	private TurmaRepository turmaRepository;

	@RequestMapping
	private ModelAndView list(String disciplina) {
		ModelAndView model = new ModelAndView("turma/turmas");
		List<Turma> turmas = turmaRepository.findAll();
		model.addObject("turmas", turmas);
		return model;
	}
}
