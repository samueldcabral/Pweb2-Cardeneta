package br.edu.ifpb.pweb2.cardeneta.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.edu.ifpb.pweb2.cardeneta.entity.Aula;
import br.edu.ifpb.pweb2.cardeneta.repository.AulaRepository;


@Controller
@RequestMapping("/aulas")
public class AulaController {
	
	@Autowired
	private AulaRepository repository;

	@RequestMapping
	public ModelAndView list(String asssunto) {
		ModelAndView model = new ModelAndView("aula/aulas");
		asssunto = (asssunto == null) ? "" : asssunto;
//		List<Aula> aulas = repository.findByDisciplinaStartingWithIgnoreCase(asssunto);
		List<Aula> aulas = repository.findByAssunto(asssunto);
		model.addObject("aulas", aulas);
		return model;
	}
	
	@RequestMapping("/registrar")
	public String form() {
		return "aula/registro";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String save(Aula aula) {
		repository.save(aula);
		return "redirect:aulas";
	}
}
