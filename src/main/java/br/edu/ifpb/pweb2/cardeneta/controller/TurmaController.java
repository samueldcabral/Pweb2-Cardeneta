package br.edu.ifpb.pweb2.cardeneta.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.edu.ifpb.pweb2.cardeneta.entity.Aluno;
import br.edu.ifpb.pweb2.cardeneta.entity.Professor;
import br.edu.ifpb.pweb2.cardeneta.entity.Turma;
import br.edu.ifpb.pweb2.cardeneta.repository.AlunoRepository;
import br.edu.ifpb.pweb2.cardeneta.repository.ProfessorRepository;
import br.edu.ifpb.pweb2.cardeneta.repository.TurmaRepository;

@Controller
@RequestMapping("/turmas")
public class TurmaController {
	
	@Autowired
	private TurmaRepository turmaRepository;
	
	@Autowired
	private AlunoRepository alunoRepository;
	
	@Autowired
	private ProfessorRepository professorRepository;

	@RequestMapping
	private ModelAndView list(HttpServletRequest request, HttpSession session) {
		ModelAndView model = new ModelAndView("turma/turmas");
		String loginId = "";
		for (Cookie cookie : request.getCookies()) {
			if (cookie.getName().equals("clogin")) {
				loginId = cookie.getValue();
			}
		}
		Professor professor = (Professor) session.getAttribute("professor");
		Professor prof = professorRepository.findByUsuarioId(professor.getId());		

		List<Turma> turmas = turmaRepository.findByProfessorId(prof.getId());
		
		model.addObject("turmas", turmas);
		return model;
	}
	
	@RequestMapping(value = "/visualizarTurma/{codigo}")
	public String visualizarTurma(@PathVariable Long codigo, Model model) {
		Optional<Turma> opturma = turmaRepository.findTurmaByCodigo(codigo);
		Turma turma = null;
		if (opturma.isPresent()) {
			turma = opturma.get();
		}
		List<Aluno> alunos = turma.getAlunos();
		model.addAttribute("turma", turma);
		model.addAttribute("alunos", alunos);
		return "turma/turma";
	}
	
	@RequestMapping("/matricular")
	public ModelAndView matricularAlunos() {
		ModelAndView model = new ModelAndView("turma/matricula");
		List<Turma> turmas = turmaRepository.findAll();
		
		model.addObject("turmas", turmas);
		
		return model;
	}
	
	@RequestMapping("/matricular/{codigo}")
	public ModelAndView matricularPorTurma(@PathVariable Long codigo) {
		ModelAndView model = new ModelAndView("turma/matriculaPorTurmas");
		
		List<Aluno> alunos = alunoRepository.findAll();
		List<Long> idAlunos = new ArrayList<Long>();
		
		Optional<Turma> opturma = turmaRepository.findTurmaByCodigo(codigo);
		Turma turma = null;
		if (opturma.isPresent()) {
			turma = opturma.get();
		}
		
		model.addObject("turma", turma);
		model.addObject("alunos", alunos);
		model.addObject("idAlunos", idAlunos);
		return model;
	}
	
	@RequestMapping(method = RequestMethod.POST, value="/matricular/{codigo}")
	public ModelAndView matricularPorTurmaPost(@PathVariable Long codigo, @RequestParam List<Long> idAlunos) {

		ModelAndView model = new ModelAndView("redirect:/turmas/matricular");
		Optional<Turma> opturma = turmaRepository.findTurmaByCodigo(codigo);
		Turma turma = null;
		if (opturma.isPresent()) {
			turma = opturma.get();
		}
		
		for(Long id : idAlunos) {
			Optional<Aluno> opaluno = alunoRepository.findById(id);
			
			if(opaluno.isPresent()) {
				turma.addAlunos(opaluno.get());
			}
		}
		
		turmaRepository.saveAndFlush(turma);
		
		return model;
		
//		List<Aluno> alunos = alunoRepository.findAll();
//		List<Long> idAlunos = new ArrayList<Long>();
//		
//		
//		
//		model.addObject("turma", turma);
//		model.addObject("alunos", alunos);
//		model.addObject("idAlunos", idAlunos);
//		return model;
	}
}
