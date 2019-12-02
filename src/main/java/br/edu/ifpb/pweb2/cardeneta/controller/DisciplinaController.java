package br.edu.ifpb.pweb2.cardeneta.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.edu.ifpb.pweb2.cardeneta.entity.Aluno;
import br.edu.ifpb.pweb2.cardeneta.entity.Aula;
import br.edu.ifpb.pweb2.cardeneta.entity.Disciplina;
import br.edu.ifpb.pweb2.cardeneta.entity.Professor;
import br.edu.ifpb.pweb2.cardeneta.entity.Turma;
import br.edu.ifpb.pweb2.cardeneta.repository.AlunoRepository;
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
	private AlunoRepository alunoRepository;
	
	@Autowired
	private ProfessorRepository professorRepository;
	
	@RequestMapping(method = RequestMethod.GET)
	private ModelAndView listarDisciplinas(HttpSession session) {
		
		ModelAndView model = new ModelAndView("disciplina/disciplinas");
		Aluno aluno = null;
		aluno = (Aluno) session.getAttribute("aluno");
		
		List<Disciplina> disciplinas = new ArrayList<Disciplina>();
		HashMap<Disciplina, Double> frequencia = new HashMap<Disciplina, Double>();
		
		if(aluno != null && aluno.getNome() != null) {
			List<Aula> aulasAluno = aluno.getPresencas();
			for(Turma turma : aluno.getTurmas()) {
				if(turma.getDisciplina() != null) {
					disciplinas.add(turma.getDisciplina());
					
					double participacao = 0;
					
					for(Aula aula : turma.getAulas()) {
						for(Aula aulaAluno : aulasAluno) {
							if(aula.getId() == aulaAluno.getId()) {
								participacao++;
							}
						}
					}
					
					int totalAulas = turma.getAulas().size();
					double porcentagemFrequencia = 0;
					
					if(totalAulas > 0) {
						porcentagemFrequencia = (participacao * 100) / totalAulas;
					}
					
					frequencia.put(turma.getDisciplina(), porcentagemFrequencia);
					
					System.out.println("turma.getAulas().size()    " + turma.getAulas().size());
					System.out.println("participacao     " +  participacao);
					System.out.println("porcentagemFrequencia    "  + porcentagemFrequencia);
				}
			}
			
		}else {
			System.out.println("parte 2");
			disciplinas = disciplinaRepository.findAll();
		}
		
		System.out.println("parte 3");
		model.addObject("disciplinas", disciplinas);
		model.addObject("frequencia", frequencia);
		return model;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	private ModelAndView cadastrarDisciplina(Disciplina disciplina, String turma, String professor, String isUpdatingForm) {
		boolean isUpdating = Boolean.parseBoolean(isUpdatingForm);
		ModelAndView model = new ModelAndView("redirect:disciplinas");
	
		if(isUpdating) {
			Disciplina discBd = disciplinaRepository.getOne(disciplina.getCodigo());
			discBd.setNome(disciplina.getNome());
			discBd.setCargaHoraria(disciplina.getCargaHoraria());
			discBd.setCurso(disciplina.getCurso());
			
			disciplinaRepository.save(discBd);
			
		}else {
			Optional<Professor> profOpt = professorRepository.findById(Long.parseLong(professor));
			Professor profNew = null;
			
			if (profOpt.isPresent()) {
				profNew = profOpt.get();
			}
			
			Turma turmaNew = new Turma();
			turmaNew.setDisciplina(disciplina);
			turmaNew.setProfessor(profNew);
			Disciplina disc = disciplinaRepository.save(disciplina);
			disc.adicionarTurma(turmaNew);
			profNew.addTurmas(turmaNew);
			
			turmaRepository.save(turmaNew);
			disciplinaRepository.saveAndFlush(disc);
			professorRepository.saveAndFlush(profNew);
		}		
		
		return model;
	}
	
	@RequestMapping("/cadastrar")
	private ModelAndView cadastrarDisciplina() {
		ModelAndView model = new ModelAndView("disciplina/cadastrar_disciplina");
		List<Turma> turmas = turmaRepository.findAll();
		List<Professor> professores = professorRepository.findAll();
		Disciplina disciplina = new Disciplina();
		model.addObject("turmas", turmas);
		model.addObject("professores", professores);
		model.addObject("disciplina", disciplina);
		model.addObject("isUpdating", false);
		return model;
	}
	
	@RequestMapping("/editar/{id}")
	private ModelAndView cadastrarDisciplina(@PathVariable("id") Long id) {
		ModelAndView model = new ModelAndView("disciplina/cadastrar_disciplina");
		List<Turma> turmas = turmaRepository.findAll();
		List<Professor> professores = professorRepository.findAll();
		Disciplina disciplina = disciplinaRepository.getOne(id);
		model.addObject("turmas", turmas);
		model.addObject("professores", professores);
		model.addObject("disciplina", disciplina);
		model.addObject("isUpdating", true);
		return model;
	}
	
	@RequestMapping("/deletar/{id}")
	private ModelAndView deletarDisciplina(@PathVariable("id") Long id) {
		ModelAndView model = new ModelAndView("redirect:/disciplinas");
		Optional<Disciplina> disciplina = disciplinaRepository.findById(id);
		
		if(disciplina.isPresent()) {
			Optional<Turma> turmaBd = turmaRepository.findByDisciplina(disciplina.get());
			if(turmaBd.isPresent()) {
				Turma turma = turmaBd.get();
				turma.setDisciplina(null);
				turma.setProfessor(null);
				turmaRepository.saveAndFlush(turma);
			}
				
			disciplinaRepository.deleteById(id);
		}
		return model;
	}
}
