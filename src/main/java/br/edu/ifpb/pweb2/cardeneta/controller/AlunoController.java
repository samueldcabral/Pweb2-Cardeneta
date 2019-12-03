package br.edu.ifpb.pweb2.cardeneta.controller;

import java.util.List;

import br.edu.ifpb.pweb2.cardeneta.entity.Disciplina;
import br.edu.ifpb.pweb2.cardeneta.entity.Professor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.edu.ifpb.pweb2.cardeneta.entity.Aluno;
import br.edu.ifpb.pweb2.cardeneta.entity.Turma;
import br.edu.ifpb.pweb2.cardeneta.repository.AlunoRepository;
import br.edu.ifpb.pweb2.cardeneta.repository.TurmaRepository;

import javax.validation.Valid;

@Controller
@RequestMapping("/aluno")
public class AlunoController {

	@Autowired
	private TurmaRepository turmaRepository;
	
	@Autowired
	private AlunoRepository alunoRepository;
	
//	@RequestMapping()
//	public String listar(String turma) {
//		return "aulaRegistro";
//	}

	@RequestMapping(method = RequestMethod.GET)
	public String listagem(Model model){
		List<Aluno> alunos = alunoRepository.findAll();
		model.addAttribute("alunos", alunos);
		return "aluno/listarAlunos";
	}

	@RequestMapping("/registrar")
	private ModelAndView cadastrarAluno() {
		ModelAndView model = new ModelAndView("aluno/registrarAluno");
		List<Aluno> alunos = alunoRepository.findAll();

		model.addObject("alunos", alunos);
		return model;
	}

	@RequestMapping(method = RequestMethod.POST)
	public String registrarAluno(Aluno aluno, String nome, String matricula){
		aluno.setNome(nome);
		aluno.setMatricula(matricula);
		alunoRepository.saveAndFlush(aluno);
		return "redirect:/aluno";
	}
}