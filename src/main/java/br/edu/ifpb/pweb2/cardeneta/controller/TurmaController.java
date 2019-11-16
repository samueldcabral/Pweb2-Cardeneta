package br.edu.ifpb.pweb2.cardeneta.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.edu.ifpb.pweb2.cardeneta.entity.Aluno;
import br.edu.ifpb.pweb2.cardeneta.entity.Turma;
import br.edu.ifpb.pweb2.cardeneta.repository.TurmaRepository;

@Controller
@RequestMapping("/turmas")
public class TurmaController {
	
	@Autowired
	private TurmaRepository turmaRepository;

	@RequestMapping
	private ModelAndView list(HttpServletRequest request) {
		ModelAndView model = new ModelAndView("turma/turmas");
		String login = "";
		for (Cookie cookie : request.getCookies()) {
			if (cookie.getName().equals("clogin")) {
				login = cookie.getValue();
			}
		}
		List<Turma> turmas = turmaRepository.findByProfessorLogin(login);
		model.addObject("turmas", turmas);
		return model;
	}
	
	@RequestMapping(value = "/visualizarTurma/{codigo}")
	public String visualizarTurma(@PathVariable Integer codigo, Model model) {
		Optional<Turma> opturma = turmaRepository.findById(codigo);
		Turma turma = null;
		if (opturma.isPresent()) {
			turma = opturma.get();
		}
		List<Aluno> alunos = turma.getAlunos();
		System.out.println("AQYUIIIIIIIIIIIIIIIIIIIIIII "+alunos);
		model.addAttribute("turma", turma);
		model.addAttribute("alunos", alunos);
		return "turma/turma";
	}
}
