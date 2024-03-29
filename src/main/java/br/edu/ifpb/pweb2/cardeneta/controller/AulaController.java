package br.edu.ifpb.pweb2.cardeneta.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.Cookie;
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
import br.edu.ifpb.pweb2.cardeneta.entity.Professor;
import br.edu.ifpb.pweb2.cardeneta.entity.Turma;
import br.edu.ifpb.pweb2.cardeneta.repository.AlunoRepository;
import br.edu.ifpb.pweb2.cardeneta.repository.AulaRepository;
import br.edu.ifpb.pweb2.cardeneta.repository.TurmaRepository;

@Controller
@RequestMapping("/aula")
public class AulaController {

	@Autowired
	private TurmaRepository turmaRepository;
	
	@Autowired
	private AulaRepository aulaRepository;
	
	@Autowired
	private AlunoRepository alunoRepository;
	
	@RequestMapping("/adicionar")
	public String paginaRegistro(Model model, HttpServletRequest request, HttpSession session) {
//		ModelAndView model = new ModelAndView("aula/registrar");
		String login = "";
		for (Cookie cookie : request.getCookies()) {
			if (cookie.getName().equals("clogin")) {
				login = cookie.getValue();
			}
		}
		Professor professor = (Professor) session.getAttribute("professor");

        if (professor == null) {
        	return "redirect:disciplinas";        	
        } 
        List<Turma> turmas = turmaRepository.findByProfessorId(professor.getId());
		model.addAttribute("turmas", turmas);
		return "aula/form-registro2";
	}

	@RequestMapping("/adicionar/{codigo}")
	public String paginaRegistro(@PathVariable String codigo, Model model, HttpServletRequest request, HttpSession session) {
//		ModelAndView model = new ModelAndView("aula/registrar");
		String login = "";
		for (Cookie cookie : request.getCookies()) {
			if (cookie.getName().equals("clogin")) {
				login = cookie.getValue();
			}
		}
		Professor professor = (Professor) session.getAttribute("professor");

        if (professor == null) {
        	return "redirect:disciplinas";        	
        } 
        Optional<Turma> opturma = turmaRepository.findTurmaByCodigo(Long.parseLong(codigo));
		Turma turma = null;
		if (opturma.isPresent()) {
			turma = opturma.get();
		}
		model.addAttribute("turma", turma);
		return "aula/form-registro";
	}
		
	
//	public String paginaRegistro(@ModelAttribute Turma turma, Model model, HttpServletRequest request) {
////		ModelAndView model = new ModelAndView("aula/registrar");
//		String login = "";
//		for (Cookie cookie : request.getCookies()) {
//			if (cookie.getName().equals("clogin")) {
//				login = cookie.getValue();
//			}
//		}
//        String letra = login.substring(0, 1);
//        if (!letra.equalsIgnoreCase("p")) {
//        	return "redirect:disciplinas";        	
//        } 
//		model.addAttribute("turma", turma);
//		return "aula/registrar";
//	}
	
	@RequestMapping("/registrar")
	public String registrar(Model model, String assunto, String date, String turma) {
		Aula aula = new Aula();
		aula.setAssunto(assunto);
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		Date data = null;
		try {
			data = new java.sql.Date(format.parse(date).getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		aula.setData(data);
		Optional<Turma> opT = turmaRepository.findTurmaByCodigo(Long.parseLong(turma));
		Turma t = null;
		if (opT.isPresent()) {
			t = opT.get();
		}
		aula.setTurma(t);
		t.addAulas(aula);
		aulaRepository.save(aula);
		turmaRepository.saveAndFlush(t);
		aulaRepository.flush();
		model.addAttribute("aula", aula);
		model.addAttribute("turma", t);
		return "aula/registrar-presenca";
	}
	
	@RequestMapping("/registrar-presenca")
	public String registrarPresenca(@ModelAttribute("aula") Aula aula) {
		
		List<Aluno> alunos =  aula.getAlunosPresentes();
		aulaRepository.save(aula);
		aulaRepository.flush();
		for (Aluno aluno : alunos) {
			aluno.addPresenca(aula);
			alunoRepository.save(aluno);
			alunoRepository.flush();
		}
		return "redirect:/turmas";
	}
}
