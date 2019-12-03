package br.edu.ifpb.pweb2.cardeneta.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import br.edu.ifpb.pweb2.cardeneta.entity.Aluno;
import br.edu.ifpb.pweb2.cardeneta.entity.Aula;
import br.edu.ifpb.pweb2.cardeneta.entity.Nota;
import br.edu.ifpb.pweb2.cardeneta.entity.Professor;
import br.edu.ifpb.pweb2.cardeneta.entity.Turma;
import br.edu.ifpb.pweb2.cardeneta.repository.AlunoRepository;
import br.edu.ifpb.pweb2.cardeneta.repository.TurmaRepository;

@Controller
@RequestMapping("/nota")
public class NotaController {

	@Autowired
	private TurmaRepository turmaRepository;
	
	@Autowired
	private AlunoRepository alunoRepository;
	
	
	@RequestMapping("/adicionar/{codigo}")
	public String paginaAdicionarNota(@PathVariable String codigo, Model model, HttpServletRequest request, HttpSession session) {	
		Professor professor = (Professor) session.getAttribute("professor");

        if (professor == null) {
        	return "redirect:disciplinas";        	
        } 
        Optional<Turma> opturma = turmaRepository.findTurmaByCodigo(Long.parseLong(codigo));
		Turma turma = null;
		if (opturma.isPresent()) {
			turma = opturma.get();
		}
		Long ch = turma.getDisciplina().getCargaHoraria();
		List<Nota> notas = new ArrayList<Nota>();
		if (ch < 34) {
			notas.add(new Nota(0));
		} else if (ch < 51) {
			notas.add(new Nota(0));
			notas.add(new Nota(0));
		} else {
			notas.add(new Nota(0));
			notas.add(new Nota(0));
			notas.add(new Nota(0));
		}
		List<Aluno> alunos = turma.getAlunos();
		model.addAttribute("turma", turma);
		model.addAttribute("alunos", alunos);
		model.addAttribute("notas", notas);
		model.addAttribute("nota", new Nota(0));
		return "turma/form-addnotas";
	}
	
	@RequestMapping("/registrar-nota")
	public String registrarNota(@ModelAttribute("nota") Nota nota, @ModelAttribute("turma") Turma turma) {
		System.out.println("FOIIIIII " + nota.getNota());
		System.out.println("FOIIIIII NOTAA " + turma.getNotas());
//		List<Aluno> alunos =  aula.getAlunosPresentes();
//		aulaRepository.flush();
//		for (Aluno aluno : alunos) {
//			aluno.addPresenca(aula);
//			alunoRepository.save(aluno);
//			alunoRepository.flush();
//		}
//		return "redirect:/turmas";
		
//		aula.setTurma(t);
//		t.addAulas(aula);
//		aulaRepository.save(aula);
//		turmaRepository.saveAndFlush(t);
//		aulaRepository.flush();
//		model.addAttribute("aula", aula);
//		model.addAttribute("turma", t);
		return "redirect:/turmas";
	}
}
