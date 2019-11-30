package br.edu.ifpb.pweb2.cardeneta.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.edu.ifpb.pweb2.cardeneta.entity.Disciplina;
import br.edu.ifpb.pweb2.cardeneta.entity.Professor;
import br.edu.ifpb.pweb2.cardeneta.entity.Turma;
import br.edu.ifpb.pweb2.cardeneta.repository.DisciplinaRepository;
import br.edu.ifpb.pweb2.cardeneta.repository.ProfessorRepository;
import br.edu.ifpb.pweb2.cardeneta.repository.TurmaRepository;

@Controller
@RequestMapping("/disciplinas")
public class DisciplinaController {
	
	@Autowired
	private DisciplinaRepository disciplinaRepository;
	
	@Autowired
	private TurmaRepository turmaRepository;
	
	@Autowired
	private ProfessorRepository professorRepository;
	
	@RequestMapping(method = RequestMethod.GET)
	private ModelAndView listarDisciplinas() {
		ModelAndView model = new ModelAndView("disciplina/disciplinas");
		List<Disciplina> disciplinas = disciplinaRepository.findAll();
		model.addObject("disciplinas", disciplinas);
		return model;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	private ModelAndView cadastrarDisciplina(Disciplina disciplina, String turma, String professor) {
		ModelAndView model = new ModelAndView("redirect:disciplinas");
		Optional<Turma> turmaOpt = turmaRepository.findTurmaByCodigo(Long.parseLong(turma));
		Optional<Professor> profOpt = professorRepository.findById(Long.parseLong(professor));
		
		Turma turmaNew = null;
		Professor profNew = null;
		
		if (turmaOpt.isPresent()) {
			turmaNew = turmaOpt.get();
		}
		
		if (profOpt.isPresent()) {
			profNew = profOpt.get();
		}
		
	
		turmaNew.setDisciplina(disciplina);
		turmaNew.setProfessor(profNew);
		
		Disciplina disc = disciplinaRepository.save(disciplina);
		disc.adicionarTurma(turmaNew);
		
		profNew.addTurmas(turmaNew);
		
		turmaRepository.save(turmaNew);
		disciplinaRepository.saveAndFlush(disc);
		professorRepository.saveAndFlush(profNew);
		
		return model;
	}
	
	@RequestMapping("/cadastrar")
	private ModelAndView cadastrarDisciplina() {
		ModelAndView model = new ModelAndView("disciplina/cadastrar_disciplina");
		List<Turma> turmas = turmaRepository.findAll();
		List<Professor> professores = professorRepository.findAll();
		model.addObject("turmas", turmas);
		model.addObject("professores", professores);
		return model;
	}
}
